package com.example.cellularautomaton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class Settings extends AppCompatActivity {

    LinearLayout ruleBook;
    Button newRule;

    int clickCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setTitle("Settings");

        int clickCounter = 0;

        newRule = findViewById(R.id.addRule);
        ruleBook = findViewById(R.id.ruleBook);
        

    }



    public void addNewRule(View v) {
        Button rule = new Button(this);
        ruleBook.addView(rule);
        clickCounter++;
        rule.setText("Rule " + clickCounter);
    }



    public void saveSettings() {


    }
}