package com.fms.fmsindia.webservices.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by androiduser2 on 7/9/16.
 */
public class UserInfoRs extends BaseRS {
    @SerializedName("UserInfo")
    public List<UserRs> userRs;
    @SerializedName("assigned")
    public List<VendorTicketAssignedRs> assignedRs;
}
