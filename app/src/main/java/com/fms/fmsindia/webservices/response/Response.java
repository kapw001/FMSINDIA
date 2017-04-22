package com.fms.fmsindia.webservices.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by krishna on 25/8/15.
 */
public class Response {
    @SerializedName("medicid")
    public String medicid;

    @SerializedName("otp")
    public String otpCode;

}
