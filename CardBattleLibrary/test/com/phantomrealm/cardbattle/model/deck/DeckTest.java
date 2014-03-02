package com.phantomrealm.cardbattle.model.deck;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.fail;

import java.util.EmptyStackException;

import org.junit.Test;

import com.phantomrealm.cardbattle.model.card.AttackType;
import com.phantomrealm.cardbattle.model.card.BonusEffect;
import com.phantomrealm.cardbattle.model.card.Card;
import static org.hamcrest.core.IsNull.nullValue;
import static org.hamcrest.core.IsNot.not;

public class DeckTest {

//	private static final String TEST_NAME = "Wimpy Newb";
//	private static final BonusEffect TEST_EFFECT = null;
//	private static final AttackType TEST_TYPE = AttackType.PHYSICAL;
//	private static final int TEST_ATTACK = 1;
//	private static final int TEST_RESISTANCE = 2;
//	private static final int TEST_DEFENSE = 3;

	@Test
	public void testConstructor() {
		final Deck testDeck = new Deck();
		assertThat(testDeck.size(), equalTo(0));
		try {
		    testDeck.peek();
		    fail( "Should have thrown EmptyStackException but did not");
		} catch (EmptyStackException exception) {}
		try {
		    testDeck.pop();
		    fail( "Should have thrown EmptyStackException but did not");
		} catch (EmptyStackException exception) {}
	}
	
	@Test
	public void testPush_BasicPush() {
		final Deck testDeck = new Deck();
		testDeck.push(createPhysicalCard());
		assertThat(testDeck.size(), equalTo(1));
		
		testDeck.push(createMagicalCard());
		assertThat(testDeck.size(), equalTo(2));
	}
	
	@Test
	public void testPush_PastMax() {
		final Deck testDeck = new Deck();
		for (int i = 1; i < 100; ++i) {
			testDeck.push(createPhysicalCard());
		}
		
		// deck has 99 cards and room for only 1 more
		Card testCard = testDeck.push(createPhysicalCard());
		assertThat(testCard.equals(createPhysicalCard()), equalTo(true));
		assertThat(testDeck.peek().equals(createPhysicalCard()), equalTo(true));
		assertThat(testDeck.size(), equalTo(100));
		
		// deck has 100 cards and further pushes should fail
		testCard = testDeck.push(createMagicalCard());
		assertThat(testCard, nullValue());
		assertThat(testDeck.peek().equals(createMagicalCard()), equalTo(false));
		assertThat(testDeck.size(), equalTo(100));
	}
	
	@Test
	public void testClone_Equal() {
		final Deck testDeck = DeckFactory.generateTestDeck();
		final Deck clonedDeck = testDeck.clone();
		for (Card card : testDeck) {
			Card clonedCard = clonedDeck.pop();
			assertThat(card.equals(clonedCard), equalTo(true));
		}
	}
	
	@Test
	public void testClone_Unique() {
		final Deck testDeck = DeckFactory.generateTestDeck();
		final Deck clonedDeck = testDeck.clone();
		for (Card card : testDeck) {
			Card clonedCard = clonedDeck.pop();
			assertThat(card, not(clonedCard));
		}
	}
	
	private Card createPhysicalCard() {
		final String testName = "Great Barbarian";
		final BonusEffect testEffect = null;
		final AttackType testType = AttackType.PHYSICAL;
		final int testAttack = 5;
		final int testResistance = 1;
		final int testDefense = 4;
		
		return new Card(testName, testEffect, testType, testAttack, testResistance, testDefense);
	}
	
	private Card createMagicalCard() {
		final String testName = "Tricky Sage";
		final BonusEffect testEffect = null;
		final AttackType testType = AttackType.MAGICAL;
		final int testAttack = 5;
		final int testResistance = 4;
		final int testDefense = 1;
		
		return new Card(testName, testEffect, testType, testAttack, testResistance, testDefense);
	}
	
	
}
