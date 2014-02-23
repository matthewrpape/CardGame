package com.phantomrealm.cardbattle.model.board;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.nullValue;

import org.junit.Test;

import com.phantomrealm.cardbattle.controller.player.PlayerIdentity;
import com.phantomrealm.cardbattle.model.card.AttackType;
import com.phantomrealm.cardbattle.model.card.BonusEffect;
import com.phantomrealm.cardbattle.model.card.Card;

public class BoardTest {

	private static final int DEFAULT_BOARD_WIDTH = 3;
	private static final int DEFAULT_BOARD_HEIGHT = 3;
	private static final int MIN_BOARD_WIDTH = 2;
	private static final int MIN_BOARD_HEIGHT = 1;
	
	@Test
	public void testConstructor_NoParams() {
		final Board board = new Board();
		assertThat(board.getWidth(), equalTo(DEFAULT_BOARD_WIDTH));
		assertThat(board.getHeight(), equalTo(DEFAULT_BOARD_HEIGHT));
	}
	
	@Test
	public void testConstructor_ValidParams() {
		final int testWidth = 4;
		final int testHeight = 5;
		final Board board = new Board(testWidth, testHeight);
		assertThat(board.getWidth(), equalTo(testWidth));
		assertThat(board.getHeight(), equalTo(testHeight));
	}
	
	@Test
	public void testConstructor_InvalidParams() {
		final int testWidth = 1;
		final int testHeight = 0;
		final Board board = new Board(testWidth, testHeight);
		assertThat(board.getWidth(), equalTo(MIN_BOARD_WIDTH));
		assertThat(board.getHeight(), equalTo(MIN_BOARD_HEIGHT));
	}
	
	@Test
	public void testClone() {
		final Board board = createTestBoard();
		final Board clone = board.clone();
		assertThat(clone.getWidth(), equalTo(board.getWidth()));
		assertThat(clone.getHeight(), equalTo(board.getHeight()));
		
		for (int row = 0; row < clone.getHeight(); ++row) {
			for (int col = 0; col < clone.getWidth(); ++col) {
				assertThat(clone.getBoardSlot(row, col).equals(board.getBoardSlot(row, col)), equalTo(true));
			}
		}
	}
	
	@Test
	public void testGetPosition_Valid() {
		final Board testBoard = createTestBoard();
		for (int row = 0; row < testBoard.getHeight(); ++row) {
			for (int col = 0; col < testBoard.getWidth(); ++col) {
				final BoardSlot testSlot = testBoard.getBoardSlot(row, col);
				final Position position = testBoard.getPosition(testSlot);
				assertThat(position.getRow(), equalTo(row));
				assertThat(position.getColumn(), equalTo(col));
			}
		}
	}
	
	@Test
	public void testGetPosition_Invalid() {
		final Board testBoard = createTestBoard();
		final BoardSlot testSlot = new BoardSlot();
		assertThat(testBoard.getPosition(testSlot), nullValue());
	}
	
	/**
	 * Create a sample board for testing. The sample board will have a card in one slot.
	 * @return
	 */
	private Board createTestBoard() {
		final int testWidth = 2;
		final int testHeight = 4;
		final int testRow = 1;
		final int testCol = 0;
		final PlayerIdentity testOwner = PlayerIdentity.LEFT_PLAYER;
		final Board board = new Board(testWidth, testHeight);
		board.getBoardSlot(testCol, testRow).setCard(createTestCard());
		board.getBoardSlot(testCol, testRow).setSlotOwner(testOwner);
		return board;
	}
	
	/**
	 * Create a sample card for testing
	 * @return
	 */
	private Card createTestCard() {
		final String testName = "Legendary Outrider";
		final BonusEffect testEffect = null;
		final AttackType testType = AttackType.PHYSICAL;
		final int testAttack = 5;
		final int testDefense = 4;
		final int testResistance = 3;
		return new Card(testName, testEffect, testType, testAttack, testResistance, testDefense);
	}
}