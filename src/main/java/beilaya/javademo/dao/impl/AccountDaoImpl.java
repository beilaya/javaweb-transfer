package beilaya.javademo.dao.impl;

import beilaya.javademo.dao.AccountDao;
import beilaya.javademo.dao.DaoException;
import beilaya.javademo.domain.Account;
import beilaya.javademo.util.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

public class AccountDaoImpl implements AccountDao {
    @Override
    public Account getAccountByUsername(String username) throws DaoException {
        Account account = null;
        SqlSession sqlSession = null;
        try {
            sqlSession = SqlSessionUtil.getSqlSession();
            account = sqlSession.selectOne("Account.selectByUsername", username);
        } catch (Exception e) {
            throw new DaoException("DAO：无法查询用户信息");
        }
        return account;
    }

    @Override
    public int updateAccount(Account account) throws DaoException {
        int rows = 0;
        try {
            SqlSession sqlSession = SqlSessionUtil.getSqlSession();
            rows = sqlSession.update("Account.updateById", account);
        } catch (Exception e) {
            throw new DaoException(e.getMessage());
        }
        return rows;
    }
}
