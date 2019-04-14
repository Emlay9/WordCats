package com.example.emily.wordswipe;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LevelThreeActivity extends AppCompatActivity implements View.OnTouchListener{

    private static final int WORDSINTHELEVEL = 5;
    private static final String topLetter = "E";
    private static final String topLeftLetter = "S";
    private static final String bottomLeftLetter = "G";
    private static final String bottomRightLetter = "S";
    private static final String topRightLetter = "U";

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

    View swipeCircle;
    TextView firstLetterPicked;
    TextView secondLetterPicked;
    TextView thirdLetterPicked;
    TextView fourthLetterPicked;
    TextView fifthLetterPicked;

    int countLettersPicked = 0;
    int countWordsFound = 0;
    List<String> lettersPicked = new ArrayList<>();

    String[] words = new String[]{"SUE", "USE", "SUES", "USES", "GUESS"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_three);
        countLettersPicked = 0;

        swipeCircle = findViewById(R.id.letter_choice_circle);
        swipeCircle.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        resetBackgroundColor();
        if (view.getId() == R.id.letter_choice_circle) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_MOVE: {
                    float x = event.getX();
                    float y = event.getY();
                    //Only allow up to 5 letters picked
                    getLettersPicked(x, y);
                    break;
                }
                case MotionEvent.ACTION_UP: {
                    checkForMatch();
                    clearSelectedLetters();
                    countLettersPicked = 0;
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
            Intent levelComplete = new Intent(this, WinActivity.class);
            startActivity(levelComplete);
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

        if (countLettersPicked <= 5) {
            if (x < 145 && y < 390 && y > 240 && !topLeftLetterPicked) {
                lettersPicked.add(countLettersPicked, topLeftLetter);
                topLeftLetterPicked = true;
                countLettersPicked++;
                displayLetterChoice(lettersPicked, countLettersPicked);
            }
            if (x > 290 && x < 450 && y < 180 && !topLetterPicked) {
                lettersPicked.add(countLettersPicked, topLetter);
                topLetterPicked = true;
                countLettersPicked++;
                displayLetterChoice(lettersPicked, countLettersPicked);
            }
            if (x > 175 && x < 250 && y > 555 && !bottomLeftLetterPicked) {
                lettersPicked.add(countLettersPicked, bottomLeftLetter);
                bottomLeftLetterPicked = true;
                countLettersPicked++;
                displayLetterChoice(lettersPicked, countLettersPicked);
            }
            if (x > 530 && x < 650 && y > 535 && !bottomRightLetterPicked) {
                lettersPicked.add(countLettersPicked, bottomRightLetter);
                bottomRightLetterPicked = true;
                countLettersPicked++;
                displayLetterChoice(lettersPicked, countLettersPicked);
            }
            if (x > 595 && y > 260 && y < 390 && !topRightLetterPicked) {
                lettersPicked.add(countLettersPicked, topRightLetter);
                topRightLetterPicked = true;
                countLettersPicked++;
                displayLetterChoice(lettersPicked, countLettersPicked);
            }
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
        StringBuilder sb = new StringBuilder();
        for (String letters : lettersPicked) {
            sb.append(letters);
        }
        String pickedWord = sb.toString();

        if (Arrays.asList(words).contains(pickedWord)) {
            if (pickedWord.equals(words[0]) && !firstWordFound) {
                int[] ids = new int[]{R.id.S_in_SUE, R.id.U_in_SUE, R.id.E_in_SUE};
                setWord(lettersPicked, ids);
                firstWordFound = true;
                setBackgroundColorWordFound();
            }
            if (pickedWord.equals(words[1]) && !secondWordFound) {
                int[] ids = new int[]{R.id.U_in_USE, R.id.S_in_USE, R.id.E_in_USE};
                setWord(lettersPicked, ids);
                secondWordFound = true;
                setBackgroundColorWordFound();
            }
            if (pickedWord.equals(words[2]) && !thirdWordFound) {
                int[] ids = new int[]{R.id.S_in_SUES, R.id.U_in_SUES, R.id.E_in_SUES, R.id.second_S_in_SUES};
                setWord(lettersPicked, ids);
                thirdWordFound = true;
                setBackgroundColorWordFound();
            }
            if (pickedWord.equals(words[3]) && !fourthWordFound) {
                int[] ids = new int[]{R.id.U_in_USES, R.id.S_in_USES, R.id.E_in_USES, R.id.second_S_in_USES};
                setWord(lettersPicked, ids);
                fourthWordFound = true;
                setBackgroundColorWordFound();
            }
            if (pickedWord.equals(words[4]) && !fifthWordFound) {
                int[] ids = new int[]{R.id.G_in_GUESS, R.id.U_in_GUESS, R.id.E_in_GUESS, R.id.S_in_GUESS, R.id.second_S_in_GUESS};
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
            }
        }, 500);

    }
}
