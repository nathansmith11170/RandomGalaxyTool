package model;

public enum Faction {
    PLAYER("faction.player", "", ""),
    ARGON("argon", "arg", "Argon"),
    ANTIGONE("antigone", "arg", "Antigone"),
    HATIKVAH("hatikvah", "arg", "HatikvahFreeLeague"),
    PARANID("paranid", "par","GodrealmOfTheParanid"),
    HOLYORDER("holyorder", "par", "HolyOrderFactionLogic"),
    ALLIANCE("alliance", "par", "AllianceOfTheWord"),
    TELADI("teladi", "tel", "TeladiCompany"),
    MINISTRY("ministry", "tel", "MinistryOfFinance"),
    SCALEPLATE("scaleplate", "tel", "ScalePlatePact"),
    XENON("xenon", "xen", "XenonFaction"),
    BUCCANEERS("buccaneers", "par", "Buccaneers"),
    TRINITY("trinity", "par", "Trinity"),
    SPLIT("split", "spl", "Split"),
    FREESPLIT("freesplit", "spl", "FreeSplit"),
    COURT("court", "spl", "Court"),
    FALLENFAMILIES("fallensplit", "spl", "FallenFamilies"),
    TERRAN("terran", "ter", "Terran"),
    PIONEER("pioneers", "ter", "Pioneer"),
    YAKI("yaki", "ter", "Yaki"),
    PLAYEROWNER("player", "", ""),
    OWNERLESS("ownerless", "", "");

    private String name;
    private String raceAbbreviation;
    private String managerName;

    public String getName() {
        return name;
    }

    public String getRaceAbbreviation(){
        return raceAbbreviation;
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

    Faction(String name, String raceAbbreviation, String managerName) {
        this.name = name;
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
