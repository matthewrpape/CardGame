package com.phantomrealm.cardbattle.controller.game;

import com.phantomrealm.cardbattle.model.board.Position;
import com.phantomrealm.cardbattle.model.card.AttackType;
import com.phantomrealm.cardbattle.model.player.PlayerIdentity;

/**
 * Handles callbacks regarding events that occur during the course of gameplay
 * 
 * @author matthewpape
 */
public interface GameControllerListener {
	public void onCardMoved(PlayerIdentity player, Position move);
	public void onCardDefeated(Position position, AttackType enemyAttackType);
}