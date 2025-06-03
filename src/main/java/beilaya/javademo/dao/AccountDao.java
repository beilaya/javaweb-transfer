package beilaya.javademo.dao;

import beilaya.javademo.domain.Account;

public interface AccountDao {
    Account getAccountByUsername(String username) throws DaoException;
    int updateAccount(Account account) throws DaoException;
}
