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

import configurationmodel.GeneratorConfig;

public class FactionPlacer {

    private Set<Pair<String, OddQHexCoord>> ownedSectors = new HashSet<>();
    private Random rand = new Random( new Date().getTime() );
    private List<Cluster> clusters;
    private Map<String, Integer> factionToSectorCountMap = new HashMap<>();
    private boolean generateConnectedTerritory;

    public Set<Pair<String, OddQHexCoord>> placeFactions( Galaxy gal, GeneratorConfig config ) {
        clusters = gal.getClusters();
        generateConnectedTerritory = config.getGenerateConnectedTerritory();

        // For each faction, check for 'de jure' capitols
        // if found, claim for faction
        if( config.getArgonSectors() != 0 ) {
            Optional<Cluster> factionCapitol = clusters.stream().filter( (cluster) -> cluster.getName().equals( "Argon Prime" ) ).findFirst();

            if( factionCapitol.isPresent() ) {
                addFactionHq( "argon", factionCapitol.get() );
            }
        }
        if( config.getAntigoneSectors() != 0 ) {
            Optional<Cluster> factionCapitol = clusters.stream().filter( (cluster) -> cluster.getName().equals( "Antigone Memorial" ) ).findFirst();

            if( factionCapitol.isPresent() ) {
                addFactionHq( "antigone", factionCapitol.get() );
            }
        }
        if( config.getHolyOrderSectors() != 0 ) {
            Optional<Cluster> factionCapitol = clusters.stream().filter( (cluster) -> cluster.getName().equals( "Holy Vision" ) ).findFirst();

            if( factionCapitol.isPresent() ) {
                addFactionHq( "holyorder", factionCapitol.get() );
            }
        }
        if( config.getGodrealmSectors() != 0 ) {
            Optional<Cluster> factionCapitol = clusters.stream().filter( (cluster) -> cluster.getName().equals( "Trinity Sanctum" ) ).findFirst();

            if( factionCapitol.isPresent() ) {
                addFactionHq( "paranid", factionCapitol.get() );
            }
        }
        if( config.getTeladiSectors() != 0 ) {
            Optional<Cluster> factionCapitol = clusters.stream().filter( (cluster) -> cluster.getName().equals( "Profit Center Alpha" ) ).findFirst();

            if( factionCapitol.isPresent() ) {
                addFactionHq( "teladi", factionCapitol.get() );
            }
        }
        if( config.getMinistrySectors() != 0 ) {
            Optional<Cluster> factionCapitol = clusters.stream().filter( (cluster) -> cluster.getName().equals( "Eighteen Billion" ) ).findFirst();

            if( factionCapitol.isPresent() ) {
                addFactionHq( "ministry", factionCapitol.get() );
            }
        }
        if( config.getZyarthSectors() != 0 ) {
            Optional<Cluster> factionCapitol = clusters.stream().filter( (cluster) -> cluster.getName().equals( "Zyarth's Dominion" ) ).findFirst();

            if( factionCapitol.isPresent() ) {
                addFactionHq( "split", factionCapitol.get() );
            }
        }
        if( config.getFreeFamiliesSectors() != 0 ) {
            Optional<Cluster> factionCapitol = clusters.stream().filter( (cluster) -> cluster.getName().equals( "Heart of Acrimony" ) ).findFirst();

            if( factionCapitol.isPresent() ) {
                addFactionHq( "freesplit", factionCapitol.get() );
            }
        }
        if( config.getTerranSectors() != 0 ) {
            Optional<Cluster> factionCapitol = clusters.stream().filter( (cluster) -> cluster.getName().equals( "Sol" ) ).findFirst();

            if( factionCapitol.isPresent() ) {
                addFactionHq( "terran", factionCapitol.get() );
            }
        }
        if( config.getSegarisSectors() != 0 ) {
            Optional<Cluster> factionCapitol = clusters.stream().filter( (cluster) -> cluster.getName().equals( "Segaris" ) ).findFirst();

            if( factionCapitol.isPresent() ) {
                addFactionHq( "pioneers", factionCapitol.get() );
            }
        }

        // If any enabled faction did not receive an HQ, select random non-owned sector and assign
        config.getEnabledFactions().forEach( factionName -> {
            if( !factionToSectorCountMap.containsKey( factionName ) ) {
                addFactionHq( factionName );
            }
        } );

        // while any sector has less than the assigned number of sectors, add a defence station
        // If the user chose a connected map, prioritize adding to clusters adjacent to current territory
        boolean notAllFactionsPlaced = true;
        while( notAllFactionsPlaced ) {
            boolean addedASector = false;
            if( factionToSectorCountMap.getOrDefault( "argon", 0 ) < config.getArgonSectors() ) {
                addSector( "argon" );
                addedASector = true;
            }
            if( factionToSectorCountMap.getOrDefault( "antigone", 0 ) < config.getAntigoneSectors() ) {
                addSector( "antigone" );
                addedASector = true;
            }
            if( factionToSectorCountMap.getOrDefault( "holyorder", 0 ) < config.getHolyOrderSectors() ) {
                addSector( "holyorder" );
                addedASector = true;
            }
            if( factionToSectorCountMap.getOrDefault( "paranid", 0 ) < config.getGodrealmSectors() ) {
                addSector( "paranid" );
                addedASector = true;
            }
            if( factionToSectorCountMap.getOrDefault( "teladi", 0 ) < config.getTeladiSectors() ) {
                addSector( "teladi" );
                addedASector = true;
            }
            if( factionToSectorCountMap.getOrDefault( "ministry", 0 ) < config.getMinistrySectors() ) {
                addSector( "ministry" );
                addedASector = true;
            }
            if( factionToSectorCountMap.getOrDefault( "split", 0 ) < config.getZyarthSectors() ) {
                addSector( "split" );
                addedASector = true;
            }
            if( factionToSectorCountMap.getOrDefault( "freesplit", 0 ) < config.getFreeFamiliesSectors() ) {
                addSector( "freesplit" );
                addedASector = true;
            }
            if( factionToSectorCountMap.getOrDefault( "terran", 0 ) < config.getTerranSectors() ) {
                addSector( "terran" );
                addedASector = true;
            }
            if( factionToSectorCountMap.getOrDefault( "pioneers", 0 ) < config.getSegarisSectors() ) {
                addSector( "pioneers" );
                addedASector = true;
            }
            if( !addedASector ) notAllFactionsPlaced = false;
        }

        placeXenon( config );
        return ownedSectors;
    }

