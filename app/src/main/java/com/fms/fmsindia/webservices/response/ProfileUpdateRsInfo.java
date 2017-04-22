package com.fms.fmsindia.webservices.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by androiduser2 on 28/9/16.
 */

public class ProfileUpdateRsInfo extends BaseRS {

    @SerializedName("userid")
    public String userId;
    @SerializedName("mobile")
    public String phone;
    @SerializedName("firstname")
    public String firstName;
    @SerializedName("lastname")
    public String lastName;
    @SerializedName("email")
    public String eMail;
    @SerializedName("currentaddress")
    public String currentAddress;
    @SerializedName("landmark")
    public String landmark;
    @SerializedName("role")
    public String role;
    @SerializedName("houseid")
    public String houseId;
}
