package by.paramonov.linkcount.dao;

import by.paramonov.linkcount.model.Link;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@ApplicationScoped
public class LinkManagementImpl implements ILinkManagement {
    private CopyOnWriteArrayList<Link> links = new CopyOnWriteArrayList<Link>();
    public void setLinks(CopyOnWriteArrayList<Link> links) {
        this.links = links;
    }

    public CopyOnWriteArrayList<Link> getLinks() {
        return links;
    }

    @Override
    public void addLink(Link link) {
        synchronized (this) {
            int size = links.size();
            long id = 1;
            if (size != 0) {
                id = links.get(size - 1).getId();
                id++;
            }
            link.setId(id);
            links.add(link);
        }
    }
   public void analyzeLink(List<Link> linkList) {
        linkList.stream().forEach(link -> {
            synchronized (this) {
                links.set(link.getId().intValue(), link);
            }
        });
   }
}
