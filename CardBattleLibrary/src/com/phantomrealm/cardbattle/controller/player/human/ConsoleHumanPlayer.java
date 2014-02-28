package com.phantomrealm.cardbattle.controller.player.human;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import com.phantomrealm.cardbattle.controller.player.BasePlayer;
import com.phantomrealm.cardbattle.controller.player.PlayerIdentity;
import com.phantomrealm.cardbattle.model.board.Board;
import com.phantomrealm.cardbattle.model.board.Position;
import com.phantomrealm.cardbattle.model.card.Card;
import com.phantomrealm.cardbattle.model.deck.Deck;

/**
 * A human controlled player who accepts input through the console
 * 
 * @author matthewpape
 */
public class ConsoleHumanPlayer extends BasePlayer {

	/**
	 * Creates a new player who accepts input through the console to make moves
	 * @param identity
	 * @param deck
	 */
	public ConsoleHumanPlayer(PlayerIdentity identity, Deck deck) {
		super(identity, deck);
	}

	@Override
	public Position getMove(Board board, Deck opponentDeck) {
		Card card = getDeck().peek();
		System.out.println(card.toString());
		Position move = null;
		List<Position> moves = board.getPossibleMoves(getIdentity());
		System.out.print("Possible moves: ");
		for (Position position : moves) {
			System.out.print("(" + position.getColumn() + ", " + position.getRow() + ") ");
		}
		System.out.println("");
		while (!board.isValidMove(getIdentity(), move)) {
			try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		        System.out.println("Enter move \"x, y\":");
		        String input = reader.readLine();
		        input = input.replaceAll("\\s+","");
				int col = Integer.parseInt(input.substring(0, input.indexOf(',')));
				int row = Integer.parseInt(input.substring(input.indexOf(',') + 1));
				move = new Position(row, col);
			} catch (IOException exception) {
				System.out.println(exception.toString());
			} catch (NumberFormatException exception) {
				System.out.println(exception.toString());
			} catch (StringIndexOutOfBoundsException exception) {
				System.out.println(exception.toString());
			}
		}
		return move;
	}

}