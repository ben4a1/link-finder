package by.paramonov.linkcount.model;

public class Link {
    private Long id;
    private String url;
    private String linkName;
    private boolean complete;

    public Link() {
    }

    public Link(String url, String linkName, boolean complete) {
        this.url = url;
        this.linkName = linkName;
        this.complete = complete;
    }

    public Link(String url, String linkName) {
        this.url = url;
        this.linkName = linkName;
        this.complete = false;
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
