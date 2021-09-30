package model;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.Optional;
import java.util.Random;

import org.javatuples.Pair;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.NoSuchElementException;

import java.util.Stack;
import java.util.Map.Entry;

import java.util.Iterator;

import org.json.JSONTokener;
import org.json.JSONArray;
import org.json.JSONObject;

import configurationmodel.RandomizerConfig;

public class FactionPlacer {

    private Set<Pair<String, OddQHexCoord>> ownedSectors = new HashSet<>();
    private Random rand = new Random( new Date().getTime() );
    private List<Cluster> clusters;
    private Map<String, Integer> factionToSectorCountMap = new HashMap<>();
    private boolean generateConnectedTerritory;

    private static Map<String, OddQHexCoord> OddColUnitDirections = new HashMap<String, OddQHexCoord>() {{
        put( "N",   new OddQHexCoord(  0,  1 ) );
        put( "S",   new OddQHexCoord(  0, -1 ) );
        put( "NE",  new OddQHexCoord(  1,  1 ) );
        put( "NW",  new OddQHexCoord( -1,  1 ) );
        put( "SE",  new OddQHexCoord(  1,  0 ) );
        put( "SW",  new OddQHexCoord( -1,  0 ) );
    }};

    private static Map<String, OddQHexCoord> EvenColUnitDirections = new HashMap<String, OddQHexCoord>() {{
        put( "N",   new OddQHexCoord(  0,  1 ) );
        put( "S",   new OddQHexCoord(  0, -1 ) );
        put( "NE",  new OddQHexCoord(  1,  0 ) );
        put( "NW",  new OddQHexCoord( -1,  0 ) );
        put( "SE",  new OddQHexCoord(  1, -1 ) );
        put( "SW",  new OddQHexCoord( -1, -1 ) );
    }};

    private Map<String, String> factionCapitols = new HashMap<>() {{
        put( "argon", "Argon Prime" );
        put( "antigone", "Antigone Memorial" );
        put( "holyorder", "Holy Vision" );
        put( "paranid", "Trinity Sanctum" );
        put( "teladi", "Profit Center Alpha" );
        put( "ministry", "Eighteen Billion" );
        put( "split", "Zyarth's Dominion");
        put( "freesplit", "Heart of Acrimony" );
        put( "terran", "Sol" );
        put( "pioneers", "Segaris" );
    }};

    public List<Cluster> setClusters( HexagonalMaze maze ) {
        this.clusters = toClusterList( maze );
        return this.clusters;
    }

    public Set<Pair<String, OddQHexCoord>> placeFactions( HexagonalMaze maze, RandomizerConfig config ) {
        generateConnectedTerritory = config.getGenerateConnectedTerritory();

        for( String faction : config.getEnabledFactions() ) {
            Optional<Cluster> deJureCapitol = clusters.stream().filter( cls -> cls.getName().equals( factionCapitols.get( faction ) ) ).findFirst();
            if( deJureCapitol.isPresent( ) ) {
                addFactionHq( faction, deJureCapitol.get() );
            }
            else {
                addFactionHq( faction );
            }
        }

        // while any sector has less than the assigned number of sectors, add a defence station
        // If the user chose a connected map, prioritize adding to clusters adjacent to current territory
        boolean sectorAdded;
        do {
            sectorAdded = claimASectorForEachEnabledFaction( config.getEnabledFactions(), config );
        } while( sectorAdded );

        return ownedSectors;
    }

    private boolean claimASectorForEachEnabledFaction( List<String> enabledFactions, RandomizerConfig config ) {
        boolean addedASector = false;

        for( String faction : enabledFactions ) {
            int maxSectors;
            switch( faction ) {
                case "argon":
                    maxSectors = config.getArgonSectors();
                    break;
                case "antigone":
                    maxSectors = config.getAntigoneSectors();
                    break;
                case "holyorder":
                    maxSectors = config.getHolyOrderSectors();
                    break;
                case "paranid":
                    maxSectors = config.getGodrealmSectors();
                    break;
                case "teladi":
                    maxSectors = config.getTeladiSectors();
                    break;
                case "ministry":
                    maxSectors = config.getMinistrySectors();
                    break;
                case "split":
                    maxSectors = config.getZyarthSectors();
                    break;
                case "freesplit":
                    maxSectors = config.getFreeFamiliesSectors();
                    break;
                case "terran":
                    maxSectors = config.getTerranSectors();
                    break;
                case "pioneers":
                    maxSectors = config.getSegarisSectors();
                    break;
                case "xenon":
                    maxSectors = config.getXenonShipyardCount();
                    break;
                default:
                    throw new IllegalArgumentException( String.format( "Faction %s does not exist.", faction ) );
            }

            if( factionToSectorCountMap.get( faction ) < maxSectors ) {
                if( faction.equals( "xenon" ) ) {
                    addFactionHq( "xenon" );
                    addedASector = true;
                }
                else {
                    addSector( faction );
                    addedASector = true;
                }
            }
        }
        return addedASector;
    }

