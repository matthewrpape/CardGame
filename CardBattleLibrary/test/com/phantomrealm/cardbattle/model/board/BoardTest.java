package com.phantomrealm.cardbattle.model.board;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.nullValue;

import java.util.List;

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
	
	@Test
	public void testIsFull_NotFull() {
		final Board testBoard = createTestBoard();
		assertThat(testBoard.isFull(), equalTo(false));
	}
	
	@Test
	public void testIsFull_Full() {
		final Board testBoard = createTestBoard();
		final Card testCard = createTestCard();
		for (int row = 0; row < testBoard.getHeight(); ++row) {
			for (int col = 0; col < testBoard.getWidth(); ++col) {
				final BoardSlot testSlot = testBoard.getBoardSlot(row, col);
				testSlot.setCard(testCard);
				testSlot.setSlotOwner(PlayerIdentity.LEFT_PLAYER);
			}
		}
		assertThat(testBoard.isFull(), equalTo(true));
	}
	
	@Test
	public void testExecuteMove_NullMove() {
		final Board testBoard = createTestBoard();
		final Board clonedBoard = testBoard.clone();
		testBoard.executeMove(createTestCard(), PlayerIdentity.LEFT_PLAYER, null);
		// move should fail, leaving the testBoard in the same state as the clonedBoard
		for (int row = 0; row < testBoard.getHeight(); ++row) {
			for (int col = 0; col < testBoard.getWidth(); ++col) {
				final BoardSlot testSlot = testBoard.getBoardSlot(row, col);
				final BoardSlot cloneSlot = clonedBoard.getBoardSlot(row, col);
				assertThat(testSlot.equals(cloneSlot), equalTo(true));
			}
		}
	}
	
	@Test
	public void testExecuteMove_ValidMove() {
		final Board testBoard = createTestBoard();
		final Card testCard = createTestCard();
		final PlayerIdentity testOwner = PlayerIdentity.RIGHT_PLAYER;
		final int testRow = 0;
		final int testCol = 0;
		final Position testPosition = new Position(testRow, testCol);
		assertThat(testBoard.getBoardSlot(testRow, testCol).getOwner(), nullValue());
		assertThat(testBoard.getBoardSlot(testRow, testCol).getCard(), nullValue());
		
		testBoard.executeMove(testCard, testOwner, testPosition);
		assertThat(testBoard.getBoardSlot(testRow, testCol).getOwner(), equalTo(testOwner));
		assertThat(testBoard.getBoardSlot(testRow, testCol).getCard(), equalTo(testCard));
	}
	
	@Test
	public void testResolveBoardConflicts_NoConflicts() {
		final Board testBoard = createTestBoard();
		final Board clonedBoard = testBoard.clone();
		testBoard.resolveBoardConflicts();
		// there should be no conflicts to resolve, leaving the testBoard in the same state as the clonedBoard
		for (int row = 0; row < testBoard.getHeight(); ++row) {
			for (int col = 0; col < testBoard.getWidth(); ++col) {
				final BoardSlot testSlot = testBoard.getBoardSlot(row, col);
				final BoardSlot cloneSlot = clonedBoard.getBoardSlot(row, col);
				assertThat(testSlot.equals(cloneSlot), equalTo(true));
			}
		}
	}
	
	@Test
	public void testResolveBoardConflicts_OneStalemate() {
		final Board testBoard = createTestBoard();
		final int testRow = 1;
		final int testCol = 1;
		final PlayerIdentity testOwner = PlayerIdentity.RIGHT_PLAYER;
		final Card testCard = createTestCard();
		testCard.setAttack(4);
		testCard.setDefense(5);
		testBoard.getBoardSlot(testRow, testCol).setCard(testCard);
		testBoard.getBoardSlot(testRow, testCol).setSlotOwner(testOwner);
		final Board clonedBoard = testBoard.clone();
		testBoard.resolveBoardConflicts();
		// the only conflict should be a stalemate, leaving the testBoard in the same state as the clonedBoard
		for (int row = 0; row < testBoard.getHeight(); ++row) {
			for (int col = 0; col < testBoard.getWidth(); ++col) {
				final BoardSlot testSlot = testBoard.getBoardSlot(row, col);
				final BoardSlot cloneSlot = clonedBoard.getBoardSlot(row, col);
				assertThat(testSlot.equals(cloneSlot), equalTo(true));
			}
		}
	}
	
	@Test
	public void testResolveBoardConflicts_ConflictBothDie() {
		final Board testBoard = createTestBoard();
		final int testRow = 1;
		final int testCol = 1;
		final int leftCol = 0;
		final PlayerIdentity testOwner = PlayerIdentity.RIGHT_PLAYER;
		final Card testCard = createTestCard();
		testBoard.getBoardSlot(testRow, testCol).setCard(testCard);
		testBoard.getBoardSlot(testRow, testCol).setSlotOwner(testOwner);
		final Board clonedBoard = testBoard.clone();
		
		testBoard.resolveBoardConflicts();
		// the only conflict should result in both cards being destroyed
		final BoardSlot testLeftSlot = testBoard.getBoardSlot(testRow, leftCol);
		final BoardSlot testRightSlot = testBoard.getBoardSlot(testRow, testCol);
		final BoardSlot cloneLeftSlot = clonedBoard.getBoardSlot(testRow, leftCol);
		final BoardSlot cloneRightSlot = clonedBoard.getBoardSlot(testRow, testCol);
		assertThat(testLeftSlot.equals(cloneLeftSlot), equalTo(false));
		assertThat(testRightSlot.equals(cloneRightSlot), equalTo(false));
		assertThat(testLeftSlot.getOwner(), equalTo(null));
		assertThat(testLeftSlot.getCard(), equalTo(null));
		assertThat(testRightSlot.getOwner(), equalTo(null));
		assertThat(testRightSlot.getCard(), equalTo(null));
	}
	
	@Test
	public void testResolveBoardConflicts_ConflictOneDies() {
		final Board testBoard = createTestBoard();
		final int testRow = 1;
		final int testCol = 1;
		final int leftCol = 0;
		final PlayerIdentity testOwner = PlayerIdentity.RIGHT_PLAYER;
		final Card testCard = createTestCard();
		testCard.setDefense(5);
		testBoard.getBoardSlot(testRow, testCol).setCard(testCard);
		testBoard.getBoardSlot(testRow, testCol).setSlotOwner(testOwner);
		final Board clonedBoard = testBoard.clone();
		
		testBoard.resolveBoardConflicts();
		// the only conflict should result in one card being destroyed
		final BoardSlot testLeftSlot = testBoard.getBoardSlot(testRow, leftCol);
		final BoardSlot testRightSlot = testBoard.getBoardSlot(testRow, testCol);
		final BoardSlot cloneLeftSlot = clonedBoard.getBoardSlot(testRow, leftCol);
		final BoardSlot cloneRightSlot = clonedBoard.getBoardSlot(testRow, testCol);
		assertThat(testLeftSlot.equals(cloneLeftSlot), equalTo(false));
		assertThat(testRightSlot.equals(cloneRightSlot), equalTo(true));
		assertThat(testLeftSlot.getOwner(), equalTo(null));
		assertThat(testLeftSlot.getCard(), equalTo(null));
		assertThat(testRightSlot.getOwner(), equalTo(testOwner));
		assertThat(testRightSlot.getCard().equals(testCard), equalTo(true));
	}
	
	@Test
	public void testResolveBoardStalemates_NoStalemates() {
		final Board testBoard = createTestBoard();
		final Board clonedBoard = testBoard.clone();
		testBoard.resolveBoardStalemates();
		// there should be no stalemates to resolve, leaving the testBoard in the same state as the clonedBoard
		for (int row = 0; row < testBoard.getHeight(); ++row) {
			for (int col = 0; col < testBoard.getWidth(); ++col) {
				final BoardSlot testSlot = testBoard.getBoardSlot(row, col);
				final BoardSlot cloneSlot = clonedBoard.getBoardSlot(row, col);
				assertThat(testSlot.equals(cloneSlot), equalTo(true));
			}
		}
	}
	
	@Test
	public void testResolveBoardStalemates_BoardNotFull() {
		final Board testBoard = createTestBoard();
		final int testRow = 1;
		final int testCol = 1;
		final PlayerIdentity testOwner = PlayerIdentity.RIGHT_PLAYER;
		final Card testCard = createTestCard();
		testCard.setAttack(4);
		testCard.setDefense(5);
		testBoard.getBoardSlot(testRow, testCol).setCard(testCard);
		testBoard.getBoardSlot(testRow, testCol).setSlotOwner(testOwner);
		final Board clonedBoard = testBoard.clone();
		testBoard.resolveBoardConflicts();
		// there is one stalemate, but it won't be resolved because the board is not empty
		for (int row = 0; row < testBoard.getHeight(); ++row) {
			for (int col = 0; col < testBoard.getWidth(); ++col) {
				final BoardSlot testSlot = testBoard.getBoardSlot(row, col);
				final BoardSlot cloneSlot = clonedBoard.getBoardSlot(row, col);
				assertThat(testSlot.equals(cloneSlot), equalTo(true));
			}
		}
	}
	
	@Test
	public void testResolveBoardStalemates_BoardFull() {
		// fill the board
		final Board testBoard = new Board(2, 2);
		final PlayerIdentity testOwner = PlayerIdentity.RIGHT_PLAYER;
		for (int row = 0; row < testBoard.getHeight(); ++row) {
			for (int col = 0; col < testBoard.getWidth(); ++col) {
				testBoard.executeMove(createTestCard(), testOwner, new Position(row, col));
			}
		}
		
		// create a stalemate
		final int testRow = 0;
		final int leftCol = 0;
		final int rightCol = 1;
		final Card testCard = createTestCard();
		testCard.setAttack(4);
		testCard.setDefense(5);
		testBoard.getBoardSlot(testRow, leftCol).setCard(testCard);
		testBoard.getBoardSlot(testRow, leftCol).setSlotOwner(PlayerIdentity.not(testOwner));
		final Board clonedBoard = testBoard.clone();
		testBoard.resolveBoardStalemates();
		
		// there is one stalemate, and it should be removed
		final BoardSlot testLeftSlot = testBoard.getBoardSlot(testRow, leftCol);
		final BoardSlot testRightSlot = testBoard.getBoardSlot(testRow, rightCol);
		final BoardSlot cloneLeftSlot = clonedBoard.getBoardSlot(testRow, leftCol);
		final BoardSlot cloneRightSlot = clonedBoard.getBoardSlot(testRow, rightCol);
		assertThat(testLeftSlot.equals(cloneLeftSlot), equalTo(false));
		assertThat(testRightSlot.equals(cloneRightSlot), equalTo(false));
		assertThat(testLeftSlot.getOwner(), equalTo(null));
		assertThat(testLeftSlot.getCard(), equalTo(null));
		assertThat(testRightSlot.getOwner(), equalTo(null));
		assertThat(testRightSlot.getCard(), equalTo(null));
	}
	
	@Test
	public void testGetWinner_NoRowsOwned() {
		final Board testBoard = new Board();
		assertThat(testBoard.getWinner(), nullValue());
	}
	
	@Test
	public void testGetWinner_MinorityOfRowsOwned() {
		final Board testBoard = new Board();
		final int testRow = 1;
		final Card testCard = createTestCard();
		final PlayerIdentity testOwner = PlayerIdentity.LEFT_PLAYER;
		for (BoardSlot slot : testBoard.getBoardRow(testRow)) {
			slot.setCard(testCard);
			slot.setSlotOwner(testOwner);
		}
		assertThat(testBoard.getWinner(), nullValue());
	}
	
	@Test
	public void testGetWinner_EvenNumberOfRowsOwned() {
		final Board testBoard = new Board(2, 4);
		final Card testCard = createTestCard();
		for (int row = 0; row < testBoard.getHeight(); ++row) {
			for (int col = 0; col < testBoard.getWidth(); ++col) {
				final BoardSlot slot = testBoard.getBoardSlot(row, col);
				final PlayerIdentity owner = (col % 2 == 0) ? PlayerIdentity.LEFT_PLAYER : PlayerIdentity.RIGHT_PLAYER;
				slot.setCard(testCard);
				slot.setSlotOwner(owner);
			}
		}
		assertThat(testBoard.getWinner(), nullValue());
	}
	
	@Test
	public void testGetWinner_MajorityOfRowsOwned() {
		final Board testBoard = new Board();
		final int testRowStart = 0;
		final int testRowEnd = 1;
		final Card testCard = createTestCard();
		final PlayerIdentity testOwner = PlayerIdentity.LEFT_PLAYER;
		for (int row = testRowStart; row <= testRowEnd; ++row) {
			for (int col = 0; col < testBoard.getWidth(); ++col) {
				final BoardSlot slot = testBoard.getBoardSlot(row, col);
				slot.setCard(testCard);
				slot.setSlotOwner(testOwner);
			}
		}
		assertThat(testBoard.getWinner(), equalTo(PlayerIdentity.LEFT_PLAYER));
	}
	
	@Test
	public void testGetPossibleMoves_LeftPlayerEmptyBoard() {
		final Board testBoard = new Board();
		final PlayerIdentity testPlayer = PlayerIdentity.LEFT_PLAYER;
		List<Position> moves = testBoard.getPossibleMoves(testPlayer);
		assertThat(moves.size(), equalTo(3));
		assertThat(moves.get(0).equals(new Position(0, 0)), equalTo(true));
		assertThat(moves.get(1).equals(new Position(1, 0)), equalTo(true));
		assertThat(moves.get(2).equals(new Position(2, 0)), equalTo(true));
	}
	
	@Test
	public void testGetPossibleMoves_RightPlayerEmptyBoard() {
		final Board testBoard = new Board();
		final PlayerIdentity testPlayer = PlayerIdentity.RIGHT_PLAYER;
		List<Position> moves = testBoard.getPossibleMoves(testPlayer);
		assertThat(moves.size(), equalTo(3));
		assertThat(moves.get(0).equals(new Position(0, 2)), equalTo(true));
		assertThat(moves.get(1).equals(new Position(1, 2)), equalTo(true));
		assertThat(moves.get(2).equals(new Position(2, 2)), equalTo(true));
	}
	
	@Test
	public void testGetPossibleMoves_PartiallyFilledBoard() {
		final Board testBoard = createTestBoard();
		final PlayerIdentity testPlayer = PlayerIdentity.LEFT_PLAYER;
		final Card testCard = createTestCard();
		for (BoardSlot testSlot : testBoard.getBoardRow(0)) {
			testSlot.setCard(testCard);
			testSlot.setSlotOwner(PlayerIdentity.not(testPlayer));
		}
		List<Position> moves = testBoard.getPossibleMoves(testPlayer);
		assertThat(moves.size(), equalTo(2));
		assertThat(moves.get(0).equals(new Position(1, 1)), equalTo(true));
		assertThat(moves.get(1).equals(new Position(2, 0)), equalTo(true));
	}
	
	@Test
	public void testIsValidMove_ValidLeftPlayerMove() {
		final Board testBoard = new Board();
		final Position testMove = new Position(0, 0);
		assertThat(testBoard.isValidMove(PlayerIdentity.LEFT_PLAYER, testMove), equalTo(true));
		assertThat(testBoard.isValidMove(PlayerIdentity.RIGHT_PLAYER, testMove), equalTo(false));
	}
	
	@Test
	public void testIsValidMove_ValidMoveForOtherPlayer() {
		final Board testBoard = new Board();
		final Position testMove = new Position(0, 2);
		assertThat(testBoard.isValidMove(PlayerIdentity.LEFT_PLAYER, testMove), equalTo(false));
		assertThat(testBoard.isValidMove(PlayerIdentity.RIGHT_PLAYER, testMove), equalTo(true));
	}
	
	@Test
	public void testIsValidMove_InvalidMove() {
		final Board testBoard = new Board();
		final Position testMove = new Position(1, 1);
		assertThat(testBoard.isValidMove(PlayerIdentity.LEFT_PLAYER, testMove), equalTo(false));
		assertThat(testBoard.isValidMove(PlayerIdentity.RIGHT_PLAYER, testMove), equalTo(false));
	}
	
	/**
	 * Create a sample board for testing. The sample board will have a card in one slot.
	 * @return
	 */
	private Board createTestBoard() {
		final int testWidth = 2;
		final int testHeight = 3;
		final int testRow = 1;
		final int testCol = 0;
		final PlayerIdentity testOwner = PlayerIdentity.LEFT_PLAYER;
		final Board board = new Board(testWidth, testHeight);
		board.getBoardSlot(testRow, testCol).setCard(createTestCard());
		board.getBoardSlot(testRow, testCol).setSlotOwner(testOwner);
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