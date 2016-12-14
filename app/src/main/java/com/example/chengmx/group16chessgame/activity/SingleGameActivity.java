package com.example.chengmx.group16chessgame.activity;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.example.chengmx.group16chessgame.R;
import com.example.chengmx.group16chessgame.game.DrawView;
import com.example.chengmx.group16chessgame.game.Move;
import com.example.chengmx.group16chessgame.game.Rule;
import com.example.chengmx.group16chessgame.widget.MessageDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chengmx on 2016/11/12.
 */

public class SingleGameActivity extends Activity {

    private static final byte NULL = 0;
    private static final byte BLACK = -1;
    private static final byte WHITE = 1;

    private static final int STATE_PLAYER_MOVE = 0;
    private static final int STATE_AI_MOVE = 1;
    private static final int STATE_GAME_OVER = 2;

    private DrawView drawView = null;
    private Button undoButton;
    private Button newGameButton;

    private byte playerColor = BLACK;
    private byte aiColor = WHITE;

    private static final int M = 10;

    private byte[][] chessBoard = new byte[M][M];
    private List<byte[][]> chessBoards = new ArrayList<byte[][]>();

    private int gameState;

    private MessageDialog msgDialog;

    private SoundPool soundPool;
    private int soundId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.single_game);
        drawView = (DrawView) findViewById(R.id.drawView);
        undoButton = (Button) findViewById(R.id.undoButton);
        newGameButton = (Button) findViewById(R.id.newGameButton);

        soundPool= new SoundPool(10, AudioManager.STREAM_SYSTEM,0);
        soundId = soundPool.load(this,R.raw.sound,1);

        initialChessboard();

        drawView.setOnTouchListener(new View.OnTouchListener() {

            boolean down = false;
            int downRow;
            int downCol;

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (gameState == STATE_GAME_OVER) {
                    return false;
                }

                float x = event.getX();
                float y = event.getY();
                if (!drawView.inChessBoard(x, y)) {
                    return false;
                }
                int row = drawView.getRow(y);
                int col = drawView.getCol(x);
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        down = true;
                        downRow = row;
                        downCol = col;
                        break;

                    case MotionEvent.ACTION_UP:
                        if (down && downRow == row && downCol == col) {
                            down = false;
                            if (!Rule.isLegalMove(chessBoard, new Move(row, col), playerColor)) {
                                return true;
                            }

                            Move move = new Move(row, col);
                            List<Move> moves = Rule.move(chessBoard, move, playerColor);
                            drawView.move(chessBoard, moves, move, playerColor);
                            soundPool.play(1,1, 1, 0, 0, 1);


                            //TODO
                            if(Rule.isEnded(chessBoard,move,playerColor)){
                                gameState = STATE_GAME_OVER;
                                gameOverMessage(playerColor);
                            }

                            if (playerColor == BLACK) {
                                playerColor = WHITE;
                            } else {
                                playerColor = BLACK;
                            }
                        }
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        down = false;
                        break;
                }

                return true;
            }
        });

        /*
        undoButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                chessBoard[newestPlayerX][newestPlayerY] = NULL;
                chessBoard[newestAIX][newestAIY] = NULL;
                drawView.updateChessBoard(chessBoard);
            }
        });
        */

        newGameButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                initialChessboard();
                drawView.initialChessBoard();
                gameState = STATE_PLAYER_MOVE;
            }
        });
    }

    private void initialChessboard(){
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                chessBoard[i][j] = NULL;
            }
        }
    }

    private void gameOverMessage(byte playerColor){
        String msg;
        if (playerColor==BLACK)
            msg = "BLACK WINS";
        else
            msg = "WHITE WINS";
        msgDialog = new MessageDialog(SingleGameActivity.this, msg);
        msgDialog.show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(SingleGameActivity.this, MainActivity.class);
            setResult(RESULT_CANCELED, intent);
            SingleGameActivity.this.finish();
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}


