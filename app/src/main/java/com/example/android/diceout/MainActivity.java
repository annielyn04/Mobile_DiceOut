package com.example.android.diceout;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int score;
    Random rand;

    TextView rollResult;
    //Button rollButton;
    TextView scoreText;

    int dice1;
    int dice2;
    int dice3;

    ArrayList<Integer> dice;

    ArrayList<ImageView> diceImageViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        /*fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rollDice(view);
            }
        });

        score = 0;

        Toast.makeText(getApplicationContext(),"Welcome!",Toast.LENGTH_SHORT).show();

        rollResult = (TextView)findViewById(R.id.rollResult);
        //rollButton = (Button) findViewById(R.id.rollButton);
        scoreText = (TextView)findViewById(R.id.scoreText);

        rand = new Random();

        dice = new ArrayList<Integer>();

        ImageView dice1Image = (ImageView)findViewById(R.id.dice1Image);
        ImageView dice2Image = (ImageView)findViewById(R.id.dice2Image);
        ImageView dice3Image = (ImageView)findViewById(R.id.dice3Image);

        diceImageViews = new ArrayList<ImageView>();
        diceImageViews.add(dice1Image);
        diceImageViews.add(dice2Image);
        diceImageViews.add(dice3Image);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void rollDice(View v){

        String message = "";
        dice1 = rand.nextInt(6)+1;
        dice2 = rand.nextInt(6)+1;
        dice3 = rand.nextInt(6)+1;

        dice.clear();
        dice.add(dice1);
        dice.add(dice2);
        dice.add(dice3);

        for(int i = 0; i<3; i++)
        {
            String imgName = "die_"+dice.get(i)+".png";
            try{
                InputStream stream = getAssets().open(imgName);
                Drawable drawable = Drawable.createFromStream(stream,null);
                diceImageViews.get(i).setImageDrawable(drawable);
            }catch (IOException e){e.printStackTrace();}

        }
        int roundScore=0;

        if(dice1 == dice2 && dice2 == dice3){
            //triple
            roundScore = dice1 * 100;
            message = "You rolled a triple " + dice1 + "! You scored " + roundScore + " points!";
            score += roundScore;
        }
        else if(dice1 == dice2 || dice1 == dice3 || dice2 == dice3){
            //double
            roundScore = 50;
            message = "You rolled doubles for 50 points!";
            score += roundScore;
        }
        else{
            message = "Try again!";
        }

        rollResult.setText(message);
        scoreText.setText("Score : " + score);
//   rollResult.setText(dice1 + "-" + dice2 + "-" + dice3);
    }

}
