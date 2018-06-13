package bupt.com.travelandroid.Acvivity.Presenter;

import android.content.Context;

import bupt.com.travelandroid.Acvivity.CallBack.IICallBack;
import bupt.com.travelandroid.Acvivity.IView.IResiterView;
import bupt.com.travelandroid.Acvivity.Model.AccountModel;
import bupt.com.travelandroid.Bean.response.RegisterInterface;

public class ResiterPresenter {

    public Context context;

    public AccountModel accountModel = new AccountModel();

    public IResiterView resiterView;

    public ResiterPresenter(Context context) {
        this.context = context;
    }

    public void setResiterView(IResiterView resiterView){
        this.resiterView = resiterView;
    }

    public void regiser(IICallBack<RegisterInterface> callBack){
        if(resiterView != null && resiterView.getUserBean() != null){
            accountModel.registerAccount(resiterView.getUserBean(),callBack);
        }
    }
}
