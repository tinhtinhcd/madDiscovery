package com.anhlt.maddiscover.Entities;

/**
 * Created by anhlt on 2/19/16.
 */
public class EventImage {

    private long id;
    private long eventId;
    private String url;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
