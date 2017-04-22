package com.fms.fmsindia.webservices.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by androiduser2 on 25/8/16.
 */
public class GetPriorityInfoRs extends BaseRS {

    @SerializedName("id")
    public List<GetPriorityRs> priorityDetails;
}
