package by.paramonov.linkcount.bean;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

public class EntryBean {
    private static final String PARAM_ID = "id";
    private Integer id = null;

    public EntryBean() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();
        String id = request.getParameter(PARAM_ID);
        if (id != null) {
            setId(Integer.parseInt(id));
        }
    }

    public String getEntryLocation(){
        String result = "/linkList.jsf";
        if (getId() != null) {
            result = "/linkDetail.jsf";
        }
        return result;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
