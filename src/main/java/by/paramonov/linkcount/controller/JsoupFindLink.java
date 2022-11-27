package by.paramonov.linkcount.controller;

import by.paramonov.linkcount.model.Link;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

public class JsoupFindLink {
    public static List<Link> findLinks(String url) throws IOException {
        List<Link> links = new LinkedList<>();
        Document doc = Jsoup.connect(url)
                .data("query", "Java")
                .userAgent("Chrome")
                .cookie("auth", "token")
                .timeout(3000)
                .get();


        Elements elements = doc.select("a[href]");
        for (Element element : elements) {
            if (element.attr("href").startsWith("https://")) {
                links.add(new Link(element.attr("href"),
                        element.text()));
            }
        }
        return links;
    }
}
