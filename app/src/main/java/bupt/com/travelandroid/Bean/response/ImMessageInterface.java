package bupt.com.travelandroid.Bean.response;

import java.util.List;

import bupt.com.travelandroid.Bean.IM.ImMessage;

public class ImMessageInterface {
    public String msg;
    public Integer code;
    public List<ImMessage> data;

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

    public List<ImMessage> getData() {
        return data;
    }

    public void setData(List<ImMessage> data) {
        this.data = data;
    }
}
