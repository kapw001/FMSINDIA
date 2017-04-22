package com.fms.fmsindia.webservices.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by androiduser2 on 27/8/15.
 */
public class PrescriptionsHistoryRs  {
   @SerializedName("prescription_id")
    public String Prescription_id;
    @SerializedName("date")
    public String Prescription_Date;
    @SerializedName("next_appointment")
    public String Prescription_next_appointment;
    @SerializedName("doctorname")
    public String Prescription_doctorname;
    @SerializedName("hospitalname")
    public String Prescription_hospitalname;
    @SerializedName("expiry_status")
    public String Prescription_expiry_status;
    @SerializedName("expiry_date")
    public String Prescription_expiry_Date;

}
