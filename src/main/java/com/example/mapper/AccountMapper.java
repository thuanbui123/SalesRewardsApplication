package com.example.mapper;

import com.example.DTO.AccountDTO;
import com.example.entity.AccountEntity;
import com.example.entity.RoleEntity;
import com.example.model.AccountModel;
import com.example.utils.PasswordEncoderUtil;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AccountMapper {

    public AccountEntity mapDtoToEntity (AccountDTO accountDTO) {

        String passwordHash = PasswordEncoderUtil.encodePassword(accountDTO.getPassword());

        AccountEntity account = new AccountEntity();
        account.setEmail(accountDTO.getEmail());
        account.setPasswordHash(passwordHash);
        return account;
    }

    public AccountEntity mapModelToEntity (AccountModel accountModel) {
        AccountEntity account = new AccountEntity();


        String passwordHash = PasswordEncoderUtil.encodePassword(accountModel.getPassword());

        account.setName(accountModel.getName());
        account.setEmail(accountModel.getEmail());
        account.setPasswordHash(passwordHash);
        account.setRoleId(2);
        account.setCreatedAt(LocalDateTime.now());
        return account;
    }

    public static AccountDTO mapToDTO (AccountEntity accountModel) {
        AccountDTO account = new AccountDTO();

        account.setUsername(accountModel.getName());
        account.setEmail(accountModel.getEmail());
        account.setPassword("");
        return account;
    }

    public static AccountModel mapEntityToModel (AccountEntity accountEntity) {
        AccountModel accountModel = new AccountModel();

        accountModel.setId(accountEntity.getId());
        accountModel.setEmail(accountEntity.getEmail());
        accountModel.setName(accountEntity.getName());
        accountModel.setPassword(accountEntity.getPasswordHash());
        accountModel.setRoleId(accountEntity.getRoleId());
        accountModel.setCreatedAt(accountEntity.getCreatedAt());

        return accountModel;
    }
}
