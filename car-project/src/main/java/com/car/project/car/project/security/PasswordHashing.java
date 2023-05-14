package com.car.project.car.project.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHashing {

    public static String hashPassword(String password) {

        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance("SHA256");
            messageDigest.update(password.getBytes());

            byte[] passByte = messageDigest.digest();

            StringBuilder sb = new StringBuilder();

            for (byte b : passByte) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }
}
