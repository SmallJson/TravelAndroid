package bupt.com.travelandroid.util;

import android.content.pm.InstrumentationInfo;
import android.support.design.widget.Snackbar;
import android.view.View;

public class SnackUtils {

    public static  void showSnackShort(View view , String msg){
        Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show();
    }

    public static void showSnackLong(View view , String msg){
        Snackbar.make(view, msg, Snackbar.LENGTH_LONG).show();
    }
}
