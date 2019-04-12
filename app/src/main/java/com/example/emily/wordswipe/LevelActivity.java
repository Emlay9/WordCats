package com.example.emily.wordswipe;

import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import android.os.Handler;
import java.util.logging.LogRecord;

// OnTouchListener implemented to register touch events (in this case the swiping) on the
// custom View (the swipe circle where you pick words)

public class LevelActivity extends AppCompatActivity implements View.OnTouchListener
{

//    TextView topLetter;
//    TextView topLeftLetter;
//    TextView topRightLetter;
//    TextView bottomLeftLetter;
//    TextView bottomRightLetter;
    TextView swipeCircle;

    Boolean topLetterPicked = false;
    Boolean topLeftLetterPicked = false;
    Boolean bottomLeftLetterPicked = false;
    Boolean topRightLetterPicked = false;
    Boolean bottomRightLetterPicked = false;

    //TextViews of the letters picked that shows up above the circle
    TextView firstLetterPicked;
    TextView secondLetterPicked;
    TextView thirdLetterPicked;
    TextView fourthLetterPicked;
    TextView fifthLetterPicked;

    int countLettersPicked = 0;
    List<String> lettersPicked = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);
        countLettersPicked = 0;
        // All the textviews for the letters that you can swipe to make words with
//        topLetter = (TextView)findViewById(R.id.top_letter_choice);
//        topLeftLetter = (TextView)findViewById(R.id.left_top_letter_choice);
//        topRightLetter = (TextView)findViewById(R.id.right_top_letter_choice);
//        bottomLeftLetter = (TextView)findViewById(R.id.left_bottom_letter_choice);
//        bottomRightLetter = (TextView)findViewById(R.id.right_bottom_letter_choice);
        swipeCircle = (TextView)findViewById(R.id.letter_choice_circle);
        swipeCircle.setOnTouchListener(this);


        //Set on touch listener for all of the swipeable letter textviews
//        topLetter.setOnTouchListener(this);
//        topLeftLetter.setOnTouchListener(this);
//        topRightLetter.setOnTouchListener(this);
//        bottomLeftLetter.setOnTouchListener(this);
    }

    /***** onTouch(View v, MotionEvent event) info *****/
    /* - This method needs to be added when we implement View.OnTouchListener
       - It's similar to the onClick() method for buttons
     It's parameters are:
            - the View v being touched and
            - MotionEvent event gives information about the touch on that view (example: coordinates
            of where its being pressed, if it's moving, where it stops etc etc...)
     */
    @Override
    public boolean onTouch(View view, MotionEvent event) {


        if (view.getId() == R.id.letter_choice_circle) {
            switch (event.getAction()) {
                // ACTION_MOVE means when your finger is dragging across the screen
                // During this time we want to give feedback to the user to show what letters they're selecting
                case MotionEvent.ACTION_MOVE: {
                    //get the coordinates of where on the circle is being touched
                    float x = event.getX();
                    float y = event.getY();
                    // Uncomment the following line of code for demo of how to see the coordinates on the circle
//                    Toast.makeText(this, String.valueOf(x) + ", " + String.valueOf(y), Toast.LENGTH_SHORT).show();
                    if (countLettersPicked <= 2) {
                        countLettersPicked = getLettersPicked(x, y, countLettersPicked);
                    }
                    break;
                }
//                ACTION_UP means when you stop touching the screen
//                 when this happens we want to check if the swiped word is a match and clear the word selection that pops up as you swipe
                case MotionEvent.ACTION_UP: {
                    checkForMatch();
                    clearSelectedLetters();
                    countLettersPicked = 0;
                    topLetterPicked = false;
                    topLeftLetterPicked = false;

                }
            }
        }
        return true;
    }

    private int getLettersPicked(float x, float y, int countLettersPicked) {
        String topLetter = "E";
        String topLeftLetter = "S";

            // coordinate areas on the circle corresponding to where the 5 letters are
            if(x < 120 && y < 365 && y > 230 && topLeftLetterPicked == false)
            {
                lettersPicked.add(countLettersPicked, topLeftLetter);
                topLeftLetterPicked = true;
                countLettersPicked++;
                displayLetterChoice(lettersPicked, countLettersPicked);
            }
            if(x > 290 && x < 450 && y < 180 && topLetterPicked == false)
            {
                lettersPicked.add(countLettersPicked, topLetter);
                topLetterPicked = true;
                countLettersPicked++;
                displayLetterChoice(lettersPicked, countLettersPicked);
            }
            if(x > 175 && x < 250 && y > 555)
            {

            }

        return countLettersPicked;
    }

    private void displayLetterChoice(List<String> lettersPicked, int countLettersPicked) {

        switch (countLettersPicked) {
            case 1:
            {
                firstLetterPicked = (TextView)findViewById(R.id.first_letter_picked);
                firstLetterPicked.setVisibility(View.VISIBLE);
                firstLetterPicked.setText(lettersPicked.get(0));
                break;
            }
            case 2:
            {
                secondLetterPicked = (TextView)findViewById(R.id.second_letter_picked);
                secondLetterPicked.setVisibility(View.VISIBLE);
                secondLetterPicked.setText(lettersPicked.get(1));
                break;
            }
            case 3:
            {

                break;
            }
            case 4:
            {

                break;
            }
            case 5:
            {

                break;
            }
        }
    }

    private void checkForMatch() {

    }



    private void clearSelectedLetters() {
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                firstLetterPicked = (TextView)findViewById(R.id.first_letter_picked);
                firstLetterPicked.setVisibility(View.INVISIBLE);

                secondLetterPicked = (TextView)findViewById(R.id.second_letter_picked);
                secondLetterPicked.setVisibility(View.INVISIBLE);
            }
        }, 500);

//        new Timer().schedule(new TimerTask() {
//            @Override
//            public void run() {
//
//            }
//        }, 2000);

    }
}
