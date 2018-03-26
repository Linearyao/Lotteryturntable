package com.example.lenovo.lotteryturntable;

import android.app.Application;

/**
 * Created by lenovo on 2016/10/13.
 */
public class MyApplication extends Application {
    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public MyApplication() {
        super();
        uname = null;
    }

    private String uname;

}
