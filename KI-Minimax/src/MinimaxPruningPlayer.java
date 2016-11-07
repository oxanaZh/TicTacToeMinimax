import java.util.ArrayList;

public class MinimaxPruningPlayer extends Player {
	
	private int nodeCounter = 0;
	
	public int getNodeCounter(){
		return this.nodeCounter;
	}

	@Override
	public Field makeMove(GameBoard board) {
		
		return Minimax(board);
	}

	public MinimaxPruningPlayer(String name, Mark m) {
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
	
	public Field Minimax(GameBoard state){
		int val = Integer.MIN_VALUE;
		Field move = null;
		ArrayList<GameBoard> successors = successors(state, getMark());
		for(GameBoard s : successors){
			int v = MinValue(s, Integer.MIN_VALUE, Integer.MAX_VALUE);
			if (val <= v){
				val = v;
				move = s.getLastMove();
			}
		}
		return move;
	}

	public int MaxValue(GameBoard state, int alpha, int beta) {
		if (terminalTest(state)) {
			return utility(state);
		}

		int v = Integer.MIN_VALUE;
		ArrayList<GameBoard> successors = successors(state, getMark());
		for (GameBoard s : successors) {
			v = Math.max(v, MinValue(s, alpha, beta));
			if(v>=beta){
				return v;
			}
			alpha = Math.max(alpha,v);
		}
		nodeCounter++;
		return v;
	}

	public int MinValue(GameBoard state, int alpha, int beta) {
		if (terminalTest(state)) {
			return utility(state);
		}

		int v = Integer.MAX_VALUE;
		ArrayList<GameBoard> successors = successors(state, getMark()==Mark.X? Mark.O : Mark.X);
		for (GameBoard s : successors) {
			v = Math.min(v, MaxValue(s, alpha, beta));
			if(v<=alpha){
				return v;
			}
			beta = Math.min(beta, v);
		}
		nodeCounter++;
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
