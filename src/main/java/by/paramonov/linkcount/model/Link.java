package by.paramonov.linkcount.model;

import lombok.Data;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.io.Serializable;


@Data
public class Link implements Serializable {

    private static final long serialVersionUID = 6437012982370705547L;
    private Long id;
    private String url;
    private String linkName;

    public Link() {
    }

    public Link(String url) {
        Document document;
        this.url = url;
        try {
            document = Jsoup.connect(url).get();
            this.linkName = document.text();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Link(String url, String linkName) {
        this.url = url;
        this.linkName = linkName;
    }

    @Override
    public String toString(){
        return String.format("Link[id='%d', name='%s', URL='%s'",
                id, linkName, url);
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
}
