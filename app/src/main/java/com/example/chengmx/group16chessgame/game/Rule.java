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

        int i, j, dirx, diry, row = move.row, col = move.col;
        if (!isLegal(row, col) || chessBoard[row][col] != 0)
            return false;
        for (dirx = -1; dirx < 2; dirx++) {
            for (diry = -1; diry < 2; diry++) {
                if (dirx == 0 && diry == 0)
                    continue;
                int x = col + dirx, y = row + diry;
                if (isLegal(y, x) && chessBoard[y][x] == (-chessColor)) {
                    for (i = row + diry * 2, j = col + dirx * 2; isLegal(i, j); i += diry, j += dirx) {
                        if (chessBoard[i][j] == (-chessColor)) {
                            continue;
                        } else if (chessBoard[i][j] == chessColor) {
                            return true;
                        } else {
                            break;
                        }
                    }
                }
            }
        }
        return false;
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

    /*
    public static List<Move> move(byte[][] chessBoard, Move move, byte chessColor) {
        int row = move.row;
        int col = move.col;
         int i, j, temp, m, n, dirx, diry;
        List<Move> moves = new ArrayList<Move>();
        for (dirx = -1; dirx < 2; dirx++) {
            for (diry = -1; diry < 2; diry++) {
                if (dirx == 0 && diry == 0)
                    continue;
                temp = 0;
                int x = col + dirx, y = row + diry;
                if (isLegal(y, x) && chessBoard[y][x] == (-chessColor)) {
                    temp++;
                    for (i = row + diry * 2, j = col + dirx * 2; isLegal(i, j); i += diry, j += dirx) {
                        if (chessBoard[i][j] == (-chessColor)) {
                            temp++;
                            continue;
                        } else if (chessBoard[i][j] == chessColor) {
                            for (m = row + diry, n = col + dirx; m <= row + temp && m >= row - temp && n <= col + temp
                                    && n >= col - temp; m += diry, n += dirx) {
                                chessBoard[m][n] = chessColor;
                                moves.add(new Move(m, n));
                            }
                            break;
                        } else
                            break;
                    }
                }
            }
        }
        chessBoard[row][col] = chessColor;
        return moves;
    }
    */


}
