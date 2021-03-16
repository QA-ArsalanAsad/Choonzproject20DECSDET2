package com.qa.choonz.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qa.choonz.persistence.domain.User;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM USER WHERE USER_NAME =?1", nativeQuery = true)
    User userLogin(String username);

    @Modifying
    @Transactional
    @Query(value = "UPDATE USER t SET t.AUTH = ?1 WHERE t.USER_NAME =?2", nativeQuery = true)
    void insertAuth(String authToken, String username);
}