    private void addFactionHq( String factionName, Cluster deJureCapitol ) {
        deJureCapitol.setFactionHq( Faction.getEnum( factionName ) );
        String[] coordinates = deJureCapitol.getId().split(" ");

        List<Station> stationList = new ArrayList<>();

        Race stationRace = getRace( factionName );

        Station shipyard = new Station();
        shipyard.setFaction( Faction.getEnum( factionName ) );
        shipyard.setOwner( Faction.getEnum( factionName ) );
        shipyard.setRace( stationRace );
        shipyard.setType( StationType.SHIPYARD );

        Station wharf = new Station();
        wharf.setFaction( Faction.getEnum( factionName ) );
        wharf.setOwner( Faction.getEnum( factionName ) );
        wharf.setRace( stationRace );
        wharf.setType( StationType.WHARF );

        Station defense = new Station();
        defense.setFaction( Faction.getEnum( factionName ) );
        defense.setOwner( Faction.getEnum( factionName ) );
        defense.setRace( stationRace );
        defense.setType( StationType.DEFENCE );

        stationList.add( shipyard );
        stationList.add( wharf );
        stationList.add( defense );

        deJureCapitol.setStations( stationList );

        ownedSectors.add( new Pair<>( factionName, new OddQHexCoord(Integer.parseInt( coordinates[0] ), Integer.parseInt( coordinates[1] ) ) ) );
        factionToSectorCountMap.put( factionName, 1 );

        clusters.removeIf( sector -> sector.getName().equals( deJureCapitol.getName() ) );
        clusters.add( deJureCapitol );
    }

    private void addFactionHq( String factionName ) {
        Cluster factionCapitol = getRandomNonOwnedCluster(clusters);
        factionCapitol.setFactionHq( Faction.getEnum( factionName ) );

        List<Station> stationList = new ArrayList<>();

        Race stationRace = getRace( factionName );

        Station shipyard = new Station();
        shipyard.setFaction( Faction.getEnum( factionName ) );
        shipyard.setOwner( Faction.getEnum( factionName ) );
        shipyard.setRace( stationRace );
        shipyard.setType( StationType.SHIPYARD );

        Station wharf = new Station();
        wharf.setFaction( Faction.getEnum( factionName ) );
        wharf.setOwner( Faction.getEnum( factionName ) );
        wharf.setRace( stationRace );
        wharf.setType( StationType.WHARF );

        Station defense = new Station();
        defense.setFaction( Faction.getEnum( factionName ) );
        defense.setOwner( Faction.getEnum( factionName ) );
        defense.setRace( stationRace );
        defense.setType( StationType.DEFENCE );

        stationList.add( shipyard );
        stationList.add( wharf );
        stationList.add( defense );

        factionCapitol.setStations( stationList );

        String[] coordinates = factionCapitol.getId().split(" ");
        ownedSectors.add( new Pair<>( factionName, new OddQHexCoord(Integer.parseInt( coordinates[0] ), Integer.parseInt( coordinates[1] ) ) ) );
        

        clusters.removeIf( sector -> sector.getName().equals( factionCapitol.getName() ) );
        clusters.add( factionCapitol );

        if( factionToSectorCountMap.containsKey( factionName ) ) {
            factionToSectorCountMap.replace( factionName, factionToSectorCountMap.get( factionName ) + 1 );
        }
        else {
            factionToSectorCountMap.put( factionName, 1 );
        }
    }

