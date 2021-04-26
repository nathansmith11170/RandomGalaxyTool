package model.jobs;

import model.AbstractJson;

public class JsonPage extends AbstractJson {

    private String comment;
    private Integer exact;

    public String getComment() {
        return comment;
    }

    public Integer getExact() {
        return exact;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setExact(Integer exact) {
        this.exact = exact;
    }
}
