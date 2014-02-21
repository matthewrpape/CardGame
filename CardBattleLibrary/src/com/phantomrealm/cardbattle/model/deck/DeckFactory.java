package com.phantomrealm.cardbattle.model.deck;

import com.phantomrealm.cardbattle.model.card.CardFactory;

/**
 * Used to generate Decks for a variety of purposes
 * 
 * @author matthewpape
 */
public class DeckFactory {
	
	private static final int DEFAULT_DECK_SIZE = 100;
	private static final int ATTACK_MIN = 1;
	private static final int ATTACK_MAX = 5;
	private static final int DEFENSE_MIN = 0;
	private static final int DEFENSE_MAX = 4;
	private static final int POWERFUL_DECK_ATTACK_MIN = 2;
	private static final int POWERFUL_DECK_DEFENSE_MIN = 1;
	private static final int WEAK_DECK_ATTACK_MAX = 3;
	private static final int WEAK_DECK_DEFENSE_MAX = 4;
	
	/**
	 * Creates a deck of 100 cards with above average stats
	 * @return
	 */
	public static Deck generatePowerfulTestDeck() {
		return generatePowerfulTestDeck(DEFAULT_DECK_SIZE);
	}
	
	/**
	 * Creates a deck with above average stats of a given size
	 * @param deckSize must be between 0 and 100 inclusive
	 * @return
	 */
	public static Deck generatePowerfulTestDeck(int deckSize) {
		return generateTestDeck(deckSize, ATTACK_MAX, DEFENSE_MAX, POWERFUL_DECK_ATTACK_MIN, POWERFUL_DECK_DEFENSE_MIN);
	}
	
	/**
	 * Creates a deck of 100 cards with bellow average stats
	 * @return
	 */
	public static Deck generateWeakTestDeck() {
		return generateWeakTestDeck(DEFAULT_DECK_SIZE);
	}
	
	/**
	 * Creates a deck with bellow average stats of a given size
	 * @param deckSize must be between 0 and 100 inclusive
	 * @return
	 */
	public static Deck generateWeakTestDeck(int deckSize) {
		return generateTestDeck(deckSize, WEAK_DECK_ATTACK_MAX, WEAK_DECK_DEFENSE_MAX, ATTACK_MIN, DEFENSE_MIN);
	}
	
	/**
	 * Generates a deck of 100 cards with average stats
	 * @return
	 */
	public static Deck generateTestDeck() {
		return generateTestDeck(DEFAULT_DECK_SIZE);
	}
	
	/**
	 * Generates a deck of a given size with average stats
	 * @param deckSize
	 * @return
	 */
	public static Deck generateTestDeck(int deckSize) {
		return generateTestDeck(deckSize, ATTACK_MAX, DEFENSE_MAX, ATTACK_MIN, DEFENSE_MIN);
	}
	
	/**
	 * Generates a deck of 100 cards, filled with cards using specified stats.
	 * @param attackMax subject to same rules as CardFactory cards
	 * @param defenseMax subject to same rules as CardFactory cards
	 * @param attackMin subject to same rules as CardFactory cards
	 * @param defenseMin subject to same rules as CardFactory cards
	 * @return
	 */
	public static Deck generateTestDeck(int attackMax, int defenseMax, int attackMin, int defenseMin) {
		return generateTestDeck(DEFAULT_DECK_SIZE, attackMax, defenseMax, attackMin, defenseMin);
	}
	
	/**
	 * Generates a deck of specified size, filled with cards using specified stats.
	 * @param deckSize must be between 0 and 100 inclusive
	 * @param attackMax subject to same rules as CardFactory cards
	 * @param defenseMax subject to same rules as CardFactory cards
	 * @param attackMin subject to same rules as CardFactory cards
	 * @param defenseMin subject to same rules as CardFactory cards
	 * @return
	 */
	public static Deck generateTestDeck(int deckSize, int attackMax, int defenseMax, int attackMin, int defenseMin) {
		deckSize = Math.min(100, deckSize);
		deckSize = Math.max(deckSize, 0);
		
		Deck deck = new Deck();
		for (int i = 0; i < deckSize; ++i) {
			deck.push(CardFactory.generateTestCard(attackMax, defenseMax, attackMin, defenseMin));
		}
		return deck;
	}

}
