import java.util.ArrayList;

public class MinimaxPlayer extends Player {

	@Override
	public Field makeMove(GameBoard board) {
		System.out.println("THE BOARD" + board.toString());
		return Minimax(board);
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
	public ArrayList<GameBoard> successors(GameBoard board, boolean minval) {
		ArrayList<GameBoard> moves = new ArrayList<>();

		GameBoard temp;

		Mark mark = getMark();
		if(minval){
			if(mark == Mark.X){
				mark = Mark.O;
			}
			else
			{
				mark = Mark.X;
			}
		}
		/*
		 * for(int i=0;i<3;i++){ for(int x=0;x<3;x++){ if(board.getFieldOf(i,
		 * x).isEmpty()){ temp = new Field(i,x,getMark()); moves.add(temp); } }
		 * }
		 */

		for (int row = 0; row < 3; row++) {
			for (int column = 0; column < 3; column++) {
				if (board.getFieldOf(row, column).isEmpty()) {
					temp = new GameBoard(board.getFields());
					//
					System.out.println("print temp" + temp.toString());
					//
					temp.getFieldOf(row, column).setMark(mark);
					temp.setLastMove(temp.getFieldOf(row, column));
					moves.add(temp);
				}
			}
		}

		return moves;
	}
	
	public Field Minimax(GameBoard state){
		int val = Integer.MIN_VALUE;
		Field move = null;
		for(GameBoard s:successors(state,false)){
			int v = MinValue(s);
			if (val <= v){
				val = v;
				move = s.getLastMove();
			}
		}
		return move;
	}

	public int MaxValue(GameBoard state) {
		if (terminalTest(state)) {
			return utility(state);
		}

		int v = Integer.MIN_VALUE;

		for (GameBoard s : successors(state,false)) {
			v = Math.max(v, MinValue(s));
		}
		return v;
	}

	public int MinValue(GameBoard state) {
		if (terminalTest(state)) {
			return utility(state);
		}

		int v = Integer.MAX_VALUE;

		for (GameBoard s : successors(state,true)) {
			v = Math.min(v, MinValue(s));
		}
		return v;
	}

	public int utility(GameBoard state) {
		int utility = 0;
		if(state.isFull() && !state.isWinState()){
			return 0;
		}
		Field lastmove = state.getLastMove();
		if(lastmove.equalsMark(getMark())){
			utility = 1;
		} else {
			utility = -1;
		}
		
		return utility;
	}

	public boolean terminalTest(GameBoard state) {
		return state.isEndState();
	}

}
