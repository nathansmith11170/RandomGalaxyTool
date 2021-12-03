package model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.apache.commons.lang3.NotImplementedException;
import org.javatuples.Pair;

import model.jobs.JsonCargo;
import model.jobs.JsonCategory;
import model.jobs.JsonEnvironment;
import model.jobs.JsonJob;
import model.jobs.JsonLevel;
import model.jobs.JsonLoadout;
import model.jobs.JsonLocation;
import model.jobs.JsonModifiers;
import model.jobs.JsonOrder;
import model.jobs.JsonOwner;
import model.jobs.JsonQuota;
import model.jobs.JsonSelect;
import model.jobs.JsonShip;
import model.jobs.JsonValueType;

public class EcoPlacer {

    public List<Cluster> placeMinistryOfFinance(Set<Pair<String, OddQHexCoord>> ownedSectors, List<Cluster> clusters) {
        List<Pair<String, OddQHexCoord>> teladiSectors = ownedSectors.stream()
                .filter((pair) -> pair.getValue0().equals(Faction.TELADI.getName())).collect(Collectors.toList());

        if (teladiSectors.size() == 0)
            return clusters;

        Collections.shuffle(teladiSectors);
        Deque<Pair<String, OddQHexCoord>> factionSectorsDeque = new ArrayDeque<>(teladiSectors);
        OddQHexCoord tradeCoord = factionSectorsDeque.pop().getValue1();
        Optional<Cluster> maybeCluster = clusters.stream()
                .filter((cluster) -> cluster.getId().equals(tradeCoord.col() + "_" + tradeCoord.row())).findFirst();

        Cluster minCapitol = maybeCluster.orElseThrow(() -> new IllegalArgumentException(
                String.format("Cluster ( %s , %s ) does not exist.", tradeCoord.col(), tradeCoord.row())));

        List<Station> stationList = minCapitol.getStations();

        Station shipyard = new Station();
        shipyard.setFaction(Faction.MINISTRY);
        shipyard.setOwner(Faction.MINISTRY);
        shipyard.setRace(Race.TELADI);
        shipyard.setType(StationType.SHIPYARD);

        Station wharf = new Station();
        wharf.setFaction(Faction.MINISTRY);
        wharf.setOwner(Faction.MINISTRY);
        wharf.setRace(Race.TELADI);
        wharf.setType(StationType.WHARF);

        stationList.add(shipyard);
        stationList.add(wharf);

        minCapitol.setStations(stationList);

        clusters.removeIf(sector -> sector.getName().equals(minCapitol.getName()));
        clusters.add(minCapitol);

        return clusters;
    }

    private JsonJob setEcoJob(Faction faction, String Id, String name, boolean commandeerable, boolean rebuild, String order, boolean defaultOrder, HashMap<String, String> params, String basket, String categoryTags, String size, int maxGalaxy, int galaxy, int cluster, String clazz, String locationMacro, String relation, String comparison, boolean buildAtShipyard, String shipTags, double loadoutMin, double loadoutMax, boolean overrideNPC, boolean isMiner, String minerRegion) {
        JsonJob newJob = new JsonJob();

        newJob.setId( faction.getFactionAbbreviation() + Id );
        newJob.setName( name );
        
        JsonModifiers mods = new JsonModifiers();
        mods.setCommandeerable( commandeerable );

        if( !faction.getRace().equals(Race.XENON)) {
            mods.setRebuild( rebuild );
        }


        newJob.setModifiers( mods );
        
        JsonOrder ord = new JsonOrder();
        ord.setOrder(order);
        ord.setDefaultOrder(defaultOrder);
        ord.setParams( params );

        newJob.setOrder( ord );
        newJob.setBasket( basket );

        JsonCategory cat = new JsonCategory();
        cat.setFaction( faction.getName() );
        cat.setSize( size );
        cat.setTags( categoryTags );

        newJob.setCategory( cat );

        JsonQuota quota = new JsonQuota();
        if( maxGalaxy != -1 ) {
            quota.setMaxgalaxy( maxGalaxy );
        }
        quota.setGalaxy( galaxy );
        if( cluster != -1 ) {
            quota.setCluster( cluster );
        }

        newJob.setQuota( quota );

        JsonLocation loc = new JsonLocation();
        loc.setClazz( clazz );
        loc.setFaction( faction.getName() );
        loc.setRelation( relation );
        loc.setComparison( comparison );
        if( categoryTags.equals("[police]") ) {
            loc.setPoliceFaction( faction.getName() );
        }

        if( isMiner ) {
            loc.setRegionBasket(minerRegion);
        }

        newJob.setLocation( loc );

        JsonEnvironment env = new JsonEnvironment();
        env.setBuildatshipyard( buildAtShipyard );

        newJob.setEnvironment( env );

        JsonShip ship = new JsonShip();
        
        JsonSelect sel = new JsonSelect();
        sel.setFaction( faction.getName() );
        sel.setSize( size );
        sel.setTags( shipTags );

        ship.setSelect( sel );

        JsonLoadout load = new JsonLoadout();
        
        JsonLevel level = new JsonLevel();
        level.setMin( loadoutMin );
        level.setMax( loadoutMax );
        load.setLevel( level ); 
        ship.setLoadout( load );

        JsonOwner owner = new JsonOwner();
        owner.setExact( faction.getName() );
        owner.setOverridenpc( true );
        ship.setOwner( owner );

        newJob.setShip( ship );

        if( isMiner ) {
            JsonCargo carg = new JsonCargo();
            JsonValueType val = new JsonValueType();
            val.setMin( "0" );
            val.setMax( "100" );
            val.setProfile( "inversebell" );
            carg.setFillPercent( val );
            ship.setCargo( carg );
        }
        
        return newJob;
    }

