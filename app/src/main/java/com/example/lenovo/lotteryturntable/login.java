package com.example.lenovo.lotteryturntable;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.Toast;

/**
 * Created by lenovo on 2016/10/7.
 */

public class login extends Activity{
    private Button button;
    private ImageView imageView;
    private ImageButton btn1,imageButtom;
    private ImageButton Ibtn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        btn1= (ImageButton) findViewById(R.id.Ibtn1);
        imageButtom= (ImageButton) findViewById(R.id.imageButton);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number=((EditText)findViewById(R.id.editText2)).getText().toString();
                String pwd=((EditText)findViewById(R.id.editText)).getText().toString();
                boolean flag=false;
                String uname="";
                //通过遍历数据的形式判断输入的账号和密码是否正确
                for (int i=0;i<Data.USER.length;i++){
                    if (number.equals(Data.USER[i][0])){
                        if (pwd.equals(Data.USER[i][1])){
                            uname=Data.USER[i][2];
                            flag=true;
                            break;
                        }
                    }
                }
                if (flag){
                    Intent intent=new Intent(login.this,MainActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putString("uname",uname);
                    bundle.putString("flag","1");
                    intent.putExtras(bundle);
                    startActivity(intent);
                }else {
                    Toast.makeText(login.this,"您输入的账号或密码错误!",Toast.LENGTH_SHORT);
                }
            }
        });
        imageButtom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //finish();
            }
        });
    }
}
