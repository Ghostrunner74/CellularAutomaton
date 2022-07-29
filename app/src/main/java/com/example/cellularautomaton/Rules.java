package com.example.cellularautomaton;

import android.graphics.Color;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class Rules extends MainActivity{

    static Random rng = new Random();

    public static int allStates(int State) {
        int[] userClr = new int[10];

        userClr[0] = R.color.white;
        userClr[1] = R.color.red;
        userClr[2] = R.color.purple_700;
        userClr[3] = R.color.purple_500;
        userClr[4] = R.color.purple_200;
        userClr[5] = R.color.teal_700;
        userClr[6] = R.color.orange;
        userClr[7] = R.color.black;
        userClr[8] = R.color.teal_200;
        userClr[9] = R.color.jade;


        return userClr[State];
    }


    public static int randomFill(int i) {
            return allStates(rng.nextInt(i));
    }

    public static int fill(int i) {
        return allStates(i);
    }

}


