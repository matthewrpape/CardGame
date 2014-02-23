package com.phantomrealm.cardbattle.controller.game;

import com.phantomrealm.cardbattle.controller.player.PlayerIdentity;
import com.phantomrealm.cardbattle.model.board.Position;
import com.phantomrealm.cardbattle.model.card.AttackType;

/**
 * Handles callbacks regarding events that occur during the course of gameplay
 * 
 * @author matthewpape
 */
public interface GameControllerListener {
	/**
	 * Signifies that a given player has placed their next card into a given position
	 * @param player
	 * @param move
	 */
	public void onCardMoved(PlayerIdentity player, Position move);
	
	/**
	 * Signifies that the card in a given position was defeated by a given attack type
	 * @param position
	 * @param enemyAttackType
	 */
	public void onCardDefeated(Position position, AttackType enemyAttackType);
}