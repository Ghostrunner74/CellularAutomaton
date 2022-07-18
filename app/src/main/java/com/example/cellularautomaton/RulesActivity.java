package com.example.cellularautomaton;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RulesActivity extends AppCompatActivity {

    private SharedPreferences def_pref;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Rules");
        setContentView(R.layout.activity_rules);

        def_pref = PreferenceManager.getDefaultSharedPreferences(this);
        LinearLayout rulebook = findViewById(R.id.ruleBook);

        int numberOfRules =  Integer.valueOf(def_pref.getString("edit_numberofrules","5"));
        Button[] btn = new Button[numberOfRules];

        for (int i = 0; i < numberOfRules; i++) {
            btn[i] = new Button(this);
            btn[i].setText("Rule " + (i+1));
            rulebook.addView(btn[i]);
        }
    }


}
