package com.dishant.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int tapCount = 0;
    boolean gameOn = true;
    int activePlayer = 1;
    int[] boxState = {-1, -1, -1, -1, -1, -1, -1, -1, -1};

    //  State References:
    //      -1 : NULL
    //      0  : 0
    //      1  : X

    int[][] winStates = {{0,1,2}, {3,4,5}, {6,7,8},
                        {0,3,6}, {1,4,7}, {2,5,8},
                        {0,4,8}, {2,4,6}};

    public void tap(View view) {
        tapCount++;                                                                                 //  Maintaining tap-count.
        ImageView img = (ImageView) view;                                                           //  Creating image-view.
        int tappedImage = Integer.parseInt(img.getTag().toString());                                //  Getting tag (number) of tapped box.
        if (boxState[tappedImage] == -1 && gameOn) {                                                //  If box is initially empty.
            boxState[tappedImage] = activePlayer;                                                   //  Assigning state (X or 0) to box.
            TextView status = findViewById(R.id.status);                                            //  Finding status box.
            if (activePlayer == 0) {
                activePlayer = 1;
                status.setText("X's turn.");                                                        //  Setting status for next turn of X.
                img.setImageResource(R.drawable.zero);                                              //  Setting zero as image in the tapped box.
            } else {
                activePlayer = 0;
                status.setText("O's turn");                                                         //  Setting status for next turn of 0.
                img.setImageResource(R.drawable.cross);                                             //  Setting cross as image in the tapped box.
            }
        }

        if(tapCount==9) {                                                                           //  Checking for a tie.
            TextView status = findViewById(R.id.status);                                            //  Finding status box.
            if(status.getText().equals("X's turn") || status.getText().equals("O's turn"))
                status.setText("It's a tie!");                                                      //  Assigning status as tie.
            Button reset = findViewById(R.id.reset);                                                //  Finding reset button.
            reset.setVisibility(View.VISIBLE);                                                      //  Making reset button visible.
        }

        for (int[] winState : winStates)
            if (boxState[winState[0]] != -1 && boxState[winState[0]] == boxState[winState[1]] && boxState[winState[1]] == boxState[winState[2]]) {
                String victory;                                                                     //  Checked for all boxes in a winState to be in the same non-null state.
                if (boxState[winState[0]] == 1) {
                    victory = "X wins!";                                                            //  Assigning victory message as per the state.
                    gameOn = false;
                } else {
                    victory = "0 wins!";                                                            //  Assigning victory message as per the state.
                    gameOn = false;
                }
                TextView status = findViewById(R.id.status);                                        //  Finding status box.
                status.setText(victory);                                                            //  Setting victory message.
                Button reset = findViewById(R.id.reset);                                            //  Finding reset button.
                reset.setVisibility(View.VISIBLE);                                                  //  Making reset button visible if someone wins.
                break;
            }
    }


    public void reset(View view) {
        gameOn = true;                                                                              //  Turning game on.
        activePlayer = 1;                                                                           //  Making X the active player.
        tapCount = 0;                                                                               //  Resetting tap count.
        for(int i=0 ; i<boxState.length ; i++)
            boxState[i] = -1;                                                                       //  Setting box states to null.
        ((ImageView)findViewById(R.id.i0)).setImageResource(0);                                     //  Making all boxes blank.
        ((ImageView)findViewById(R.id.i1)).setImageResource(0);
        ((ImageView)findViewById(R.id.i2)).setImageResource(0);
        ((ImageView)findViewById(R.id.i3)).setImageResource(0);
        ((ImageView)findViewById(R.id.i4)).setImageResource(0);
        ((ImageView)findViewById(R.id.i5)).setImageResource(0);
        ((ImageView)findViewById(R.id.i6)).setImageResource(0);
        ((ImageView)findViewById(R.id.i7)).setImageResource(0);
        ((ImageView)findViewById(R.id.i8)).setImageResource(0);
        Button reset = findViewById(R.id.reset);                                                    //  Finding reset button.
        reset.setVisibility(View.INVISIBLE);                                                        //  Making reset button invisible.
        TextView status = findViewById(R.id.status);                                                //  Finding status box.
        status.setText("X's turn");                                                                 //  Setting initial status.
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
