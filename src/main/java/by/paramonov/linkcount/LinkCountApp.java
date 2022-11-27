package by.paramonov.linkcount;

import by.paramonov.linkcount.controller.LinkController;
import by.paramonov.linkcount.model.Link;
import org.primefaces.PrimeFaces;

import java.io.IOException;
import java.util.List;

public class LinkCountApp {
    PrimeFaces pfInstance = PrimeFaces.current();
    public static void main(String[] args) throws IOException {
        Link link = new Link("https://github.com/");
//        List<Link> links = LinkController.findLinks(link.getUrl());
//        System.out.println(links);
    }
}
