package com.devx.software.ferias.DTOs.Profiles;

import java.util.ArrayList;
import java.util.List;

public class OrganizationChartDTO {

    private String title;

    private String cssClass = "ngx-org-ceo";

    private List<OrganizationChartDTO> childs = new ArrayList<>();

    public OrganizationChartDTO(String name, List<OrganizationChartDTO> childs) {
        this.title = name;
        this.childs = childs;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCssClass() {
        return cssClass;
    }

    public void setCssClass(String cssClass) {
        this.cssClass = cssClass;
    }

    public List<OrganizationChartDTO> getChilds() {
        return childs;
    }

    public void setChilds(List<OrganizationChartDTO> childs) {
        this.childs = childs;
    }
}
