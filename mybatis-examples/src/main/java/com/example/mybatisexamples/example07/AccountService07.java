package com.example.mybatisexamples.example07;

import com.example.mybatisexamples.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountService07 {
    @Autowired
    private AccountMapper07 accountMapper07;
    public Account buy(Integer uid, float expense) {
        Account account = accountMapper07.selectById(uid);
        float balance = account.getBalance() - expense;
        if (balance < 0) {
            throw new RuntimeException("余额不足");
        }
        account.setBalance(balance);
        // 返回影响记录的行数。0，没有更新记录
        int update = accountMapper07.updateById(account);
        if (update == 0) {
            throw new RuntimeException("乐观锁，余额不足");
        }
        return account;
    }
}
