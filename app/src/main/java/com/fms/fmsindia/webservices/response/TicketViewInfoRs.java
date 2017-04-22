package com.fms.fmsindia.webservices.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by androiduser2 on 27/8/16.
 */
public class TicketViewInfoRs extends BaseRS {
        @SerializedName("ticketno")
        public List<TicketViewRs> ticketView;
}
