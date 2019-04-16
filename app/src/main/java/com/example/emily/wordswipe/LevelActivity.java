package com.example.emily.wordswipe;

import android.content.Intent;
import android.os.Looper;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.os.Handler;
import android.widget.Toast;


// OnTouchListener implemented to register touch events (in this case the swiping) on the
// the circle wheel where you pick words)

public class LevelActivity extends AppCompatActivity implements View.OnTouchListener {

    View swipeCircle;
    static final int WORDSINTHELEVEL = 5;

    //The letters in the circle
    static final String topLetter = "E";
    static final String topLeftLetter = "S";
    static final String bottomLeftLetter = "L";
    static final String bottomRightLetter = "F";
    static final String topRightLetter = "H";

    Boolean topLetterPicked = false;
    Boolean topLeftLetterPicked = false;
    Boolean bottomLeftLetterPicked = false;
    Boolean topRightLetterPicked = false;
    Boolean bottomRightLetterPicked = false;

    Boolean firstWordFound = false;
    Boolean secondWordFound = false;
    Boolean thirdWordFound = false;
    Boolean fourthWordFound = false;
    Boolean fifthWordFound = false;

    //TextViews of the letters picked that shows up above the circle
    TextView firstLetterPicked;
    TextView secondLetterPicked;
    TextView thirdLetterPicked;
    TextView fourthLetterPicked;
    TextView fifthLetterPicked;

    //TextViews of the letters in the circle
    TextView topLeftLetterTv;
    TextView topLetterTv;
    TextView topRightLetterTv;
    TextView bottomLeftLetterTv;
    TextView bottomRightLetterTv;

    int countLettersPicked = 0;
    int countWordsFound = 0;
    List<String> lettersPicked = new ArrayList<>();

    String[] words = new String[]{"ELF", "SHE", "SELF", "FLESH", "SHELF"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);
        countLettersPicked = 0;

        topLeftLetterTv = (TextView)findViewById(R.id.left_top_letter_choice);
        topRightLetterTv = (TextView)findViewById(R.id.right_top_letter_choice);
        topLetterTv = (TextView)findViewById(R.id.top_letter_choice);
        bottomRightLetterTv = (TextView)findViewById(R.id.right_bottom_letter_choice);
        bottomLeftLetterTv = (TextView)findViewById(R.id.left_bottom_letter_choice);

