package com.example.chengmx.group16chessgame.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.chengmx.group16chessgame.R;
import com.example.chengmx.group16chessgame.data.Data;

import java.io.FileOutputStream;

/**
 * CSIT 5510 (L1)
 * CHENG Mingxin, 20387442, mchengaa@connect.ust.hk
 * CHEN Kangle, 20403480, kchenam@connect.ust.hk
 * WANG Ziwei, 20402072, zwangcp@connect.ust.hk
 */

/**
 * Created by Will on 2016-11-15.
 */

public class ProfileActivity extends Activity {

    private String ID = "name";
    private int record_AI_win = 0;
    private int record_AI_lose = 0;
    private int record_player_win = 0;
    private int record_player_lose = 0;
    //private final String FILE_NAME = "profile.txt";
    private FileOutputStream fos = null;
    private EditText text_ID;
    private TextView text_record_AI;
    private TextView text_record_player;
    private Button button_name;
    private Button button_reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        loadProfile();

        text_ID = (EditText) this.findViewById(R.id.text_ID);
        text_record_AI = (TextView) this.findViewById(R.id.text_record_AI);
        text_record_player = (TextView) this.findViewById(R.id.text_record_player);
        button_name = (Button) this.findViewById(R.id.button_name);
        button_reset = (Button) this.findViewById(R.id.button_reset);

        text_ID.setText(ID);
        text_ID.setEnabled(true);
        text_ID.setInputType(InputType.TYPE_CLASS_TEXT);
        text_ID.setSelection(text_ID.getText().length());
        saveProfile(ID, record_AI_win, record_AI_lose, record_player_win, record_player_lose);

        text_record_AI.setText(record_AI_win + "/" + record_AI_lose);
        text_record_player.setText(record_player_win + "/" + record_player_lose);

        button_name.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ID = text_ID.getText().toString();
                saveProfile(ID, record_AI_win, record_AI_lose, record_player_win, record_player_lose);
            }
        });
        button_reset.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                record_AI_win = 0;
                record_AI_lose = 0;
                record_player_win = 0;
                record_player_lose = 0;
                text_record_AI.setText(record_AI_win + "/" + record_AI_lose);
                text_record_player.setText(record_player_win + "/" + record_player_lose);
                saveProfile(ID, record_AI_win, record_AI_lose, record_player_win, record_player_lose);
            }
        });
    }

    public void saveProfile(String ID, int record_AI_win, int record_AI_lose, int record_player_win, int record_player_lose) {
        String text = ID + "/" + Integer.toString(record_AI_win) + "/" + Integer.toString(record_AI_lose) + "/" + Integer.toString(record_player_win) + "/" + Integer.toString(record_player_lose);
        Data.saveProfile(getApplicationContext(), text);
    }

    public void loadProfile(){
        String[] out = Data.loadProfile(getApplicationContext());
        this.ID = out[0];
        this.record_AI_win = Integer.valueOf(out[1]);
        this.record_AI_lose = Integer.valueOf(out[2]);
        this.record_player_win = Integer.valueOf(out[3]);
        this.record_player_lose = Integer.valueOf((out[4]));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            ProfileActivity.this.finish();
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
