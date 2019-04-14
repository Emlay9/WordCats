package com.example.emily.wordswipe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LevelWonActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_won);

        Button nextLevelBtn = (Button)findViewById(R.id.next_level_button);
        nextLevelBtn.setOnClickListener(this);
        Button homeBtn = (Button)findViewById(R.id.main_menu_btn);
        homeBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.next_level_button:
            {
                int level = getIntent().getIntExtra("level", 0);
                if(level ==1)
                {
                    Intent intent = new Intent(LevelWonActivity.this, LevelTwoActivity.class);
                    startActivity(intent);
                }
                if(level == 2)
                {
                    Intent intent = new Intent(LevelWonActivity.this, LevelThreeActivity.class);
                    startActivity(intent);
                }
                break;
            }
            case R.id.main_menu_btn:
            {
                Intent intent = new Intent(LevelWonActivity.this, TitleScreenActivity.class);
                startActivity(intent);
            }
        }
    }
}
