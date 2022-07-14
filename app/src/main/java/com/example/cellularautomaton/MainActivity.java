package com.example.cellularautomaton;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

public class MainActivity extends AppCompatActivity {

    TableLayout mainTable;
    TableRow tr;
    Button[][] btn;
    Button button;
    Button clrBtn;

    EditText editNumberOfStates;
    EditText editBlockSize;

    int numberOfStates;
    int rows;
    int columns;
    int blockSize;
    boolean active;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        clrBtn = new Button(this);

        clrBtn = findViewById(R.id.clear);
        clrBtn.setEnabled(false);


        mainTable = (TableLayout) findViewById(R.id.mainTable);
        button = (Button) findViewById(R.id.settings);

        editBlockSize = findViewById(R.id.Settings);
        editBlockSize.setText("100");

        editNumberOfStates = findViewById(R.id.editNumberOfStates);
        editNumberOfStates.setText("2");

        blockSize = Integer. valueOf(String.valueOf(editBlockSize.getText()));
        numberOfStates = Integer. valueOf(String.valueOf(editNumberOfStates.getText()));

        active = false;

    }

    public void reference() {
        blockSize = Integer. valueOf(String.valueOf(editBlockSize.getText()));
        numberOfStates = Integer. valueOf(String.valueOf(editNumberOfStates.getText()));

        rows = mainTable.getLayoutParams().height/blockSize;
        columns = mainTable.getLayoutParams().width/blockSize;

        btn = new Button[rows][columns];
    }

    public void openSettings(View v) {
        openRules();
    }

    public void setRandom(View v) {
        initializeState();
    }

    public void clearMainTable(View v) {

        clearMainTable();
    }

    private void openRules() {
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }

    private void initializeState() {
        mainTable.removeAllViews();

        reference();


        for (int i = 0; i < rows; i++) {
            tr = new TableRow(this);
            for (int j = 0; j < columns; j++) {
                btn[i][j] = new Button(this);
                tr.addView(btn[i][j]);
                btn[i][j].setBackgroundColor(getResources().getColor(Rules.randomFill(numberOfStates)));

                int finalI = i;
                int finalJ = j;
                final int[] k = {0};
                btn[i][j].setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        if ( k[0] < numberOfStates && k[0] != 0) {
                            btn[finalI][finalJ].setBackgroundColor(getResources().getColor(Rules.fill(k[0])));
                            k[0]++; // Tell me why
                        }
                        else {
                            k[0] = 0;
                            btn[finalI][finalJ].setBackgroundColor(getResources().getColor(Rules.fill(k[0])));
                            k[0]++;
                        }
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
    }

    private void clearMainTable() {

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                btn[i][j].setBackgroundColor(getResources().getColor(Rules.fill(0)));
            }
        }
    }

}