package com.example.utils;

import lombok.NoArgsConstructor;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.tomcat.util.codec.binary.Base64;

import java.security.MessageDigest;

@NoArgsConstructor
public class PasswordEncoderUtil {

    private static final Logger LOGGER = LogManager.getLogger(PasswordEncoderUtil.class);

    public static String encodePassword (String plainPassword) {
        String salt = "aabjsowkshwusl@nsdj#shdj;jdjVN";
        String result = null;

        plainPassword = plainPassword + salt;

        try {
            byte[] dataBytes = plainPassword.getBytes("UTF-8");
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            result = Base64.encodeBase64String(messageDigest.digest(dataBytes));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

        return result;
    }
}
