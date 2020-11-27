package com.example.yinian.menkou.ping;


import android.app.Activity;
import android.app.Application;
import android.content.Context;


import androidx.multidex.MultiDex;


import com.example.yinian.menkou.ping.beans.SaveInfo;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.tencent.bugly.Bugly;
import com.tencent.mmkv.MMKV;


import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import me.jessyan.autosize.AutoSizeConfig;
import okhttp3.OkHttpClient;


/**
 * Created by Administrator on 2018/8/3.
 */

public class MyApplication extends Application {

    ArrayList<Activity> list = new ArrayList<Activity>();
    public static MyApplication myApplication;
    public static final String URL ="http://172.17.8.32:8082";


    public static String SDPATH1 = null;
    public static String SDPATH2 = null;
    public static String SDPATH3 = null;
    public static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .writeTimeout(20000, TimeUnit.MILLISECONDS)
            .connectTimeout(20000, TimeUnit.MILLISECONDS)
            .readTimeout(20000, TimeUnit.MILLISECONDS)
            //.cookieJar(new CookiesManager())
            //.retryOnConnectionFailure(true)
            .build();



    public void init(){
        //设置该CrashHandler为程序的默认处理器
        UnCeHandler catchExcep = new UnCeHandler(this,this);
        Thread.setDefaultUncaughtExceptionHandler(catchExcep);
    }






    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }



    @Override
    public void onCreate() {
        super.onCreate();
        myApplication=this;

        Bugly.init(this, "577f4cf317", false);
      //  BleManager.getInstance().init(this);
        Logger.addLogAdapter(new AndroidLogAdapter());
        String rootDir = MMKV.initialize(this);
        System.out.println("mmkv root: " + rootDir);

        SDPATH1 = getExternalFilesDir(null)+ File.separator+"yinian1";
        SDPATH2 = getExternalFilesDir(null)+ File.separator+"yinian2";
        SDPATH3 = getExternalFilesDir(null)+ File.separator+"yinian3";

        // Application中配置(设置宽适配)
        AutoSizeConfig.getInstance().setExcludeFontScale(true).setBaseOnWidth(true);

       // init();
        SaveInfo saveIn= MMKV.defaultMMKV().decodeParcelable("save",SaveInfo.class);
        if (saveIn==null){
            SaveInfo saveInfo=new SaveInfo();
            saveInfo.setFangjian("");
            saveInfo.setFangjianId("");
            saveInfo.setJigou("");
            saveInfo.setJigouId("");
            saveInfo.setLouceng("");
            saveInfo.setLoucengId("");
            saveInfo.setLoudong("");
            saveInfo.setLoudongId("");
            MMKV.defaultMMKV().encode("save",saveInfo);
        }
    }




}
