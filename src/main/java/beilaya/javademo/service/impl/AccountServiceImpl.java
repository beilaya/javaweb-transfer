package beilaya.javademo.service.impl;

import beilaya.javademo.dao.AccountDao;
import beilaya.javademo.dao.DaoException;
import beilaya.javademo.dao.impl.AccountDaoImpl;
import beilaya.javademo.domain.Account;
import beilaya.javademo.service.AccountException;
import beilaya.javademo.service.AccountService;
import beilaya.javademo.service.ServiceException;

public class AccountServiceImpl implements AccountService {

    private static final AccountDao ACOUNT_DAO = new AccountDaoImpl();

    @Override
    public void transfer(String from, String to, int amount) throws ServiceException, AccountException {
        try {
            // 查看转账人的信息
            Account fromAccount = ACOUNT_DAO.getAccountByUsername(from);

            // 确认转账人的余额是否足够本次转账业务
            if (fromAccount.getBalance() < amount) { // 不够 -> 余额不足
                throw new AccountException("转账失败：余额不足");
            }

            // 足够 将转账人的余额更新为 -> 余额 - 转账金额
            fromAccount.setBalance(fromAccount.getBalance() - amount);
            ACOUNT_DAO.updateAccount(fromAccount);

            // 查看收款人的信息
            Account toAccount = ACOUNT_DAO.getAccountByUsername(to);
            // 将收款人的余额更新为 -> 余额 + 收到的转账金额
            toAccount.setBalance(toAccount.getBalance() + amount);
            ACOUNT_DAO.updateAccount(toAccount);

        } catch (DaoException e) {
            throw new ServiceException("转账失败：服务器繁忙");
        } catch (AccountException e) {
            throw new AccountException(e.getMessage());
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
