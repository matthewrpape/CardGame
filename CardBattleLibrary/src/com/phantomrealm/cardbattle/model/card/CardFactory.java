package com.phantomrealm.cardbattle.model.card;

import java.util.Random;

/**
 * Used to generate Cards for a variety of purposes
 * 
 * @author mpape
 */
public class CardFactory {
	
	private static final int ATTACK_MIN = 1;
	private static final int ATTACK_MAX = 5;
	private static final int DEFENSE_MIN = 0;
	private static final int DEFENSE_MAX = 4;
	
	/**
	 * Create a random test card named "Test Card" for testing purposes. The card will be randomly assigned
	 *  a magical or physical attack type. It will have no bonus effect. Attack will vary between 1 and 5
	 *  inclusively. Defense and Resistance will vary between 0 and 4 inclusively.
	 * @return 
	 */
	public static Card generateTestCard() {
		return generateTestCard(ATTACK_MAX, DEFENSE_MAX);
	}
	
	/**
	 * Create a random test card named "Test Card" for testing purposes. The card will be randomly assigned
	 *  a magical or physical attack type. It will have no bonus effect. Attack will vary between 1 and 
	 *  attackMax inclusively. Defense and Resistance will vary between 0 and defenseMax inclusively.
	 * @param attackMax max value for Attack (must be >= 1)
	 * @param defenseMax max value for both Defense and Resistance (must be >= 0)
	 * @return 
	 */
	public static Card generateTestCard(int attackMax, int defenseMax) {
		return generateTestCard(attackMax, defenseMax, ATTACK_MIN, DEFENSE_MIN);
	}
	
	/**
	 * Create a random test card named "Test Card" for testing purposes. The card will be randomly assigned
	 *  a magical or physical attack type. It will have no bonus effect. Attack will vary between attackMin
	 *  and attackMax inclusively. Defense and Resistance will vary between defenseMin and defenseMax inclusively.
	 * @param attackMax max value for Attack (must be >= attackMin, <= 5)
	 * @param defenseMax max value for both Defense and Resistance (must be >= defenseMin, <= 4)
	 * @param attackMin min value for Attack (must be >= 1)
	 * @param defenseMin min value for both Defense and Resistance (must be >= 0)
	 * @return 
	 */
	public static Card generateTestCard(int attackMax, int defenseMax, int attackMin, int defenseMin) {
		// validate stat min/maxes
		attackMin = Math.max(ATTACK_MIN, attackMin);
		attackMin = Math.min(attackMin, ATTACK_MAX);
		defenseMin = Math.max(DEFENSE_MIN, defenseMin);
		defenseMin = Math.min(defenseMin, DEFENSE_MAX);
		attackMax = Math.max(attackMin, attackMax);
		attackMax = Math.min(attackMax, ATTACK_MAX);
		defenseMax = Math.max(defenseMin, defenseMax);
		defenseMax = Math.max(defenseMax, DEFENSE_MAX);
		
		
		Random randomGenerator = new Random();
		int attack = getRandomIntBetween(attackMax, attackMin);
		int defense = getRandomIntBetween(defenseMax, defenseMin);
		int resistance = getRandomIntBetween(defenseMax, defenseMin);
		AttackType attackType = randomGenerator.nextInt(2) == 0 ? AttackType.MAGICAL : AttackType.PHYSICAL;
		return new Card("TestCard", null, attackType, attack, resistance, defense);
	}
	
	/**
	 * Generate a psuedo-random int between two values inclusively
	 * @param max highest possible value for the int
	 * @param min lowest possible value for the int
	 * @return
	 */
	private static int getRandomIntBetween(int max, int min) {
		Random randomGenerator = new Random();
		return randomGenerator.nextInt((max - min) + 1) + min;
	}
	
}
