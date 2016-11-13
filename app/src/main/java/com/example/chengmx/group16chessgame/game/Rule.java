package com.example.chengmx.group16chessgame.game;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chengmx on 2016/11/12.
 */
public class Rule {
    /**
     * 该步是否合法
     */
    public static boolean isLegalMove(byte[][] chessBoard, Move move, byte chessColor) {
        int row = move.row, col = move.col;
        if (!isLegal(row, col) || chessBoard[row][col] != 0)
            return false;
        return true;
    }

    public static boolean isLegal(int row, int col) {
        return row >= 0 && row < 8 && col >= 0 && col < 8;
    }

    /**
     * 使用前务必先确认该步合法
     */

    public static List<Move> move(byte[][] chessBoard, Move move, byte chessColor) {
        int row = move.row;
        int col = move.col;
        List<Move> moves = new ArrayList<Move>();
        moves.add(new Move(row, col));
        chessBoard[row][col] = chessColor;
        return moves;
    }
}
