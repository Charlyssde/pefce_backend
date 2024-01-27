package com.devx.software.ferias.Entities.Meetings;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "a_meeting_zoom_settings")
public class MeetingZoomSettingsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "meeting_zoom_id", referencedColumnName = "id", nullable = false)
    @JsonBackReference
    private MeetingZoomEntity meetingZoomId;
    @Column(name = "allow_multiple_devices", nullable = false)
    private boolean allow_multiple_devices = true;
    @Column(name = "approval_type", nullable = false)
    private Integer approval_type = 2;
    @Column(name = "auto_recording", nullable = false)
    private String auto_recording; // local, cloud, none 'enum'
    @Column(name = "jbh_time", nullable = false)
    private Integer jbh_time = 10;
    @Column(name = "join_before_host", nullable = false)
    private Boolean join_before_host = true;
    @Column(name = "mute_upon_entry", nullable = false)
    private Boolean mute_upon_entry = true;
    @Column(name = "participant_video", nullable = false)
    private Boolean participant_video = false;
    @Column(name = "registration_type", nullable = false)
    private Integer registration_type; // If meeting type = 8 can : 1 - register once; 2: register each, 3: register optional
    @Column(name = "waiting_room", nullable = false)
    private Boolean waiting_room;

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

    public boolean isAllow_multiple_devices() {
        return allow_multiple_devices;
    }

    public void setAllow_multiple_devices(boolean allow_multiple_devices) {
        this.allow_multiple_devices = allow_multiple_devices;
    }

    public Integer getApproval_type() {
        return approval_type;
    }

    public void setApproval_type(Integer approval_type) {
        this.approval_type = approval_type;
    }

    public String getAuto_recording() {
        return auto_recording;
    }

    public void setAuto_recording(String auto_recording) {
        this.auto_recording = auto_recording;
    }

    public Integer getJbh_time() {
        return jbh_time;
    }

    public void setJbh_time(Integer jbh_time) {
        this.jbh_time = jbh_time;
    }

    public Boolean getJoin_before_host() {
        return join_before_host;
    }

    public void setJoin_before_host(Boolean join_before_host) {
        this.join_before_host = join_before_host;
    }

    public Boolean getMute_upon_entry() {
        return mute_upon_entry;
    }

    public void setMute_upon_entry(Boolean mute_upon_entry) {
        this.mute_upon_entry = mute_upon_entry;
    }

    public Boolean getParticipant_video() {
        return participant_video;
    }

    public void setParticipant_video(Boolean participant_video) {
        this.participant_video = participant_video;
    }

    public Integer getRegistration_type() {
        return registration_type;
    }

    public void setRegistration_type(Integer registration_type) {
        this.registration_type = registration_type;
    }

    public Boolean getWaiting_room() {
        return waiting_room;
    }

    public void setWaiting_room(Boolean waiting_room) {
        this.waiting_room = waiting_room;
    }
}
