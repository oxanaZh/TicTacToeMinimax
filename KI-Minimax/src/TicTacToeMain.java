
public class TicTacToeMain {

   public static void main(String[] args) {
         
      TicTacToe game = new  TicTacToe("NegaMax", "Random", PlayerType.MINIMAX, PlayerType.RANDOM);
      game.play();
   }

}
