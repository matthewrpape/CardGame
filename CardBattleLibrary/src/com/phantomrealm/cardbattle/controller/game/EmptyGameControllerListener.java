package com.phantomrealm.cardbattle.controller.game;

import com.phantomrealm.cardbattle.controller.player.PlayerIdentity;
import com.phantomrealm.cardbattle.model.board.Position;
import com.phantomrealm.cardbattle.model.card.AttackType;

/**
 * GameControllerListener which takes no action
 * 
 * @author matthewpape
 */
public class EmptyGameControllerListener implements GameControllerListener {

	@Override
	public void onCardMoved(PlayerIdentity player, Position move) {
	}

	@Override
	public void onCardDefeated(Position position, AttackType enemyAttackType) {
	}

}
