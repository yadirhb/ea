package edu.mum.cs544.bank.domain;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;


@NoArgsConstructor
@Entity
public class Account {
    @Setter
    @Getter
    @Id
    private long accountnumber;

    @Getter
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "accountnumber")
    private Collection<AccountEntry> entryList = new ArrayList<AccountEntry>();

    @Setter
    @Getter
    @OneToOne(cascade = CascadeType.ALL)
    private Customer customer;

    public Account(long accountnr) {
        this.accountnumber = accountnr;
    }


    public double getBalance() {
        double balance = 0;
        for (AccountEntry entry : entryList) {
            balance += entry.getAmount();
        }
        return balance;
    }

    public void deposit(double amount) {
        AccountEntry entry = new AccountEntry(new Date(), amount, "deposit", "", "");
        entryList.add(entry);
    }

    public void withdraw(double amount) {
        AccountEntry entry = new AccountEntry(new Date(), -amount, "withdraw", "", "");
        entryList.add(entry);
    }

    private void addEntry(AccountEntry entry) {
        entryList.add(entry);
    }

    public void transferFunds(Account toAccount, double amount, String description) {
        AccountEntry fromEntry = new AccountEntry(new Date(), -amount, description, "" + toAccount.getAccountnumber(), toAccount.getCustomer().getName());
        AccountEntry toEntry = new AccountEntry(new Date(), amount, description, "" + toAccount.getAccountnumber(), toAccount.getCustomer().getName());
        entryList.add(fromEntry);
        toAccount.addEntry(toEntry);
    }


}
