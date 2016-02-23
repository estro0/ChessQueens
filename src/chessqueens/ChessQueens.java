package chessqueens;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class Gui {

    ChangePanel chng = new ChangePanel();
    int N = 8;

    JPanel[][] PanelBoard = new JPanel[8][8];
    int[][] board = new int[N][N];
    JFrame gui;
    JPanel p;
    JLabel l;
    int a = 0;
    int s = 80;

    ImageIcon image = new ImageIcon("src/image/ccc.png");

    void go() {

        gui = new JFrame();
        gui.setLayout(null);
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //    l.setSize(40, 40);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                p = new JPanel();
                p.setSize(s, s);
                //p.setSize(50 * (i + 1), 50 * (j + 1));
                p.setLocation(s * (i + 1), s * (j + 1));
                gui.add(p);

                p.setBackground(Color.white);
                if ((i + j) % 2 == 0) {
                    p.setBackground(Color.black);
                }
                PanelBoard[i][j] = p;

            }
        }

        gui.setBounds(700, 100, 800, 800);
        gui.setVisible(true);

        chng.solve(0, board, N);
    }

    //---------------------INSIDE-------------------------------
    class ChangePanel {

        boolean solve(int row, int[][] board, int N) {

            for (int i = 0; i < 8; i++) {

                for (int j = 0; j < 8; j++) {
                    l = new JLabel(image);
                    if (board[i][j] == 1 && !(l.getParent() == PanelBoard[i][j])) {
                       
                        l.setSize(80, 80);
                        // PanelBoard[i][j].setBackground(Color.red);
                        //PanelBoard[i][j].add(new JLabel(pic));
                        PanelBoard[i][j].add(l, BorderLayout.CENTER);
                    } else {
                        PanelBoard[i][j].removeAll();

                    }
                }
            }

            gui.repaint();

            if (row == N) {
                try {
                    a++;
                    Thread.sleep(3000);
                } catch (Exception ex) {
                }
            } else {
                try {
                    a++;
                    Thread.sleep(100);
                } catch (Exception ex) {
                }
            }
            System.out.println("asdasdasdd: " + a);

////    -----------------        
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board[i][j] == 1) {

                        System.out.print("Q ");
                    } else {
                        System.out.print("* ");
                    }
                }
                System.out.println();
            }
            //   ******************************************  
//            if (row >= N) {
//                return true;
//            }

            for (int position = 0; position < N; position++) {
                if (isValid(board, row, position, N)) {
                    board[row][position] = 1;

                    if (!solve(row + 1, board, N)) {
                        board[row][position] = 0;

                    } else {
                        return true;
                    }
                }

            }
            return false; //alapértelmezetten hamis a metódus
        }

        boolean isValid(int[][] board, int x, int y, int N) {
            int i, j;
            for (i = 0; i < x; i++) {
                if (board[i][y] == 1) {
                    return false;
                }
            }
            i = x - 1;
            j = y - 1;
            while ((i >= 0) && (j >= 0)) {
                if (board[i--][j--] == 1) {
                    return false;
                }
            }
            i = x - 1;
            j = y + 1;
            while ((i >= 0) && (j < N)) {
                if (board[i--][j++] == 1) {
                    return false;
                }
            }
            return true;
        }
    }

}

public class ChessQueens {

    public static void main(String[] args) {
        Gui gui = new Gui();
        gui.go();
    }

}