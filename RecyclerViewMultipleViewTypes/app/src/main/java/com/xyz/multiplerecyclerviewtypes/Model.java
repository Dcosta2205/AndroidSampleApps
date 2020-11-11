package com.xyz.multiplerecyclerviewtypes;

public class Model {
    public static final int TEXT_TYPE = 0;
    public static final int IMAGE_TYPE = 1;
    public static final int AUDIO_TYPE = 2;

    private int itemType;
    private int resId;
    private String content;

    public Model(int itemType, int resId, String content) {
        this.itemType = itemType;
        this.resId = resId;
        this.content = content;
    }

    public int getItemType() {
        return itemType;
    }

    public int getResId() {
        return resId;
    }

    public String getContent() {
        return content;
    }
}
