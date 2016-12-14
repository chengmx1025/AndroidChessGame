package com.example.chengmx.group16chessgame.data;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by Will on 2016-12-13.
 */

public class Data {

    private static String PROFILE_NAME = "profile.txt";
    private static String SETTING_NAME = "profile.txt";

    public static void saveProfile(Context fileContext, String profileContent) {
        FileOutputStream fos = null;
        try {
            fos = fileContext.openFileOutput(PROFILE_NAME, Context.MODE_PRIVATE);
            fos.write(profileContent.getBytes());
        }
        catch (java.io.FileNotFoundException e){
            return;
        }
        catch (java.io.IOException e){
            return;
        }
    }

    public static String[] loadProfile(Context fileContext){
        File file = new File(fileContext.getApplicationContext().getFilesDir().getAbsolutePath() + "/profile.txt");
        if(!file.exists()){
            saveProfile(fileContext, "USER/0/0/0/0");
        }
        try {
            FileInputStream fis = null;
            fis = fileContext.openFileInput(PROFILE_NAME);
            if (fis.available() == 0){
                return null;
            }
            byte[] readBytes = new byte[fis.available()];
            while(fis.read(readBytes) != -1){
            }
            String text = new String(readBytes);
            return text.split("/");
        }
        catch (java.io.IOException e){
            return null;
        }
    }

    public static void add_win_AI(Context fileContext){
        String[] out = loadProfile(fileContext);
        String ID = out[0];
        int record_AI_win = Integer.valueOf(out[1]);
        int record_AI_lose = Integer.valueOf(out[2]);
        int record_player_win = Integer.valueOf(out[3]);
        int record_player_lose = Integer.valueOf((out[4]));
        record_AI_win++;

        String text = ID + "/" + Integer.toString(record_AI_win) + "/" + Integer.toString(record_AI_lose) + "/" + Integer.toString(record_player_win) + "/" + Integer.toString(record_player_lose);
        saveProfile(fileContext, text);
    }

    public static void add_lose_AI(Context fileContext){
        String[] out = loadProfile(fileContext);
        String ID = out[0];
        int record_AI_win = Integer.valueOf(out[1]);
        int record_AI_lose = Integer.valueOf(out[2]);
        int record_player_win = Integer.valueOf(out[3]);
        int record_player_lose = Integer.valueOf((out[4]));
        record_AI_lose++;

        String text = ID + "/" + Integer.toString(record_AI_win) + "/" + Integer.toString(record_AI_lose) + "/" + Integer.toString(record_player_win) + "/" + Integer.toString(record_player_lose);
        saveProfile(fileContext, text);
    }

    public static void add_win_player(Context fileContext){
        String[] out = loadProfile(fileContext);
        String ID = out[0];
        int record_AI_win = Integer.valueOf(out[1]);
        int record_AI_lose = Integer.valueOf(out[2]);
        int record_player_win = Integer.valueOf(out[3]);
        int record_player_lose = Integer.valueOf((out[4]));
        record_player_win++;

        String text = ID + "/" + Integer.toString(record_AI_win) + "/" + Integer.toString(record_AI_lose) + "/" + Integer.toString(record_player_win) + "/" + Integer.toString(record_player_lose);
        saveProfile(fileContext, text);
    }

    public static void add_lose_player(Context fileContext){
        String[] out = loadProfile(fileContext);
        String ID = out[0];
        int record_AI_win = Integer.valueOf(out[1]);
        int record_AI_lose = Integer.valueOf(out[2]);
        int record_player_win = Integer.valueOf(out[3]);
        int record_player_lose = Integer.valueOf((out[4]));
        record_player_lose++;

        String text = ID + "/" + Integer.toString(record_AI_win) + "/" + Integer.toString(record_AI_lose) + "/" + Integer.toString(record_player_win) + "/" + Integer.toString(record_player_lose);
        saveProfile(fileContext, text);
    }

    public static void saveSetting(Context fileContext, String settingContent) {
        FileOutputStream fos = null;
        try {
            fos = fileContext.openFileOutput(SETTING_NAME, Context.MODE_PRIVATE);
            fos.write(settingContent.getBytes());
        }
        catch (java.io.FileNotFoundException e){
            return;
        }
        catch (java.io.IOException e){
            return;
        }
    }


    public static String[] loadSetting(Context fileContext){
        File file = new File(fileContext.getApplicationContext().getFilesDir().getAbsolutePath() + "/setting.txt");
        if(!file.exists()){
            saveProfile(fileContext, "0/0/9/0");
        }
        try {
            FileInputStream fis = null;
            fis = fileContext.openFileInput(SETTING_NAME);
            if (fis.available() == 0){
                return null;
            }
            byte[] readBytes = new byte[fis.available()];
            while(fis.read(readBytes) != -1){
            }
            String text = new String(readBytes);
            return text.split("/");
        }
        catch (java.io.IOException e){
            return null;
        }
    }

}
