package com.example.service;

import com.example.DTO.AccountDTO;
import com.example.entity.AccountEntity;
import com.example.model.AccountModel;

public interface IAccountService {
    public int register (AccountModel accountModel);

    public AccountEntity login (AccountDTO accountDTO);
}
