package com.devx.software.ferias.Entities.Meetings;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "a_meeting_zoom")
public class MeetingZoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "meeting_id", referencedColumnName = "id", nullable = false)
    @JsonBackReference
    private MeetingEntity meetingId;
    @Column(name = "agenda", nullable = false)
    private String agenda;
    @Column(name = "type", nullable = false)
    private Integer type;
    @Column(name = "default_password", nullable = true)
    private Boolean default_password;
    @Column(name = "duration", nullable = true)
    private Integer duration = 60;
    @Column(name = "password", nullable = true)
    private String password;
    @Column(name = "pre_schedule", nullable = true)
    private Boolean pre_schedule;
    @OneToOne(cascade = CascadeType.PERSIST, mappedBy = "meetingZoomId", fetch = FetchType.LAZY)
    @JsonManagedReference
    private MeetingZoomRecurrenceEntity recurrence;
    @Column(name = "schedule_for", nullable = true)
    private String schedule_for;
    @OneToOne(cascade = CascadeType.PERSIST, mappedBy = "meetingZoomId", fetch = FetchType.LAZY)
    @JsonManagedReference
    private MeetingZoomSettingsEntity settings;
    @Column(name = "start_time", nullable = false)
    private String start_time;
    @Column(name = "timezone", nullable = false)
    private String timezone = "America/Mexico_City";
    @Column(name = "topic", nullable = false)
    private String topic;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MeetingEntity getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(MeetingEntity meetingId) {
        this.meetingId = meetingId;
    }

    public String getAgenda() {
        return agenda;
    }

    public void setAgenda(String agenda) {
        this.agenda = agenda;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Boolean getDefault_password() {
        return default_password;
    }

    public void setDefault_password(Boolean default_password) {
        this.default_password = default_password;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getPre_schedule() {
        return pre_schedule;
    }

    public void setPre_schedule(Boolean pre_schedule) {
        this.pre_schedule = pre_schedule;
    }

    public MeetingZoomRecurrenceEntity getRecurrence() {
        return recurrence;
    }

    public void setRecurrence(MeetingZoomRecurrenceEntity recurrence) {
        this.recurrence = recurrence;
    }

    public String getSchedule_for() {
        return schedule_for;
    }

    public void setSchedule_for(String schedule_for) {
        this.schedule_for = schedule_for;
    }

    public MeetingZoomSettingsEntity getSettings() {
        return settings;
    }

    public void setSettings(MeetingZoomSettingsEntity settings) {
        this.settings = settings;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
