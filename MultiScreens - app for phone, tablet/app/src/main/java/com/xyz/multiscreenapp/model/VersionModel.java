package com.xyz.multiscreenapp.model;

public class VersionModel {

    private String versionName;
    private String versionDescription;

    public VersionModel(String versionName, String versionDescription) {
        this.versionName = versionName;
        this.versionDescription = versionDescription;
    }

    public String getVersionName() {
        return versionName;
    }

    public String getVersionDescription() {
        return versionDescription;
    }
}