    private void addSector( String factionName )  {
        Optional<Cluster> prospect = getRandomNonOwnedAdjacentCluster(factionName, clusters);
                
        List<Station> stations = new ArrayList<>();
        Station defenseStation = new Station();
        defenseStation.setFaction( Faction.getEnum( factionName ) );
        defenseStation.setRace( getRace( factionName ) );
        defenseStation.setOwner( Faction.getEnum( factionName ) );
        defenseStation.setType( StationType.DEFENCE );
        stations.add( defenseStation );

        if( generateConnectedTerritory && prospect.isPresent() ) {
            prospect.get().setStations( stations );

            clusters.removeIf( cluster -> cluster.getId().equals( prospect.get().getId() ) );
            clusters.add( prospect.get() );
            
            String[] coordinates = prospect.get().getId().split(" ");
            ownedSectors.add( new Pair<>( factionName, new OddQHexCoord(Integer.parseInt( coordinates[0] ), Integer.parseInt( coordinates[1] ) ) ) );
        }
        else {
            Cluster otherProspect = getRandomNonOwnedCluster(clusters);
            otherProspect.setStations( stations );

            clusters.removeIf( cluster -> cluster.getId().equals( otherProspect.getId() ) );
            clusters.add( otherProspect );

            String[] coordinates = otherProspect.getId().split(" ");
            ownedSectors.add( new Pair<>( factionName, new OddQHexCoord(Integer.parseInt( coordinates[0] ), Integer.parseInt( coordinates[1] ) ) ) );
        }


        factionToSectorCountMap.replace( factionName, factionToSectorCountMap.get( factionName ) + 1 );
    }

    private Cluster getRandomNonOwnedCluster( List<Cluster> clusters ) {
        List<Cluster> nonowned = clusters.stream().filter( cluster -> cluster.getStations().size() == 0 ).collect( Collectors.toList() );

        Optional<Cluster> randomChoice = nonowned.stream().skip( (int) (nonowned.size() * this.rand.nextDouble() ) ).findFirst();

        return randomChoice.orElseThrow( () -> new IllegalStateException( "Cannot get nonowned cluster from fully populated galaxy." ) );
    }

    private Optional<Cluster> getRandomNonOwnedAdjacentCluster( String factionName, List<Cluster> clusters ) {
        // Get owned sectors from the map, collect coordinates to a list
        List<OddQHexCoord> owned = ownedSectors.stream()
                                    .filter( item -> item.getValue0().equals(factionName) )
                                    .collect( Collectors.mapping( Pair::getValue1, Collectors.toList() ) );

        // Convert coordinates to a list of Cluster objects so we know neighbors
        List<Cluster> ownedClusters = new ArrayList<>();
        for( OddQHexCoord hexCoord : owned ) {
            String id = hexCoord.col() + " " + hexCoord.row();
            ownedClusters.add( 
                clusters.stream()
                            .filter( cluster -> cluster.getId().equals( id ) )
                            .findFirst()
                            .orElseThrow( 
                                () -> new IllegalArgumentException( String.format( "Cluster %s does not exist." ) ) 
                            ) 
            );
        }
        
        List<Cluster> nonOwnedNeighbors = new ArrayList<>();

        // Now check for nonwned neighbors
        ownedClusters.forEach( cluster -> {
            cluster.getConnections().forEach( conn -> {
                Cluster neighbor = clusters.stream()
                                        .filter( cls-> cls.getId().equals( conn.getTargetClusterId() ) )
                                        .findFirst()
                                        .orElseThrow( () -> new IllegalArgumentException( String.format( "Neighbor %s of %s does not exist.", conn.getTargetClusterId(), cluster.getId() ) ) );
                if( neighbor.getStations().size() == 0 ) {
                    nonOwnedNeighbors.add( neighbor );
                }
            } );
        } );

        // Return random nonowned neighbor
        return nonOwnedNeighbors.stream().skip( (int) (nonOwnedNeighbors.size() * this.rand.nextDouble() ) ).findFirst();
    }
    
    private Race getRace( String faction ) {
        switch( faction ) {
            case "argon":
                return Race.ARGON;
            case "antigone":
                return Race.ARGON;
            case "pioneers":
                return Race.TERRAN;
            case "terran":
                return Race.TERRAN;
            case "paranid":
                return Race.PARANID;
            case "holyorder":
                return Race.PARANID;
            case "teladi":
                return Race.TELADI;
            case "ministry":
                return Race.TELADI;
            case "split":
                return Race.SPLIT;
            case "freesplit":
                return Race.SPLIT;
            case "xenon":
                return Race.XENON;
            default:
                throw new IllegalArgumentException( String.format( "No race for faction %s", faction ) );
        }
    }

