package com.example.nihal.retrofit.Pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyResponse {

    @SerializedName("result")
    @Expose
    private Boolean result;
    @SerializedName("is_registered")
    @Expose
    private Boolean isRegistered;
    @SerializedName("data")
    @Expose
    private Detail data;

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public Boolean getRegistered() {
        return isRegistered;
    }

    public void setRegistered(Boolean registered) {
        isRegistered = registered;
    }

    public Detail getData() {
        return data;
    }

    public void setData(Detail data) {
        this.data = data;
    }
}
