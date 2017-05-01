import javax.swing.JFrame;

public class PenteMain {

	public static final int EMPTY =0;
	public static final int DARK = 1;
	public static final int LIGHT = -1;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int boardWidth = 720;
		int boardWidthInSquares = 19;
		
		JFrame f = new JFrame ("Play Pente -- All the Time...");
		
		PenteGameBoard p = new PenteGameBoard(boardWidth, boardWidthInSquares);
		f.add(p);
		f.setSize(boardWidth, boardWidth);
		
		f.setVisible(true);
	}

}




