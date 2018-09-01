package com.metrologygate.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Login {
    @SerializedName("LoginResult")
    @Expose
    private Integer loginResult;

    public Integer getLoginResult() {
        return loginResult;
    }

    public void setLoginResult(Integer loginResult) {
        this.loginResult = loginResult;
    }
}
