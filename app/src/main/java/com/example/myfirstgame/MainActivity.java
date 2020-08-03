package com.example.myfirstgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button playBtn = (Button)findViewById(R.id.playButton);
        Button exitBtn = (Button)findViewById(R.id.exitButton);
        playBtn.setOnClickListener(this);
        exitBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        boolean exit = false;
        switch (view.getId())
        {
            case R.id.playButton:
                EditText maxNumTxt = (EditText)findViewById(R.id.maxEditTextNumberDecimal);
                String maxNumStr = maxNumTxt.getText().toString();
                Intent activityIntent = new Intent(this, GameActivity.class);
                Bundle activityBundle = new Bundle();
                activityBundle.putString("maxNum", maxNumStr);
                activityIntent.putExtras(activityBundle);
                startActivity(activityIntent);
                break;
            case R.id.exitButton:
                exit = true;
                break;
        }
        if (exit)
            super.finish();
    }
}