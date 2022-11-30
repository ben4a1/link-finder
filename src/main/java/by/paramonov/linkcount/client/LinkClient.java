package by.paramonov.linkcount.client;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class LinkClient {

    /**
     *
     * @param url адрес анализируемой WEB-страницы.
     * @return List элементов со списками атрибутов для каждого.
     */
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

    /**
     *
     * @param url адрес анализируемой WEB-страницы.
     * @return true, если запрос к @url выполнен успешно.
     * @throws IOException если @url недоступен или не существует
     */
    public static boolean checkUrl(String url) throws IOException {
        URL checkingUrl = new URL(url);
        HttpURLConnection huc = (HttpURLConnection) checkingUrl.openConnection();
        return huc.getResponseCode() == HttpURLConnection.HTTP_OK;
    }
}
