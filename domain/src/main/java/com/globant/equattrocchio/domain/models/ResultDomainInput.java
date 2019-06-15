package com.globant.equattrocchio.domain.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultDomainInput {
    @SerializedName("images")
    @Expose
    private List<Image> mImages = null;

    public List<Image> getImages() {
        return mImages;
    }

    public void setImages(List<Image> images) {
        this.mImages = images;
    }

}
