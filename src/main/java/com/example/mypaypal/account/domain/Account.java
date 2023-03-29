package com.example.mypaypal.account.domain;

import lombok.Value;

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

        return false;
    }

    private boolean myWithdraw(Money money){
        return Money.add(this.calculateBalance(),money.negate()).isPositive();
    }

    @Value
    public static class AccountId {
        private Long value;
    }

}
