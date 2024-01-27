package com.devx.software.ferias.DTOs.Projects;

public class ProjectRequest {
    private ProjectBasicFormRequestDTO project;
    private ProjectColaboratorDTO institutionResponsible;
    private ProjectColaboratorDTO enterpriseResponsible;

    public ProjectBasicFormRequestDTO getProject() {
        return project;
    }

    public void setProject(ProjectBasicFormRequestDTO project) {
        this.project = project;
    }

    public ProjectColaboratorDTO getInstitutionResponsible() {
        return institutionResponsible;
    }

    public void setInstitutionResponsible(ProjectColaboratorDTO institutionResponsible) {
        this.institutionResponsible = institutionResponsible;
    }

    public ProjectColaboratorDTO getEnterpriseResponsible() {
        return enterpriseResponsible;
    }

    public void setEnterpriseResponsible(ProjectColaboratorDTO enterpriseResponsible) {
        this.enterpriseResponsible = enterpriseResponsible;
    }
}
