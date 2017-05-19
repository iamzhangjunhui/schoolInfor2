package com.cdxy.schoolinforapplication.model.topic;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by PC on 2017/2/16.
 */
//选择图片界面显示的图片
public class SelectPhoto implements Parcelable {
    private String path;
    private int isSelect;

    public SelectPhoto() {
    }

    public SelectPhoto(String path, int isSelect) {
        this.path = path;
        this.isSelect = isSelect;
    }

    protected SelectPhoto(Parcel in) {
        path = in.readString();
        isSelect = in.readInt();
    }
    public static final Creator<SelectPhoto> CREATOR = new Creator<SelectPhoto>() {
        @Override
        public SelectPhoto createFromParcel(Parcel in) {
            SelectPhoto selectPhoto=new SelectPhoto();
            selectPhoto.path=in.readString();
            selectPhoto.isSelect=in.readInt();
            return selectPhoto;
        }

        @Override
        public SelectPhoto[] newArray(int size) {
            return new SelectPhoto[size];
        }
    };

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getIsSelect() {
        return isSelect;
    }

    public void setIsSelect(int isSelect) {
        this.isSelect = isSelect;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(path);
        dest.writeInt(isSelect);
    }
}

