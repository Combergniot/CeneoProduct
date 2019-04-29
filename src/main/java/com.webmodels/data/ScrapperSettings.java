package com.webmodels.data;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class ScrapperSettings {

    private final String USER_AGENT =
            "Mozilla/5.0 (Windows NT 6.1; WOW64) " +
                    "AppleWebKit/537.36 (KHTML, like Gecko) " +
                    "Chrome/45.0.2454.101 Safari/537.36";
    private final String REFERRER = "http://www.google.com";
//    Do ustawienia jesli to konieczne
    private final String PROXY_HOST = "";
    private final int PROXY_PORT = 8080;
    private final int TIMEOUT = 10 * 1000;

    protected Document connectWith(String link) throws IOException {
        Document document = Jsoup.connect(link)
//                .proxy(PROXY_HOST, PROXY_PORT)
                .userAgent(USER_AGENT)
                .referrer(REFERRER)
                .timeout(TIMEOUT)
                .ignoreHttpErrors(true)
                .followRedirects(true)
                .get();
        return document;
    }


}
