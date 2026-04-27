package com.example.spitifi.data.api;

public class ConvertRequest {
    public String url;
    public String tag;
    public String title;

    public ConvertRequest(String url, String tag, String title) {
        this.url = url;
        this.tag = tag;
        this.title = title;
    }
}