        swipeCircle = findViewById(R.id.letter_choice_circle);
        swipeCircle.setOnTouchListener(this);
    }

    /***** onTouch(View v, MotionEvent event) info *****/
    /* - The following method needs to be added when we implement View.OnTouchListener
       - It's similar to the onClick() method for buttons
     It's parameters are:
            - the View v being touched and
            - MotionEvent event gives information about the touch on that view (example: coordinates
            of where its being pressed, if it's moving, where it stops etc etc...)
     */
    @Override
    public boolean onTouch(View view, MotionEvent event) {

        resetBackgroundColor();
        if (view.getId() == R.id.letter_choice_circle) {
            switch (event.getAction()) {
//                 ACTION_MOVE means when your finger is dragging across the screen
//                 During this time we want to give feedback to the user to show what letters they're selecting
                case MotionEvent.ACTION_MOVE: {
//                   Get the coordinates of where on the circle is being touched
                    float x = event.getX();
                    float y = event.getY();
//                    Uncomment the following line of code for a demo of how to see the coordinates on the circle
//                    Toast.makeText(this, String.valueOf(x) + ", " + String.valueOf(y), Toast.LENGTH_SHORT).show();
                    getLettersPicked(x, y);
                    break;
                }
//                ACTION_UP means when you stop touching the screen
//                 - When this happens we want to check if the swiped word is a match
//                   and clear the word selection that pops up as you swipe
                case MotionEvent.ACTION_UP: {
                    checkForMatch();
                    clearSelectedLetters();
//                    Reset the variables
                    countLettersPicked = 0;
                    //Empty the array of letters picked
                    lettersPicked.clear();

                    topLetterPicked = false;
                    topLeftLetterPicked = false;
                    bottomLeftLetterPicked = false;
                    bottomRightLetterPicked = false;
                    topRightLetterPicked = false;

                    checkForWin();
                }
            }
        }
        return true;
    }

    private void checkForWin() {
        if(countWordsFound == WORDSINTHELEVEL)
        {
            Intent intent = new Intent(this, LevelWonActivity.class);
            intent.putExtra("level", 1);
            startActivity(intent);
        }
    }

    private void resetBackgroundColor() {
        firstLetterPicked = (TextView) findViewById(R.id.first_letter_picked);
        firstLetterPicked.setBackgroundColor(ContextCompat.getColor(this,R.color.pickedLettersBackground));

        secondLetterPicked = (TextView) findViewById(R.id.second_letter_picked);
        secondLetterPicked.setBackgroundColor(ContextCompat.getColor(this,R.color.pickedLettersBackground));

        thirdLetterPicked = (TextView) findViewById(R.id.third_letter_picked);
        thirdLetterPicked.setBackgroundColor(ContextCompat.getColor(this,R.color.pickedLettersBackground));

        fourthLetterPicked = (TextView) findViewById(R.id.fourth_letter_picked);
        fourthLetterPicked.setBackgroundColor(ContextCompat.getColor(this,R.color.pickedLettersBackground));

        fifthLetterPicked = (TextView) findViewById(R.id.fifth_letter_picked);
        fifthLetterPicked.setBackgroundColor(ContextCompat.getColor(this,R.color.pickedLettersBackground));
    }

    private int getLettersPicked(float x, float y) {

        //Only allow up to 5 letters picked
            // coordinate areas on the circle corresponding to where the 5 letters are
            if (x < 145 && y < 390 && y > 240 && !topLeftLetterPicked) {
                lettersPicked.add(countLettersPicked, topLeftLetter);
                topLeftLetterPicked = true;
                countLettersPicked++;
                topLeftLetterTv.setTextColor(getResources().getColor(R.color.letterPicked));
                displayLetterChoice(lettersPicked, countLettersPicked);
            }
            if (x > 290 && x < 450 && y < 180 && !topLetterPicked) {
                lettersPicked.add(countLettersPicked, topLetter);
                topLetterPicked = true;
                countLettersPicked++;
                topLetterTv.setTextColor(getResources().getColor(R.color.letterPicked));
                displayLetterChoice(lettersPicked, countLettersPicked);
            }
            if (x > 175 && x < 250 && y > 555 && !bottomLeftLetterPicked) {
                lettersPicked.add(countLettersPicked, bottomLeftLetter);
                bottomLeftLetterPicked = true;
                countLettersPicked++;
                bottomLeftLetterTv.setTextColor(getResources().getColor(R.color.letterPicked));
                displayLetterChoice(lettersPicked, countLettersPicked);
            }
            if (x > 530 && x < 650 && y > 535 && !bottomRightLetterPicked) {
                lettersPicked.add(countLettersPicked, bottomRightLetter);
                bottomRightLetterPicked = true;
                countLettersPicked++;
                bottomRightLetterTv.setTextColor(getResources().getColor(R.color.letterPicked));
                displayLetterChoice(lettersPicked, countLettersPicked);
            }
            if (x > 595 && y > 260 && y < 390 && !topRightLetterPicked) {
                lettersPicked.add(countLettersPicked, topRightLetter);
                topRightLetterPicked = true;
                countLettersPicked++;
                topRightLetterTv.setTextColor(getResources().getColor(R.color.letterPicked));
                displayLetterChoice(lettersPicked, countLettersPicked);
            }
        return countLettersPicked;
    }

    private void displayLetterChoice(List<String> lettersPicked, int countLettersPicked) {
        switch (countLettersPicked) {
            case 1: {
                firstLetterPicked = (TextView) findViewById(R.id.first_letter_picked);
                firstLetterPicked.setVisibility(View.VISIBLE);
                firstLetterPicked.setText(lettersPicked.get(0));
                break;
            }
            case 2: {
                secondLetterPicked = (TextView) findViewById(R.id.second_letter_picked);
                secondLetterPicked.setVisibility(View.VISIBLE);
                secondLetterPicked.setText(lettersPicked.get(1));
                break;
            }
            case 3: {
                thirdLetterPicked = (TextView) findViewById(R.id.third_letter_picked);
                thirdLetterPicked.setVisibility(View.VISIBLE);
                thirdLetterPicked.setText(lettersPicked.get(2));
                break;
            }
            case 4: {
                fourthLetterPicked = (TextView) findViewById(R.id.fourth_letter_picked);
                fourthLetterPicked.setVisibility(View.VISIBLE);
                fourthLetterPicked.setText(lettersPicked.get(3));
                break;
            }
            case 5: {
                fifthLetterPicked = (TextView) findViewById(R.id.fifth_letter_picked);
                fifthLetterPicked.setVisibility(View.VISIBLE);
                fifthLetterPicked.setText(lettersPicked.get(4));
                break;
            }
        }
    }

    private void checkForMatch() {
        //Convert List of chosen letters to a String
        StringBuilder sb = new StringBuilder();
        for (String letters : lettersPicked) {
            sb.append(letters);
        }
        String pickedWord = sb.toString();

        //first check if the picked word is one of the level words
        if (Arrays.asList(words).contains(pickedWord)) {
            if (pickedWord.equals(words[0]) && !firstWordFound) {
                int[] ids = new int[]{R.id.E_in_ELF, R.id.L_in_ELF, R.id.F_in_ELF};
                setWord(lettersPicked, ids);
                firstWordFound = true;
                //change the background color of the popup letters to green to indicate word was found
                setBackgroundColorWordFound();
            }
            if (pickedWord.equals(words[1]) && !secondWordFound) {
                int[] ids = new int[]{R.id.S_in_SHE, R.id.H_in_SHE, R.id.E_in_SHE};
                setWord(lettersPicked, ids);
                secondWordFound = true;
                setBackgroundColorWordFound();
            }
            if (pickedWord.equals(words[2]) && !thirdWordFound) {
                int[] ids = new int[]{R.id.S_in_SELF, R.id.E_in_SELF, R.id.L_in_SELF, R.id.F_in_SELF};
                setWord(lettersPicked, ids);
                thirdWordFound = true;
                setBackgroundColorWordFound();
            }
            if (pickedWord.equals(words[3]) && !fourthWordFound) {
                int[] ids = new int[]{R.id.F_in_FLESH, R.id.L_in_FLESH, R.id.E_in_FLESH, R.id.S_in_FLESH, R.id.H_in_FLESH};
                setWord(lettersPicked, ids);
                fourthWordFound = true;
                setBackgroundColorWordFound();
            }
            if (pickedWord.equals(words[4]) && !fifthWordFound) {
                int[] ids = new int[]{R.id.S_in_SHELF, R.id.H_in_SHELF, R.id.E_in_SHELF, R.id.L_in_SHELF, R.id.F_in_SHELF};
                setWord(lettersPicked, ids);
                fifthWordFound = true;
                setBackgroundColorWordFound();
            }
        }
        else //give the user some feedback that they didn't find a word
        {
            setBackgroundColorWordNotFound();
        }
    }

    private void setBackgroundColorWordFound() {
        firstLetterPicked = (TextView) findViewById(R.id.first_letter_picked);
        firstLetterPicked.setBackgroundColor(ContextCompat.getColor(this,R.color.pickedLetterWordFoundBg));

        secondLetterPicked = (TextView) findViewById(R.id.second_letter_picked);
        secondLetterPicked.setBackgroundColor(ContextCompat.getColor(this,R.color.pickedLetterWordFoundBg));

        thirdLetterPicked = (TextView) findViewById(R.id.third_letter_picked);
        thirdLetterPicked.setBackgroundColor(ContextCompat.getColor(this,R.color.pickedLetterWordFoundBg));

        fourthLetterPicked = (TextView) findViewById(R.id.fourth_letter_picked);
        fourthLetterPicked.setBackgroundColor(ContextCompat.getColor(this,R.color.pickedLetterWordFoundBg));

        fifthLetterPicked = (TextView) findViewById(R.id.fifth_letter_picked);
        fifthLetterPicked.setBackgroundColor(ContextCompat.getColor(this,R.color.pickedLetterWordFoundBg));
    }

    private void setBackgroundColorWordNotFound() {
        firstLetterPicked = (TextView) findViewById(R.id.first_letter_picked);
        firstLetterPicked.setBackgroundColor(ContextCompat.getColor(this,R.color.pickedLetterNotAWordBg));

        secondLetterPicked = (TextView) findViewById(R.id.second_letter_picked);
        secondLetterPicked.setBackgroundColor(ContextCompat.getColor(this,R.color.pickedLetterNotAWordBg));

        thirdLetterPicked = (TextView) findViewById(R.id.third_letter_picked);
        thirdLetterPicked.setBackgroundColor(ContextCompat.getColor(this,R.color.pickedLetterNotAWordBg));

        fourthLetterPicked = (TextView) findViewById(R.id.fourth_letter_picked);
        fourthLetterPicked.setBackgroundColor(ContextCompat.getColor(this,R.color.pickedLetterNotAWordBg));

        fifthLetterPicked = (TextView) findViewById(R.id.fifth_letter_picked);
        fifthLetterPicked.setBackgroundColor(ContextCompat.getColor(this,R.color.pickedLetterNotAWordBg));
    }

    public void setWord(List<String> pickedLetters, int[] ids) {
        //set the TextViews to the letters of the word that was found
        int i = 0;
        while (i < pickedLetters.size()) {
            TextView letterTV = (TextView) findViewById(ids[i]);
            letterTV.setText(pickedLetters.get(i));
            i++;
        }
        countWordsFound++;
    }


    // make the letters above the circle that were chosen disappear after 0.5 seconds
    private void clearSelectedLetters() {
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                firstLetterPicked = (TextView) findViewById(R.id.first_letter_picked);
                firstLetterPicked.setVisibility(View.GONE);

                secondLetterPicked = (TextView) findViewById(R.id.second_letter_picked);
                secondLetterPicked.setVisibility(View.GONE);

                thirdLetterPicked = (TextView) findViewById(R.id.third_letter_picked);
                thirdLetterPicked.setVisibility(View.GONE);

                fourthLetterPicked = (TextView) findViewById(R.id.fourth_letter_picked);
                fourthLetterPicked.setVisibility(View.GONE);

                fifthLetterPicked = (TextView) findViewById(R.id.fifth_letter_picked);
                fifthLetterPicked.setVisibility(View.GONE);

                topLeftLetterTv.setTextColor(getResources().getColor(R.color.white));
                topLetterTv.setTextColor(getResources().getColor(R.color.white));
                bottomLeftLetterTv.setTextColor(getResources().getColor(R.color.white));
                bottomRightLetterTv.setTextColor(getResources().getColor(R.color.white));
                topRightLetterTv.setTextColor(getResources().getColor(R.color.white));

            }
        }, 500);

    }
}
