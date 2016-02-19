package com.anhlt.maddiscover.entities;

/**
 * Created by anhlt on 2/19/16.
 */
public class EventImage {

    private long id;
    private String url;
    private String fileName;

    public long getId() {return id;}

    public void setId(long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFileName() {return fileName;}

    public void setFileName(String fileName) {this.fileName = fileName;}
}
