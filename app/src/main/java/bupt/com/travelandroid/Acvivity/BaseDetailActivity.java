package bupt.com.travelandroid.Acvivity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.storage.StorageManager;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.Serializable;
import java.lang.reflect.Method;

import bupt.com.travelandroid.Acvivity.CallBack.IICallBack;
import bupt.com.travelandroid.Acvivity.Presenter.ImagePresenter;
import bupt.com.travelandroid.R;
import bupt.com.travelandroid.util.BitmapUtil;
import bupt.com.travelandroid.util.ContanApplication;
import bupt.com.travelandroid.util.FileUtil;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by Administrator on 2018/5/29 0029.
 *交通/地点/酒店/饭店/备注等页面抽象的activity
 */
public abstract class BaseDetailActivity extends BaseActivity {
    ImageView ivBack;
    TextView tvTitle;
    Button btConfirm;

    //打开图库
    public final int REQUEST_PIC = 2;

    //从相册获取的图片Uri
    public Uri imageUri;

    //ImageModel;
    ImagePresenter imagePresenter = new ImagePresenter(mContext);


    public ImageView ivAdd;
    public ImageView ivCancel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initView() {
        super.initView();
        //统一设置标题栏功能
        ivBack = (ImageView) findViewById(R.id.iv_back);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                conveyData();
            }
        });

        //统一设置标题颜色
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitle.setTextColor(getResources().getColor(R.color.black));

        //统一设置回退按钮
        btConfirm = (Button) findViewById(R.id.bt_confirm);
        btConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                conveyData();
            }
        });

        //设置添加照片的点击事件
        ivAdd = findViewById(R.id.iv_add);
        if(ivAdd != null){
            ivAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    gotoImage();
                }
            });
        }

        //设置取消照片的点击事件
        ivCancel = findViewById(R.id.iv_cancle);
        if(ivCancel != null){
            ivCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickCancle();
                }
            });
        }
    }

    //子activity收集输入框内容
    public abstract Serializable getResultData();

    //覆盖android的后退键
    @Override
    public void onBackPressed() {
        conveyData();
    }

    private void conveyData(){
        if(BaseDetailActivity.this.getResultData()!=null){
            Intent intent = new Intent();
            intent.putExtra("result",BaseDetailActivity.this.getResultData());
            setResult(RESULT_OK,intent);
            finish();
        }else{
            //弹出消息提示框
            Snackbar.make(findViewById(R.id.ll_root),"信息不能为空",Snackbar.LENGTH_SHORT).show();
        }
    }


    //打开图库
    public void gotoImage(){

        if (Build.VERSION.SDK_INT >= 23) {
            int REQUEST_CODE_CONTACT = 101;
            String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
            //验证是否许可权限
            for (String str : permissions) {
                if (mContext.checkSelfPermission(str) != PackageManager.PERMISSION_GRANTED) {
                    //申请权限
                    mContext.requestPermissions(permissions, REQUEST_CODE_CONTACT);
                    return;
                }
            }
        }

        // 图库选择
        // 激活系统图库，选择一张图片
        Intent intent_gallery = new Intent(Intent.ACTION_PICK);
        intent_gallery.setType("image/*");
        startActivityForResult(intent_gallery, REQUEST_PIC);
    }

    //打开图库后的回调方法
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode != Activity.RESULT_OK || data == null || data.getData() == null){
            return;
        }
        if (requestCode == REQUEST_PIC) {//相册
            imageUri= data.getData();
            //0.通过Uri解析图片真正路径
            String[] filePathColumn = {MediaStore.Audio.Media.DATA};
            Cursor cursor = getContentResolver().query(imageUri, filePathColumn, null, null, null);
            cursor.moveToFirst();
            String photoPath = cursor.getString(cursor.getColumnIndex(filePathColumn[0]));

            //1.压缩图片
            Bitmap bitmap = BitmapUtil.compressBySize(photoPath);
            //2.构造压缩图片地址
            String extrenPath = "";
            try {
                StorageManager sm = (StorageManager) getSystemService(STORAGE_SERVICE);
                Method getVolumePathsMethod = StorageManager.class.getMethod("getVolumePaths", null);
                String[] paths = (String[]) getVolumePathsMethod.invoke(sm, null);
                extrenPath = paths[0];
            }catch (Exception e){
                e.printStackTrace();
                return;
            }

            File file = new File(photoPath);
            extrenPath = extrenPath +File.separator+ file.getName();
            //3.保存压缩图片到指定地址
            try {
                FileUtil.saveFile(bitmap, extrenPath);
            }catch (Exception e){
                e.printStackTrace();
                Log.e("pic","文件存储异常");
            }
            Log.e("extrenPath", extrenPath);
            Log.e("PicActivity",photoPath);
            //4.上传压缩图片
            cursor.close();
            upLoadMessage(extrenPath);
        }
    }

    //上传方法
    private void upLoadMessage(String imagePath){
        ContanApplication app  = (ContanApplication) getApplication();
        Integer uid = app.getUid();
        if(uid ==null){
            return;
        }
        //图片文件
        File file = new File(imagePath);
        Log.e("pic",file.getName());
        RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpeg"), file);
        /**
         *file部分，最终拼接成以下部分（注意“app_user_header”是后台定义好的，后台会用它作为key去查询你传的图片信息）：
         *Content-Disposition: form-data; name="app_user_header"; filename=fileNameByTimeStamp
         *Content-Type: image/jpeg
         *Content-Length: 52251(图片流字节数组的长度，底层的Okhttp帮我们计算了)
         *...(文件流)
         */
        MultipartBody.Part body = MultipartBody.Part.createFormData("image", file.getName(), requestFile);

        imagePresenter.uploadImage( body,new IICallBack<String>(){
            @Override
            public void getData(String response) {
                Log.e("server_sucess",response);
                upLoadSuccess(response);
            }

            @Override
            public void error(String msg) {
                Log.e("server_error",msg);
                uploadError(msg);
            }
        });
    }

    //上传照片成功后回调该方法
    protected void upLoadSuccess(String url){

    }

    //上传照片失败回调该方法
    protected  void uploadError(String msg){

    }

    //点击取消按钮操作
    protected  void clickCancle(){

    }
}
