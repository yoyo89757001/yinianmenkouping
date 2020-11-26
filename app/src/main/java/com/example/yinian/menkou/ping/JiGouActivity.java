package com.example.yinian.menkou.ping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yinian.menkou.ping.databinding.ActivityJiGouBinding;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;
import com.qmuiteam.qmui.widget.popup.QMUIPopup;
import com.qmuiteam.qmui.widget.popup.QMUIPopups;

import java.util.ArrayList;
import java.util.List;

public class JiGouActivity extends AppCompatActivity {
    private ActivityJiGouBinding binding=null;
    private PopHeadBlackAdapter2 popHeadAdapterXM = null;
    private List<String> xiangmuList = new ArrayList<>();
    private QMUITipDialog qmuiTipDialog = null;
    private QMUIPopup popup = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityJiGouBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.queding.setRadius(QMUIDisplayHelper.dp2px(this, 20));
        binding.queding.setChangeAlphaWhenPress(true);//点击改变透明度

        xiangmuList.add("dadsada");
        xiangmuList.add("替换");
        xiangmuList.add("得到");
        xiangmuList.add("佛挡杀佛");
        xiangmuList.add("的非官方的");

        popHeadAdapterXM = new PopHeadBlackAdapter2(xiangmuList, JiGouActivity.this);

        binding.loudong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup=QMUIPopups.listPopup(JiGouActivity.this, QMUIDisplayHelper.dp2px(JiGouActivity.this, 220), QMUIDisplayHelper.dp2px(JiGouActivity.this, 180), popHeadAdapterXM, new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Log.d("LoginActivity", "position:" + position);
                        popup.dismiss();
                        binding.jigou.setText(xiangmuList.get(position));
                    }
                }).edgeProtection(QMUIDisplayHelper.dp2px(JiGouActivity.this, 10))
                        // .offsetX(QMUIDisplayHelper.dp2px(this, 20))
                        .offsetYIfBottom(QMUIDisplayHelper.dp2px(JiGouActivity.this, 6))
                        .shadow(true)
                        .arrow(true)
                        .bgColor(Color.parseColor("#ffffff"))
                        .animStyle(QMUIPopup.ANIM_GROW_FROM_CENTER)
                        .show(binding.rrr);
            }
        });
        binding.queding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.jigou.getText().toString().equals("未设置机构")){
                    Toast.makeText(JiGouActivity.this,"请先选择机构",Toast.LENGTH_SHORT).show();
                    return;
                }else {


                }
            }
        });

        qmuiTipDialog = new QMUITipDialog.Builder(JiGouActivity.this)
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                .setTipWord("加载机构中...")
                .create();
       // qmuiTipDialog.show();

    }


    public  class PopHeadBlackAdapter2 extends BaseAdapter {

        private List<String> mGroupNames;
        private LayoutInflater mLayoutInflater;


        public PopHeadBlackAdapter2(List<String> data, Context context) {
            mGroupNames=data;

        }


        @Override
        public int getCount() {
            return mGroupNames == null ? 0 : mGroupNames.size();
        }

        @Override
        public Object getItem(int position) {
            return mGroupNames == null ? null : mGroupNames.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if (mLayoutInflater == null) {
                mLayoutInflater = LayoutInflater.from(parent.getContext());
            }
            ViewHolder holder;
            if (convertView == null) {
                convertView = mLayoutInflater.inflate(R.layout.pophead_black_item, parent, false);
                holder = new ViewHolder();
                holder.groupNameTv =  convertView.findViewById(R.id.title);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.groupNameTv.setText(mGroupNames.get(position));

            return convertView;
        }


        public  class ViewHolder {
            TextView groupNameTv;
        }



    }


}