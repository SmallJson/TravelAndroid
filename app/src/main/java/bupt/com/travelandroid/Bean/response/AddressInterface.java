package bupt.com.travelandroid.Bean.response;


import java.util.List;

public class AddressInterface {

    /**
     * status : 0
     * message : ok
     * result : [{"source":"baidu","location":{"lat":39.88783523782654,"lng":116.41578469886984},"bound":"39.880736,116.407118;39.894480,116.427018","formatted_address":"北京市东城区","address_components":{"province":"北京市","city":"北京市","district":"东城区","street":"","level":"旅游景点"},"precise":0.3}]
     */
    private int status;
    private String message;
    private List<LocationBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<LocationBean> getData() {
        return data;
    }

    public void setData(List<LocationBean> data) {
        this.data = data;
    }
}
