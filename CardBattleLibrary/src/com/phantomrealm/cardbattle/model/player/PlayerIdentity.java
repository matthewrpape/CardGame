package com.phantomrealm.cardbattle.model.player;

/**
 * Indicates the identity of a given player (playing on the left side of the board or the right)
 * 
 * @author matthewpape
 */
public enum PlayerIdentity {
	/**
	 * The player who begins on the left side of the board and always plays first
	 */
	LEFT_PLAYER,
	
	/**
	 * The player who begins on the right side of the board and always plays second
	 */
	RIGHT_PLAYER;
	
	/**
	 * Gets the player identity that was not passed in (eg passing in LEFT_PLAYER will yield RIGHT_PLAYER)
	 * @param identity
	 * @return
	 */
	public static PlayerIdentity not(PlayerIdentity identity) {
		return identity == LEFT_PLAYER ? RIGHT_PLAYER : LEFT_PLAYER;
	}
}
