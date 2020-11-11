package com.xyz.servicehandlercommunication;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ResponseModel implements Serializable {

    @SerializedName("userId")
    private int userId;

    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String title;

    @SerializedName("body")
    private String body;

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    @Override
    public String toString() {
        return
                "ResponseModel{" +
                        "userId = '" + userId + '\'' +
                        ",id = '" + id + '\'' +
                        ",title = '" + title + '\'' +
                        ",body = '" + body + '\'' +
                        "}";
    }
}