package bupt.com.travelandroid.Acvivity;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import bupt.com.travelandroid.Acvivity.CallBack.IICallBack;
import bupt.com.travelandroid.Acvivity.IView.IResiterView;
import bupt.com.travelandroid.Acvivity.Presenter.ResiterPresenter;
import bupt.com.travelandroid.Bean.UserBean;
import bupt.com.travelandroid.Bean.response.RegisterInterface;
import bupt.com.travelandroid.DesiginView.BackTitle;
import bupt.com.travelandroid.DesiginView.EditTextPlus;
import bupt.com.travelandroid.R;
import bupt.com.travelandroid.util.SnackUtils;

/**
 * Created by tuyin on 2018/5/18 0018.
 */

public class RegisterActivity extends BaseActivity {

    BackTitle backTitle;
    Button btNextStep;
    EditTextPlus etpPhone;
    EditTextPlus etpPassword;
    EditTextPlus etpAccount;
    RelativeLayout rlRoot;

    //过渡加载动画
    View dialogView;
    AlertDialog dialog;
    public ResiterPresenter registerPresenter = new ResiterPresenter(mContext);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Log.e("registerActivity","enter");
        initView();
        initData();
    }

    @Override
    public void initView() {
        super.initView();
        backTitle = (BackTitle) findViewById(R.id.bt_title);
        btNextStep = (Button) findViewById(R.id.bt_next_step);
        etpPhone = findViewById(R.id.etp_phone);
        etpPassword = findViewById(R.id.etp_password);
        etpPassword.setPasswordType();

        etpAccount = findViewById(R.id.etp_account);
        rlRoot = findViewById(R.id.rl_root);

      /*  autoScrllView();
*/
        registerPresenter.setResiterView(new IResiterView() {
            @Override
            public UserBean getUserBean() {
                UserBean user = new UserBean();
                user.setName(etpAccount.getContent());
                user.setPassword(etpPassword.getContent());
                user.setPhone(etpPhone.getContent());
                return user;
            }
        });
    }

  /*  private int scrollToPosition=0;
    public void autoScrllView(){
        rlRoot.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect rect = new Rect();

                //获取root在窗体的可视区域
                rlRoot.getWindowVisibleDisplayFrame(rect);

                //获取root在窗体的不可视区域高度(被遮挡的高度)
                int rootInvisibleHeight = rlRoot.getRootView().getHeight() - rect.bottom;

                //若不可视区域高度大于150，则键盘显示
                if (rootInvisibleHeight > 150) {

                    //获取scrollToView在窗体的坐标,location[0]为x坐标，location[1]为y坐标
                    int[] location = new int[2];
                    btNextStep.getLocationInWindow(location);

                    //计算root滚动高度，使scrollToView在可见区域的底部
                    int scrollHeight = (location[1] + btNextStep.getHeight()) - rect.bottom;

                    //注意，scrollHeight是一个相对移动距离，而scrollToPosition是一个绝对移动距离
                    scrollToPosition += scrollHeight;

                } else {
                    //键盘隐藏
                    scrollToPosition = 0;
                }
                rlRoot.scrollTo(0, scrollToPosition);

            }
        });
    }*/

    @Override
    public void initData() {
        //返回键设置返回动作
        backTitle.setLeftClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.finish();
            }
        });
        //进入验证码页面
        btNextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Log.e("registerActivity","test");
                mContext.startActivity(new Intent(mContext,RegisterMessageActivity.class));*/
                if(TextUtils.isEmpty(etpPhone.getContent())){
                    SnackUtils.showSnackShort(rlRoot,"请填写手机号");
                    return ;
                }
                if(TextUtils.isEmpty(etpPassword.getContent())){
                    SnackUtils.showSnackShort(rlRoot,"请填写密码");
                    return ;
                }
                if(TextUtils.isEmpty(etpAccount.getContent())){
                    SnackUtils.showSnackShort(rlRoot,"请填写昵称");
                    return ;
                }
               showDialog();
               registerPresenter.regiser(new IICallBack<RegisterInterface>() {
                   @Override
                   public void getData(RegisterInterface response) {
                       hideDialog();
                       //注册成功之后，跳转到登录页面
                       startActivity(new Intent(mContext, LoginActivity.class));
                       finish();
                       Toast.makeText(mContext,"注册成功",Toast.LENGTH_LONG).show();
                   }
                   @Override
                   public void error(String msg) {
                       SnackUtils.showSnackShort(rlRoot,msg);
                       hideDialog();
                   }
               });
            }
        });
    }


    //展示对话框
    public void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        dialogView = LayoutInflater.from(mContext).inflate(R.layout.loading, null);
        TextView tvNotice = (TextView) dialogView.findViewById(R.id.tv_notice);
        tvNotice.setText("注册中");

        builder.setView(dialogView);
        dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        //改变对话框的大小
        WindowManager.LayoutParams  lp= dialog.getWindow().getAttributes();
        lp.width=550;//定义宽度
        lp.height=450;//定义高度
        dialog.getWindow().setAttributes(lp);
    }
    public  void hideDialog(){
        if(dialog != null && dialog.isShowing()){
            dialog.dismiss();
        }
    }
}
