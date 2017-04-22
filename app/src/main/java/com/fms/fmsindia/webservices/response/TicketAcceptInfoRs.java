package com.fms.fmsindia.webservices.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by androiduser2 on 13/9/16.
 */
public class TicketAcceptInfoRs extends BaseRS {


    @SerializedName("ticketno")
    public String ticketNo;
    @SerializedName("ticketstatus")
    public String assigned;
}
