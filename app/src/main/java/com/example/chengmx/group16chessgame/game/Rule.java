package com.example.chengmx.group16chessgame.game;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chengmx on 2016/11/12.
 */
public class Rule {

    private static final int M = 8;

    public static boolean isLegalMove(byte[][] chessBoard, Move move, byte chessColor) {
        int row = move.row, col = move.col;
        if (!isLegal(row, col) || chessBoard[row][col] != 0)
            return false;
        return true;
    }

    public static boolean isLegal(int row, int col) {
        return row >= 0 && row < 8 && col >= 0 && col < 8;
    }

    public static List<Move> move(byte[][] chessBoard, Move move, byte chessColor) {
        int row = move.row;
        int col = move.col;
        List<Move> moves = new ArrayList<Move>();
        moves.add(new Move(row, col));
        chessBoard[row][col] = chessColor;
        return moves;
    }

    public static boolean isEnded(byte[][] chessBoard, Move move, byte chessColor) {
        int row = move.row;
        int col = move.col;
        int flag = 0;

        if(row >= 5) {
            for (int i = 1; i < 5; i++) {
                if (chessBoard[row - i][col] != chessColor)
                    flag = 1;
            }
            if (flag == 0)
                return true;
        }

        if(col >= 5) {
            flag = 0;
            for (int i = 1; i < 5; i++) {
                if (chessBoard[row][col - i] != chessColor)
                    flag = 1;
            }
            if (flag == 0)
                return true;
        }

        if(row >= 5 && col <= M - 5) {
            flag = 0;
            for (int i = 1; i < 5; i++) {
                if (chessBoard[row - i][col + i] != chessColor)
                    flag = 1;
            }
            if (flag == 0)
                return true;
        }

        if(col >= 5 && row <= M - 5) {
            flag = 0;
            for (int i = 1; i < 5; i++) {
                if (chessBoard[row + i][col - i] != chessColor)
                    flag = 1;
            }
            if (flag == 0)
                return true;
        }

        if(col <= M - 5) {
            flag = 0;
            for (int i = 1; i < 5; i++) {
                if (chessBoard[row][col + i] != chessColor)
                    flag = 1;
            }
            if (flag == 0)
                return true;
        }

        if(row <= M - 5) {
            flag = 0;
            for (int i = 1; i < 5; i++) {
                if (chessBoard[row + i][col] != chessColor)
                    flag = 1;
            }
            if (flag == 0)
                return true;
        }

        if(row>=5 && col >=5) {
            flag = 0;
            for (int i = 1; i < 5; i++) {
                if (chessBoard[row - i][col - i] != chessColor)
                    flag = 1;
            }
            if (flag == 0)
                return true;
        }

        if(col <= M-5  && row <= M-5) {
                flag = 0;
            for(int i=1; i<5;i++){
                if(chessBoard[row+i][col+i] != chessColor)
                    flag = 1;
            }
            if (flag==0)
                return true;
        }

        return false;
    }
}
