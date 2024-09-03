package com.test.dice;

public class TestRollerFactory {

	public static IDiceRoller getDiceRoller() {
		return new MyDiceRoller();
	}

}
