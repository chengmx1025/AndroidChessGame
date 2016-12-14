package com.example.chengmx.group16chessgame.Service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Binder;
import android.os.IBinder;

import com.example.chengmx.group16chessgame.R;

/**
 * Created by chengmx on 2016/11/15.
 */

public class MyMusicService extends Service {

    private MediaPlayer mediaPlayer;
    private int pos = 0;

    /**
     * Usr binderService() to bind Service
     */
    @Override
    public IBinder onBind(Intent intent) {
        playMusic();
        return new MyBinder();

    }

    public class MyBinder extends Binder {
        public MyMusicService getMusicService() {
            return MyMusicService.this;
        }
    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        if (mediaPlayer == null) {
            mediaPlayer = mediaPlayer.create(MyMusicService.this,R.raw.bgm);
            mediaPlayer.setLooping(true);
        }
        mediaPlayer.setOnCompletionListener(new OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {
                mediaPlayer.release();

            }
        });
    }

    public void playMusic() {
        if (mediaPlayer != null && !mediaPlayer.isLooping()) {
            try {
                if (pos != 0) {
                    mediaPlayer.seekTo(pos);
                    mediaPlayer.start();
                } else {
                    mediaPlayer.stop();
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void stopMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }
}
