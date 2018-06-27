package bupt.com.travelandroid.Bean.response;

public class AttractionInterface {
    public String msg;

    public Integer code;
    public AttractionBean data;

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

    public AttractionBean getData() {
        return data;
    }

    public void setData(AttractionBean data) {
        this.data = data;
    }
}
