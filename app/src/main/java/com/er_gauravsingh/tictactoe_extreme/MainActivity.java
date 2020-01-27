package com.er_gauravsingh.tictactoe_extreme;

import android.graphics.drawable.AnimationDrawable;
import android.media.Image;
import android.support.annotation.DrawableRes;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    boolean nightMode = false;
    boolean gameActive = true;
    // Player Representation
    // 0 - X
    // 1 - O
    int activePlayer = 0;
    int [] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    // state meanings
    // 0 - X
    // 1 - O
    // 2 - Null
    int[][] winPositions = {{0,1,2}, {3,4,5}, {6,7,8},
                            {0,3,6}, {1,4,7}, {2,5,8},
                            {0,4,8}, {2,4,6}};



    public void playerTap(View view){
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());
        if(!gameActive){
            gameReset(view);
        }
        else{

            if (gameState[tappedImage] == 2) {
                gameState[tappedImage] = activePlayer;
                img.setTranslationY(-1000f);
                if (activePlayer == 0) {
                    if(nightMode) {
                        img.setImageResource(R.drawable.xn);
                    }
                    else {
                        img.setImageResource(R.drawable.x);
                    }
                    activePlayer = 1;
                    TextView status = findViewById(R.id.status);
                    status.setText("0's Turn - Tap To Play");
                } else {
                    if(nightMode) {
                        img.setImageResource(R.drawable.on);
                    }
                    else {
                        img.setImageResource(R.drawable.o);
                    }
                    activePlayer = 0;
                    TextView status = findViewById(R.id.status);
                    status.setText("X's Turn - Tap To Play");
                }

                img.animate().translationYBy(1000f).setDuration(300);
            }


            // check if any player has won
            for (int[] winPosition : winPositions) {
                if (gameState[winPosition[0]] == gameState[winPosition[1]] &&
                        gameState[winPosition[1]] == gameState[winPosition[2]] &&
                        gameState[winPosition[0]] != 2) {
                    // somebody has won....find out who
                    String winnerStr;
                    gameActive = false;
                    if (gameState[winPosition[0]] == 0) {
                        winnerStr = "X has won";
                    } else {
                        winnerStr = "O has won";
                    }
                    // update status bar for winner announcement
                    TextView status = findViewById(R.id.status);
                    status.setText(winnerStr);

                }
            }
                                                                 }

    }




    public void gameReset(View view){
        gameActive = true;
        activePlayer = 0;
        for (int i=0; i<gameState.length; i++){
            gameState[i] = 2;
        }
        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
        TextView status = findViewById(R.id.status);
        status.setText("X's Turn - Tap To Play");
    }






    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ImageView vs = (ImageView)findViewById(R.id.imageView);
        final ConstraintLayout cl = (ConstraintLayout)findViewById(R.id.cl);
        cl.setBackgroundResource(R.drawable.gradient_list);
        final AnimationDrawable animationDrawable = (AnimationDrawable) cl.getBackground();         //
        animationDrawable.setEnterFadeDuration(2000);           //
        animationDrawable.setExitFadeDuration(4000);            //
        animationDrawable.start();                              //


        final Switch sswitch = findViewById(R.id.switch1);
        sswitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameReset(view);
                if(sswitch.isChecked()){
                    cl.setBackgroundResource(R.color.black);
                    vs.setImageResource(R.drawable.gridn);
                    nightMode = true;

                }
                else {
                    cl.setBackgroundResource(R.drawable.gradient_list);
                    AnimationDrawable animationDrawable = (AnimationDrawable) cl.getBackground();         //
                    animationDrawable.setEnterFadeDuration(2000);           //
                    animationDrawable.setExitFadeDuration(4000);            //
                    animationDrawable.start();                              //
                    vs.setImageResource(R.drawable.grid);
                    nightMode = false;

                }

            }
        });

    }
}
