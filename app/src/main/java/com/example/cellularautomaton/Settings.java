package com.example.cellularautomaton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.Objects;

public class Settings extends AppCompatActivity {

    LinearLayout ruleBook;
    Button newRule;
    Button[] rule = new Button[10]; // use wrapper?

    int clickCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setTitle("Settings");

        if (savedInstanceState != null) {
            clickCounter = savedInstanceState.getInt("clickCounter", 0);
        } else {
            clickCounter = 0;
        }

        newRule = findViewById(R.id.addRule);
        ruleBook = findViewById(R.id.ruleBook);

    }

    public void addNewRule(View v) {
        rule[clickCounter] = new Button(this);
        ruleBook.addView(rule[clickCounter]);
        clickCounter++;
        rule[clickCounter - 1].setText("Rule " + clickCounter);
    }



    public void saveState(View v) {
        onPause();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("clickCounter", clickCounter);

//        for (int i = 0; i < rule.length; i++) {
//
//        }
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        clickCounter = savedInstanceState.getInt("clickCounter");
    }


}