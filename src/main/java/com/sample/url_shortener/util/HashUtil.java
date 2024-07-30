package com.sample.url_shortener.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtil {

    private static final String BASE62_ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";


    public static String generateHashForUrl(String url) {
        // Generate SHA-256 hash first
        byte[] hashBytes = toSHA256(url);

        // Turn it to Base62 then
        return toBase62(hashBytes);
    }

    private static byte[] toSHA256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            return digest.digest(input.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Failed to generate SHA-256 hash", e);
        }
    }

    private static String toBase62(byte[] bytes) {
        // Turn byte array to a number
        BigInteger number = new BigInteger(1, bytes);

        // Generate Base62 hash
        StringBuilder base62 = new StringBuilder();
        while (number.compareTo(BigInteger.ZERO) > 0) {
            BigInteger[] divMod = number.divideAndRemainder(BigInteger.valueOf(BASE62_ALPHABET.length()));
            number = divMod[0];
            int index = divMod[1].intValue();
            base62.append(BASE62_ALPHABET.charAt(index));
        }

        // Reverse a hash so its symbols' order is correct
        return base62.reverse().toString();
    }
}