    public List<JsonJob> addEcoJobs(Faction faction, int clusterCount,
            String locationMacro) {
        List<JsonJob> jobs = new ArrayList<JsonJob>();

        if (!faction.getRace().equals(Race.TERRAN) && !faction.getRace().equals(Race.XENON)) {

            HashMap<String, String> parms = new HashMap<>();
            parms.put("minbuy", "0");
            parms.put("maxbuy", "5");
            parms.put("minsell", "0");
            parms.put("maxsell", "5");
            jobs.add( setEcoJob( faction,
                              "_bio_trader_l", 
                              "Bio Transporter", 
                              true, 
                              false, 
                              "TradeRoutine", 
                              true, 
                              parms, 
                              "bio", 
                              "[factionlogic, freighter, trader]", 
                              "ship_l",
                              -1, 
                              4*clusterCount, 
                              4, 
                              "galaxy",
                              locationMacro,
                              "self",
                              "ge",
                              true,
                              "[trader, container]",
                              0.0,
                              0.4,
                              true,
                              false,
                              "")
            );

            parms = new HashMap<>();
            parms.put("range", "5");
            jobs.add( setEcoJob( faction,
                              "_bio_trader_m", 
                              "Bio Transporter", 
                              true, 
                              false, 
                              "Middleman", 
                              true, 
                              parms, 
                              "bio", 
                              "[factionlogic, freighter, trader]", 
                              "ship_m",
                              -1, 
                              8*clusterCount, 
                              8, 
                              "galaxy",
                              locationMacro,
                              "self",
                              "ge",
                              true,
                              "[trader, container]",
                              0.0,
                              0.4,
                              true,
                              false,
                              "")
            );

            parms = new HashMap<>();
            parms.put("range", "3");
            jobs.add( setEcoJob( faction,
                              "_food_trader_s", 
                              "Food Distribution", 
                              true, 
                              false, 
                              "Middleman", 
                              true, 
                              parms, 
                              "food", 
                              "[factionlogic, freighter, trader]", 
                              "ship_s",
                              -1, 
                              10*clusterCount, 
                              10, 
                              "galaxy",
                              locationMacro,
                              "self",
                              "ge",
                              true,
                              "[trader, container]",
                              0.0,
                              0.4,
                              true,
                              false,
                              "")
            );

            parms = new HashMap<>();
            parms.put("range", "3");
            jobs.add( setEcoJob( faction,
                              "_medical_trader_s", 
                              "Medical Supplies Distribution", 
                              true, 
                              false, 
                              "Middleman", 
                              true, 
                              parms, 
                              "medicalsupplies", 
                              "[factionlogic, freighter, trader]", 
                              "ship_s",
                              -1, 
                              clusterCount, 
                              1, 
                              "galaxy",
                              locationMacro,
                              "self",
                              "ge",
                              true,
                              "[trader, container]",
                              0.0,
                              0.4,
                              true,
                              false,
                              "")
            );

            parms = new HashMap<>();
            parms.put("minbuy", "0");
            parms.put("maxbuy", "5");
            parms.put("minsell", "0");
            parms.put("maxsell", "5");
            jobs.add( setEcoJob( faction,
                              "_energycell_trader_l", 
                              "Energy Distribution", 
                              true, 
                              false, 
                              "TradeRoutine", 
                              true, 
                              parms, 
                              "energycells", 
                              "[factionlogic, freighter, trader]", 
                              "ship_l",
                              -1, 
                              2*clusterCount, 
                              2, 
                              "galaxy",
                              locationMacro,
                              "self",
                              "ge",
                              true,
                              "[trader, container]",
                              0.0,
                              0.4,
                              true,
                              false,
                              "")
            );

            parms = new HashMap<>();
            parms.put("minbuy", "0");
            parms.put("maxbuy", "5");
            parms.put("minsell", "0");
            parms.put("maxsell", "5");
            jobs.add( setEcoJob( faction,
                              "_energycell_trader_m", 
                              "Energy Distribution", 
                              true, 
                              false, 
                              "TradeRoutine", 
                              true, 
                              parms, 
                              "energycells", 
                              "[factionlogic, freighter, trader]", 
                              "ship_m",
                              -1, 
                              7*clusterCount, 
                              7, 
                              "galaxy",
                              locationMacro,
                              "self",
                              "ge",
                              true,
                              "[trader, container]",
                              0.0,
                              0.4,
                              true,
                              false,
                              "")
            );

            parms = new HashMap<>();
            parms.put("range", "5");
            jobs.add( setEcoJob( faction,
                              "_energycell_trader_s", 
                              "Energy Distribution", 
                              true, 
                              false, 
                              "Middleman", 
                              true, 
                              parms, 
                              "energycells", 
                              "[factionlogic, freighter, trader]", 
                              "ship_s",
                              -1, 
                              clusterCount, 
                              1, 
                              "galaxy",
                              locationMacro,
                              "self",
                              "ge",
                              true,
                              "[trader, container]",
                              0.0,
                              0.4,
                              true,
                              false,
                              "")
            );

            parms = new HashMap<>();
            parms.put("minbuy", "0");
            parms.put("maxbuy", "5");
            parms.put("minsell", "0");
            parms.put("maxsell", "5");
            jobs.add( setEcoJob( faction,
                              "_equipment_trader_l", 
                              "Equipment Distribution", 
                              true, 
                              false, 
                              "TradeRoutine", 
                              true, 
                              parms, 
                              "equipment", 
                              "[factionlogic, freighter, trader]", 
                              "ship_l",
                              -1, 
                              clusterCount, 
                              1, 
                              "galaxy",
                              locationMacro,
                              "self",
                              "ge",
                              true,
                              "[trader, container]",
                              0.0,
                              0.4,
                              true,
                              false,
                              "")
            );

            parms = new HashMap<>();
            parms.put("range", "5");
            jobs.add( setEcoJob( faction,
                              "_equipment_trader_m", 
                              "Equipment Distribution", 
                              true, 
                              false, 
                              "Middleman", 
                              true, 
                              parms, 
                              "equipment", 
                              "[factionlogic, freighter, trader]", 
                              "ship_m",
                              -1, 
                              3*clusterCount, 
                              3, 
                              "galaxy",
                              locationMacro,
                              "self",
                              "ge",
                              true,
                              "[trader, container]",
                              0.0,
                              0.4,
                              true,
                              false,
                              "")
            );

            parms = new HashMap<>();
            parms.put("minbuy", "0");
            parms.put("maxbuy", "5");
            parms.put("minsell", "0");
            parms.put("maxsell", "5");
            jobs.add( setEcoJob( faction,
                              "_construction_trader_l", 
                              "Construction Supply Distribution", 
                              true, 
                              false, 
                              "TradeRoutine", 
                              true, 
                              parms, 
                              "construction_stations", 
                              "[factionlogic, freighter, trader]", 
                              "ship_l",
                              -1, 
                              2*clusterCount, 
                              2, 
                              "galaxy",
                              locationMacro,
                              "self",
                              "ge",
                              true,
                              "[trader, container]",
                              0.0,
                              0.4,
                              true,
                              false,
                              "")
            );

            parms = new HashMap<>();
            parms.put("range", "5");
            jobs.add( setEcoJob( faction,
                              "_construction_trader_m", 
                              "Construction Supply Distribution", 
                              true, 
                              false, 
                              "Middleman", 
                              true, 
                              parms, 
                              "construction_stations", 
                              "[factionlogic, freighter, trader]", 
                              "ship_m",
                              -1, 
                              3*clusterCount, 
                              3, 
                              "galaxy",
                              locationMacro,
                              "self",
                              "ge",
                              true,
                              "[trader, container]",
                              0.0,
                              0.4,
                              true,
                              false,
                              "")
            );

            parms = new HashMap<>();
            parms.put("minbuy", "0");
            parms.put("maxbuy", "5");
            parms.put("minsell", "0");
            parms.put("maxsell", "5");
            jobs.add( setEcoJob( faction,
                              "_ship_construction_trader_l", 
                              "Construction Supply Distribution", 
                              true, 
                              false, 
                              "TradeRoutine", 
                              true, 
                              parms, 
                              "construction_ships", 
                              "[factionlogic, freighter, trader]", 
                              "ship_l",
                              -1, 
                              3, 
                              -1, 
                              "galaxy",
                              locationMacro,
                              "self",
                              "ge",
                              true,
                              "[trader, container]",
                              0.0,
                              0.4,
                              true,
                              false,
                              "")
            );

            parms = new HashMap<>();
            parms.put("minbuy", "0");
            parms.put("maxbuy", "5");
            parms.put("minsell", "0");
            parms.put("maxsell", "5");
            jobs.add( setEcoJob( faction,
                              "_ship_construction_trader_m", 
                              "Construction Supply Distribution", 
                              true, 
                              false, 
                              "TradeRoutine", 
                              true, 
                              parms, 
                              "construction_ships", 
                              "[factionlogic, freighter, trader]", 
                              "ship_m",
                              -1, 
                              clusterCount, 
                              1, 
                              "galaxy",
                              locationMacro,
                              "self",
                              "ge",
                              true,
                              "[trader, container]",
                              0.0,
                              0.4,
                              true,
                              false,
                              "")
            );

            parms = new HashMap<>();
            parms.put("minbuy", "0");
            parms.put("maxbuy", "5");
            parms.put("minsell", "0");
            parms.put("maxsell", "5");
            jobs.add( setEcoJob( faction,
                              "_refinedgas_trader_l", 
                              "Refined Goods Distribution", 
                              true, 
                              false, 
                              "TradeRoutine", 
                              true, 
                              parms, 
                              "refined_gas", 
                              "[factionlogic, freighter, trader]", 
                              "ship_l",
                              -1, 
                              clusterCount, 
                              1, 
                              "galaxy",
                              locationMacro,
                              "self",
                              "ge",
                              true,
                              "[trader, container]",
                              0.0,
                              0.4,
                              true,
                              false,
                              "")
            );

            parms = new HashMap<>();
            parms.put("minbuy", "0");
            parms.put("maxbuy", "5");
            parms.put("minsell", "0");
            parms.put("maxsell", "5");
            jobs.add( setEcoJob( faction,
                              "_refinedgas_trader_m", 
                              "Refined Goods Distribution", 
                              true, 
                              false, 
                              "TradeRoutine", 
                              true, 
                              parms, 
                              "refined_gas", 
                              "[factionlogic, freighter, trader]", 
                              "ship_m",
                              -1, 
                              3*clusterCount, 
                              3, 
                              "galaxy",
                              locationMacro,
                              "self",
                              "ge",
                              true,
                              "[trader, container]",
                              0.0,
                              0.4,
                              true,
                              false,
                              "")
            );

            parms = new HashMap<>();
            parms.put("minbuy", "0");
            parms.put("maxbuy", "5");
            parms.put("minsell", "0");
            parms.put("maxsell", "5");
            jobs.add( setEcoJob( faction,
                              "_refinedmineral_trader_l", 
                              "Refined Goods Distribution", 
                              true, 
                              false, 
                              "TradeRoutine", 
                              true, 
                              parms, 
                              "refined_mineral", 
                              "[factionlogic, freighter, trader]", 
                              "ship_l",
                              -1, 
                              2*clusterCount, 
                              2, 
                              "galaxy",
                              locationMacro,
                              "self",
                              "ge",
                              true,
                              "[trader, container]",
                              0.0,
                              0.4,
                              true,
                              false,
                              "")
            );

            parms = new HashMap<>();
            parms.put("minbuy", "0");
            parms.put("maxbuy", "5");
            parms.put("minsell", "0");
            parms.put("maxsell", "5");
            jobs.add( setEcoJob( faction,
                              "_refinedmineral_trader_m", 
                              "Refined Goods Distribution", 
                              true, 
                              false, 
                              "TradeRoutine", 
                              true, 
                              parms, 
                              "refined_mineral", 
                              "[factionlogic, freighter, trader]", 
                              "ship_m",
                              -1, 
                              3*clusterCount, 
                              3, 
                              "galaxy",
                              locationMacro,
                              "self",
                              "ge",
                              true,
                              "[trader, container]",
                              0.0,
                              0.4,
                              true,
                              false,
                              "")
            );

            parms = new HashMap<>();
            parms.put("minbuy", "0");
            parms.put("maxbuy", "5");
            parms.put("minsell", "0");
            parms.put("maxsell", "5");
            jobs.add( setEcoJob( faction,
                              "_tech_trader_l", 
                              "Tech Distribution", 
                              true, 
                              false, 
                              "TradeRoutine", 
                              true, 
                              parms, 
                              "tech", 
                              "[factionlogic, freighter, trader]", 
                              "ship_l",
                              -1, 
                              2*clusterCount, 
                              2, 
                              "galaxy",
                              locationMacro,
                              "self",
                              "ge",
                              true,
                              "[trader, container]",
                              0.4,
                              0.7,
                              true,
                              false,
                              "")
            );

            parms = new HashMap<>();
            parms.put("range", "5");
            jobs.add( setEcoJob( faction,
                              "_tech_trader_m", 
                              "Tech Distribution", 
                              true, 
                              false, 
                              "Middleman", 
                              true, 
                              parms, 
                              "tech", 
                              "[factionlogic, freighter, trader]", 
                              "ship_m",
                              -1, 
                              3*clusterCount, 
                              3, 
                              "galaxy",
                              locationMacro,
                              "self",
                              "ge",
                              true,
                              "[trader, container]",
                              0.4,
                              0.7,
                              true,
                              false,
                              "")
            );

            parms = new HashMap<>();
            parms.put("range", "5");
            jobs.add( setEcoJob( faction,
                              "_tech_trader_s", 
                              "Tech Distribution", 
                              true, 
                              false, 
                              "Middleman", 
                              true, 
                              parms, 
                              "tech", 
                              "[factionlogic, freighter, trader]", 
                              "ship_s",
                              -1, 
                              3*clusterCount, 
                              3, 
                              "galaxy",
                              locationMacro,
                              "self",
                              "ge",
                              true,
                              "[trader, container]",
                              0.4,
                              0.7,
                              true,
                              false,
                              "")
            );

            parms = new HashMap<>();
            parms.put("minbuy", "0");
            parms.put("maxbuy", "5");
            parms.put("minsell", "0");
            parms.put("maxsell", "5");
            jobs.add( setEcoJob( faction,
                              "_water_trader_l", 
                              "Water Distribution", 
                              true, 
                              false, 
                              "TradeRoutine", 
                              true, 
                              parms, 
                              "water", 
                              "[factionlogic, freighter, trader]", 
                              "ship_l",
                              -1, 
                              clusterCount, 
                              1, 
                              "galaxy",
                              locationMacro,
                              "self",
                              "ge",
                              true,
                              "[trader, container]",
                              0.0,
                              0.4,
                              true,
                              false,
                              "")
            );

            parms = new HashMap<>();
            parms.put("range", "5");
            jobs.add( setEcoJob( faction,
                              "_water_trader_m", 
                              "Water Distribution", 
                              true, 
                              false, 
                              "Middleman", 
                              true, 
                              parms, 
                              "water", 
                              "[factionlogic, freighter, trader]", 
                              "ship_m",
                              -1, 
                              4*clusterCount, 
                              4, 
                              "galaxy",
                              locationMacro,
                              "self",
                              "ge",
                              true,
                              "[trader, container]",
                              0.0,
                              0.4,
                              true,
                              false,
                              "")
            );

            parms = new HashMap<>();
            parms.put("maxbuy", "5");
            jobs.add( setEcoJob( faction,
                              "_stationtrader_l", 
                              "Priority Distribution", 
                              false, 
                              false, 
                              "TradeRoutine", 
                              true, 
                              parms, 
                              "all_container", 
                              "[stationtrader]", 
                              "ship_l",
                              6, 
                              0, 
                              -1, 
                              "galaxy",
                              locationMacro,
                              "self",
                              "ge",
                              true,
                              "[trader, container]",
                              0.0,
                              0.6,
                              true,
                              false,
                              "")
            );

            parms = new HashMap<>();
            parms.put("maxbuy", "5");
            jobs.add( setEcoJob( faction,
                              "_stationtrader_m", 
                              "Priority Distribution", 
                              false, 
                              false, 
                              "TradeRoutine", 
                              true, 
                              parms, 
                              "all_container", 
                              "[stationtrader]", 
                              "ship_m",
                              15, 
                              0, 
                              -1, 
                              "galaxy",
                              locationMacro,
                              "self",
                              "ge",
                              true,
                              "[trader, container]",
                              0.0,
                              0.6,
                              true,
                              false,
                              "")
            );

            parms = new HashMap<>();
            parms.put("minbuy", "0");
            parms.put("maxbuy", "5");
            parms.put("minsell", "0");
            parms.put("maxsell", "5");
            jobs.add( setEcoJob( faction,
                              "_free_miner_l_liquid", 
                              "Gas Miner", 
                              true, 
                              false, 
                              "MiningRoutine", 
                              true, 
                              parms, 
                              "gases", 
                              "[factionlogic, freighter, miner]", 
                              "ship_l",
                              -1, 
                              clusterCount, 
                              1, 
                              "galaxy",
                              locationMacro,
                              "self",
                              "ge",
                              true,
                              "[miner, liquid]",
                              0.0,
                              0.7,
                              true,
                              true,
                              "gases")
            );

            parms = new HashMap<>();
            parms.put("minbuy", "0");
            parms.put("maxbuy", "5");
            parms.put("minsell", "0");
            parms.put("maxsell", "5");
            jobs.add( setEcoJob( faction,
                              "_free_miner_m_liquid", 
                              "Gas Miner", 
                              true, 
                              false, 
                              "MiningRoutine", 
                              true, 
                              parms, 
                              "gases", 
                              "[factionlogic, freighter, miner]", 
                              "ship_m",
                              -1, 
                              3 * clusterCount, 
                              3, 
                              "galaxy",
                              locationMacro,
                              "self",
                              "ge",
                              true,
                              "[miner, liquid]",
                              0.0,
                              0.7,
                              true,
                              true,
                              "gases")
            );

            parms = new HashMap<>();
            parms.put("minbuy", "0");
            parms.put("maxbuy", "5");
            parms.put("minsell", "0");
            parms.put("maxsell", "5");
            jobs.add( setEcoJob( faction,
                              "_free_miner_l_mineral", 
                              "Mineral Reclaimer", 
                              true, 
                              false, 
                              "MiningRoutine", 
                              true, 
                              parms, 
                              "minerals", 
                              "[factionlogic, freighter, miner]", 
                              "ship_l",
                              -1, 
                              clusterCount, 
                              1, 
                              "galaxy",
                              locationMacro,
                              "self",
                              "ge",
                              true,
                              "[miner, solid]",
                              0.0,
                              0.7,
                              true,
                              true,
                              "minerals")
            );

            parms = new HashMap<>();
            parms.put("minbuy", "0");
            parms.put("maxbuy", "5");
            parms.put("minsell", "0");
            parms.put("maxsell", "5");
            jobs.add( setEcoJob( faction,
                              "_free_miner_m_mineral", 
                              "Mineral Reclaimer", 
                              true, 
                              false, 
                              "MiningRoutine", 
                              true, 
                              parms, 
                              "minerals", 
                              "[factionlogic, freighter, miner]", 
                              "ship_m",
                              -1, 
                              3*clusterCount, 
                              3, 
                              "galaxy",
                              locationMacro,
                              "self",
                              "ge",
                              true,
                              "[miner, solid]",
                              0.0,
                              0.7,
                              true,
                              true,
                              "minerals")
            );

            parms = new HashMap<>();
            parms.put("minbuy", "0");
            parms.put("maxbuy", "5");
            parms.put("minsell", "0");
            parms.put("maxsell", "5");
            jobs.add( setEcoJob( faction,
                              "_free_miner_s_mineral", 
                              "Mineralogist", 
                              true, 
                              false, 
                              "MiningRoutine", 
                              true, 
                              parms, 
                              "minerals", 
                              "[factionlogic, freighter, miner]", 
                              "ship_s",
                              -1, 
                              2*clusterCount, 
                              2, 
                              "galaxy",
                              locationMacro,
                              "self",
                              "ge",
                              true,
                              "[miner, solid]",
                              0.0,
                              0.7,
                              true,
                              true,
                              "minerals")
            );

            parms = new HashMap<>();
            parms.put("minbuy", "0");
            parms.put("maxbuy", "5");
            parms.put("minsell", "0");
            parms.put("maxsell", "5");
            jobs.add( setEcoJob( faction,
                              "_free_miner_l_ice", 
                              "Ice Miner", 
                              true, 
                              false, 
                              "MiningRoutine", 
                              true, 
                              parms, 
                              "ice", 
                              "[factionlogic, freighter, miner]", 
                              "ship_l",
                              -1, 
                              clusterCount, 
                              1, 
                              "galaxy",
                              locationMacro,
                              "self",
                              "ge",
                              true,
                              "[miner, solid]",
                              0.0,
                              0.7,
                              true,
                              true,
                              "ice")
            );

            parms = new HashMap<>();
            parms.put("minbuy", "0");
            parms.put("maxbuy", "5");
            parms.put("minsell", "0");
            parms.put("maxsell", "5");
            jobs.add( setEcoJob( faction,
                              "_free_miner_m_ice", 
                              "Ice Miner", 
                              true, 
                              false, 
                              "MiningRoutine", 
                              true, 
                              parms, 
                              "ice", 
                              "[factionlogic, freighter, miner]", 
                              "ship_l",
                              -1, 
                              3*clusterCount, 
                              3, 
                              "galaxy",
                              locationMacro,
                              "self",
                              "ge",
                              true,
                              "[miner, solid]",
                              0.0,
                              0.7,
                              true,
                              true,
                              "ice")
            );

            parms = new HashMap<>();
            parms.put("maxjumps", "6");
            jobs.add( setEcoJob( faction,
                              "_construction_vessel_xl", 
                              "Constructor", 
                              true, 
                              false, 
                              "FindBuildTasks", 
                              true, 
                              parms, 
                              "ice", 
                              "[factionlogic, builder]", 
                              "ship_xl",
                              15, 
                              clusterCount, 
                              1, 
                              "galaxy",
                              locationMacro,
                              "self",
                              "ge",
                              true,
                              "[builder]",
                              0.4,
                              0.7,
                              true,
                              false,
                              "false")
            );

            parms = new HashMap<>();
            jobs.add( setEcoJob( faction,
                              "_police_patrol_s", 
                              "Police", 
                              true, 
                              false, 
                              "Police", 
                              true, 
                              parms, 
                              "all_container", 
                              "[police]", 
                              "ship_s",
                              -1, 
                              3*clusterCount, 
                              3, 
                              "galaxy",
                              locationMacro,
                              "self",
                              "ge",
                              true,
                              "[fighter, light]",
                              0.4,
                              0.7,
                              true,
                              false,
                              "false")
            );

        }
        if ( faction.getRace().equals( Race.TERRAN ) ) {

            HashMap<String, String> parms = new HashMap<>();
            parms.put("minbuy", "0");
            parms.put("maxbuy", "5");
            parms.put("minsell", "0");
            parms.put("maxsell", "5");
            jobs.add( setEcoJob( faction,
                              "_bio_trader_l", 
                              "Food Distribution", 
                              true, 
                              false, 
                              "TradeRoutine", 
                              true, 
                              parms, 
                              "food_terran", 
                              "[factionlogic, freighter, trader]", 
                              "ship_l",
                              -1, 
                              4*clusterCount, 
                              4, 
                              "galaxy",
                              locationMacro,
                              "self",
                              "ge",
                              true,
                              "[trader, container]",
                              0.0,
                              0.4,
                              true,
                              false,
                              "")
            );

            parms = new HashMap<>();
            parms.put("range", "5");
            jobs.add( setEcoJob( faction,
                              "_food_trader_m", 
                              "Food Distribution", 
                              true, 
                              false, 
                              "Middleman", 
                              true, 
                              parms, 
                              "food_terran", 
                              "[factionlogic, freighter, trader]", 
                              "ship_m",
                              -1, 
                              8*clusterCount, 
                              8, 
                              "galaxy",
                              locationMacro,
                              "self",
                              "ge",
                              true,
                              "[trader, container]",
                              0.0,
                              0.4,
                              true,
                              false,
                              "")
            );

            parms = new HashMap<>();
            parms.put("range", "3");
            jobs.add( setEcoJob( faction,
                              "_food_trader_s", 
                              "Food Distribution", 
                              true, 
                              false, 
                              "Middleman", 
                              true, 
                              parms, 
                              "food_terran", 
                              "[factionlogic, freighter, trader]", 
                              "ship_s",
                              -1, 
                              10*clusterCount, 
                              10, 
                              "galaxy",
                              locationMacro,
                              "self",
                              "ge",
                              true,
                              "[trader, container]",
                              0.0,
                              0.4,
                              true,
                              false,
                              "")
            );

            parms = new HashMap<>();
            parms.put("range", "3");
            jobs.add( setEcoJob( faction,
                              "_medical_trader_s", 
                              "Medical Supplies Distribution", 
                              true, 
                              false, 
                              "Middleman", 
                              true, 
                              parms, 
                              "medicalsupplies", 
                              "[factionlogic, freighter, trader]", 
                              "ship_s",
                              -1, 
                              clusterCount, 
                              1, 
                              "galaxy",
                              locationMacro,
                              "self",
                              "ge",
                              true,
                              "[trader, container]",
                              0.0,
                              0.4,
                              true,
                              false,
                              "")
            );

            parms = new HashMap<>();
            parms.put("minbuy", "0");
            parms.put("maxbuy", "5");
            parms.put("minsell", "0");
            parms.put("maxsell", "5");
            jobs.add( setEcoJob( faction,
                              "_energycell_trader_l", 
                              "Energy Distribution", 
                              true, 
                              false, 
                              "TradeRoutine", 
                              true, 
                              parms, 
                              "energycells", 
                              "[factionlogic, freighter, trader]", 
                              "ship_l",
                              -1, 
                              2*clusterCount, 
                              2, 
                              "galaxy",
                              locationMacro,
                              "self",
                              "ge",
                              true,
                              "[trader, container]",
                              0.0,
                              0.4,
                              true,
                              false,
                              "")
            );

            parms = new HashMap<>();
            parms.put("minbuy", "0");
            parms.put("maxbuy", "5");
            parms.put("minsell", "0");
            parms.put("maxsell", "5");
            jobs.add( setEcoJob( faction,
                              "_energycell_trader_m", 
                              "Energy Distribution", 
                              true, 
                              false, 
                              "TradeRoutine", 
                              true, 
                              parms, 
                              "energycells", 
                              "[factionlogic, freighter, trader]", 
                              "ship_m",
                              -1, 
                              7*clusterCount, 
                              7, 
                              "galaxy",
                              locationMacro,
                              "self",
                              "ge",
                              true,
                              "[trader, container]",
                              0.0,
                              0.4,
                              true,
                              false,
                              "")
            );

            parms = new HashMap<>();
            parms.put("range", "5");
            jobs.add( setEcoJob( faction,
                              "_energycell_trader_s", 
                              "Energy Distribution", 
                              true, 
                              false, 
                              "Middleman", 
                              true, 
                              parms, 
                              "energycells", 
                              "[factionlogic, freighter, trader]", 
                              "ship_s",
                              -1, 
                              clusterCount, 
                              1, 
                              "galaxy",
                              locationMacro,
                              "self",
                              "ge",
                              true,
                              "[trader, container]",
                              0.0,
                              0.4,
                              true,
                              false,
                              "")
            );

            parms = new HashMap<>();
            parms.put("minbuy", "0");
            parms.put("maxbuy", "5");
            parms.put("minsell", "0");
            parms.put("maxsell", "5");
            jobs.add( setEcoJob( faction,
                              "_equipment_trader_l", 
                              "Equipment Distribution", 
                              true, 
                              false, 
                              "TradeRoutine", 
                              true, 
                              parms, 
                              "tech_terran", 
                              "[factionlogic, freighter, trader]", 
                              "ship_l",
                              -1, 
                              clusterCount, 
                              1, 
                              "galaxy",
                              locationMacro,
                              "self",
                              "ge",
                              true,
                              "[trader, container]",
                              0.0,
                              0.4,
                              true,
                              false,
                              "")
            );

            parms = new HashMap<>();
            parms.put("range", "5");
            jobs.add( setEcoJob( faction,
                              "_equipment_trader_m", 
                              "Equipment Distribution", 
                              true, 
                              false, 
                              "Middleman", 
                              true, 
                              parms, 
                              "tech_terran", 
                              "[factionlogic, freighter, trader]", 
                              "ship_m",
                              -1, 
                              3*clusterCount, 
                              3, 
                              "galaxy",
                              locationMacro,
                              "self",
                              "ge",
                              true,
                              "[trader, container]",
                              0.0,
                              0.4,
                              true,
                              false,
                              "")
            );

            parms = new HashMap<>();
            parms.put("minbuy", "0");
            parms.put("maxbuy", "5");
            parms.put("minsell", "0");
            parms.put("maxsell", "5");
            jobs.add( setEcoJob( faction,
                              "_construction_trader_l", 
                              "Construction Supply Distribution", 
                              true, 
                              false, 
                              "TradeRoutine", 
                              true, 
                              parms, 
                              "construction_stations_terran", 
                              "[factionlogic, freighter, trader]", 
                              "ship_l",
                              -1, 
                              2*clusterCount, 
                              2, 
                              "galaxy",
                              locationMacro,
                              "self",
                              "ge",
                              true,
                              "[trader, container]",
                              0.0,
                              0.4,
                              true,
                              false,
                              "")
            );

            parms = new HashMap<>();
            parms.put("range", "5");
            jobs.add( setEcoJob( faction,
                              "_construction_trader_m", 
                              "Construction Supply Distribution", 
                              true, 
                              false, 
                              "Middleman", 
                              true, 
                              parms, 
                              "construction_stations_terran", 
                              "[factionlogic, freighter, trader]", 
                              "ship_m",
                              -1, 
                              3*clusterCount, 
                              3, 
                              "galaxy",
                              locationMacro,
                              "self",
                              "ge",
                              true,
                              "[trader, container]",
                              0.0,
                              0.4,
                              true,
                              false,
                              "")
            );

            parms = new HashMap<>();
            parms.put("minbuy", "0");
            parms.put("maxbuy", "5");
            parms.put("minsell", "0");
            parms.put("maxsell", "5");
            jobs.add( setEcoJob( faction,
                              "_ship_construction_trader_l", 
                              "Construction Supply Distribution", 
                              true, 
                              false, 
                              "TradeRoutine", 
                              true, 
                              parms, 
                              "construction_ships_terran", 
                              "[factionlogic, freighter, trader]", 
                              "ship_l",
                              -1, 
                              3, 
                              -1, 
                              "galaxy",
                              locationMacro,
                              "self",
                              "ge",
                              true,
                              "[trader, container]",
                              0.0,
                              0.4,
                              true,
                              false,
                              "")
            );

            parms = new HashMap<>();
            parms.put("minbuy", "0");
            parms.put("maxbuy", "5");
            parms.put("minsell", "0");
            parms.put("maxsell", "5");
            jobs.add( setEcoJob( faction,
                              "_ship_construction_trader_m", 
                              "Construction Supply Distribution", 
                              true, 
                              false, 
                              "TradeRoutine", 
                              true, 
                              parms, 
                              "construction_ships_terran", 
                              "[factionlogic, freighter, trader]", 
                              "ship_m",
                              -1, 
                              clusterCount, 
                              1, 
                              "galaxy",
                              locationMacro,
                              "self",
                              "ge",
                              true,
                              "[trader, container]",
                              0.0,
                              0.4,
                              true,
                              false,
                              "")
            );

            parms = new HashMap<>();
            parms.put("minbuy", "0");
            parms.put("maxbuy", "5");
            parms.put("minsell", "0");
            parms.put("maxsell", "5");
            jobs.add( setEcoJob( faction,
                              "_tech_trader_l", 
                              "Tech Distribution", 
                              true, 
                              false, 
                              "TradeRoutine", 
                              true, 
                              parms, 
                              "tech_terran", 
                              "[factionlogic, freighter, trader]", 
                              "ship_l",
                              -1, 
                              2*clusterCount, 
                              2, 
                              "galaxy",
                              locationMacro,
                              "self",
                              "ge",
                              true,
                              "[trader, container]",
                              0.4,
                              0.7,
                              true,
                              false,
                              "")
            );

            parms = new HashMap<>();
            parms.put("range", "5");
            jobs.add( setEcoJob( faction,
                              "_tech_trader_m", 
                              "Tech Distribution", 
                              true, 
                              false, 
                              "Middleman", 
                              true, 
                              parms, 
                              "tech_terran", 
                              "[factionlogic, freighter, trader]", 
                              "ship_m",
                              -1, 
                              3*clusterCount, 
                              3, 
                              "galaxy",
                              locationMacro,
                              "self",
                              "ge",
                              true,
                              "[trader, container]",
                              0.4,
                              0.7,
                              true,
                              false,
                              "")
            );

            parms = new HashMap<>();
            parms.put("range", "5");
            jobs.add( setEcoJob( faction,
                              "_tech_trader_s", 
                              "Tech Distribution", 
                              true, 
                              false, 
                              "Middleman", 
                              true, 
                              parms, 
                              "tech_terran", 
                              "[factionlogic, freighter, trader]", 
                              "ship_s",
                              -1, 
                              3*clusterCount, 
                              3, 
                              "galaxy",
                              locationMacro,
                              "self",
                              "ge",
                              true,
                              "[trader, container]",
                              0.4,
                              0.7,
                              true,
                              false,
                              "")
            );

            parms = new HashMap<>();
            parms.put("minbuy", "0");
            parms.put("maxbuy", "5");
            parms.put("minsell", "0");
            parms.put("maxsell", "5");
            jobs.add( setEcoJob( faction,
                              "_water_trader_l", 
                              "Water Distribution", 
                              true, 
                              false, 
                              "TradeRoutine", 
                              true, 
                              parms, 
                              "water", 
                              "[factionlogic, freighter, trader]", 
                              "ship_l",
                              -1, 
                              clusterCount, 
                              1, 
                              "galaxy",
                              locationMacro,
                              "self",
                              "ge",
                              true,
                              "[trader, container]",
                              0.0,
                              0.4,
                              true,
                              false,
                              "")
            );

            parms = new HashMap<>();
            parms.put("range", "5");
            jobs.add( setEcoJob( faction,
                              "_water_trader_m", 
                              "Water Distribution", 
                              true, 
                              false, 
                              "Middleman", 
                              true, 
                              parms, 
                              "water", 
                              "[factionlogic, freighter, trader]", 
                              "ship_m",
                              -1, 
                              4*clusterCount, 
                              4, 
                              "galaxy",
                              locationMacro,
                              "self",
                              "ge",
                              true,
                              "[trader, container]",
                              0.0,
                              0.4,
                              true,
                              false,
                              "")
            );

            parms = new HashMap<>();
            parms.put("maxbuy", "5");
            jobs.add( setEcoJob( faction,
                              "_stationtrader_l", 
                              "Priority Distribution", 
                              false, 
                              false, 
                              "TradeRoutine", 
                              true, 
                              parms, 
                              "all_container_terran", 
                              "[stationtrader]", 
                              "ship_l",
                              6, 
                              0, 
                              -1, 
                              "galaxy",
                              locationMacro,
                              "self",
                              "ge",
                              true,
                              "[trader, container]",
                              0.0,
                              0.6,
                              true,
                              false,
                              "")
            );

            parms = new HashMap<>();
            parms.put("maxbuy", "5");
            jobs.add( setEcoJob( faction,
                              "_stationtrader_m", 
                              "Priority Distribution", 
                              false, 
                              false, 
                              "TradeRoutine", 
                              true, 
                              parms, 
                              "all_container_terran", 
                              "[stationtrader]", 
                              "ship_m",
                              15, 
                              0, 
                              -1, 
                              "galaxy",
                              locationMacro,
                              "self",
                              "ge",
                              true,
                              "[trader, container]",
                              0.0,
                              0.6,
                              true,
                              false,
                              "")
            );

            parms = new HashMap<>();
            parms.put("minbuy", "0");
            parms.put("maxbuy", "5");
            parms.put("minsell", "0");
            parms.put("maxsell", "5");
            jobs.add( setEcoJob( faction,
                              "_free_miner_l_liquid", 
                              "Gas Miner", 
                              true, 
                              false, 
                              "MiningRoutine", 
                              true, 
                              parms, 
                              "gases", 
                              "[factionlogic, freighter, miner]", 
                              "ship_l",
                              -1, 
                              clusterCount, 
                              1, 
                              "galaxy",
                              locationMacro,
                              "self",
                              "ge",
                              true,
                              "[miner, liquid]",
                              0.0,
                              0.7,
                              true,
                              true,
                              "gases")
            );

            parms = new HashMap<>();
            parms.put("minbuy", "0");
            parms.put("maxbuy", "5");
            parms.put("minsell", "0");
            parms.put("maxsell", "5");
            jobs.add( setEcoJob( faction,
                              "_free_miner_m_liquid", 
                              "Gas Miner", 
                              true, 
                              false, 
                              "MiningRoutine", 
                              true, 
                              parms, 
                              "gases", 
                              "[factionlogic, freighter, miner]", 
                              "ship_m",
                              -1, 
                              3 * clusterCount, 
                              3, 
                              "galaxy",
                              locationMacro,
                              "self",
                              "ge",
                              true,
                              "[miner, liquid]",
                              0.0,
                              0.7,
                              true,
                              true,
                              "gases")
            );

            parms = new HashMap<>();
            parms.put("minbuy", "0");
            parms.put("maxbuy", "5");
            parms.put("minsell", "0");
            parms.put("maxsell", "5");
            jobs.add( setEcoJob( faction,
                              "_free_miner_l_mineral", 
                              "Mineral Reclaimer", 
                              true, 
                              false, 
                              "MiningRoutine", 
                              true, 
                              parms, 
                              "minerals", 
                              "[factionlogic, freighter, miner]", 
                              "ship_l",
                              -1, 
                              clusterCount, 
                              1, 
                              "galaxy",
                              locationMacro,
                              "self",
                              "ge",
                              true,
                              "[miner, solid]",
                              0.0,
                              0.7,
                              true,
                              true,
                              "minerals")
            );

            parms = new HashMap<>();
            parms.put("minbuy", "0");
            parms.put("maxbuy", "5");
            parms.put("minsell", "0");
            parms.put("maxsell", "5");
            jobs.add( setEcoJob( faction,
                              "_free_miner_m_mineral", 
                              "Mineral Reclaimer", 
                              true, 
                              false, 
                              "MiningRoutine", 
                              true, 
                              parms, 
                              "minerals", 
                              "[factionlogic, freighter, miner]", 
                              "ship_m",
                              -1, 
                              3*clusterCount, 
                              3, 
                              "galaxy",
                              locationMacro,
                              "self",
                              "ge",
                              true,
                              "[miner, solid]",
                              0.0,
                              0.7,
                              true,
                              true,
                              "minerals")
            );

            parms = new HashMap<>();
            parms.put("minbuy", "0");
            parms.put("maxbuy", "5");
            parms.put("minsell", "0");
            parms.put("maxsell", "5");
            jobs.add( setEcoJob( faction,
                              "_free_miner_s_mineral", 
                              "Mineralogist", 
                              true, 
                              false, 
                              "MiningRoutine", 
                              true, 
                              parms, 
                              "minerals", 
                              "[factionlogic, freighter, miner]", 
                              "ship_s",
                              -1, 
                              2*clusterCount, 
                              2, 
                              "galaxy",
                              locationMacro,
                              "self",
                              "ge",
                              true,
                              "[miner, solid]",
                              0.0,
                              0.7,
                              true,
                              true,
                              "minerals")
            );

            parms = new HashMap<>();
            parms.put("minbuy", "0");
            parms.put("maxbuy", "5");
            parms.put("minsell", "0");
            parms.put("maxsell", "5");
            jobs.add( setEcoJob( faction,
                              "_free_miner_l_ice", 
                              "Ice Miner", 
                              true, 
                              false, 
                              "MiningRoutine", 
                              true, 
                              parms, 
                              "ice", 
                              "[factionlogic, freighter, miner]", 
                              "ship_l",
                              -1, 
                              clusterCount, 
                              1, 
                              "galaxy",
                              locationMacro,
                              "self",
                              "ge",
                              true,
                              "[miner, solid]",
                              0.0,
                              0.7,
                              true,
                              true,
                              "ice")
            );

            parms = new HashMap<>();
            parms.put("minbuy", "0");
            parms.put("maxbuy", "5");
            parms.put("minsell", "0");
            parms.put("maxsell", "5");
            jobs.add( setEcoJob( faction,
                              "_free_miner_m_ice", 
                              "Ice Miner", 
                              true, 
                              false, 
                              "MiningRoutine", 
                              true, 
                              parms, 
                              "ice", 
                              "[factionlogic, freighter, miner]", 
                              "ship_l",
                              -1, 
                              3*clusterCount, 
                              3, 
                              "galaxy",
                              locationMacro,
                              "self",
                              "ge",
                              true,
                              "[miner, solid]",
                              0.0,
                              0.7,
                              true,
                              true,
                              "ice")
            );

            parms = new HashMap<>();
            parms.put("maxjumps", "6");
            jobs.add( setEcoJob( faction,
                              "_construction_vessel_xl", 
                              "Constructor", 
                              true, 
                              false, 
                              "FindBuildTasks", 
                              true, 
                              parms, 
                              "ice", 
                              "[factionlogic, builder]", 
                              "ship_xl",
                              15, 
                              clusterCount, 
                              1, 
                              "galaxy",
                              locationMacro,
                              "self",
                              "ge",
                              true,
                              "[builder]",
                              0.4,
                              0.7,
                              true,
                              false,
                              "false")
            );

            parms = new HashMap<>();
            jobs.add( setEcoJob( faction,
                              "_police_patrol_s", 
                              "Police", 
                              true, 
                              false, 
                              "Police", 
                              true, 
                              parms, 
                              "all_container", 
                              "[police]", 
                              "ship_s",
                              -1, 
                              3*clusterCount, 
                              3, 
                              "galaxy",
                              locationMacro,
                              "self",
                              "ge",
                              true,
                              "[fighter, light]",
                              0.4,
                              0.7,
                              true,
                              false,
                              "false")
            );

        }
        if (faction.getRace().equals(Race.XENON)) {
            
            HashMap<String, String> parms = new HashMap<>();
            parms.put("minbuy", "0");
            parms.put("maxbuy", "5");
            parms.put("minsell", "0");
            parms.put("maxsell", "5");
            jobs.add( setEcoJob( faction,
                              "_free_miner_m_mineral", 
                              "Collector", 
                              true, 
                              false, 
                              "MiningRoutine", 
                              true, 
                              parms, 
                              "minerals_xenon", 
                              "[factionlogic, miner]", 
                              "ship_m",
                              -1, 
                              8*clusterCount, 
                              8, 
                              "galaxy",
                              locationMacro,
                              "self",
                              "ge",
                              true,
                              "[miner, solid]",
                              0.0,
                              0.7,
                              true,
                              true,
                              "minerals_xenon")
            );

            parms = new HashMap<>();
            parms.put("minbuy", "0");
            parms.put("maxbuy", "5");
            parms.put("minsell", "0");
            parms.put("maxsell", "5");
            jobs.add( setEcoJob( faction,
                              "_free_trader_m_energy", 
                              "Transporter", 
                              true, 
                              false, 
                              "TradeRoutine", 
                              true, 
                              parms, 
                              "energycells", 
                              "[factionlogic, freighter, trader]", 
                              "ship_m",
                              -1, 
                              6*clusterCount, 
                              6, 
                              "galaxy",
                              locationMacro,
                              "self",
                              "ge",
                              true,
                              "[miner, solid]",
                              0.0,
                              0.7,
                              true,
                              false,
                              "")
            );

        }

        return jobs;
    }

