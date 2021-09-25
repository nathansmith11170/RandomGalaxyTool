package configurationmodel;

import java.math.BigDecimal;

public class GeneratorConfig {

    private int clusters = 0;

    private int passes = 0;

    private BigDecimal deadPercent = new BigDecimal(0);

    public int getClusters() {
        return clusters;
    }

    public int getPasses() {
        return passes;
    }

    public BigDecimal getDeadPercent() {
        return deadPercent;
    }

    public void setClusters(int val) {
        this.clusters = val;
    }

    public void setPasses(int val) {
        this.passes = val;
    }

    public void setDeadPercent(BigDecimal val) {
        this.deadPercent = val;
    }
}