package com.fms.fmsindia.webservices.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by androiduser2 on 27/8/15.
 */
public class PrescriptionsInfoRS extends BaseRS{
    @SerializedName("HISTORY")
    public List<PrescriptionsHistoryRs> prescriptionsHistory;
}
