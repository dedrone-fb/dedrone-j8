package com.test.dice;

import static com.test.dice.TestRollerFactory.getDiceRoller;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import java.util.HashMap;
import java.util.Map;
import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;

class DiceRollerStochasticTest {

	private static final int ROLL_COUNT = 1_000_000;

	@Test
	void testDistribution() {
		final IDiceRoller diceRoller = getDiceRoller();
		final Map<Integer, Long> resultCount = new HashMap<>();
		for (int i = 0; i < ROLL_COUNT; i++) {
			final int result = diceRoller.roll(2, 6);
			resultCount.compute(result, (k, v) -> (v == null ? 0 : v) + 1);
		}
		assertSoftly(softly -> {
			softly.assertThat(resultCount.keySet()).containsExactlyInAnyOrder(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
			softly.assertThat(resultCount.get(2) / ((double) ROLL_COUNT)).isCloseTo(1.0 / 36.0, Percentage.withPercentage(1.0));
			softly.assertThat(resultCount.get(3) / ((double) ROLL_COUNT)).isCloseTo(2.0 / 36.0, Percentage.withPercentage(1.0));
			softly.assertThat(resultCount.get(4) / ((double) ROLL_COUNT)).isCloseTo(3.0 / 36.0, Percentage.withPercentage(1.0));
			softly.assertThat(resultCount.get(5) / ((double) ROLL_COUNT)).isCloseTo(4.0 / 36.0, Percentage.withPercentage(1.0));
			softly.assertThat(resultCount.get(6) / ((double) ROLL_COUNT)).isCloseTo(5.0 / 36.0, Percentage.withPercentage(1.0));
			softly.assertThat(resultCount.get(7) / ((double) ROLL_COUNT)).isCloseTo(6.0 / 36.0, Percentage.withPercentage(1.0));
			softly.assertThat(resultCount.get(8) / ((double) ROLL_COUNT)).isCloseTo(5.0 / 36.0, Percentage.withPercentage(1.0));
			softly.assertThat(resultCount.get(9) / ((double) ROLL_COUNT)).isCloseTo(4.0 / 36.0, Percentage.withPercentage(1.0));
			softly.assertThat(resultCount.get(10) / ((double) ROLL_COUNT)).isCloseTo(3.0 / 36.0, Percentage.withPercentage(1.0));
			softly.assertThat(resultCount.get(11) / ((double) ROLL_COUNT)).isCloseTo(2.0 / 36.0, Percentage.withPercentage(1.0));
			softly.assertThat(resultCount.get(12) / ((double) ROLL_COUNT)).isCloseTo(1.0 / 36.0, Percentage.withPercentage(1.0));
		});

	}

}
