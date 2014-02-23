package com.phantomrealm.cardbattle.view.board;

import com.phantomrealm.cardbattle.controller.player.PlayerIdentity;
import com.phantomrealm.cardbattle.model.board.Board;
import com.phantomrealm.cardbattle.model.board.BoardSlot;
import com.phantomrealm.cardbattle.model.card.AttackType;
import com.phantomrealm.cardbattle.model.card.Card;

/**
 * Displays a visual representation of a Board
 * 
 * @author matthewpape
 */
public class ConsoleBoardView {

	private static final int DEFAULT_CARD_WIDTH = 13;
	private static final int MINIMUM_CARD_WIDTH = 11;
	
	private Board mBoard;
	private int mCardWidth;
	
	/**
	 * Creates a new view for displaying a given board in the console, with each
	 *  card displaying at the default width
	 * @param board
	 */
	public ConsoleBoardView(Board board) {
		this(board, DEFAULT_CARD_WIDTH);
	}
	
	/**
	 * Creates a new view for displaying a given board in the console, with each
	 *  card displaying at the given width
	 * @param board
	 * @param cardWidth must be >= 11
	 */
	public ConsoleBoardView(Board board, int cardWidth) {
		mBoard = board;
		mCardWidth = Math.max(MINIMUM_CARD_WIDTH, cardWidth);
	}
	
	/**
	 * Prints the board to the console
	 */
	public void displayBoard() {
		for (int row = 0; row < mBoard.getHeight(); ++row) {
			displayRow(mBoard.getBoardRow(row));
		}
		System.out.println("");
	}
	
	/**
	 * Print one row of the board to the console
	 * @param row
	 */
	private void displayRow(BoardSlot[] row) {
		for (BoardSlot slot : row) {
			printEdge(slot);
		}
		System.out.println();
		
		for (BoardSlot slot : row) {
			printName(slot.getCard());
		}
		System.out.println();
		
		for (int slot = 0; slot < row.length; ++slot) {
			printEmpty();
		}
		System.out.println();
		
		for (BoardSlot slot : row) {
			printAttackType(slot.getCard());
		}
		System.out.println();
		
		for (BoardSlot slot : row) {
			printAttack(slot.getCard());
		}
		System.out.println();
		
		for (BoardSlot slot : row) {
			printDefense(slot.getCard());
		}
		System.out.println();
		
		for (BoardSlot slot : row) {
			printResistance(slot.getCard());
		}
		System.out.println();
		
		for (BoardSlot slot : row) {
			printEdge(slot);
		}
		System.out.println();
		
		
	}
	
	/**
	 * Prints the upper or lower edge for a single slot
	 * @param slot
	 */
	private void printEdge(BoardSlot slot) {
		PlayerIdentity owner = slot.getOwner();
		char edge = '=';
		if (owner == PlayerIdentity.LEFT_PLAYER) {
			edge = '<';
		} else if (owner == PlayerIdentity.RIGHT_PLAYER) {
			edge = '>';
		}
		for (int i = 0; i < mCardWidth; ++i) {
			System.out.print(edge);
		}
	}
	
	/**
	 * Prints an empty row within a single slot
	 */
	private void printEmpty() {
		System.out.print("|");
		for (int i = 0; i < mCardWidth - 2; ++i) {
			System.out.print(" ");
		}
		System.out.print("|");
	}
	
	/**
	 * Prints the name of the card within a single slot, or a substring
	 *  of the name if it is too long, or an empty row if the card is null
	 * @param card
	 */
	private void printName(Card card) {
		if (card != null) {
			int width = mCardWidth - 4;
			StringBuilder builder = new StringBuilder("| ");
			String name = card.getName();
			if (name.length() > width) {
				builder.append(name.substring(0, width));
			} else {
				builder.append(name);
				for (int i = 0; i < width - name.length(); ++i) {
					builder.append(" ");
				}
			}
			builder.append(" |");
			System.out.print(builder.toString());
		} else {
			printEmpty();
		}
	}
	
	
	/**
	 * Prints the AttackType of the card within a single slot,
	 *  or an empty row if the card is null
	 * @param card
	 */
	private void printAttackType(Card card) {
		if (card != null) {
			StringBuilder builder = new StringBuilder();
			String typeString = "";
			AttackType type = card.getAttackType();
			switch (type) {
			case MAGICAL:
				typeString = "| type: mag ";
				break;
			case PHYSICAL:
				typeString = "| type: phys";
				break;
			}
			if (mCardWidth < 13) {
				builder.append(typeString.substring(0, mCardWidth - 1));
			} else {
				builder.append(typeString);
				for (int i = 0; i < mCardWidth - 13; ++i) {
					builder.append(" ");
				}
			}
			builder.append("|");
			System.out.print(builder.toString());
		} else {
			printEmpty();
		}
	}
	
	/**
	 * Prints the attack of the card within a single slot, or a substring
	 *  of the attack if it is too long, or an empty row if the card is null
	 * @param card
	 */
	private void printAttack(Card card) {
		if (card != null) {
			int width = mCardWidth - 9;
			int attack = card.getAttack();	
			StringBuilder builder = new StringBuilder("| atk: ");
			String attackString = "" + attack;
			if (attackString.length() > width) {
				builder.append(attackString.subSequence(0, width));
			} else {
				builder.append(attackString);
				for (int i = 0; i < width - attackString.length(); ++i) {
					builder.append(" ");
				}
			}
			builder.append(" |");
			System.out.print(builder.toString());
		} else {
			printEmpty();
		}
	}
	
	/**
	 * Prints the defense of the card within a single slot, or a substring
	 *  of the defense if it is too long, or an empty row if the card is null
	 * @param card
	 */
	private void printDefense(Card card) {
		if (card != null) {
			int width = mCardWidth - 9;
			int defense = card.getResistance();	
			StringBuilder builder = new StringBuilder("| def: ");
			String defenseString = "" + defense;
			if (defenseString.length() > width) {
				builder.append(defenseString.subSequence(0, width));
			} else {
				builder.append(defenseString);
				for (int i = 0; i < width - defenseString.length(); ++i) {
					builder.append(" ");
				}
			}
			builder.append(" |");
			System.out.print(builder.toString());
		} else {
			printEmpty();
		}
	}
	
	/**
	 * Prints the resistance of the card within a single slot, or a substring
	 *  of the resistance if it is too long, or an empty row if the card is null
	 * @param card
	 */
	private void printResistance(Card card) {
		if (card != null) {
			int width = mCardWidth - 9;
			int resistance = card.getResistance();	
			StringBuilder builder = new StringBuilder("| res: ");
			String resistanceString = "" + resistance;
			if (resistanceString.length() > width) {
				builder.append(resistanceString.subSequence(0, width));
			} else {
				builder.append(resistanceString);
				for (int i = 0; i < width - resistanceString.length(); ++i) {
					builder.append(" ");
				}
			}
			builder.append(" |");
			System.out.print(builder.toString());
		} else {
			printEmpty();
		}
	}

}
