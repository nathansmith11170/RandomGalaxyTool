package model;

public enum BeltType {
    // GAS
    DAGO_LIQUID_BLUE("dago_liquid_blue", 5),
    // GAS
    DAGO_LIQUID_WHITE("dago_liquid_white", 5),
    // GAS
    DAGO_LIQUID_GREEN("dago_liquid_green", 5),
    // GAS
    DAGO_LIQUID_RED("dago_liquid_red", 5),
    // ORE + ICE
    DAGO_MIXED_ONE("dago_mixed_one", 10),
    // SILICON + HYDROGEN
    DAGO_MIXED_TWO("dago_mixed_two", 10),
    // ICE + METHANE
    DAGO_MIXED_THREE("dago_mixed_three", 10),
    // ORE + SILICON + NVIDIUM + ICE
    DAGO_MIXED_FOUR("dago_mixed_four", 1),
    // EVERYTHING
    DAGO1_OUTER_RESOURCES("dago1_outer_resources", 1),
    // EVERYTHING
    DAGO1_OUTER_RESOURCESBLUE("dago1_outer_resourcesblue", 1),
    // ORE + SILICON + NVIDIUM
    DAGO1_OUTER_RESOURCES2("dago1_outer_resources2", 1),
    // EVERYTHING
    DAGO2_OUTER_RESOURCES("dago2_outer_resources", 1),
    // EVERYTHING
    DAGO2_OUTER_RESOURCESRED("dago2_outer_resourcesred", 1),
    // EVERYTHING
    DAGO3_OUTER_RESOURCES("dago3_outer_resources", 1),
    // EVERYTHING
    DAGO3_OUTER_RESOURCESGREEN("dago3_outer_resourcesgreen", 1),
    // ORE! + SILICON + NVIDIUM + ICE
    DAGO_SOLID_HIGH_ORE("dago_solid_high_ore", 1),
    // ORE + SILICON! + NVIDIUM + ICE
    DAGO_SOLID_HIGH_SILICON("dago_solid_high_silicon", 1),
    // ORE + SILICON + NVIDIUM + ICE!
    DAGO_SOLID_HIGH_ICE("dago_solid_high_ice", 1),
    // ORE! + SILICON! + NVIDIUM! + ICE!
    DAGO_SOLID_HIGH_ALL("dago_solid_high_all", 1);

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
