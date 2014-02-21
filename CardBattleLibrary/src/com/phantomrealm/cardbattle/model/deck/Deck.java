package com.phantomrealm.cardbattle.model.deck;

import java.util.Collections;
import java.util.Stack;

import com.phantomrealm.cardbattle.model.card.Card;

/**
 * Model object used to represent a deck of up to 100 Cards
 * 
 * @author matthewpape
 */
public class Deck extends Stack<Card>{

	/**
	 * Serialization version identifier
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * If there are already 100 cards in the deck, no more can be added
	 * @return the card that was pushed, or null if the deck was already full
	 */
	@Override
	public Card push(Card card) {
		if (size() < 100) {
			super.push(card);
			return card;
		}
		
		return null;
	}
	
	/**
	 * Creates a clone of the deck with references to new data objects, rather
	 *  than the originals
	 */
	@Override
	public Deck clone() {
		return (Deck) this.clone();
	}
	
	/**
	 * Randomizes the order of the contents of this deck
	 */
	public void Shuffle() {
		Collections.shuffle(this);
	}
}
