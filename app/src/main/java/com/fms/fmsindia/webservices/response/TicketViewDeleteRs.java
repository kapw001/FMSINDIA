package com.fms.fmsindia.webservices.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by androiduser2 on 13/9/16.
 */
public class TicketViewDeleteRs {
    @SerializedName("ticketno")
    public String ticketNo;
    @SerializedName("issue")
    public String issue;
    @SerializedName("cd")
    public String dateTime;
    @SerializedName("priority")
    public String priority;
    @SerializedName("status")
    public String status;
    @SerializedName("action")
    public String action;
    @SerializedName("paymentstatus")
    public String paymentStatus;
    @SerializedName("ticketstatus")
    public String ticketstatus;
    @SerializedName("amount")
    public String amount;

    @SerializedName("category")
    public String category;
    @SerializedName("pct")
    public String pct;
    @SerializedName("pat")
    public String pat;
    @SerializedName("userdescription")
    public String userDescription;
    @SerializedName("targetdate")
    public String targetDate;

    @SerializedName("firstname")
    public String firstname;
    @SerializedName("lastname")
    public String lastname;
    @SerializedName("username")
    public String username;
    @SerializedName("resolvedby")
    public String resolvedby;
    @SerializedName("paymentmode")
    public String paymentmode;
    @SerializedName("assignedby")
    public String assignedby;
    @SerializedName("mobile")
    public String mobile;
    @SerializedName("appartmentid")
    public String appartmentid;
    @SerializedName("blocknumber")
    public String blocknumber;
    @SerializedName("flatnumber")
    public String flatnumber;
    @SerializedName("atn")
    public String attachmentname;
    @SerializedName("attachmentpath")
    public String attachmentpath;
}
