package com.example.yinian.menkou.ping.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.yinian.menkou.ping.DateUtils;
import com.example.yinian.menkou.ping.MyApplication;
import com.example.yinian.menkou.ping.R;
import com.example.yinian.menkou.ping.beans.PepoleBean;
import com.example.yinian.menkou.ping.beans.RenBean;
import com.example.yinian.menkou.ping.beans.SaveInfo;
import com.example.yinian.menkou.ping.databinding.ActivityInfoBinding;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.orhanobut.logger.Logger;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;
import com.tencent.mmkv.MMKV;
import com.youth.banner.adapter.BannerAdapter;

import org.jetbrains.annotations.NotNull;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.Response;

public class InfoActivity extends AppCompatActivity {
    private ActivityInfoBinding binding=null;
    private int width=200,height=200;
    private List<RenBean> dangBeanList=new ArrayList<>();
    private QMUITipDialog qmuiTipDialog = null;
    private String id="";
    final static int COUNTS = 6;// 点击次数
    final static long DURATION = 1000;// 规定有效时间
    long[] mHits = new long[COUNTS];
    private SaveInfo saveInfo=null;
    private ImageAdapter adapter=null;
    private TimeChangeReceiver timeChangeReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        id=getIntent().getStringExtra("roomId");
        binding.riqi.setText(DateUtils.time1(System.currentTimeMillis()+""));
        binding.shijian.setText(DateUtils.ti(System.currentTimeMillis()+""));
        saveInfo= MMKV.defaultMMKV().decodeParcelable("save",SaveInfo.class);
        binding.gggh.setText(saveInfo.getJigou());
        binding.loudong.setText(saveInfo.getLoudong());
        binding.fangjian.setText(saveInfo.getFangjian());
        if (saveInfo.getQrCodeData()!=null)
        zxing(saveInfo.getQrCodeData());
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        binding.fangjian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //每次点击时，数组向前移动一位
                System.arraycopy(mHits, 1, mHits, 0, mHits.length - 1);
                //为数组最后一位赋值
                mHits[mHits.length - 1] = SystemClock.uptimeMillis();
                if (mHits[0] >= (SystemClock.uptimeMillis() - DURATION)) {
                    mHits = new long[COUNTS];//重新初始化数组
                    startActivity(new Intent(InfoActivity.this, LouDongActivity.class));
                }
            }
        });

        adapter=new ImageAdapter(dangBeanList);
        binding.banner.setAdapter(adapter);
        binding.shijian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginloudong(id);
            }
        });


        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_TIME_TICK);//每分钟变化
        intentFilter.addAction(Intent.ACTION_TIMEZONE_CHANGED);//设置了系统时区
        intentFilter.addAction(Intent.ACTION_TIME_CHANGED);//设置了系统时间
        timeChangeReceiver = new TimeChangeReceiver();
        registerReceiver(timeChangeReceiver, intentFilter);


        loginloudong(id);

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


    /**
     * 自定义布局，下面是常见的图片样式，更多实现可以看demo，可以自己随意发挥
     */
    public class ImageAdapter extends BannerAdapter<RenBean, ImageAdapter.BannerViewHolder> {

        public ImageAdapter(List<RenBean> mDatas) {
            //设置数据，也可以调用banner提供的方法,或者自己在adapter中实现
            super(mDatas);
        }

        //创建ViewHolder，可以用viewType这个字段来区分不同的ViewHolder
        @Override
        public BannerViewHolder onCreateHolder(ViewGroup parent, int viewType) {

            return new BannerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.aakkkc,parent,false));
        }

        @Override
        public void onBindView(BannerViewHolder holder, RenBean data, int position, int size) {
            holder.age.setText(data.getResult().get(0).getElderAge()+"岁 "+(data.getResult().get(0).getElderSex()==1?"男":"女"));
            holder.name.setText(data.getResult().get(0).getElderName()+"");
            holder.jiehu.setText(data.getResult().get(0).getNurseLevelName()+"");
            holder.hulizu.setText("护理组:"+data.getResult().get(0).getNurseGroupName());
            holder.bianhao.setText(data.getResult().get(0).getId()+"");
            holder.zhuzhi.setText("主治医生:"+data.getResult().get(0).getDoctorName());
            String timer=DateUtils.time(data.getResult().get(0).getCheckInTime()+"");
            holder.ruyuanshijian.setText("入院时间:"+DateUtils.time1(data.getResult().get(0).getCheckInTime()+""));
            @SuppressLint("SimpleDateFormat")
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date startTime = null;
            Date endTime = null;
            try {
                startTime = df.parse(DateUtils.time(System.currentTimeMillis()+""));
                endTime = df.parse(timer);
                long diff = startTime.getTime() - endTime.getTime();
                long days = diff / (1000 * 60 * 60 * 24);
                holder.ruyuandi.setText("入院第:"+days+"天");
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Glide.with(getApplicationContext())
                    .load(data.getResult().get(0).getElderImage())
                    .error(R.drawable.ooppp)
                    .apply(RequestOptions.bitmapTransform(new RoundedCorners( 6)))
                    .into(holder.imageView);
            if (data.getResult().size()>1){
                holder.age1.setText(data.getResult().get(1).getElderAge()+"岁 "+(data.getResult().get(1).getElderSex()==1?"男":"女"));
                holder.name1.setText(data.getResult().get(1).getElderName()+"");
                holder.jiehu1.setText(data.getResult().get(1).getNurseLevelName()+"");
                holder.hulizu1.setText("护理组:"+data.getResult().get(1).getNurseGroupName());
                holder.bianhao1.setText(data.getResult().get(1).getId()+"");
                holder.zhuzhi1.setText("主治医生:"+data.getResult().get(1).getDoctorName());
                String timer1=DateUtils.time(data.getResult().get(1).getCheckInTime()+"");
                holder.ruyuanshijian1.setText("入院时间:"+DateUtils.time1(data.getResult().get(1).getCheckInTime()+""));
                @SuppressLint("SimpleDateFormat")
                DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                Date startTime1 = null;
                Date endTime1 = null;
                try {
                    startTime1 = df1.parse(DateUtils.time(System.currentTimeMillis()+""));
                    endTime1 = df1.parse(timer1);
                    long diff1 = startTime1.getTime() - endTime1.getTime();
                    long days1 = diff1 / (1000 * 60 * 60 * 24);
                    holder.ruyuandi1.setText("入院第:"+days1+"天");
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Glide.with(getApplicationContext())
                        .load(data.getResult().get(1).getElderImage())
                        .error(R.drawable.ooppp)
                        .apply(RequestOptions.bitmapTransform(new RoundedCorners( 6)))
                        .into(holder.imageView1);
            }

        }

        class BannerViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;
            TextView name;
            TextView jiehu;
            TextView age;
            TextView zhuzhi;
            TextView hulizu;
            TextView ruyuandi;
            TextView ruyuanshijian;
            TextView bianhao;

            ImageView imageView1;
            TextView name1;
            TextView jiehu1;
            TextView age1;
            TextView zhuzhi1;
            TextView hulizu1;
            TextView ruyuandi1;
            TextView ruyuanshijian1;
            TextView bianhao1;

            public BannerViewHolder(@NonNull View view) {
                super(view);
                imageView=view.findViewById(R.id.touxiang);
                name=view.findViewById(R.id.name);
                jiehu=view.findViewById(R.id.jiehu);
                age=view.findViewById(R.id.age);
                zhuzhi=view.findViewById(R.id.zhuzhi);
                hulizu=view.findViewById(R.id.hulizu);
                ruyuandi=view.findViewById(R.id.ruyuandi);
                ruyuanshijian=view.findViewById(R.id.ruyuanshijian);
                bianhao=view.findViewById(R.id.bianhao);

                imageView1=view.findViewById(R.id.touxiang1);
                name1=view.findViewById(R.id.name1);
                jiehu1=view.findViewById(R.id.jiehu1);
                age1=view.findViewById(R.id.age1);
                zhuzhi1=view.findViewById(R.id.zhuzhi1);
                hulizu1=view.findViewById(R.id.hulizu1);
                ruyuandi1=view.findViewById(R.id.ruyuandi1);
                ruyuanshijian1=view.findViewById(R.id.ruyuanshijian1);
                bianhao1=view.findViewById(R.id.bianhao1);
            }
        }
    }


    private void loginloudong(String did){ //机构楼栋
        qmuiTipDialog = new QMUITipDialog.Builder(InfoActivity.this)
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                .setTipWord("加载中...")
                .create();
        qmuiTipDialog.show();
        String url = MyApplication.URL+ "/api/nurse/elderlyListByRoomId";
        FormBody formBody = new FormBody.Builder()
                .add("roomId", did)
                .build();
        Request builder = new  Request.Builder()
                .addHeader("Content-Type", "application/json")
                .post(formBody).url(url).build();
        MyApplication.okHttpClient.newCall(builder).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                if (!InfoActivity.this.isFinishing())
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (qmuiTipDialog != null)
                                qmuiTipDialog.dismiss();
                            Toast.makeText(InfoActivity.this, "请求失败", Toast.LENGTH_LONG).show();
                        }
                    });
            }
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                try {
                    String stA = response.body().string();
                    Logger.d("查询房间老人:" + call.request().url()+stA);
                    PepoleBean healthBean = JSON.parseObject(stA, PepoleBean.class);
                    if (healthBean!=null && healthBean.isSuccess() && healthBean.getResult()!=null && healthBean.getResult().size()>0){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Log.d("InfoActivity", "healthBean.getResult().size():" + healthBean.getResult().size());
                                binding.banner.setVisibility(View.VISIBLE);
                                binding.shuju.setVisibility(View.GONE);
                              List<List<PepoleBean.ResultDTO>> dddd=  DateUtils.spliceArrays(healthBean.getResult(),2);
                                dangBeanList.clear();
                                for (List<PepoleBean.ResultDTO> resultDTOS : dddd) {
                                    List<RenBean.ResultDTO> renBeans=new ArrayList<>();
                                    int i=0;
                                    for (PepoleBean.ResultDTO resultDTO : resultDTOS) {
                                        Log.d("InfoActivity", "resultDTO:" + resultDTO.getElderName());
                                        RenBean.ResultDTO bean=new RenBean.ResultDTO();
                                        bean.setElderName(resultDTO.getElderName());
                                        bean.setDoctorName(resultDTO.getDoctorName());
                                        bean.setNurseGroupName(resultDTO.getNurseGroupName());
                                        bean.setElderAge(resultDTO.getElderAge());
                                        bean.setElderSex(resultDTO.getElderSex());
                                        bean.setCreateTime(resultDTO.getCreateTime());
                                        bean.setCheckInTime(resultDTO.getCheckInTime());
                                        bean.setNurseLevelName(resultDTO.getNurseLevelName());
                                        //bean.setElderImage(resultDTO.getElderImage());
                                        if (i==0){
                                            bean.setElderImage("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1606647944241&di=079f8e332e60013b7509da6a427c3780&imgtype=0&src=http%3A%2F%2Fn.sinaimg.cn%2Fsinacn%2Fw640h914%2F20180211%2Fbb18-fyrkuxt4639327.jpg");
                                        }
                                        if (i==1){
                                            bean.setElderImage("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1606647944238&di=901448a231f4b0a00430e1db88110156&imgtype=0&src=http%3A%2F%2Fn.sinaimg.cn%2Ftranslate%2Fw391h561%2F20180226%2F6C1P-fyrwsqi2966178.jpg");
                                        }
                                        i++;
                                        bean.setId(resultDTO.getId());
                                        renBeans.add(bean);
                                    }
                                    RenBean renBean=new RenBean();
                                    renBean.setResult(renBeans);
                                    dangBeanList.add(renBean);

                                }
                                adapter.notifyDataSetChanged();
                            }
                        });

                    }else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                binding.banner.setVisibility(View.GONE);
                                binding.shuju.setVisibility(View.VISIBLE);
                            }
                        });
                    }
                } catch (Exception e) {
                    Log.d("WGSettingActivity", e.getMessage()+"请求结果异常");
                }finally {
                    if (!InfoActivity.this.isFinishing())
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (qmuiTipDialog != null)
                                    qmuiTipDialog.dismiss();
                            }
                        });
                }
            }
        });
    }


    class TimeChangeReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (Objects.requireNonNull(intent.getAction())) {
                case Intent.ACTION_TIME_TICK:
                    binding.riqi.setText(DateUtils.time1(System.currentTimeMillis()+""));
                    binding.shijian.setText(DateUtils.ti(System.currentTimeMillis()+""));
//                    if (xiaoshiss.split(":")[0].equals("03") && xiaoshiss.split(":")[1].equals("15")) {
//                        Log.d("TimeChangeReceiver", "ssss");
//                        DengUT.reboot();
//                    }
//                    //1分钟一次指令获取
//                    if (baoCunBean.getHoutaiDiZhi() != null && !baoCunBean.getHoutaiDiZhi().equals("") && paAccessControl!=null) {
//                        if (isGET){
//                            isGET=false;
//                            link_get_zhiling();
//                        }
//                    }
                    break;
                case Intent.ACTION_TIME_CHANGED:
                    //设置了系统时间
                    // Toast.makeText(context, "system time changed", Toast.LENGTH_SHORT).show();
                    break;
                case Intent.ACTION_TIMEZONE_CHANGED:
                    //设置了系统时区的action
                    //  Toast.makeText(context, "system time zone changed", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(timeChangeReceiver);
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        binding.zhuanshu.setText(saveInfo.getFangjian()+"房间专属码");
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        // super.onBackPressed();
    }
}