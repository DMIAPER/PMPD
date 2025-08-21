package com.example.marsroverphotos;

import com.google.gson.annotations.SerializedName;

public class Photo {
    @SerializedName("img_src")
    private String imgSrc;

    public String getImgSrc() {
        return imgSrc.replaceFirst("http", "https");
    }

}
