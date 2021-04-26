package model.jobs;

import model.AbstractJson;

public class JsonEnvironment extends AbstractJson {

    private boolean buildatshipyard;
    private Boolean gate;

    public boolean isBuildatshipyard() {
        return buildatshipyard;
    }

    public Boolean getGate() {
        return gate;
    }

    public void setBuildatshipyard(boolean buildatshipyard) {
        this.buildatshipyard = buildatshipyard;
    }

    public void setGate(Boolean gate) {
        this.gate = gate;
    }
}
