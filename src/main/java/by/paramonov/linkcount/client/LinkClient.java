package by.paramonov.linkcount.client;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class LinkClient {

    private final static String URL_REGEX = "/^((http|https|ftp):\\/\\/)?(([A-Z0-9][A-Z0-9_-]*)(\\.[A-Z0-9][A-Z0-9_-]*)+)/i";
    /**
     * @param url адрес анализируемой WEB-страницы (URL должен начинаться 'https://' или 'http://').
     * @return Map<url, nameUrl>.
     */
    public static Map<String, String> findLinksForResource(String url) {
        Map<String, String> mapOfUrls = new HashMap<>();
        Elements elements;
        try {
            elements = Jsoup.connect(url)
                    .data("query", "Java")
                    .userAgent("Chrome")
                    .cookie("auth", "token")
                    .timeout(3000)
                    .get().select("a[href]");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (Element element : elements) {
            String tempUrl = element.attr("href");
            String tempText = element.text();
            if (tempUrl.startsWith("https://")
                    || tempUrl.startsWith("http://")) {
                mapOfUrls.put(tempUrl, tempText);
            } else if (tempUrl.length() == 1
                    || tempUrl.length() == 0) {
                mapOfUrls.put(url, tempText);
            } else {
                mapOfUrls.put(url + tempUrl.substring(1, (tempUrl.length() - 1)),
                        tempText);
            }
        }

        return mapOfUrls;
    }

    /**
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
