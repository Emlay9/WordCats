package com.example.emily.wordswipe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

// OnTouchListener implemented to register touch events (in this case the swiping) on the
// custom View (the swipe circle where you pick words)
public class LevelActivity extends AppCompatActivity implements View.OnTouchListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);

    }

    /***** onTouch(View v, MotionEvent event) info *****/
    /* - This method needs to be added when we implement View.OnTouchListener
       - It's similar to the onClick() method for buttons
     It's parameters are: the View v being touched and
        MotionEvent event gives information about the touch on that view (example: coordinates of
     where its being pressed, if it's moving, where it stops etc etc...)
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        event.getAction();
        return false;
    }


}
