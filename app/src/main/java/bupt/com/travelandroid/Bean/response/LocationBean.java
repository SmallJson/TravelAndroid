package bupt.com.travelandroid.Bean.response;

public class LocationBean {
    /**
     * lat : 39.88783523782654
     * lng : 116.41578469886984
     */

    private double lat;
    private double lng;

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    @Override
    public String toString() {
        return "LocationBean{" +
                "lat=" + lat +
                ", lng=" + lng +
                '}';
    }
}