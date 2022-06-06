package com.ghtk.recycleview.utils;

import android.graphics.Color;

import java.text.NumberFormat;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by SonNM on 6/5/2022.
 */

public class Utils {
    public static int getColorRamdom(){
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        return color;
    }
    public static int getNumberRamdom(int minLenght , int maxLenght){
        Random rnd = new Random();
        int count = rnd.nextInt(maxLenght-minLenght);
        String text ="";
        for (int i = 0; i < count; i++) {
            text += i+"";
        }
        return Integer.parseInt(text);
    }
}
