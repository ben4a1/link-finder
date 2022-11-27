package by.paramonov.linkcount.controller;

import by.paramonov.linkcount.model.Link;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class JsoupFindLink {

    public static void main(String[] args) throws IOException {
        Link link = new Link("https://github.com/");
        Set<String> links = findLinks(link.getUrl());
        System.out.println(links);
    }

    public static Set<String> findLinks(String url) throws IOException {
        Set<String> links = new HashSet<>();
        Document doc = Jsoup.connect(url)
                .data("query", "Java")
                .userAgent("Chrome")
                .cookie("auth", "token")
                .timeout(3000)
                .get();
        System.out.println(doc.title());


        Elements elements = doc.select("a[href]");
        for (Element element : elements) {
            if (!element.attr("href").startsWith("https://")) {
                links.add(element.attr(url + "href"));
            } else links.add(element.attr("href"));
        }
        return links;
    }
}
