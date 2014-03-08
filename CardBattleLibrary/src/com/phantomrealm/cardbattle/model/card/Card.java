package com.phantomrealm.cardbattle.model.card;

import java.io.Serializable;


/**
 * Model object representing a single card to be played
 * 
 * @author matthewpape
 */
public class Card implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String mName;
	private BonusEffect mBonusEffect;
	private AttackType mAttackType;
	private int mAttack;
	private int mResistance;
	private int mDefense;
	
	/**
	 * Creates a card with the given qualities
	 * @param name
	 * @param bonusEffect
	 * @param attackType
	 * @param attack
	 * @param resistance
	 * @param defense
	 */
	public Card(String name, BonusEffect bonusEffect, AttackType attackType, int attack, int resistance, int defense) {
		mName = name;
		mBonusEffect = bonusEffect;
		mAttackType = attackType;
		mAttack = attack;
		mResistance = resistance;
		mDefense = defense;
	}
	
	/**
	 * Creates a copy of a given card
	 */
	public Card clone() {
		return new Card(mName, mBonusEffect, mAttackType, mAttack, mResistance, mDefense);
	}

	/**
	 * Gets the display name to be used for the card
	 * @return
	 */
	public String getName() {
		return mName;
	}

	/**
	 * Sets the display name to be used for the card
	 * @param name
	 */
	public void setName(String name) {
		mName = name;
	}

	/**
	 * Gets the bonus effect to be used for the card
	 * @return
	 */
	public BonusEffect getBonusEffect() {
		return mBonusEffect;
	}

	/**
	 * Sets the bonus effect to be used for the card
	 * @param bonusEffect
	 */
	public void setBonusEffect(BonusEffect bonusEffect) {
		mBonusEffect = bonusEffect;
	}

	/**
	 * Gets the type of attack that the card uses in battle
	 * @return
	 */
	public AttackType getAttackType() {
		return mAttackType;
	}

	/**
	 * Sets the type of attack that the card uses in battle
	 * @param attackType
	 */
	public void setAttackType(AttackType attackType) {
		mAttackType = attackType;
	}

	/**
	 * Gets the attack strength of the card
	 * @return
	 */
	public int getAttack() {
		return mAttack;
	}

	/**
	 * Sets the attack strength of the card
	 * @param attack
	 */
	public void setAttack(int attack) {
		mAttack = attack;
	}

	/**
	 * Gets the resistance of the card to magical attacks
	 * @return
	 */
	public int getResistance() {
		return mResistance;
	}

	/**
	 * Sets the resistance of the card to magical attacks
	 * @param resistance
	 */
	public void setResistance(int resistance) {
		mResistance = resistance;
	}

	/**
	 * Gets the defense of the card to physical attacks
	 * @return
	 */
	public int getDefense() {
		return mDefense;
	}

	/**
	 * Sets the defense of the card to physical attacks
	 * @param defense
	 */
	public void setDefense(int defense) {
		mDefense = defense;
	}
	
	/**
	 * Gets the resistance or defense of the card based on the type of attack
	 * @param attackType Magical (yields resistance) or Physical (yields defense)
	 * @return
	 */
	public int getDefenseForAttackType(AttackType attackType) {
		return attackType == AttackType.MAGICAL ? getResistance() : getDefense();
	}
	
	/**
	 * Indicates whether or not two cards are equivalent to each other
	 * @param card
	 * @return
	 */
	public boolean equalTo(Card card) {
		if (card == null
				|| !card.getName().equalsIgnoreCase(getName())
				|| card.getAttackType() != getAttackType()
				|| card.getBonusEffect() != getBonusEffect()
				|| card.getAttack() != getAttack()
				|| card.getDefense() != getDefense()
				|| card.getResistance() != getResistance()) {
			return false;
		}
		return true;
	}
	
	/**
	 * Gets the string representation of a card class
	 */
	public String toString() {
		return getName() + " type: " + getAttackType() + ", atk: " + getAttack() + ", def: " + getDefense() + ", res: " + getResistance();
	}
	
}