package com.example.myfirstgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class GameActivity extends AppCompatActivity implements View.OnClickListener{

    Random rnd = new Random();
    int rightAnswer;
    TextView num1Txt;
    TextView num2Txt;
    TextView scoreTxt;
    TextView maxTxt;
    TextView signTxt;
    int maxNum;
    Button btn1;
    Button btn2;
    Button btn3;
    int currentScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        currentScore = 0;
        num1Txt = (TextView)findViewById(R.id.num1TextView);
        num2Txt = (TextView)findViewById(R.id.num2TextView);
        scoreTxt = (TextView)findViewById(R.id.scoreTextView);
        maxTxt = (TextView)findViewById(R.id.maxTextView);
        signTxt = (TextView)findViewById(R.id.signTextView);
        Bundle localBundle = getIntent().getExtras();
        String maxNumStr = localBundle.getString("maxNum");
        maxNum = Integer.parseInt(maxNumStr);
        maxTxt.setText("Max: " + maxNum);
        btn1 = (Button)findViewById(R.id.choice1Button);
        btn2 = (Button)findViewById(R.id.choice2Button);
        btn3 = (Button)findViewById(R.id.choice3Button);
        scoreTxt.setText("Score: " + currentScore);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        setQuestion();
    }

    void setQuestion()
    {
        int num1 = rnd.nextInt(maxNum) + 1;
        int num2 = rnd.nextInt(maxNum) + 1;
        int operation = rnd.nextInt(3);
        int wrongAnswer1 = 0;
        int wrongAnswer2 = 0;
        rightAnswer = 0;
        switch (operation)
        {
            case 0: //*
                rightAnswer = num1 * num2;
                wrongAnswer1 = rightAnswer - 3;
                wrongAnswer2 = rightAnswer + 3;
                signTxt.setText("*");
                break;
            case 1: //+
                rightAnswer = num1 + num2;
                wrongAnswer1 = rightAnswer - 3;
                wrongAnswer2 = rightAnswer + 3;
                signTxt.setText("+");
                break;
            case 2: //-
                rightAnswer = num1 - num2;
                wrongAnswer1 = rightAnswer - 3;
                wrongAnswer2 = rightAnswer + 3;
                signTxt.setText("-");
                break;
        }
        num1Txt.setText("" + num1);
        num2Txt.setText("" + num2);
        int choiceBtn = rnd.nextInt(3);
        switch (choiceBtn)
        {
            case 0:
                btn1.setText("" + rightAnswer);
                btn2.setText("" + wrongAnswer1);
                btn3.setText("" + wrongAnswer2);
                break;
            case 1:
                btn1.setText("" + wrongAnswer1);
                btn2.setText("" + rightAnswer);
                btn3.setText("" + wrongAnswer2);
                break;
            case 2:
                btn1.setText("" + wrongAnswer1);
                btn2.setText("" + wrongAnswer2);
                btn3.setText("" + rightAnswer);
                break;
        }
    }

    boolean isCorrect(int answerGiven)
    {
        if (answerGiven == rightAnswer)
            Toast.makeText(getApplicationContext(), "Right!", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getApplicationContext(), "Wrong!", Toast.LENGTH_SHORT).show();
        return answerGiven == rightAnswer;
    }

    void updateScore(int answerGiven)
    {
        if (isCorrect(answerGiven)) {
            currentScore++;
            scoreTxt.setText("Score: " + currentScore);
        }
    }

    @Override
    public void onClick(View view) {
        int answer = 0;
        switch (view.getId())
        {
            case R.id.choice1Button:
                answer = Integer.parseInt("" + btn1.getText());
                break;
            case R.id.choice2Button:
                answer = Integer.parseInt("" + btn2.getText());
                break;
            case R.id.choice3Button:
                answer = Integer.parseInt("" + btn3.getText());
                break;
        }
        updateScore(answer);
        setQuestion();
    }
}