package bupt.com.travelandroid.Bean.response;

import bupt.com.travelandroid.Bean.TravelTotalBean;

public class TravelIdInterface {
    public  int code;
    public String msg;
    public TravelTotalBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public TravelTotalBean getData() {
        return data;
    }

    public void setData(TravelTotalBean data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "TravelIdInterface{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
