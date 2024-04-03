package com.example.match3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class Match3Game extends AppCompatActivity {

    Button[][] buttons;
    Button reset;

    private boolean checkX (Button[][] buttons, int y) {
//        for (int i = 0; i < 5; i++) {
//            int ctr = i + 1;
//            int tempScore = 0;
//            while (buttons[y][i].getText().equals(buttons[y][ctr].getText())) {
//                tempScore++;
//                ctr++;
//            }
//            if (tempScore >= 3) {
////                SWAP TILES YAWA
//                return true;
//            } else {
//                tempScore = 0;
//            }
//        }
        return false;
    }

    private boolean checkY (Button[][] buttons, int x) {
//        for (int i = 0; i < 5; i++) {
//            int ctr = i + 1;
//            int tempScore = 0;
//            while (buttons[i][x].getText().equals(buttons[ctr][x].getText())) {
//                tempScore++;
//                ctr++;
//            }
//            if (tempScore >= 3) {
////                SWAP TILES YAWA
//                return true;
//            } else {
//                tempScore = 0;
//            }
//        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match3_game);

        final int[] score = {0};
        TextView scoretext = (TextView) findViewById(R.id.scoreTxt);

        buttons = new Button[5][5];
        int[] colors = {Color.RED,Color.GREEN,Color.BLUE,Color.YELLOW};
        Random rand = new Random();

        int ctr = 1;
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

                // scorer?
                int finalI = i;
                int finalJ = j;
                buttons[i][j].setOnClickListener(v -> {
                    if (checkX(buttons, finalI) || checkY(buttons, finalJ)) {
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