package com.example.lenovo.lotteryturntable;

import android.content.Intent;
import android.graphics.Canvas;
import android.icu.text.SimpleDateFormat;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.Format;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.SimpleFormatter;

public class MainActivity extends AppCompatActivity {
    private Pan pan;
    private ImageView mStartBtn;
    private Button log1;
    private static String uname;
    int count=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView= (TextView) findViewById(R.id.uname);
        uname = ((MyApplication)getApplication()).getUname();

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        String flag=bundle.getString("flag");
        if (flag.equals("1")) {
            uname=bundle.getString("uname");
            ((MyApplication)getApplication()).setUname(uname);
        }
        textView.setText("当前登录"+uname);
        Button button= (Button) findViewById(R.id.u_exit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //finish();
                uname = null;
                ((MyApplication)getApplication()).setUname(uname);
                Intent intent=new Intent(MainActivity.this,login.class);
                startActivity(intent);
            }
        });
        log1= (Button) findViewById(R.id.log1);
        log1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,datetimedialog.class);
                startActivity(intent);
            }
        });
        pan= (Pan) findViewById(R.id.id_Pan);
        mStartBtn= (ImageView) findViewById(R.id.id_start_btn);
        mStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count < 3) {
                    if (!pan.isStart()) {
                        pan.luckyStart();
                        mStartBtn.setImageResource(R.mipmap.stop);
                        count++;
                    } else {
                        if (!pan.isShouldEnd()) {
                            pan.luckyEnd();
                            mStartBtn.setImageResource(R.mipmap.start);
                            count++;
                        }
                    }
                }else {
                    Toast.makeText(MainActivity.this,"您已经抽奖",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
