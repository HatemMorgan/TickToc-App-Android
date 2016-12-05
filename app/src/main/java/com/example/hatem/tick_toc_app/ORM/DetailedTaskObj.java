package com.example.hatem.tick_toc_app.ORM;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by hatem on 12/5/16.
 */
public class DetailedTaskObj {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("startDateTime")
    @Expose
    private String startDateTime;
    @SerializedName("endDateTime")
    @Expose
    private String endDateTime;
    @SerializedName("location")
    @Expose
    private Location location;
    @SerializedName("userID")
    @Expose
    private String userID;

    /**
     *
     * @return
     * The id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     * The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     * The description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     * The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     * The startDateTime
     */
    public String getStartDateTime() {
        return startDateTime;
    }

    /**
     *
     * @param startDateTime
     * The startDateTime
     */
    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }

    /**
     *
     * @return
     * The endDateTime
     */
    public String getEndDateTime() {
        return endDateTime;
    }

    /**
     *
     * @param endDateTime
     * The endDateTime
     */
    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
    }

    /**
     *
     * @return
     * The location
     */
    public Location getLocation() {
        return location;
    }

    /**
     *
     * @param location
     * The location
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     *
     * @return
     * The userID
     */
    public String getUserID() {
        return userID;
    }

    /**
     *
     * @param userID
     * The userID
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }
}
