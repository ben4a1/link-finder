package by.paramonov.linkcount.controller;

import by.paramonov.linkcount.client.LinkSearch;
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
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

@Named("controller")
@ViewScoped
public class LinkController implements Serializable {
    private static long serialVersionUID = 1L;
    private final static String URL_REGEX = "/^((http|https|ftp):\\/\\/)?(([A-Z0-9][A-Z0-9_-]*)(\\.[A-Z0-9][A-Z0-9_-]*)+)/i";
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

    public void buttonAnalyze() {
        linkManagement.setLinks(new CopyOnWriteArrayList<>());
        Elements elements = LinkSearch.linkSearch(this.url);
        for (Element element : elements) {
            Link tempLink = new Link();
            Set<String> tempSetOfUrl = new HashSet<>();
            String tempUrl = element.attr("href").trim();
            if (tempUrl.startsWith("https://")
                    || tempUrl.startsWith("http://")) {
                tempLink.setUrl(tempUrl);
            } else if (tempUrl.length() == 1
                    || tempUrl.length() == 0) {
                tempLink.setUrl(url);
            } else {
                tempLink.setUrl(url + tempUrl.substring(1, (tempUrl.length() - 1)));
            }
            tempLink.setLinkName(element.text());
            if (tempSetOfUrl.add(tempLink.getUrl())) {
                linkManagement.addLink(tempLink);
            }
        }
    }

    @PostConstruct
    public void init() {
        selectedLink = new Link();
    }
}
