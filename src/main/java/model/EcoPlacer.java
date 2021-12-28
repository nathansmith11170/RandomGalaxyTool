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

    private JsonJob setEcoJob(Faction faction, String id, String name, int quota, String locationMacro, int clusterCount) {
        Map<String, String> basketMap = new HashMap<>();
        // Universal (NOT TER)
        basketMap.put("_bio_trader_l", "bio");
        basketMap.put("_bio_trader_m", "bio");
        basketMap.put("_food_trader_s", "food");
        basketMap.put("_medical_trader_s", "medicalsupplies");
        basketMap.put("_energycell_trader_l", "energycells");
        basketMap.put("_longrange_energycell_trader_l", "energycells");
        basketMap.put("_longrange_energycell_trader_m", "energycells");
        basketMap.put("_energycell_trader_m", "energycells");
        basketMap.put("_energycell_trader_s", "energycells");
        basketMap.put("_equipment_trader_l", "equipment");
        basketMap.put("_equipment_trader_m", "equipment");
        basketMap.put("_construction_trader_l", "construction_stations");
        basketMap.put("_construction_trader_m", "construction_stations");
        basketMap.put("_ship_construction_trader_l", "construction_ships");
        basketMap.put("_ship_construction_trader_m", "construction_ships");
        basketMap.put("_refinedgas_trader_l", "refined_gas");
        basketMap.put("_refinedgas_trader_m", "refined_gas");
        basketMap.put("_refinedmineral_trader_l", "refined_mineral");
        basketMap.put("_refinedmineral_trader_m", "refined_mineral");
        basketMap.put("_tech_trader_l", "tech");
        basketMap.put("_tech_trader_m", "tech");
        basketMap.put("_tech_trader_s", "tech");
        basketMap.put("_water_trader_l", "water");
        basketMap.put("_water_trader_m", "water");
        basketMap.put("_stationtrader_m", "all_container");
        basketMap.put("_stationtrader_l", "all_container");
        basketMap.put("_liquid_free_miner_l", "gases");
        basketMap.put("_liquid_free_miner_m", "gases");
        basketMap.put("_mineral_free_miner_l", "minerals");
        basketMap.put("_mineral_free_miner_m", "minerals");
        basketMap.put("_mineral_free_miner_s", "minerals");
        basketMap.put("_ice_free_miner_l", "ice");
        basketMap.put("_ice_free_miner_m", "ice");

        // TERRAN
        basketMap.put("_food_trader_l", "food");
        basketMap.put("_food_trader_m", "food");
        ArrayList<String> terranSpecificBaskets = new ArrayList<>();
        terranSpecificBaskets.add("construction_stations");
        terranSpecificBaskets.add("construction_ships");
        terranSpecificBaskets.add("refined_gas");
        terranSpecificBaskets.add("refined_mineral");
        terranSpecificBaskets.add("tech");
        terranSpecificBaskets.add("construction_ships");
        terranSpecificBaskets.add("food");
        terranSpecificBaskets.add("all_container");

        JsonJob result = new JsonJob();
        result.setId(faction.getFactionAbbreviation()+id);
        result.setName(name);

        // Standard freighter
        if (id.contains("trader") && !id.contains("stationtrader")) {
            JsonModifiers mods = new JsonModifiers();
            mods.setCommandeerable(true);
            if(faction.getRace().equals(Race.XENON)) {
                mods.setRebuild(true);
            } else {
                mods.setRebuild(false);
            }
            result.setModifiers(mods);

            JsonOrder ord = new JsonOrder();
            ord.setDefaultOrder(true);
            if(id.substring(0,9).equals("_longrange")){
                ord.setOrder("TradeRoutine");
                HashMap<String, String> parms = new HashMap<>();
                parms.put("minbuy", "0");
                parms.put("maxbuy", "4");
                parms.put("minsell", "5");
                parms.put("maxsell", "8");
                ord.setParams(parms);
            }else {
                ord.setOrder("TradeRoutine");
                HashMap<String, String> parms = new HashMap<>();
                parms.put("minbuy", "0");
                parms.put("maxbuy", "8");
                parms.put("minsell", "0");
                parms.put("maxsell", "5");
                ord.setParams(parms);
            }
            result.setOrder(ord);

            if(faction.getRace().equals(Race.TERRAN) && terranSpecificBaskets.contains(basketMap.get(id))){
                result.setBasket( basketMap.get(id)+"_terran" );
            } else {
                result.setBasket( basketMap.get(id) );
            }

            JsonCategory cat = new JsonCategory();
            cat.setFaction( faction.getName() );
            cat.setTags("[factionlogic, freighter, trader]");
            if(id.endsWith("s")){
                cat.setSize("ship_s");
            }
            if(id.endsWith("m")) {
                cat.setSize("ship_m");
            }
            if(id.endsWith("l")) {
                cat.setSize("ship_l");
            }
            result.setCategory(cat);

            JsonQuota quotaTag = new JsonQuota();
            quotaTag.setCluster(quota);
            quotaTag.setGalaxy(quota * clusterCount);
            result.setQuota(quotaTag);

            JsonLocation loc = new JsonLocation();
            loc.setClazz("galaxy");
            loc.setFaction(faction.getName());
            loc.setRelation("self");
            loc.setComparison("ge");
            result.setLocation(loc);

            JsonEnvironment env = new JsonEnvironment();
            env.setBuildatshipyard(true);
            result.setEnvironment(env);

            JsonShip ship = new JsonShip();
                JsonSelect sel = new JsonSelect();
                sel.setFaction(faction.getName());
                if(id.endsWith("s")){
                    sel.setSize("ship_s");
                }
                if(id.endsWith("m")) {
                    sel.setSize("ship_m");
                }
                if(id.endsWith("l")) {
                    sel.setSize("ship_l");
                }
                if(faction.getRace().equals(Race.XENON)) {
                    sel.setTags("[miner, solid]");
                } else {
                    sel.setTags("[trader, container]");
                }
            ship.setSelect(sel);
                JsonLoadout load = new JsonLoadout();
                    JsonLevel level = new JsonLevel();
                    level.setMin(.3);
                    level.setMax(.6);
                load.setLevel(level);
            ship.setLoadout(load);
                JsonOwner owner = new JsonOwner();
                owner.setExact(faction.getName());
                owner.setOverridenpc(true);
            ship.setOwner(owner);
            result.setShip(ship);
        } else if (id.contains("miner")) {
            JsonModifiers mods = new JsonModifiers();
            mods.setCommandeerable(true);
            if(faction.getRace().equals(Race.XENON)) {
                mods.setRebuild(true);
            } else {
                mods.setRebuild(false);
            }
            result.setModifiers(mods);

            JsonOrder ord = new JsonOrder();
            ord.setDefaultOrder(true);
            ord.setOrder("MiningRoutine");
            HashMap<String, String> parms = new HashMap<>();
            parms.put("minbuy", "0");
            parms.put("maxbuy", "4");
            parms.put("minsell", "0");
            parms.put("maxsell", "4");
            ord.setParams(parms);
            result.setOrder(ord);

            if(faction.getRace().equals(Race.XENON)) {
                result.setBasket( basketMap.get(id)+"_xenon" );
            } else {
                result.setBasket( basketMap.get(id) );
            }


            JsonCategory cat = new JsonCategory();
            cat.setFaction( faction.getName() );
            if(faction.getRace().equals(Race.XENON)) {
                cat.setTags("[factionlogic, miner]");
            } else {
                cat.setTags("[factionlogic, freighter, miner]");
            }
            if(id.endsWith("s")){
                cat.setSize("ship_s");
            }
            if(id.endsWith("m")) {
                cat.setSize("ship_m");
            }
            if(id.endsWith("l")) {
                cat.setSize("ship_l");
            }
            result.setCategory(cat);

            JsonQuota quotaTag = new JsonQuota();
            quotaTag.setCluster(quota);
            quotaTag.setGalaxy(quota * clusterCount);
            result.setQuota(quotaTag);

            JsonLocation loc = new JsonLocation();
            loc.setClazz("galaxy");
            loc.setFaction(faction.getName());
            loc.setRelation("self");
            loc.setComparison("ge");
            if(id.contains("liquid")) {
                loc.setRegionBasket("gases");
            }
            if(id.contains("mineral")) {
                loc.setRegionBasket("minerals");
            }
            if(id.contains("mineral") && faction.getRace().equals(Race.XENON)) {
                loc.setRegionBasket("minerals_xenon");
            }
            if(id.contains("ice")) {
                loc.setRegionBasket("ice");
            }
            result.setLocation(loc);

            JsonEnvironment env = new JsonEnvironment();
            env.setBuildatshipyard(true);
            result.setEnvironment(env);

            JsonShip ship = new JsonShip();
                JsonSelect sel = new JsonSelect();
                sel.setFaction(faction.getName());
                if(id.endsWith("s")){
                    sel.setSize("ship_s");
                }
                if(id.endsWith("m")) {
                    sel.setSize("ship_m");
                }
                if(id.endsWith("l")) {
                    sel.setSize("ship_l");
                }
                if(id.contains("liquid")) {
                    sel.setTags("[miner, liquid]");
                }
                if(id.contains("mineral") || id.contains("ice")) {
                    sel.setTags("[miner, solid]");
                }
            ship.setSelect(sel);
                JsonLoadout load = new JsonLoadout();
                    JsonLevel level = new JsonLevel();
                    level.setMin(.3);
                    level.setMax(.6);
                load.setLevel(level);
            ship.setLoadout(load);
                JsonOwner owner = new JsonOwner();
                owner.setExact(faction.getName());
                owner.setOverridenpc(true);
            ship.setOwner(owner);
                JsonCargo carg = new JsonCargo();
                    JsonValueType fillP = new JsonValueType();
                    fillP.setMin("0");
                    fillP.setMax("100");
                    fillP.setProfile("inversebell");
                carg.setFillPercent(fillP);
            ship.setCargo(carg);
            result.setShip(ship);
        } else if (id.contains("stationtrader")) {
            JsonModifiers mods = new JsonModifiers();
            mods.setCommandeerable(false);
            mods.setRebuild(false);
            result.setModifiers(mods);

            JsonOrder ord = new JsonOrder();
            ord.setDefaultOrder(true);
            ord.setOrder("TradeRoutine");
            HashMap<String, String> parms = new HashMap<>();
            parms.put("maxbuy", "5");
            ord.setParams(parms);
            result.setOrder(ord);

            if(faction.getRace().equals(Race.TERRAN) && terranSpecificBaskets.contains(basketMap.get(id))){
                result.setBasket( basketMap.get(id)+"_terran" );
            } else {
                result.setBasket( basketMap.get(id) );
            }

            JsonCategory cat = new JsonCategory();
            cat.setFaction( faction.getName() );
            cat.setTags("[stationtrader]");
            if(id.endsWith("s")){
                cat.setSize("ship_s");
            }
            if(id.endsWith("m")) {
                cat.setSize("ship_m");
            }
            if(id.endsWith("l")) {
                cat.setSize("ship_l");
            }
            result.setCategory(cat);

            JsonQuota quotaTag = new JsonQuota();
            quotaTag.setMaxgalaxy(quota);
            result.setQuota(quotaTag);

            JsonLocation loc = new JsonLocation();
            loc.setClazz("galaxy");
            loc.setFaction(faction.getName());
            loc.setRelation("self");
            loc.setComparison("ge");
            result.setLocation(loc);

            JsonEnvironment env = new JsonEnvironment();
            env.setBuildatshipyard(true);
            result.setEnvironment(env);

            JsonShip ship = new JsonShip();
                JsonSelect sel = new JsonSelect();
                sel.setFaction(faction.getName());
                if(id.endsWith("s")){
                    sel.setSize("ship_s");
                }
                if(id.endsWith("m")) {
                    sel.setSize("ship_m");
                }
                if(id.endsWith("l")) {
                    sel.setSize("ship_l");
                }
                sel.setTags("[trader, container]");
            ship.setSelect(sel);
            JsonLoadout load = new JsonLoadout();
                JsonLevel level = new JsonLevel();
                level.setMin(.3);
                level.setMax(.6);
                load.setLevel(level);
            ship.setLoadout(load);
            JsonOwner owner = new JsonOwner();
            owner.setExact(faction.getName());
            owner.setOverridenpc(true);
            ship.setOwner(owner);
            result.setShip(ship);
        } else if (id.contains("construction_vessel")) {
            JsonModifiers mods = new JsonModifiers();
            mods.setCommandeerable(true);
            mods.setRebuild(true);
            result.setModifiers(mods);

            JsonOrder ord = new JsonOrder();
            ord.setDefaultOrder(true);
            ord.setOrder("FindBuildTasks");
            HashMap<String, String> parms = new HashMap<>();
            parms.put("maxjumps", "6");
            ord.setParams(parms);
            result.setOrder(ord);

            if(faction.getRace().equals(Race.TERRAN) && terranSpecificBaskets.contains(basketMap.get(id))){
                result.setBasket( basketMap.get(id)+"_terran" );
            } else {
                result.setBasket( basketMap.get(id) );
            }

            JsonCategory cat = new JsonCategory();
            cat.setFaction( faction.getName() );
            cat.setTags("[factionlogic, builder]");
            cat.setSize("ship_xl");
            result.setCategory(cat);

            JsonQuota quotaTag = new JsonQuota();
            quotaTag.setGalaxy(quota);
            result.setQuota(quotaTag);

            JsonLocation loc = new JsonLocation();
            loc.setClazz("galaxy");
            loc.setFaction(faction.getName());
            loc.setRelation("self");
            loc.setComparison("ge");
            result.setLocation(loc);

            JsonEnvironment env = new JsonEnvironment();
            env.setBuildatshipyard(true);
            result.setEnvironment(env);

            JsonShip ship = new JsonShip();
                JsonSelect sel = new JsonSelect();
                sel.setFaction(faction.getName());
                sel.setSize("ship_xl");
                sel.setTags("[builder]");
            ship.setSelect(sel);
                JsonLoadout load = new JsonLoadout();
                    JsonLevel level = new JsonLevel();
                    level.setMin(.3);
                    level.setMax(.6);
                load.setLevel(level);
            ship.setLoadout(load);
                JsonOwner owner = new JsonOwner();
                owner.setExact(faction.getName());
                owner.setOverridenpc(true);
            ship.setOwner(owner);
            result.setShip(ship);
        } else if (id.contains("police")) {
            JsonModifiers mods = new JsonModifiers();
            mods.setCommandeerable(false);
            result.setModifiers(mods);

            JsonOrder ord = new JsonOrder();
            ord.setDefaultOrder(true);
            ord.setOrder("Police");
            result.setOrder(ord);

            if(faction.getRace().equals(Race.TERRAN) && terranSpecificBaskets.contains(basketMap.get(id))){
                result.setBasket( basketMap.get(id)+"_terran" );
            } else {
                result.setBasket( basketMap.get(id) );
            }

            JsonCategory cat = new JsonCategory();
            cat.setFaction( faction.getName() );
            cat.setTags("[police]");
            result.setCategory(cat);

            JsonQuota quotaTag = new JsonQuota();
            quotaTag.setCluster(quota);
            quotaTag.setMaxgalaxy(quota * clusterCount);
            result.setQuota(quotaTag);

            JsonLocation loc = new JsonLocation();
            loc.setClazz("galaxy");
            loc.setMacro(locationMacro);
            loc.setFaction(faction.getName());
            loc.setRelation("self");
            loc.setComparison("ge");
            result.setLocation(loc);

            JsonEnvironment env = new JsonEnvironment();
            env.setBuildatshipyard(true);
            result.setEnvironment(env);

            JsonShip ship = new JsonShip();
                JsonSelect sel = new JsonSelect();
                sel.setFaction(faction.getName());
                sel.setTags("[police]");
            ship.setSelect(sel);
                JsonLoadout load = new JsonLoadout();
                    JsonLevel level = new JsonLevel();
                    level.setMin(.3);
                    level.setMax(.6);
                load.setLevel(level);
            ship.setLoadout(load);
            JsonOwner owner = new JsonOwner();
            owner.setExact(faction.getName());
            owner.setOverridenpc(true);
            ship.setOwner(owner);
            result.setShip(ship);
        }

        return result;
    }

    public List<JsonJob> addEcoJobs(Faction faction, int clusterCount,
            String locationMacro) {
        List<JsonJob> jobs = new ArrayList<JsonJob>();

        Map<String, Integer> quotaMap = new HashMap<>();
        Map<String, String> nameMap = new HashMap<>();

        // Universal (NOT TER)
        quotaMap.put("_bio_trader_l", 0);
        nameMap.put("_bio_trader_l", "Bio Transporter");

        quotaMap.put("_bio_trader_m", 0);
        nameMap.put("_bio_trader_m", "Bio Transporter");

        quotaMap.put("_food_trader_s", 0);
        nameMap.put("_food_trader_s", "Food Delivery Service");

        quotaMap.put("_medical_trader_s", 0);
        nameMap.put("_medical_trader_s", "Pharmaceutical Supplier");

        quotaMap.put("_energycell_trader_l", 0);
        nameMap.put("_energycell_trader_l", "Energy Transporter");

        quotaMap.put("_longrange_energycell_trader_l", 0);
        nameMap.put("_longrange_energycell_trader_l", "Energy Transporter");

        quotaMap.put("_longrange_energycell_trader_m", 0);
        nameMap.put("_longrange_energycell_trader_m", "Energy Transporter");

        quotaMap.put("_energycell_trader_m", 0);
        nameMap.put("_energycell_trader_m", "Energy Transporter");

        quotaMap.put("_energycell_trader_s", 0);
        nameMap.put("_energycell_trader_s", "Energy Transporter");

        quotaMap.put("_equipment_trader_l", 0);
        nameMap.put("_equipment_trader_l", "Eequipment Transporter");

        quotaMap.put("_equipment_trader_m", 0);
        nameMap.put("_equipment_trader_m", "Equipment Transporter");

        quotaMap.put("_construction_trader_l", 0);
        nameMap.put("_construction_trader_l", "Construction Material Transporter");

        quotaMap.put("_construction_trader_m", 0);
        nameMap.put("_construction_trader_m", "Construction Material Transporter");

        quotaMap.put("_ship_construction_trader_l", 0);
        nameMap.put("_ship_construction_trader_l", "Construction Material Transporter");

        quotaMap.put("_ship_construction_trader_m", 0);
        nameMap.put("_ship_construction_trader_m", "Construction Material Transporter");

        quotaMap.put("_refinedgas_trader_l", 0);
        nameMap.put("_refinedgas_trader_l", "Intermediate Products Transporter");

        quotaMap.put("_refinedgas_trader_m", 0);
        nameMap.put("_refinedgas_trader_m", "Intermediate Products Transporter");

        quotaMap.put("_refinedmineral_trader_l", 0);
        nameMap.put("_refinedmineral_trader_l", "Intermediate Products Transporter");

        quotaMap.put("_refinedmineral_trader_m", 0);
        nameMap.put("_refinedmineral_trader_m", "Intermediate Products Transporter");

        quotaMap.put("_tech_trader_l", 0);
        nameMap.put("_tech_trader_l", "Technology Transporter");

        quotaMap.put("_tech_trader_m", 0);
        nameMap.put("_tech_trader_m", "Technology Transporter");

        quotaMap.put("_tech_trader_s", 0);
        nameMap.put("_tech_trader_s", "Technology Transporter");

        quotaMap.put("_water_trader_l", 0);
        nameMap.put("_water_trader_l", "Water Transporter");

        quotaMap.put("_water_trader_m", 0);
        nameMap.put("_water_trader_m", "Water Transporter");

        quotaMap.put("_stationtrader_m", 0);
        nameMap.put("_stationtrader_m", "Priority Goods Transporter");

        quotaMap.put("_stationtrader_l", 0);
        nameMap.put("_stationtrader_l", "Priority Goods Transporter");

        quotaMap.put("_liquid_free_miner_l", 0);
        nameMap.put("_liquid_free_miner_l", "Gas Collector");

        quotaMap.put("_liquid_free_miner_m", 0);
        nameMap.put("_liquid_free_miner_m", "Gas Collector");

        quotaMap.put("_mineral_free_miner_l", 0);
        nameMap.put("_mineral_free_miner_l", "Mineral Collector");

        quotaMap.put("_mineral_free_miner_m", 0);
        nameMap.put("_mineral_free_miner_m", "Mineral Collector");

        quotaMap.put("_mineral_free_miner_s", 0);
        nameMap.put("_mineral_free_miner_s", "Mineral Collector");

        quotaMap.put("_ice_free_miner_l", 0);
        nameMap.put("_ice_free_miner_l", "Ice Collector");

        quotaMap.put("_ice_free_miner_m", 0);
        nameMap.put("_ice_free_miner_m", "Ice Collector");

        quotaMap.put("_police_patrol_s", 0);
        nameMap.put("_police_patrol_s", "Police");

        // XENON use m free miner and m energycell trader

        // TERRAN
        quotaMap.put("_food_trader_l", 0);
        nameMap.put("_food_trader_l", "Food Delivery Service");

        quotaMap.put("_food_trader_m", 0);
        nameMap.put("_food_trader_m", "Food Delivery Service");

        quotaMap.put("_construction_vessel_xl", 0);
        nameMap.put("_construction_vessel_xl", "Constructor");

        if (!faction.getRace().equals(Race.TERRAN) && !faction.getRace().equals(Race.XENON)) {

            // Per cluster quotas
            quotaMap.replace("_bio_trader_l", 1);
            quotaMap.replace("_bio_trader_m", 2);
            quotaMap.replace("_food_trader_s", 1);
            quotaMap.replace("_medical_trader_s", 1);
            quotaMap.replace("_energycell_trader_l", 1);
            quotaMap.replace("_longrange_energycell_trader_l", 1);
            quotaMap.replace("_longrange_energycell_trader_m", 2);
            quotaMap.replace("_energycell_trader_m", 2);
            quotaMap.replace("_energycell_trader_s", 1);
            quotaMap.replace("_equipment_trader_l", 1);
            quotaMap.replace("_equipment_trader_m", 2);
            quotaMap.replace("_construction_trader_l", 1);
            quotaMap.replace("_construction_trader_m", 2);
            quotaMap.replace("_ship_construction_trader_l", 1);
            quotaMap.replace("_ship_construction_trader_m", 2);
            quotaMap.replace("_refinedgas_trader_l", 1);
            quotaMap.replace("_refinedgas_trader_m", 2);
            quotaMap.replace("_refinedmineral_trader_l", 1);
            quotaMap.replace("_refinedmineral_trader_m", 2);
            quotaMap.replace("_tech_trader_l", 1);
            quotaMap.replace("_tech_trader_m", 2);
            quotaMap.replace("_tech_trader_s", 1);
            quotaMap.replace("_water_trader_l", 1);
            quotaMap.replace("_water_trader_m", 1);
            quotaMap.replace("_liquid_free_miner_l", 1);
            quotaMap.replace("_liquid_free_miner_m", 2);
            quotaMap.replace("_mineral_free_miner_l", 2);
            quotaMap.replace("_mineral_free_miner_m", 4);
            quotaMap.replace("_mineral_free_miner_s", 2);
            quotaMap.replace("_ice_free_miner_l", 1);
            quotaMap.replace("_ice_free_miner_m", 1);

            // max galaxy quotas
            quotaMap.replace("_stationtrader_m", 10);
            quotaMap.replace("_stationtrader_l", 10);
            quotaMap.replace("_construction_vessel_xl", 10);

            for (Entry<String, Integer> job : quotaMap.entrySet()) {
                if (job.getValue() != 0) {
                    jobs.add(setEcoJob(faction, job.getKey(),
                            nameMap.get(job.getKey()), job.getValue(), locationMacro, clusterCount));
                }
            }
        } else if (faction.getRace().equals(Race.XENON)) {
            quotaMap.replace("_mineral_free_miner_m", 12);
            quotaMap.replace("_energycell_trader_m", 4);
            for (Entry<String, Integer> job : quotaMap.entrySet()) {
                if (job.getValue() != 0) {
                    jobs.add(setEcoJob(faction, job.getKey(),
                            nameMap.get(job.getKey()), job.getValue(), locationMacro, clusterCount));
                }
            }
        } else if (faction.getRace().equals(Race.TERRAN)) {
            // Per cluster quotas
            quotaMap.replace("_food_trader_l", 1);
            quotaMap.replace("_food_trader_m", 2);
            quotaMap.replace("_food_trader_s", 1);
            quotaMap.replace("_medical_trader_s", 1);
            quotaMap.replace("_energycell_trader_l", 1);
            quotaMap.replace("_longrange_energycell_trader_l", 1);
            quotaMap.replace("_longrange_energycell_trader_m", 2);
            quotaMap.replace("_energycell_trader_m", 2);
            quotaMap.replace("_energycell_trader_s", 1);
            quotaMap.replace("_equipment_trader_l", 1);
            quotaMap.replace("_equipment_trader_m", 2);
            quotaMap.replace("_construction_trader_l", 1);
            quotaMap.replace("_construction_trader_m", 2);
            quotaMap.replace("_ship_construction_trader_l", 1);
            quotaMap.replace("_ship_construction_trader_m", 2);
            quotaMap.replace("_refinedgas_trader_l", 1);
            quotaMap.replace("_refinedgas_trader_m", 2);
            quotaMap.replace("_refinedmineral_trader_l", 1);
            quotaMap.replace("_refinedmineral_trader_m", 2);
            quotaMap.replace("_tech_trader_l", 1);
            quotaMap.replace("_tech_trader_m", 2);
            quotaMap.replace("_tech_trader_s", 1);
            quotaMap.replace("_liquid_free_miner_l", 1);
            quotaMap.replace("_liquid_free_miner_m", 2);
            quotaMap.replace("_mineral_free_miner_l", 2);
            quotaMap.replace("_mineral_free_miner_m", 4);
            quotaMap.replace("_mineral_free_miner_s", 2);
            quotaMap.replace("_free_miner_l_ice", 1);
            quotaMap.replace("_free_miner_m_ice", 1);

            // max galaxy quotas
            quotaMap.replace("_stationtrader_m", 10);
            quotaMap.replace("_stationtrader_l", 10);
            quotaMap.replace("_construction_vessel_xl", 10);

            for (Entry<String, Integer> job : quotaMap.entrySet()) {
                if (job.getValue() != 0) {
                    jobs.add(setEcoJob(faction, job.getKey(),
                            nameMap.get(job.getKey()), job.getValue(), locationMacro, clusterCount));
                }
            }
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

    private Product placeProductStation(Faction faction, int galaxyQuota, String ware, String id) {
        Product result = new Product();
        result.setOwner(faction);
        result.setId(id);
        result.setRace(faction.getRace());
        result.setWare(ware);

        ProductLocation loc = new ProductLocation();
        List<Faction> spawns = new ArrayList<>();
        spawns.add(faction);
        loc.setSpawnLocations(spawns);

        result.setLocationInfo(loc);
        result.setGalaxyQuota(galaxyQuota);

        return result;
    }

    /*
     * Base economy is 1 module of every step 2 product, which is
     * 1 Missile Components, 1 Wapon Components, 1 Drone Components, 1 Antimatter
     * Converters, 1 Claytronics, 1 Shield Components, 1 Field Coils, 1 Advanced
     * Electronics
     * In addition to any material used in end products which is
     * 2 Hull Parts, 2 Advanced Composites, 2 Engine Parts, 2 Smart Chips
     * The workforce for these is around 10k, if fully staffed, so that requires
     * 4 food ingredient, 2 spice, 2 water, 4 food product, 6 medical supply
     * 
     * Supporting modules for the above:
     * 5 Ecell, 4 graphene, 3 refined metal, 3 plasma conductor, 2 superfluid
     * coolant, 3 microchip, 1 scanning array, 1 antimatter cell, 2 quantum tube
     * 
     * In general, production stations have a 25% chance to have energy cells
     * production so only 1 dedicated Ecell station is probably needed per round.
     * Graphene is a
     * pretty common addon so 2 per round seems reasonable.
     */

    public List<Product> addEconomy(Faction faction, Set<Pair<String, OddQHexCoord>> ownedSectors) {
        ArrayList<Product> products = new ArrayList<Product>();

        int ownedSectorCount = (int) (long) ownedSectors.stream()
                .filter((pair) -> pair.getValue0().equals(faction.getName())).count();

        // 7 zones per sector, one station per zone, subtract defense stations and trade
        // stations and shipyard and wharf
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

        // Terran
        quotaMap.put("computronicsubstrate", 0);
        quotaMap.put("metallicmicrolattice", 0);
        quotaMap.put("siliconcarbide", 0);
        quotaMap.put("proteinpaste", 0);
        quotaMap.put("terranmre", 0);
        quotaMap.put("stimulants", 0);

        // Argon
        quotaMap.put("foodrations", 0);
        quotaMap.put("meat", 0);
        quotaMap.put("wheat", 0);
        quotaMap.put("spacefuel", 0);

        // Teladi
        quotaMap.put("swampplant", 0);
        quotaMap.put("teladianium", 0);
        quotaMap.put("sunriseflowers", 0);
        quotaMap.put("spaceweed", 0);
        quotaMap.put("nostropoil", 0);

        // Paranid
        quotaMap.put("majasnails", 0);
        quotaMap.put("sojabeans", 0);
        quotaMap.put("sojahusk", 0);

        // Split
        quotaMap.put("scruffinfruits", 0);
        quotaMap.put("cheltmeat", 0);

        // 2 owned sector
        if(stationLimit < 40) {
            int stationCount = 0;
            while (stationCount < stationLimit) {
                if(!faction.getRace().equals(Race.TELADI) && !faction.getRace().equals(Race.TERRAN) && !faction.getRace().equals(Race.XENON)) {
                    quotaMap.replace("claytronics", quotaMap.get("claytronics") + 8);
                    quotaMap.replace("hullparts", quotaMap.get("hullparts") + 10);
                    quotaMap.replace("energycells", quotaMap.get("energycells") + 6);
                    quotaMap.replace("graphene", quotaMap.get("graphene") + 4);
                    quotaMap.replace("refinedmetals", quotaMap.get("refinedmetals") + 4);
                    quotaMap.replace("antimattercells", quotaMap.get("antimattercells") + 4);
                    quotaMap.replace("microchips", quotaMap.get("microchips") + 8);
                    quotaMap.replace("quantumtubes", quotaMap.get("quantumtubes") + 4);
                    quotaMap.replace("siliconwafers", quotaMap.get("siliconwafers") + 4);
                    quotaMap.replace("superfluidcoolant", quotaMap.get("superfluidcoolant") + 4);
                    stationCount+=10;
                }
                if(faction.getRace().equals(Race.TELADI)) {
                    quotaMap.replace("claytronics", quotaMap.get("claytronics") + 8);
                    quotaMap.replace("hullparts", quotaMap.get("hullparts") + 10);
                    quotaMap.replace("energycells", quotaMap.get("energycells") + 6);
                    quotaMap.replace("graphene", quotaMap.get("graphene") + 4);
                    quotaMap.replace("teladianium", quotaMap.get("teladianium") + 4);
                    quotaMap.replace("antimattercells", quotaMap.get("antimattercells") + 4);
                    quotaMap.replace("microchips", quotaMap.get("microchips") + 8);
                    quotaMap.replace("quantumtubes", quotaMap.get("quantumtubes") + 4);
                    quotaMap.replace("siliconwafers", quotaMap.get("siliconwafers") + 4);
                    quotaMap.replace("superfluidcoolant", quotaMap.get("superfluidcoolant") + 4);
                    stationCount+=10;
                }
                if(faction.getRace().equals(Race.TERRAN)) {
                    quotaMap.replace("computronicsubstrate", quotaMap.get("computronicsubstrate") + 4);
                    quotaMap.replace("metallicmicrolattice", quotaMap.get("metallicmicrolattice") + 6);
                    quotaMap.replace("siliconcarbide", quotaMap.get("siliconcarbide") + 8);
                    quotaMap.replace("energycells", quotaMap.get("energycells") + 12);
                    stationCount+=10;
                }
                if(faction.getRace().equals(Race.XENON)) {
                    quotaMap.replace("energycells", quotaMap.get("energycells") + 20);
                    stationCount+=10;
                }
            }
        }
        else {
            int stationCount = 0;
            while (stationCount < stationLimit) {
                switch (faction.getRace()) {
                    case ARGON:
                        // Main - missile components, weapon components, turret components, shield
                        // components, hull parts, engine parts, antimatter converters,
                        // field coils, advanced electronics, claytronics, smart chips, advanced
                        // composites
                        // Add one station's worth of modules for each of these
                        quotaMap.replace("advancedcomposites", quotaMap.get("advancedcomposites") + 4);
                        stationCount++;
                        quotaMap.replace("advancedelectronics", quotaMap.get("advancedelectronics") + 8);
                        stationCount++;
                        quotaMap.replace("antimatterconverters", quotaMap.get("antimatterconverters") + 4);
                        stationCount++;
                        quotaMap.replace("claytronics", quotaMap.get("claytronics") + 8);
                        stationCount++;
                        quotaMap.replace("dronecomponents", quotaMap.get("dronecomponents") + 4);
                        stationCount++;
                        quotaMap.replace("engineparts", quotaMap.get("engineparts") + 8);
                        stationCount++;
                        quotaMap.replace("fieldcoils", quotaMap.get("fieldcoils") + 8);
                        stationCount++;
                        quotaMap.replace("hullparts", quotaMap.get("hullparts") + 20);
                        stationCount+=2;
                        quotaMap.replace("missilecomponents", quotaMap.get("missilecomponents") + 4);
                        stationCount++;
                        quotaMap.replace("shieldcomponents", quotaMap.get("shieldcomponents") + 8);
                        stationCount++;
                        quotaMap.replace("smartchips", quotaMap.get("smartchips") + 8);
                        stationCount++;
                        quotaMap.replace("turretcomponents", quotaMap.get("turretcomponents") + 8);
                        stationCount++;
                        quotaMap.replace("weaponcomponents", quotaMap.get("weaponcomponents") + 8);
                        stationCount++;

                        // Add supporting stations for the above, excepting workforce support
                        // the AI can build their workforce stations later
                        quotaMap.replace("antimattercells", quotaMap.get("antimattercells") + 4);
                        stationCount++;
                        quotaMap.replace("energycells", quotaMap.get("energycells") + 18);
                        stationCount += 3;
                        quotaMap.replace("graphene", quotaMap.get("graphene") + 16);
                        stationCount += 4;
                        quotaMap.replace("microchips", quotaMap.get("microchips") + 24);
                        stationCount += 3;
                        quotaMap.replace("plasmaconductors", quotaMap.get("plasmaconductors") + 16);
                        stationCount += 2;
                        quotaMap.replace("quantumtubes", quotaMap.get("quantumtubes") + 16);
                        stationCount += 4;
                        quotaMap.replace("refinedmetals", quotaMap.get("refinedmetals") + 12);
                        stationCount += 3;
                        quotaMap.replace("scanningarrays", quotaMap.get("scanningarrays") + 3);
                        stationCount++;
                        quotaMap.replace("siliconwafers", quotaMap.get("siliconwafers") + 12);
                        stationCount += 3;
                        quotaMap.replace("superfluidcoolant", quotaMap.get("superfluidcoolant") + 8);
                        stationCount += 2;
                        break;
                    case TELADI:
                        // Main - missile components, weapon components, turret components, shield
                        // components, hull parts, engine parts, antimatter converters,
                        // field coils, advanced electronics, claytronics, smart chips, advanced
                        // composites
                        // Add one station's worth of modules for each of these
                        quotaMap.replace("advancedcomposites", quotaMap.get("advancedcomposites") + 4);
                        stationCount++;
                        quotaMap.replace("advancedelectronics", quotaMap.get("advancedelectronics") + 8);
                        stationCount++;
                        quotaMap.replace("antimatterconverters", quotaMap.get("antimatterconverters") + 4);
                        stationCount++;
                        quotaMap.replace("claytronics", quotaMap.get("claytronics") + 8);
                        stationCount++;
                        quotaMap.replace("dronecomponents", quotaMap.get("dronecomponents") + 4);
                        stationCount++;
                        quotaMap.replace("engineparts", quotaMap.get("engineparts") + 8);
                        stationCount++;
                        quotaMap.replace("fieldcoils", quotaMap.get("fieldcoils") + 8);
                        stationCount++;
                        quotaMap.replace("hullparts", quotaMap.get("hullparts") + 20);
                        stationCount+=2;
                        quotaMap.replace("missilecomponents", quotaMap.get("missilecomponents") + 4);
                        stationCount++;
                        quotaMap.replace("shieldcomponents", quotaMap.get("shieldcomponents") + 8);
                        stationCount++;
                        quotaMap.replace("smartchips", quotaMap.get("smartchips") + 8);
                        stationCount++;
                        quotaMap.replace("turretcomponents", quotaMap.get("turretcomponents") + 8);
                        stationCount++;
                        quotaMap.replace("weaponcomponents", quotaMap.get("weaponcomponents") + 8);
                        stationCount++;

                        // Add supporting stations for the above, excepting workforce support
                        // the AI can build their workforce stations later
                        quotaMap.replace("antimattercells", quotaMap.get("antimattercells") + 4);
                        stationCount++;
                        quotaMap.replace("energycells", quotaMap.get("energycells") + 18);
                        stationCount += 3;
                        quotaMap.replace("graphene", quotaMap.get("graphene") + 16);
                        stationCount += 4;
                        quotaMap.replace("microchips", quotaMap.get("microchips") + 24);
                        stationCount += 3;
                        quotaMap.replace("plasmaconductors", quotaMap.get("plasmaconductors") + 16);
                        stationCount += 2;
                        quotaMap.replace("quantumtubes", quotaMap.get("quantumtubes") + 16);
                        stationCount += 4;
                        quotaMap.replace("teladianium", quotaMap.get("teladianium") + 8);
                        stationCount += 2;
                        quotaMap.replace("scanningarrays", quotaMap.get("scanningarrays") + 3);
                        stationCount++;
                        quotaMap.replace("siliconwafers", quotaMap.get("siliconwafers") + 12);
                        stationCount += 3;
                        quotaMap.replace("superfluidcoolant", quotaMap.get("superfluidcoolant") + 8);
                        stationCount += 2;
                        break;
                    case PARANID:
                        // Main - missile components, weapon components, turret components, shield
                        // components, hull parts, engine parts, antimatter converters,
                        // field coils, advanced electronics, claytronics, smart chips, advanced
                        // composites
                        // Add one station's worth of modules for each of these
                        quotaMap.replace("advancedcomposites", quotaMap.get("advancedcomposites") + 4);
                        stationCount++;
                        quotaMap.replace("advancedelectronics", quotaMap.get("advancedelectronics") + 8);
                        stationCount++;
                        quotaMap.replace("antimatterconverters", quotaMap.get("antimatterconverters") + 4);
                        stationCount++;
                        quotaMap.replace("claytronics", quotaMap.get("claytronics") + 8);
                        stationCount++;
                        quotaMap.replace("dronecomponents", quotaMap.get("dronecomponents") + 4);
                        stationCount++;
                        quotaMap.replace("engineparts", quotaMap.get("engineparts") + 8);
                        stationCount++;
                        quotaMap.replace("fieldcoils", quotaMap.get("fieldcoils") + 8);
                        stationCount++;
                        quotaMap.replace("hullparts", quotaMap.get("hullparts") + 20);
                        stationCount+=2;
                        quotaMap.replace("missilecomponents", quotaMap.get("missilecomponents") + 4);
                        stationCount++;
                        quotaMap.replace("shieldcomponents", quotaMap.get("shieldcomponents") + 8);
                        stationCount++;
                        quotaMap.replace("smartchips", quotaMap.get("smartchips") + 8);
                        stationCount++;
                        quotaMap.replace("turretcomponents", quotaMap.get("turretcomponents") + 8);
                        stationCount++;
                        quotaMap.replace("weaponcomponents", quotaMap.get("weaponcomponents") + 8);
                        stationCount++;

                        // Add supporting stations for the above, excepting workforce support
                        // the AI can build their workforce stations later
                        quotaMap.replace("antimattercells", quotaMap.get("antimattercells") + 4);
                        stationCount++;
                        quotaMap.replace("energycells", quotaMap.get("energycells") + 18);
                        stationCount += 3;
                        quotaMap.replace("graphene", quotaMap.get("graphene") + 16);
                        stationCount += 4;
                        quotaMap.replace("microchips", quotaMap.get("microchips") + 24);
                        stationCount += 3;
                        quotaMap.replace("plasmaconductors", quotaMap.get("plasmaconductors") + 16);
                        stationCount += 2;
                        quotaMap.replace("quantumtubes", quotaMap.get("quantumtubes") + 16);
                        stationCount += 4;
                        quotaMap.replace("refinedmetals", quotaMap.get("refinedmetals") + 12);
                        stationCount += 3;
                        quotaMap.replace("scanningarrays", quotaMap.get("scanningarrays") + 3);
                        stationCount++;
                        quotaMap.replace("siliconwafers", quotaMap.get("siliconwafers") + 12);
                        stationCount += 3;
                        quotaMap.replace("superfluidcoolant", quotaMap.get("superfluidcoolant") + 8);
                        stationCount += 2;
                        break;
                    case SPLIT:
                        // Main - missile components, weapon components, turret components, shield
                        // components, hull parts, engine parts, antimatter converters,
                        // field coils, advanced electronics, claytronics, smart chips, advanced
                        // composites
                        // Add one station's worth of modules for each of these
                        quotaMap.replace("advancedcomposites", quotaMap.get("advancedcomposites") + 4);
                        stationCount++;
                        quotaMap.replace("advancedelectronics", quotaMap.get("advancedelectronics") + 8);
                        stationCount++;
                        quotaMap.replace("antimatterconverters", quotaMap.get("antimatterconverters") + 4);
                        stationCount++;
                        quotaMap.replace("claytronics", quotaMap.get("claytronics") + 8);
                        stationCount++;
                        quotaMap.replace("dronecomponents", quotaMap.get("dronecomponents") + 4);
                        stationCount++;
                        quotaMap.replace("engineparts", quotaMap.get("engineparts") + 8);
                        stationCount++;
                        quotaMap.replace("fieldcoils", quotaMap.get("fieldcoils") + 8);
                        stationCount++;
                        quotaMap.replace("hullparts", quotaMap.get("hullparts") + 20);
                        stationCount+=2;
                        quotaMap.replace("missilecomponents", quotaMap.get("missilecomponents") + 4);
                        stationCount++;
                        quotaMap.replace("shieldcomponents", quotaMap.get("shieldcomponents") + 8);
                        stationCount++;
                        quotaMap.replace("smartchips", quotaMap.get("smartchips") + 8);
                        stationCount++;
                        quotaMap.replace("turretcomponents", quotaMap.get("turretcomponents") + 8);
                        stationCount++;
                        quotaMap.replace("weaponcomponents", quotaMap.get("weaponcomponents") + 8);
                        stationCount++;

                        // Add supporting stations for the above, excepting workforce support
                        // the AI can build their workforce stations later
                        quotaMap.replace("antimattercells", quotaMap.get("antimattercells") + 4);
                        stationCount++;
                        quotaMap.replace("energycells", quotaMap.get("energycells") + 18);
                        stationCount += 3;
                        quotaMap.replace("graphene", quotaMap.get("graphene") + 16);
                        stationCount += 4;
                        quotaMap.replace("microchips", quotaMap.get("microchips") + 24);
                        stationCount += 3;
                        quotaMap.replace("plasmaconductors", quotaMap.get("plasmaconductors") + 16);
                        stationCount += 2;
                        quotaMap.replace("quantumtubes", quotaMap.get("quantumtubes") + 16);
                        stationCount += 4;
                        quotaMap.replace("refinedmetals", quotaMap.get("refinedmetals") + 12);
                        stationCount += 3;
                        quotaMap.replace("scanningarrays", quotaMap.get("scanningarrays") + 3);
                        stationCount++;
                        quotaMap.replace("siliconwafers", quotaMap.get("siliconwafers") + 12);
                        stationCount += 3;
                        quotaMap.replace("superfluidcoolant", quotaMap.get("superfluidcoolant") + 8);
                        stationCount += 2;
                        break;
                    case TERRAN:
                        quotaMap.replace("computronicsubstrate", quotaMap.get("computronicsubstrate") + 2);
                        stationCount++;
                        quotaMap.replace("metallicmicrolattice", quotaMap.get("metallicmicrolattice") + 3);
                        stationCount += 2;
                        quotaMap.replace("siliconcarbide", quotaMap.get("siliconcarbide") + 4);
                        stationCount++;
                        quotaMap.replace("energycells", quotaMap.get("energycells") + 6);
                        stationCount++;
                        break;
                    case XENON:
                        quotaMap.replace("energycells", quotaMap.get("energycells") + 2);
                        stationCount++;
                        break;
                }
            }
        }

        for (Entry<String, Integer> product : quotaMap.entrySet()) {
            if (product.getValue() != 0)
                products.add(placeProductStation(faction, product.getValue(), product.getKey(),
                        String.format("%s_%s", faction.getFactionAbbreviation(), product.getKey())));
        }

        return products;
    }

    private JsonJob setMilJob(Faction faction, String id, String name, boolean subordinate, boolean commandeerable,
            String order, boolean defaultOrder, HashMap<String, String> params, String categoryTags, String size,
            int maxGalalaxyQuota, int galaxyQuota, int clusterQuota, int wingQuota, String locationClass,
            String locationMacro, String relation, String comparison, boolean matchExtension, boolean buildAtShipyard,
            String selectTags, String loadoutVariationMin, String loadoutVariationMax, String loadoutLevel,
            boolean overrideNpc, List<String> subordinates) {
        JsonJob newJob = new JsonJob();

        newJob.setId(id);
        newJob.setName(name);
        if (subordinate) {
            newJob.setStartActive(false);
        }

        JsonModifiers mods = new JsonModifiers();
        if (commandeerable)
            mods.setCommandeerable(commandeerable);
        if (subordinate)
            mods.setSubordinate(subordinate);

        JsonOrder ord = new JsonOrder();
        ord.setDefaultOrder(defaultOrder);
        ord.setOrder(order);
        ord.setParams(params);

        JsonCategory cat = new JsonCategory();
        cat.setFaction(faction.getName());
        cat.setSize(size);
        cat.setTags(categoryTags);

        JsonQuota quota = new JsonQuota();
        if (clusterQuota != -1) {
            quota.setCluster(clusterQuota);
        }
        if (galaxyQuota != -1) {
            quota.setGalaxy(galaxyQuota);
        }
        if (maxGalalaxyQuota != -1) {
            quota.setMaxgalaxy(maxGalalaxyQuota);
        }
        if (wingQuota != -1) {
            quota.setWing(wingQuota);
        }

        JsonLocation loc = new JsonLocation();
        loc.setClazz(locationClass);
        loc.setMacro(locationMacro);
        loc.setComparison(comparison);
        loc.setFaction(faction.getName());
        loc.setMatchExtension(matchExtension);
        loc.setRelation(relation);

        JsonEnvironment env = new JsonEnvironment();
        env.setBuildatshipyard(buildAtShipyard);

        JsonShip ship = new JsonShip();
        JsonOwner own = new JsonOwner();
        own.setExact(faction.getName());
        own.setOverridenpc(overrideNpc);
        ship.setOwner(own);

        JsonSelect sel = new JsonSelect();
        sel.setFaction(faction.getName());
        sel.setSize(size);
        sel.setTags(selectTags);

        ship.setSelect(sel);

        newJob.setModifiers(mods);
        newJob.setOrder(ord);
        newJob.setCategory(cat);
        newJob.setQuota(quota);
        newJob.setLocation(loc);
        newJob.setEnvironment(env);
        newJob.setShip(ship);
        newJob.setSubordinates(subordinates);

        return newJob;
    }

    public List<JsonJob> addMilitaryJobs(Faction faction, int clusterCount, String galaxyMacro) {
        switch (faction) {
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
                throw new NotImplementedException(
                        "Military generation for a faction which has not been implemented was attempted.");
        }
    }

    private List<JsonJob> addArgonMil(Faction faction, int clusterCount, String galaxyMacro) {
        ArrayList<JsonJob> jobs = new ArrayList<>();

        HashMap<String, String> parms = new HashMap<>();
        parms.put("range", "class.cluster");

        ArrayList<String> subordinates = new ArrayList<>();
        subordinates.add(faction.getFactionAbbreviation() + "_destroyer_escort_l");
        subordinates.add(faction.getFactionAbbreviation() + "_frigate_escort_m");
        subordinates.add(faction.getFactionAbbreviation() + "_frigate_escort_m");
        subordinates.add(faction.getFactionAbbreviation() + "_frigate_escort_m");
        subordinates.add(faction.getFactionAbbreviation() + "_frigate_escort_m");
        subordinates.add(faction.getFactionAbbreviation() + "_heavyfighter_escort_s_carrier");
        subordinates.add(faction.getFactionAbbreviation() + "_fighter_escort_s_carrier");
        subordinates.add(faction.getFactionAbbreviation() + "_fighter_escort_s_carrier");
        subordinates.add(faction.getFactionAbbreviation() + "_fighter_escort_s_carrier");
        subordinates.add(faction.getFactionAbbreviation() + "_fighter_escort_s_carrier");
        subordinates.add(faction.getFactionAbbreviation() + "_resupplier_escort_xl");

        jobs.add(
                setMilJob(
                        faction,
                        faction.getFactionAbbreviation() + "_carrier_patrol_xl_sector",
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
                        subordinates));

        parms = new HashMap<>();
        parms.put("range", "class.cluster");

        subordinates = new ArrayList<>();
        subordinates.add(faction.getFactionAbbreviation() + "_frigate_escort_m");
        subordinates.add(faction.getFactionAbbreviation() + "_frigate_escort_m");
        subordinates.add(faction.getFactionAbbreviation() + "_fighter_escort_s_sector");
        subordinates.add(faction.getFactionAbbreviation() + "_fighter_escort_s_sector");
        subordinates.add(faction.getFactionAbbreviation() + "_fighter_escort_s_sector");

        jobs.add(
                setMilJob(
                        faction,
                        faction.getFactionAbbreviation() + "_destroyer_patrol_l_sector",
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
                        subordinates));

        parms = new HashMap<>();
        parms.put("range", "class.cluster");

        subordinates = new ArrayList<>();
        subordinates.add(faction.getFactionAbbreviation() + "_fighter_escort_s_sector");
        subordinates.add(faction.getFactionAbbreviation() + "_fighter_escort_s_sector");

        jobs.add(
                setMilJob(
                        faction,
                        faction.getFactionAbbreviation() + "_frigate_patrol_m_sector",
                        "Patrol Frigate",
                        false,
                        true,
                        "Patrol",
                        true,
                        parms,
                        "[factionlogic, military, frigate]",
                        "ship_m",
                        -1,
                        2 * clusterCount,
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
                        subordinates));

        parms = new HashMap<>();
        parms.put("formation", "formationshape.invpointguard");
        parms.put("overrideformationskill", "true");

        subordinates = new ArrayList<>();

        jobs.add(
                setMilJob(
                        faction,
                        faction.getFactionAbbreviation() + "_destroyer_escort_l",
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
                        subordinates));

        parms = new HashMap<>();
        parms.put("formation", "formationshape.pointguard");
        parms.put("overrideformationskill", "true");

        subordinates = new ArrayList<>();

        jobs.add(
                setMilJob(
                        faction,
                        faction.getFactionAbbreviation() + "_frigate_escort_m",
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
                        subordinates));

        parms = new HashMap<>();
        parms.put("formation", "formationshape.triangle");
        parms.put("overrideformationskill", "true");

        subordinates = new ArrayList<>();

        jobs.add(
                setMilJob(
                        faction,
                        faction.getFactionAbbreviation() + "_fighter_escort_s_carrier",
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
                        subordinates));

        parms = new HashMap<>();
        parms.put("formation", "formationshape.triangle");
        parms.put("overrideformationskill", "true");

        subordinates = new ArrayList<>();

        jobs.add(
                setMilJob(
                        faction,
                        faction.getFactionAbbreviation() + "_heavyfighter_escort_s_carrier",
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
                        subordinates));

        parms = new HashMap<>();
        parms.put("formation", "formationshape.triangle");
        parms.put("overrideformationskill", "true");

        subordinates = new ArrayList<>();

        jobs.add(
                setMilJob(
                        faction,
                        faction.getFactionAbbreviation() + "_fighter_escort_s_sector",
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
                        subordinates));

        parms = new HashMap<>();
        subordinates = new ArrayList<>();
        jobs.add(
                setMilJob(
                        faction,
                        faction.getFactionAbbreviation() + "_resupplier_escort_xl",
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
                        subordinates));

        parms = new HashMap<>();
        parms.put("deploysatellites", "true");
        subordinates = new ArrayList<>();
        jobs.add(
                setMilJob(
                        faction,
                        faction.getFactionAbbreviation() + "_recon_s",
                        "Recon",
                        false,
                        true,
                        "Recon",
                        true,
                        parms,
                        "[factionlogic, scout]",
                        "ship_s",
                        -1,
                        1*clusterCount,
                        1,
                        -1,
                        "galaxy",
                        galaxyMacro,
                        "self",
                        "exact",
                        false,
                        true,
                        "[scout]",
                        "0.0",
                        "0.3",
                        "1.0",
                        true,
                        subordinates));

        return jobs;
    }

    private List<JsonJob> addParanidMil(Faction faction, int clusterCount, String galaxyMacro) {
        ArrayList<JsonJob> jobs = new ArrayList<>();

        HashMap<String, String> parms = new HashMap<>();
        parms.put("range", "class.cluster");

        ArrayList<String> subordinates = new ArrayList<>();
        subordinates.add(faction.getFactionAbbreviation() + "_destroyer_escort_l");
        subordinates.add(faction.getFactionAbbreviation() + "_frigate_escort_m");
        subordinates.add(faction.getFactionAbbreviation() + "_frigate_escort_m");
        subordinates.add(faction.getFactionAbbreviation() + "_frigate_escort_m");
        subordinates.add(faction.getFactionAbbreviation() + "_frigate_escort_m");
        subordinates.add(faction.getFactionAbbreviation() + "_heavyfighter_escort_s_carrier");
        subordinates.add(faction.getFactionAbbreviation() + "_heavyfighter_escort_s_carrier");
        subordinates.add(faction.getFactionAbbreviation() + "_fighter_escort_s_carrier");
        subordinates.add(faction.getFactionAbbreviation() + "_fighter_escort_s_carrier");
        subordinates.add(faction.getFactionAbbreviation() + "_fighter_escort_s_carrier");
        subordinates.add(faction.getFactionAbbreviation() + "_fighter_escort_s_carrier");
        subordinates.add(faction.getFactionAbbreviation() + "_resupplier_escort_xl");

        jobs.add(
                setMilJob(
                        faction,
                        faction.getFactionAbbreviation() + "_carrier_patrol_xl_sector",
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
                        subordinates));

        parms = new HashMap<>();
        parms.put("range", "class.cluster");

        parms = new HashMap<>();
        parms.put("range", "class.cluster");

        subordinates = new ArrayList<>();
        subordinates.add(faction.getFactionAbbreviation() + "_fighter_escort_s_sector");
        subordinates.add(faction.getFactionAbbreviation() + "_fighter_escort_s_sector");
        subordinates.add(faction.getFactionAbbreviation() + "_fighter_escort_s_sector");

        jobs.add(
                setMilJob(
                        faction,
                        faction.getFactionAbbreviation() + "_frigate_patrol_m_sector",
                        "Patrol Frigate",
                        false,
                        true,
                        "Patrol",
                        true,
                        parms,
                        "[factionlogic, military, frigate]",
                        "ship_m",
                        -1,
                        2 * clusterCount,
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
                        subordinates));

        parms = new HashMap<>();
        parms.put("formation", "formationshape.invpointguard");
        parms.put("overrideformationskill", "true");

        subordinates = new ArrayList<>();

        jobs.add(
                setMilJob(
                        faction,
                        faction.getFactionAbbreviation() + "_destroyer_escort_l",
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
                        subordinates));

        parms = new HashMap<>();
        parms.put("formation", "formationshape.pointguard");
        parms.put("overrideformationskill", "true");

        subordinates = new ArrayList<>();

        jobs.add(
                setMilJob(
                        faction,
                        faction.getFactionAbbreviation() + "_frigate_escort_m",
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
                        subordinates));

        parms = new HashMap<>();
        parms.put("formation", "formationshape.triangle");
        parms.put("overrideformationskill", "true");

        subordinates = new ArrayList<>();

        jobs.add(
                setMilJob(
                        faction,
                        faction.getFactionAbbreviation() + "_fighter_escort_s_carrier",
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
                        subordinates));

        parms = new HashMap<>();
        parms.put("formation", "formationshape.triangle");
        parms.put("overrideformationskill", "true");

        subordinates = new ArrayList<>();

        jobs.add(
                setMilJob(
                        faction,
                        faction.getFactionAbbreviation() + "_heavyfighter_escort_s_carrier",
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
                        subordinates));

        parms = new HashMap<>();
        parms.put("formation", "formationshape.triangle");
        parms.put("overrideformationskill", "true");

        subordinates = new ArrayList<>();

        jobs.add(
                setMilJob(
                        faction,
                        faction.getFactionAbbreviation() + "_fighter_escort_s_sector",
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
                        subordinates));

        parms = new HashMap<>();
        subordinates = new ArrayList<>();
        jobs.add(
                setMilJob(
                        faction,
                        faction.getFactionAbbreviation() + "_resupplier_escort_xl",
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
                        subordinates));

        parms = new HashMap<>();
        parms.put("deploysatellites", "true");
        subordinates = new ArrayList<>();
        jobs.add(
                setMilJob(
                        faction,
                        faction.getFactionAbbreviation() + "_recon_s",
                        "Recon",
                        false,
                        true,
                        "Recon",
                        true,
                        parms,
                        "[factionlogic, scout]",
                        "ship_s",
                        -1,
                        1*clusterCount,
                        1,
                        -1,
                        "galaxy",
                        galaxyMacro,
                        "self",
                        "exact",
                        false,
                        true,
                        "[scout]",
                        "0.0",
                        "0.3",
                        "1.0",
                        true,
                        subordinates));

        return jobs;
    }

    private List<JsonJob> addTeladiMil(Faction faction, int clusterCount, String galaxyMacro) {
        ArrayList<JsonJob> jobs = new ArrayList<>();

        HashMap<String, String> parms = new HashMap<>();
        parms.put("range", "class.cluster");

        ArrayList<String> subordinates = new ArrayList<>();
        subordinates.add(faction.getFactionAbbreviation() + "_destroyer_escort_l");
        subordinates.add(faction.getFactionAbbreviation() + "_frigate_escort_m");
        subordinates.add(faction.getFactionAbbreviation() + "_frigate_escort_m");
        subordinates.add(faction.getFactionAbbreviation() + "_frigate_escort_m");
        subordinates.add(faction.getFactionAbbreviation() + "_frigate_escort_m");
        subordinates.add(faction.getFactionAbbreviation() + "_heavyfighter_escort_s_carrier");
        subordinates.add(faction.getFactionAbbreviation() + "_fighter_escort_s_carrier");
        subordinates.add(faction.getFactionAbbreviation() + "_fighter_escort_s_carrier");
        subordinates.add(faction.getFactionAbbreviation() + "_fighter_escort_s_carrier");
        subordinates.add(faction.getFactionAbbreviation() + "_fighter_escort_s_carrier");
        subordinates.add(faction.getFactionAbbreviation() + "_resupplier_escort_xl");

        jobs.add(
                setMilJob(
                        faction,
                        faction.getFactionAbbreviation() + "_carrier_patrol_xl_sector",
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
                        subordinates));

        parms = new HashMap<>();
        parms.put("range", "class.cluster");

        subordinates = new ArrayList<>();
        subordinates.add(faction.getFactionAbbreviation() + "_frigate_escort_m");
        subordinates.add(faction.getFactionAbbreviation() + "_frigate_escort_m");
        subordinates.add(faction.getFactionAbbreviation() + "_fighter_escort_s_sector");
        subordinates.add(faction.getFactionAbbreviation() + "_fighter_escort_s_sector");
        subordinates.add(faction.getFactionAbbreviation() + "_fighter_escort_s_sector");

        jobs.add(
                setMilJob(
                        faction,
                        faction.getFactionAbbreviation() + "_destroyer_patrol_l_sector",
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
                        subordinates));

        parms = new HashMap<>();
        parms.put("range", "class.cluster");

        subordinates = new ArrayList<>();
        subordinates.add(faction.getFactionAbbreviation() + "_fighter_escort_s_sector");
        subordinates.add(faction.getFactionAbbreviation() + "_fighter_escort_s_sector");
        subordinates.add(faction.getFactionAbbreviation() + "_fighter_escort_s_sector");

        jobs.add(
                setMilJob(
                        faction,
                        faction.getFactionAbbreviation() + "_frigate_patrol_m_sector",
                        "Patrol Frigate",
                        false,
                        true,
                        "Patrol",
                        true,
                        parms,
                        "[factionlogic, military, frigate]",
                        "ship_m",
                        -1,
                        2 * clusterCount,
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
                        subordinates));

        parms = new HashMap<>();
        parms.put("range", "class.cluster");

        subordinates = new ArrayList<>();
        subordinates.add(Faction.MINISTRY.getFactionAbbreviation() + "_destroyer_escort_l");
        subordinates.add(Faction.MINISTRY.getFactionAbbreviation() + "_frigate_escort_m");
        subordinates.add(Faction.MINISTRY.getFactionAbbreviation() + "_frigate_escort_m");
        subordinates.add(Faction.MINISTRY.getFactionAbbreviation() + "_frigate_escort_m");
        subordinates.add(Faction.MINISTRY.getFactionAbbreviation() + "_frigate_escort_m");
        subordinates.add(Faction.MINISTRY.getFactionAbbreviation() + "_fighter_escort_s_carrier");
        subordinates.add(Faction.MINISTRY.getFactionAbbreviation() + "_fighter_escort_s_carrier");
        subordinates.add(Faction.MINISTRY.getFactionAbbreviation() + "_fighter_escort_s_carrier");
        subordinates.add(Faction.MINISTRY.getFactionAbbreviation() + "_fighter_escort_s_carrier");
        subordinates.add(Faction.MINISTRY.getFactionAbbreviation() + "_fighter_escort_s_carrier");
        subordinates.add(Faction.MINISTRY.getFactionAbbreviation() + "_resupplier_escort_xl");

        jobs.add(
                setMilJob(
                        Faction.MINISTRY,
                        Faction.MINISTRY.getFactionAbbreviation() + "_carrier_patrol_xl_sector",
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
                        subordinates));

        parms = new HashMap<>();
        parms.put("formation", "formationshape.invpointguard");
        parms.put("overrideformationskill", "true");

        subordinates = new ArrayList<>();

        jobs.add(
                setMilJob(
                        faction,
                        faction.getFactionAbbreviation() + "_destroyer_escort_l",
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
                        subordinates));

        parms = new HashMap<>();
        parms.put("formation", "formationshape.invpointguard");
        parms.put("overrideformationskill", "true");

        subordinates = new ArrayList<>();

        jobs.add(
                setMilJob(
                        Faction.MINISTRY,
                        Faction.MINISTRY.getFactionAbbreviation() + "_destroyer_escort_l",
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
                        subordinates));

        parms = new HashMap<>();
        parms.put("formation", "formationshape.pointguard");
        parms.put("overrideformationskill", "true");

        subordinates = new ArrayList<>();

        jobs.add(
                setMilJob(
                        faction,
                        faction.getFactionAbbreviation() + "_frigate_escort_m",
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
                        subordinates));

        parms = new HashMap<>();
        parms.put("formation", "formationshape.pointguard");
        parms.put("overrideformationskill", "true");

        subordinates = new ArrayList<>();

        jobs.add(
                setMilJob(
                        Faction.MINISTRY,
                        Faction.MINISTRY.getFactionAbbreviation() + "_frigate_escort_m",
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
                        subordinates));

        parms = new HashMap<>();
        parms.put("formation", "formationshape.triangle");
        parms.put("overrideformationskill", "true");

        subordinates = new ArrayList<>();

        jobs.add(
                setMilJob(
                        faction,
                        faction.getFactionAbbreviation() + "_fighter_escort_s_carrier",
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
                        subordinates));

        parms = new HashMap<>();
        parms.put("formation", "formationshape.triangle");
        parms.put("overrideformationskill", "true");

        subordinates = new ArrayList<>();

        jobs.add(
                setMilJob(
                        faction,
                        faction.getFactionAbbreviation() + "_heavyfighter_escort_s_carrier",
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
                        subordinates));

        parms = new HashMap<>();
        parms.put("formation", "formationshape.triangle");
        parms.put("overrideformationskill", "true");

        subordinates = new ArrayList<>();

        jobs.add(
                setMilJob(
                        Faction.MINISTRY,
                        Faction.MINISTRY.getFactionAbbreviation() + "_fighter_escort_s_carrier",
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
                        subordinates));

        parms = new HashMap<>();
        parms.put("formation", "formationshape.triangle");
        parms.put("overrideformationskill", "true");

        subordinates = new ArrayList<>();

        jobs.add(
                setMilJob(
                        faction,
                        faction.getFactionAbbreviation() + "_fighter_escort_s_sector",
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
                        subordinates));

        parms = new HashMap<>();
        parms.put("formation", "formationshape.triangle");
        parms.put("overrideformationskill", "true");

        subordinates = new ArrayList<>();

        jobs.add(
                setMilJob(
                        Faction.MINISTRY,
                        Faction.MINISTRY.getFactionAbbreviation() + "_fighter_escort_s_sector",
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
                        subordinates));

        parms = new HashMap<>();
        subordinates = new ArrayList<>();
        jobs.add(
                setMilJob(
                        Faction.MINISTRY,
                        Faction.MINISTRY.getFactionAbbreviation() + "_resupplier_escort_xl",
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
                        subordinates));

        parms = new HashMap<>();
        subordinates = new ArrayList<>();
        jobs.add(
                setMilJob(
                        faction,
                        faction.getFactionAbbreviation() + "_resupplier_escort_xl",
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
                        subordinates));
        
        parms = new HashMap<>();
        parms.put("deploysatellites", "true");
        subordinates = new ArrayList<>();
        jobs.add(
                setMilJob(
                        faction,
                        faction.getFactionAbbreviation() + "_recon_s",
                        "Recon",
                        false,
                        true,
                        "Recon",
                        true,
                        parms,
                        "[factionlogic, scout]",
                        "ship_s",
                        -1,
                        1*clusterCount,
                        1,
                        -1,
                        "galaxy",
                        galaxyMacro,
                        "self",
                        "exact",
                        false,
                        true,
                        "[scout]",
                        "0.0",
                        "0.3",
                        "1.0",
                        true,
                        subordinates));

        return jobs;
    }

    private List<JsonJob> addSplitMil(Faction faction, int clusterCount, String galaxyMacro) {
        ArrayList<JsonJob> jobs = new ArrayList<>();

        HashMap<String, String> parms = new HashMap<>();
        parms.put("range", "class.cluster");

        ArrayList<String> subordinates = new ArrayList<>();
        subordinates.add(faction.getFactionAbbreviation() + "_destroyer_escort_l");
        subordinates.add(faction.getFactionAbbreviation() + "_destroyer_escort_l");
        subordinates.add(faction.getFactionAbbreviation() + "_frigate_escort_m");
        subordinates.add(faction.getFactionAbbreviation() + "_frigate_escort_m");
        subordinates.add(faction.getFactionAbbreviation() + "_frigate_escort_m");
        subordinates.add(faction.getFactionAbbreviation() + "_heavyfighter_escort_s_carrier");
        subordinates.add(faction.getFactionAbbreviation() + "_fighter_escort_s_carrier");
        subordinates.add(faction.getFactionAbbreviation() + "_fighter_escort_s_carrier");
        subordinates.add(faction.getFactionAbbreviation() + "_resupplier_escort_xl");

        jobs.add(
                setMilJob(
                        faction,
                        faction.getFactionAbbreviation() + "_carrier_patrol_xl_sector",
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
                        subordinates));

        parms = new HashMap<>();
        parms.put("range", "class.cluster");

        subordinates = new ArrayList<>();
        subordinates.add(faction.getFactionAbbreviation() + "_frigate_escort_m");
        subordinates.add(faction.getFactionAbbreviation() + "_frigate_escort_m");
        subordinates.add(faction.getFactionAbbreviation() + "_fighter_escort_s_sector");

        jobs.add(
                setMilJob(
                        faction,
                        faction.getFactionAbbreviation() + "_destroyer_patrol_l_sector",
                        "Patrol Destroyer",
                        false,
                        true,
                        "Patrol",
                        true,
                        parms,
                        "[factionlogic, military, destroyer]",
                        "ship_l",
                        -1,
                        2 * clusterCount,
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
                        subordinates));

        parms = new HashMap<>();
        parms.put("range", "class.cluster");

        subordinates = new ArrayList<>();
        subordinates.add(faction.getFactionAbbreviation() + "_fighter_escort_s_sector");
        subordinates.add(faction.getFactionAbbreviation() + "_fighter_escort_s_sector");

        jobs.add(
                setMilJob(
                        faction,
                        faction.getFactionAbbreviation() + "_frigate_patrol_m_sector",
                        "Patrol Frigate",
                        false,
                        true,
                        "Patrol",
                        true,
                        parms,
                        "[factionlogic, military, frigate]",
                        "ship_m",
                        -1,
                        2 * clusterCount,
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
                        subordinates));

        parms = new HashMap<>();
        parms.put("formation", "formationshape.invpointguard");
        parms.put("overrideformationskill", "true");

        subordinates = new ArrayList<>();

        jobs.add(
                setMilJob(
                        faction,
                        faction.getFactionAbbreviation() + "_destroyer_escort_l",
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
                        subordinates));

        parms = new HashMap<>();
        parms.put("formation", "formationshape.pointguard");
        parms.put("overrideformationskill", "true");

        subordinates = new ArrayList<>();

        jobs.add(
                setMilJob(
                        faction,
                        faction.getFactionAbbreviation() + "_frigate_escort_m",
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
                        subordinates));

        parms = new HashMap<>();
        parms.put("formation", "formationshape.triangle");
        parms.put("overrideformationskill", "true");

        subordinates = new ArrayList<>();

        jobs.add(
                setMilJob(
                        faction,
                        faction.getFactionAbbreviation() + "_fighter_escort_s_carrier",
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
                        subordinates));

        parms = new HashMap<>();
        parms.put("formation", "formationshape.triangle");
        parms.put("overrideformationskill", "true");

        subordinates = new ArrayList<>();

        jobs.add(
                setMilJob(
                        faction,
                        faction.getFactionAbbreviation() + "_heavyfighter_escort_s_carrier",
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
                        subordinates));

        parms = new HashMap<>();
        parms.put("formation", "formationshape.triangle");
        parms.put("overrideformationskill", "true");

        subordinates = new ArrayList<>();

        jobs.add(
                setMilJob(
                        faction,
                        faction.getFactionAbbreviation() + "_fighter_escort_s_sector",
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
                        subordinates));

        parms = new HashMap<>();
        subordinates = new ArrayList<>();
        jobs.add(
                setMilJob(
                        faction,
                        faction.getFactionAbbreviation() + "_resupplier_escort_xl",
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
                        subordinates));

        parms = new HashMap<>();
        parms.put("deploysatellites", "true");
        subordinates = new ArrayList<>();
        jobs.add(
                setMilJob(
                        faction,
                        faction.getFactionAbbreviation() + "_recon_s",
                        "Recon",
                        false,
                        true,
                        "Recon",
                        true,
                        parms,
                        "[factionlogic, scout]",
                        "ship_s",
                        -1,
                        1*clusterCount,
                        1,
                        -1,
                        "galaxy",
                        galaxyMacro,
                        "self",
                        "exact",
                        false,
                        true,
                        "[scout]",
                        "0.0",
                        "0.3",
                        "1.0",
                        true,
                        subordinates));

        return jobs;
    }

    private List<JsonJob> addTerranMil(Faction faction, int clusterCount, String galaxyMacro) {
        ArrayList<JsonJob> jobs = new ArrayList<>();

        HashMap<String, String> parms = new HashMap<>();
        parms.put("range", "class.cluster");

        ArrayList<String> subordinates = new ArrayList<>();
        subordinates.add(faction.getFactionAbbreviation() + "_destroyer_escort_l");
        subordinates.add(faction.getFactionAbbreviation() + "_destroyer_escort_l");
        subordinates.add(faction.getFactionAbbreviation() + "_frigate_escort_m");
        subordinates.add(faction.getFactionAbbreviation() + "_frigate_escort_m");
        subordinates.add(faction.getFactionAbbreviation() + "_frigate_escort_m");
        subordinates.add(faction.getFactionAbbreviation() + "_heavyfighter_escort_s_carrier");
        subordinates.add(faction.getFactionAbbreviation() + "_fighter_escort_s_carrier");
        subordinates.add(faction.getFactionAbbreviation() + "_fighter_escort_s_carrier");
        subordinates.add(faction.getFactionAbbreviation() + "_resupplier_escort_xl");

        jobs.add(
                setMilJob(
                        faction,
                        faction.getFactionAbbreviation() + "_carrier_patrol_xl_sector",
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
                        subordinates));

        parms = new HashMap<>();
        parms.put("range", "class.cluster");

        subordinates = new ArrayList<>();
        subordinates.add(faction.getFactionAbbreviation() + "_frigate_escort_m");
        subordinates.add(faction.getFactionAbbreviation() + "_frigate_escort_m");
        subordinates.add(faction.getFactionAbbreviation() + "_fighter_escort_s_sector");

        jobs.add(
                setMilJob(
                        faction,
                        faction.getFactionAbbreviation() + "_destroyer_patrol_l_sector",
                        "Patrol Destroyer",
                        false,
                        true,
                        "Patrol",
                        true,
                        parms,
                        "[factionlogic, military, destroyer]",
                        "ship_l",
                        -1,
                        2 * clusterCount,
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
                        subordinates));

        parms = new HashMap<>();
        parms.put("range", "class.cluster");

        subordinates = new ArrayList<>();
        subordinates.add(faction.getFactionAbbreviation() + "_fighter_escort_s_sector");
        subordinates.add(faction.getFactionAbbreviation() + "_fighter_escort_s_sector");

        jobs.add(
                setMilJob(
                        faction,
                        faction.getFactionAbbreviation() + "_frigate_patrol_m_sector",
                        "Patrol Frigate",
                        false,
                        true,
                        "Patrol",
                        true,
                        parms,
                        "[factionlogic, military, frigate]",
                        "ship_m",
                        -1,
                        2 * clusterCount,
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
                        subordinates));

        parms = new HashMap<>();
        parms.put("formation", "formationshape.invpointguard");
        parms.put("overrideformationskill", "true");

        subordinates = new ArrayList<>();

        jobs.add(
                setMilJob(
                        faction,
                        faction.getFactionAbbreviation() + "_destroyer_escort_l",
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
                        subordinates));

        parms = new HashMap<>();
        parms.put("formation", "formationshape.pointguard");
        parms.put("overrideformationskill", "true");

        subordinates = new ArrayList<>();

        jobs.add(
                setMilJob(
                        faction,
                        faction.getFactionAbbreviation() + "_frigate_escort_m",
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
                        subordinates));

        parms = new HashMap<>();
        parms.put("formation", "formationshape.triangle");
        parms.put("overrideformationskill", "true");

        subordinates = new ArrayList<>();

        jobs.add(
                setMilJob(
                        faction,
                        faction.getFactionAbbreviation() + "_fighter_escort_s_carrier",
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
                        subordinates));

        parms = new HashMap<>();
        parms.put("formation", "formationshape.triangle");
        parms.put("overrideformationskill", "true");

        subordinates = new ArrayList<>();

        jobs.add(
                setMilJob(
                        faction,
                        faction.getFactionAbbreviation() + "_heavyfighter_escort_s_carrier",
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
                        subordinates));

        parms = new HashMap<>();
        parms.put("formation", "formationshape.triangle");
        parms.put("overrideformationskill", "true");

        subordinates = new ArrayList<>();

        jobs.add(
                setMilJob(
                        faction,
                        faction.getFactionAbbreviation() + "_fighter_escort_s_sector",
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
                        subordinates));

        parms = new HashMap<>();
        subordinates = new ArrayList<>();
        jobs.add(
                setMilJob(
                        faction,
                        faction.getFactionAbbreviation() + "_resupplier_escort_xl",
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
                        subordinates));

        parms = new HashMap<>();
        parms.put("deploysatellites", "true");
        subordinates = new ArrayList<>();
        jobs.add(
                setMilJob(
                        faction,
                        faction.getFactionAbbreviation() + "_recon_s",
                        "Recon",
                        false,
                        true, 
                        "Recon",
                        true,
                        parms,
                        "[factionlogic, scout]",
                        "ship_s",
                        -1,
                        1*clusterCount,
                        1,
                        -1,
                        "galaxy",
                        galaxyMacro,
                        "self",
                        "exact",
                        false,
                        true,
                        "[scout]",
                        "0.0",
                        "0.3",
                        "1.0",
                        true,
                        subordinates));

        return jobs;
    }

    private List<JsonJob> addXenonMil(Faction faction, int clusterCount, String galaxyMacro) {
        ArrayList<JsonJob> jobs = new ArrayList<>();

        HashMap<String, String> parms = new HashMap<>();
        parms.put("range", "class.cluster");

        ArrayList<String> subordinates = new ArrayList<>();
        subordinates.add(faction.getFactionAbbreviation() + "_destroyer_escort_xl");
        subordinates.add(faction.getFactionAbbreviation() + "_destroyer_escort_xl");
        subordinates.add(faction.getFactionAbbreviation() + "_frigate_escort_m");
        subordinates.add(faction.getFactionAbbreviation() + "_frigate_escort_m");
        subordinates.add(faction.getFactionAbbreviation() + "_frigate_escort_m");
        subordinates.add(faction.getFactionAbbreviation() + "_frigate_escort_m");
        subordinates.add(faction.getFactionAbbreviation() + "_heavyfighter_escort_s_carrier");
        subordinates.add(faction.getFactionAbbreviation() + "_fighter_escort_s_carrier");
        subordinates.add(faction.getFactionAbbreviation() + "_fighter_escort_s_carrier");
        subordinates.add(faction.getFactionAbbreviation() + "_fighter_escort_s_carrier");

        jobs.add(
                setMilJob(
                        faction,
                        faction.getFactionAbbreviation() + "_carrier_patrol_xl_sector",
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
                        subordinates));

        parms = new HashMap<>();
        parms.put("range", "class.cluster");

        subordinates = new ArrayList<>();
        subordinates.add(faction.getFactionAbbreviation() + "_frigate_escort_m");
        subordinates.add(faction.getFactionAbbreviation() + "_frigate_escort_m");
        subordinates.add(faction.getFactionAbbreviation() + "_fighter_escort_s_sector");

        jobs.add(
                setMilJob(
                        faction,
                        faction.getFactionAbbreviation() + "_destroyer_patrol_xl_sector",
                        "Patrol Destroyer",
                        false,
                        true,
                        "Patrol",
                        true,
                        parms,
                        "[factionlogic, military, destroyer]",
                        "ship_xl",
                        -1,
                        2 * clusterCount,
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
                        subordinates));

        parms = new HashMap<>();
        parms.put("range", "class.cluster");

        subordinates = new ArrayList<>();
        subordinates.add(faction.getFactionAbbreviation() + "_fighter_escort_s_sector");
        subordinates.add(faction.getFactionAbbreviation() + "_fighter_escort_s_sector");

        jobs.add(
                setMilJob(
                        faction,
                        faction.getFactionAbbreviation() + "_frigate_patrol_m_sector",
                        "Patrol Frigate",
                        false,
                        true,
                        "Patrol",
                        true,
                        parms,
                        "[factionlogic, military, frigate]",
                        "ship_m",
                        -1,
                        2 * clusterCount,
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
                        subordinates));

        parms = new HashMap<>();
        parms.put("formation", "formationshape.invpointguard");
        parms.put("overrideformationskill", "true");

        subordinates = new ArrayList<>();

        jobs.add(
                setMilJob(
                        faction,
                        faction.getFactionAbbreviation() + "_destroyer_escort_xl",
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
                        subordinates));

        parms = new HashMap<>();
        parms.put("formation", "formationshape.pointguard");
        parms.put("overrideformationskill", "true");

        subordinates = new ArrayList<>();

        jobs.add(
                setMilJob(
                        faction,
                        faction.getFactionAbbreviation() + "_frigate_escort_m",
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
                        subordinates));

        parms = new HashMap<>();
        parms.put("formation", "formationshape.triangle");
        parms.put("overrideformationskill", "true");

        subordinates = new ArrayList<>();

        jobs.add(
                setMilJob(
                        faction,
                        faction.getFactionAbbreviation() + "_fighter_escort_s_carrier",
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
                        subordinates));

        parms = new HashMap<>();
        parms.put("formation", "formationshape.triangle");
        parms.put("overrideformationskill", "true");

        subordinates = new ArrayList<>();

        jobs.add(
                setMilJob(
                        faction,
                        faction.getFactionAbbreviation() + "_heavyfighter_escort_s_carrier",
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
                        subordinates));

        parms = new HashMap<>();
        parms.put("formation", "formationshape.triangle");
        parms.put("overrideformationskill", "true");

        subordinates = new ArrayList<>();

        jobs.add(
                setMilJob(
                        faction,
                        faction.getFactionAbbreviation() + "_fighter_escort_s_sector",
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
                        subordinates));


        parms = new HashMap<>();
        parms.put("deploysatellites", "true");
        subordinates = new ArrayList<>();
        jobs.add(
                setMilJob(
                        faction,
                        faction.getFactionAbbreviation() + "_recon_s",
                        "Recon",
                        false,
                        true,
                        "Recon",
                        true,
                        parms,
                        "[factionlogic, scout]",
                        "ship_s",
                        -1,
                        1*clusterCount,
                        1,
                        -1,
                        "galaxy",
                        galaxyMacro,
                        "self",
                        "exact",
                        false,
                        true,
                        "[scout]",
                        "0.0",
                        "0.3",
                        "1.0",
                        true,
                        subordinates));

        return jobs;
    }
}
