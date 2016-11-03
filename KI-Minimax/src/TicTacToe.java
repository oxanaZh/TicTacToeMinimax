import java.util.Random;

public class TicTacToe {
   
   private Player[] players;
   private GameBoard gameBoard;
   private enum GameStates {START,CONTINUE,END};  
   private GameStates state;
   private Random rand;
   private int spielerAnzahl =2;
   
   /**
    * Überladene Konstruktor
    * kann ohne argumente aufgeruffen werden und ruft zweiten Knstruktor auf mit default Spielernamen
    */  
   public TicTacToe(){
      this("Player1","Player2");
   }
   
   /**
    * Überladene Konstruktor nimmt als argument String Array mit Sielernamen an
    * Inizialisiert Spieler Array, Spielbrett
    * @param names
    */
   public TicTacToe(String... names){
      rand = new Random();
      players = new Player[spielerAnzahl];
      setPlayers(names);
      setGameBoard();
   }
   
   private void setGameBoard(){
      gameBoard = new GameBoard();
   }
   
   /**
    * inizialisiert Spieler Array, nimmt String Array mit Spielernamen an, 
    * füllt den spieler Array mit Spielern in zufällige reihenfolge, so dass z.B Spieler1 nicht unbedingt vorne im Array ist
    * durch die reihenfolge in Spielerarray wird auch bestimmt welcher Spieler zuerst dran ist
    * für jede Name in names array wird zufällig ein freie platz im players array gesucht.
    * @param names
    */
   private void setPlayers(String[] names ){
      for(int i = 0;i<spielerAnzahl;i++){
         boolean done = false;
         while( !done){
            int order = rand.nextInt(spielerAnzahl);
            if(players[order] == null){
               players[order]=new RandomPlayer(names[i],Mark.values()[order]);
               done=true;
            }
         }
      }
   }
   
   private void showGameBoard(){
     System.out.print(gameBoard.toString());
   }
   
   /**
    * setzt game status auf Start und startet das spiel
    * läst die spieler abwechselnd ziehen solange das board nich voll ist
    * nach dem de Spieler sein Zug gemacht hat, das aktuele brett zustand angezeigt
    * anschließen wird ausgewertet ob der spieler gewonnen hat und ob das brett schon voll ist
    * wenn es ein gewinner gibt oder das brett schon voll ist wird das spiel beendet, sons wird s weiter geführt
    */
   public void play(){
      Field markedField = new Field();
      for(Player p : players){
         System.out.printf("%s has: %s\n",p.getName(),p.getMark());   
      }
      state = GameStates.START;
      System.out.print("Start:\n");         
      showGameBoard();
      while(state != GameStates.END){
         for(int i = 0;i <2; i++){
            System.out.print(players[i].getName()+": \n");
            markedField = players[i].makeMove(gameBoard);
            showGameBoard();            
            if(hasWinner(markedField)){
               state = GameStates.END;
               players[i].setHasWon();
               System.out.print("Winner: "+players[i].getName()+"\n");
               break;
            }
            else if(gameBoard.isFull()){
               state = GameStates.END;
               System.out.print("no one has won :)");
               break;
            }
            else{
               state = GameStates.CONTINUE;
            }
            
         }   
      }
      
   }
   /**
    * prüft ob der aktuelle zug zu gewinn geführt hat
    * @param 
    * @return true wenn Markierung von Feld 3 mal hintereinandr auftrit (vrtikal,horizontal oder diagonal), sonst false
    */
   private boolean hasWinner(Field f){
      boolean hasWon = false;
      int markCounter = 0;
      
      //check vertical
      for(int i = 0; i < gameBoard.getSize();i++){
         if(this.gameBoard.getFieldOf(i, f.getCollumn()).equalsMark(f.getMark())){
            markCounter++;
         }
         
      }
      if(markCounter == gameBoard.getSize()){
         hasWon=true;
      }
      markCounter = 0;
      //check horizontal
      for(int i = 0; i < gameBoard.getSize();i++){
         if(this.gameBoard.getFieldOf(f.getRow(),i).equalsMark(f.getMark())){
            markCounter++;
         }
      }
      if(markCounter == gameBoard.getSize()){
         hasWon=true;
      }
      markCounter = 0;
    //check diagonal top right to  left
      for(int i = 0; i < gameBoard.getSize();i++){
         if(this.gameBoard.getFieldOf(i,i).equalsMark(f.getMark())){
            markCounter++;
         }
      }
      if(markCounter == gameBoard.getSize()){
         hasWon=true;
      }
      markCounter = 0;
    //check diagonal top left to  right
      for(int i = gameBoard.getSize()-1; i>=0;i--){
         if(this.gameBoard.getFieldOf(i,i).equalsMark(f.getMark())){
            markCounter++;
         }
      }
      if(markCounter == gameBoard.getSize()){
         hasWon=true;
      }
      System.out.printf("has winner?: %b\n\n",hasWon);
      return hasWon;
   }
   public Player[] getPlayers(){
      return players;
   }
}
