package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    boolean isWinner = false;
    int imageClicked = -1;

    int[][] winningStates = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{3,4,6}};
    int[] gameState = {-1, -1, -1, -1, -1, -1, -1, -1, -1};

    int player = 1; //Player 1 is cross


    public void load(View view) {
        if (!isWinner && imageClicked == -1) {
            ImageView v = (ImageView) view;
            int tag = Integer.parseInt(v.getTag().toString());
            imageClicked = gameState[tag];
            if (!isWinner && imageClicked == -1) {
                if (player == 1) {
                    v.setImageResource(R.drawable.tictaccross);
                    gameState[tag] = player;
                    Toast.makeText(this, tag + "" + "Cross", Toast.LENGTH_SHORT).show();
                    player = 0;
                } else {
                    v.setImageResource(R.drawable.zero);
                    gameState[tag] = player;
                    Toast.makeText(this, tag + "" + "Zero", Toast.LENGTH_SHORT).show();
                    player = 1;

                }
                for (int[] winningState : winningStates) {
                    if (gameState[winningState[0]] == gameState[winningState[1]] && gameState[winningState[1]] == gameState[winningState[2]] && gameState[winningState[0]] > -1) {
                        Toast.makeText(this, "Winner is " + (player == 0 ? 1 : 0), Toast.LENGTH_SHORT).show();
                        isWinner = true;

                    }

                }


            }

        }
    }
    public void reset(View view){
        GridLayout gridLayout=findViewById(R.id.gridLayout);
        int total_images=gridLayout.getChildCount();
        for(int i=0;i<total_images;i++){

            ImageView v= (ImageView) gridLayout.getChildAt(i);
            v.setImageDrawable(null);

        }
        isWinner = false;
        imageClicked = -1;
        player=1;
        Arrays.fill(gameState, -1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Be Alert")
                .setMessage("You are about to play Tick Tac Toe")
                .setNeutralButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(),"Hi, Welcome",Toast.LENGTH_SHORT).show();
                    }
                }).show();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}


