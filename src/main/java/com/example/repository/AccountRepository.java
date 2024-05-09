package com.example.repository;

import com.example.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Integer> {

    @Query(value = "SELECT * FROM account a WHERE a.email = :email AND a.password_hash = :password", nativeQuery = true)
    AccountEntity findOneByEmailAndPasswordHash(@Param("email") String email, @Param("password") String password);

    boolean existsByEmail(String email);
}
