package beilaya.javademo.service;

public interface AccountService {
    void transfer(String from, String to, int amount) throws ServiceException, AccountException;
}
