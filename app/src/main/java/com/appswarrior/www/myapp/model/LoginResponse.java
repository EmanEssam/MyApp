package com.appswarrior.www.myapp.model;

/**
 * Created by Eman Essam on 27/09/2017.
 */


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("UserName")
    @Expose
    private String userName;
    @SerializedName("Image")
    @Expose
    private String image;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
    }
}