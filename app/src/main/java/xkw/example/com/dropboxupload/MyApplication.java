package xkw.example.com.dropboxupload;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {

    private static final String ACCESS_TOKEN =
            "BV7nR2vAGaAAAAAAAAAADnPkzzLGklSjZF3cxY-u-YUQn7XK_KhKOV1DTRnif2vp";

    private static final String APP_KEY = "0jjjzbiowhjazdf";

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        MyApplication.context = getApplicationContext();
    }

    public static Context getContext(){
        return MyApplication.context;
    }

    public static String getAccessToken() {
        return ACCESS_TOKEN;
    }

    public static String getAppKey() {
        return APP_KEY;
    }
}
