package com.devx.software.ferias.DTOs.Agenda;

import java.util.Date;

public class AgendaListRequest {
    private Date startDate;
    private Date endDate;
    private Boolean owner;
    private Long ownerId;

    public AgendaListRequest() {
    }

    public AgendaListRequest(Date startDate, Date endDate, Boolean owner, Long ownerId) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.owner = owner;
        this.ownerId = ownerId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Boolean getOwner() {
        return owner;
    }

    public void setOwner(Boolean owner) {
        this.owner = owner;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }
}
