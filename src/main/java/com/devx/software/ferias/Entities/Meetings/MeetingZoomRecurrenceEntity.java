package com.devx.software.ferias.Entities.Meetings;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "a_meeting_zoom_recurrence")
public class MeetingZoomRecurrenceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "meeting_zoom_id", referencedColumnName = "id", nullable = false)
    @JsonBackReference
    private MeetingZoomEntity meetingZoomId;
    @Column(name = "end_date_time", nullable = true)
    private Date end_date_time;
    @Column(name = "end_times", nullable = true)
    private Integer endTimes;
    @Column(name = "monthly_day", nullable = true)
    private Integer monthly_day;
    @Column(name = "monthly_week", nullable = true)
    private Integer monthly_week;
    @Column(name = "monthly_week_day", nullable = true)
    private Integer monthly_week_day;
    @Column(name = "repeat_interval", nullable = true)
    private Integer repeat_iterval;
    @Column(name = "type", nullable = true)
    private Integer type; //1: Diario | 2: Semanal | 3: Mensual
    @Column(name = "weekly_days", nullable = true)
    private String weekly_days; // Días de la semana separados por coma (1: Domingo, 2: Lunes, ...7: Sábado)

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MeetingZoomEntity getMeetingZoomId() {
        return meetingZoomId;
    }

    public void setMeetingZoomId(MeetingZoomEntity meetingZoomId) {
        this.meetingZoomId = meetingZoomId;
    }

    public Date getEnd_date_time() {
        return end_date_time;
    }

    public void setEnd_date_time(Date end_date_time) {
        this.end_date_time = end_date_time;
    }

    public Integer getEndTimes() {
        return endTimes;
    }

    public void setEndTimes(Integer endTimes) {
        this.endTimes = endTimes;
    }

    public Integer getMonthly_day() {
        return monthly_day;
    }

    public void setMonthly_day(Integer monthly_day) {
        this.monthly_day = monthly_day;
    }

    public Integer getMonthly_week() {
        return monthly_week;
    }

    public void setMonthly_week(Integer monthly_week) {
        this.monthly_week = monthly_week;
    }

    public Integer getMonthly_week_day() {
        return monthly_week_day;
    }

    public void setMonthly_week_day(Integer monthly_week_day) {
        this.monthly_week_day = monthly_week_day;
    }

    public Integer getRepeatI_iterval() {
        return repeat_iterval;
    }

    public void setRepeat_iterval(Integer repeat_iterval) {
        this.repeat_iterval = repeat_iterval;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getWeekly_days() {
        return weekly_days;
    }

    public void setWeekly_days(String weekly_days) {
        this.weekly_days = weekly_days;
    }
}
