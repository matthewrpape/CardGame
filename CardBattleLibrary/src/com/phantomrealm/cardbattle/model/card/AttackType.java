package com.phantomrealm.cardbattle.model.card;

/**
 * Determines if a particular card uses physical or magical based attacks
 * 
 * @author matthewpape
 */
public enum AttackType {
	/**
	 * Physical attack that target the defense stat
	 */
	PHYSICAL,
	
	/**
	 * Magical attack that targets the resistance stat
	 */
	MAGICAL
}
