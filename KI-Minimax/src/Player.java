
public abstract class Player {
   private String name;
   private Mark mark;
   private boolean hasWon;
   public Player(String name,Mark m){
      setName(name);
      setMark(m);
   }
   
   private void setName(String name){
      this.name = name;
   }
   private boolean setMark(Mark m){
      boolean done = false;
      if(m != Mark.EMPTY){
         this.mark = m;
         done = true;
      }
      else{
         System.out.print("Fehler!Der Spieler kann kein Leerzeichen als Markierung benutzen!");
      }
      return done;
   }
   public abstract Field makeMove(GameBoard board);
   public boolean isWinner(){
      return hasWon;
   }
   public void setHasWon(){
      hasWon = true;
   }
   public Mark getMark(){
      return mark;
   }
   public String getName(){
      return name;
   }

}
