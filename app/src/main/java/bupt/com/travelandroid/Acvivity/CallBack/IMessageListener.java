package bupt.com.travelandroid.Acvivity.CallBack;

import java.util.List;

import bupt.com.travelandroid.Bean.IM.ImMessage;

public interface IMessageListener {
    public void messageArrival(List<ImMessage> message);
}
