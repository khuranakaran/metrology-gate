
package com.metrologygate.models.InstrumentDashboard;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Error {

    @SerializedName("Date")
    @Expose
    private String date;
    @SerializedName("FormattedDate")
    @Expose
    private String formattedDate;
    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("Type")
    @Expose
    private Integer type;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFormattedDate() {
        return formattedDate;
    }

    public void setFormattedDate(String formattedDate) {
        this.formattedDate = formattedDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

}
