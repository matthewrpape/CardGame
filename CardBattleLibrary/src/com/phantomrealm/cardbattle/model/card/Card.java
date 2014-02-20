package com.phantomrealm.cardbattle.model.card;


/**
 * Model object representing a single card to be played
 * 
 * @author matthewpape
 */
public class Card {

	private String mName;
	private BonusEffect mBonusEffect;
	private AttackType mAttackType;
	private int mAttack;
	private int mResistance;
	private int mDefense;
	
	public Card(String name, BonusEffect bonusEffect, AttackType attackType, int attack, int resistance, int defense) {
		mName = name;
		mBonusEffect = bonusEffect;
		mAttackType = attackType;
		mAttack = attack;
		mResistance = resistance;
		mDefense = defense;
	}
	
	public Card(Card card) {
		mName = card.getName();
		mBonusEffect = card.getBonusEffect();
		mAttackType = card.getAttackType();
		mAttack = card.getAttack();
		mResistance = card.getResistance();
		mDefense = card.getDefense();
	}

	/**
	 * Get the display name to be used for the card
	 * @return
	 */
	public String getName() {
		return mName;
	}

	/**
	 * Set the display name to be used for the card
	 * @param name
	 */
	public void setName(String name) {
		mName = name;
	}

	/**
	 * Get the bonus effect to be used for the card
	 * @return
	 */
	public BonusEffect getBonusEffect() {
		return mBonusEffect;
	}

	/**
	 * Set the bonus effect to be used for the card
	 * @param bonusEffect
	 */
	public void setBonusEffect(BonusEffect bonusEffect) {
		mBonusEffect = bonusEffect;
	}

	/**
	 * Get the type of attack that the card uses in battle
	 * @return
	 */
	public AttackType getAttackType() {
		return mAttackType;
	}

	/**
	 * Set the type of attack that the card uses in battle
	 * @param attackType
	 */
	public void setAttackType(AttackType attackType) {
		mAttackType = attackType;
	}

	/**
	 * Get the attack strength of the card
	 * @return
	 */
	public int getAttack() {
		return mAttack;
	}

	/**
	 * Set the attack strength of the card
	 * @param attack
	 */
	public void setAttack(int attack) {
		mAttack = attack;
	}

	/**
	 * Get the resistance of the card to magical attacks
	 * @return
	 */
	public int getResistance() {
		return mResistance;
	}

	/**
	 * Set the resistance of the card to magical attacks
	 * @param resistance
	 */
	public void setResistance(int resistance) {
		mResistance = resistance;
	}

	/**
	 * Get the defense of the card to physical attacks
	 * @return
	 */
	public int getDefense() {
		return mDefense;
	}

	/**
	 * Set the defense of the card to physical attacks
	 * @param defense
	 */
	public void setDefense(int defense) {
		mDefense = defense;
	}
	
	/**
	 * Get the resistance or defense of the card based on the type of attack
	 * @param attackType Magical (yields resistance) or Physical (yields defense)
	 * @return
	 */
	public int getDefenseForAttackType(AttackType attackType) {
		return attackType == AttackType.MAGICAL ? getResistance() : getDefense();
	}
	
}