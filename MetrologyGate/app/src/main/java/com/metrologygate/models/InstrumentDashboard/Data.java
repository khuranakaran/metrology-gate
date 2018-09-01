
package com.metrologygate.models.InstrumentDashboard;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {

    @SerializedName("Axes")
    @Expose
    private List<Axis> axes = null;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("Errors")
    @Expose
    private List<com.metrologygate.models.InstrumentDashboard.Error> errors = null;
    @SerializedName("HardwareStatus")
    @Expose
    private Integer hardwareStatus;
    @SerializedName("ImageUrl")
    @Expose
    private String imageUrl;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("OpenProject")
    @Expose
    private String openProject;
    @SerializedName("ProbeTriggers")
    @Expose
    private Integer probeTriggers;
    @SerializedName("ProgramStatus")
    @Expose
    private Integer programStatus;

    public List<Axis> getAxes() {
        return axes;
    }

    public void setAxes(List<Axis> axes) {
        this.axes = axes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<com.metrologygate.models.InstrumentDashboard.Error> getErrors() {
        return errors;
    }

    public void setErrors(List<com.metrologygate.models.InstrumentDashboard.Error> errors) {
        this.errors = errors;
    }

    public Integer getHardwareStatus() {
        return hardwareStatus;
    }

    public void setHardwareStatus(Integer hardwareStatus) {
        this.hardwareStatus = hardwareStatus;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOpenProject() {
        return openProject;
    }

    public void setOpenProject(String openProject) {
        this.openProject = openProject;
    }

    public Integer getProbeTriggers() {
        return probeTriggers;
    }

    public void setProbeTriggers(Integer probeTriggers) {
        this.probeTriggers = probeTriggers;
    }

    public Integer getProgramStatus() {
        return programStatus;
    }

    public void setProgramStatus(Integer programStatus) {
        this.programStatus = programStatus;
    }

}
