package com.example.hatem.tick_toc_app.ORM;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by hatem on 12/5/16.
 */
public class Attendee {
    @SerializedName("displayName")
    @Expose
    private String displayName;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("responseStatus")
    @Expose
    private String responseStatus;

    /**
     *
     * @return
     *     The displayName
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     *
     * @param displayName
     *     The displayName
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     *
     * @return
     *     The email
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     *     The email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return
     *     The responseStatus
     */
    public String getResponseStatus() {
        return responseStatus;
    }

    /**
     *
     * @param responseStatus
     *     The responseStatus
     */
    public void setResponseStatus(String responseStatus) {
        this.responseStatus = responseStatus;
    }
}
