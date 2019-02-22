package com.example.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // 0 is red, 1 is blue

    int activePlayer = 0;
    int turn = 0;
    boolean gameIsActive = true;
    int[] gameStatus = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    public void dropIn(View view) {

        ImageView count = (ImageView) view;
        int tag = Integer.parseInt(count.getTag().toString());
        int winningPostions[][] = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

        if (gameStatus[tag] == 2 && gameIsActive) {
            count.setTranslationY(-1000f);
            this.gameStatus[tag] = activePlayer;
            if (this.activePlayer == 0) {
                count.setImageResource(R.drawable.chip);
                this.activePlayer = 1;
            } else {
                count.setImageResource(R.drawable.chipblue);
                this.activePlayer = 0;
            }

            count.animate().translationYBy(1000f).setDuration(300);



            for (int[] winningPositions : winningPostions) {
                if (gameStatus[winningPositions[0]] == gameStatus[winningPositions[1]] &&
                        gameStatus[winningPositions[1]] == gameStatus[winningPositions[2]] &&
                        gameStatus[winningPositions[0]] != 2) {
                    String result = String.valueOf(gameStatus[winningPositions[0]]);
                    TextView winnermsg = (TextView) findViewById(R.id.winnermessage);
                    winnermsg.setText("Player " + result + " won!");
                    LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                    layout.setVisibility(View.VISIBLE);
                    Log.i("result", result);
                    this.gameIsActive = false;
                }
            }
        }
    }


    public void playAgain(View view){
        gameIsActive = true;

        for (int x = 0; x< gameStatus.length; x++){
            gameStatus[x] = 2;
        }

        GridLayout gridl = (GridLayout) findViewById(R.id.gridLayout);

        for (int i = 0; i<gridl.getChildCount(); i++){

            ((ImageView) gridl.getChildAt(i)).setImageResource(0);

        }
        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