    private void placeXenon( GeneratorConfig config ) {
        int placedShipyards = 0;
        while( placedShipyards < config.getXenonShipyardCount() ) {
            Cluster xenonSector = getRandomNonOwnedCluster(clusters);
            addFactionHq( "xenon", xenonSector );
            placedShipyards++;
        }
    }

    private void addFactionHq( String factionName, Cluster deJureCapitol ) {
        deJureCapitol.setFactionHq( Faction.getEnum( factionName ) );
        String[] coordinates = deJureCapitol.getId().split(" ");
        ownedSectors.add( new Pair<>( factionName, new OddQHexCoord(Integer.parseInt( coordinates[0] ), Integer.parseInt( coordinates[1] ) ) ) );
        factionToSectorCountMap.put( factionName, 1 );

        clusters.removeIf( sector -> sector.getName().equals( deJureCapitol.getName() ) );
        clusters.add( deJureCapitol );
    }

    private void addFactionHq( String factionName ) {
        Cluster factionCapitol = getRandomNonOwnedCluster(clusters);
        factionCapitol.setFactionHq( Faction.getEnum( factionName ) );

        List<Station> stationList = new ArrayList<>();

        Race stationRace;
        switch( Faction.getEnum( factionName ).getRaceAbbreviation() ) {
            case "arg":
                stationRace = Race.ARGON;
                break;
            case "ter":
                stationRace = Race.TERRAN;
                break;
            case "par":
                stationRace = Race.PARANID;
                break;
            case "tel":
                stationRace = Race.TELADI;
                break;
            case "spl":
                stationRace = Race.SPLIT;
                break;
            case "xen":
                stationRace = Race.XENON;
                break;
            default:
                stationRace = Race.ARGON;
        }

        Station shipyard = new Station();
        shipyard.setFaction( Faction.getEnum( factionName ) );
        shipyard.setOwner( Faction.getEnum( factionName ) );
        shipyard.setRace( stationRace );
        shipyard.setType( StationType.SHIPYARD );

        Station wharf = new Station();
        wharf.setFaction( Faction.getEnum( factionName ) );
        wharf.setOwner( Faction.getEnum( factionName ) );
        wharf.setRace( stationRace );
        wharf.setType( StationType.SHIPYARD );

        Station defense = new Station();
        defense.setFaction( Faction.getEnum( factionName ) );
        defense.setOwner( Faction.getEnum( factionName ) );
        defense.setRace( stationRace );
        defense.setType( StationType.SHIPYARD );

        stationList.add( shipyard );
        stationList.add( wharf );
        stationList.add( defense );

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
        switch( Faction.getEnum( factionName ).getRaceAbbreviation() ) {
            case "arg":
                defenseStation.setRace( Race.ARGON );
                break;
            case "ter":
                defenseStation.setRace( Race.TERRAN );
                break;
            case "par":
                defenseStation.setRace( Race.PARANID );
                break;
            case "tel":
                defenseStation.setRace( Race.TELADI );
                break;
            case "spl":
                defenseStation.setRace( Race.SPLIT );
                break;
        }
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
        List<Cluster> nonowned = clusters.stream().filter( cluster -> !ownedSectors.contains( clusterToPair(cluster) ) ).collect( Collectors.toList() );

        Optional<Cluster> randomChoice = nonowned.stream().skip( (int) (nonowned.size() * this.rand.nextDouble() ) ).findFirst();

        return randomChoice.orElseThrow( () -> new IllegalStateException( "Cannot get nonowned cluster from fully populated galaxy." ) );
    }

