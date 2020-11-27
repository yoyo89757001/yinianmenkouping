package com.example.yinian.menkou.ping.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.example.yinian.menkou.ping.MyApplication;
import com.example.yinian.menkou.ping.beans.BiudBean;
import com.example.yinian.menkou.ping.beans.LouDongBean;
import com.example.yinian.menkou.ping.beans.SaveInfo;
import com.example.yinian.menkou.ping.databinding.ActivityMainBinding;
import com.orhanobut.logger.Logger;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;
import com.tencent.mmkv.MMKV;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class LouDongActivity extends AppCompatActivity {
    private ActivityMainBinding binding=null;
    private ArrayList<BiudBean> options1Items = new ArrayList<>();//省
    private ArrayList<ArrayList<BiudBean>> options2Items = new ArrayList<>();//市
    private ArrayList<ArrayList<ArrayList<BiudBean>>> options3Items = new ArrayList<>();//区
    private SaveInfo saveInfo=null;
    private MediaType JSONTYPE  = MediaType.parse("application/json");
    private QMUITipDialog qmuiTipDialog = null;
    private List<LouDongBean.ResultDTO> louDongBeanA =new ArrayList<>();
    private OptionsPickerView pvOptions=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        EventBus.getDefault().register(this);

        binding.queding.setRadius(QMUIDisplayHelper.dp2px(this, 20));
        binding.queding.setChangeAlphaWhenPress(true);//点击改变透明度
        saveInfo= MMKV.defaultMMKV().decodeParcelable("save",SaveInfo.class);
        if (!saveInfo.getJigou().equals(""))
        binding.jigou.setText(saveInfo.getJigou());

        binding.ddcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSelete();
            }
        });
        binding.rrr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSelete();
            }
        });

        loginloudong(saveInfo.getJigouId());

    }


    private void showSelete(){
        /**
         * 注意 ：如果是三级联动的数据(省市区等)，请参照 JsonDataActivity 类里面的写法。
         */

        pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                binding.t1.setText(options1Items.get(options1).getPickerViewText());
                binding.t2.setText(options2Items.get(options1).get(options2).getPickerViewText()+"-"+options3Items.get(options1).get(options2).get(options3).getPickerViewText());

                  //      + options2Items.get(options1).get(options2)
                        /* + options3Items.get(options1).get(options2).get(options3).getPickerViewText()*/;

            }
        })
                .setTitleText("")
                .setContentTextSize(20)//设置滚轮文字大小
                .setDividerColor(Color.LTGRAY)//设置分割线的颜色
                .setSelectOptions(0, 1)//默认选中项
                .setBgColor(Color.parseColor("#ffffff"))
                .setTitleBgColor(Color.parseColor("#dd59B683"))
                .setTitleColor(Color.parseColor("#333333"))
                .setCancelColor(Color.WHITE)
                .setSubmitColor(Color.WHITE)
                .setTextColorCenter(Color.parseColor("#333333"))
                .isRestoreItem(true)//切换时是否还原，设置默认选中第一项。
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setLabels("", "", "")
                .setOutSideColor(0x55111111) //设置外部遮罩颜色
                .build();

