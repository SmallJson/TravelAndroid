package bupt.com.travelandroid.Acvivity;

import android.Manifest;
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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.lang.reflect.Method;

import bupt.com.travelandroid.Acvivity.CallBack.IICallBack;
import bupt.com.travelandroid.Acvivity.Presenter.MessagePresenter;
import bupt.com.travelandroid.R;
import bupt.com.travelandroid.util.BitmapUtil;
import bupt.com.travelandroid.util.ContanApplication;
import bupt.com.travelandroid.util.FileUtil;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;



public class PictureActivity extends BaseActivity {

    //打开相机
    public final int REQUEST_CAMER = 1;
    //打开图库
    public final int REQUEST_PIC = 2;
    //裁剪图片
    public final int RESULT_REQUEST_CODE =3;

    MessagePresenter messagePresenter = new MessagePresenter(mContext);

    //从相册获取的图片Uri
    public Uri imageUri;
    //裁剪图片之后的图片路径
    public String FilePath;

    Button btCamer;
    Button btPic;
    ImageView ivPic;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic);
        initView();
        initData();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        super.initView();
        //打开本地图库
        btCamer = findViewById(R.id.bt_carmer);
        btCamer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        });

        btPic = findViewById(R.id.bt_pic);

        ivPic = findViewById(R.id.iv_pic);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
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
            sendImageMessage(extrenPath);
        }
    }

    private void sendImageMessage(String imagePath){
        ContanApplication app  = (ContanApplication) getApplication();
        Integer uid = app.getUid();
        if(uid ==null){
            return;
        }
        //fromUid
        RequestBody fromUid = RequestBody.create(null, uid+"");
        //目标电话
        RequestBody dest = RequestBody.create(null, "18435134579");
        //图片消息的type =1
        RequestBody type = RequestBody.create(null, "1");
        //关于图片的文字描述
        RequestBody text = RequestBody.create(null, "图片");
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

        messagePresenter.setMessage(fromUid, dest, type,text,null, body,new IICallBack<String>(){
            @Override
            public void getData(String response) {
                Log.e("server_sucess",response);
            }

            @Override
            public void error(String msg) {
                Log.e("server_error",msg);
            }
        });
    }
}
