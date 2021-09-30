package generator;

import model.Cluster;
import model.FactionHqLocation;
import model.FactionStart;
import model.Galaxy;

public class FactionLogicProcessor {

    public void processFactionLogicData(Galaxy galaxy, Cluster cluster){
        if(cluster.getFactionHq() != null){
            galaxy.addFactionHqLocation(new FactionHqLocation(cluster.getFactionHq(), cluster.getId()));
        }

        if(cluster.getFactionStart() != null){
            FactionStart factionStart = cluster.getFactionStart();
            factionStart.setClusterId(cluster.getId());
            galaxy.addFactionStart(factionStart);
        }
    }
}
