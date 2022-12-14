package com.example.myretrofitdemo.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ImageModel {
    private String created_at;
    private String alt_description;
    private UrlModel urls;

    public ImageModel(String created_at, String alt_description, UrlModel urls) {
        this.created_at = created_at;
        this.alt_description = alt_description;
        this.urls = urls;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {

        this.created_at = created_at;
    }

    public String getAlt_description() {
        return alt_description;
    }

    public void setAlt_description(String alt_description) {
        this.alt_description = alt_description;
    }

    public UrlModel getUrls() {

        return urls;
    }

    public void setUrls(UrlModel urls) {

        this.urls = urls;
    }
}




