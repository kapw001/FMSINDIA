package com.fms.fmsindia.webservices.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by androiduser2 on 25/8/16.
 */
public class GetIssueRs implements Serializable {
    @SerializedName("issueid")
    public String issueId;
    @SerializedName("issue")
    public String issueName;
}
