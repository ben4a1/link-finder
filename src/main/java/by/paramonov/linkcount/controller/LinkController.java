package by.paramonov.linkcount.controller;

import by.paramonov.linkcount.client.LinkClient;
import by.paramonov.linkcount.dao.LinkManagementImpl;
import by.paramonov.linkcount.model.Link;
import jakarta.annotation.PostConstruct;
import lombok.SneakyThrows;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.*;
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

    /**
     * Происходит анализ введённой ссылки при нажатии.
     */
    @SneakyThrows
    public void buttonAnalyze() {
        if (LinkClient.checkUrl(this.url)) {
            linkManagement.setLinks(new CopyOnWriteArrayList<>());
            Map<String, String> mapOfUrls = LinkClient.findLinksForResource(this.url);
            Iterator iterator = mapOfUrls.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry pair = (Map.Entry) iterator.next();
                linkManagement.addLink(new Link(pair.getKey().toString(),
                        pair.getValue().toString()));
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
     *
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
