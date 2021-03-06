package model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Product extends AbstractJson {

    private String id;
    private String ware;
    private Integer galaxyQuota;
    private Integer zoneQuota;
    private Integer sectorQuota;
    private Integer clusterQuota;
    private Race race;
    private Faction owner;
    private ProductLocation locationInfo;

    public String getId() {
        return id;
    }

    public String getWare() {
        return ware;
    }

    public Integer getGalaxyQuota() {
        return galaxyQuota;
    }

    public Integer getZoneQuota() {
        return zoneQuota;
    }

    public Integer getSectorQuota() {
        return sectorQuota;
    }

    public Integer getClusterQuota() {
        return clusterQuota;
    }

    public Race getRace() {
        return race;
    }

    public Faction getOwner() {
        return owner;
    }

    public ProductLocation getLocationInfo() {
        return locationInfo;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setWare(String ware) {
        this.ware = ware;
    }

    public void setGalaxyQuota(Integer galaxyQuota) {
        this.galaxyQuota = galaxyQuota;
    }

    public void setZoneQuota(Integer zoneQuota) {
        this.zoneQuota = zoneQuota;
    }

    public void setSectorQuota(Integer sectorQuota) {
        this.sectorQuota = sectorQuota;
    }

    public void setClusterQuota(Integer clusterQuota) {
        this.clusterQuota = clusterQuota;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public void setOwner(Faction owner) {
        this.owner = owner;
    }

    public void setLocationInfo(ProductLocation locationInfo) {
        this.locationInfo = locationInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return new EqualsBuilder()
                .append(id, product.id)
                .append(ware, product.ware)
                .append(galaxyQuota, product.galaxyQuota)
                .append(zoneQuota, product.zoneQuota)
                .append(sectorQuota, product.sectorQuota)
                .append(clusterQuota, product.clusterQuota)
                .append(race, product.race)
                .append(owner, product.owner)
                .append(locationInfo, product.locationInfo)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(ware)
                .append(galaxyQuota)
                .append(zoneQuota)
                .append(sectorQuota)
                .append(clusterQuota)
                .append(race)
                .append(owner)
                .append(locationInfo)
                .toHashCode();
    }
}
