package com.example.hatem.tick_toc_app.ORM;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by hatem on 12/5/16.
 */
public class DetailedEvent {
    @SerializedName("attendees")
    @Expose
    private List<Attendee> attendees = null;
    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("end")
    @Expose
    private Date end;
    @SerializedName("etag")
    @Expose
    private String etag;
    @SerializedName("htmlLink")
    @Expose
    private String htmlLink;
    @SerializedName("iCalUID")
    @Expose
    private String iCalUID;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("kind")
    @Expose
    private String kind;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("organizer")
    @Expose
    private Organizer organizer;
    @SerializedName("start")
    @Expose
    private Date start;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("summary")
    @Expose
    private String summary;
    @SerializedName("updated")
    @Expose
    private String updated;

    /**
     *
     * @return
     *     The attendees
     */
    public List<Attendee> getAttendees() {
        return attendees;
    }

    /**
     *
     * @param attendees
     *     The attendees
     */
    public void setAttendees(List<Attendee> attendees) {
        this.attendees = attendees;
    }

    /**
     *
     * @return
     *     The created
     */
    public String getCreated() {
        return created;
    }

    /**
     *
     * @param created
     *     The created
     */
    public void setCreated(String created) {
        this.created = created;
    }



    /**
     *
     * @return
     *     The description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     *     The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     *     The end
     */
    public Date getEnd() {
        return end;
    }

    /**
     *
     * @param end
     *     The end
     */
    public void setEnd(Date end) {
        this.end = end;
    }

    /**
     *
     * @return
     *     The etag
     */
    public String getEtag() {
        return etag;
    }

    /**
     *
     * @param etag
     *     The etag
     */
    public void setEtag(String etag) {
        this.etag = etag;
    }

    /**
     *
     * @return
     *     The htmlLink
     */
    public String getHtmlLink() {
        return htmlLink;
    }

    /**
     *
     * @param htmlLink
     *     The htmlLink
     */
    public void setHtmlLink(String htmlLink) {
        this.htmlLink = htmlLink;
    }

    /**
     *
     * @return
     *     The iCalUID
     */
    public String getICalUID() {
        return iCalUID;
    }

    /**
     *
     * @param iCalUID
     *     The iCalUID
     */
    public void setICalUID(String iCalUID) {
        this.iCalUID = iCalUID;
    }

    /**
     *
     * @return
     *     The id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     *     The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     *     The kind
     */
    public String getKind() {
        return kind;
    }

    /**
     *
     * @param kind
     *     The kind
     */
    public void setKind(String kind) {
        this.kind = kind;
    }

    /**
     *
     * @return
     *     The location
     */
    public String getLocation() {
        return location;
    }

    /**
     *
     * @param location
     *     The location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     *
     * @return
     *     The organizer
     */
    public Organizer getOrganizer() {
        return organizer;
    }

    /**
     *
     * @param organizer
     *     The organizer
     */
    public void setOrganizer(Organizer organizer) {
        this.organizer = organizer;
    }


    /**
     *
     * @return
     *     The start
     */
    public Date getStart() {
        return start;
    }

    /**
     *
     * @param start
     *     The start
     */
    public void setStart(Date start) {
        this.start = start;
    }

    /**
     *
     * @return
     *     The status
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status
     *     The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     *
     * @return
     *     The summary
     */
    public String getSummary() {
        return summary;
    }

    /**
     *
     * @param summary
     *     The summary
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     *
     * @return
     *     The updated
     */
    public String getUpdated() {
        return updated;
    }

    /**
     *
     * @param updated
     *     The updated
     */
    public void setUpdated(String updated) {
        this.updated = updated;
    }
}
