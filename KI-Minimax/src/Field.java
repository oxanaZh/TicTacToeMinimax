
public class Field {
   
   private Mark mark = Mark.EMPTY;
   private boolean empty = true;
   private int row;
   private int collumn;
   
   
   public Field(){
      this(0,0,Mark.EMPTY);     
   }
   public Field(int row,int collumn,Mark m){
      setMark(m);
      this.setRow(row);
      this.setCollumn(collumn);
   }
   
   public void setMark(Mark m){
      if(m != Mark.EMPTY){
         if(isEmpty()){
            this.mark = m;
            empty = false;
         }  
      }
          
   }
   
   public boolean isEmpty(){
      return empty;
   }
   
   public Mark getMark(){
      return this.mark;
   }
   public int getRow() {
      return row;
   }
   public void setRow(int row) {
      this.row = row;
   }
   public int getCollumn() {
      return collumn;
   }
   public void setCollumn(int collumn) {
      this.collumn = collumn;
   }
   public boolean equalsMark(Mark m){
      return (this.mark==m);
   }
   

}
