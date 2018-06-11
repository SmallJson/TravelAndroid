package bupt.com.travelandroid.Bean.response;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Administrator on 2018/6/11 0011.
 */

public class RelationInterface {
    public String msg;
    public String code;
    ArrayList<RelationInfo> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ArrayList<RelationInfo> getData() {
        return data;
    }

    public void setData(ArrayList<RelationInfo> data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
