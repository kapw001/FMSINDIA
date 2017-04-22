package com.fms.fmsindia.webservices.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by androiduser2 on 13/9/16.
 */
public class TicketViewDeleteInfoRs extends BaseRS {
    @SerializedName("ticketno")
    public List<TicketViewDeleteRs> ticketView;

}
