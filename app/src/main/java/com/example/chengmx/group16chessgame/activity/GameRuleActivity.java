package com.example.chengmx.group16chessgame.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;

import com.example.chengmx.group16chessgame.R;

/**
 * CSIT 5510 (L1)
 * CHENG Mingxin, 20387442, mchengaa@connect.ust.hk
 * CHEN Kangle, 20403480, kchenam@connect.ust.hk
 * WANG Ziwei, 20402072, zwangcp@connect.ust.hk
 */

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
