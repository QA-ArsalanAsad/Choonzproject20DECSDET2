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

    // This Query sets the Auth token of a user to an empty string ('')
    // this signifies that the user has logged out
    @Modifying
    @Transactional
    @Query(value = "UPDATE USER t set t.AUTH = '' WHERE t.AUTH =?1", nativeQuery = true)
    void removeAuth(String authToken);

    // This Query searches for users by Auth token, this allows us to identify
    // a user by their auth token instead of awkwardly passing around a username
    // and password everytime we wanted to get user information
    @Query(value = "SELECT * FROM USER WHERE AUTH =?1", nativeQuery = true)
    User searchByAuth(String authToken);
}
