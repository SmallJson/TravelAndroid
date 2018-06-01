package bupt.com.travelandroid.Acvivity.Presenter;

import android.content.Context;

import java.util.Map;

import bupt.com.travelandroid.Acvivity.CallBack.IResultCallBack;
import bupt.com.travelandroid.Acvivity.IView.ILoginView;
import bupt.com.travelandroid.Acvivity.Model.AccountModel;
import retrofit2.Callback;

/**
 * Created by Administrator on 2018/5/31 0031.
 */

public class LoginPresenter {
    ILoginView loginView;
    Context context;
    AccountModel accountModel = new AccountModel();
    public LoginPresenter(Context context) {
        this.context = context;
    }

    public void setLoginView(ILoginView loginView) {
        this.loginView = loginView;
    }

    public  void login(IResultCallBack callBack){
        Map<String, String> map = loginView.getLogin();
        accountModel.selectAccountSyn(map.get("account"),map.get("password"), callBack);
    }
}
