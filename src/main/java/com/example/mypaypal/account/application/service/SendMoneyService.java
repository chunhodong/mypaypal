package com.example.mypaypal.account.application.service;

import com.example.mypaypal.account.application.port.in.SendMoneyCommand;
import com.example.mypaypal.account.application.port.in.SendMoneyUseCase;

public class SendMoneyService implements SendMoneyUseCase {
    @Override
    public boolean sendMoney(SendMoneyCommand sendMoneyCommand) {
        return false;
    }
}
