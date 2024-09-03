package com.test.dice;

/**
 * <p>
 * An interface for a dice roller. Simulates rolling one or more polyhedral dice, like those used in Dungeons & Dragons.
 * </p>
 */
public interface IDiceRoller
{
	/**
	 * Simulates rolling a number of Dungeons & Dragons dice.
	 * 
	 * @param numberOfDice The number of dice to roll. Must be a positive integer.
	 * @param numberOfSidesPerDie The number of sides on each die. The dice can have 4, 6, 8, 10, 12, 20, or 100 sides.
	 * @return The sum of the rolled dice
	 * @throws IllegalArgumentException if either of the passed in parameters is invalid
	 */
	public int roll(int numberOfDice, int numberOfSidesPerDie) throws IllegalArgumentException;
}
