package com.example.yinian.menkou.ping;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.yinian.menkou.ping.databinding.ActivityMainBinding;

public class LouDongActivity extends AppCompatActivity {
    private ActivityMainBinding binding=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


    }

}