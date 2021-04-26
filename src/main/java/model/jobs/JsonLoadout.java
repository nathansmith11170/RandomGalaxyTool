package model.jobs;

import model.AbstractJson;

public class JsonLoadout extends AbstractJson {

    private JsonLevel level;
    private Integer exact;

    public JsonLevel getLevel() {
        return level;
    }

    public Integer getExact() {
        return exact;
    }

    public void setLevel(JsonLevel level) {
        this.level = level;
    }

    public void setExact(Integer exact) {
        this.exact = exact;
    }
}
