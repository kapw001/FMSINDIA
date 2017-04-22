package com.fms.fmsindia.webservices.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by androiduser2 on 26/8/16.
 */
public class OtpInfoRs extends BaseRS {
    @SerializedName("phoneno")
    public String phone;
    @SerializedName("userId")
    public String userId;
    @SerializedName("username")
    public String emailId;
    @SerializedName("firstname")
    public String firstName;
    @SerializedName("lastname")
    public String lastName;
    @SerializedName("currentaddress")
    public String currentAddress;
    @SerializedName("landmark")
    public String landmark;
    @SerializedName("houseid")
    public String houseid;
    @SerializedName("attachmentname")
    public String attachmentname;
    public String userimage;
    @SerializedName("role")
    public String role;
}

