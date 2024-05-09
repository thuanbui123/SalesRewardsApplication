package com.example.service.impl;

import com.example.DTO.AccountDTO;
import com.example.entity.AccountEntity;
import com.example.mapper.AccountMapper;
import com.example.model.AccountModel;
import com.example.repository.AccountRepository;
import com.example.service.IAccountService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class AccountService implements IAccountService {

    private static final Logger LOGGER = LogManager.getLogger(AccountService.class);

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public int register(AccountModel accountModel) {
        try {
            AccountEntity account = new AccountMapper().mapModelToEntity(accountModel);

            boolean isExists = accountRepository.existsByEmail(account.getEmail());

            if (!isExists) {
                AccountEntity saveAccount = accountRepository.save(account);
                return 1;
            }
            return 0;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return -1;
        }
    }

    @Override
    public AccountEntity login(AccountDTO accountDTO) {
        try {
            AccountEntity account = new AccountMapper().mapDtoToEntity(accountDTO);
            AccountEntity existsAccount = accountRepository.findOneByEmailAndPasswordHash(account.getEmail(), account.getPasswordHash());

            return existsAccount;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    public ResponseEntity<?> signUp (AccountModel accountModel) {
        int register = register(accountModel);
        if (register == 1) {
            return new ResponseEntity<>("Account registration successful", HttpStatus.CREATED);
        } else if (register == 0) {
            return new ResponseEntity<>("Email is already in use", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Internal sever error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<?> signIn (AccountDTO accountDTO) {
        AccountEntity login = login(accountDTO);
        if (login != null) {
            return new ResponseEntity<>(AccountMapper.mapToDTO(login), HttpStatus.OK);
        }
        return new ResponseEntity<>("Login failed", HttpStatus.BAD_REQUEST);
    }
}
