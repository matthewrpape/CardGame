package com.phantomrealm.cardbattle.model.card;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.nullValue;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anyOf;
import org.junit.Test;
import static org.hamcrest.core.IsNull.notNullValue;

public class CardFactoryTest {
	private static final int ATTACK_MIN = 1;
	private static final int ATTACK_MAX = 5;
	private static final int DEFENSE_MIN = 0;
	private static final int DEFENSE_MAX = 4;

	@Test
	public void testGenerateTestCard_NoParams() {
		Card card = CardFactory.generateTestCard();
		assertThat(card.getName(), notNullValue());
		assertThat(card.getBonusEffect(), nullValue());
		assertThat(card.getAttackType(), notNullValue());
		assertThat(card.getAttack(), allOf(greaterThanOrEqualTo(ATTACK_MIN), lessThanOrEqualTo(ATTACK_MAX)));
		assertThat(card.getResistance(), allOf(greaterThanOrEqualTo(DEFENSE_MIN), lessThanOrEqualTo(DEFENSE_MAX)));
		assertThat(card.getDefense(), allOf(greaterThanOrEqualTo(DEFENSE_MIN), lessThanOrEqualTo(DEFENSE_MAX)));
	}
	
	@Test
	public void testGenerateTestCard_MaxValues_ValidValues() {
		final int testAttackMax = 1;
		final int testDefenseMax = 0;
		Card card = CardFactory.generateTestCard(testAttackMax, testDefenseMax);
		assertThat(card.getName(), notNullValue());
		assertThat(card.getBonusEffect(), nullValue());
		assertThat(card.getAttackType(), notNullValue());
		assertThat(card.getAttack(), equalTo(ATTACK_MIN));
		assertThat(card.getResistance(), equalTo(DEFENSE_MIN));
		assertThat(card.getDefense(), equalTo(DEFENSE_MIN));
	}
	
	@Test
	public void testGenerateTestCard_MaxValues_InvalidValues() {
		final int testAttackMax = -3;
		final int testDefenseMax = -2;
		Card card = CardFactory.generateTestCard(testAttackMax, testDefenseMax);
		assertThat(card.getName(), notNullValue());
		assertThat(card.getBonusEffect(), nullValue());
		assertThat(card.getAttackType(), notNullValue());
		assertThat(card.getAttack(), equalTo(ATTACK_MIN));
		assertThat(card.getResistance(), equalTo(DEFENSE_MIN));
		assertThat(card.getDefense(), equalTo(DEFENSE_MIN));
	}
	
	@Test
	public void testGenerateTestCard_AllValues_ValidValues() {
		final int testAttackMax = 2;
		final int testDefenseMax = 1;
		final int testAttackMin = 2;
		final int testDefenseMin = 1;
		Card card = CardFactory.generateTestCard(testAttackMax, testDefenseMax, testAttackMin, testDefenseMin);
		assertThat(card.getName(), notNullValue());
		assertThat(card.getBonusEffect(), nullValue());
		assertThat(card.getAttackType(), notNullValue());
		assertThat(card.getAttack(), equalTo(testAttackMin));
		assertThat(card.getResistance(), equalTo(testDefenseMin));
		assertThat(card.getDefense(), equalTo(testDefenseMin));
	}
	
	@Test
	public void testGenerateTestCard_AllValues_InvalidlyLowValues() {
		final int testAttackMax = -3;
		final int testDefenseMax = -4;
		final int testAttackMin = -5;
		final int testDefenseMin = -6;
		Card card = CardFactory.generateTestCard(testAttackMax, testDefenseMax, testAttackMin, testDefenseMin);
		assertThat(card.getName(), notNullValue());
		assertThat(card.getBonusEffect(), nullValue());
		assertThat(card.getAttackType(), notNullValue());
		assertThat(card.getAttack(), equalTo(ATTACK_MIN));
		assertThat(card.getResistance(), equalTo(DEFENSE_MIN));
		assertThat(card.getDefense(), equalTo(DEFENSE_MIN));
	}
	
	@Test
	public void testGenerateTestCard_AllValues_InvalidlyHighValues() {
		final int testAttackMax = 10;
		final int testDefenseMax = 9;
		final int testAttackMin = 8;
		final int testDefenseMin = 7;
		Card card = CardFactory.generateTestCard(testAttackMax, testDefenseMax, testAttackMin, testDefenseMin);
		assertThat(card.getName(), notNullValue());
		assertThat(card.getBonusEffect(), nullValue());
		assertThat(card.getAttackType(), notNullValue());
		assertThat(card.getAttack(), equalTo(ATTACK_MAX));
		assertThat(card.getResistance(), equalTo(DEFENSE_MAX));
		assertThat(card.getDefense(), equalTo(DEFENSE_MAX));
	}

