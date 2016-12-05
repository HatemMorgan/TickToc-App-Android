package com.example.hatem.tick_toc_app.ORM;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by hatem on 12/5/16.
 */
public class DetailedTaskResponseObject {
    @SerializedName("Status")
    @Expose
    private int status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("results")
    @Expose
    private DetailedTaskObj results;

    /**
     *
     * @return
     * The status
     */
    public int getStatus() {
        return status;
    }

    /**
     *
     * @param status
     * The Status
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     *
     * @return
     * The message
     */
    public String getMessage() {
        return message;
    }

    /**
     *
     * @param message
     * The message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     *
     * @return
     * The results
     */
    public DetailedTaskObj getResults() {
        return results;
    }

    /**
     *
     * @param results
     * The results
     */
    public void setResults(DetailedTaskObj results) {
        this.results = results;
    }
}
