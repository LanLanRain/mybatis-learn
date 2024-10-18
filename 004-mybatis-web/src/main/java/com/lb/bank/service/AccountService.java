package com.lb.bank.service;

import com.lb.bank.exception.AppException;
import com.lb.bank.exception.MoneyNotEnoughException;

public interface AccountService {

    /**
     * 银行账户转正
     *
     * @param fromActno 转出账户
     * @param toActno   转入账户
     * @param money     转账金额
     * @throws MoneyNotEnoughException 余额不足异常
     * @throws AppException            App发生异常
     */
    void transfer(String fromActno, String toActno, double money) throws MoneyNotEnoughException, AppException;
}
