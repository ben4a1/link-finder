package by.paramonov.linkcount.controller;

import by.paramonov.linkcount.model.Link;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class JsoupFindLink {

    public static void main(String[] args) throws IOException {
        Link link = new Link("https://github.com/");
        Map<String, String> links = findLinks(link.getUrl());
        System.out.println(links);
    }

    public static Map<String, String> findLinks(String url) throws IOException {
        Map<String, String> links = new HashMap<>();
        Document doc = Jsoup.connect(url)
                .data("query", "Java")
                .userAgent("Chrome")
                .cookie("auth", "token")
                .timeout(3000)
                .get();


        Elements elements = doc.select("a[href]");
        for (Element element : elements) {

            if (element.attr("href").startsWith("https://")) {
                links.put(element.text(),
                        element.attr("href"));
            }
        }
        return links;
    }
}
