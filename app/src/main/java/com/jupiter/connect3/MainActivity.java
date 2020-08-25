package com.jupiter.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // 0: yellow, 1: red, 2: empty

    int[] gameState = {2,2,2,2,2,2,2,2,2};

    int[][] winningStates = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

    boolean hasWon = false;

    int activePlayer = 0;

    // 0 = yellow, 1 = red

    public void dropIn(View view){
        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if(hasWon == false){
            if (gameState[tappedCounter] == 2){
                gameState[tappedCounter] = activePlayer;

                counter.setTranslationY(-1500);

                if(activePlayer == 0){
                    counter.setImageResource(R.drawable.yellow);
                    activePlayer = 1;
                } else {
                    counter.setImageResource(R.drawable.red);
                    activePlayer = 0;
                }

                counter.animate().translationYBy(1500).rotation(3600).setDuration(300);

                for(int[] winningState : winningStates){

                    String winner = "";

                    if(activePlayer == 1){
                        winner = "Yellow";
                    }else{
                        winner = "Red";
                    }

                    if(gameState[winningState[0]] == gameState[winningState[1]] && gameState[winningState[1]] == gameState[winningState[2]] && gameState[winningState[0]] != 2){
                        // someone has won!!
                        hasWon = true;

                        Button restartButton = (Button) findViewById(R.id.restartButton);
                        restartButton.setVisibility(View.VISIBLE);

                        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
                        winnerTextView.setText(winner + " has won!");
                    } else {
                        Button restartButton = (Button) findViewById(R.id.restartButton);
                        restartButton.setVisibility(View.VISIBLE);

                        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
                        winnerTextView.setText("DRAW!");
                    }
                }
            } else{
                Toast.makeText(this, "Choose Another Place!", Toast.LENGTH_SHORT).show();
            }
        } else{
            Toast.makeText(this, "Game is over!", Toast.LENGTH_SHORT).show();
        }

    }

    public void restart(View view){
        Button restartButton = (Button) findViewById(R.id.restartButton);
        restartButton.setVisibility(View.INVISIBLE);

        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
        winnerTextView.setText("Connect 3 Game");

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        for(int i=0; i<gridLayout.getChildCount(); i++){
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }

        for(int i=0; i<gameState.length; i++){
            gameState[i] = 2;
        }

        hasWon = false;

        activePlayer = 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}