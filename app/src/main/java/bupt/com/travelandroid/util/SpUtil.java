package bupt.com.travelandroid.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.content.SharedPreferencesCompat;

/**
 * Created by Administrator on 2018/6/1 0001.
 */

public class SpUtil {

    public static SharedPreferences sp ;

    public static final String FILE_NAME = "share_data";

    public static void putString(Context context ,String key, String name){
        if(sp == null){
            sp = context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE);
        }
        sp.edit().putString(key, name).commit();
    }

    public static String getString(Context context ,String key){
        if(sp == null){
            sp = context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE);
        }
        return sp.getString(key,"");
    }
}
