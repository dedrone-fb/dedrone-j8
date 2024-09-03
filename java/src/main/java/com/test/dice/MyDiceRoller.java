package com.test.dice;

import java.util.Arrays;

public class MyDiceRoller implements IDiceRoller {

	enum Dice {
		D4(4), D6(6), D8(8), D10(10), D12(12), D20(20), D100(100);

		private final int sideCount;

		Dice(final int sideCount) {
			this.sideCount = sideCount;
		}

		public static Dice forSideCount(final int sideCount) {
			return Arrays.stream(Dice.values())
					.filter(dice -> dice.sideCount == sideCount)
					.findFirst()
					.orElseThrow(() -> new IllegalArgumentException("Invalid side count: " + sideCount));
		}

		public int rollOne() {
			return (int) (Math.random() * this.sideCount) + 1;
		}

		public int rollMultiple(final int numberOfRolls) {
			if (numberOfRolls <= 0) {
				throw new IllegalArgumentException("The number of dice must be a positive integer");
			}
			final long maxValue = (long) this.sideCount * (long) numberOfRolls;
			if (maxValue > Integer.MAX_VALUE) {
				throw new IllegalArgumentException("Overflow, result can be greater than Integer.MAX_VALUE");
			}

			int sum = 0;
			for (int i = 0; i < numberOfRolls; i++) {
				sum += rollOne();
			}
			return sum;
		}
	}

	@Override
	public int roll(final int numberOfDice, final int numberOfSidesPerDie) throws IllegalArgumentException {
		return Dice.forSideCount(numberOfSidesPerDie).rollMultiple(numberOfDice);
	}
}