    private Optional<Cluster> getRandomNonOwnedAdjacentCluster( String factionName, List<Cluster> clusters ) {
        List<Cluster> owned = clusters.stream().filter( cluster -> ownedSectors.contains( clusterToPair(cluster) ) )
                                .filter( cluster -> cluster.getStations().get(0).getFaction().getName().equals( factionName ) )
                                .collect( Collectors.toList() );

        List<Cluster> nonOwnedNeighbor = new ArrayList<>();
        owned.forEach( cluster -> {
            cluster.getConnections().forEach( conn -> {
                Cluster neighbor = clusters.stream()
                                        .filter( cls-> cls.getId().equals( conn.getTargetClusterId() ) )
                                        .findFirst()
                                        .orElseThrow( () -> new IllegalArgumentException( String.format( "Neighbor %s does not exist.", conn.getTargetClusterId() ) ) );
                if( !ownedSectors.contains( clusterToPair( neighbor ) ) ) {
                    nonOwnedNeighbor.add( neighbor );
                }
            } );
        } );

        return nonOwnedNeighbor.stream().skip( (int) (nonOwnedNeighbor.size() * this.rand.nextDouble() ) ).findFirst();
    }

    private Pair<String, OddQHexCoord> clusterToPair( Cluster cluster ) {
        String name;
        if( cluster.getStations().size() == 0 ) {
            name = Faction.OWNERLESS.toString();
        }
        else {
            name = cluster.getStations().get(0).getFaction().getName();
        } 
        String[] idSplitBySpace = cluster.getId().split( " " );
        OddQHexCoord coord = new OddQHexCoord( Integer.parseInt( idSplitBySpace[0] ), Integer.parseInt( idSplitBySpace[1] ) );
        return new Pair<>( name, coord );
    }
    
}
