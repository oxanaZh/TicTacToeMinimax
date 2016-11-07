
public class TicTacToeMain {

   public static void main(String[] args) {
         
      TicTacToe game = new  TicTacToe("MiniMax", "Random", PlayerType.MINIMAX, PlayerType.RANDOM);
      game.play();
      
      System.out.println("Count Nodes AVG: " + game.getNodeCounterAverage());
      
      game = new  TicTacToe("MiniMax", "MaxMini", PlayerType.MINIMAX, PlayerType.MINIMAX);
      game.play();
      
      System.out.println("Count Nodes AVG: " + game.getNodeCounterAverage());
   }

}
