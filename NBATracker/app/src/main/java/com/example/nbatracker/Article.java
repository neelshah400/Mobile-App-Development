package com.example.nbatracker;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Article {

    private String sourceID;
    private String sourceName;
    private String author;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private String publishedAt;
    private String content;

    public Article(JSONObject jsonObject) {
        try {
            sourceID = jsonObject.getJSONObject("source").getString("id");
            sourceName = jsonObject.getJSONObject("source").getString("name");

            author = jsonObject.getString("author");
            if (author.contains(","))
                author = author.substring(0, author.indexOf(","));
            if (author.equals("null"))
                author = sourceName;

            title = jsonObject.getString("title");
            description = jsonObject.getString("description");
            url = jsonObject.getString("url");
            urlToImage = jsonObject.getString("urlToImage");

            publishedAt = jsonObject.getString("publishedAt");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date date = dateFormat.parse(publishedAt);
            dateFormat = new SimpleDateFormat("MMM dd, yyyy");
            dateFormat.setTimeZone(TimeZone.getDefault());
            publishedAt = dateFormat.format(date);

            content = jsonObject.getString("content");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getSourceID() {
        return sourceID;
    }

    public void setSourceID(String sourceID) {
        this.sourceID = sourceID;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
