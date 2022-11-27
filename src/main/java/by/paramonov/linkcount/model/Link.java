package by.paramonov.linkcount.model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class Link {
    private Long id;
    private String url;
    private String linkName;
    private boolean complete;

    public Link() {
    }

    public Link(String url) {
        Document document;
        this.url = url;
        try {
            document = Jsoup.connect(url).get();
            this.linkName = document.title();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString(){
        return String.format("Link[id='%d', name='%s', URL='%s', complete='%b'",
                id, linkName, url, complete);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }
}
