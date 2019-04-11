package com.example.emily.wordswipe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

// OnTouchListener implemented to register touch events (in this case the swiping) on the
// custom View (the swipe circle where you pick words)
public class LevelActivity extends AppCompatActivity implements View.OnTouchListener
{
    TextView topLetter;
    TextView topLeftLetter;
    TextView topRightLetter;
    TextView bottomLeftLetter;
    TextView bottomRightLetter;
    TextView swipeCircle;

    TextView firstLetterPicked;
    TextView secondLetterPicked;
    TextView thirdLetterPicked;
    int countLettersPicked = 0;
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

        Boolean topLetterPicked = false;
        Boolean topLeftLetterPicked = false;
        List<String> lettersPicked = new ArrayList<>();
        String firstLetter;
        String secondLetter;


        // ACTION_MOVE means when your finger is dragging across the screen
        switch(event.getAction())
        {
            case MotionEvent.ACTION_MOVE:
            {

            }
            case MotionEvent.ACTION_UP:
            {
                checkForMatch();
                clearSelectedLetters();

            }
        }
            if(event.getAction() == MotionEvent.ACTION_MOVE ) {
                float x = event.getX();
                float y = event.getY();

                //top letter x=

//                while (event.getAction() != MotionEvent.ACTION_UP) {
                    if (view.getId() == R.id.letter_choice_circle) {
                        Toast.makeText(this, String.valueOf(x) + ", " + String.valueOf(y), Toast.LENGTH_SHORT).show();
                        getLettersPicked(x, y);

                        }

                    }//end switch
//            }
        return true;
    }




    private void getLettersPicked(float x, float y) {
        Boolean topLetterPicked = false;
        Boolean topLeftLetterPicked = false;
        String topLeftLetter = "S";
        int countLettersPicked = 0;
        List<String> lettersPicked = new ArrayList<>();

        if(x < 120 && y < 365 && y > 230 && topLeftLetterPicked == false)
        {
            lettersPicked.add(countLettersPicked, topLeftLetter);
//            firstLetterPicked = (TextView)findViewById(R.id.first_letter_picked);
//            firstLetterPicked.setText("S");
            topLeftLetterPicked = true;
            countLettersPicked++;
            displayLetterChoice(lettersPicked, countLettersPicked);

        }
        if(x > 290 && x < 450 && y < 180 && topLetterPicked == false)
        {
            lettersPicked.add(countLettersPicked, topLeftLetter);
            displayLetterChoice(lettersPicked, countLettersPicked);
            countLettersPicked++;
//            secondLetterPicked = (TextView)findViewById(R.id.second_letter_picked);
//            secondLetterPicked.setText("E");
        }
    }

    private void displayLetterChoice(List<String> lettersPicked, int countLettersPicked) {

        if(countLettersPicked == 1)
        {
            firstLetterPicked = (TextView)findViewById(R.id.first_letter_picked);
            firstLetterPicked.setText(lettersPicked.get(0));
        }
        if(countLettersPicked == 2)
        {
            secondLetterPicked = (TextView)findViewById(R.id.second_letter_picked);
            secondLetterPicked.setText(lettersPicked.get(1));
        }
    }

    private void checkForMatch() {\
    }

    private void clearSelectedLetters() {

    }


//    private void displayLetterPicker(List<String> lettersPicked) {
//        firstLetterPicked = (TextView)findViewById(R.id.first_letter_picked);
//        firstLetterPicked.setText(lettersPicked.get(0));
//
//        if (lettersPicked.size() > 1)
//        {
//            secondLetterPicked = (TextView)findViewById(R.id.second_letter_picked);
//            secondLetterPicked.setText(lettersPicked.get(1));
//        }
//    }


}