    public List<Cluster> placeTradeStations(Faction faction, Set<Pair<String, OddQHexCoord>> ownedSectors,
            List<Cluster> clusters) {
        List<Pair<String, OddQHexCoord>> factionSectors = ownedSectors.stream()
                .filter((pair) -> pair.getValue0().equals(faction.getName())).collect(Collectors.toList());
        int ownedSectorCount = (int) (long) factionSectors.size();
        int tradeStationCount = (int) (ownedSectorCount * .2);

        Collections.shuffle(factionSectors);
        Deque<Pair<String, OddQHexCoord>> factionSectorsDeque = new ArrayDeque<>(factionSectors);
        for (int i = 0; i < tradeStationCount; i++) {
            OddQHexCoord tradeCoord = factionSectorsDeque.pop().getValue1();
            Optional<Cluster> maybeCluster = clusters.stream()
                    .filter((cluster) -> cluster.getId().equals(tradeCoord.col() + "_" + tradeCoord.row())).findFirst();

            Cluster tradeCluster = maybeCluster.orElseThrow(() -> new IllegalArgumentException(
                    String.format("Cluster ( %s , %s ) does not exist.", tradeCoord.col(), tradeCoord.row())));

            clusters.remove(tradeCluster);

            Station tradeStation = new Station();
            tradeStation.setType(StationType.TRADE);
            tradeStation.setOwner(faction);
            tradeStation.setRace(faction.getRace());
            tradeStation.setFaction(faction);

            List<Station> stations = tradeCluster.getStations();
            stations.add(tradeStation);
            tradeCluster.setStations(stations);

            clusters.add(tradeCluster);
        }

        return clusters;
    }

