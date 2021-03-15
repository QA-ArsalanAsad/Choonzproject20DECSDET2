package com.qa.choonz.utils;

import com.qa.choonz.persistence.domain.User;
import org.apache.commons.codec.digest.DigestUtils;

import java.time.LocalDateTime;

public class Token {

    public static String generateToken(User user) {
        LocalDateTime localDateTime = LocalDateTime.now();
        String toEncode = localDateTime.toString() + user.getUserName();
        String hash = DigestUtils.sha256Hex(toEncode);
        return hash;
    }
}
