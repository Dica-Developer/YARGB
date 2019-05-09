package com.yarpg.core.util;

import java.util.Random;

public class Maths {

    private static Random randomValue = new Random();

    public static double randomFloat(double min, double max) {
        double precision = 1000000D;
        double number = randomValue.nextInt((int) ((max - min) * precision + 1)) + min * precision;
        return number / precision;
    }
}