import java.util.Random;


public class RandomPlayer extends Player{

   private Random rand;
   public RandomPlayer(String name, Mark m) {
      super(name, m);
      rand = new Random();
   }

   @Override
   public Field makeMove(GameBoard board) {
      boolean done = false;
      Field tempField = new Field(0,0,getMark());
      while(!done){
         tempField.setRow(rand.nextInt(3));
         tempField.setCollumn(rand.nextInt(3));
         done = board.setField(tempField);
      }
      return tempField;
      
      
   }
   
   
   

}
