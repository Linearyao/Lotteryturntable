package com.example.lenovo.lotteryturntable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * Created by lenovo on 2016/10/10.
 */
public class awards extends Activity {
    private Button but1;
    private Button but2;
    private Button but3;
    private ImageButton imageButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setawards);
        but1= (Button) findViewById(R.id.button3);
        but2= (Button) findViewById(R.id.button4);
        but3= (Button) findViewById(R.id.button5);
        imageButton= (ImageButton) findViewById(R.id.imageButton2);
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(awards.this,awardssetting.class);
                startActivity(intent);
            }
        });
        but3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(awards.this,listview.class);
                startActivity(intent);
            }
        });
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(awards.this,MainActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("flag","0");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
