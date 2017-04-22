package com.fms.fmsindia.webservices.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by senthil on 9/21/16.
 */
public class TicketFullDetails {
    @SerializedName("ticketnum")
    public String ticketnum;
    @SerializedName("logtime")
    public String logtime;
    @SerializedName("ticketstatus")
    public String ticketstatus;
    @SerializedName("assignedby")
    public String assignedby;
    @SerializedName("assignedto")
    public String assignedto;
    @SerializedName("resolvedby")
    public String resolvedby;
    @SerializedName("description")
    public String description;
}
