package com.puzzle.mazing.Activities;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.puzzle.mazing.DataAccess.CrazyMatchManager;
import com.puzzle.mazing.Models.Animal;
import com.puzzle.mazing.Models.CrazyMatchBoard;
import com.puzzle.mazing.R;

import java.util.Arrays;
import java.util.Collections;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class  CrazyMatchActivity extends AppCompatActivity {
    TextView score;

    ImageView[] buttonPos = new ImageView[12];

    Integer[] cardArray = {101, 102, 103, 104, 105, 106, 201, 202, 203, 204, 205, 206};

    private Animal[] animals;
    private CrazyMatchManager matchManager = new CrazyMatchManager();
    int[] drawable;


    int firstCard, secondCard;
    int clickedFirst, clickedSecond;
    int cardNumber = 1;

    int turn = 1;
    int playerPoints = 0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matching);
        score = findViewById(R.id.score);


        setPos();
        matchManager.restart(buttonPos);
        animals = matchManager.getBoard().getAnimals();
        setAnimalPos();
        drawable = matchManager.getBoard().getDrawables();
        setImageOnClickListener();
        Collections.shuffle(Collections.singletonList(drawable));

    }
    private void setImageOnClickListener() {

        for (final ImageView card : buttonPos) {
            card.setOnClickListener(v -> {
                int k = (int)v.getTag();
                update(card, k);


            });
        }

    }

    private void setPos() {
        buttonPos[0] = findViewById(R.id.iv_11);
        buttonPos[1] = findViewById(R.id.iv_12);
        buttonPos[2] = findViewById(R.id.iv_13);
        buttonPos[3] = findViewById(R.id.iv_14);
        buttonPos[4] = findViewById(R.id.iv_21);
        buttonPos[5] = findViewById(R.id.iv_22);
        buttonPos[6] = findViewById(R.id.iv_23);
        buttonPos[7] = findViewById(R.id.iv_24);
        buttonPos[8] = findViewById(R.id.iv_31);
        buttonPos[9] = findViewById(R.id.iv_32);
        buttonPos[10] = findViewById(R.id.iv_33);
        buttonPos[11] = findViewById(R.id.iv_34);

    }
    private  void setAnimalPos(){
        for (int i =0; i<buttonPos.length; i++){
            animals[i].setView(buttonPos[i]);
            buttonPos[i].setTag(i);

        }
    }




    private void update(ImageView iv, int card) {

        // flip the image, change the default image into corresponding animal 
        iv.setImageResource(drawable[card]);

        if (cardNumber == 1) {
            firstCard = cardArray[card];
            if (firstCard > 200) {
                firstCard = firstCard - 100;
            }
            cardNumber = 2;
            clickedFirst = card;

            iv.setEnabled(false);

        } else if (cardNumber == 2) {
            secondCard = cardArray[card];
            if (secondCard > 200) {
                secondCard = secondCard - 100;

            }
            cardNumber = 1;
            clickedSecond = card;

            for (ImageView image : buttonPos) {
                image.setEnabled(false);
            }
            Handler handler = new Handler();
            handler.postDelayed(this::calculate, 1000);

        }


    }

    @SuppressLint("SetTextI18n")
    public void calculate() {
        if (firstCard == secondCard) {
            for (int i = 0; i < buttonPos.length; i++){
                if (clickedFirst == i) {
                    buttonPos[i].setVisibility(View.INVISIBLE);
                }
                if (clickedSecond == i) {
                    buttonPos[i].setVisibility(View.INVISIBLE);
                }
            }

            if (turn == 1) {
                playerPoints++;
                score.setText("Score:" + playerPoints);
            }
        } else {

            for (ImageView k : buttonPos) {
                k.setImageResource(R.drawable.planet);
            }
        }
        for (ImageView k : buttonPos) {
            k.setEnabled(true);
        }
        checkEnd();
    }
    public void checkEnd() {
        if (matchManager.End()) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(CrazyMatchActivity.this);
            alertDialogBuilder.setMessage("Game OVER \n Player:" + playerPoints).setCancelable(false)
                    .setPositiveButton("New", (dialog, which) -> {
                        Intent intent = new Intent(getApplicationContext(), CrazyMatchActivity.class);
                        startActivity(intent);
                        finish();
                    }).setNegativeButton("Exit", (dialog, which) -> finish());
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
    }


}






