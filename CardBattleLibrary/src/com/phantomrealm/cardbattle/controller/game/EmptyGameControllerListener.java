package com.phantomrealm.cardbattle.controller.game;

import java.io.Serializable;

import com.phantomrealm.cardbattle.controller.player.PlayerIdentity;
import com.phantomrealm.cardbattle.model.board.Position;
import com.phantomrealm.cardbattle.model.card.AttackType;

/**
 * GameControllerListener which takes no action
 * 
 * @author matthewpape
 */
public class EmptyGameControllerListener implements GameControllerListener, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public void onCardMoved(PlayerIdentity player, Position move) {
	}

	@Override
	public void onCardDefeated(Position position, AttackType enemyAttackType) {
	}

}
