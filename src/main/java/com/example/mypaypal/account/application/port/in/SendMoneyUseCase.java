package com.example.mypaypal.account.application.port.in;

public interface SendMoneyUseCase {
    boolean sendMoney(SendMoneyCommand sendMoneyCommand);
}
