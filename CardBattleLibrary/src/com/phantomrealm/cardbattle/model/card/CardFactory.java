package com.phantomrealm.cardbattle.model.card;

import java.util.Random;

/**
 * Used to generate Cards for a variety of purposes
 * 
 * @author matthewpape
 */
public class CardFactory {
	
	private static final int ATTACK_MIN = 1;
	private static final int ATTACK_MAX = 5;
	private static final int DEFENSE_MIN = 0;
	private static final int DEFENSE_MAX = 4;
	
	/**
	 * Creates a random test card named "Test Card" for testing purposes. The card will be randomly assigned
	 *  a magical or physical attack type. It will have no bonus effect. Attack will vary between 1 and 5
	 *  inclusively. Defense and Resistance will vary between 0 and 4 inclusively.
	 * @return 
	 */
	public static Card generateTestCard() {
		return generateTestCard(ATTACK_MAX, DEFENSE_MAX);
	}
	
	/**
	 * Creates a random test card named "Test Card" for testing purposes. The card will be randomly assigned
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
	 * Creates a random test card named "Test Card" for testing purposes. The card will be randomly assigned
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
		int attack = getRandomIntBetween(attackMin, attackMax);
		int defense = getRandomIntBetween(defenseMin, defenseMax);
		int resistance = getRandomIntBetween(defenseMin, defenseMax);
		AttackType attackType = randomGenerator.nextInt(2) == 0 ? AttackType.MAGICAL : AttackType.PHYSICAL;
		String name = generateRandomName(attackType, attack, defense, resistance);
		return new Card(name, null, attackType, attack, resistance, defense);
	}
	
	/**
	 * Generates a psuedo-random int between two values inclusively
	 * @param min lowest possible value for the int
	 * @param max highest possible value for the int
	 * @return
	 */
	private static int getRandomIntBetween(int min, int max) {
		Random randomGenerator = new Random();
		return randomGenerator.nextInt((max - min) + 1) + min;
	}
	
	/**
	 * Generate a pseudo-random name based on the stats of a card
	 * @param attackType
	 * @param attack
	 * @param defense
	 * @param resistance
	 * @return
	 */
	private static String generateRandomName(AttackType attackType, int attack, int defense, int resistance) {
		StringBuilder builder = new StringBuilder();
		builder.append(generateAdjective(attack, defense, resistance));
		builder.append(" ");
		builder.append(generateNoun(attackType, attack));
		return builder.toString();
	}
	
	/**
	 * Generate an adjective based on a pseudo-random set of the stats of a card
	 * @param attack
	 * @param defense
	 * @param resistance
	 * @return
	 */
	private static String generateAdjective(int attack, int defense, int resistance) {
		int stat = getRandomIntBetween(0, 3);
		if (stat == 1) {
			return generateAdjectiveFromAttack(attack);
		} else if (stat == 2) {
			return generateAdjectiveFromDefense(defense);
		} else if (stat == 3) {
			return generateAdjectiveFromResistance(resistance);
		} else {
			return generateAdjectiveFromAllStats(attack + defense + resistance);
		}
	}
	
	/**
	 * Generate a noun based on the attack type and attack stat of a card
	 * @param attackType
	 * @param attack
	 * @return
	 */
	private static String generateNoun(AttackType attackType, int attack) {
		switch (attackType) {
		case MAGICAL:
			if (attack < 2) {
				return "Faker";
			} else if (attack < 3) {
				return "Witch";
			} else if (attack < 5) {
				return "Mage";
			} else {
				return "Genie";
			}
		case PHYSICAL:
			if (attack < 2) {
				return "Man";
			} else if (attack < 3) {
				return "Cadet";
			} else if (attack < 5) {
				return "Knight";
			} else {
				return "Hero";
			}
		default:
			return "";
		}
	}
	
	/**
	 * Generate an adjective based on the combined stats of a card
	 * @param total
	 * @return
	 */
	private static String generateAdjectiveFromAllStats(int total) {
		if (total < 3) {
			return "Newbie";
		} else if (total < 5) {
			return "Green";
		} else if (total < 8) {
			return "Novice";
		} else if (total < 12) {
			return "Master";
		} else {
			return "Legendary";
		}
	}
	
	/**
	 * Generate an adjective based on the attack stat of a card
	 * @param attack
	 * @return
	 */
	private static String generateAdjectiveFromAttack(int attack) {
		if (attack < 2) {
			return "Puny";
		} else if (attack < 3) {
			return "Weak";
		} else if (attack < 5) {
			return "Tough";
		} else {
			return "Iron";
		}
	}
	
	/**
	 * Generate an adjective based on the defense stat of a card
	 * @param defense
	 * @return
	 */
	private static String generateAdjectiveFromDefense(int defense) {
		if (defense < 1) {
			return "Frail";
		} else if (defense < 2) {
			return "Weak";
		} else if (defense < 4) {
			return "Firm";
		} else {
			return "Tough";
		}
	}
	
	/**
	 * Generate an adjective based on the resistance stat of a card
	 * @param resistance
	 * @return
	 */
	private static String generateAdjectiveFromResistance(int resistance) {
		if (resistance < 1) {
			return "Dull";
		} else if (resistance < 2) {
			return "Keen";
		} else if (resistance < 4) {
			return "Wise";
		} else {
			return "Sage";
		}
	}

}