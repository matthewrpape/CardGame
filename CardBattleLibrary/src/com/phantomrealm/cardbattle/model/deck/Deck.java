package com.phantomrealm.cardbattle.model.deck;

import java.util.Collections;
import java.util.Stack;

import com.phantomrealm.cardbattle.model.card.Card;

/**
 * Model object used to represent a deck of 100 Cards
 * 
 * @author matthewpape
 */
public class Deck {

	Stack<Card> mCardStack;
	
	/**
	 * Create a deck form a list of up to 100 Cards. Any more than 100 will be ignored.
	 * @param cards
	 */
	public Deck(Stack<Card> cards) {
		if (cards == null || cards.size() == 0) {
			// no cards yields an empty deck
			mCardStack = new Stack<Card>();
		} else if (cards.size() > 100) {
			// too many cards, only keep the first 100
			mCardStack = new Stack<Card>();
			mCardStack.addAll(cards.subList(0, 100));
		} else {
			// an acceptable number of cards, use them all
			mCardStack = cards;
		}
	}
	
	/**
	 * Randomize the order of the contents of this deck
	 */
	public void Shuffle() {
		Collections.shuffle(mCardStack);
	}
	
	/**
	 * Peek at the next card in the deck without removing it
	 * @return card that is still on the top of the deck
	 */
	public Card peek () {
		return mCardStack.peek();
	}
	
	/**
	 * Pop the next card off the deck
	 * @return card that up until now had been on the top of the deck
	 */
	public Card pop() {
		return mCardStack.pop();
	}
	
	/**
	 * True if the given deck is null or of size zero
	 * @return
	 */
	public boolean isEmpty() {
		return mCardStack == null || mCardStack.isEmpty();
	}
}
