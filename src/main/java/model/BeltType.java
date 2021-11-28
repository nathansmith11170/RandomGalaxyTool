package model;

public enum BeltType {
    ORE("region_highyield_ore",1.0), ICE("p1_40km_ice_field",0.9), SILICON("region_highyield_silicon", .7), HYDROGEN("p1_80km_hydrogen_field",0.6),
    HELIUM("p1_40km_helium_highyield_field",0.6), METHANE("p1_40km_methane_highyield_field",0.6),  NVIDIUM("region_highyield_nvidium",0.1);

    private final String name;
    private final Double weight;

    public String getName() {
        return name;
    }

    public double getWeight() { return weight; }

    BeltType(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }
}