	@Test
	public void testAdjectiveGeneration_FirstLevel() {
		final int testAttackMax = ATTACK_MIN;
		final int testDefenseMax = DEFENSE_MIN;
		Card card = CardFactory.generateTestCard(testAttackMax, testDefenseMax);
		String adjective = getAdjective(card);
		assertThat(adjective, anyOf(equalTo("Newbie"), equalTo("Tiny"), equalTo("Frail"), equalTo("Dull")));
	}
	
	@Test
	public void testAdjectiveGeneration_SecondLevel() {
		final int testAttackMax = 2;
		final int testDefenseMax = 1;
		final int testAttackMin = 2;
		final int testDefenseMin = 1;
		Card card = CardFactory.generateTestCard(testAttackMax, testDefenseMax, testAttackMin, testDefenseMin);
		String adjective = getAdjective(card);
		assertThat(adjective, anyOf(equalTo("Green"), equalTo("Puny"), equalTo("Weak"), equalTo("Keen")));
	}
	
	@Test
	public void testAdjectiveGeneration_ThirdLevel() {
		final int testAttackMax = 4;
		final int testDefenseMax = 3;
		final int testAttackMin = 3;
		final int testDefenseMin = 2;
		Card card = CardFactory.generateTestCard(testAttackMax, testDefenseMax, testAttackMin, testDefenseMin);
		String adjective = getAdjective(card);
		assertThat(adjective, anyOf(equalTo("Novice"), equalTo("Tough"), equalTo("Solid"), equalTo("Wise")));
	}
	
	@Test
	public void testAdjectiveGeneration_FourthLevel() {
		final int testAttackMin = ATTACK_MAX;
		final int testDefenseMin = DEFENSE_MAX;
		Card card = CardFactory.generateTestCard(ATTACK_MAX, DEFENSE_MAX, testAttackMin, testDefenseMin);
		String adjective = getAdjective(card);
		assertThat(adjective, anyOf(equalTo("Master"), equalTo("Iron"), equalTo("Armor"), equalTo("Sage")));
	}
	
	@Test
	public void testNounGeneration_FirstLevel() {
		final int testAttackMax = ATTACK_MIN;
		final int testDefenseMax = DEFENSE_MIN;
		Card card = CardFactory.generateTestCard(testAttackMax, testDefenseMax);
		String noun = getNoun(card);
		if (card.getAttackType() == AttackType.MAGICAL) {
			assertThat(noun, equalTo("Faker"));
		} else {
			assertThat(noun, equalTo("Man"));
		}
	}
	
	@Test
	public void testNounGeneration_SecondLevel() {
		final int testAttackMax = 2;
		final int testDefenseMax = 1;
		final int testAttackMin = 2;
		final int testDefenseMin = 1;
		Card card = CardFactory.generateTestCard(testAttackMax, testDefenseMax, testAttackMin, testDefenseMin);
		String noun = getNoun(card);
		if (card.getAttackType() == AttackType.MAGICAL) {
			assertThat(noun, equalTo("Witch"));
		} else {
			assertThat(noun, equalTo("Cadet"));
		}
	}
	
	@Test
	public void testNounGeneration_ThirdLevel() {
		final int testAttackMax = 4;
		final int testDefenseMax = 3;
		final int testAttackMin = 3;
		final int testDefenseMin = 2;
		Card card = CardFactory.generateTestCard(testAttackMax, testDefenseMax, testAttackMin, testDefenseMin);
		String noun = getNoun(card);
		if (card.getAttackType() == AttackType.MAGICAL) {
			assertThat(noun, equalTo("Mage"));
		} else {
			assertThat(noun, equalTo("Knight"));
		}
	}
	
	@Test
	public void testNounGeneration_FourthLevel() {
		final int testAttackMin = ATTACK_MAX;
		final int testDefenseMin = DEFENSE_MAX;
		Card card = CardFactory.generateTestCard(ATTACK_MAX, DEFENSE_MAX, testAttackMin, testDefenseMin);
		String noun = getNoun(card);
		if (card.getAttackType() == AttackType.MAGICAL) {
			assertThat(noun, equalTo("Genie"));
		} else {
			assertThat(noun, equalTo("Hero"));
		}
	}
	
	/**
	 * Get the adjective from the name of the given card
	 * @param card
	 * @return
	 */
	private String getAdjective(Card card) {
		return card.getName().split("\\s+")[0];
	}
	
	/**
	 * Get the noun form the name of the given card
	 * @param card
	 * @return
	 */
	private String getNoun(Card card) {
		return card.getName().split("\\s+")[1];
	}
}