    private Product placeProductStation( Faction faction, int galaxyQuota, String ware, String id ) {
        Product result = new Product();
        result.setOwner( faction );
        result.setId( id );
        result.setRace( faction.getRace() );
        result.setWare( ware );

        ProductLocation loc = new ProductLocation();
        List<Faction> spawns = new ArrayList<>();
        spawns.add( faction );
        loc.setSpawnLocations( spawns );

        result.setLocationInfo( loc );
        result.setGalaxyQuota( galaxyQuota );

        return result;
    }

    /*
     * Base economy is 1 module of every step 2 product, which is
     *      1 Missile Components, 1 Wapon Components, 1 Drone Components, 1 Antimatter Converters, 1 Claytronics, 1 Shield Components, 1 Field Coils, 1 Advanced Electronics
     * In addition to any material used in end products which is
     *      2 Hull Parts, 2 Advanced Composites, 2 Engine Parts, 2 Smart Chips
     * The workforce for these is around 10k, if fully staffed, so that requires
     *      4 food ingredient, 2 spice, 2 water, 4 food product, 6 medical supply
     * 
     * Supporting modules for the above:
     *      5 Ecell, 4 graphene, 3 refined metal, 3 plasma conductor, 2 superfluid coolant, 3 microchip, 1 scanning array, 1 antimatter cell, 2 quantum tube
     * 
     * In general, production stations have a 25% chance to have energy cells production so only 1 dedicated Ecell station is probably needed per round. Graphene is a
     * pretty common addon so 2 per round seems reasonable.
     */

    public List<Product> addEconomy(Faction faction, Set<Pair<String, OddQHexCoord>> ownedSectors) {
        ArrayList<Product> products = new ArrayList<Product>();

        int ownedSectorCount = (int) (long) ownedSectors.stream()
                .filter((pair) -> pair.getValue0().equals(faction.getName())).count();

        // 7 zones per sector, one station per zone, subtract defense stations and trade stations and shipyard and wharf
        int stationLimit = ownedSectorCount * 6 - (int) (ownedSectorCount * .2) - 2;

        Map<String, Integer> quotaMap = new HashMap<>();

        // Universal (NOT TER)
        quotaMap.put("advancedcomposites", 0);
        quotaMap.put("advancedelectronics", 0);
        quotaMap.put("antimattercells", 0);
        quotaMap.put("antimatterconverters", 0);
        quotaMap.put("claytronics", 0);
        quotaMap.put("dronecomponents", 0);
        quotaMap.put("energycells", 0);
        quotaMap.put("engineparts", 0);
        quotaMap.put("fieldcoils", 0);       
        quotaMap.put("graphene", 0);
        quotaMap.put("hullparts", 0);
        quotaMap.put("medicalsupplies", 0);
        quotaMap.put("microchips", 0);
        quotaMap.put("missilecomponents", 0);
        quotaMap.put("plasmaconductors", 0);
        quotaMap.put("quantumtubes", 0);
        quotaMap.put("refinedmetals", 0);
        quotaMap.put("scanningarrays", 0);
        quotaMap.put("shieldcomponents", 0);
        quotaMap.put("siliconwafers", 0);
        quotaMap.put("smartchips", 0);
        quotaMap.put("spices", 0);
        quotaMap.put("superfluidcoolant", 0);
        quotaMap.put("turretcomponents", 0);
        quotaMap.put("water", 0);
        quotaMap.put("weaponcomponents", 0);

        //Terran
        quotaMap.put("computronicsubstrate", 0);
        quotaMap.put("metallicmicrolattice", 0);
        quotaMap.put("siliconcarbide", 0);
        quotaMap.put("proteinpaste", 0);
        quotaMap.put("terranmre", 0);
        quotaMap.put("stimulants", 0);

        //Argon
        quotaMap.put("foodrations", 0);
        quotaMap.put("meat", 0);
        quotaMap.put("wheat", 0);
        quotaMap.put("spacefuel", 0);

        //Teladi
        quotaMap.put("swampplant", 0);
        quotaMap.put("teladianium", 0);
        quotaMap.put("sunriseflowers", 0);
        quotaMap.put("spaceweed", 0);
        quotaMap.put("nostropoil", 0);

        //Paranid
        quotaMap.put("majasnails", 0);
        quotaMap.put("sojabeans", 0);
        quotaMap.put("sojahusk", 0);

        //Split
        quotaMap.put("scruffinfruits", 0);
        quotaMap.put("cheltmeat", 0);

        int stationsPerRound = 0;
        switch( faction.getRace() ) {
            case ARGON:
                // Add universal stations
                stationsPerRound += 26;
                // Add Argon specific, minus spacefuel
                stationsPerRound += 3;
                break; 
            case TELADI:
                // Add universal stations minus refined metals
                stationsPerRound += 25;
                // Add Teladi specific
                stationsPerRound = 5;
                break;
            case PARANID:
                // Add universal stations
                stationsPerRound += 26;
                // Add Paranid specific
                stationsPerRound += 3;
                break;
            case SPLIT:
                // Add universal stations
                stationsPerRound += 26;
                // Add Split specific
                stationsPerRound += 2;
                break;
            case TERRAN:
                // Add Terran stations
                stationsPerRound += 6;
                break;
            case XENON:
                // Add solar
                stationsPerRound += 1;
                break;
        }

        while( quotaMap.values().stream().reduce(0, Integer::sum ) + stationsPerRound < stationLimit ) {
            switch( faction.getRace() ) {
                case ARGON:
                    quotaMap.replace( "advancedcomposites", quotaMap.get( "advancedcomposites" ) + 1 );
                    quotaMap.replace( "advancedelectronics", quotaMap.get( "advancedelectronics" ) + 1 );
                    quotaMap.replace( "antimattercells", quotaMap.get( "antimattercells" ) + 1 );
                    quotaMap.replace( "antimatterconverters", quotaMap.get( "antimatterconverters" ) + 1 );
                    quotaMap.replace( "claytronics", quotaMap.get( "claytronics" ) + 1 );
                    quotaMap.replace( "dronecomponents", quotaMap.get( "dronecomponents" ) + 1 );
                    quotaMap.replace( "energycells", quotaMap.get( "energycells" ) + 1 );
                    quotaMap.replace( "engineparts", quotaMap.get( "engineparts" ) + 1 );
                    quotaMap.replace( "fieldcoils", quotaMap.get( "fieldcoils" ) + 1 );       
                    quotaMap.replace( "graphene", quotaMap.get( "graphene" ) + 1 );
                    quotaMap.replace( "hullparts", quotaMap.get( "hullparts" ) + 1 );
                    quotaMap.replace( "medicalsupplies", quotaMap.get( "medicalsupplies" ) + 1 );
                    quotaMap.replace( "microchips", quotaMap.get( "microchips" ) + 1 );
                    quotaMap.replace( "missilecomponents", quotaMap.get( "missilecomponents" ) + 1 );
                    quotaMap.replace( "plasmaconductors", quotaMap.get( "plasmaconductors" ) + 1 );
                    quotaMap.replace( "quantumtubes", quotaMap.get( "quantumtubes" ) + 1 );
                    quotaMap.replace( "refinedmetals", quotaMap.get( "refinedmetals" ) + 1 );
                    quotaMap.replace( "scanningarrays", quotaMap.get( "scanningarrays" ) + 1 );
                    quotaMap.replace( "shieldcomponents", quotaMap.get( "shieldcomponents" ) + 1 );
                    quotaMap.replace( "siliconwafers", quotaMap.get( "siliconwafers" ) + 1 );
                    quotaMap.replace( "smartchips", quotaMap.get( "smartchips" ) + 1 );
                    quotaMap.replace( "spices", quotaMap.get( "spices" ) + 1 );
                    quotaMap.replace( "superfluidcoolant", quotaMap.get( "superfluidcoolant" ) + 1 );
                    quotaMap.replace( "turretcomponents", quotaMap.get( "turretcomponents" ) + 1 );
                    quotaMap.replace( "water", quotaMap.get( "water" ) + 1 );
                    quotaMap.replace( "weaponcomponents", quotaMap.get( "weaponcomponents" ) + 1 );
                    quotaMap.replace( "foodrations", quotaMap.get( "foodrations" ) + 1 );
                    quotaMap.replace( "meat", quotaMap.get( "meat" ) + 1 );
                    quotaMap.replace( "wheat", quotaMap.get( "wheat" ) + 1 );
                    if( quotaMap.get( "spacefuel" ) == 0 && faction.equals( Faction.ARGON ) ) {
                        quotaMap.replace( "spacefuel", 1 );
                    }
                    break; 
                case TELADI:
                    quotaMap.replace( "advancedcomposites", quotaMap.get( "advancedcomposites" ) + 1 );
                    quotaMap.replace( "advancedelectronics", quotaMap.get( "advancedelectronics" ) + 1 );
                    quotaMap.replace( "antimattercells", quotaMap.get( "antimattercells" ) + 1 );
                    quotaMap.replace( "antimatterconverters", quotaMap.get( "antimatterconverters" ) + 1 );
                    quotaMap.replace( "claytronics", quotaMap.get( "claytronics" ) + 1 );
                    quotaMap.replace( "dronecomponents", quotaMap.get( "dronecomponents" ) + 1 );
                    quotaMap.replace( "energycells", quotaMap.get( "energycells" ) + 1 );
                    quotaMap.replace( "engineparts", quotaMap.get( "engineparts" ) + 1 );
                    quotaMap.replace( "fieldcoils", quotaMap.get( "fieldcoils" ) + 1 );       
                    quotaMap.replace( "graphene", quotaMap.get( "graphene" ) + 1 );
                    quotaMap.replace( "hullparts", quotaMap.get( "hullparts" ) + 1 );
                    quotaMap.replace( "medicalsupplies", quotaMap.get( "medicalsupplies" ) + 1 );
                    quotaMap.replace( "microchips", quotaMap.get( "microchips" ) + 1 );
                    quotaMap.replace( "missilecomponents", quotaMap.get( "missilecomponents" ) + 1 );
                    quotaMap.replace( "plasmaconductors", quotaMap.get( "plasmaconductors" ) + 1 );
                    quotaMap.replace( "quantumtubes", quotaMap.get( "quantumtubes" ) + 1 );
                    quotaMap.replace( "scanningarrays", quotaMap.get( "scanningarrays" ) + 1 );
                    quotaMap.replace( "shieldcomponents", quotaMap.get( "shieldcomponents" ) + 1 );
                    quotaMap.replace( "siliconwafers", quotaMap.get( "siliconwafers" ) + 1 );
                    quotaMap.replace( "smartchips", quotaMap.get( "smartchips" ) + 1 );
                    quotaMap.replace( "spices", quotaMap.get( "spices" ) + 1 );
                    quotaMap.replace( "superfluidcoolant", quotaMap.get( "superfluidcoolant" ) + 1 );
                    quotaMap.replace( "turretcomponents", quotaMap.get( "turretcomponents" ) + 1 );
                    quotaMap.replace( "water", quotaMap.get( "water" ) + 1 );
                    quotaMap.replace( "weaponcomponents", quotaMap.get( "weaponcomponents" ) + 1 );
                    quotaMap.replace( "swampplant", quotaMap.get( "swampplant" ) + 1 );
                    quotaMap.replace( "teladianium", quotaMap.get( "teladianium" ) + 1 );
                    quotaMap.replace( "sunriseflowers", quotaMap.get( "sunriseflowers" ) + 1 );
                    quotaMap.replace( "nostropoil", 0 );
                    if( quotaMap.get( "spaceweed" ) == 0 ) {
                        quotaMap.replace( "spaceweed", 1 );
                    }
                    break;
                case PARANID:
                    quotaMap.replace( "advancedcomposites", quotaMap.get( "advancedcomposites" ) + 1 );
                    quotaMap.replace( "advancedelectronics", quotaMap.get( "advancedelectronics" ) + 1 );
                    quotaMap.replace( "antimattercells", quotaMap.get( "antimattercells" ) + 1 );
                    quotaMap.replace( "antimatterconverters", quotaMap.get( "antimatterconverters" ) + 1 );
                    quotaMap.replace( "claytronics", quotaMap.get( "claytronics" ) + 1 );
                    quotaMap.replace( "dronecomponents", quotaMap.get( "dronecomponents" ) + 1 );
                    quotaMap.replace( "energycells", quotaMap.get( "energycells" ) + 1 );
                    quotaMap.replace( "engineparts", quotaMap.get( "engineparts" ) + 1 );
                    quotaMap.replace( "fieldcoils", quotaMap.get( "fieldcoils" ) + 1 );       
                    quotaMap.replace( "graphene", quotaMap.get( "graphene" ) + 1 );
                    quotaMap.replace( "hullparts", quotaMap.get( "hullparts" ) + 1 );
                    quotaMap.replace( "medicalsupplies", quotaMap.get( "medicalsupplies" ) + 1 );
                    quotaMap.replace( "microchips", quotaMap.get( "microchips" ) + 1 );
                    quotaMap.replace( "missilecomponents", quotaMap.get( "missilecomponents" ) + 1 );
                    quotaMap.replace( "plasmaconductors", quotaMap.get( "plasmaconductors" ) + 1 );
                    quotaMap.replace( "quantumtubes", quotaMap.get( "quantumtubes" ) + 1 );
                    quotaMap.replace( "refinedmetals", quotaMap.get( "refinedmetals" ) + 1 );
                    quotaMap.replace( "scanningarrays", quotaMap.get( "scanningarrays" ) + 1 );
                    quotaMap.replace( "shieldcomponents", quotaMap.get( "shieldcomponents" ) + 1 );
                    quotaMap.replace( "siliconwafers", quotaMap.get( "siliconwafers" ) + 1 );
                    quotaMap.replace( "smartchips", quotaMap.get( "smartchips" ) + 1 );
                    quotaMap.replace( "spices", quotaMap.get( "spices" ) + 1 );
                    quotaMap.replace( "superfluidcoolant", quotaMap.get( "superfluidcoolant" ) + 1 );
                    quotaMap.replace( "turretcomponents", quotaMap.get( "turretcomponents" ) + 1 );
                    quotaMap.replace( "water", quotaMap.get( "water" ) + 1 );
                    quotaMap.replace( "weaponcomponents", quotaMap.get( "weaponcomponents" ) + 1 );
                    quotaMap.replace( "majasnails", quotaMap.get( "majasnails" ) + 1 );
                    quotaMap.replace( "sojabeans", quotaMap.get( "sojabeans" ) + 1 );
                    quotaMap.replace( "sojahusk",quotaMap.get( "sojahusk" ) + 1 );
                    break;
                case SPLIT:
                    quotaMap.replace( "advancedcomposites", quotaMap.get( "advancedcomposites" ) + 1 );
                    quotaMap.replace( "advancedelectronics", quotaMap.get( "advancedelectronics" ) + 1 );
                    quotaMap.replace( "antimattercells", quotaMap.get( "antimattercells" ) + 1 );
                    quotaMap.replace( "antimatterconverters", quotaMap.get( "antimatterconverters" ) + 1 );
                    quotaMap.replace( "claytronics", quotaMap.get( "claytronics" ) + 1 );
                    quotaMap.replace( "dronecomponents", quotaMap.get( "dronecomponents" ) + 1 );
                    quotaMap.replace( "energycells", quotaMap.get( "energycells" ) + 1 );
                    quotaMap.replace( "engineparts", quotaMap.get( "engineparts" ) + 1 );
                    quotaMap.replace( "fieldcoils", quotaMap.get( "fieldcoils" ) + 1 );       
                    quotaMap.replace( "graphene", quotaMap.get( "graphene" ) + 1 );
                    quotaMap.replace( "hullparts", quotaMap.get( "hullparts" ) + 1 );
                    quotaMap.replace( "medicalsupplies", quotaMap.get( "medicalsupplies" ) + 1 );
                    quotaMap.replace( "microchips", quotaMap.get( "microchips" ) + 1 );
                    quotaMap.replace( "missilecomponents", quotaMap.get( "missilecomponents" ) + 1 );
                    quotaMap.replace( "plasmaconductors", quotaMap.get( "plasmaconductors" ) + 1 );
                    quotaMap.replace( "quantumtubes", quotaMap.get( "quantumtubes" ) + 1 );
                    quotaMap.replace( "refinedmetals", quotaMap.get( "refinedmetals" ) + 1 );
                    quotaMap.replace( "scanningarrays", quotaMap.get( "scanningarrays" ) + 1 );
                    quotaMap.replace( "shieldcomponents", quotaMap.get( "shieldcomponents" ) + 1 );
                    quotaMap.replace( "siliconwafers", quotaMap.get( "siliconwafers" ) + 1 );
                    quotaMap.replace( "smartchips", quotaMap.get( "smartchips" ) + 1 );
                    quotaMap.replace( "spices", quotaMap.get( "spices" ) + 1 );
                    quotaMap.replace( "superfluidcoolant", quotaMap.get( "superfluidcoolant" ) + 1 );
                    quotaMap.replace( "turretcomponents", quotaMap.get( "turretcomponents" ) + 1 );
                    quotaMap.replace( "water", quotaMap.get( "water" ) + 1 );
                    quotaMap.replace( "weaponcomponents", quotaMap.get( "weaponcomponents" ) + 1 );
                    quotaMap.replace( "scruffinfruits", quotaMap.get( "scruffinfruits" ) + 1 );
                    quotaMap.replace( "cheltmeat", quotaMap.get( "cheltmeat" ) + 1 );
                    break;
                case TERRAN:
                    quotaMap.replace( "computronicsubstrate", quotaMap.get( "computronicsubstrate" ) + 1 );
                    quotaMap.replace( "metallicmicrolattice", quotaMap.get( "metallicmicrolattice" ) + 1 );
                    quotaMap.replace( "siliconcarbide", quotaMap.get( "siliconcarbide" ) + 1);
                    quotaMap.replace( "proteinpaste", quotaMap.get( "proteinpaste" ) + 1 );
                    quotaMap.replace( "terranmre", quotaMap.get( "terranmre" ) + 1 );
                    quotaMap.replace( "stimulants", quotaMap.get( "stimulants" ) + 1 );
                    break;
                case XENON:
                    quotaMap.replace( "energycells", quotaMap.get( "energycells" ) + 1 );
                    break;
            }
        }

        for( Entry<String, Integer> product : quotaMap.entrySet() ) {
            if( product.getValue() != 0 )
                products.add( placeProductStation( faction, product.getValue(), product.getKey(), String.format( "%s_%s", faction.getFactionAbbreviation(), product.getKey() ) ) );
        }

        return products;
    }

