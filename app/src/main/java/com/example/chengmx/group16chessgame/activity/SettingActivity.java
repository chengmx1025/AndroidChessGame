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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.ToggleButton;

import com.example.chengmx.group16chessgame.R;
import com.example.chengmx.group16chessgame.Service.MyMusicService;
import com.example.chengmx.group16chessgame.Service.MyMusicService.MyBinder;
import com.example.chengmx.group16chessgame.data.Data;

/**
 * CSIT 5510 (L1)
 * CHENG Mingxin, 20387442, mchengaa@connect.ust.hk
 * CHEN Kangle, 20403480, kchenam@connect.ust.hk
 * WANG Ziwei, 20402072, zwangcp@connect.ust.hk
 */

/**
 * Created by chengmx on 2016/11/14.
 */

public class SettingActivity extends Activity {
    private ServiceConnection conn;
    private MyMusicService musicService;

    private ToggleButton musicToggleButton;
    private ToggleButton soundToggleButton;
    private Button saveButton;
    private Spinner spinner;


    private int if_music;
    private int if_sound;
    private int theme;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);
        if_music = 0;
        if_sound = 0;
        theme = 0;

        musicToggleButton = (ToggleButton)findViewById(R.id.toggleButton1);
        soundToggleButton = (ToggleButton)findViewById(R.id.toggleButton2);
        saveButton = (Button)findViewById(R.id.saveButton);
        spinner = (Spinner)findViewById(R.id.spinner);

        //loadSettings();
        conn = new Myconn();

        musicToggleButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
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
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                theme = pos;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (musicToggleButton.isChecked())
                    if_music = 1;
                else
                    if_music = 0;

                if (soundToggleButton.isChecked())
                    if_sound = 1;
                else
                    if_sound = 0;
                //saveSettings();
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

    public void loadSettings(){

        String[] out = Data.loadSetting(getApplicationContext());
        if_music = Integer.valueOf(out[0]);
        if_sound = Integer.valueOf(out[1]);
        theme = Integer.valueOf(out[2]);
    }

    public void saveSettings() {
        String text = Integer.toString(if_music) + "/" + Integer.toString(if_sound) + "/" + Integer.toString(theme);
        Data.saveSetting(getApplicationContext(), text);
    }
}