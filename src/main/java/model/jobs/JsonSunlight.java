package model.jobs;

import model.AbstractJson;

public class JsonSunlight extends AbstractJson {

    private Double min;
    private Double max;

    public JsonSunlight() {
    }
    public JsonSunlight(Double min, Double max) {
        this.min = min;
        this.max = max;
    }

    public Double getMin() {
        return min;
    }

    public Double getMax() {
        return max;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public void setMax(Double max) {
        this.max = max;
    }
}
