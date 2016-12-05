package com.example.hatem.tick_toc_app.ORM;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by hatem on 12/4/16.
 */
public class EventListItem {


        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("start")
        @Expose
        private Date start;
        @SerializedName("summary")
        @Expose
        private String summary;

        /**
         * @return The id
         */
        public String getId() {
                return id;
        }

        /**
         * @param id The id
         */
        public void setId(String id) {
                this.id = id;
        }

        /**
         * @return The start
         */
        public Date getStart() {
                return start;
        }

        /**
         * @param start The start
         */
        public void setStart(Date start) {
                this.start = start;
        }

        /**
         * @return The summary
         */
        public String getSummary() {
                return summary;
        }

        /**
         * @param summary The summary
         */
        public void setSummary(String summary) {
                this.summary = summary;
        }
}
