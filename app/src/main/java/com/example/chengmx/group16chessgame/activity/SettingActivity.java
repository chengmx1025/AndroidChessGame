package com.example.chengmx.group16chessgame.activity;

import android.os.Bundle;
import android.os.IBinder;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ToggleButton;

import com.example.chengmx.group16chessgame.R;
import com.example.chengmx.group16chessgame.Service.MyMusicService;
import com.example.chengmx.group16chessgame.Service.MyMusicService.MyBinder;


/**
 * Created by chengmx on 2016/11/14.
 */

public class SettingActivity extends Activity {
    private ServiceConnection conn;
    private MyMusicService musicService;
    private ToggleButton musicToggleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);
        conn = new Myconn();
        musicToggleButton = (ToggleButton)findViewById(R.id.toggleButton1);
        musicToggleButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (musicService == null) {
                        Intent intent = new Intent(SettingActivity.this,
                                MyMusicService.class);
                        bindService(intent, conn, Context.BIND_AUTO_CREATE);//绑定服务,后台也能播放
                    } else {
                        musicService.playMusic();
                    }
                /*
                //first-clicked
                if (musicToggleButton.isChecked()) {
                    if (musicService == null) {
                        Intent intent = new Intent(SettingActivity.this,
                                MyMusicService.class);
                        bindService(intent, conn, Context.BIND_AUTO_CREATE);//绑定服务,后台也能播放
                    } else {
                        musicService.playMusic();
                    }
                }
                //re-clicked
                else {
                    if (musicService != null) {
                        musicService.stopMusic();
                        musicService = null;
                        unbindService(conn);
                    }
                }
                */
            }
        });
    }

    public class Myconn implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyBinder myBinder = (MyBinder) service;
            musicService = myBinder.getMusicService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            conn = null;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            SettingActivity.this.finish();
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