    private JsonJob setMilJob(Faction faction, String id, String name, boolean subordinate, boolean commandeerable, String order, boolean defaultOrder, HashMap<String, String> params, String categoryTags, String size, int maxGalalaxyQuota, int galaxyQuota, int clusterQuota, int wingQuota, String locationClass, String locationMacro, String relation, String comparison, boolean matchExtension, boolean buildAtShipyard, String selectTags, String loadoutVariationMin, String loadoutVariationMax, String loadoutLevel, boolean overrideNpc, List<String> subordinates) {
        JsonJob newJob = new JsonJob();

        newJob.setId( id );
        newJob.setName( name );
        if( subordinate ) {
            newJob.setStartActive(false);
        }

        
        JsonModifiers mods = new JsonModifiers();
        if( commandeerable )
            mods.setCommandeerable( commandeerable );
        if( subordinate )
            mods.setSubordinate( subordinate );

        JsonOrder ord = new JsonOrder();
        ord.setDefaultOrder( defaultOrder );
        ord.setOrder( order );
        ord.setParams( params );

        JsonCategory cat = new JsonCategory();
        cat.setFaction( faction.getName() );
        cat.setSize( size );
        cat.setTags( categoryTags );
        
        JsonQuota quota = new JsonQuota();
        if( clusterQuota != -1 ) {
            quota.setCluster( clusterQuota );
        }
        if( galaxyQuota != -1 ) {
            quota.setGalaxy( galaxyQuota );
        }
        if( maxGalalaxyQuota != -1 ) {
            quota.setMaxgalaxy( maxGalalaxyQuota );
        }
        if( wingQuota != -1 ){
            quota.setWing( wingQuota );
        }

        JsonLocation loc = new JsonLocation();
        loc.setClazz( locationClass );
        loc.setMacro( locationMacro );
        loc.setComparison( comparison );
        loc.setFaction( faction.getName() );
        loc.setMatchExtension( matchExtension );
        loc.setRelation( relation );

        JsonEnvironment env = new JsonEnvironment();
        env.setBuildatshipyard( buildAtShipyard );
        
        JsonShip ship = new JsonShip();
        JsonOwner own = new JsonOwner();
        own.setExact( faction.getName() );
        own.setOverridenpc( overrideNpc );
        ship.setOwner( own );
        
        JsonSelect sel = new JsonSelect();
        sel.setFaction(faction.getName());
        sel.setSize(size);
        sel.setTags(selectTags);

        ship.setSelect( sel );

        newJob.setModifiers( mods );
        newJob.setOrder( ord );
        newJob.setCategory( cat );
        newJob.setQuota( quota );
        newJob.setLocation( loc );
        newJob.setEnvironment( env );
        newJob.setShip( ship );
        newJob.setSubordinates( subordinates );

        return newJob;
    }

    public List<JsonJob> addMilitaryJobs(Faction faction, int clusterCount, String galaxyMacro) {
        switch( faction ) {
            case ARGON:
                return addArgonMil(faction, clusterCount, galaxyMacro);
            case ANTIGONE:
                return addArgonMil(faction, clusterCount, galaxyMacro);
            case PARANID:
                return addParanidMil(faction, clusterCount, galaxyMacro);
            case HOLYORDER:
                return addParanidMil(faction, clusterCount, galaxyMacro);
            case SPLIT:
                return addSplitMil(faction, clusterCount, galaxyMacro);
            case FREESPLIT:
                return addSplitMil(faction, clusterCount, galaxyMacro);
            case TELADI:
                return addTeladiMil(faction, clusterCount, galaxyMacro);
            case TERRAN:
                return addTerranMil(faction, clusterCount, galaxyMacro);
            case PIONEER:
                return addTerranMil(faction, clusterCount, galaxyMacro);
            case XENON:
                return addXenonMil(faction, clusterCount, galaxyMacro);
            default:
                throw new NotImplementedException("Military generation for a faction which has not been implemented was attempted.");
        }
    }

    private List<JsonJob> addArgonMil(Faction faction, int clusterCount, String galaxyMacro) {
        ArrayList<JsonJob> jobs = new ArrayList<>();

        HashMap<String, String> parms = new HashMap<>();
        parms.put("range", "class.cluster");

        ArrayList<String> subordinates = new ArrayList<>();
        subordinates.add( faction.getFactionAbbreviation()+"_destroyer_escort_l" );
        subordinates.add( faction.getFactionAbbreviation()+"_frigate_escort_m" );
        subordinates.add( faction.getFactionAbbreviation()+"_frigate_escort_m" );
        subordinates.add( faction.getFactionAbbreviation()+"_frigate_escort_m" );
        subordinates.add( faction.getFactionAbbreviation()+"_frigate_escort_m" );
        subordinates.add( faction.getFactionAbbreviation()+"_heavyfighter_escort_s_carrier" );
        subordinates.add( faction.getFactionAbbreviation()+"_fighter_escort_s_carrier" );
        subordinates.add( faction.getFactionAbbreviation()+"_fighter_escort_s_carrier" );
        subordinates.add( faction.getFactionAbbreviation()+"_fighter_escort_s_carrier" );
        subordinates.add( faction.getFactionAbbreviation()+"_fighter_escort_s_carrier" );
        subordinates.add( faction.getFactionAbbreviation()+"_resupplier_escort_xl" );

        jobs.add(
            setMilJob(
                faction,
                faction.getFactionAbbreviation()+"_carrier_patrol_xl_sector",
                "Patrol Carrier",
                false,
                true,
                "Patrol",
                true,
                parms,
                "[factionlogic, military, carrier]",
                "ship_xl",
                clusterCount / 4,
                -1,
                1,
                -1,
                "galaxy",
                galaxyMacro,
                "self",
                "exact",
                false,
                true,
                "[military, carrier]",
                "0.0",
                "0.3",
                "1.0",
                true,
                subordinates
            ) );

        parms = new HashMap<>();
        parms.put("range", "class.cluster");

        subordinates = new ArrayList<>();
        subordinates.add( faction.getFactionAbbreviation()+"_frigate_escort_m" );
        subordinates.add( faction.getFactionAbbreviation()+"_frigate_escort_m" );
        subordinates.add( faction.getFactionAbbreviation()+"_fighter_escort_s_sector" );
        subordinates.add( faction.getFactionAbbreviation()+"_fighter_escort_s_sector" );
        subordinates.add( faction.getFactionAbbreviation()+"_fighter_escort_s_sector" );

        jobs.add(
                setMilJob(
                    faction,
                    faction.getFactionAbbreviation()+"_destroyer_patrol_l_sector",
                    "Patrol Destroyer",
                    false,
                    true,
                    "Patrol",
                    true,
                    parms,
                    "[factionlogic, military, destroyer]",
                    "ship_l",
                    -1,
                    clusterCount,
                    2,
                    -1,
                    "galaxy",
                    galaxyMacro,
                    "self",
                    "exact",
                    false,
                    true,
                    "[military, destroyer]",
                    "0.0",
                    "0.3",
                    "1.0",
                    true,
                    subordinates
                ) );

        parms = new HashMap<>();
        parms.put("range", "class.cluster");

        subordinates = new ArrayList<>();
        subordinates.add( faction.getFactionAbbreviation()+"_fighter_escort_s_sector" );
        subordinates.add( faction.getFactionAbbreviation()+"_fighter_escort_s_sector" );

        jobs.add(
                setMilJob(
                    faction,
                    faction.getFactionAbbreviation()+"_frigate_patrol_m_sector",
                    "Patrol Frigate",
                    false,
                    true,
                    "Patrol",
                    true,
                    parms,
                    "[factionlogic, military, frigate]",
                    "ship_m",
                    -1,
                    2*clusterCount,
                    2,
                    -1,
                    "galaxy",
                    galaxyMacro,
                    "self",
                    "exact",
                    false,
                    true,
                    "[military, frigate]",
                    "0.0",
                    "0.3",
                    "1.0",
                    true,
                    subordinates
                ) );

        parms = new HashMap<>();
        parms.put("formation", "formationshape.invpointguard");
        parms.put("overrideformationskill", "true");
        
        subordinates = new ArrayList<>();

        jobs.add(
                setMilJob(
                    faction,
                    faction.getFactionAbbreviation()+"_destroyer_escort_l",
                    "Escort Destroyer",
                    true,
                    false,
                    "Escort",
                    true,
                    parms,
                    "[military, destroyer]",
                    "ship_l",
                    -1,
                    -1,
                    -1,
                    1,
                    "galaxy",
                    galaxyMacro,
                    "self",
                    "exact",
                    false,
                    true,
                    "[military, destroyer]",
                    "0.0",
                    "0.3",
                    "1.0",
                    true,
                    subordinates
                ) );

        parms = new HashMap<>();
        parms.put("formation", "formationshape.pointguard");
        parms.put("overrideformationskill", "true");
        
        subordinates = new ArrayList<>();

        jobs.add(
                setMilJob(
                    faction,
                    faction.getFactionAbbreviation()+"_frigate_escort_m",
                    "Escort Frigate",
                    true,
                    false,
                    "Escort",
                    true,
                    parms,
                    "[military, frigate]",
                    "ship_m",
                    -1,
                    -1,
                    -1,
                    1,
                    "galaxy",
                    galaxyMacro,
                    "self",
                    "exact",
                    false,
                    true,
                    "[military, frigate]",
                    "0.0",
                    "0.3",
                    "1.0",
                    true,
                    subordinates
                ) );
        
        parms = new HashMap<>();
        parms.put("formation", "formationshape.triangle");
        parms.put("overrideformationskill", "true");
        
        subordinates = new ArrayList<>();

        jobs.add(
                setMilJob(
                    faction,
                    faction.getFactionAbbreviation()+"_fighter_escort_s_carrier",
                    "Carrier Fighter",
                    true,
                    false,
                    "Escort",
                    true,
                    parms,
                    "[military, fighter]",
                    "ship_s",
                    -1,
                    -1,
                    -1,
                    10,
                    "galaxy",
                    galaxyMacro,
                    "self",
                    "exact",
                    false,
                    true,
                    "[military, fighter]",
                    "0.0",
                    "0.3",
                    "1.0",
                    true,
                    subordinates
                ) );

        parms = new HashMap<>();
        parms.put("formation", "formationshape.triangle");
        parms.put("overrideformationskill", "true");
        
        subordinates = new ArrayList<>();

        jobs.add(
                setMilJob(
                    faction,
                    faction.getFactionAbbreviation()+"_heavyfighter_escort_s_carrier",
                    "Carrier Fighter",
                    true,
                    false,
                    "Escort",
                    true,
                    parms,
                    "[military, fighter, heavy]",
                    "ship_s",
                    -1,
                    -1,
                    -1,
                    10,
                    "galaxy",
                    galaxyMacro,
                    "self",
                    "exact",
                    false,
                    true,
                    "[military, fighter]",
                    "0.0",
                    "0.3",
                    "1.0",
                    true,
                    subordinates
                ) );

        parms = new HashMap<>();
        parms.put("formation", "formationshape.triangle");
        parms.put("overrideformationskill", "true");
        
        subordinates = new ArrayList<>();

        jobs.add(
                setMilJob(
                    faction,
                    faction.getFactionAbbreviation()+"_fighter_escort_s_sector",
                    "Escort Fighter",
                    true,
                    false,
                    "Escort",
                    true,
                    parms,
                    "[military, fighter]",
                    "ship_s",
                    -1,
                    -1,
                    -1,
                    5,
                    "galaxy",
                    galaxyMacro,
                    "self",
                    "exact",
                    false,
                    true,
                    "[military, fighter]",
                    "0.0",
                    "0.3",
                    "1.0",
                    true,
                    subordinates
                ) );

        parms = new HashMap<>();   
        subordinates = new ArrayList<>();
        jobs.add(
                setMilJob(
                    faction,
                    faction.getFactionAbbreviation()+"_resupplier_escort_xl",
                    "Resupply",
                    true,
                    false,
                    "SupplyFleet",
                    true,
                    parms,
                    "[resupplier]",
                    "ship_xl",
                    -1,
                    -1,
                    -1,
                    1,
                    "galaxy",
                    galaxyMacro,
                    "self",
                    "exact",
                    false,
                    true,
                    "[resupplier]",
                    "0.0",
                    "0.3",
                    "1.0",
                    true,
                    subordinates
                ) );


        return jobs;
    }

