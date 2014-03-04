package com.phantomrealm.cardbattle.model.deck;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.hamcrest.core.IsEqual.equalTo;

import org.junit.Test;

import com.phantomrealm.cardbattle.model.card.Card;

public class DeckFactoryTest {

	private static final int DEFAULT_DECK_SIZE = 100;
	private static final int TEST_DECK_SIZE = 95;
	private static final int ATTACK_MIN = 1;
	private static final int ATTACK_MAX = 5;
	private static final int DEFENSE_MIN = 0;
	private static final int DEFENSE_MAX = 4;
	private static final int POWERFUL_DECK_ATTACK_MIN = 2;
	private static final int POWERFUL_DECK_DEFENSE_MIN = 1;
	private static final int WEAK_DECK_ATTACK_MAX = 3;
	private static final int WEAK_DECK_DEFENSE_MAX = 4;
	
	@Test
	public void testGeneratePowerfulTestDeck_SizeWithNoParams() {
		final Deck testDeck = DeckFactory.generatePowerfulTestDeck();
		assertThat(testDeck.size(), equalTo(DEFAULT_DECK_SIZE));
	}
	
	@Test
	public void testGeneratePowerfulTestDeck_StatsWithNoParams() {
		final Deck testDeck = DeckFactory.generatePowerfulTestDeck();
		for (Card testCard : testDeck) {
			assertThat(testCard.getAttack(), allOf(greaterThanOrEqualTo(POWERFUL_DECK_ATTACK_MIN), lessThanOrEqualTo(ATTACK_MAX)));
			assertThat(testCard.getDefense(), allOf(greaterThanOrEqualTo(POWERFUL_DECK_DEFENSE_MIN), lessThanOrEqualTo(DEFENSE_MAX)));
		}
	}
	
	@Test
	public void testGeneratePowerfulTestDeck_SizeWithParams() {
		final Deck testDeck = DeckFactory.generatePowerfulTestDeck(TEST_DECK_SIZE);
		assertThat(testDeck.size(), equalTo(TEST_DECK_SIZE));
	}
	
	@Test
	public void testGeneratePowerfulTestDeck_StatsWithParams() {
		final Deck testDeck = DeckFactory.generatePowerfulTestDeck(TEST_DECK_SIZE);
		for (Card testCard : testDeck) {
			assertThat(testCard.getAttack(), allOf(greaterThanOrEqualTo(POWERFUL_DECK_ATTACK_MIN), lessThanOrEqualTo(ATTACK_MAX)));
			assertThat(testCard.getDefense(), allOf(greaterThanOrEqualTo(POWERFUL_DECK_DEFENSE_MIN), lessThanOrEqualTo(DEFENSE_MAX)));
		}
	}
	
	@Test
	public void testGenerateWeakTestDeck_SizeWithNoParams() {
		final Deck testDeck = DeckFactory.generateWeakTestDeck();
		assertThat(testDeck.size(), equalTo(DEFAULT_DECK_SIZE));
	}
	
	@Test
	public void testGenerateWeakTestDeck_StatsWithNoParams() {
		final Deck testDeck = DeckFactory.generateWeakTestDeck();
		for (Card testCard : testDeck) {
			assertThat(testCard.getAttack(), allOf(greaterThanOrEqualTo(ATTACK_MIN), lessThanOrEqualTo(WEAK_DECK_ATTACK_MAX)));
			assertThat(testCard.getDefense(), allOf(greaterThanOrEqualTo(DEFENSE_MIN), lessThanOrEqualTo(WEAK_DECK_DEFENSE_MAX)));
		}
	}
	
	@Test
	public void testGenerateWeakTestDeck_SizeWithParams() {
		final Deck testDeck = DeckFactory.generateWeakTestDeck(TEST_DECK_SIZE);
		assertThat(testDeck.size(), equalTo(TEST_DECK_SIZE));
	}
	
	@Test
	public void testGenerateWeakTestDeck_StatsWithParams() {
		final Deck testDeck = DeckFactory.generateWeakTestDeck(TEST_DECK_SIZE);
		for (Card testCard : testDeck) {
			assertThat(testCard.getAttack(), allOf(greaterThanOrEqualTo(ATTACK_MIN), lessThanOrEqualTo(WEAK_DECK_ATTACK_MAX)));
			assertThat(testCard.getDefense(), allOf(greaterThanOrEqualTo(DEFENSE_MIN), lessThanOrEqualTo(WEAK_DECK_DEFENSE_MAX)));
		}
	}
	
