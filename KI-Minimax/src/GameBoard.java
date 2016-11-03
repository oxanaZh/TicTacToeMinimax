
public class GameBoard {
   private Field[][] fields;
   private boolean full;
   private int size = 3;
   public GameBoard(){
      fields = new Field[size][size];
      clearFields();
      full = false;
   }
   private void clearFields(){
      for(int row=0;row<size;row++){
         for(int column=0;column<size;column++){
            fields[row][column] = new Field();
         }
      }
   }
/**
 * 
 * @param newField
 * @return true wenn der Feld erfolgreich gesetzt wurde, das heist wenn diese feld vorher Leer war, sonst false
 */
   public boolean setField(Field newField){
      boolean done = false;
      if(fields[newField.getRow()][newField.getCollumn()].isEmpty()){
         fields[newField.getRow()][newField.getCollumn()]=newField;
         done = true;
      }
      return done;
   }
   
   public boolean isFull(){
      for(Field[] m1: fields){
         for(Field m2: m1){
            if(m2.getMark()==Mark.EMPTY){
               full = false;
               return full;
            }
         } 
      }
      full = true;
      return full;
   }
   /**
    * zeigt den aktuellen brettzustand
    */
   public String toString(){
      StringBuilder string = new StringBuilder();
      string.append(" - - - \n");
      for(Field[] m1: fields){
         for(Field m2: m1){
            
            switch(m2.getMark()){
               case EMPTY:
                  string.append("| ");
                  break;
               case X:
                  string.append("|X");
                  break;
               case O:
                  string.append("|O");
                  break;
            }
         }
         string.append("|\n");
      }
      string.append(" - - -\n");
      return string.toString();
   }
   public Field getFieldOf(int row, int collumn){
      return fields[row][collumn];
   }
   public int getSize(){
      return this.size;
   }
   
}