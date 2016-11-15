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
    private Button MusicButton;
    private ToggleButton musicToggleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);
        conn = new Myconn();//建立服务连接对象

        musicToggleButton = (ToggleButton)findViewById(R.id.toggleButton1);
        /*
        if(musicService != null) {
            musicToggleButton.setChecked(true);
        }
        */
        musicToggleButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // 当按钮第一次被点击时候响应的事件
                if (musicToggleButton.isChecked()) {
                    if (musicService == null) {
                        Intent intent = new Intent(SettingActivity.this,
                                MyMusicService.class);
                        bindService(intent, conn, Context.BIND_AUTO_CREATE);//绑定服务,后台也能播放
                    } else {
                        musicService.playMusic();
                    }
                }
                // 当按钮再次被点击时候响应的事件
                else {
                    if (musicService != null) {
                        musicService.stopMusic();
                        musicService = null;
                        unbindService(conn);
                    }
                }
            }
        });

        /*
        MusicButton = (Button)findViewById(R.id.musicButton);
        MusicButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (musicService == null) {
                    Intent intent = new Intent(SettingActivity.this,
                            MyMusicService.class);
                    bindService(intent, conn, Context.BIND_AUTO_CREATE);//绑定服务,后台也能播放
                } else {
                    musicService.playMusic();
                }

            }
        });
        */
    }

    /*
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.musicButton:// 启动音乐
                if (musicService == null) {
                    Intent intent = new Intent(SettingActivity.this,
                            MyMusicService.class);
                    bindService(intent, conn, Context.BIND_AUTO_CREATE);//绑定服务,后台也能播放
                } else {
                    musicService.playMusic();
                }
                break;

            case R.id.toggleButton1:// 关闭音乐
                if (musicService != null) {
                    musicService.stopMusic();
                    musicService = null;
                    unbindService(conn);
                }
                break;

            default:
                break;
        }
    }
    */

    /**
     * 服务连接
     */
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

    /*
    togglebutton.setOnClickListener(new OnClickListener() {
        public void onClick(View v) {
            // 当按钮第一次被点击时候响应的事件
            if (togglebutton.isChecked()) {
                Toast.makeText(MainActivity.this, "你喜欢球类运动", Toast.LENGTH_SHORT).show();
            }
            // 当按钮再次被点击时候响应的事件
            else {
                Toast.makeText(MainActivity.this, "你不喜欢球类运动", Toast.LENGTH_SHORT).show();
            }
        }
    });
    */
}