	@Test
	public void testGenerateTestDeck_SizeWithNoParams() {
		final Deck testDeck = DeckFactory.generateTestDeck();
		assertThat(testDeck.size(), equalTo(DEFAULT_DECK_SIZE));
	}
	
	@Test
	public void testGenerateTestDeck_StatsWithNoParams() {
		final Deck testDeck = DeckFactory.generateTestDeck();
		for (Card testCard : testDeck) {
			assertThat(testCard.getAttack(), allOf(greaterThanOrEqualTo(ATTACK_MIN), lessThanOrEqualTo(ATTACK_MAX)));
			assertThat(testCard.getDefense(), allOf(greaterThanOrEqualTo(DEFENSE_MIN), lessThanOrEqualTo(DEFENSE_MAX)));
		}
	}
	
	@Test
	public void testGenerateTestDeck_SizeWithSize() {
		final Deck testDeck = DeckFactory.generateTestDeck(TEST_DECK_SIZE);
		assertThat(testDeck.size(), equalTo(TEST_DECK_SIZE));
	}
	
	@Test
	public void testGenerateTestDeck_StatsWithSize() {
		final Deck testDeck = DeckFactory.generateTestDeck(TEST_DECK_SIZE);
		for (Card testCard : testDeck) {
			assertThat(testCard.getAttack(), allOf(greaterThanOrEqualTo(ATTACK_MIN), lessThanOrEqualTo(ATTACK_MAX)));
			assertThat(testCard.getDefense(), allOf(greaterThanOrEqualTo(DEFENSE_MIN), lessThanOrEqualTo(DEFENSE_MAX)));
		}
	}
	
	@Test
	public void testGenerateTestDeck_SizeWithParams() {
		final Deck testDeck = DeckFactory.generateTestDeck(WEAK_DECK_ATTACK_MAX, WEAK_DECK_DEFENSE_MAX, POWERFUL_DECK_ATTACK_MIN, POWERFUL_DECK_DEFENSE_MIN);
		assertThat(testDeck.size(), equalTo(DEFAULT_DECK_SIZE));
	}
	
	@Test
	public void testGenerateTestDeck_StatsWithParams() {
		final Deck testDeck = DeckFactory.generateTestDeck(WEAK_DECK_ATTACK_MAX, WEAK_DECK_DEFENSE_MAX, POWERFUL_DECK_ATTACK_MIN, POWERFUL_DECK_DEFENSE_MIN);
		for (Card testCard : testDeck) {
			assertThat(testCard.getAttack(), allOf(greaterThanOrEqualTo(POWERFUL_DECK_ATTACK_MIN), lessThanOrEqualTo(WEAK_DECK_ATTACK_MAX)));
			assertThat(testCard.getDefense(), allOf(greaterThanOrEqualTo(POWERFUL_DECK_DEFENSE_MIN), lessThanOrEqualTo(WEAK_DECK_DEFENSE_MAX)));
		}
	}
	
	@Test
	public void testGenerateTestDeck_SizeWithSizeAndParams() {
		final Deck testDeck = DeckFactory.generateTestDeck(TEST_DECK_SIZE, WEAK_DECK_ATTACK_MAX, WEAK_DECK_DEFENSE_MAX, POWERFUL_DECK_ATTACK_MIN, POWERFUL_DECK_DEFENSE_MIN);
		assertThat(testDeck.size(), equalTo(TEST_DECK_SIZE));
	}
	
	@Test
	public void testGenerateTestDeck_StatsWithSizeAndParams() {
		final Deck testDeck = DeckFactory.generateTestDeck(TEST_DECK_SIZE, WEAK_DECK_ATTACK_MAX, WEAK_DECK_DEFENSE_MAX, POWERFUL_DECK_ATTACK_MIN, POWERFUL_DECK_DEFENSE_MIN);
		for (Card testCard : testDeck) {
			assertThat(testCard.getAttack(), allOf(greaterThanOrEqualTo(POWERFUL_DECK_ATTACK_MIN), lessThanOrEqualTo(WEAK_DECK_ATTACK_MAX)));
			assertThat(testCard.getDefense(), allOf(greaterThanOrEqualTo(POWERFUL_DECK_DEFENSE_MIN), lessThanOrEqualTo(WEAK_DECK_DEFENSE_MAX)));
		}
	}

}