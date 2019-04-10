package com.example.emily.wordswipe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

// OnTouchListener implemented to register touch events (in this case the swiping) on the
// custom View (the swipe circle where you pick words)
public class LevelActivity extends AppCompatActivity implements View.OnTouchListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);

        // All the textviews for the letters that you can swipe to make words with
        TextView topLetter = (TextView)findViewById(R.id.top_letter_choice);
        TextView topLeftLetter = (TextView)findViewById(R.id.left_top_letter_choice);
        TextView topRightLetter = (TextView)findViewById(R.id.right_top_letter_choice);
        TextView bottomLeftLetter = (TextView)findViewById(R.id.left_bottom_letter_choice);
        TextView bottomRightLetter = (TextView)findViewById(R.id.right_bottom_letter_choice);


        topLetter.setOnTouchListener(this);
        topLeftLetter.setOnTouchListener(this);
    }

    /***** onTouch(View v, MotionEvent event) info *****/
    /* - This method needs to be added when we implement View.OnTouchListener
       - It's similar to the onClick() method for buttons
     It's parameters are: the View v being touched and
        MotionEvent event gives information about the touch on that view (example: coordinates of
     where its being pressed, if it's moving, where it stops etc etc...)
     */
    @Override
    public boolean onTouch(View view, MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_MOVE)
        {
            switch(view.getId()) {
                case R.id.top_letter_choice:
                {
                    Toast.makeText(this, "top letter", Toast.LENGTH_SHORT).show();
                    break;
                }
                case R.id.left_top_letter_choice:
                {
                    Toast.makeText(this, "top left letter", Toast.LENGTH_SHORT).show();
                    break;
                }

            }
        }
        return true;
    }


}
