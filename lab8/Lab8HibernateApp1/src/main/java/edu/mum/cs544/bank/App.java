package edu.mum.cs544.bank;

import edu.mum.cs544.bank.domain.Account;
import edu.mum.cs544.bank.domain.AccountEntry;
import edu.mum.cs544.bank.domain.Customer;
import edu.mum.cs544.bank.service.IAccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Collection;


public class App {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

        IAccountService accountService = context.getBean(IAccountService.class);
        accountService.createAccount(123, "Tina Xing");
        accountService.createAccount(234, "John Doe");
        accountService.deposit(123, 999999);
        accountService.deposit(234, 529);
        accountService.withdrawEuros(123, 230);
        //use account 2;
        accountService.deposit(234, 12450);
        accountService.depositEuros(123, 234234234);
        accountService.transferFunds(123, 234, 200, "payment of invoice 2019128-1");
        // show balances

        Collection<Account> accountlist = accountService.getAllAccounts();
        Customer customer = null;
        for (Account account : accountlist) {
            customer = account.getCustomer();
            System.out.println("Statement for Account: " + account.getAccountnumber());
            System.out.println("Account Holder: " + customer.getName());
            System.out.println("-Date-------------------------"
                    + "-Description------------------"
                    + "-Amount-------------");
            for (AccountEntry entry : account.getEntryList()) {
                System.out.printf("%30s%30s%20.2f\n", entry.getDate()
                        .toString(), entry.getDescription(), entry.getAmount());
            }
            System.out.println("----------------------------------------"
                    + "----------------------------------------");
            System.out.printf("%30s%30s%20.2f\n\n", "", "Current Balance:",
                    account.getBalance());
        }
    }

}


