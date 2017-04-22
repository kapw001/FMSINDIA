package com.fms.fmsindia.webservices.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by androiduser2 on 14/9/16.
 */
public class VendorTicketDetailsInfoRs extends BaseRS{
    @SerializedName("ticketno")
    public List<VendorTicketDetailsRs> ticketView;
    @SerializedName("ticketstatus")
    public List<VendorTicketStatusRs> ticketStatus;
    @SerializedName("assigned")
    public List<VendorTicketAssignedRs> assignedRs;
    @SerializedName("ticketDetails")
    public List<TicketFullDetails> ticketDetails;
}
