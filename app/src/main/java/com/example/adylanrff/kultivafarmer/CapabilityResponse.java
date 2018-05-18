package com.example.adylanrff.kultivafarmer;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class CapabilityResponse {
    @SerializedName("id")
    private int id;
    @SerializedName("volume")
    private float volume;
    @SerializedName("start_date")
    private Date startDate;
    @SerializedName("end_date")
    private Date endDate;
    @SerializedName("product_id")
    private int productId;

}
