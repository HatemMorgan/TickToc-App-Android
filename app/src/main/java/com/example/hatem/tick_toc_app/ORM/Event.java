package com.example.hatem.tick_toc_app.ORM;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by hatem on 12/4/16.
 */
public class Event {



        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("summary")
        @Expose
        private String summary;

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


}
