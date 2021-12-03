package model;

public class Relation extends AbstractJson {
    private String faction;
    private String otherfaction;
    private String value;

    public String getFaction() {
        return this.faction;
    }

    public String getOtherFaction() {
        return this.otherfaction;
    }

    public String getValue() {
        return this.value;
    }

    public void setFaction( String faction ) {
        this.faction = faction;
    }

    public void setOtherFaction( String otherFaction ) {
        this.otherfaction = otherFaction;
    }

    public void setValue( String val ) {
        this.value = val;
    } 
}