    private List<JsonJob> addParanidMil(Faction faction, int clusterCount, String galaxyMacro) {
        ArrayList<JsonJob> jobs = new ArrayList<>();

        HashMap<String, String> parms = new HashMap<>();
        parms.put("range", "class.cluster");

        ArrayList<String> subordinates = new ArrayList<>();
        subordinates.add( faction.getFactionAbbreviation()+"_destroyer_escort_l" );
        subordinates.add( faction.getFactionAbbreviation()+"_frigate_escort_m" );
        subordinates.add( faction.getFactionAbbreviation()+"_frigate_escort_m" );
        subordinates.add( faction.getFactionAbbreviation()+"_frigate_escort_m" );
        subordinates.add( faction.getFactionAbbreviation()+"_frigate_escort_m" );
        subordinates.add( faction.getFactionAbbreviation()+"_heavyfighter_escort_s_carrier" );
        subordinates.add( faction.getFactionAbbreviation()+"_heavyfighter_escort_s_carrier" );
        subordinates.add( faction.getFactionAbbreviation()+"_fighter_escort_s_carrier" );
        subordinates.add( faction.getFactionAbbreviation()+"_fighter_escort_s_carrier" );
        subordinates.add( faction.getFactionAbbreviation()+"_fighter_escort_s_carrier" );
        subordinates.add( faction.getFactionAbbreviation()+"_fighter_escort_s_carrier" );
        subordinates.add( faction.getFactionAbbreviation()+"_resupplier_escort_xl" );

        jobs.add(
            setMilJob(
                faction,
                faction.getFactionAbbreviation()+"_carrier_patrol_xl_sector",
                "Patrol Carrier",
                false,
                true,
                "Patrol",
                true,
                parms,
                "[factionlogic, military, carrier]",
                "ship_xl",
                clusterCount / 2,
                -1,
                2,
                -1,
                "galaxy",
                galaxyMacro,
                "self",
                "exact",
                false,
                true,
                "[military, carrier]",
                "0.0",
                "0.3",
                "1.0",
                true,
                subordinates
            ) );

        parms = new HashMap<>();
        parms.put("range", "class.cluster");

        parms = new HashMap<>();
        parms.put("range", "class.cluster");

        subordinates = new ArrayList<>();
        subordinates.add( faction.getFactionAbbreviation()+"_fighter_escort_s_sector" );
        subordinates.add( faction.getFactionAbbreviation()+"_fighter_escort_s_sector" );
        subordinates.add( faction.getFactionAbbreviation()+"_fighter_escort_s_sector" );

        jobs.add(
                setMilJob(
                    faction,
                    faction.getFactionAbbreviation()+"_frigate_patrol_m_sector",
                    "Patrol Frigate",
                    false,
                    true,
                    "Patrol",
                    true,
                    parms,
                    "[factionlogic, military, frigate]",
                    "ship_m",
                    -1,
                    2*clusterCount,
                    2,
                    -1,
                    "galaxy",
                    galaxyMacro,
                    "self",
                    "exact",
                    false,
                    true,
                    "[military, frigate]",
                    "0.0",
                    "0.3",
                    "1.0",
                    true,
                    subordinates
                ) );

        parms = new HashMap<>();
        parms.put("formation", "formationshape.invpointguard");
        parms.put("overrideformationskill", "true");
        
        subordinates = new ArrayList<>();

        jobs.add(
                setMilJob(
                    faction,
                    faction.getFactionAbbreviation()+"_destroyer_escort_l",
                    "Escort Destroyer",
                    true,
                    false,
                    "Escort",
                    true,
                    parms,
                    "[military, destroyer]",
                    "ship_l",
                    -1,
                    -1,
                    -1,
                    1,
                    "galaxy",
                    galaxyMacro,
                    "self",
                    "exact",
                    false,
                    true,
                    "[military, destroyer]",
                    "0.0",
                    "0.3",
                    "1.0",
                    true,
                    subordinates
                ) );

        parms = new HashMap<>();
        parms.put("formation", "formationshape.pointguard");
        parms.put("overrideformationskill", "true");
        
        subordinates = new ArrayList<>();

        jobs.add(
                setMilJob(
                    faction,
                    faction.getFactionAbbreviation()+"_frigate_escort_m",
                    "Escort Frigate",
                    true,
                    false,
                    "Escort",
                    true,
                    parms,
                    "[military, frigate]",
                    "ship_m",
                    -1,
                    -1,
                    -1,
                    1,
                    "galaxy",
                    galaxyMacro,
                    "self",
                    "exact",
                    false,
                    true,
                    "[military, frigate]",
                    "0.0",
                    "0.3",
                    "1.0",
                    true,
                    subordinates
                ) );
        
        parms = new HashMap<>();
        parms.put("formation", "formationshape.triangle");
        parms.put("overrideformationskill", "true");
        
        subordinates = new ArrayList<>();

        jobs.add(
                setMilJob(
                    faction,
                    faction.getFactionAbbreviation()+"_fighter_escort_s_carrier",
                    "Carrier Fighter",
                    true,
                    false,
                    "Escort",
                    true,
                    parms,
                    "[military, fighter]",
                    "ship_s",
                    -1,
                    -1,
                    -1,
                    10,
                    "galaxy",
                    galaxyMacro,
                    "self",
                    "exact",
                    false,
                    true,
                    "[military, fighter]",
                    "0.0",
                    "0.3",
                    "1.0",
                    true,
                    subordinates
                ) );

            parms = new HashMap<>();
            parms.put("formation", "formationshape.triangle");
            parms.put("overrideformationskill", "true");
            
            subordinates = new ArrayList<>();
    
            jobs.add(
                    setMilJob(
                        faction,
                        faction.getFactionAbbreviation()+"_heavyfighter_escort_s_carrier",
                        "Carrier Fighter",
                        true,
                        false,
                        "Escort",
                        true,
                        parms,
                        "[military, fighter, heavy]",
                        "ship_s",
                        -1,
                        -1,
                        -1,
                        10,
                        "galaxy",
                        galaxyMacro,
                        "self",
                        "exact",
                        false,
                        true,
                        "[military, fighter]",
                        "0.0",
                        "0.3",
                        "1.0",
                        true,
                        subordinates
                    ) );

        parms = new HashMap<>();
        parms.put("formation", "formationshape.triangle");
        parms.put("overrideformationskill", "true");
        
        subordinates = new ArrayList<>();

        jobs.add(
                setMilJob(
                    faction,
                    faction.getFactionAbbreviation()+"_fighter_escort_s_sector",
                    "Escort Fighter",
                    true,
                    false,
                    "Escort",
                    true,
                    parms,
                    "[military, fighter]",
                    "ship_s",
                    -1,
                    -1,
                    -1,
                    5,
                    "galaxy",
                    galaxyMacro,
                    "self",
                    "exact",
                    false,
                    true,
                    "[military, fighter]",
                    "0.0",
                    "0.3",
                    "1.0",
                    true,
                    subordinates
                ) );

        parms = new HashMap<>();   
        subordinates = new ArrayList<>();
        jobs.add(
                setMilJob(
                    faction,
                    faction.getFactionAbbreviation()+"_resupplier_escort_xl",
                    "Resupply",
                    true,
                    false,
                    "SupplyFleet",
                    true,
                    parms,
                    "[resupplier]",
                    "ship_xl",
                    -1,
                    -1,
                    -1,
                    1,
                    "galaxy",
                    galaxyMacro,
                    "self",
                    "exact",
                    false,
                    true,
                    "[resupplier]",
                    "0.0",
                    "0.3",
                    "1.0",
                    true,
                    subordinates
                ) );


        return jobs;
    }

