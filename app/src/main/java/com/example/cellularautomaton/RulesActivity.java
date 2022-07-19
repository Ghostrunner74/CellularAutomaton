package com.example.cellularautomaton;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class RulesActivity extends AppCompatActivity {

    private SharedPreferences def_pref;
    LinearLayout ruleBook;
    Button[] rule;
    TableRow[] trRule;

    int clickCounter;
    Intent main;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        def_pref = PreferenceManager.getDefaultSharedPreferences(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);
        setTitle("Rules");
        int numberofrules = Integer.valueOf(def_pref.getString("edit_numberofrules","10"));

        main = new Intent(RulesActivity.this, MainActivity.class);

        ruleBook = findViewById(R.id.ruleBook);
        trRule = new TableRow[numberofrules];

        for(int i = 0; i < numberofrules; i++) {
            trRule[i] = findViewById(R.id.trRule);
        }

        clickCounter = 0;

    }

    public void newRule(View v) {
        trRule[clickCounter] = new TableRow(this);
        ruleBook.addView(trRule[clickCounter]);
        clickCounter++;
    }

    public void saveRules(View v) {
        startActivity(main);
    }
}
