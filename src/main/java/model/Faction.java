package model;

public enum Faction {
    PLAYER("faction.player", "", "", ""),
    ARGON("argon", "arg", "arg", "Argon"),
    ANTIGONE("antigone", "ant", "arg", "Antigone"),
    HATIKVAH("hatikvah", "hat", "arg", "HatikvahFreeLeague"),
    PARANID("paranid", "par", "par","GodrealmOfTheParanid"),
    HOLYORDER("holyorder", "hol", "par", "HolyOrderFactionLogic"),
    ALLIANCE("alliance", "ali", "par", "AllianceOfTheWord"),
    TELADI("teladi", "tel", "tel", "TeladiCompany"),
    MINISTRY("ministry", "min", "tel", "MinistryOfFinance"),
    SCALEPLATE("scaleplate", "sca", "tel", "ScalePlatePact"),
    XENON("xenon", "xen", "xen", "XenonFaction"),
    BUCCANEERS("buccaneers", "buc", "par", "Buccaneers"),
    TRINITY("trinity", "tri", "par", "Trinity"),
    SPLIT("split", "spl", "spl", "Split"),
    FREESPLIT("freesplit", "frf", "spl", "FreeSplit"),
    COURT("court", "cou", "spl", "Court"),
    FALLENFAMILIES("fallensplit", "faf", "spl", "FallenFamilies"),
    TERRAN("terran", "ter", "ter", "Terran"),
    PIONEER("pioneers", "pio", "ter", "Pioneer"),
    YAKI("yaki", "yak", "ter", "Yaki"),
    PLAYEROWNER("player", "", "", ""),
    OWNERLESS("ownerless", "", "", "");

    private String name;
    private String factionAbbreviation;
    private String raceAbbreviation;
    private String managerName;

    public String getName() {
        return name;
    }

    public String getProperName() {
        return name.substring(0,1).toUpperCase() + name.substring(1);
    }

    public String getRaceAbbreviation(){
        return raceAbbreviation;
    }

    public String getFactionAbbreviation() {
        return factionAbbreviation;
    }

    public Race getRace() {
        switch( getRaceAbbreviation() ) {
            case "arg":
                return Race.ARGON;
            case "par":
                return Race.PARANID;
            case "tel":
                return Race.TELADI;
            case "ter":
                return Race.TERRAN;
            case "xen":
                return Race.XENON;
            case "spl":
                return Race.SPLIT;
            default:
                return Race.ARGON;
        }
    }

    public String getPlayerStartMacro(){
        switch( this.name ) {
            case "argon":
                return "tutorial";
            case "terran":
                return "tutorial";
            case "paranid":
                return "fight";
            case "teladi":
                return "trade";
            case "split":
                return "pirate";
            case "freesplit":
                return "pirate";
            case "pioneers":
                return "tutorial";
            case "ministry":
                return "trade";
            case "antigone":
                return "tutorial";
            case "holyorder":
                return "fight";
            default:
                return null;
        }
    }

    public String getManagerName(){
        return managerName;
    }

    Faction(String name, String factionAbbreviation, String raceAbbreviation, String managerName) {
        this.name = name;
        this.factionAbbreviation = factionAbbreviation;
        this.raceAbbreviation = raceAbbreviation;
        this.managerName = managerName;
    }

    public static Faction getEnum( String n ) {
        switch( n ) {
            case "argon":
                return ARGON;
            case "terran":
                return TERRAN;
            case "paranid":
                return PARANID;
            case "teladi":
                return TELADI;
            case "split":
                return SPLIT;
            case "xenon":
                return XENON;
            case "freesplit":
                return FREESPLIT;
            case "pioneers":
                return PIONEER;
            case "ministry":
                return MINISTRY;
            case "antigone":
                return ANTIGONE;
            case "holyorder":
                return HOLYORDER;
            default:
                return null;
        }
    }
}
