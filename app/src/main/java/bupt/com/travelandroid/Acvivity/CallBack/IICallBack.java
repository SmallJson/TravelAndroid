package bupt.com.travelandroid.Acvivity.CallBack;

import java.util.Map;

/**
 * Created by Administrator on 2018/6/11 0011.
 */

public interface IICallBack<T> {
    public void  getData(T response);
    public void  error(String msg);
}
