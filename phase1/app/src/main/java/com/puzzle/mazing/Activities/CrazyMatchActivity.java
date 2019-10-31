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

public class CrazyMatchActivity extends AppCompatActivity {
    TextView score;

    ImageView[] IMGS = new ImageView[12];

    Integer[] cardArray = {101, 102, 103, 104, 105, 106, 201, 202, 203, 204, 205, 206};

    private Animal[] animals;
    private CrazyMatchManager matchManager = new CrazyMatchManager();
    int[] drawable = matchManager.getBoard().getdrawables();


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

        animals = matchManager.getBoard().getAnimals();
        setPos();
        setImageOnClickListener();
        Collections.shuffle(Collections.singletonList(drawable));

    }

    private void setPos() {
        IMGS[0] = findViewById(R.id.iv_11);
        IMGS[1] = findViewById(R.id.iv_12);
        IMGS[2] = findViewById(R.id.iv_13);
        IMGS[3] = findViewById(R.id.iv_14);
        IMGS[4] = findViewById(R.id.iv_21);
        IMGS[5] = findViewById(R.id.iv_22);
        IMGS[6] = findViewById(R.id.iv_23);
        IMGS[7] = findViewById(R.id.iv_24);
        IMGS[8] = findViewById(R.id.iv_31);
        IMGS[9] = findViewById(R.id.iv_32);
        IMGS[10] = findViewById(R.id.iv_33);
        IMGS[11] = findViewById(R.id.iv_34);
        for (int i =0; i<IMGS.length; i++){
            animals[i].setView(IMGS[i]);
            IMGS[i].setTag(i);

        }
    }

    public void setImageOnClickListener() {

        for (final ImageView card : IMGS) {
            card.setOnClickListener(v -> {
                int k = (int)v.getTag();
                update(card, k);


            });
        }

    }


    private void update(ImageView iv, int card) {

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

            for (ImageView image : IMGS) {
                image.setEnabled(false);
            }
            Handler handler = new Handler();
            handler.postDelayed(this::calculate, 1000);

        }


    }

    @SuppressLint("SetTextI18n")
    public void calculate() {
        if (firstCard == secondCard) {
            for (int i = 0; i < IMGS.length; i++){
                if (clickedFirst == i) {
                    IMGS[i].setVisibility(View.INVISIBLE);
                }
                if (clickedSecond == i) {
                    IMGS[i].setVisibility(View.INVISIBLE);
                }
            }

            if (turn == 1) {
                playerPoints++;
                score.setText("Score:" + playerPoints);
            }
        } else {

            for (ImageView k : IMGS) {
                k.setImageResource(R.drawable.planet);
            }
        }
        for (ImageView k : IMGS) {
            k.setEnabled(true);
        }
        checkEnd();
    }


    private boolean check_invisible(){
        return IMGS[0].getVisibility() == View.INVISIBLE &&
                IMGS[1].getVisibility() == View.INVISIBLE &&
                IMGS[2].getVisibility() == View.INVISIBLE &&
                IMGS[3].getVisibility() == View.INVISIBLE &&
                IMGS[4].getVisibility() == View.INVISIBLE &&
                IMGS[5].getVisibility() == View.INVISIBLE &&
                IMGS[6].getVisibility() == View.INVISIBLE &&
                IMGS[7].getVisibility() == View.INVISIBLE &&
                IMGS[8].getVisibility() == View.INVISIBLE &&
                IMGS[9].getVisibility() == View.INVISIBLE &&
                IMGS[10].getVisibility() == View.INVISIBLE &&
                IMGS[11].getVisibility() == View.INVISIBLE ;
    }
    private void checkEnd() {
        if (check_invisible()) {
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






