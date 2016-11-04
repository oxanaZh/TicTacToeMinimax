import java.util.ArrayList;

public class MinimaxPlayer extends Player {

	@Override
	public Field makeMove(GameBoard board) {

		return null;
	}

	public MinimaxPlayer(String name, Mark m) {
		super(name, m);
	}

	/*
	 * 1. Erzeuge kompletten Suchbaum mit Tiefensuche 2. Wende Nutzenfunktion
	 * (Utility) auf jeden Endzustand an 3. Ausgehend von Endzuständen ⇒ Bewerte
	 * Vorgängerknoten: - Min-Knoten: Nutzen ist das Minimum der Kindknoten -
	 * Max-Knoten: Nutzen ist das Maximum der Kindknoten 4. Startknoten: Max
	 * wählt den Zug, der zum Nachfolger mit der höchsten Bewertung führt
	 * 
	 */
	public ArrayList<GameBoard> successors(GameBoard board) {
		ArrayList<GameBoard> moves = new ArrayList<>();

		GameBoard temp;

		/*
		 * for(int i=0;i<3;i++){ for(int x=0;x<3;x++){ if(board.getFieldOf(i,
		 * x).isEmpty()){ temp = new Field(i,x,getMark()); moves.add(temp); } }
		 * }
		 */

		for (int row = 0; row < 3; row++) {
			for (int column = 0; column < 3; column++) {
				if (board.getFieldOf(row, column).isEmpty()) {
					temp = new GameBoard(board.getFields());
					temp.getFieldOf(row, column).setMark(getMark());
					moves.add(temp);
				}
			}
		}

		return moves;
	}

	public int MaxValue(GameBoard state) {
		if (terminalTest(state)) {
			return utility();
		}

		int v = Integer.MIN_VALUE;

		for (GameBoard s : successors(state)) {

		}

		return 0;
	}

	public int MinValue(GameBoard state) {

		return 0;
	}

	public int utility() {

		return 0;
	}

	public boolean terminalTest(GameBoard state) {
		if(state.isFull()){
			return true;
		}
		
		//if x won
		
		//if o won
		
		return false;
	}

	public void createSearchTree() {

	}

}
