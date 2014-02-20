package com.phantomrealm.cardbattle.model.card;

/**
 * Bonus effect taken by a card after doing battle
 * 
 * @author matthewpape
 */
public enum BonusEffect {
	/**
	 * Winning a battle will cause a duplication of this card to take the position of the fallen foe
	 */
	DUPLICATE,
	
	/**
	 * Winning a battle results in a 1 point increase to this cards attack
	 */
	ATTACK_LEVEL_UP,
	
	/**
	 * Winning a battle results in a 1 point increase to this cards resistance
	 */
	RESISTANCE_LEVEL_UP,
	
	/**
	 * Winning a battle results in a 1 point increase to this cards defense
	 */
	DEFENSE_LEVEL_UP,
	
	/**
	 * Winning a battle results in a 1 point increase to all of this cards statistics
	 */
	FULL_LEVEL_UP,
	
	/**
	 * Winning a battle results in a 1 point decrease to this cards attack (0 minimum)
	 */
	ATTACK_LEVEL_DOWN,
	
	/**
	 * Winning a battle results in a 1 point decrease to this cards resistance (0 minimum)
	 */
	RESISTANCE_LEVEL_DOWN,
	
	/**
	 * Winning a battle results in a 1 point decrease to this cards defense (0 minimum)
	 */
	DEFENSE_LEVEL_DOWN,
	
	/**
	 * Winning a battle results in a 1 point decrease to all of this cards statistics (0 minimum)
	 */
	FULL_LEVEL_DOWN,
	
	/**
	 * Winning or losing a battle results in this card destroying itself and its opponent
	 */
	KAMIKAZE,
	
	/**
	 * Losing a battle will result in this card destroying itself and its opponent 
	 */
	SELF_DESTRUCT
}
