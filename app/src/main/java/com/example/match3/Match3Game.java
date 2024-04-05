package com.example.match3;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

public class
Match3Game extends AppCompatActivity {

    Button[][] buttons;
    Button reset;

    private boolean compare (String x, String y, String z) {
        return (x.equals(y) && x.equals(z));
    }

    private boolean checkX (Button[][] buttons) {
        for (int i = 0; i < 5; i++) {
            if (    compare(buttons[i][0].getText().toString(),buttons[i][1].getText().toString(),buttons[i][2].getText().toString()) ||
                    compare(buttons[i][1].getText().toString(),buttons[i][2].getText().toString(),buttons[i][3].getText().toString()) ||
                    compare(buttons[i][2].getText().toString(),buttons[i][3].getText().toString(),buttons[i][4].getText().toString())
            ) {
                return true;
            }
        }
        return false;
    }

    private boolean checkY (Button[][] buttons) {
        for (int i = 0; i < 5; i++) {
            if (    compare(buttons[0][i].getText().toString(),buttons[1][i].getText().toString(),buttons[2][i].getText().toString()) ||
                    compare(buttons[1][i].getText().toString(),buttons[2][i].getText().toString(),buttons[3][i].getText().toString()) ||
                    compare(buttons[2][i].getText().toString(),buttons[3][i].getText().toString(),buttons[4][i].getText().toString())
            ) {
                return true;
            }
        }
        return false;
    }

    private void isAdjacent (int[] prev, int[] curr) {
        int x1 = prev[0];
        int y1 = prev[1];
        int x2 = curr[0];
        int y2 = curr[1];

        if (buttons[x1 - 1][y1] == buttons[x2][y2]) {
//            swap left
            swapBtn(new int[]{x1 - 1, x2},curr);
        }
        else if (buttons[x1 + 1][y1] == buttons[x2][y2]) {
//            swap right
            swapBtn(new int[]{x1 + 1, x2},curr);
        }
        else if (buttons[x1][y1 - 1] == buttons[x2][y2]) {
//            swap up
            swapBtn(new int[]{x1, x2 - 1},curr);
        }
        else if (buttons[x1][y1 + 1] == buttons[x2][y2]) {
            swapBtn(new int[]{x1, x2 + 1},curr);
//            swap down
        }
    }

    private void swapBtn (int[] prev, int[] curr) {
        int x1 = prev[0];
        int y1 = prev[1];
        int x2 = curr[0];
        int y2 = curr[1];

        String tempText = buttons[x1][y1].getText().toString();
        buttons[x1][y1].setText(buttons[x2][y2].getText());
        buttons[x2][y2].setText(tempText);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match3_game);

        final int[] score = {0};
        final int[] toSwap = {0};
        TextView scoretext = (TextView) findViewById(R.id.scoreTxt);

        buttons = new Button[5][5];
        int[] colors = {Color.RED,Color.GREEN,Color.BLUE,Color.YELLOW};
        Random rand = new Random();

        int ctr = 1;
//        for swapping
        final int[] prev = new int[2];
        final int[] curr = new int[2];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                // colorsz
                String buttonID = "button" + Integer.toString(ctr++);
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = ((Button) findViewById(resID));
                int temp = rand.nextInt(4);
                buttons[i][j].setText(Integer.toString(temp));
                buttons[i][j].setBackgroundColor(colors[temp]);
                buttons[i][j].setTextColor(colors[temp]);

                int finalI = i;
                int finalJ = j;
                buttons[i][j].setOnClickListener(v -> {
                // swap
                    if (toSwap[0] == 2) {
                        curr[0] = finalI;
                        curr[1] = finalJ;
                        isAdjacent(prev,curr);
                        toSwap[0] = 0;
                    } else {
                        prev[0] = finalI;
                        prev[1] = finalJ;
                        toSwap[0]++;
                    }

                // score
                    if (checkX(buttons) || checkY(buttons)) {
                        score[0]++;
//                        updateScore;
                        scoretext.setText("Score " + Integer.toString(score[0]));
                    }
                });
            }
        }

        reset = (Button) findViewById(R.id.buttonReset);
        reset.setOnClickListener(v -> {
            score[0] = 0;
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    int temp = rand.nextInt(4);
                    buttons[i][j].setText(Integer.toString(temp));
                    buttons[i][j].setBackgroundColor(colors[temp]);
                    buttons[i][j].setTextColor(colors[temp]);
                }
            }
        });


    }
}