package by.paramonov.linkcount.client;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class LinkClient {

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

    public static boolean checkUrl(String url) throws IOException {
        URL checkingUrl = new URL(url);
        HttpURLConnection huc = (HttpURLConnection) checkingUrl.openConnection();
        if (huc.getResponseCode() == HttpURLConnection.HTTP_OK) {
            return true;
        } else return false;

    }
}
