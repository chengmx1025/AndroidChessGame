package com.example.chengmx.group16chessgame.game;

import android.util.Log;

import java.util.List;
import com.example.chengmx.group16chessgame.game.Move;
import java.util.Random;

/**
 * Created by chengmx on 2016/11/14.
 */

public class AI {
    private int[][] valueTable = {
    {0,0,0,0,0,0,0,0,0,0},
    {0,1,1,1,1,1,1,1,1,0},
    {0,1,2,2,2,2,2,2,1,0},
    {0,1,2,3,3,3,3,2,1,0},
    {0,1,2,3,4,4,3,2,1,0},
    {0,1,2,3,4,4,3,2,1,0},
    {0,1,2,3,3,3,3,2,1,0},
    {0,1,2,2,2,2,2,2,1,0},
    {0,1,1,1,1,1,1,1,1,0},
    {0,0,0,0,0,0,0,0,0,0}};

    public Move getAINextMove(byte[][] chessBoard, int chessColor) {
        int row = 0;
        int col = 0;
        int max = -1;
        for (int i=0; i<10; i++) {
            for (int j = 0; j < 10; j++) {
                if(chessBoard[i][j]==1 || chessBoard[i][j]==-1){
                    this.valueTable[i][j] = -1;
                }
                else{
                    //1
                    if(i>=1 && chessBoard[i-1][j]==-1)
                        valueTable[i][j] += 10;
                    if(i>=1 && chessBoard[i-1][j]==1)
                        valueTable[i][j] += 8;
                    if(j>=1 && chessBoard[i][j-1]==-1)
                        valueTable[i][j] += 10;
                    if(j>=1 && chessBoard[i][j-1]==1)
                        valueTable[i][j] += 8;
                    if(i<=8 && chessBoard[i+1][j]==-1)
                        valueTable[i][j] += 10;
                    if(i<=8 && chessBoard[i+1][j]==1)
                        valueTable[i][j] += 8;
                    if(j<=8 && chessBoard[i][j+1]==-1)
                        valueTable[i][j] += 10;
                    if(j<=8 && chessBoard[i][j+1]==1)
                        valueTable[i][j] += 8;

                    //2
                    if(i>=2 && chessBoard[i-1][j]==-1 && chessBoard[i-2][j]==-1)
                        valueTable[i][j] += 300;
                    if(i>=2 && chessBoard[i-1][j]==1 && chessBoard[i-2][j]==1)
                        valueTable[i][j] += 200;
                    if(j>=2 && chessBoard[i][j-1]==-1 && chessBoard[i][j-2]==-1)
                        valueTable[i][j] += 300;
                    if(j>=2 && chessBoard[i][j-1]==1 && chessBoard[i][j-2]==1)
                        valueTable[i][j] += 200;
                    if(i<=7 && chessBoard[i+1][j]==-1 && chessBoard[i+2][j]==-1)
                        valueTable[i][j] += 300;
                    if(i<=7 && chessBoard[i+1][j]==1 && chessBoard[i+2][j]==1)
                        valueTable[i][j] += 200;
                    if(j<=7 && chessBoard[i][j+1]==-1 && chessBoard[i][j+2]==-1)
                        valueTable[i][j] += 300;
                    if(j<=7 && chessBoard[i][j+1]==1 && chessBoard[i][j+2]==1)
                        valueTable[i][j] += 200;

                    if(i>=2 && j>=2 && chessBoard[i-1][j-1]==-1 && chessBoard[i-2][j-2]==-1)
                        valueTable[i][j] += 300;
                    if(i>=2 && j>=2 && chessBoard[i-1][j-1]==1 && chessBoard[i-2][j-2]==1)
                        valueTable[i][j] += 200;
                    if(i>=2 && j<=7 && chessBoard[i-1][j+1]==-1 && chessBoard[i-2][j+2]==-1)
                        valueTable[i][j] += 300;
                    if(i>=2 && j<=7 && chessBoard[i-1][j+1]==1 && chessBoard[i-2][j+2]==1)
                        valueTable[i][j] += 200;
                    if(i<=7 && j>=2 && chessBoard[i+1][j-1]==-1 && chessBoard[i+2][j-2]==-1)
                        valueTable[i][j] += 300;
                    if(i<=7 && j>=2 && chessBoard[i+1][j-1]==1 && chessBoard[i+2][j-2]==1)
                        valueTable[i][j] += 200;
                    if(i<=7 && j<=7 && chessBoard[i+1][j+1]==-1 && chessBoard[i+2][j+2]==-1)
                        valueTable[i][j] += 300;
                    if(i<=7 && j<=7 && chessBoard[i+1][j+1]==1 && chessBoard[i+2][j+2]==1)
                        valueTable[i][j] += 200;

                    //3
                    if(i>=3 && chessBoard[i-1][j]==-1 && chessBoard[i-2][j]==-1 && chessBoard[i-3][j]==-1)
                        valueTable[i][j] += 3000;
                    if(i>=3 && chessBoard[i-1][j]==1 && chessBoard[i-2][j]==1 && chessBoard[i-3][j]==1)
                        valueTable[i][j] += 2000;
                    if(j>=3 && chessBoard[i][j-1]==-1 && chessBoard[i][j-2]==-1 && chessBoard[i][j-3]==-1)
                        valueTable[i][j] += 3000;
                    if(j>=3 && chessBoard[i][j-1]==1 && chessBoard[i][j-2]==1 && chessBoard[i][j-3]==1)
                        valueTable[i][j] += 2000;
                    if(i<=6 && chessBoard[i+1][j]==-1 && chessBoard[i+2][j]==-1 && chessBoard[i+3][j]==-1)
                        valueTable[i][j] += 3000;
                    if(i<=6 && chessBoard[i+1][j]==1 && chessBoard[i+2][j]==1 && chessBoard[i+3][j]==1)
                        valueTable[i][j] += 2000;
                    if(j<=6 && chessBoard[i][j+1]==-1 && chessBoard[i][j+2]==-1 && chessBoard[i][j+3]==-1)
                        valueTable[i][j] += 3000;
                    if(j<=6 && chessBoard[i][j+1]==1 && chessBoard[i][j+2]==1 && chessBoard[i][j+3]==1)
                        valueTable[i][j] += 2000;

                    if(i>=3 && j>=3 && chessBoard[i-1][j-1]==-1 && chessBoard[i-2][j-2]==-1 && chessBoard[i-3][j-3]==-1)
                        valueTable[i][j] += 3000;
                    if(i>=3 && j>=3 && chessBoard[i-1][j-1]==1 && chessBoard[i-2][j-2]==1 && chessBoard[i-3][j-3]==1)
                        valueTable[i][j] += 2000;
                    if(i>=3 && j<=6 && chessBoard[i-1][j+1]==-1 && chessBoard[i-2][j+2]==-1 && chessBoard[i-3][j+3]==-1)
                        valueTable[i][j] += 3000;
                    if(i>=3 && j<=6 && chessBoard[i-1][j+1]==1 && chessBoard[i-2][j+2]==1 && chessBoard[i-3][j+3]==1)
                        valueTable[i][j] += 2000;
                    if(i<=6 && j>=3 && chessBoard[i+1][j-1]==-1 && chessBoard[i+2][j-2]==-1 && chessBoard[i+3][j-3]==-1)
                        valueTable[i][j] += 3000;
                    if(i<=6 && j>=3 && chessBoard[i+1][j-1]==1 && chessBoard[i+2][j-2]==1 && chessBoard[i+3][j-3]==1)
                        valueTable[i][j] += 2000;
                    if(i<=6 && j<=6 && chessBoard[i+1][j+1]==-1 && chessBoard[i+2][j+2]==-1 && chessBoard[i+3][j+3]==-1)
                        valueTable[i][j] += 3000;
                    if(i<=6 && j<=6 && chessBoard[i+1][j+1]==1 && chessBoard[i+2][j+2]==1 && chessBoard[i+3][j+3]==1)
                        valueTable[i][j] += 2000;


                    //4
                    if(i>=4 && chessBoard[i-1][j]==-1 && chessBoard[i-2][j]==-1 && chessBoard[i-3][j]==-1 && chessBoard[i-4][j]==-1)
                        valueTable[i][j] += 30000;
                    if(i>=4 && chessBoard[i-1][j]==1 && chessBoard[i-2][j]==1 && chessBoard[i-3][j]==1 && chessBoard[i-4][j]==1)
                        valueTable[i][j] += 20000;
                    if(j>=4 && chessBoard[i][j-1]==-1 && chessBoard[i][j-2]==-1 && chessBoard[i][j-3]==-1 && chessBoard[i][j-4]==-1)
                        valueTable[i][j] += 30000;
                    if(j>=4 && chessBoard[i][j-1]==1 && chessBoard[i][j-2]==1 && chessBoard[i][j-3]==1 && chessBoard[i][j-4]==1)
                        valueTable[i][j] += 20000;
                    if(i<=5 && chessBoard[i+1][j]==-1 && chessBoard[i+2][j]==-1 && chessBoard[i+3][j]==-1 && chessBoard[i+4][j]==-1)
                        valueTable[i][j] += 30000;
                    if(i<=5 && chessBoard[i+1][j]==1 && chessBoard[i+2][j]==1 && chessBoard[i+3][j]==1 && chessBoard[i+4][j]==1)
                        valueTable[i][j] += 20000;
                    if(j<=5 && chessBoard[i][j+1]==-1 && chessBoard[i][j+2]==-1 && chessBoard[i][j+3]==-1 && chessBoard[i][j+4]==-1)
                        valueTable[i][j] += 30000;
                    if(j<=5 && chessBoard[i][j+1]==1 && chessBoard[i][j+2]==1 && chessBoard[i][j+3]==1 && chessBoard[i][j+4]==1)
                        valueTable[i][j] += 20000;

                    if(i>=4 && j>=4 && chessBoard[i-1][j-1]==-1 && chessBoard[i-2][j-2]==-1 && chessBoard[i-3][j-3]==-1 && chessBoard[i-4][j-4]==-1)
                        valueTable[i][j] += 30000;
                    if(i>=4 && j>=4 && chessBoard[i-1][j-1]==1 && chessBoard[i-2][j-2]==1 && chessBoard[i-3][j-3]==1 && chessBoard[i-4][j-4]==1)
                        valueTable[i][j] += 20000;
                    if(i>=4 && j<=6 && chessBoard[i-1][j+1]==-1 && chessBoard[i-2][j+2]==-1 && chessBoard[i-3][j+3]==-1 && chessBoard[i-4][j+4]==-1)
                        valueTable[i][j] += 30000;
                    if(i>=4 && j<=6 && chessBoard[i-1][j+1]==1 && chessBoard[i-2][j+2]==1 && chessBoard[i-3][j+3]==1 && chessBoard[i-4][j+4]==1)
                        valueTable[i][j] += 20000;
                    if(i<=5 && j>=4 && chessBoard[i+1][j-1]==-1 && chessBoard[i+2][j-2]==-1 && chessBoard[i+3][j-3]==-1 && chessBoard[i+4][j-4]==-1)
                        valueTable[i][j] += 30000;
                    if(i<=5 && j>=4 && chessBoard[i+1][j-1]==1 && chessBoard[i+2][j-2]==1 && chessBoard[i+3][j-3]==1 && chessBoard[i+4][j-4]==1)
                        valueTable[i][j] += 20000;
                    if(i<=5 && j<=5 && chessBoard[i+1][j+1]==-1 && chessBoard[i+2][j+2]==-1 && chessBoard[i+3][j+3]==-1 && chessBoard[i+4][j+4]==-1)
                        valueTable[i][j] += 30000;
                    if(i<=5 && j<=5 && chessBoard[i+1][j+1]==1 && chessBoard[i+2][j+2]==1 && chessBoard[i+3][j+3]==1 && chessBoard[i+4][j+4]==1)
                        valueTable[i][j] += 20000;

                }
            }
        }

        for (int i=0; i<10; i++) {
            String abc = "";
            for (int j = 0; j < 10; j++) {

                if (valueTable[i][j] > max) {
                    row = i;
                    col = j;
                    max = valueTable[i][j];
                }
                abc += String.valueOf(valueTable[i][j]) + " ";
            }
            Log.d("MyAndroid", abc);
        }
        Log.d("MyAndroid", "=========================");

        Move move = new Move(row,col);
        return move;
    }

    public static Move getAINextMove0(byte[][] chessBoard, int chessColor) {
        Random random = new Random();
        int col,row;
        col = random.nextInt(10);
        row = random.nextInt(10);
        while(chessBoard[col][row]!=0){
            col = random.nextInt(10);
            row = random.nextInt(10);
        }
        Move move = new Move(col,row);
        return move;
    }
}
