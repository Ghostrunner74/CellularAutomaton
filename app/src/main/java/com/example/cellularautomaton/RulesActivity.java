package com.example.cellularautomaton;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class RulesActivity extends AppCompatActivity {

    private SharedPreferences def_pref;
    TableLayout ruleBook;
    TableRow trRule;

    Button[] prevState;
    Button[] postState;
    TextView support;

    int clickCounter;
    int buttonSize;
    Intent main;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        def_pref = PreferenceManager.getDefaultSharedPreferences(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);
        setTitle("Rules");

        main = new Intent(RulesActivity.this, MainActivity.class);

        ruleBook = findViewById(R.id.ruleBook);
        int numberofrules = Integer.valueOf(def_pref.getString("edit_numberofrules","10"));

        prevState = new Button[numberofrules];
        postState = new Button[numberofrules];

        buttonSize = 150;
        clickCounter = 0;

    }

    public void newRule(View v) {
        trRule = new TableRow(this);
        ruleBook.addView(trRule);

        prevState[clickCounter] = new Button(this);
        trRule.addView(prevState[clickCounter]);

        LinearLayout.LayoutParams prevParams = (LinearLayout.LayoutParams) prevState[clickCounter].getLayoutParams();
        prevParams.width = buttonSize;
        prevParams.height = buttonSize;
        prevState[clickCounter].setLayoutParams(prevParams);

        support = new TextView(this);
        support.setText(" ----------------> ");
        trRule.addView(support);

        postState[clickCounter] = new Button(this);
        trRule.addView(postState[clickCounter]);

        LinearLayout.LayoutParams postParams = (LinearLayout.LayoutParams) postState[clickCounter].getLayoutParams();
        postParams.width = buttonSize;
        postParams.height = buttonSize;
        postState[clickCounter].setLayoutParams(postParams);

        clickCounter++;
    }

    public void saveRules(View v) {
        startActivity(main);
    }
}
