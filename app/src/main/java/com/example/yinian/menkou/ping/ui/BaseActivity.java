package com.example.yinian.menkou.ping.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.yinian.menkou.ping.MyApplication;
import com.example.yinian.menkou.ping.beans.SaveInfo;
import com.example.yinian.menkou.ping.databinding.ActivityBaseBinding;
import com.tencent.mmkv.MMKV;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class BaseActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks{
    private ActivityBaseBinding baseBinding=null;
    final static int COUNTS = 6;// 点击次数
    final static long DURATION = 1000;// 规定有效时间
    long[] mHits = new long[COUNTS];
    private SaveInfo saveInfo=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseBinding=ActivityBaseBinding.inflate(getLayoutInflater());
        setContentView(baseBinding.getRoot());
        EventBus.getDefault().register(this);
        baseBinding.jigou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //每次点击时，数组向前移动一位
                System.arraycopy(mHits, 1, mHits, 0, mHits.length - 1);
                //为数组最后一位赋值
                mHits[mHits.length - 1] = SystemClock.uptimeMillis();
                if (mHits[0] >= (SystemClock.uptimeMillis() - DURATION)) {
                    mHits = new long[COUNTS];//重新初始化数组
                    startActivity(new Intent(BaseActivity.this, JiGouActivity.class));
                }
            }
        });
        baseBinding.loudong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BaseActivity.this, LouDongActivity.class));
            }
        });


        methodRequiresTwoPermission();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SaveInfo saveInfo=MMKV.defaultMMKV().decodeParcelable("save",SaveInfo.class);
        if (saveInfo.getJigou().equals("")){
            baseBinding.loudong.setEnabled(false);
        }else {
            baseBinding.loudong.setEnabled(true);
        }
        if (!saveInfo.getJigou().equals(""))
            baseBinding.jigou.setText(saveInfo.getJigou());
        Log.d("BaseActivity", "保存信息："+saveInfo.toString());
    }

    private final int RC_CAMERA_AND_LOCATION = 10000;
    @AfterPermissionGranted(RC_CAMERA_AND_LOCATION)
    private void methodRequiresTwoPermission() {
        String[] perms = {Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO,
                Manifest.permission.READ_PHONE_STATE, Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.WAKE_LOCK,
                Manifest.permission.BLUETOOTH, Manifest.permission.BLUETOOTH_ADMIN,
                Manifest.permission.ACCESS_WIFI_STATE};

        if (EasyPermissions.hasPermissions(this, perms)) {
            // 已经得到许可，就去做吧 //第一次授权成功也会走这个方法
            Log.d("LoginActivity", "成功获得权限");
            File file = new File(MyApplication.SDPATH1);
            Log.d("TAG", "methodRequiresTwoPermission: "+file.getAbsolutePath());
            if (!file.exists()) {
                Log.d("LoginActivity", "file.mkdirs():" + file.mkdirs());
            }
            File file2 = new File(MyApplication.SDPATH2);
            if (!file2.exists()) {
                Log.d("LoginActivity", "file.mkdirs():" + file2.mkdirs());
            }
            File file3 = new File(MyApplication.SDPATH3);
            if (!file3.exists()) {
                Log.d("LoginActivity", "file.mkdirs():" + file3.mkdirs());
            }



            SaveInfo saveInfo=MMKV.defaultMMKV().decodeParcelable("save",SaveInfo.class);
            if (!saveInfo.getJigou().equals("") && !saveInfo.getLoudong().equals("") && !saveInfo.getLouceng().equals("") && !saveInfo.getFangjian().equals("")){
                startActivity(new Intent(BaseActivity.this, InfoActivity.class));//跳转主界面
                finish();
            }

        } else {
            // Do not have permissions, request them now
            EasyPermissions.requestPermissions(this, "需要授予app权限,请点击确定",
                    RC_CAMERA_AND_LOCATION, perms);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> list) {
        // Some permissions have been granted
        Log.d("LoginActivity", "list.size():" + list.size());

    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> list) {
        // Some permissions have been denied
        // ...
        Log.d("LoginActivity", "list.size():" + list.size());
        for (String s : list) {
            Log.d("LoginActivity", "被拒绝的权限:"+s);
        }
        Toast.makeText(BaseActivity.this, "权限被拒绝无法正常使用app", Toast.LENGTH_LONG).show();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void wxMSG(String msgWarp) {
        if (msgWarp.equals("finish")){
            finish();
        }
    }
    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}