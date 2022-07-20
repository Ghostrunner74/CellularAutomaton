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
    Button[] inputState;
    Button[] logicOperator;

    String[] logicOperatorValue;

    TextView support;
    EditText[] inputNumber;

    int clickCounter;
    int buttonSize;
    int numberOfStates;
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
        numberOfStates = Integer. valueOf(def_pref.getString("edit_numberofstates","2"));
        logicOperatorValue = new String[]{"=", "<", ">"};


        prevState = new Button[numberofrules];
        postState = new Button[numberofrules];
        logicOperator = new Button[numberofrules];
        inputNumber = new EditText[numberofrules];
        inputState = new Button[numberofrules];

        buttonSize = 150;
        clickCounter = 0;

    }

    public void newRule(View v) {
        trRule = new TableRow(this);
        ruleBook.addView(trRule);

        prevState[clickCounter] = new Button(this);
        trRule.addView(prevState[clickCounter]);

        final int[] i = {0};
        prevState[clickCounter].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if ( i[0] < numberOfStates && i[0] != 0) {
                    v.setBackgroundColor(getResources().getColor(Rules.fill(i[0])));
                    i[0]++; // Tell me why
                }
                else {
                    i[0] = 0;
                    v.setBackgroundColor(getResources().getColor(Rules.fill(i[0])));
                    i[0]++;
                }
            }
        });

        LinearLayout.LayoutParams prevParams = (LinearLayout.LayoutParams) prevState[clickCounter].getLayoutParams();
        prevParams.width = buttonSize;
        prevParams.height = buttonSize;
        prevState[clickCounter].setLayoutParams(prevParams);

        support = new TextView(this);
        support.setText(" ----------------> ");
        trRule.addView(support);

        postState[clickCounter] = new Button(this);
        trRule.addView(postState[clickCounter]);

        final int[] j = {0};
        postState[clickCounter].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if ( j[0] < numberOfStates && j[0] != 0) {
                    v.setBackgroundColor(getResources().getColor(Rules.fill(j[0])));
                    j[0]++; // Tell me why
                }
                else {
                    j[0] = 0;
                    v.setBackgroundColor(getResources().getColor(Rules.fill(j[0])));
                    j[0]++;
                }
            }
        });

        LinearLayout.LayoutParams postParams = (LinearLayout.LayoutParams) postState[clickCounter].getLayoutParams();
        postParams.width = buttonSize;
        postParams.height = buttonSize;
        postState[clickCounter].setLayoutParams(postParams);

        support = new TextView(this);
        support.setText(" if ");
        trRule.addView(support);

        logicOperator[clickCounter] = new Button(this);
        trRule.addView(logicOperator[clickCounter]);

        final int[] l = {0};
        logicOperator[clickCounter].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                int clickCounter = RulesActivity.this.clickCounter;

                Button button = (Button) v;

                if ( l[0] < logicOperatorValue.length && l[0] != 0) {
                    button.setText(logicOperatorValue[l[0]]);
                    l[0]++; // Tell me why
                }
                else {
                    l[0] = 0;
                    button.setText(logicOperatorValue[l[0]]);
                    l[0]++;
                }
            }
        });

        LinearLayout.LayoutParams logicParams = (LinearLayout.LayoutParams) logicOperator[clickCounter].getLayoutParams();
        logicParams.width = buttonSize;
        logicParams.height = buttonSize;
        logicOperator[clickCounter].setLayoutParams(logicParams);

        inputNumber[clickCounter] = new EditText(this);
        trRule.addView(inputNumber[clickCounter]);

        inputState[clickCounter] = new Button(this);
        trRule.addView(inputState[clickCounter]);

        final int[] k = {0};
        inputState[clickCounter].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if ( k[0] < numberOfStates && k[0] != 0) {
                    v.setBackgroundColor(getResources().getColor(Rules.fill(k[0])));
                    k[0]++; //
                }
                else {
                    k[0] = 0;
                    v.setBackgroundColor(getResources().getColor(Rules.fill(k[0])));
                    k[0]++;
                }
            }
        });

        LinearLayout.LayoutParams inputParams = (LinearLayout.LayoutParams) inputState[clickCounter].getLayoutParams();
        inputParams.width = buttonSize;
        inputParams.height = buttonSize;
        postState[clickCounter].setLayoutParams(inputParams);

        clickCounter++;

    }

    public void saveRules(View v) {
        startActivity(main);
    }
}