    private List<Cluster> toClusterList( HexagonalMaze hexMaze ) {
        List<Cluster> result = new ArrayList<>();

        // Get the names and descriptions from resources
        Stack<Sector> namesAndDescriptions = new Stack<>();
        try{
            String obj = Files.readString( Path.of("./src/main/java/model/XStars.json") );

            JSONObject jsonObject = new JSONObject( new JSONTokener(obj) );

            JSONArray rawSectors = (JSONArray) jsonObject.get("Sectors");

            List<Sector> sectors = new ArrayList<>();
            rawSectors.forEach( (jObj) -> {
                JSONObject item = (JSONObject) jObj;
                sectors.add( new Sector( item.get("Name").toString(), item.get("Description").toString() ) );
            } );

            Collections.shuffle(sectors );
            Iterator<Sector> iterator = sectors.iterator();

            while( iterator.hasNext() ) {
                namesAndDescriptions.push(  iterator.next() );
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        // Add each node to cluster list with an id
        // Set connections for each cluster
        // Set name and description

        for( Entry<OddQHexCoord, ArrayList<OddQHexCoord>> entry : hexMaze.maze.entrySet() ) {
            Cluster temp = oddQCoordToCluster( entry.getKey() );
            List<Connection> connectionList = new ArrayList<>();
            temp.setId( String.valueOf( entry.getKey().col() ) + " " + String.valueOf( entry.getKey().row() ) );

            entry.getValue().forEach( neighbor -> {
                Connection conn = new Connection();
                conn.setTargetClusterId( String.valueOf( neighbor.col() ) + " " + String.valueOf( neighbor.row() ) );
                conn.setConnectionType( getConnectionType( entry.getKey(), neighbor ) );
                connectionList.add( conn );
            } );

            temp.setConnections( connectionList );

            if( namesAndDescriptions.size() != 0) {
                Sector name = namesAndDescriptions.pop();
                temp.setName( name.Name );
                temp.setDescription( name.Description );
            }
            else {
                temp.setName( String.format("Unknown Sector %s", temp.getId().replace(' ', '-') ) );
                temp.setDescription( "No description available." );
            }

            result.add( temp );
        }

        return result;
    }

    private Cluster oddQCoordToCluster( OddQHexCoord oddQ ) {
        Cluster result = new Cluster();
        result.setX( oddQ.col() );
        result.setY( oddQ.row() );
        return result;
    }

    private ConnectionType getConnectionType( OddQHexCoord source, OddQHexCoord dest ) {
        Cluster sourceX4Offset = oddQCoordToCluster( source );
        Cluster destX4Offset = oddQCoordToCluster( dest );
        
        int unitX = destX4Offset.getX() - sourceX4Offset.getX();
        int unitY = destX4Offset.getY() - sourceX4Offset.getY();
        OddQHexCoord x4UnitDirection = new OddQHexCoord( unitX, unitY );
        Map.Entry<String, OddQHexCoord> entry;
        if( (source.col()&1) != 1 ) {
            entry = EvenColUnitDirections.entrySet().stream()
                .filter( (dir) -> dir.getValue().equals( x4UnitDirection ) )
                .findFirst().orElseThrow( () -> { 
                    throw new NoSuchElementException( 
                        String.format( "Unit direction %s from (%s,%s) to (%s,%s)", 
                            x4UnitDirection.toString(), 
                            sourceX4Offset.getX(), 
                            sourceX4Offset.getY(), 
                            destX4Offset.getX(), 
                            destX4Offset.getY() 
                        ) );
                } );
        }
        else {
            entry = OddColUnitDirections.entrySet().stream()
            .filter( (dir) -> dir.getValue().equals( x4UnitDirection ) )
            .findFirst().orElseThrow( () -> { 
                throw new NoSuchElementException( 
                    String.format( "Unit direction %s from (%s,%s) to (%s,%s)", 
                        x4UnitDirection.toString(), 
                        sourceX4Offset.getX(), 
                        sourceX4Offset.getY(), 
                        destX4Offset.getX(), 
                        destX4Offset.getY() 
                    ) );
            } );
        }
        
        switch( entry.getKey() ) {
            case "N":
                return ConnectionType.N;
            case "S":
                return ConnectionType.S;
            case "NE":
                return ConnectionType.NE;
            case "SW":
                return ConnectionType.SW;
            case "SE":
                return ConnectionType.SE;
            case "NW":
                return ConnectionType.NW;
            default:
                return ConnectionType.CUSTOM;
        }
    }
}


class Sector {
    final String Name;
    final String Description;

    Sector( String name, String desc ) {
        this.Name = name;
        this.Description = desc;
    }

    public String getName() {
        return this.Name;
    }

    public String getDescription() {
        return this.Description;
    }
}
