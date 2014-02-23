package com.phantomrealm.cardbattle.model.card;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.nullValue;

import org.junit.Test;

public class CardTest {

	private static final String TEST_NAME = "Wimpy Newb";
	private static final BonusEffect TEST_EFFECT = null;
	private static final AttackType TEST_TYPE = AttackType.PHYSICAL;
	private static final int TEST_ATTACK = 1;
	private static final int TEST_RESISTANCE = 2;
	private static final int TEST_DEFENSE = 3;

	@Test
	public void testConstructor() {
		Card card = createCard();
		assertThat(card.getName(), equalTo(TEST_NAME));
		assertThat(card.getBonusEffect(), nullValue());
		assertThat(card.getAttackType(), equalTo(TEST_TYPE));
		assertThat(card.getAttack(), equalTo(TEST_ATTACK));
		assertThat(card.getResistance(), equalTo(TEST_RESISTANCE));
		assertThat(card.getDefense(), equalTo(TEST_DEFENSE));
	}
	
	@Test
	public void testClone() {
		final String newName = "Leveled Newb";
		final AttackType newType = AttackType.MAGICAL;
		final int newAttack = 2;
		final int newResistance = 3;
		final int newDefense = 4;
		
		Card card = createCard();
		Card clone = card.clone();
		card.setName(newName);
		card.setAttackType(newType);
		card.setAttack(newAttack);
		card.setResistance(newResistance);
		card.setDefense(newDefense);
		
		assertThat(clone.getName(), equalTo(TEST_NAME));
		assertThat(clone.getBonusEffect(), nullValue());
		assertThat(clone.getAttackType(), equalTo(TEST_TYPE));
		assertThat(clone.getAttack(), equalTo(TEST_ATTACK));
		assertThat(clone.getResistance(), equalTo(TEST_RESISTANCE));
		assertThat(clone.getDefense(), equalTo(TEST_DEFENSE));
	}
	
	@Test
	public void testDefenseForAttack() {
		Card card = createCard();
		assertThat(card.getDefenseForAttackType(AttackType.MAGICAL), equalTo(TEST_RESISTANCE));
		assertThat(card.getDefenseForAttackType(AttackType.PHYSICAL), equalTo(TEST_DEFENSE));
	}
	
	@Test
	public void testEquals_Name() {
		final String newName = "Bogus Weirdo";
		Card cardOne = createCard();
		Card cardTwo = createCard();
		assertThat(cardOne.equals(cardTwo), equalTo(true));
		
		cardOne.setName(newName);
		assertThat(cardOne.equals(cardTwo), equalTo(false));
	}
	
	@Test
	public void testEquals_AttackType() {
		final AttackType newType = AttackType.MAGICAL;
		Card cardOne = createCard();
		Card cardTwo = createCard();
		assertThat(cardOne.equals(cardTwo), equalTo(true));
		
		cardOne.setAttackType(newType);
		assertThat(cardOne.equals(cardTwo), equalTo(false));
	}
	
	@Test
	public void testEquals_Attack() {
		final int newAttack = 5;
		Card cardOne = createCard();
		Card cardTwo = createCard();
		assertThat(cardOne.equals(cardTwo), equalTo(true));
		
		cardOne.setAttack(newAttack);
		assertThat(cardOne.equals(cardTwo), equalTo(false));
	}
	
	@Test
	public void testEquals_Defense() {
		final int newDefense = 4;
		Card cardOne = createCard();
		Card cardTwo = createCard();
		assertThat(cardOne.equals(cardTwo), equalTo(true));
		
		cardOne.setDefense(newDefense);
		assertThat(cardOne.equals(cardTwo), equalTo(false));
	}
	
	@Test
	public void testEquals_Resistance() {
		final int newResistance = 0;
		Card cardOne = createCard();
		Card cardTwo = createCard();
		assertThat(cardOne.equals(cardTwo), equalTo(true));
		
		cardOne.setResistance(newResistance);
		assertThat(cardOne.equals(cardTwo), equalTo(false));
	}
	
	@Test
	public void testEquals_NullCard() {
		Card card = createCard();
		assertThat(card.equals(null), equalTo(false));
	}

	private Card createCard() {
		return new Card(TEST_NAME, TEST_EFFECT, TEST_TYPE, TEST_ATTACK, TEST_RESISTANCE, TEST_DEFENSE);
	}
}
