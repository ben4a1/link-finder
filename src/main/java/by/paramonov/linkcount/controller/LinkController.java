package by.paramonov.linkcount.controller;

import by.paramonov.linkcount.dao.LinkManagementInMemory;
import by.paramonov.linkcount.model.Link;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
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
        this.links = links;
    }

    private List<Link> selectedLinks;
    public List<Link> getSelectedLinks() {
        return selectedLinks;
    }
    public void setSelectedLinks(List<Link> selectedLinks) {
        this.selectedLinks = selectedLinks;
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

    public void buttonCheckLink(){
        FacesMessage message = new FacesMessage("Идёт анализ", "пока так");
        message.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }



}
