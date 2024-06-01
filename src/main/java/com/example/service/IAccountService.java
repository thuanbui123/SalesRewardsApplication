package com.example.service;

import com.example.DTO.AccountDTO;
import com.example.response.AccountResponse;
import com.example.entity.AccountEntity;
import com.example.model.AccountModel;

import java.util.List;

public interface IAccountService {
    int register (AccountModel accountModel);

    AccountEntity login (AccountDTO accountDTO);

    List<AccountResponse> findUsers();

    boolean lockOrUnlockAccount(Integer id, boolean isLock);

    int deleteAccount (Integer id);
}
