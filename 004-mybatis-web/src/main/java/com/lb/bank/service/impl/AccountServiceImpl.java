package com.lb.bank.service.impl;

import com.lb.bank.dao.AccountDao;
import com.lb.bank.dao.impl.AccountDaoImpl;
import com.lb.bank.exception.AppException;
import com.lb.bank.exception.MoneyNotEnoughException;
import com.lb.bank.pojo.Account;
import com.lb.bank.service.AccountService;
import com.lb.bank.util.GenerateDaoByJavassist;
import com.lb.bank.util.SqlSessionUtil;

public class AccountServiceImpl implements AccountService {

    // private AccountDao accountDao = new AccountDaoImpl();

    private AccountDao accountDao = (AccountDao) GenerateDaoByJavassist.getMapper(SqlSessionUtil.openSession(), AccountDao.class);
    /**
     * 实现转账功能
     *
     * @param fromActno 转出账户账号
     * @param toActno   转入账户账号
     * @param money     转账金额
     * @throws MoneyNotEnoughException 如果转出账户余额不足
     * @throws AppException            如果转账过程中发生未知错误
     */
    @Override
    public void transfer(String fromActno, String toActno, double money) throws MoneyNotEnoughException, AppException {
        // 查询转出账户的余额
        Account fromAct = accountDao.selectByActno(fromActno);
        if (fromAct.getBalance() < money) {
            throw new MoneyNotEnoughException("对不起，您的余额不足。");
        }
        try {
            // 程序如果执行到这里说明余额充足
            // 修改账户余额
            Account toAct = accountDao.selectByActno(toActno);
            fromAct.setBalance(fromAct.getBalance() - money);
            toAct.setBalance(toAct.getBalance() + money);
            // 更新数据库
            accountDao.update(fromAct);
            accountDao.update(toAct);
        } catch (Exception e) {
            throw new AppException("转账失败，未知原因！");
        }
    }

}