package com.example.mypaypal.account.application.port.in;

import com.example.mypaypal.account.domain.Account.AccountId;
import com.example.mypaypal.account.domain.Money;
import lombok.Getter;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

@Getter
public class SendMoneyCommand {
    private final AccountId sourceAccountId;
    private final AccountId targetAccountId;
    private final Money money;

    public SendMoneyCommand(AccountId sourceAccountId,
                            AccountId targetAccountId,
                            Money money){
        this.sourceAccountId = sourceAccountId;
        this.targetAccountId = targetAccountId;
        this.money = money;
        requireNonNull(sourceAccountId);
        requireNonNull(targetAccountId);
        requireNonNull(money);
        requireGreaterThan(money,0);
    }

    private void requireGreaterThan(Money money,int price){
        money.isGreaterThan(Money.of(price));
    }

}
