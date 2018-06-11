package bupt.com.travelandroid.Acvivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import org.angmarch.views.NiceSpinner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;

import bupt.com.travelandroid.Acvivity.CallBack.IResultCallBack;
import bupt.com.travelandroid.Acvivity.IView.IaddParentView;
import bupt.com.travelandroid.Acvivity.Presenter.AddParentPresenter;
import bupt.com.travelandroid.R;
import bupt.com.travelandroid.util.ContanApplication;
import bupt.com.travelandroid.util.SpUtil;

/**
 * Created by Administrator on 2018/6/11 0011.
 */
//添加亲属的页面
public class AddRelationActivity extends  BaseActivity {


    NiceSpinner spinnerMe;

    NiceSpinner spinnerDest;

    Integer childPosition = -1;
    Integer parentPosition = -1;

    Button btConfrim;

    ImageView imageView;

    EditText etPhone;

    AddParentPresenter addParentPresenter;

    LinearLayout llRoot;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_parent);
        initView();
        initData();
    }

    @Override
    public void initView() {
        super.initView();
        llRoot = (LinearLayout)findViewById(R.id.ll_root);
        spinnerMe = (NiceSpinner) findViewById(R.id.sp_me);
        spinnerMe.attachDataSource(new LinkedList<String>(Arrays.asList("儿子","女儿")));
        spinnerMe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerDest = (NiceSpinner) findViewById(R.id.sp_dest);
        spinnerDest.attachDataSource(new LinkedList<String>(Arrays.asList("父亲","母亲")));
        spinnerMe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    childPosition = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerDest.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    parentPosition = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        etPhone = (EditText) findViewById(R.id.et_phone);


        //2. 点击确认事件
        btConfrim = (Button) findViewById(R.id.bt_confirm);
        btConfrim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addParentPresenter.addParent(new IResultCallBack() {
                    @Override
                    public void getData(Map<String, Object> response) {
                        //成功提示
                        Snackbar.make(findViewById(R.id.ll_root),(String)response.get("msg"),Snackbar.LENGTH_SHORT).show();
                    }

                    @Override
                    public void error(String msg) {
                        //失败提示
                        Snackbar.make(findViewById(R.id.rl_root),msg,Snackbar.LENGTH_SHORT).show();
                    }

                });
            }
        });

        //3.取消按钮
        ImageView imageView = findViewById(R.id.iv_cancle);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void initData() {
        addParentPresenter = new AddParentPresenter(mContext);
        addParentPresenter.setAddParentView(new IaddParentView() {
            @Override
            public Map<String, Object> getRelation() {
                Map<String, Object> map = new HashMap<String, Object>();
                //0.获取Uid
                ContanApplication app = (ContanApplication) getApplication();
                Integer uid = app.getUid();
                map.put("uid", uid);
                //1. 获取对方手机号码
                String phone  = etPhone.getText().toString();
                map.put("destPhone", phone);

                //2.获取选择的输入
                int relationType = (childPosition  + parentPosition *2) <=1 ? 1 : 2 ;
                map.put("relationType", relationType);
   /*             switch (type){
                    case 0:
                        //我是儿子，选择父亲
                        relationType = 1;
                        break;
                    case 1:
                        //我是女儿，选择父亲
                        relationType = 1;
                        break;
                    case 2:
                        //我是儿子，选择母亲
                        relationType = 2;
                        break;
                    case 3:
                        relationType = 2;
                        //我是女儿，选择母亲
                        break;
                    default:
                        break;
                }*/
                return map;
            }
        });
    }

}
