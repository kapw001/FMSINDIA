package com.fms.fmsindia.webservices.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by androiduser2 on 12/8/16.
 */
public class LoginRs extends BaseRS {

    @SerializedName("message")
    public String message;
    @SerializedName("userid")
    public String userId;
    @SerializedName("mobile")
    public String logInPhone;
    @SerializedName("firstname")
    public String firstname;
    @SerializedName("lastname")
    public String lastname;
    @SerializedName("username")
    public String email;
    @SerializedName("houseid")
    public String houseid;
    @SerializedName("role")
    public String role;
    @SerializedName("attachmentname")
    public String attachmentname;
    @SerializedName("userimage")
    public String userimage;
    @SerializedName("currentaddress")
    public String currentaddress;
    @SerializedName("landmark")
    public String landmark;
}
