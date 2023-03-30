package com.example.mypaypal.account.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.time.LocalDateTime;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Account {

    private AccountId id;
    private Money baselineBalance;
    private ActivityWindow activityWindow;

    public Money calculateBalance(){
        return Money.add(this.baselineBalance,this.activityWindow.calculateBalance(this.id));
    }

    public boolean withdraw(Money money,AccountId targetAccountId){
        if(!myWithdraw(money)){
            return false;
        }
        Activity withdrawal = new Activity(this.id,this.id,targetAccountId, LocalDateTime.now(),money);
        this.activityWindow.addActivity(withdrawal);
        return true;
    }

    private boolean myWithdraw(Money money){
        return Money.add(this.calculateBalance(),money.negate()).isPositive();
    }

    public boolean deposit(Money money,AccountId sourceAccountId){
        Activity deposit = new Activity(
                this.id,
                sourceAccountId,
                this.id,
                LocalDateTime.now(),
                money
        );
        this.activityWindow.addActivity(deposit);
        return true;
    }

    @Value
    public static class AccountId {
        private Long value;
    }
}
