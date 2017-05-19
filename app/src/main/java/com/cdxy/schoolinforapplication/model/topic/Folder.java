package com.cdxy.schoolinforapplication.model.topic;

import java.util.List;

/**
 * Created by PC on 2017/2/17.
 */

public class Folder {
    private String name;
    private String path;
    private List<SelectPhoto> selectPhotos;

    public Folder(String name, String path, List<SelectPhoto> selectPhotos) {
        this.name = name;
        this.path = path;
        this.selectPhotos = selectPhotos;
    }

    public Folder() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<SelectPhoto> getSelectPhotos() {
        return selectPhotos;
    }

    public void setSelectPhotos(List<SelectPhoto> selectPhotos) {
        this.selectPhotos = selectPhotos;
    }
}
