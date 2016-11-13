package com.example.chengmx.group16chessgame.game;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chengmx on 2016/11/12.
 */
public class Rule {

    private static final int M = 10;

    public static boolean isLegalMove(byte[][] chessBoard, Move move, byte chessColor) {
        int row = move.row, col = move.col;
        if (!isLegal(row, col) || chessBoard[row][col] != 0)
            return false;
        return true;
    }

    public static boolean isLegal(int row, int col) {
        return row >= 0 && row < M && col >= 0 && col < M;
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

        int count = 1;
        while (row>=1 && chessBoard[row-1][col]==chessColor){
            count++;
            row--;
        }
        row = move.row;
        col = move.col;
        while (row<M-1 && chessBoard[row+1][col]==chessColor){
            count++;
            row++;
        }
        if(count>=5)
            return true;

        count = 1;
        while (col>=1 && chessBoard[row][col-1]==chessColor){
            count++;
            col--;
        }
        row = move.row;
        col = move.col;
        while (col<M-1 && chessBoard[row][col+1]==chessColor){
            count++;
            col++;
        }
        if(count>=5)
            return true;

        count = 1;
        while (row>=1 && col>=1 && chessBoard[row-1][col-1]==chessColor){
            count++;
            row--;
            col--;
        }
        row = move.row;
        col = move.col;
        while (row<M-1 && col<M-1 && chessBoard[row+1][col+1]==chessColor){
            count++;
            row++;
            col++;
        }
        if(count>=5)
            return true;

        count = 1;
        while (row>=1 && col<M-1 && chessBoard[row-1][col+1]==chessColor){
            count++;
            row--;
            col++;
        }
        row = move.row;
        col = move.col;
        while (row<M-1 && col>=1 && chessBoard[row+1][col-1]==chessColor){
            count++;
            row++;
            col--;
        }
        if(count>=5)
            return true;

        return false;
    }
}
