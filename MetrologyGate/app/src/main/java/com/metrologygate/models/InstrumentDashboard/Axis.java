
package com.metrologygate.models.InstrumentDashboard;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Axis {

    @SerializedName("Distance")
    @Expose
    private Double distance;
    @SerializedName("Time")
    @Expose
    private String time;
    @SerializedName("Type")
    @Expose
    private String type;

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
