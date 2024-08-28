package com.sample.url_shortener.util;

import java.security.SecureRandom;

public class RandomStringGenerator {

    private static final String BASE62_ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final SecureRandom secureRandom = new SecureRandom();


    public static String generateRandomString(int length) {
        StringBuilder randomString = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int randomIndex = secureRandom.nextInt(BASE62_ALPHABET.length());
            randomString.append(BASE62_ALPHABET.charAt(randomIndex));
        }

        return randomString.toString();
    }
}
