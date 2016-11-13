package com.example.chengmx.group16chessgame.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;

import com.example.chengmx.group16chessgame.R;

/**
 * Created by chengmx on 2016/11/12.
 */

public class GameRuleActivity  extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_rule);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            GameRuleActivity.this.finish();
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}