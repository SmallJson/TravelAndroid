package bupt.com.travelandroid.Bean.response;

import android.content.Intent;

import java.util.List;

public class PhotoInfoInterface {
    public String msg;

    public Integer code;

    public List<PhotoInfo> data;

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

    public List<PhotoInfo> getData() {
        return data;
    }

    public void setData(List<PhotoInfo> data) {
        this.data = data;
    }
}
