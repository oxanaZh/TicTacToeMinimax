
public class TicTacToeMain {

   public static void main(String[] args) {
         
      TicTacToe game = new  TicTacToe("MiniMax", "Random", PlayerType.MINIMAX, PlayerType.RANDOM);
      game.play();
   }

}
