package model.jobs;

import model.AbstractJson;

public class JsonCategory extends AbstractJson {

    private String faction;
    private String tags;
    private String size;

    public String getFaction() {
        return faction;
    }

    public String getTags() {
        return tags;
    }

    public String getSize() {
        return size;
    }

    public void setFaction(String faction) {
        this.faction = faction;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
