package com.example.hatem.tick_toc_app.ORM;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by hatem on 12/4/16.
 */
public class ResponseObject {


        @SerializedName("Status")
        @Expose
        private Integer status;
        @SerializedName("message")
        @Expose
        private String message;
        @SerializedName("page")
        @Expose
        private Integer page;
        @SerializedName("calendar")
        @Expose
        private String calendar;
        @SerializedName("results")
        @Expose
        private List<EventListItem> results = null;

        /**
         *
         * @return
         * The status
         */
        public Integer getStatus() {
                return status;
        }

        /**
         *
         * @param status
         * The Status
         */
        public void setStatus(Integer status) {
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
         * The page
         */
        public Integer getPage() {
                return page;
        }

        /**
         *
         * @param page
         * The page
         */
        public void setPage(Integer page) {
                this.page = page;
        }

        /**
         *
         * @return
         * The calendar
         */
        public String getCalendar() {
                return calendar;
        }

        /**
         *
         * @param calendar
         * The calendar
         */
        public void setCalendar(String calendar) {
                this.calendar = calendar;
        }

        /**
         *
         * @return
         * The results
         */
        public List<EventListItem> getResults() {
                return results;
        }

        /**
         *
         * @param results
         * The results
         */
        public void setResults(List<EventListItem> results) {
                this.results = results;
        }

}
