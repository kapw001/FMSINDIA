package com.fms.fmsindia.webservices.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by senthil on 9/20/16.
 */
public class VendorUserInfoRs extends BaseRS {

    @SerializedName("assigned")
    public List<VendorTicketAssignedRs> assignedRs;
}
