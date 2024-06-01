package com.example.mapper;

import com.example.DTO.AccountDTO;
import com.example.response.AccountResponse;
import com.example.entity.AccountEntity;
import com.example.model.AccountModel;
import com.example.utils.DateTimeUtil;
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
        account.setIsActive(true);
        account.setLastLogin(LocalDateTime.now());
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
        if (accountEntity.getRoleId() == 1) {
            accountModel.setRole("Admin");
        } else {
            accountModel.setRole("User");
        }
        accountModel.setLastLogin(DateTimeUtil.formatLocalDateTime(accountEntity.getLastLogin()));
        accountModel.setIsActive(accountEntity.getIsActive());
        accountModel.setCreatedAt(accountEntity.getCreatedAt());

        return accountModel;
    }

    public static AccountResponse mapToResponse (AccountEntity accountEntity) {
        AccountResponse accountResponse = new AccountResponse();
        accountResponse.setId(accountEntity.getId());
        accountResponse.setEmail(accountEntity.getEmail());
        accountResponse.setName(accountEntity.getName());
        if (1 != accountEntity.getRoleId()) {
            accountResponse.setRole("User");
        } else {
            accountResponse.setRole("Admin");
        }
        accountResponse.setLastLogin(DateTimeUtil.formatLocalDateTime(accountEntity.getLastLogin()));
        accountResponse.setIsActive(accountEntity.getIsActive());
        accountResponse.setCreatedAt(DateTimeUtil.formatLocalDateTime(accountEntity.getCreatedAt()));

        return accountResponse;
    }
}
