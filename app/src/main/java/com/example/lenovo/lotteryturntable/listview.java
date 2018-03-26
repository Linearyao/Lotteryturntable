package com.example.lenovo.lotteryturntable;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2016/10/11.
 */
public class listview extends Activity implements AdapterView.OnItemClickListener{

    private ListView listView;
    private ArrayAdapter<String>arr_adapter;
    private SimpleAdapter simpleAdapter;
    private String[] mStrs=new String[]{"单反相机","IPAD","恭喜发财","IPHONE","服装","恭喜发财"};
    private List<Map<String,Object>>dateList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainlist);
        listView=(ListView) findViewById(R.id.listView);
        String[]arr_date={"文件1","文件2","文件3","文件4"};
        arr_adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arr_date);
        dateList=new ArrayList<Map<String,Object>>();
        simpleAdapter =new SimpleAdapter(this,getDate(),R.layout.item,new String[]{"pic","text"},new int[]{R.id.pic,R.id.text});
        listView.setAdapter(simpleAdapter);
        listView.setOnItemClickListener(this);

    }
    private List<Map<String,Object>>getDate(){
        for (int i=0;i<6;i++)
        {
            Map<String,Object>map=new HashMap<String,Object>();
            map.put("pic",R.mipmap.ic_launcher);
            map.put(null,mStrs);
          /*  for (int j=0;j<6;++j){
                map.put("text", mStrs[j]);
            }*/
            dateList.add(map);
        }

        return dateList;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent=new Intent(listview.this,awardssetting.class);
        startActivity(intent);
    }

}
