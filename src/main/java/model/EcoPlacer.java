package model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.javatuples.Pair;

public class EcoPlacer {

    public List<Cluster> placeMinistryOfFinance(Set<Pair<String, OddQHexCoord>> ownedSectors, List<Cluster> clusters) {
        List<Pair<String, OddQHexCoord>> teladiSectors = ownedSectors.stream()
                .filter((pair) -> pair.getValue0().equals(Faction.TELADI.getName())).collect(Collectors.toList());

        if ( teladiSectors.size() == 0 ) return clusters;

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

    public List<Product> addEconomy(Faction faction, Set<Pair<String, OddQHexCoord>> ownedSectors) {
        ArrayList<Product> products = new ArrayList<Product>();

        int ownedSectorCount = (int) (long) ownedSectors.stream()
                .filter((pair) -> pair.getValue0().equals(faction.getName())).count();

        // handle not-TER, not XEN general eco
        if (!faction.getRace().equals(Race.TERRAN) && !faction.getRace().equals(Race.XENON)) {
            products.add(new Product() {
                {
                    setOwner(faction);
                    setRace(faction.getRace());
                    setGalaxyQuota(Math.max((int) (2.0 / 11 * ownedSectorCount), 1));
                    setWare("advancedcomposites");
                    setId(faction.getFactionAbbreviation() + "_advancedcomposites");
                    setLocationInfo(new ProductLocation() {
                        {
                            setSpawnLocations(new ArrayList<Faction>() {
                                {
                                    add(faction);
                                }
                            });
                        }
                    });
                }
            });

            products.add(new Product() {
                {
                    setOwner(faction);
                    setRace(faction.getRace());
                    setGalaxyQuota(Math.max((int) (2.0 / 11 * ownedSectorCount), 1));
                    setWare("advancedelectronics");
                    setId(faction.getFactionAbbreviation() + "_advancedelectronics");
                    setLocationInfo(new ProductLocation() {
                        {
                            setSpawnLocations(new ArrayList<Faction>() {
                                {
                                    add(faction);
                                }
                            });
                        }
                    });
                }
            });

            products.add(new Product() {
                {
                    setOwner(faction);
                    setRace(faction.getRace());
                    setGalaxyQuota(Math.max((int) (3.0 / 11 * ownedSectorCount), 1));
                    setWare("antimattercells");
                    setId(faction.getFactionAbbreviation() + "_antimattercells");
                    setLocationInfo(new ProductLocation() {
                        {
                            setSpawnLocations(new ArrayList<Faction>() {
                                {
                                    add(faction);
                                }
                            });
                            setWares(new ArrayList<String>() {
                                {
                                    add("hydrogen");
                                }
                            });
                        }
                    });
                }
            });

            products.add(new Product() {
                {
                    setOwner(faction);
                    setRace(faction.getRace());
                    setGalaxyQuota(Math.max((int) (2.0 / 11 * ownedSectorCount), 1));
                    setWare("antimatterconverters");
                    setId(faction.getFactionAbbreviation() + "_antimatterconverters");
                    setLocationInfo(new ProductLocation() {
                        {
                            setSpawnLocations(new ArrayList<Faction>() {
                                {
                                    add(faction);
                                }
                            });
                        }
                    });
                }
            });

            products.add(new Product() {
                {
                    setOwner(faction);
                    setRace(faction.getRace());
                    setGalaxyQuota(Math.max((int) (7.0 / 11 * ownedSectorCount), 1));
                    setWare("claytronics");
                    setId(faction.getFactionAbbreviation() + "_claytronics");
                    setLocationInfo(new ProductLocation() {
                        {
                            setSpawnLocations(new ArrayList<Faction>() {
                                {
                                    add(faction);
                                }
                            });
                        }
                    });
                }
            });

            products.add(new Product() {
                {
                    setOwner(faction);
                    setRace(faction.getRace());
                    setGalaxyQuota(Math.max((int) (2.0 / 11 * ownedSectorCount), 1));
                    setWare("dronecomponents");
                    setId(faction.getFactionAbbreviation() + "_dronecomponents");
                    setLocationInfo(new ProductLocation() {
                        {
                            setSpawnLocations(new ArrayList<Faction>() {
                                {
                                    add(faction);
                                }
                            });
                        }
                    });
                }
            });

            products.add(new Product() {
                {
                    setOwner(faction);
                    setRace(faction.getRace());
                    setGalaxyQuota(Math.max((int) (10.0 / 11 * ownedSectorCount), 1));
                    setSectorQuota(6);
                    setWare("energycells");
                    setId(faction.getFactionAbbreviation() + "_energycells");
                    setLocationInfo(new ProductLocation() {
                        {
                            setSpawnLocations(new ArrayList<Faction>() {
                                {
                                    add(faction);
                                }
                            });
                            setSunlightMin("0.5");
                            setSunlightMax("10");
                        }
                    });
                }
            });

            products.add(new Product() {
                {
                    setOwner(faction);
                    setRace(faction.getRace());
                    setGalaxyQuota(Math.max((int) (2.0 / 11 * ownedSectorCount), 1));
                    setWare("engineparts");
                    setId(faction.getFactionAbbreviation() + "_engineparts");
                    setLocationInfo(new ProductLocation() {
                        {
                            setSpawnLocations(new ArrayList<Faction>() {
                                {
                                    add(faction);
                                }
                            });
                        }
                    });
                }
            });

            products.add(new Product() {
                {
                    setOwner(faction);
                    setRace(faction.getRace());
                    setGalaxyQuota(Math.max((int) (2.0 / 11 * ownedSectorCount), 1));
                    setWare("fieldcoils");
                    setId(faction.getFactionAbbreviation() + "_fieldcoils");
                    setLocationInfo(new ProductLocation() {
                        {
                            setSpawnLocations(new ArrayList<Faction>() {
                                {
                                    add(faction);
                                }
                            });
                        }
                    });
                }
            });

            products.add(new Product() {
                {
                    setOwner(faction);
                    setRace(faction.getRace());
                    setGalaxyQuota(Math.max((int) (6.0 / 11 * ownedSectorCount), 1));
                    setWare("graphene");
                    setId(faction.getFactionAbbreviation() + "_graphene");
                    setLocationInfo(new ProductLocation() {
                        {
                            setSpawnLocations(new ArrayList<Faction>() {
                                {
                                    add(faction);
                                }
                            });
                            setWares(new ArrayList<String>() {
                                {
                                    add("methane");
                                }
                            });
                        }
                    });
                }
            });

            if (!faction.getRace().equals(Race.TELADI)) {
                products.add(new Product() {
                    {
                        setOwner(faction);
                        setRace(faction.getRace());
                        setGalaxyQuota(Math.max((int) (6.0 / 11 * ownedSectorCount), 1));
                        setSectorQuota(Math.max((int) (4.0 / 11 * ownedSectorCount), 1));
                        setWare("refinedmetals");
                        setId(faction.getFactionAbbreviation() + "_refinedmetals");
                        setLocationInfo(new ProductLocation() {
                            {
                                setSpawnLocations(new ArrayList<Faction>() {
                                    {
                                        add(faction);
                                    }
                                });
                                setWares(new ArrayList<String>() {
                                    {
                                        add("ore");
                                    }
                                });
                            }
                        });
                    }
                });
            }

            products.add(new Product() {
                {
                    setOwner(faction);
                    setRace(faction.getRace());
                    setGalaxyQuota(Math.max((int) (20.0 / 11 * ownedSectorCount), 1));
                    setWare("hullparts");
                    setId(faction.getFactionAbbreviation() + "_hullparts");
                    setLocationInfo(new ProductLocation() {
                        {
                            setSpawnLocations(new ArrayList<Faction>() {
                                {
                                    add(faction);
                                }
                            });
                        }
                    });
                }
            });

            products.add(new Product() {
                {
                    setOwner(faction);
                    setRace(faction.getRace());
                    setGalaxyQuota(Math.max((int) (2.0 * ownedSectorCount), 1));
                    setWare("medicalsupplies");
                    setId(faction.getFactionAbbreviation() + "_medicalsupplies");
                    setLocationInfo(new ProductLocation() {
                        {
                            setSpawnLocations(new ArrayList<Faction>() {
                                {
                                    add(faction);
                                }
                            });
                        }
                    });
                }
            });

            products.add(new Product() {
                {
                    setOwner(faction);
                    setRace(faction.getRace());
                    setGalaxyQuota(Math.max((int) (8.0 / 11 * ownedSectorCount), 1));
                    setWare("microchips");
                    setId(faction.getFactionAbbreviation() + "_microchips");
                    setLocationInfo(new ProductLocation() {
                        {
                            setSpawnLocations(new ArrayList<Faction>() {
                                {
                                    add(faction);
                                }
                            });
                        }
                    });
                }
            });

            products.add(new Product() {
                {
                    setOwner(faction);
                    setRace(faction.getRace());
                    setGalaxyQuota(Math.max((int) (2.0 / 11 * ownedSectorCount), 1));
                    setWare("missilecomponents");
                    setId(faction.getFactionAbbreviation() + "_missilecomponents");
                    setLocationInfo(new ProductLocation() {
                        {
                            setSpawnLocations(new ArrayList<Faction>() {
                                {
                                    add(faction);
                                }
                            });
                        }
                    });
                }
            });

            products.add(new Product() {
                {
                    setOwner(faction);
                    setRace(faction.getRace());
                    setGalaxyQuota(Math.max((int) (2.0 / 11 * ownedSectorCount), 1));
                    setWare("plasmaconductors");
                    setId(faction.getFactionAbbreviation() + "_plasmaconductors");
                    setLocationInfo(new ProductLocation() {
                        {
                            setSpawnLocations(new ArrayList<Faction>() {
                                {
                                    add(faction);
                                }
                            });
                        }
                    });
                }
            });

            products.add(new Product() {
                {
                    setOwner(faction);
                    setRace(faction.getRace());
                    setGalaxyQuota(Math.max((int) (3.0 / 11 * ownedSectorCount), 1));
                    setWare("quantumtubes");
                    setId(faction.getFactionAbbreviation() + "_quantumtubes");
                    setLocationInfo(new ProductLocation() {
                        {
                            setSpawnLocations(new ArrayList<Faction>() {
                                {
                                    add(faction);
                                }
                            });
                        }
                    });
                }
            });

            products.add(new Product() {
                {
                    setOwner(faction);
                    setRace(faction.getRace());
                    setGalaxyQuota(Math.max((int) (2.0 / 11 * ownedSectorCount), 1));
                    setWare("scanningarrays");
                    setId(faction.getFactionAbbreviation() + "_scanningarrays");
                    setLocationInfo(new ProductLocation() {
                        {
                            setSpawnLocations(new ArrayList<Faction>() {
                                {
                                    add(faction);
                                }
                            });
                        }
                    });
                }
            });

            products.add(new Product() {
                {
                    setOwner(faction);
                    setRace(faction.getRace());
                    setGalaxyQuota(Math.max((int) (3.0 / 11 * ownedSectorCount), 1));
                    setWare("shieldcomponents");
                    setId(faction.getFactionAbbreviation() + "_shieldcomponents");
                    setLocationInfo(new ProductLocation() {
                        {
                            setSpawnLocations(new ArrayList<Faction>() {
                                {
                                    add(faction);
                                }
                            });
                        }
                    });
                }
            });

            products.add(new Product() {
                {
                    setOwner(faction);
                    setRace(faction.getRace());
                    setGalaxyQuota(Math.max((int) (3.0 / 11 * ownedSectorCount), 1));
                    setSectorQuota(4);
                    setWare("siliconwafers");
                    setId(faction.getFactionAbbreviation() + "_siliconwafers");
                    setLocationInfo(new ProductLocation() {
                        {
                            setSpawnLocations(new ArrayList<Faction>() {
                                {
                                    add(faction);
                                }
                            });
                            setWares(new ArrayList<String>() {
                                {
                                    add("silicon");
                                }
                            });
                        }
                    });
                }
            });

            products.add(new Product() {
                {
                    setOwner(faction);
                    setRace(faction.getRace());
                    setGalaxyQuota(Math.max((int) (8.0 / 11 * ownedSectorCount), 1));
                    setWare("smartchips");
                    setId(faction.getFactionAbbreviation() + "_smartchips");
                    setLocationInfo(new ProductLocation() {
                        {
                            setSpawnLocations(new ArrayList<Faction>() {
                                {
                                    add(faction);
                                }
                            });
                        }
                    });
                }
            });

            products.add(new Product() {
                {
                    setOwner(faction);
                    setRace(faction.getRace());
                    setGalaxyQuota(Math.max((int) (2.0 / 11 * ownedSectorCount), 1));
                    setWare("superfluidcoolant");
                    setId(faction.getFactionAbbreviation() + "_superfluidcoolant");
                    setLocationInfo(new ProductLocation() {
                        {
                            setSpawnLocations(new ArrayList<Faction>() {
                                {
                                    add(faction);
                                }
                            });
                            setWares(new ArrayList<String>() {
                                {
                                    add("helium");
                                }
                            });
                        }
                    });
                }
            });

            products.add(new Product() {
                {
                    setOwner(faction);
                    setRace(faction.getRace());
                    setGalaxyQuota(Math.max((int) (2.0 / 11 * ownedSectorCount), 1));
                    setWare("turretcomponents");
                    setId(faction.getFactionAbbreviation() + "_turretcomponents");
                    setLocationInfo(new ProductLocation() {
                        {
                            setSpawnLocations(new ArrayList<Faction>() {
                                {
                                    add(faction);
                                }
                            });
                        }
                    });
                }
            });

            products.add(new Product() {
                {
                    setOwner(faction);
                    setRace(faction.getRace());
                    setGalaxyQuota(Math.max((int) (4.0 / 11 * ownedSectorCount), 1));
                    setWare("water");
                    setId(faction.getFactionAbbreviation() + "_water");
                    setLocationInfo(new ProductLocation() {
                        {
                            setSpawnLocations(new ArrayList<Faction>() {
                                {
                                    add(faction);
                                }
                            });
                            setWares(new ArrayList<String>() {
                                {
                                    add("ice");
                                }
                            });
                        }
                    });
                }
            });

            products.add(new Product() {
                {
                    setOwner(faction);
                    setRace(faction.getRace());
                    setGalaxyQuota(Math.max((int) (4.0 / 11 * ownedSectorCount), 1));
                    setWare("weaponcomponents");
                    setId(faction.getFactionAbbreviation() + "_weaponcomponents");
                    setLocationInfo(new ProductLocation() {
                        {
                            setSpawnLocations(new ArrayList<Faction>() {
                                {
                                    add(faction);
                                }
                            });
                        }
                    });
                }
            });

            products.add(new Product() {
                {
                    setOwner(faction);
                    setRace(faction.getRace());
                    setGalaxyQuota(Math.max((int) (6.0 / 11 * ownedSectorCount), 1));
                    setWare("spices");
                    setId(faction.getFactionAbbreviation() + "_spices");
                    setLocationInfo(new ProductLocation() {
                        {
                            setSpawnLocations(new ArrayList<Faction>() {
                                {
                                    add(faction);
                                }
                            });
                        }
                    });
                }
            });
        }
        // handle specific race eco
        // Arg need spacefuel, meat, wheat, foodrations
        if (faction.getRace().equals(Race.ARGON)) {
            products.add(new Product() {
                {
                    setOwner(faction);
                    setRace(faction.getRace());
                    setGalaxyQuota(Math.max(ownedSectorCount, 1));
                    setWare("foodrations");
                    setId(faction.getFactionAbbreviation() + "_foodrations");
                    setLocationInfo(new ProductLocation() {
                        {
                            setSpawnLocations(new ArrayList<Faction>() {
                                {
                                    add(faction);
                                }
                            });
                        }
                    });
                }
            });

            products.add(new Product() {
                {
                    setOwner(faction);
                    setRace(faction.getRace());
                    setGalaxyQuota(Math.max((int) (2.0 / 11 * ownedSectorCount), 1));
                    setWare("meat");
                    setId(faction.getFactionAbbreviation() + "_meat");
                    setLocationInfo(new ProductLocation() {
                        {
                            setSpawnLocations(new ArrayList<Faction>() {
                                {
                                    add(faction);
                                }
                            });
                        }
                    });
                }
            });

            products.add(new Product() {
                {
                    setOwner(faction);
                    setRace(faction.getRace());
                    setGalaxyQuota(Math.max((int) (4.0 / 11 * ownedSectorCount), 1));
                    setWare("wheat");
                    setId(faction.getFactionAbbreviation() + "_wheat");
                    setLocationInfo(new ProductLocation() {
                        {
                            setSpawnLocations(new ArrayList<Faction>() {
                                {
                                    add(faction);
                                }
                            });
                        }
                    });
                }
            });

            // Argon have spacefuel, Antigone doesn't
            if (faction.equals(Faction.ARGON)) {
                products.add(new Product() {
                    {
                        setOwner(faction);
                        setRace(faction.getRace());
                        setGalaxyQuota(Math.max((int) (2.0 / 11 * ownedSectorCount), 1));
                        setWare("spacefuel");
                        setId(faction.getFactionAbbreviation() + "_spacefuel");
                        setLocationInfo(new ProductLocation() {
                            {
                                setSpawnLocations(new ArrayList<Faction>() {
                                    {
                                        add(faction);
                                    }
                                });
                            }
                        });
                    }
                });
            }
        }
        // PARANID need maja snails, soja beans, soja husks
        if (faction.getRace().equals(Race.PARANID)) {
            products.add(new Product() {
                {
                    setOwner(faction);
                    setRace(faction.getRace());
                    setGalaxyQuota(Math.max((int) (6.0 / 7 * ownedSectorCount), 1));
                    setWare("majasnails");
                    setId(faction.getFactionAbbreviation() + "_majasnails");
                    setLocationInfo(new ProductLocation() {
                        {
                            setSpawnLocations(new ArrayList<Faction>() {
                                {
                                    add(faction);
                                }
                            });
                        }
                    });
                }
            });

            products.add(new Product() {
                {
                    setOwner(faction);
                    setRace(faction.getRace());
                    setGalaxyQuota(Math.max((int) (4.0 / 7 * ownedSectorCount), 1));
                    setWare("sojabeans");
                    setId(faction.getFactionAbbreviation() + "_sojabeans");
                    setLocationInfo(new ProductLocation() {
                        {
                            setSpawnLocations(new ArrayList<Faction>() {
                                {
                                    add(faction);
                                }
                            });
                        }
                    });
                }
            });

            products.add(new Product() {
                {
                    setOwner(faction);
                    setRace(faction.getRace());
                    setGalaxyQuota(Math.max(ownedSectorCount, 1));
                    setWare("sojahusk");
                    setId(faction.getFactionAbbreviation() + "_sojahusk");
                    setLocationInfo(new ProductLocation() {
                        {
                            setSpawnLocations(new ArrayList<Faction>() {
                                {
                                    add(faction);
                                }
                            });
                        }
                    });
                }
            });
        }
        // TELADI need nostrop oil, spaceweed, sunrise flowers, swamp plant, teladianium
        if (faction.getRace().equals(Race.TELADI)) {
            products.add(new Product() {
                {
                    setOwner(faction);
                    setRace(faction.getRace());
                    setGalaxyQuota(Math.max((int) (10.0 / 18 * ownedSectorCount), 1));
                    setWare("nostropoil");
                    setId(faction.getFactionAbbreviation() + "_nostropoil");
                    setLocationInfo(new ProductLocation() {
                        {
                            setSpawnLocations(new ArrayList<Faction>() {
                                {
                                    add(faction);
                                }
                            });
                        }
                    });
                }
            });

            products.add(new Product() {
                {
                    setOwner(faction);
                    setRace(faction.getRace());
                    setGalaxyQuota(Math.max((int) (7.0 / 18 * ownedSectorCount), 1));
                    setWare("spaceweed");
                    setId(faction.getFactionAbbreviation() + "_spaceweed");
                    setLocationInfo(new ProductLocation() {
                        {
                            setSpawnLocations(new ArrayList<Faction>() {
                                {
                                    add(faction);
                                }
                            });
                        }
                    });
                }
            });

            products.add(new Product() {
                {
                    setOwner(faction);
                    setRace(faction.getRace());
                    setGalaxyQuota(Math.max((int) (9.0 / 18 * ownedSectorCount), 1));
                    setWare("sunriseflowers");
                    setId(faction.getFactionAbbreviation() + "_sunriseflowers");
                    setLocationInfo(new ProductLocation() {
                        {
                            setSpawnLocations(new ArrayList<Faction>() {
                                {
                                    add(faction);
                                }
                            });
                        }
                    });
                }
            });

            products.add(new Product() {
                {
                    setOwner(faction);
                    setRace(faction.getRace());
                    setGalaxyQuota(Math.max((int) (7.0 / 18 * ownedSectorCount), 1));
                    setWare("swampplant");
                    setId(faction.getFactionAbbreviation() + "_swampplant");
                    setLocationInfo(new ProductLocation() {
                        {
                            setSpawnLocations(new ArrayList<Faction>() {
                                {
                                    add(faction);
                                }
                            });
                        }
                    });
                }
            });

            products.add(new Product() {
                {
                    setOwner(faction);
                    setRace(faction.getRace());
                    setGalaxyQuota(Math.max((int) (8.0 / 18 * ownedSectorCount), 1));
                    setWare("teladianium");
                    setId(faction.getFactionAbbreviation() + "_teladianium");
                    setLocationInfo(new ProductLocation() {
                        {
                            setSpawnLocations(new ArrayList<Faction>() {
                                {
                                    add(faction);
                                }
                            });
                            setWares(new ArrayList<String>() {
                                {
                                    add("ore");
                                }
                            });
                        }
                    });
                }
            });
        }
        // SPLIT need chelt meat, scruffin fruits
        if (faction.getRace().equals(Race.SPLIT)) {
            products.add(new Product() {
                {
                    setOwner(faction);
                    setRace(faction.getRace());
                    setGalaxyQuota(Math.max((int) (3.0 / 8 * ownedSectorCount), 1));
                    setWare("cheltmeat");
                    setId(faction.getFactionAbbreviation() + "_cheltmeat");
                    setLocationInfo(new ProductLocation() {
                        {
                            setSpawnLocations(new ArrayList<Faction>() {
                                {
                                    add(faction);
                                }
                            });
                        }
                    });
                }
            });

            products.add(new Product() {
                {
                    setOwner(faction);
                    setRace(faction.getRace());
                    setGalaxyQuota(Math.max((int) (7.0 / 18 * ownedSectorCount), 1));
                    setWare("scruffinfruits");
                    setId(faction.getFactionAbbreviation() + "_scruffinfruits");
                    setLocationInfo(new ProductLocation() {
                        {
                            setSpawnLocations(new ArrayList<Faction>() {
                                {
                                    add(faction);
                                }
                            });
                        }
                    });
                }
            });
        }
        // TERRAN
        if (faction.getRace().equals(Race.TERRAN)) {
            products.add(new Product() {
                {
                    setOwner(faction);
                    setRace(faction.getRace());
                    setGalaxyQuota(Math.max((int) (7.0 / 18 * ownedSectorCount), 1));
                    setWare("metallicmicrolattice");
                    setId(faction.getFactionAbbreviation() + "_metallicmicrolattice");
                    setLocationInfo(new ProductLocation() {
                        {
                            setSpawnLocations(new ArrayList<Faction>() {
                                {
                                    add(faction);
                                }
                            });
                        }
                    });
                }
            });

            products.add(new Product() {
                {
                    setOwner(faction);
                    setRace(faction.getRace());
                    setGalaxyQuota(Math.max((int) (7.0 / 13 * ownedSectorCount), 1));
                    setWare("computronicsubstrate");
                    setId(faction.getFactionAbbreviation() + "_computronicsubstrate");
                    setLocationInfo(new ProductLocation() {
                        {
                            setSpawnLocations(new ArrayList<Faction>() {
                                {
                                    add(faction);
                                }
                            });
                        }
                    });
                }
            });

            products.add(new Product() {
                {
                    setOwner(faction);
                    setRace(faction.getRace());
                    setGalaxyQuota(Math.max((int) (5.0 / 13 * ownedSectorCount), 1));
                    setWare("siliconcarbide");
                    setId(faction.getFactionAbbreviation() + "_siliconcarbide");
                    setLocationInfo(new ProductLocation() {
                        {
                            setSpawnLocations(new ArrayList<Faction>() {
                                {
                                    add(faction);
                                }
                            });
                        }
                    });
                }
            });

            products.add(new Product() {
                {
                    setOwner(faction);
                    setRace(faction.getRace());
                    setGalaxyQuota(Math.max((int) (64 / 13 * ownedSectorCount), 1));
                    setSectorQuota(8);
                    setWare("energycells");
                    setId(faction.getFactionAbbreviation() + "_energycells");
                    setLocationInfo(new ProductLocation() {
                        {
                            setSpawnLocations(new ArrayList<Faction>() {
                                {
                                    add(faction);
                                }
                            });
                            setSunlightMin(".4");
                            setSunlightMax("10");
                        }
                    });
                }
            });

            products.add(new Product() {
                {
                    setOwner(faction);
                    setRace(faction.getRace());
                    setGalaxyQuota(Math.max((int) (16 / 13 * ownedSectorCount), 1));
                    setWare("medicalsupplies");
                    setId(faction.getFactionAbbreviation() + "_medicalsupplies");
                    setLocationInfo(new ProductLocation() {
                        {
                            setSpawnLocations(new ArrayList<Faction>() {
                                {
                                    add(faction);
                                }
                            });
                        }
                    });
                }
            });

            products.add(new Product() {
                {
                    setOwner(faction);
                    setRace(faction.getRace());
                    setGalaxyQuota(Math.max((int) (4 / 13 * ownedSectorCount), 1));
                    setWare("proteinpaste");
                    setId(faction.getFactionAbbreviation() + "_proteinpaste");
                    setLocationInfo(new ProductLocation() {
                        {
                            setSpawnLocations(new ArrayList<Faction>() {
                                {
                                    add(faction);
                                }
                            });
                        }
                    });
                }
            });

            products.add(new Product() {
                {
                    setOwner(faction);
                    setRace(faction.getRace());
                    setGalaxyQuota(Math.max((int) (9 / 13 * ownedSectorCount), 1));
                    setWare("terranmre");
                    setId(faction.getFactionAbbreviation() + "_terranmre");
                    setLocationInfo(new ProductLocation() {
                        {
                            setSpawnLocations(new ArrayList<Faction>() {
                                {
                                    add(faction);
                                }
                            });
                        }
                    });
                }
            });

            products.add(new Product() {
                {
                    setOwner(faction);
                    setRace(faction.getRace());
                    setGalaxyQuota(Math.max((int) (2 / 13 * ownedSectorCount), 1));
                    setWare("stimulants");
                    setId(faction.getFactionAbbreviation() + "_stimulants");
                    setLocationInfo(new ProductLocation() {
                        {
                            setSpawnLocations(new ArrayList<Faction>() {
                                {
                                    add(faction);
                                }
                            });
                        }
                    });
                }
            });
        }
        // Xenon
        if (faction.getRace().equals(Race.XENON)) {
            products.add(new Product() {
                {
                    setOwner(faction);
                    setRace(faction.getRace());
                    setGalaxyQuota(Math.max((int) (64 / 13 * ownedSectorCount), 1));
                    setSectorQuota(8);
                    setWare("energycells");
                    setId(faction.getFactionAbbreviation() + "_energycells");
                    setLocationInfo(new ProductLocation() {
                        {
                            setSpawnLocations(new ArrayList<Faction>() {
                                {
                                    add(faction);
                                }
                            });
                            setSunlightMin(".4");
                            setSunlightMax("10");
                        }
                    });
                }
            });
        }

        return products;
    }
}
