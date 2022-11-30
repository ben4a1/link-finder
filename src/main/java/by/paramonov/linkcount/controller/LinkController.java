package by.paramonov.linkcount.controller;

import by.paramonov.linkcount.client.LinkClient;
import by.paramonov.linkcount.dao.LinkManagementImpl;
import by.paramonov.linkcount.model.Link;
import jakarta.annotation.PostConstruct;
import lombok.SneakyThrows;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.HashSet;
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

    /**
     * Происходит анализ введённой ссылки при нажатии.
     * URL должен начинаться 'https://' или 'http://'
     */
    @SneakyThrows
    public void buttonAnalyze() {
        if (LinkClient.checkUrl(this.url)) {
            linkManagement.setLinks(new CopyOnWriteArrayList<>());
            Elements elements = LinkClient.linkSearch(this.url);
            Set<String> tempSetOfUrl = new HashSet<>();
            for (Element element : elements) {
                Link tempLink = new Link();
                String tempUrl = element.attr("href");
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
        } else {
            FacesContext.getCurrentInstance().addMessage("string",
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "URL is not valid", "Please, enter valid URL"));
        }
    }

    /**
     * Происходит очистка формы обнаруженных ссылок.
     */
    public void buttonClearTable() {
        linkManagement.clearLinks();
    }

    /**
     * происходит подстановка URL найденной страницы в строку для анализа.
     * @param url
     */
    public void buttonSetUrl(String url) {
        this.setUrl(url);
    }

    @PostConstruct
    public void init() {
        selectedLink = new Link();
    }
}