    private List<JsonJob> addTeladiMil(Faction faction, int clusterCount, String galaxyMacro) {
        ArrayList<JsonJob> jobs = new ArrayList<>();

        HashMap<String, String> parms = new HashMap<>();
        parms.put("range", "class.cluster");

        ArrayList<String> subordinates = new ArrayList<>();
        subordinates.add( faction.getFactionAbbreviation()+"_destroyer_escort_l" );
        subordinates.add( faction.getFactionAbbreviation()+"_frigate_escort_m" );
        subordinates.add( faction.getFactionAbbreviation()+"_frigate_escort_m" );
        subordinates.add( faction.getFactionAbbreviation()+"_frigate_escort_m" );
        subordinates.add( faction.getFactionAbbreviation()+"_frigate_escort_m" );
        subordinates.add( faction.getFactionAbbreviation()+"_heavyfighter_escort_s_carrier" );
        subordinates.add( faction.getFactionAbbreviation()+"_fighter_escort_s_carrier" );
        subordinates.add( faction.getFactionAbbreviation()+"_fighter_escort_s_carrier" );
        subordinates.add( faction.getFactionAbbreviation()+"_fighter_escort_s_carrier" );
        subordinates.add( faction.getFactionAbbreviation()+"_fighter_escort_s_carrier" );
        subordinates.add( faction.getFactionAbbreviation()+"_resupplier_escort_xl" );

        jobs.add(
            setMilJob(
                faction,
                faction.getFactionAbbreviation()+"_carrier_patrol_xl_sector",
                "Patrol Carrier",
                false,
                true,
                "Patrol",
                true,
                parms,
                "[factionlogic, military, carrier]",
                "ship_xl",
                clusterCount / 2,
                -1,
                2,
                -1,
                "galaxy",
                galaxyMacro,
                "self",
                "exact",
                false,
                true,
                "[military, carrier]",
                "0.0",
                "0.3",
                "1.0",
                true,
                subordinates
            ) );

            parms = new HashMap<>();
            parms.put("range", "class.cluster");
    
            subordinates = new ArrayList<>();
            subordinates.add( faction.getFactionAbbreviation()+"_frigate_escort_m" );
            subordinates.add( faction.getFactionAbbreviation()+"_frigate_escort_m" );
            subordinates.add( faction.getFactionAbbreviation()+"_fighter_escort_s_sector" );
            subordinates.add( faction.getFactionAbbreviation()+"_fighter_escort_s_sector" );
            subordinates.add( faction.getFactionAbbreviation()+"_fighter_escort_s_sector" );
    
            jobs.add(
                    setMilJob(
                        faction,
                        faction.getFactionAbbreviation()+"_destroyer_patrol_l_sector",
                        "Patrol Destroyer",
                        false,
                        true,
                        "Patrol",
                        true,
                        parms,
                        "[factionlogic, military, destroyer]",
                        "ship_l",
                        -1,
                        clusterCount,
                        2,
                        -1,
                        "galaxy",
                        galaxyMacro,
                        "self",
                        "exact",
                        false,
                        true,
                        "[military, destroyer]",
                        "0.0",
                        "0.3",
                        "1.0",
                        true,
                        subordinates
                    ) );

        parms = new HashMap<>();
        parms.put("range", "class.cluster");

        subordinates = new ArrayList<>();
        subordinates.add( faction.getFactionAbbreviation()+"_fighter_escort_s_sector" );
        subordinates.add( faction.getFactionAbbreviation()+"_fighter_escort_s_sector" );
        subordinates.add( faction.getFactionAbbreviation()+"_fighter_escort_s_sector" );

        jobs.add(
                setMilJob(
                    faction,
                    faction.getFactionAbbreviation()+"_frigate_patrol_m_sector",
                    "Patrol Frigate",
                    false,
                    true,
                    "Patrol",
                    true,
                    parms,
                    "[factionlogic, military, frigate]",
                    "ship_m",
                    -1,
                    2*clusterCount,
                    2,
                    -1,
                    "galaxy",
                    galaxyMacro,
                    "self",
                    "exact",
                    false,
                    true,
                    "[military, frigate]",
                    "0.0",
                    "0.3",
                    "1.0",
                    true,
                    subordinates
                ) );

        parms = new HashMap<>();
        parms.put("range", "class.cluster");

        subordinates = new ArrayList<>();
        subordinates.add( Faction.MINISTRY.getFactionAbbreviation()+"_destroyer_escort_l" );
        subordinates.add( Faction.MINISTRY.getFactionAbbreviation()+"_frigate_escort_m" );
        subordinates.add( Faction.MINISTRY.getFactionAbbreviation()+"_frigate_escort_m" );
        subordinates.add( Faction.MINISTRY.getFactionAbbreviation()+"_frigate_escort_m" );
        subordinates.add( Faction.MINISTRY.getFactionAbbreviation()+"_frigate_escort_m" );
        subordinates.add( Faction.MINISTRY.getFactionAbbreviation()+"_fighter_escort_s_carrier" );
        subordinates.add( Faction.MINISTRY.getFactionAbbreviation()+"_fighter_escort_s_carrier" );
        subordinates.add( Faction.MINISTRY.getFactionAbbreviation()+"_fighter_escort_s_carrier" );
        subordinates.add( Faction.MINISTRY.getFactionAbbreviation()+"_fighter_escort_s_carrier" );
        subordinates.add( Faction.MINISTRY.getFactionAbbreviation()+"_fighter_escort_s_carrier" );
        subordinates.add( Faction.MINISTRY.getFactionAbbreviation()+"_resupplier_escort_xl" );

        jobs.add(
            setMilJob(
                Faction.MINISTRY,
                Faction.MINISTRY.getFactionAbbreviation()+"_carrier_patrol_xl_sector",
                "Patrol Carrier",
                false,
                true,
                "Patrol",
                true,
                parms,
                "[factionlogic, military, carrier]",
                "ship_xl",
                1,
                1,
                -1,
                -1,
                "galaxy",
                galaxyMacro,
                "self",
                "exact",
                false,
                true,
                "[military, carrier]",
                "0.0",
                "0.3",
                "1.0",
                true,
                subordinates
            ) );

        parms = new HashMap<>();
        parms.put("formation", "formationshape.invpointguard");
        parms.put("overrideformationskill", "true");
        
        subordinates = new ArrayList<>();

        jobs.add(
                setMilJob(
                    faction,
                    faction.getFactionAbbreviation()+"_destroyer_escort_l",
                    "Escort Destroyer",
                    true,
                    false,
                    "Escort",
                    true,
                    parms,
                    "[military, destroyer]",
                    "ship_l",
                    -1,
                    -1,
                    -1,
                    1,
                    "galaxy",
                    galaxyMacro,
                    "self",
                    "exact",
                    false,
                    true,
                    "[military, destroyer]",
                    "0.0",
                    "0.3",
                    "1.0",
                    true,
                    subordinates
                ) );

        parms = new HashMap<>();
        parms.put("formation", "formationshape.invpointguard");
        parms.put("overrideformationskill", "true");
        
        subordinates = new ArrayList<>();

        jobs.add(
                setMilJob(
                    Faction.MINISTRY,
                    Faction.MINISTRY.getFactionAbbreviation()+"_destroyer_escort_l",
                    "Escort Destroyer",
                    true,
                    false,
                    "Escort",
                    true,
                    parms,
                    "[military, destroyer]",
                    "ship_l",
                    -1,
                    -1,
                    -1,
                    1,
                    "galaxy",
                    galaxyMacro,
                    "self",
                    "exact",
                    false,
                    true,
                    "[military, destroyer]",
                    "0.0",
                    "0.3",
                    "1.0",
                    true,
                    subordinates
                ) );

        parms = new HashMap<>();
        parms.put("formation", "formationshape.pointguard");
        parms.put("overrideformationskill", "true");
        
        subordinates = new ArrayList<>();

        jobs.add(
                setMilJob(
                    faction,
                    faction.getFactionAbbreviation()+"_frigate_escort_m",
                    "Escort Frigate",
                    true,
                    false,
                    "Escort",
                    true,
                    parms,
                    "[military, frigate]",
                    "ship_m",
                    -1,
                    -1,
                    -1,
                    1,
                    "galaxy",
                    galaxyMacro,
                    "self",
                    "exact",
                    false,
                    true,
                    "[military, frigate]",
                    "0.0",
                    "0.3",
                    "1.0",
                    true,
                    subordinates
                ) );

        parms = new HashMap<>();
        parms.put("formation", "formationshape.pointguard");
        parms.put("overrideformationskill", "true");
        
        subordinates = new ArrayList<>();

        jobs.add(
                setMilJob(
                    Faction.MINISTRY,
                    Faction.MINISTRY.getFactionAbbreviation()+"_frigate_escort_m",
                    "Escort Frigate",
                    true,
                    false,
                    "Escort",
                    true,
                    parms,
                    "[military, frigate]",
                    "ship_m",
                    -1,
                    -1,
                    -1,
                    1,
                    "galaxy",
                    galaxyMacro,
                    "self",
                    "exact",
                    false,
                    true,
                    "[military, frigate]",
                    "0.0",
                    "0.3",
                    "1.0",
                    true,
                    subordinates
                ) );
        
        parms = new HashMap<>();
        parms.put("formation", "formationshape.triangle");
        parms.put("overrideformationskill", "true");
        
        subordinates = new ArrayList<>();

        jobs.add(
                setMilJob(
                    faction,
                    faction.getFactionAbbreviation()+"_fighter_escort_s_carrier",
                    "Carrier Fighter",
                    true,
                    false,
                    "Escort",
                    true,
                    parms,
                    "[military, fighter]",
                    "ship_s",
                    -1,
                    -1,
                    -1,
                    10,
                    "galaxy",
                    galaxyMacro,
                    "self",
                    "exact",
                    false,
                    true,
                    "[military, fighter]",
                    "0.0",
                    "0.3",
                    "1.0",
                    true,
                    subordinates
                ) );

        parms = new HashMap<>();
        parms.put("formation", "formationshape.triangle");
        parms.put("overrideformationskill", "true");
        
        subordinates = new ArrayList<>();

        jobs.add(
                setMilJob(
                    faction,
                    faction.getFactionAbbreviation()+"_heavyfighter_escort_s_carrier",
                    "Carrier Fighter",
                    true,
                    false,
                    "Escort",
                    true,
                    parms,
                    "[military, fighter, heavy]",
                    "ship_s",
                    -1,
                    -1,
                    -1,
                    10,
                    "galaxy",
                    galaxyMacro,
                    "self",
                    "exact",
                    false,
                    true,
                    "[military, fighter]",
                    "0.0",
                    "0.3",
                    "1.0",
                    true,
                    subordinates
                ) );

        parms = new HashMap<>();
        parms.put("formation", "formationshape.triangle");
        parms.put("overrideformationskill", "true");
        
        subordinates = new ArrayList<>();

        jobs.add(
                setMilJob(
                    Faction.MINISTRY,
                    Faction.MINISTRY.getFactionAbbreviation()+"_fighter_escort_s_carrier",
                    "Carrier Fighter",
                    true,
                    false,
                    "Escort",
                    true,
                    parms,
                    "[military, fighter]",
                    "ship_s",
                    -1,
                    -1,
                    -1,
                    10,
                    "galaxy",
                    galaxyMacro,
                    "self",
                    "exact",
                    false,
                    true,
                    "[military, fighter]",
                    "0.0",
                    "0.3",
                    "1.0",
                    true,
                    subordinates
                ) );

        parms = new HashMap<>();
        parms.put("formation", "formationshape.triangle");
        parms.put("overrideformationskill", "true");
        
        subordinates = new ArrayList<>();

        jobs.add(
                setMilJob(
                    faction,
                    faction.getFactionAbbreviation()+"_fighter_escort_s_sector",
                    "Escort Fighter",
                    true,
                    false,
                    "Escort",
                    true,
                    parms,
                    "[military, fighter]",
                    "ship_s",
                    -1,
                    -1,
                    -1,
                    5,
                    "galaxy",
                    galaxyMacro,
                    "self",
                    "exact",
                    false,
                    true,
                    "[military, fighter]",
                    "0.0",
                    "0.3",
                    "1.0",
                    true,
                    subordinates
                ) );

        parms = new HashMap<>();
        parms.put("formation", "formationshape.triangle");
        parms.put("overrideformationskill", "true");
        
        subordinates = new ArrayList<>();

        jobs.add(
                setMilJob(
                    Faction.MINISTRY,
                    Faction.MINISTRY.getFactionAbbreviation()+"_fighter_escort_s_sector",
                    "Escort Fighter",
                    true,
                    false,
                    "Escort",
                    true,
                    parms,
                    "[military, fighter]",
                    "ship_s",
                    -1,
                    -1,
                    -1,
                    5,
                    "galaxy",
                    galaxyMacro,
                    "self",
                    "exact",
                    false,
                    true,
                    "[military, fighter]",
                    "0.0",
                    "0.3",
                    "1.0",
                    true,
                    subordinates
                ) );

        parms = new HashMap<>();   
        subordinates = new ArrayList<>();
        jobs.add(
                setMilJob(
                    Faction.MINISTRY,
                    Faction.MINISTRY.getFactionAbbreviation()+"_resupplier_escort_xl",
                    "Resupply",
                    true,
                    false,
                    "SupplyFleet",
                    true,
                    parms,
                    "[resupplier]",
                    "ship_xl",
                    -1,
                    -1,
                    -1,
                    1,
                    "galaxy",
                    galaxyMacro,
                    "self",
                    "exact",
                    false,
                    true,
                    "[resupplier]",
                    "0.0",
                    "0.3",
                    "1.0",
                    true,
                    subordinates
                ) );

        parms = new HashMap<>();   
        subordinates = new ArrayList<>();
        jobs.add(
                setMilJob(
                    faction,
                    faction.getFactionAbbreviation()+"_resupplier_escort_xl",
                    "Resupply",
                    true,
                    false,
                    "SupplyFleet",
                    true,
                    parms,
                    "[resupplier]",
                    "ship_xl",
                    -1,
                    -1,
                    -1,
                    1,
                    "galaxy",
                    galaxyMacro,
                    "self",
                    "exact",
                    false,
                    true,
                    "[resupplier]",
                    "0.0",
                    "0.3",
                    "1.0",
                    true,
                    subordinates
                ) );

        return jobs;
    }

    private List<JsonJob> addSplitMil(Faction faction, int clusterCount, String galaxyMacro) {
        ArrayList<JsonJob> jobs = new ArrayList<>();

        HashMap<String, String> parms = new HashMap<>();
        parms.put("range", "class.cluster");

        ArrayList<String> subordinates = new ArrayList<>();
        subordinates.add( faction.getFactionAbbreviation()+"_destroyer_escort_l" );
        subordinates.add( faction.getFactionAbbreviation()+"_destroyer_escort_l" );
        subordinates.add( faction.getFactionAbbreviation()+"_frigate_escort_m" );
        subordinates.add( faction.getFactionAbbreviation()+"_frigate_escort_m" );
        subordinates.add( faction.getFactionAbbreviation()+"_frigate_escort_m" );
        subordinates.add( faction.getFactionAbbreviation()+"_heavyfighter_escort_s_carrier" );
        subordinates.add( faction.getFactionAbbreviation()+"_fighter_escort_s_carrier" );
        subordinates.add( faction.getFactionAbbreviation()+"_fighter_escort_s_carrier" );
        subordinates.add( faction.getFactionAbbreviation()+"_resupplier_escort_xl" );

        jobs.add(
            setMilJob(
                faction,
                faction.getFactionAbbreviation()+"_carrier_patrol_xl_sector",
                "Patrol Carrier",
                false,
                true,
                "Patrol",
                true,
                parms,
                "[factionlogic, military, carrier]",
                "ship_xl",
                clusterCount / 4,
                -1,
                1,
                -1,
                "galaxy",
                galaxyMacro,
                "self",
                "exact",
                false,
                true,
                "[military, carrier]",
                "0.0",
                "0.3",
                "1.0",
                true,
                subordinates
            ) );

        parms = new HashMap<>();
        parms.put("range", "class.cluster");

        subordinates = new ArrayList<>();
        subordinates.add( faction.getFactionAbbreviation()+"_frigate_escort_m" );
        subordinates.add( faction.getFactionAbbreviation()+"_frigate_escort_m" );
        subordinates.add( faction.getFactionAbbreviation()+"_fighter_escort_s_sector" );

        jobs.add(
                setMilJob(
                    faction,
                    faction.getFactionAbbreviation()+"_destroyer_patrol_l_sector",
                    "Patrol Destroyer",
                    false,
                    true,
                    "Patrol",
                    true,
                    parms,
                    "[factionlogic, military, destroyer]",
                    "ship_l",
                    -1,
                    2*clusterCount,
                    3,
                    -1,
                    "galaxy",
                    galaxyMacro,
                    "self",
                    "exact",
                    false,
                    true,
                    "[military, destroyer]",
                    "0.0",
                    "0.3",
                    "1.0",
                    true,
                    subordinates
                ) );

        parms = new HashMap<>();
        parms.put("range", "class.cluster");

        subordinates = new ArrayList<>();
        subordinates.add( faction.getFactionAbbreviation()+"_fighter_escort_s_sector" );
        subordinates.add( faction.getFactionAbbreviation()+"_fighter_escort_s_sector" );

        jobs.add(
                setMilJob(
                    faction,
                    faction.getFactionAbbreviation()+"_frigate_patrol_m_sector",
                    "Patrol Frigate",
                    false,
                    true,
                    "Patrol",
                    true,
                    parms,
                    "[factionlogic, military, frigate]",
                    "ship_m",
                    -1,
                    2*clusterCount,
                    2,
                    -1,
                    "galaxy",
                    galaxyMacro,
                    "self",
                    "exact",
                    false,
                    true,
                    "[military, frigate]",
                    "0.0",
                    "0.3",
                    "1.0",
                    true,
                    subordinates
                ) );

        parms = new HashMap<>();
        parms.put("formation", "formationshape.invpointguard");
        parms.put("overrideformationskill", "true");
        
        subordinates = new ArrayList<>();

        jobs.add(
                setMilJob(
                    faction,
                    faction.getFactionAbbreviation()+"_destroyer_escort_l",
                    "Escort Destroyer",
                    true,
                    false,
                    "Escort",
                    true,
                    parms,
                    "[military, destroyer]",
                    "ship_l",
                    -1,
                    -1,
                    -1,
                    1,
                    "galaxy",
                    galaxyMacro,
                    "self",
                    "exact",
                    false,
                    true,
                    "[military, destroyer]",
                    "0.0",
                    "0.3",
                    "1.0",
                    true,
                    subordinates
                ) );

        parms = new HashMap<>();
        parms.put("formation", "formationshape.pointguard");
        parms.put("overrideformationskill", "true");
        
        subordinates = new ArrayList<>();

        jobs.add(
                setMilJob(
                    faction,
                    faction.getFactionAbbreviation()+"_frigate_escort_m",
                    "Escort Frigate",
                    true,
                    false,
                    "Escort",
                    true,
                    parms,
                    "[military, frigate]",
                    "ship_m",
                    -1,
                    -1,
                    -1,
                    1,
                    "galaxy",
                    galaxyMacro,
                    "self",
                    "exact",
                    false,
                    true,
                    "[military, frigate]",
                    "0.0",
                    "0.3",
                    "1.0",
                    true,
                    subordinates
                ) );
        
        parms = new HashMap<>();
        parms.put("formation", "formationshape.triangle");
        parms.put("overrideformationskill", "true");
        
        subordinates = new ArrayList<>();

        jobs.add(
                setMilJob(
                    faction,
                    faction.getFactionAbbreviation()+"_fighter_escort_s_carrier",
                    "Carrier Fighter",
                    true,
                    false,
                    "Escort",
                    true,
                    parms,
                    "[military, fighter]",
                    "ship_s",
                    -1,
                    -1,
                    -1,
                    10,
                    "galaxy",
                    galaxyMacro,
                    "self",
                    "exact",
                    false,
                    true,
                    "[military, fighter]",
                    "0.0",
                    "0.3",
                    "1.0",
                    true,
                    subordinates
                ) );

        parms = new HashMap<>();
        parms.put("formation", "formationshape.triangle");
        parms.put("overrideformationskill", "true");
        
        subordinates = new ArrayList<>();

        jobs.add(
                setMilJob(
                    faction,
                    faction.getFactionAbbreviation()+"_heavyfighter_escort_s_carrier",
                    "Carrier Fighter",
                    true,
                    false,
                    "Escort",
                    true,
                    parms,
                    "[military, fighter, heavy]",
                    "ship_s",
                    -1,
                    -1,
                    -1,
                    10,
                    "galaxy",
                    galaxyMacro,
                    "self",
                    "exact",
                    false,
                    true,
                    "[military, fighter]",
                    "0.0",
                    "0.3",
                    "1.0",
                    true,
                    subordinates
                ) );

        parms = new HashMap<>();
        parms.put("formation", "formationshape.triangle");
        parms.put("overrideformationskill", "true");
        
        subordinates = new ArrayList<>();

        jobs.add(
                setMilJob(
                    faction,
                    faction.getFactionAbbreviation()+"_fighter_escort_s_sector",
                    "Escort Fighter",
                    true,
                    false,
                    "Escort",
                    true,
                    parms,
                    "[military, fighter]",
                    "ship_s",
                    -1,
                    -1,
                    -1,
                    5,
                    "galaxy",
                    galaxyMacro,
                    "self",
                    "exact",
                    false,
                    true,
                    "[military, fighter]",
                    "0.0",
                    "0.3",
                    "1.0",
                    true,
                    subordinates
                ) );

        parms = new HashMap<>();   
        subordinates = new ArrayList<>();
        jobs.add(
                setMilJob(
                    faction,
                    faction.getFactionAbbreviation()+"_resupplier_escort_xl",
                    "Resupply",
                    true,
                    false,
                    "SupplyFleet",
                    true,
                    parms,
                    "[resupplier]",
                    "ship_xl",
                    -1,
                    -1,
                    -1,
                    1,
                    "galaxy",
                    galaxyMacro,
                    "self",
                    "exact",
                    false,
                    true,
                    "[resupplier]",
                    "0.0",
                    "0.3",
                    "1.0",
                    true,
                    subordinates
                ) );


        return jobs;
    }

