package bupt.com.travelandroid.Bean.response;

import java.util.List;

import bupt.com.travelandroid.Bean.TravelTotalBean;

/**
 * Created by Administrator on 2018/6/12 0012.
 */

public class TravelInterface {
    public String msg;
    public  Integer code;
    private List<TravelTotalBean> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public List<TravelTotalBean> getData() {
        return data;
    }

    public void setData(List<TravelTotalBean> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "TravelInterface{" +
                "msg='" + msg + '\'' +
                ", code=" + code +
                ", data=" + data +
                '}';
    }
}
