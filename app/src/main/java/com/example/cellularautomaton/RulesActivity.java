package com.example.cellularautomaton;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RulesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Rules");
        setContentView(R.layout.activity_rules);
    }


    public void newRule(View v) {
        LinearLayout rulebook = findViewById(R.id.ruleBook);
        Button[] btn = new Button[10];

        btn[1] = new Button(this);
        rulebook.addView(btn[1]);
    }


}
