package com.example.cellularautomaton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.Display;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences def_pref;

    TableLayout mainTable;
    TableRow tr;

    static Button[][] btn;
    Button clrBtn;
    Button startBtn;
    Button newStateBtn;

    Intent rules;

    static int[][] blockNewState;

    int numberOfStates;
    int neighborDistance;
    int rows;
    int columns;
    int blockSize;
    int delay;

    boolean active;
    boolean btnState;


    private Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        init();
    }

    public void init() {

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;

        def_pref = PreferenceManager.getDefaultSharedPreferences(this);
        rules = new Intent(MainActivity.this,RulesActivity.class);

        clrBtn = new Button(this);

        newStateBtn = findViewById(R.id.newState);
        newStateBtn.setEnabled(false);

        startBtn = findViewById(R.id.start);
        startBtn.setEnabled(false);

        clrBtn = findViewById(R.id.clear);
        clrBtn.setEnabled(false);

        mainTable = (TableLayout) findViewById(R.id.mainTable);
        RelativeLayout.LayoutParams tableParams = (RelativeLayout.LayoutParams)  mainTable.getLayoutParams();
        tableParams.width = width;
        tableParams.height = width;
        mainTable.setLayoutParams(tableParams);

        active = false;
        btnState = false;
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_settings) {
            Intent intent = new Intent(MainActivity.this,SettingsActivity.class);
            startActivity(intent);
        }

        if (id == R.id.menu_rules) {
            rules.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(rules);
        }

        return super.onOptionsItemSelected(item);
    }

    public void reference() {
        blockSize =  Integer.parseInt(def_pref.getString("edit_blocksize","100"));
        numberOfStates = Integer.parseInt(def_pref.getString("edit_numberofstates","2"));
        delay = Integer.parseInt(def_pref.getString("edit_delaytime","1000"));
        neighborDistance = Integer.parseInt(def_pref.getString("edit_neighbordistance","1"));;

        rows = mainTable.getLayoutParams().height/blockSize;
        columns = mainTable.getLayoutParams().width/blockSize;

        btn = new Button[rows][columns];
        blockNewState = new int[rows][columns];
    }

    public void setRandom(View v) {
        initializeState();
    }

    public void clearMainTable(View v) {
        clearMainTable();
    }



    private void initializeState() {
        mainTable.removeAllViews();

        reference();


        for (int i = 0; i < rows; i++) {
            tr = new TableRow(this);
            for (int j = 0; j < columns; j++) {

                blockNewState[i][j] = getResources().getColor(Rules.fill(0));

                btn[i][j] = new Button(this);
                tr.addView(btn[i][j]);
                btn[i][j].setBackgroundColor(getResources().getColor(Rules.randomFill(numberOfStates)));

                final int[] k = {0};
                btn[i][j].setOnClickListener(
                        v -> {
                            if ( k[0] < numberOfStates && k[0] != 0) {
                                v.setBackgroundColor(getResources().getColor(Rules.fill(k[0])));
                                k[0]++; // Tell me why
                            }
                            else {
                                k[0] = 0;
                                v.setBackgroundColor(getResources().getColor(Rules.fill(k[0])));
                                k[0]++;
                            }
                        });

                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) btn[i][j].getLayoutParams();
                params.width = blockSize;
                params.height = blockSize;
                btn[i][j].setLayoutParams(params);

            }
            mainTable.addView(tr);
            tr.setGravity(Gravity.CENTER);
        }
        clrBtn.setEnabled(true);
        startBtn.setEnabled(true);
        newStateBtn.setEnabled(true);
    }

    private void clearMainTable() {

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                btn[i][j].setBackgroundColor(getResources().getColor(Rules.fill(0)));
                blockNewState[i][j] = getResources().getColor(Rules.fill(0));
            }
        }
    }


    public int calculateInputBlocks(int n,int i, int j) { // don't calculate middle square

    int sum;
    sum = 0;

        if (((ColorDrawable) RulesActivity.inputState[n].getBackground()).getColor()
                == ((ColorDrawable) RulesActivity.btn[i][j].getBackground()).getColor()) {
            sum--;
        }

        for (int li = i - neighborDistance; li <= i + neighborDistance; li++) {
            for (int lj = j - neighborDistance; lj <= j + neighborDistance; lj++ ) {
                try {
                    if (((ColorDrawable) RulesActivity.inputState[n].getBackground()).getColor()
                            == ((ColorDrawable) RulesActivity.btn[li][lj].getBackground()).getColor()) {
                        sum++;
                    }

                }
                catch (Exception e) {
                    sum += 0;
                }
            }
        }

        return sum;

    }

    public void chooseOperator() { //How automaton threat unknown cells?

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                blockNewState[i][j] = ((ColorDrawable) RulesActivity.btn[i][j].getBackground()).getColor();
            }
        }

    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < columns; j++) {
            for (int n = 0; n < RulesActivity.clickCounter; n++) {
                    int sum = calculateInputBlocks(n,i,j);//
                    switch (String.valueOf(RulesActivity.logicOperator[n].getText()).trim()) {
                        // add 2 (or 3) for loops and an array to store new state of table
                        case "<":
                            if (sum < Integer.valueOf(RulesActivity.inputNumber[n].getText().toString())
                                    && ((ColorDrawable) RulesActivity.prevState[n].getBackground()).getColor() == ((ColorDrawable) RulesActivity.btn[i][j].getBackground()).getColor()) {
                                blockNewState[i][j] = ((ColorDrawable) RulesActivity.postState[n].getBackground()).getColor();
                            }
                        break;
                        case ">":
                            if (sum > Integer.valueOf(RulesActivity.inputNumber[n].getText().toString())
                                    && ((ColorDrawable) RulesActivity.prevState[n].getBackground()).getColor() == ((ColorDrawable) RulesActivity.btn[i][j].getBackground()).getColor()) {
                                blockNewState[i][j] = ((ColorDrawable) RulesActivity.postState[n].getBackground()).getColor();
                            }
                        break;
                        case "=":
                            if (sum == Integer.valueOf(RulesActivity.inputNumber[n].getText().toString())
                                    && ((ColorDrawable) RulesActivity.prevState[n].getBackground()).getColor() == ((ColorDrawable) RulesActivity.btn[i][j].getBackground()).getColor()) {
                                blockNewState[i][j] = ((ColorDrawable) RulesActivity.postState[n].getBackground()).getColor();
                            }
                        break;
                        default: break;
                    }
                }
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                btn[i][j].setBackgroundColor(blockNewState[i][j]);
            }
        }
    }


    public void newState(View v) {
        chooseOperator();

//        Toast.makeText(this,"i = " + RulesActivity.inputNumber[0].getText().toString(),Toast.LENGTH_SHORT).show();
//       Toast.makeText(this,"i = " + i,Toast.LENGTH_SHORT).show();
    }

    private Runnable newStateRunnable = new Runnable(){
        @Override
        public void run() {
            chooseOperator();
            handler.postDelayed(newStateRunnable,delay);
        }
    };

    public void startGame(View v) { // add thread

        if (!btnState) {
            startBtn.setText("Stop");
            newStateRunnable.run();
        }
        else {
            startBtn.setText("Start");
            handler.removeCallbacks(newStateRunnable);
        }

        btnState = !btnState;
    }

}