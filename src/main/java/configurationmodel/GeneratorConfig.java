package configurationmodel;

import java.math.BigDecimal;

public class GeneratorConfig {

    private int clusters = 0;

    private int passes = 0;

    private BigDecimal deadPercent = new BigDecimal(0);

    private int argonSectors = 0;

    private int antigoneSectors = 0;

    private int holyOrderSectors = 0;

    private int godrealmSectors = 0;
     
    private int teladiSectors = 0;

    private int ministrySectors = 0;

    private int zyarthSectors = 0;

    private int freeFamiliesSectors = 0;

    private int terranSectors = 0;

    private int segarisSectors = 0;

    public int getClusters() {
        return clusters;
    }

    public void setClusters(int val) {
        this.clusters = val;
    }

    public int getPasses() {
        return passes;
    }

    public void setPasses(int val) {
        this.passes = val;
    }

    public BigDecimal getDeadPercent() {
        return deadPercent;
    }

    public void setDeadPercent(BigDecimal val) {
        this.deadPercent = val;
    }

    public int getArgonSectors() {
        return this.argonSectors;
    }

    public void setArgonSectors( int val ) {
        this.argonSectors = val;
    }

    public int getAntigoneSectors() {
        return this.antigoneSectors;
    }

    public void setAntigoneSectors( int val ) {
        this.antigoneSectors = val;
    }

    public int getHolyOrderSectors() {
        return this.holyOrderSectors;
    }

    public void setHolyOrderSectors( int val ) {
        this.holyOrderSectors = val;
    }

    public int getGodrealmSectors() {
        return this.godrealmSectors;
    }

    public void setGodrealmSectors( int val ) {
        this.godrealmSectors = val;
    }

    public int getTeladiSectors() {
        return this.teladiSectors;
    }

    public void setTeladiSectors( int val ) {
        this.teladiSectors = val;
    }

    public int getMinistrySectors() {
        return this.ministrySectors;
    }

    public void setMinistrySectors( int val ) {
        this.ministrySectors = val;
    } 

    public int getZyarthSectors() {
        return this.zyarthSectors;
    }

    public void setZyarthSectors( int val ) {
        this.zyarthSectors = val;
    }

    public int getFreeFamiliesSectors() {
        return this.freeFamiliesSectors;
    }

    public void setFreeFamiliesSectors( int val ) { 
        this.freeFamiliesSectors = val;
    }

    public int getTerranSectors() {
        return this.terranSectors;
    }    

    public void setTerranSectors( int val ) {
        this.terranSectors = val;
    }

    public int getSegarisSectors() {
        return this.segarisSectors;
    }

    public void setSegarisSectors( int val ) {
        this.segarisSectors = val;
    }
}