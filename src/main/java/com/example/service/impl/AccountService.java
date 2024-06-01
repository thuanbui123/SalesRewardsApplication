package com.example.service.impl;

import com.example.DTO.AccountDTO;
import com.example.response.AccountResponse;
import com.example.entity.AccountEntity;
import com.example.mapper.AccountMapper;
import com.example.model.AccountModel;
import com.example.repository.AccountRepository;
import com.example.service.IAccountService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
            if (existsAccount != null) {
                existsAccount.setLastLogin(LocalDateTime.now());
                accountRepository.save(existsAccount);
            }
            return existsAccount;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    @Override
    public List<AccountResponse> findUsers() {
        try {
            List<AccountEntity> accountEntities = accountRepository.findUsers();
            List<AccountResponse> accountResponses = new ArrayList<>();

            if (accountEntities != null) {
                for (AccountEntity account : accountEntities) {
                    accountResponses.add(AccountMapper.mapToResponse(account));
                }
            }

            return accountResponses;

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    public AccountEntity findOneById(Integer id) {
        return accountRepository.findOneById(id);
    }

    @Override
    @Transactional
    public boolean lockOrUnlockAccount(Integer id, boolean isLock) {
        try {
            AccountEntity accountEntity = accountRepository.findOneById(id);
            accountEntity.setIsActive(isLock);
            accountRepository.save(accountEntity);
            return true;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return false;
        }
    }

    @Override
    @Transactional
    public int deleteAccount(Integer id) {
        try {
            AccountEntity accountEntity = accountRepository.findOneById(id);
            if (accountEntity != null) {
                accountRepository.delete(accountEntity);
                AccountEntity afterDelete = accountRepository.findOneById(id);
                if (afterDelete != null) {
                    return 0;
                }
                return 1;
            }
            return 2;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return -1;
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

    @Transactional
    public ResponseEntity<?> lockOrUnlock (Integer id, boolean isLock) {
        boolean isEdit = lockOrUnlockAccount(id, isLock);
        if (isEdit) {
            return new ResponseEntity<>("True", HttpStatus.OK);
        }
        return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Transactional
    public ResponseEntity<?> deleteData(Integer id) {
        int delete = deleteAccount(id);
        if (delete == -1) {
            return new ResponseEntity<>("System error", HttpStatus.INTERNAL_SERVER_ERROR);
        } else if (delete == 0) {
            return new ResponseEntity<>("False", HttpStatus.BAD_REQUEST);
        } else if (delete == 1) {
            return new ResponseEntity<>("True", HttpStatus.OK);
        }
        return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
    }
}
