package com.phantomrealm.cardbattle.model.board;

import com.phantomrealm.cardbattle.model.card.Card;
import com.phantomrealm.cardbattle.model.player.PlayerIdentity;

/**
 * Model object used to represent a single position on the game board
 * 
 * @author matthewpape
 */
public class BoardSlot {
	
	private PlayerIdentity mOwner;
	private Card mCard;
	
	/**
	 * Create a new slot for the game board which is controlled by neither player
	 */
	public BoardSlot() {
	}
	
	public BoardSlot(BoardSlot boardSlot) {
		mOwner = boardSlot.getOwner();
		mCard = new Card(boardSlot.getCard());
	}
	
	/**
	 * Get the owner of this slot on the board
	 * @return
	 */
	public PlayerIdentity getOwner() {
		return mOwner;
	}
	
	/**
	 * Set the owner of this slot on the board
	 * @param owner
	 */
	public void setSlotOwner(PlayerIdentity owner) {
		mOwner = owner;
	}
	
	/**
	 * Get the card on this position in the board
	 * @return
	 */
	public Card getCard() {
		return mCard;
	}
	
	/**
	 * Set the card on this position in the board
	 * @param card
	 */
	public void setCard(Card card) {
		mCard = card;
	}
	
}
