package com.test.dice;

public class BrokenDiceRoller implements IDiceRoller {
    public int roll(int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException("The number of dice must be a positive integer");
        } else if (i2 >= 4 && i2 <= 100) {
            return ((int) Math.floor(Math.random() * ((double) i2) * ((double) i))) + 1;
        } else {
            throw new IllegalArgumentException("The number of sides per die supplied is invalid");
        }
    }
}