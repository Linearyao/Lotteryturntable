package com.example.lenovo.lotteryturntable;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by lenovo on 2016/10/10.
 */
public class awardssetting extends Activity {
    private Button Abtn;
    private Button Bbtn;
    private Button Cbtn;
    private Button finishsetaward;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        Abtn= (Button) findViewById(R.id.allaward);
        Bbtn= (Button) findViewById(R.id.addaward);
        Cbtn= (Button) findViewById(R.id.change);
        finishsetaward= (Button) findViewById(R.id.finishsetaward);
        Abtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String[] items=new String[]{"一等奖","二等奖","三等奖","优秀奖"};
                AlertDialog.Builder builder=new AlertDialog.Builder(awardssetting.this);
                builder.setIcon(R.mipmap.ic_launcher);
                builder.setTitle("请选择奖项等级");
                //添加列表项
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(awardssetting.this,"您选择了"+items[i],Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setPositiveButton("确定",null);
                builder.create().show();
            }
        });
        Bbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String[] items=new String[]{"IPAD","IPHONE","相机","谢谢"};
                AlertDialog.Builder builder=new AlertDialog.Builder(awardssetting.this);
                builder.setIcon(R.mipmap.ic_launcher);
                builder.setTitle("请设置奖项名称");
                builder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(awardssetting.this,"您选择了"+items[i],Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setPositiveButton("确定",null);
                builder.create().show();
            }
        });
        Cbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String[] items=new String[]{"1","2","3","4","5","6"};
                AlertDialog.Builder builder=new AlertDialog.Builder(awardssetting.this);
                builder.setIcon(R.mipmap.ic_launcher);
                builder.setTitle("请设置奖项总量");
                builder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(awardssetting.this,"您选择了"+items[i],Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setPositiveButton("确定",null);
                builder.create().show();
            }
        });
        finishsetaward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(awardssetting.this,awards.class);
                startActivity(intent);
            }
        });
    }
}
