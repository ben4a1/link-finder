package by.paramonov.linkcount.client;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import java.io.IOException;

public class LinkSearch {

    public static Elements linkSearch(String url) {
        Elements el;
        try {
            el = Jsoup.connect(url)
                    .data("query", "Java")
                    .userAgent("Chrome")
                    .cookie("auth", "token")
                    .timeout(3000)
                    .get().select("a[href]");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return el;
    }
}
