package com.example.yinian.menkou.ping;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.yinian.menkou.ping.databinding.ActivityInfoBinding;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InfoActivity extends AppCompatActivity {
    private ActivityInfoBinding binding=null;
    private int width=200,height=200;
    private BianQianAdapter bianQianAdapter=null;
    private List<RenBean> dangBeanList=new ArrayList<>();
    private QMUITipDialog qmuiTipDialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.riqi.setText(DateUtils.time1(System.currentTimeMillis()+""));
        binding.shijian.setText(DateUtils.ti(System.currentTimeMillis()+""));

        zxing("sdfsdfsdfdsfsddfsdfjghjghjtertertertretretertertgdf");

        bianQianAdapter=new BianQianAdapter(R.layout.renbtgg,dangBeanList);
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        binding.recyclerview.setAdapter(bianQianAdapter);
        View view1= LayoutInflater.from(InfoActivity.this).inflate(R.layout.anull_data,null);
        bianQianAdapter.setEmptyView(view1);

        loginApiunBind();

    }

    private void zxing(String name){
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        Map<EncodeHintType, String> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8"); //记得要自定义长宽
        BitMatrix encode = null;
        try {
            encode = qrCodeWriter.encode(name, BarcodeFormat.QR_CODE, width, height, hints);
            int[] colors = new int[width * height];
            //利用for循环将要表示的信息写出来
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    if (encode.get(i, j)) {
                        colors[i * width + j] = Color.BLACK;
                    } else {
                        colors[i * width + j] = Color.WHITE;
                    }
                }
            }

            Bitmap bit = Bitmap.createBitmap(colors, width, height, Bitmap.Config.RGB_565);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    binding.erweima.setImageBitmap(bit);
                }
            });

        } catch (WriterException e) {
            e.printStackTrace();
        }

    }


    private static class BianQianAdapter extends BaseQuickAdapter<RenBean, BaseViewHolder> implements LoadMoreModule {


        public BianQianAdapter(int layoutResId, @Nullable List<RenBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(@NotNull BaseViewHolder baseViewHolder, RenBean taskBean) {
            try {


            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


    private void loginApiunBind(){
        qmuiTipDialog = new QMUITipDialog.Builder(this)
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                .setTipWord("加载中...")
                .create();
        qmuiTipDialog.show();

    }


}