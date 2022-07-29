package com.example.cellularautomaton;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class RulesActivity extends MainActivity {

    private SharedPreferences def_pref;
    TableLayout ruleBook;
    TableRow[] trRule;

    public static Button[] prevState;
    public static Button[] postState;
    public static Button[] inputState;
    public static Button[] logicOperator;
    public static Button deleteRule;

    public static String[] logicOperatorValue;

    TextView support;
    public static EditText[] inputNumber;

    public static int clickCounter;
    int buttonSize;
    int numberOfStates;
    int numberofrules;
    Intent main;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        def_pref = PreferenceManager.getDefaultSharedPreferences(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);
        setTitle("Rules");

        main = new Intent(RulesActivity.this, MainActivity.class);

        ruleBook = findViewById(R.id.ruleBook);

        deleteRule = findViewById(R.id.deleteButton);
        deleteRule.setEnabled(false);

        numberofrules = Integer.valueOf(def_pref.getString("edit_numberofrules","10"));
        numberOfStates = Integer. valueOf(def_pref.getString("edit_numberofstates","2"));
        logicOperatorValue = new String[]{"=", "<", ">"};


        prevState = new Button[numberofrules];
        postState = new Button[numberofrules];
        logicOperator = new Button[numberofrules];
        inputNumber = new EditText[numberofrules];
        inputState = new Button[numberofrules];

        trRule = new TableRow[numberofrules];

        buttonSize = 150;
        clickCounter = 0;

    }

    private void setButtonSize(Button button) {
        LinearLayout.LayoutParams btnParams = (LinearLayout.LayoutParams) button.getLayoutParams();
        btnParams.width = buttonSize;
        btnParams.height = buttonSize;
        button.setLayoutParams(btnParams);
    }


    public void newRule(View v) {

        if (clickCounter < numberofrules) {

            trRule[clickCounter] = new TableRow(this);
            ruleBook.addView(trRule[clickCounter]);

            prevState[clickCounter] = new Button(this);
            trRule[clickCounter].addView(prevState[clickCounter]);

            final int[] i = {0};
            prevState[clickCounter].setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (i[0] < numberOfStates && i[0] != 0) {
                        v.setBackgroundColor(getResources().getColor(Rules.fill(i[0])));
                        i[0]++; // Tell me why
                    } else {
                        i[0] = 0;
                        v.setBackgroundColor(getResources().getColor(Rules.fill(i[0])));
                        i[0]++;
                    }
                }
            });

            setButtonSize(prevState[clickCounter]);

            support = new TextView(this);
            support.setText(" ----------------> ");
            trRule[clickCounter].addView(support);

            postState[clickCounter] = new Button(this);
            trRule[clickCounter].addView(postState[clickCounter]);

            final int[] j = {0};
            postState[clickCounter].setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (j[0] < numberOfStates && j[0] != 0) {
                        v.setBackgroundColor(getResources().getColor(Rules.fill(j[0])));
                        j[0]++; // Tell me why
                    } else {
                        j[0] = 0;
                        v.setBackgroundColor(getResources().getColor(Rules.fill(j[0])));
                        j[0]++;
                    }
                }
            });

            setButtonSize(postState[clickCounter]);

            support = new TextView(this);
            support.setText(" if ");
            trRule[clickCounter].addView(support);

            logicOperator[clickCounter] = new Button(this);
            trRule[clickCounter].addView(logicOperator[clickCounter]);

            final int[] l = {0};
            logicOperator[clickCounter].setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    Button button = (Button) v;

                    if (l[0] < logicOperatorValue.length && l[0] != 0) {
                        button.setText(logicOperatorValue[l[0]]);
                        l[0]++; // Tell me why
                    } else {
                        l[0] = 0;
                        button.setText(logicOperatorValue[l[0]]);
                        l[0]++;
                    }
                }
            });

            setButtonSize(logicOperator[clickCounter]);

            inputNumber[clickCounter] = new EditText(this);
            trRule[clickCounter].addView(inputNumber[clickCounter]);

            inputState[clickCounter] = new Button(this);
            trRule[clickCounter].addView(inputState[clickCounter]);

            final int[] k = {0};
            inputState[clickCounter].setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (k[0] < numberOfStates && k[0] != 0) {
                        v.setBackgroundColor(getResources().getColor(Rules.fill(k[0])));
                        k[0]++; //
                    } else {
                        k[0] = 0;
                        v.setBackgroundColor(getResources().getColor(Rules.fill(k[0])));
                        k[0]++;
                    }
                }
            });

            setButtonSize(inputState[clickCounter]);

            trRule[clickCounter].setGravity(Gravity.CENTER);

            deleteRule.setEnabled(true);

            clickCounter++;

        }
        else {
            Toast.makeText(this,"Maximum number of rules has been reached",Toast.LENGTH_SHORT).show();
        }

    }

    public void  deleteRule(View v) {
        clickCounter--;
        ruleBook.removeView(trRule[clickCounter]);

        if (clickCounter == 0) {
            deleteRule.setEnabled(false);
        }
        else {
            deleteRule.setEnabled(true);
        }
    }

    public void saveRules(View v) {
        main.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(main);
    }

}
