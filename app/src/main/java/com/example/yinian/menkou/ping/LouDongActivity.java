package com.example.yinian.menkou.ping;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.yinian.menkou.ping.databinding.ActivityMainBinding;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;

import java.util.ArrayList;

public class LouDongActivity extends AppCompatActivity {
    private ActivityMainBinding binding=null;
    private ArrayList<String> options1Items = new ArrayList<>();//省
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();//市
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();//区


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.queding.setRadius(QMUIDisplayHelper.dp2px(this, 20));
        binding.queding.setChangeAlphaWhenPress(true);//点击改变透明度



    }

}