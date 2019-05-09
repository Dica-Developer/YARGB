package com.yarpg.core.util;

import static org.junit.Assert.fail;

import org.junit.Test;

public class MathsTest {
    @Test
    public void randomNumber() {
        for (int i = 0; i < 100000000; i++) {
            double min = 0.0013;
            double max = 0.001369;
            double randomFloat = Maths.randomFloat(min, max);
            if (randomFloat < min || randomFloat > max) {
                fail("Number " + randomFloat + " not in between or equal " + min + " and " + max);
            }
        }

        for (int i = 0; i < 100000000; i++) {
            double min = -0.25;
            double max = 0.25;
            double randomFloat = Maths.randomFloat(min, max);
            if (randomFloat < min || randomFloat > max) {
                fail("Number " + randomFloat + " not in between or equal " + min + " and " + max);
            }
        }
    }
}
