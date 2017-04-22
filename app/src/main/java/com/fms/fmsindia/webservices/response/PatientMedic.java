package com.fms.fmsindia.webservices.response;



import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by krishna on 22/8/15.
 */
public class PatientMedic implements Serializable{

    @SerializedName("RESPONSECODE")
    public String responseCode;

    @SerializedName("ERRORCODE")
    public String errorCode;

    @SerializedName("RESPONSE")
    public Response response;

}
