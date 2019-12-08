package edu.mum.cs544.bank.dao;

import edu.mum.cs544.bank.domain.Account;

import java.util.Collection;


public interface IAccountDAO {
    void saveAccount(Account account);

    void updateAccount(Account account);

    Account loadAccount(long accountnumber);

    Collection<Account> getAccounts();
}
