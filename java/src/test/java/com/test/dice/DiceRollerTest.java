package com.test.dice;

import static com.test.dice.TestRollerFactory.getDiceRoller;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;

import java.util.Arrays;
import java.util.List;
import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.Combinators;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.Provide;
import net.jqwik.api.constraints.IntRange;

class DiceRollerTest {

	static class RollerInput {

		final int rolls;
		final int sideCount;

		public RollerInput(final int rolls, final int sideCount) {
			this.rolls = rolls;
			this.sideCount = sideCount;
		}

		@Override
		public String toString() {
			return rolls + " rolls with a D" + sideCount;
		}
	}

	final List<Integer> allowedSideCount = Arrays.asList(4, 6, 8, 10, 12, 20, 100);

	@Provide
	Arbitrary<Integer> allowedSideCounts() {
		return Arbitraries.of(allowedSideCount);
	}

	@Provide
	Arbitrary<Integer> disallowedSideCounts() {
		return Arbitraries.integers().filter(i -> !allowedSideCount.contains(i));
	}

	@Provide
	Arbitrary<RollerInput> allRollerInputs() {
		return Combinators.combine(
						allowedSideCounts(),
						Arbitraries.integers().between(1, Integer.MAX_VALUE)
				)
				.as((sideCount, rolls) -> new RollerInput(rolls, sideCount));
	}

	@Provide
	Arbitrary<RollerInput> overflowingRollerInputs() {
		return allRollerInputs().filter(i -> ((long) i.rolls) * ((long) i.sideCount) > Integer.MAX_VALUE);
	}

	@Provide
	Arbitrary<RollerInput> nonOverflowingRollerInputs() {
		return allRollerInputs().filter(i -> ((long) i.rolls) * ((long) i.sideCount) <= Integer.MAX_VALUE);
	}

	@Property
	void validSideCounts(@ForAll("allowedSideCounts") int sideCount) {
		final IDiceRoller diceRoller = getDiceRoller();
		assertThatNoException().isThrownBy(() -> diceRoller.roll(1, sideCount));
	}

	@Property
	void invalidSideCounts(@ForAll("disallowedSideCounts") int sideCount) {
		final IDiceRoller diceRoller = getDiceRoller();
		assertThatExceptionOfType(IllegalArgumentException.class)
				.isThrownBy(() -> diceRoller.roll(1, sideCount))
				.withMessage("Invalid side count: " + sideCount);
	}

	@Property
	void noNegativeRolls(@ForAll @IntRange(min = Integer.MIN_VALUE, max = 0) int rolls) {
		final IDiceRoller diceRoller = getDiceRoller();
		assertThatExceptionOfType(IllegalArgumentException.class)
				.isThrownBy(() -> diceRoller.roll(rolls, 4))
				.withMessage("The number of dice must be a positive integer");
	}

	@Property
	void validResults(@ForAll("nonOverflowingRollerInputs") RollerInput input) {
		final IDiceRoller diceRoller = getDiceRoller();
		assertThat(diceRoller.roll(input.rolls, input.sideCount)).isBetween(input.rolls, input.rolls * input.sideCount);
	}

	@Property
	void overflowResults(@ForAll("overflowingRollerInputs") RollerInput input) {
		final IDiceRoller diceRoller = getDiceRoller();
		assertThatExceptionOfType(IllegalArgumentException.class)
				.isThrownBy(() -> diceRoller.roll(input.rolls, input.sideCount))
				.withMessage("Overflow, result can be greater than Integer.MAX_VALUE");
	}

}
