
package com.metrologygate.models.InstrumentDashboard;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InstrumentDashboard {

    @SerializedName("Data")
    @Expose
    private Data data;
    @SerializedName("Error")
    @Expose
    private Integer error;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
        this.error = error;
    }

}