//        pvOptions.setSelectOptions(1,1);
        /*pvOptions.setPicker(options1Items);//一级选择器*/
       // pvOptions.setPicker(options1Items, options2Items);//二级选择器
        pvOptions.setPicker(options1Items, options2Items,options3Items);//三级选择器*/
        pvOptions.show();
    }



    private void loginloudong(String did){ //机构楼栋
        qmuiTipDialog = new QMUITipDialog.Builder(LouDongActivity.this)
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                .setTipWord("加载中...")
                .create();
        qmuiTipDialog.show();
        String url = MyApplication.URL+ "/api/nurse/builListByOrgId";
        FormBody formBody = new FormBody.Builder()
                .add("orgId", did)
                .build();
        Request builder = new  Request.Builder()
                .addHeader("Content-Type", "application/json")
                .post(formBody).url(url).build();
        MyApplication.okHttpClient.newCall(builder).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                if (!LouDongActivity.this.isFinishing())
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (qmuiTipDialog != null)
                                qmuiTipDialog.dismiss();
                            Toast.makeText(LouDongActivity.this, "请求失败", Toast.LENGTH_LONG).show();
                        }
                    });
            }
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                try {
                    String stA = response.body().string();
                    Logger.d("查询机构楼栋:" + call.request().url()+stA);
                    LouDongBean healthBean = JSON.parseObject(stA, LouDongBean.class);
                    if (healthBean!=null && healthBean.isSuccess() && healthBean.getResult()!=null && healthBean.getResult().size()>0){
                        louDongBeanA.addAll(healthBean.getResult());
                        for (LouDongBean.ResultDTO resultDTO : louDongBeanA) {
                          //  Log.d("LouDongActivity", resultDTO.getBuildName());
                          //  Log.d("LouDongActivity", "resultDTO.getId():" + resultDTO.getId());
                            BiudBean biudBean=new BiudBean(resultDTO.getId()+"",resultDTO.getBuildName());
                            options1Items.add(biudBean);
                            ArrayList<BiudBean> options2 = new ArrayList<>();
                            ArrayList<ArrayList<BiudBean>> options2_2 = new ArrayList<>();
                            for (LouDongBean.ResultDTO.FloorsDTO floor : resultDTO.getFloors()) {
                                BiudBean biudBean2=new BiudBean(floor.getId()+"",floor.getFloorName());
                                options2.add(biudBean2);
                              //  Log.d("LouDongActivity", "floor.getId():" + floor.getId());
                               // Log.d("LouDongActivity", floor.getFloorName());
                                ArrayList<BiudBean> options3 = new ArrayList<>();
                                if (floor.getRoomList()==null || floor.getRoomList().size()==0){
                                   // Log.d("LouDongActivity", "加空");
                                    BiudBean biudBean3=new BiudBean("","");
                                    options3.add(biudBean3);
                                }else {
                                    for (LouDongBean.ResultDTO.FloorsDTO.RoomListDTO roomListDTO : floor.getRoomList()) {
                                       // Log.d("LouDongActivity", "roomListDTO.getId():" + roomListDTO.getId());
                                       // Log.d("LouDongActivity", roomListDTO.getRoomName());
                                        BiudBean biudBean3=new BiudBean(roomListDTO.getId()+"",roomListDTO.getRoomName());
                                        options3.add(biudBean3);
                                    }
                                }
                                options2_2.add(options3);
                                options3Items.add(options2_2);
                            }
                            options2Items.add(options2);
                        }
                    }else {
                        if (!LouDongActivity.this.isFinishing())
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(LouDongActivity.this, "没有该机构的楼栋数据", Toast.LENGTH_LONG).show();
                                }
                            });
                    }
                } catch (Exception e) {
                    Log.d("WGSettingActivity", e.getMessage()+"请求结果异常");
                }finally {
                    if (!LouDongActivity.this.isFinishing())
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


    private void loginlouceng(String did){ //机构楼栋
        String url = MyApplication.URL+ "/api/nurse/roomListByFloorId";
        JSONObject json =new  JSONObject();
        try {
            json.put("floorId", did);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Request builder = new  Request.Builder()
                .addHeader("Content-Type", "application/json")
                .post(RequestBody.create(json.toString(),JSONTYPE)).url(url).build();
        MyApplication.okHttpClient.newCall(builder).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                if (!LouDongActivity.this.isFinishing())
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (qmuiTipDialog != null)
                                qmuiTipDialog.dismiss();
                            Toast.makeText(LouDongActivity.this, "请求失败", Toast.LENGTH_LONG).show();
                        }
                    });
            }
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                try {
                    String stA = response.body().string();
                    Log.d("RenTiChuanGanQiActivity", "查询机构楼栋:" + call.request().url()+stA);
                    LouDongBean healthBean = JSON.parseObject(stA, LouDongBean.class);
                    if (healthBean!=null && healthBean.isSuccess() && healthBean.getResult()!=null){


                    }else {
                        if (!LouDongActivity.this.isFinishing())
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(LouDongActivity.this, "没有该机构的楼栋数据", Toast.LENGTH_LONG).show();
                                }
                            });
                    }

                } catch (Exception e) {
                    Log.d("WGSettingActivity", e.getMessage()+"请求结果异常");
                }finally {
                    if (!LouDongActivity.this.isFinishing())
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