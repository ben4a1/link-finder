package by.paramonov.linkcount.controller;

import by.paramonov.linkcount.dao.LinkManagementImpl;
import by.paramonov.linkcount.model.Link;
import jakarta.annotation.PostConstruct;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.awt.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Named("controller")
@ViewScoped
public class LinkController implements Serializable {
    private static long serialVersionUID = 1L;
    @Inject
    LinkManagementImpl linkManagement;
    private List<Link> links;

    public List<Link> getLinks() {
        return linkManagement.getLinks();
    }


    public void setLinks(List<Link> links) {
        this.links = links;
    }

    private Link selectedLink;

    public Link getSelectedLink() {
        return selectedLink;
    }

    public void setSelectedLink(Link selectedLink) {
        this.selectedLink = selectedLink;
    }

    private String linkName;

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //TODO variable 'userAgent in 'doc' initialize
    public void buttonAnalyze() throws IOException {
//        Desktop
        linkManagement.setLinks(new CopyOnWriteArrayList<>());
        Document doc = Jsoup.connect(this.url)
                .data("query", "Java")
                .userAgent("Chrome")
                .cookie("auth", "token")
                .timeout(3000)
                .get();
        Elements elements = doc.select("a[href]");
        for (Element element : elements) {
            if (element.attr("href").startsWith("https://")) {
                linkManagement.addLink(new Link(element.attr("href"),
                        element.text()));
            }
        }
    }

        @PostConstruct
        public void init() {
        selectedLink = new Link();
    }
}
