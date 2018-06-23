package bupt.com.travelandroid.util;

import android.graphics.Bitmap;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class FileUtil {
    //存储进SD卡
    public static void saveFile(Bitmap bm, String fileName) throws Exception {
//        File dirFile = new File(fileName);
//        //检测图片是否存在
//        if (dirFile.exists()) {
//            dirFile.delete();  //删除原图片
//        }
        File myCaptureFile = new File(fileName);
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
        //100表示不进行压缩，70表示压缩率为30%
        bm.compress(Bitmap.CompressFormat.JPEG, 100, bos);
        bos.flush();
        bos.close();
    }
}
