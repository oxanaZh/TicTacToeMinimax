
public class TicTacToeMain {

   public static void main(String[] args) {
	   TicTacToe game;
      /*game = new  TicTacToe("MiniMax", "Random", PlayerType.MINIMAX, PlayerType.RANDOM);
      game.play();
      
      System.out.println("Count Nodes AVG: " + game.getNodeCounterAverage());*/
      
      game = new  TicTacToe("MiniMax", "MinimaxPruning", PlayerType.MINIMAX, PlayerType.MINIMAX_PRUNING);
      game.play();
      int[] counters = game.getPlayersNodeCounter();
      System.out.printf("\nPlayer1 visited nodes count: %d\n Player2 visited nodes count: %d\n", counters[0], counters[1] );
      
      game = new  TicTacToe("MinimaxPruning","MiniMax", PlayerType.MINIMAX_PRUNING, PlayerType.MINIMAX);
      game.play();
      counters = game.getPlayersNodeCounter();
      System.out.printf("\nPlayer1 visited nodes count: %d\n Player2 visited nodes count: %d\n", counters[0], counters[1] );
   }

}
