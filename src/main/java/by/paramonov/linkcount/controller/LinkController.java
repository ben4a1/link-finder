package by.paramonov.linkcount.controller;

import by.paramonov.linkcount.dao.LinkManagementInMemory;
import by.paramonov.linkcount.model.Link;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@Named("controller")
@ViewScoped
public class LinkController implements Serializable {
    private static long serialVersionUID = 1L;
    @Inject
    LinkManagementInMemory linkManagement;
    private Link selectedLink;
    public Link getSelectedLink() {
        return selectedLink;
    }
    public void setSelectedLink(Link selectedLink) {
        this.selectedLink = selectedLink;
    }

    private List<Link> links;
    public List<Link> getLinks(){
        return linkManagement.getLinks();
    }
    public void setLinks(List<Link> links) {
        try {
            this.links = findLinks("");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

    public List<Link> findLinks(String url) throws IOException {
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
    public void buttonCheckLink(){
//        selectedLink
        FacesMessage message = new FacesMessage("Идёт анализ", "пока так");
        message.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }


}
