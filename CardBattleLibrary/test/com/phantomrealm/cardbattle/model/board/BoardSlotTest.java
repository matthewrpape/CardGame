package com.phantomrealm.cardbattle.model.board;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;

import org.junit.Test;

import com.phantomrealm.cardbattle.controller.player.PlayerIdentity;
import com.phantomrealm.cardbattle.model.card.AttackType;
import com.phantomrealm.cardbattle.model.card.Card;

public class BoardSlotTest {

	private static final String TEST_NAME = "Fantastic Bard";
	private static final AttackType TEST_TYPE = AttackType.PHYSICAL;
	private static final int TEST_ATTACK = 5;
	private static final int TEST_RESISTANCE = 4;
	private static final int TEST_DEFENSE = 3;
	
	@Test
	public void testConstructor() {
		final BoardSlot testSlot = new BoardSlot();
		assertThat(testSlot.getCard(), nullValue());
		assertThat(testSlot.getOwner(), nullValue());
	}
	
	@Test
	public void testClone_Equal() {
		final BoardSlot testSlot = new BoardSlot();
		BoardSlot clonedSlot = testSlot.clone();
		assertThat(clonedSlot.getCard(), nullValue());
		assertThat(clonedSlot.getOwner(), nullValue());
		
		testSlot.setCard(createTestCard());
		testSlot.setSlotOwner(PlayerIdentity.LEFT_PLAYER);
		clonedSlot = testSlot.clone();
		assertThat(clonedSlot.getCard().equalTo(testSlot.getCard()), equalTo(true));
		assertThat(clonedSlot.getOwner().equals(testSlot.getOwner()), equalTo(true));
	}
	
	@Test
	public void testClone_Distinct() {
		final BoardSlot testSlot = new BoardSlot();
		testSlot.setCard(createTestCard());
		testSlot.setSlotOwner(PlayerIdentity.LEFT_PLAYER);
		final BoardSlot clonedSlot = testSlot.clone();
		assertThat(clonedSlot.getCard().equalTo(testSlot.getCard()), equalTo(true));
		assertThat(clonedSlot.getCard(), not(testSlot.getCard()));
	}
	
	@Test
	public void testEqualTo_Equal() {
		final BoardSlot testSlot = new BoardSlot();
		final BoardSlot secondSlot = new BoardSlot();
		assertThat(testSlot.equalTo(secondSlot), equalTo(true));
		
		testSlot.setCard(createTestCard());
		testSlot.setSlotOwner(PlayerIdentity.LEFT_PLAYER);
		secondSlot.setCard(createTestCard());
		secondSlot.setSlotOwner(PlayerIdentity.LEFT_PLAYER);
		assertThat(testSlot.equalTo(secondSlot), equalTo(true));
	}
	
	@Test
	public void testEqualTo_NotEqual() {
		final BoardSlot testSlot = new BoardSlot();
		final BoardSlot secondSlot = new BoardSlot();
		testSlot.setCard(createTestCard());
		testSlot.setSlotOwner(PlayerIdentity.LEFT_PLAYER);
		assertThat(testSlot.equalTo(secondSlot), equalTo(false));
		
		secondSlot.setCard(createTestCard());
		secondSlot.setSlotOwner(PlayerIdentity.RIGHT_PLAYER);
		assertThat(testSlot.equalTo(secondSlot), equalTo(false));
	}
	
	/**
	 * Create a sample card for testing
	 * @return
	 */
	private Card createTestCard() {
		return new Card(TEST_NAME, null, TEST_TYPE, TEST_ATTACK, TEST_RESISTANCE, TEST_DEFENSE);
	}
}