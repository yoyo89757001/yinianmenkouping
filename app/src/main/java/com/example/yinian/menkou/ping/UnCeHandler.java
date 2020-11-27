package com.example.yinian.menkou.ping;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.widget.Toast;

import com.example.yinian.menkou.ping.ui.InfoActivity;


public class UnCeHandler implements Thread.UncaughtExceptionHandler {

    private Thread.UncaughtExceptionHandler mDefaultHandler;
    public static final String TAG = "CatchExcep";
    MyApplication application;
    Context context;

    public UnCeHandler(MyApplication application, Context context){
        //获取系统默认的UncaughtException处理器
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        this.application = application;
        this.context=context;
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if(!handleException(ex) && mDefaultHandler != null){
            //如果用户没有处理则让系统默认的异常处理器来处理
            mDefaultHandler.uncaughtException(thread, ex);
        }else{

            Intent intent = new Intent(context.getApplicationContext(), InfoActivity.class);
            PendingIntent restartIntent = PendingIntent.getActivity(
                    context.getApplicationContext(), 0, intent,
                    PendingIntent.FLAG_CANCEL_CURRENT);
            //退出程序
            AlarmManager mgr = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
            mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100,
                    restartIntent); // 1秒钟后重启应用
            android.os.Process.killProcess(android.os.Process.myPid());
        }
    }




    /**
     * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成.
     *
     * @param ex
     * @return true:如果处理了该异常信息;否则返回false.
     */
    private boolean handleException(Throwable ex) {
        if (ex == null) {
            return false;
        } else {
            //使用Toast来显示异常信息
        new Thread(){
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(application.getApplicationContext(), "很抱歉,程序出现异常,即将退出.",
                        Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }.start();
            return true;
        }
    }
}