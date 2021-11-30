package model;

public enum BeltType {
    ASTEROIDS("p1_c01s01_asteroid_field",1.0), ICE("p1_40km_ice_field",0.9), HYDROGEN("p1_40km_hydrogen_field",0.6),
    HELIUM("p1_40km_helium_field",0.6), METHANE("p1_40km_methane_field",0.6);



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
