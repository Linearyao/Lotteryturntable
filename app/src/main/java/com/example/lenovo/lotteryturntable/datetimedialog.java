package com.example.lenovo.lotteryturntable;
import java.util.Calendar;

import android.app.Activity;
import android.app.ActionBar;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Fragment;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.os.Build;

public class datetimedialog extends Activity implements android.view.View.OnClickListener{

    private Button date_button;
    private Button time_button;
    private Button button;
    private Button button2;
    private ImageButton Ibtn2;
    private Calendar calendar;
    private TextView textView;
    private TextView textView2;
    private TextView tv_date;
    private TextView tv_time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settinginformation);
        date_button=(Button) findViewById(R.id.date_button);
        time_button=(Button) findViewById(R.id.time_button);
        button= (Button) findViewById(R.id.button);
        button2= (Button) findViewById(R.id.button2);
        Ibtn2= (ImageButton) findViewById(R.id.Ibtn2);
        Ibtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(datetimedialog.this,awards.class);
                startActivity(intent);
            }
        });
        textView= (TextView) findViewById(R.id.textView);
        textView2= (TextView) findViewById(R.id.textView2);
        tv_date=(TextView) findViewById(R.id.tv_date);
        tv_time=(TextView) findViewById(R.id.tv_time);
        button.setOnClickListener(this);
        button2.setOnClickListener(this);
        date_button.setOnClickListener(this);
        time_button.setOnClickListener(this);
        calendar=Calendar.getInstance();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private void showDateDialog1()
    {
        DatePickerDialog date_dialog=new DatePickerDialog(datetimedialog.this, new OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                tv_date.setText("起始日期"+year+"-"+(monthOfYear+1)+"-"+dayOfMonth);

            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        date_dialog.show();
    }

    private void showDateDialog2()
    {
        DatePickerDialog date_dialog=new DatePickerDialog(datetimedialog.this, new OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                textView.setText("截止日期"+year+"-"+(monthOfYear+1)+"-"+dayOfMonth);

            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        date_dialog.show();
    }
    private void showTimeDialog1()
    {
        final TimePickerDialog time_dialog=new TimePickerDialog(datetimedialog.this, new OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                // TODO Auto-generated method stub
                tv_time.setText("起始时间"+hourOfDay+":"+minute);
            }
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
        time_dialog.show();
    }
    private void showTimeDialog2()
    {
        TimePickerDialog time_dialog=new TimePickerDialog(datetimedialog.this, new OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                // TODO Auto-generated method stub
                textView2.setText("截止时间"+hourOfDay+":"+minute);
            }
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
        time_dialog.show();
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch(v.getId())
        {
            case R.id.date_button:
                showDateDialog1();
                break;
            case R.id.time_button:
                showTimeDialog1();
                break;
            case R.id.button:
                showDateDialog2();
                break;
            case R.id.button2:
                showTimeDialog2();
                break;
        }
    }
}
