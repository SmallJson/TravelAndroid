package bupt.com.travelandroid.Bean.response;


import java.util.List;

public  class AttractionBean {
    /**
     * proId : 3
     * summary : 东方古老文明的写照。
     * cityId : 0
     * location : {"lon":"116.41704839","lat":"39.88651690"}
     * priceList : []
     * star : 5A
     * ct : 2015-08-24 17:07:42.353
     * areaId : 642
     * id : 4464
     * proName : 北京
     * areaName : 东城区
     * address : 北京市东城区天坛路天桥东侧
     * name : 天坛公园
     */

    private String proId;
    private String summary;
    private String cityId;
    private LocationBean location;
    private String star;
    private String ct;
    private String areaId;
    private String id;
    private String proName;
    private String areaName;
    private String address;
    private String name;

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public LocationBean getLocation() {
        return location;
    }

    public void setLocation(LocationBean location) {
        this.location = location;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getCt() {
        return ct;
    }

    public void setCt(String ct) {
        this.ct = ct;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

