CardGame
========

creating players...
```
Player playerOne = new OrderedPlayer(PlayerIdentity.LEFT_PLAYER, DeckFactory.generateTestDeck());
Player playerTwo = new CardCountDisparityPlayer(PlayerIdentity.RIGHT_PLAYER, DeckFactory.generateTestDeck(), 5);
```

creating a game board...
```
Board board = new Board(3, 3);
```

creating a game instance...
```
Game.Builder builder = new Game.Builder(playerOne, playerTwo, board);
Game game = builder.build();
```

playing a game...
```
while (game.getWinner() == null) {
	// allow the next player to take their turn
	game.takePlayerTurn();

	// initiate any battles
	game.resolveBoard();
}

System.out.println(game.getWinner());
```
