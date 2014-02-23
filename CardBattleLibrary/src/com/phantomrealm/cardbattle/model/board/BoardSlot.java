package com.phantomrealm.cardbattle.model.board;

import com.phantomrealm.cardbattle.controller.player.PlayerIdentity;
import com.phantomrealm.cardbattle.model.card.Card;

/**
 * Model object used to represent a single position on the game board
 * 
 * @author matthewpape
 */
public class BoardSlot {
	
	private PlayerIdentity mOwner;
	private Card mCard;
	
	/**
	 * Creates a new slot for the game board which is controlled by neither player
	 */
	public BoardSlot() {
	}
	
	/**
	 * Gets the owner of this slot on the board
	 * @return
	 */
	public PlayerIdentity getOwner() {
		return mOwner;
	}
	
	/**
	 * Sets the owner of this slot on the board
	 * @param owner
	 */
	public void setSlotOwner(PlayerIdentity owner) {
		mOwner = owner;
	}
	
	/**
	 * Gets the card on this position in the board
	 * @return
	 */
	public Card getCard() {
		return mCard;
	}
	
	/**
	 * Sets the card on this position in the board
	 * @param card
	 */
	public void setCard(Card card) {
		mCard = card;
	}
	
	/**
	 * Indicates whether or not two BoardSlots are equivalent to each other
	 * @param slot
	 * @return
	 */
	public boolean equals(BoardSlot slot) {
		if (slot == null || slot.getOwner() != getOwner()) {
			return false;
		}
		
		if (getCard() != null) {
			if(!getCard().equals(slot.getCard())) {
				return false;
			}
		} else if (slot.getCard() != null) {
			return false;
		}

		return true;
	}
	
	/**
	 * Gets the string representation of a board slot
	 */
	public String toString() {
		return getOwner() + ", " + (getCard() == null ? null : getCard().toString());
	}
}
