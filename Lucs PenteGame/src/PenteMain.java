import javax.swing.JFrame;

//import javax.swing.JFrame;

public class PenteMain {
	public static final int EMPTY = 0;
	public static final int BLACK = 1;
	public static final int WHITE = -1;
	public static void main(String[] args){
		int boardWidth = 720;
		int boardWidthInSquares = 19;
		
		JFrame f = new JFrame("Play Pente");
		PenteGameBoard pb = new PenteGameBoard(boardWidth, boardWidthInSquares);
		f.setSize(boardWidth, boardWidth);
		//angry bird, take the red bird put him in a slingshot and sling him at a pig, hit pig +1000 points and 3 stars
		f.setVisible(true);
		
		f.add(pb);
	}
}
