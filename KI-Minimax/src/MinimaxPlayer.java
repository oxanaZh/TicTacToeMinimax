import java.util.ArrayList;

public class MinimaxPlayer extends Player {

	@Override
	public Field makeMove(GameBoard board) {
		
		return negaMaxInit(board);
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
	public ArrayList<GameBoard> successors(GameBoard board, Mark mark) {
		ArrayList<GameBoard> moves = new ArrayList<>();

		GameBoard temp;

		for (int row = 0; row < 3; row++) {
			for (int column = 0; column < 3; column++) {
				if (board.getFieldOf(row, column).isEmpty()) {
					temp = new GameBoard(board.getFields());
					temp.setField(new Field(row, column, mark));
					temp.setLastMove(temp.getFieldOf(row, column));
					moves.add(temp);
				}
			}
		}

		return moves;
	}

	
	public Field negaMaxInit(GameBoard state){
		Field move = null;
		int maxWert = Integer.MIN_VALUE;
		ArrayList<GameBoard> successors = successors(state, getMark());
		for(GameBoard s : successors){
			int v = negaMax(s,getMark());
			if (v >= maxWert){
				maxWert = v;
				move = s.getLastMove();
			}
		}
		return move;
	}
	
	public int negaMax(GameBoard state, Mark mark){
		if (terminalTest(state)) {
			return utility(state);
		}
		
		int maxWert = Integer.MIN_VALUE;
		ArrayList<GameBoard> successors = successors(state, mark);
		for(GameBoard s: successors){
			
			int v = -1 * negaMax(s,mark==Mark.X? Mark.O : Mark.X);
			if(v > maxWert){
				maxWert = v;
			}
			
		}
		
		return maxWert;
		
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
