package com.example.repository;

import com.example.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Integer> {

    @Query(value = "SELECT * FROM account a WHERE a.email = :email AND a.password_hash = :password", nativeQuery = true)
    AccountEntity findOneByEmailAndPasswordHash(@Param("email") String email, @Param("password") String password);

    @Query(value = "SELECT * FROM account a where a.role_id <> 1", nativeQuery = true)
    List<AccountEntity> findUsers();

    boolean existsByEmail(String email);

    @Query(value = "select * from account a where a.id = :id", nativeQuery = true)
    AccountEntity findOneById (Integer id);
}
