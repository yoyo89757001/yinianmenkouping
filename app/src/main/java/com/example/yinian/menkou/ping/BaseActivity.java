package com.example.yinian.menkou.ping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;

import com.example.yinian.menkou.ping.databinding.ActivityBaseBinding;
import com.tencent.mmkv.MMKV;

public class BaseActivity extends AppCompatActivity {
    private ActivityBaseBinding baseBinding=null;
    final static int COUNTS = 6;// 点击次数
    final static long DURATION = 1000;// 规定有效时间
    long[] mHits = new long[COUNTS];



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseBinding=ActivityBaseBinding.inflate(getLayoutInflater());
        setContentView(baseBinding.getRoot());

        baseBinding.jigou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //每次点击时，数组向前移动一位
                System.arraycopy(mHits, 1, mHits, 0, mHits.length - 1);
                //为数组最后一位赋值
                mHits[mHits.length - 1] = SystemClock.uptimeMillis();
                if (mHits[0] >= (SystemClock.uptimeMillis() - DURATION)) {
                    mHits = new long[COUNTS];//重新初始化数组
                    startActivity(new Intent(BaseActivity.this,JiGouActivity.class));
                }
            }
        });
        baseBinding.loudong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BaseActivity.this,LouDongActivity.class));
            }
        });
        SaveInfo saveInfo=MMKV.defaultMMKV().decodeParcelable("save",SaveInfo.class);
        if (!saveInfo.getJigou().equals("") && !saveInfo.getLoudong().equals("") && !saveInfo.getLouceng().equals("") && !saveInfo.getFangjian().equals("")){
            startActivity(new Intent(BaseActivity.this,InfoActivity.class));
            finish();
        }
        startActivity(new Intent(BaseActivity.this,InfoActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        SaveInfo saveInfo=MMKV.defaultMMKV().decodeParcelable("save",SaveInfo.class);
        if (saveInfo.getJigou().equals("")){
          //  baseBinding.loudong.setEnabled(false);
        }else {
            baseBinding.loudong.setEnabled(true);
        }
    }
}