    private List<JsonJob> addTerranMil(Faction faction, int clusterCount, String galaxyMacro) {
        ArrayList<JsonJob> jobs = new ArrayList<>();

        HashMap<String, String> parms = new HashMap<>();
        parms.put("range", "class.cluster");

        ArrayList<String> subordinates = new ArrayList<>();
        subordinates.add( faction.getFactionAbbreviation()+"_destroyer_escort_l" );
        subordinates.add( faction.getFactionAbbreviation()+"_destroyer_escort_l" );
        subordinates.add( faction.getFactionAbbreviation()+"_frigate_escort_m" );
        subordinates.add( faction.getFactionAbbreviation()+"_frigate_escort_m" );
        subordinates.add( faction.getFactionAbbreviation()+"_frigate_escort_m" );
        subordinates.add( faction.getFactionAbbreviation()+"_heavyfighter_escort_s_carrier" );
        subordinates.add( faction.getFactionAbbreviation()+"_fighter_escort_s_carrier" );
        subordinates.add( faction.getFactionAbbreviation()+"_fighter_escort_s_carrier" );
        subordinates.add( faction.getFactionAbbreviation()+"_resupplier_escort_xl" );

        jobs.add(
            setMilJob(
                faction,
                faction.getFactionAbbreviation()+"_carrier_patrol_xl_sector",
                "Patrol Carrier",
                false,
                true,
                "Patrol",
                true,
                parms,
                "[factionlogic, military, carrier]",
                "ship_xl",
                clusterCount / 4,
                -1,
                1,
                -1,
                "galaxy",
                galaxyMacro,
                "self",
                "exact",
                false,
                true,
                "[military, carrier]",
                "0.0",
                "0.3",
                "1.0",
                true,
                subordinates
            ) );

        parms = new HashMap<>();
        parms.put("range", "class.cluster");

        subordinates = new ArrayList<>();
        subordinates.add( faction.getFactionAbbreviation()+"_frigate_escort_m" );
        subordinates.add( faction.getFactionAbbreviation()+"_frigate_escort_m" );
        subordinates.add( faction.getFactionAbbreviation()+"_fighter_escort_s_sector" );

        jobs.add(
                setMilJob(
                    faction,
                    faction.getFactionAbbreviation()+"_destroyer_patrol_l_sector",
                    "Patrol Destroyer",
                    false,
                    true,
                    "Patrol",
                    true,
                    parms,
                    "[factionlogic, military, destroyer]",
                    "ship_l",
                    -1,
                    2*clusterCount,
                    3,
                    -1,
                    "galaxy",
                    galaxyMacro,
                    "self",
                    "exact",
                    false,
                    true,
                    "[military, destroyer]",
                    "0.0",
                    "0.3",
                    "1.0",
                    true,
                    subordinates
                ) );

        parms = new HashMap<>();
        parms.put("range", "class.cluster");

        subordinates = new ArrayList<>();
        subordinates.add( faction.getFactionAbbreviation()+"_fighter_escort_s_sector" );
        subordinates.add( faction.getFactionAbbreviation()+"_fighter_escort_s_sector" );

        jobs.add(
                setMilJob(
                    faction,
                    faction.getFactionAbbreviation()+"_frigate_patrol_m_sector",
                    "Patrol Frigate",
                    false,
                    true,
                    "Patrol",
                    true,
                    parms,
                    "[factionlogic, military, frigate]",
                    "ship_m",
                    -1,
                    2*clusterCount,
                    2,
                    -1,
                    "galaxy",
                    galaxyMacro,
                    "self",
                    "exact",
                    false,
                    true,
                    "[military, frigate]",
                    "0.0",
                    "0.3",
                    "1.0",
                    true,
                    subordinates
                ) );

        parms = new HashMap<>();
        parms.put("formation", "formationshape.invpointguard");
        parms.put("overrideformationskill", "true");
        
        subordinates = new ArrayList<>();

        jobs.add(
                setMilJob(
                    faction,
                    faction.getFactionAbbreviation()+"_destroyer_escort_l",
                    "Escort Destroyer",
                    true,
                    false,
                    "Escort",
                    true,
                    parms,
                    "[military, destroyer]",
                    "ship_l",
                    -1,
                    -1,
                    -1,
                    1,
                    "galaxy",
                    galaxyMacro,
                    "self",
                    "exact",
                    false,
                    true,
                    "[military, destroyer]",
                    "0.0",
                    "0.3",
                    "1.0",
                    true,
                    subordinates
                ) );

        parms = new HashMap<>();
        parms.put("formation", "formationshape.pointguard");
        parms.put("overrideformationskill", "true");
        
        subordinates = new ArrayList<>();

        jobs.add(
                setMilJob(
                    faction,
                    faction.getFactionAbbreviation()+"_frigate_escort_m",
                    "Escort Frigate",
                    true,
                    false,
                    "Escort",
                    true,
                    parms,
                    "[military, frigate]",
                    "ship_m",
                    -1,
                    -1,
                    -1,
                    1,
                    "galaxy",
                    galaxyMacro,
                    "self",
                    "exact",
                    false,
                    true,
                    "[military, frigate]",
                    "0.0",
                    "0.3",
                    "1.0",
                    true,
                    subordinates
                ) );
        
        parms = new HashMap<>();
        parms.put("formation", "formationshape.triangle");
        parms.put("overrideformationskill", "true");
        
        subordinates = new ArrayList<>();

        jobs.add(
                setMilJob(
                    faction,
                    faction.getFactionAbbreviation()+"_fighter_escort_s_carrier",
                    "Carrier Fighter",
                    true,
                    false,
                    "Escort",
                    true,
                    parms,
                    "[military, fighter]",
                    "ship_s",
                    -1,
                    -1,
                    -1,
                    10,
                    "galaxy",
                    galaxyMacro,
                    "self",
                    "exact",
                    false,
                    true,
                    "[military, fighter]",
                    "0.0",
                    "0.3",
                    "1.0",
                    true,
                    subordinates
                ) );

        parms = new HashMap<>();
        parms.put("formation", "formationshape.triangle");
        parms.put("overrideformationskill", "true");
        
        subordinates = new ArrayList<>();

        jobs.add(
                setMilJob(
                    faction,
                    faction.getFactionAbbreviation()+"_heavyfighter_escort_s_carrier",
                    "Carrier Fighter",
                    true,
                    false,
                    "Escort",
                    true,
                    parms,
                    "[military, fighter, heavy]",
                    "ship_s",
                    -1,
                    -1,
                    -1,
                    10,
                    "galaxy",
                    galaxyMacro,
                    "self",
                    "exact",
                    false,
                    true,
                    "[military, fighter]",
                    "0.0",
                    "0.3",
                    "1.0",
                    true,
                    subordinates
                ) );

        parms = new HashMap<>();
        parms.put("formation", "formationshape.triangle");
        parms.put("overrideformationskill", "true");
        
        subordinates = new ArrayList<>();

        jobs.add(
                setMilJob(
                    faction,
                    faction.getFactionAbbreviation()+"_fighter_escort_s_sector",
                    "Escort Fighter",
                    true,
                    false,
                    "Escort",
                    true,
                    parms,
                    "[military, fighter]",
                    "ship_s",
                    -1,
                    -1,
                    -1,
                    5,
                    "galaxy",
                    galaxyMacro,
                    "self",
                    "exact",
                    false,
                    true,
                    "[military, fighter]",
                    "0.0",
                    "0.3",
                    "1.0",
                    true,
                    subordinates
                ) );

        parms = new HashMap<>();   
        subordinates = new ArrayList<>();
        jobs.add(
                setMilJob(
                    faction,
                    faction.getFactionAbbreviation()+"_resupplier_escort_xl",
                    "Resupply",
                    true,
                    false,
                    "SupplyFleet",
                    true,
                    parms,
                    "[resupplier]",
                    "ship_xl",
                    -1,
                    -1,
                    -1,
                    1,
                    "galaxy",
                    galaxyMacro,
                    "self",
                    "exact",
                    false,
                    true,
                    "[resupplier]",
                    "0.0",
                    "0.3",
                    "1.0",
                    true,
                    subordinates
                ) );


        return jobs;
    }

    private List<JsonJob> addXenonMil(Faction faction, int clusterCount, String galaxyMacro) {
        ArrayList<JsonJob> jobs = new ArrayList<>();

        HashMap<String, String> parms = new HashMap<>();
        parms.put("range", "class.cluster");

        ArrayList<String> subordinates = new ArrayList<>();
        subordinates.add( faction.getFactionAbbreviation()+"_destroyer_escort_xl" );
        subordinates.add( faction.getFactionAbbreviation()+"_destroyer_escort_xl" );
        subordinates.add( faction.getFactionAbbreviation()+"_frigate_escort_m" );
        subordinates.add( faction.getFactionAbbreviation()+"_frigate_escort_m" );
        subordinates.add( faction.getFactionAbbreviation()+"_frigate_escort_m" );
        subordinates.add( faction.getFactionAbbreviation()+"_frigate_escort_m" );
        subordinates.add( faction.getFactionAbbreviation()+"_heavyfighter_escort_s_carrier" );
        subordinates.add( faction.getFactionAbbreviation()+"_fighter_escort_s_carrier" );
        subordinates.add( faction.getFactionAbbreviation()+"_fighter_escort_s_carrier" );
        subordinates.add( faction.getFactionAbbreviation()+"_fighter_escort_s_carrier" );

        jobs.add(
            setMilJob(
                faction,
                faction.getFactionAbbreviation()+"_carrier_patrol_xl_sector",
                "Patrol Carrier",
                false,
                true,
                "Patrol",
                true,
                parms,
                "[factionlogic, military, carrier]",
                "ship_xl",
                clusterCount / 4,
                -1,
                1,
                -1,
                "galaxy",
                galaxyMacro,
                "self",
                "exact",
                false,
                true,
                "[military, carrier]",
                "0.0",
                "0.3",
                "1.0",
                true,
                subordinates
            ) );

        parms = new HashMap<>();
        parms.put("range", "class.cluster");

        subordinates = new ArrayList<>();
        subordinates.add( faction.getFactionAbbreviation()+"_frigate_escort_m" );
        subordinates.add( faction.getFactionAbbreviation()+"_frigate_escort_m" );
        subordinates.add( faction.getFactionAbbreviation()+"_fighter_escort_s_sector" );

        jobs.add(
                setMilJob(
                    faction,
                    faction.getFactionAbbreviation()+"_destroyer_patrol_xl_sector",
                    "Patrol Destroyer",
                    false,
                    true,
                    "Patrol",
                    true,
                    parms,
                    "[factionlogic, military, destroyer]",
                    "ship_xl",
                    -1,
                    2*clusterCount,
                    2,
                    -1,
                    "galaxy",
                    galaxyMacro,
                    "self",
                    "exact",
                    false,
                    true,
                    "[military, destroyer]",
                    "0.0",
                    "0.3",
                    "1.0",
                    true,
                    subordinates
                ) );

        parms = new HashMap<>();
        parms.put("range", "class.cluster");

        subordinates = new ArrayList<>();
        subordinates.add( faction.getFactionAbbreviation()+"_fighter_escort_s_sector" );
        subordinates.add( faction.getFactionAbbreviation()+"_fighter_escort_s_sector" );

        jobs.add(
                setMilJob(
                    faction,
                    faction.getFactionAbbreviation()+"_frigate_patrol_m_sector",
                    "Patrol Frigate",
                    false,
                    true,
                    "Patrol",
                    true,
                    parms,
                    "[factionlogic, military, frigate]",
                    "ship_m",
                    -1,
                    2*clusterCount,
                    4,
                    -1,
                    "galaxy",
                    galaxyMacro,
                    "self",
                    "exact",
                    false,
                    true,
                    "[military, frigate]",
                    "0.0",
                    "0.3",
                    "1.0",
                    true,
                    subordinates
                ) );

        parms = new HashMap<>();
        parms.put("formation", "formationshape.invpointguard");
        parms.put("overrideformationskill", "true");
        
        subordinates = new ArrayList<>();

        jobs.add(
                setMilJob(
                    faction,
                    faction.getFactionAbbreviation()+"_destroyer_escort_xl",
                    "Escort Destroyer",
                    true,
                    false,
                    "Escort",
                    true,
                    parms,
                    "[military, destroyer]",
                    "ship_xl",
                    -1,
                    -1,
                    -1,
                    1,
                    "galaxy",
                    galaxyMacro,
                    "self",
                    "exact",
                    false,
                    true,
                    "[military, destroyer]",
                    "0.0",
                    "0.3",
                    "1.0",
                    true,
                    subordinates
                ) );

        parms = new HashMap<>();
        parms.put("formation", "formationshape.pointguard");
        parms.put("overrideformationskill", "true");
        
        subordinates = new ArrayList<>();

        jobs.add(
                setMilJob(
                    faction,
                    faction.getFactionAbbreviation()+"_frigate_escort_m",
                    "Escort Frigate",
                    true,
                    false,
                    "Escort",
                    true,
                    parms,
                    "[military, frigate]",
                    "ship_m",
                    -1,
                    -1,
                    -1,
                    1,
                    "galaxy",
                    galaxyMacro,
                    "self",
                    "exact",
                    false,
                    true,
                    "[military, frigate]",
                    "0.0",
                    "0.3",
                    "1.0",
                    true,
                    subordinates
                ) );
        
        parms = new HashMap<>();
        parms.put("formation", "formationshape.triangle");
        parms.put("overrideformationskill", "true");
        
        subordinates = new ArrayList<>();

        jobs.add(
                setMilJob(
                    faction,
                    faction.getFactionAbbreviation()+"_fighter_escort_s_carrier",
                    "Carrier Fighter",
                    true,
                    false,
                    "Escort",
                    true,
                    parms,
                    "[military, fighter]",
                    "ship_s",
                    -1,
                    -1,
                    -1,
                    10,
                    "galaxy",
                    galaxyMacro,
                    "self",
                    "exact",
                    false,
                    true,
                    "[military, fighter]",
                    "0.0",
                    "0.3",
                    "1.0",
                    true,
                    subordinates
                ) );

            parms = new HashMap<>();
            parms.put("formation", "formationshape.triangle");
            parms.put("overrideformationskill", "true");
            
            subordinates = new ArrayList<>();
    
            jobs.add(
                    setMilJob(
                        faction,
                        faction.getFactionAbbreviation()+"_heavyfighter_escort_s_carrier",
                        "Carrier Fighter",
                        true,
                        false,
                        "Escort",
                        true,
                        parms,
                        "[military, fighter, heavy]",
                        "ship_s",
                        -1,
                        -1,
                        -1,
                        10,
                        "galaxy",
                        galaxyMacro,
                        "self",
                        "exact",
                        false,
                        true,
                        "[military, fighter]",
                        "0.0",
                        "0.3",
                        "1.0",
                        true,
                        subordinates
                    ) );

        parms = new HashMap<>();
        parms.put("formation", "formationshape.triangle");
        parms.put("overrideformationskill", "true");
        
        subordinates = new ArrayList<>();

        jobs.add(
                setMilJob(
                    faction,
                    faction.getFactionAbbreviation()+"_fighter_escort_s_sector",
                    "Escort Fighter",
                    true,
                    false,
                    "Escort",
                    true,
                    parms,
                    "[military, fighter]",
                    "ship_s",
                    -1,
                    -1,
                    -1,
                    5,
                    "galaxy",
                    galaxyMacro,
                    "self",
                    "exact",
                    false,
                    true,
                    "[military, fighter]",
                    "0.0",
                    "0.3",
                    "1.0",
                    true,
                    subordinates
                ) );

        return jobs;
    }
}
