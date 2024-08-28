package com.sample.url_shortener.util;

import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlUtil {

    public static boolean isURL(String input) {
        String URL_REGEX = "https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,4}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)";

        Pattern pattern = Pattern.compile(URL_REGEX);

        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    public static boolean doesUrlExist(String url) {
        URI uri;

        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        String domain = uri.getHost();

        try {
            InetAddress.getByName(domain);
        } catch (UnknownHostException e) {
            return false;
        }

        return true;
    }
}
