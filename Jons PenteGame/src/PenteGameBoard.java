import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PenteGameBoard extends JPanel  implements MouseListener{
		int bWidthPixels;
		int bWidthSquares;
		int bSquareWidth;
		int BPL = 0;
		int WPL = 0;
		int whiteCaps = 0;
		int blackCaps = 0;
		Color boardSquareColor = new Color(150, 111, 51);
		
		Square [][] theBoard;
		Filip computerMoveGenerator = null;
		boolean playingAgainstFilip = false;
		int  filipStoneColor;
		
		int currentTurn = PenteMain.DARK;
		
		public PenteGameBoard(int bWPixel, int bWSquares){
			bWidthPixels = bWPixel+19;
			bWidthSquares = bWSquares;
			bSquareWidth = (int)(bWidthPixels/bWidthSquares);
			
			this.setSize(bWidthPixels, bWidthPixels);
			this.setBackground(Color.CYAN);
			
			theBoard = new Square[bWSquares][bWSquares];
			
			for(int row = 0; row< bWidthSquares; ++row){
				for(int col = 0; col< bWSquares; ++col){
					theBoard[row][col] = new Square((col*bSquareWidth), row*bSquareWidth, bSquareWidth, row, col);
				}
			}
			
			
			
			this.addMouseListener(this);
			
			theBoard[(int)(bWidthSquares/2)][(int)(bWidthSquares/2)].setState(PenteMain.DARK);
			String computerAnswer = JOptionPane.showInputDialog("Hi, would you like to play against the computer?");
			if(computerAnswer.equals("yes")){
				computerMoveGenerator = new Filip(this, currentTurn);
				playingAgainstFilip = true;
				filipStoneColor = currentTurn;
			}
			
			this.changeTurn();
		}
		
		public void paintComponent(Graphics g){
			g.setColor(Color.CYAN);
			g.fillRect(0, 0, bWidthPixels, bWidthPixels);
			
			for(int row = 0; row<bWidthSquares; row++){
				for(int col = 0; col<bWidthSquares; col++){
					theBoard[row][col].drawMe(g);
				}
			}
			
		}
		public void changeTurn(){
			if(currentTurn == PenteMain.DARK){
				currentTurn = PenteMain.LIGHT;
			} else {
				currentTurn = PenteMain.DARK;
			}
			
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			int mouseX = e.getX();
			int mouseY = e.getY();
			System.out.println(e.getX() + "\t" +e.getY());
			playGame(e);
			repaint();
			
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		public void playGame(MouseEvent e){
			Square s = findSquare(e.getX(), e.getY());
			if(s != null){
			if(s.getState() == PenteMain.EMPTY){
				s.setState(currentTurn);
			this.checkForCaptures(s);
			checkFor5Win(s);
				this.changeTurn();
				this.repaint();
				this.requestFocus();
				if(playingAgainstFilip == true && currentTurn == filipStoneColor){
					Square cs = computerMoveGenerator.doComputerMove(s.getRow(), s.getCol());
					cs.setState(currentTurn);
					this.repaint();
					this.checkForCaptures(cs);
					checkFor5Win(cs);
					this.changeTurn();
					this.requestFocus();
				}
				
			}else{
				JOptionPane.showMessageDialog(null, "You can't click on a space with a stone!");
			}
				} else {
					JOptionPane.showMessageDialog(null, "You didnt click on a square!");
				}
		}

		public Square findSquare(int mouseX, int mouseY){
			Square clickedOnSquare = null;
			
			for(int row = 0; row<bWidthSquares; ++row){
				for(int col = 0; col<bWidthSquares; ++col){
					if(theBoard[row][col].youClickedMe(mouseX, mouseY) == true){
						clickedOnSquare =theBoard[row][col];
					}
				}
			
			
			}
			return clickedOnSquare;
		}	
		
		private void checkForCaptures(Square s) {
			int sRow = s.getRow();
			int sCol = s.getCol();
			if(sCol<bWidthSquares-3){
				if(theBoard[sRow][sCol].getState() * (-1)== theBoard[sRow][sCol+1].getState()
						&& theBoard[sRow][sCol].getState() * (-1) == theBoard[sRow][sCol+2].getState()
						&& theBoard[sRow][sCol].getState() == theBoard[sRow][sCol+3].getState()){
					theBoard[sRow][sCol+1].setState(PenteMain.EMPTY);
					theBoard[sRow][sCol+2].setState(PenteMain.EMPTY);
					scorekeeper(s);
				} if(sCol>2){
					if(theBoard[sRow][sCol].getState() * (-1)== theBoard[sRow][sCol-1].getState()
							&& theBoard[sRow][sCol].getState() * (-1) == theBoard[sRow][sCol-2].getState()
							&& theBoard[sRow][sCol].getState() == theBoard[sRow][sCol-3].getState()){
						theBoard[sRow][sCol-1].setState(PenteMain.EMPTY);
						theBoard[sRow][sCol-2].setState(PenteMain.EMPTY);
						scorekeeper(s);
					}
				}
			}
					if(sRow < bWidthSquares-3){	
						if(theBoard[sRow][sCol].getState() * (-1)== theBoard[sRow+1][sCol].getState()
								&& theBoard[sRow][sCol].getState() * (-1) == theBoard[sRow+2][sCol].getState()
								&& theBoard[sRow][sCol].getState() == theBoard[sRow+3][sCol].getState()){
							theBoard[sRow+1][sCol].setState(PenteMain.EMPTY);
							theBoard[sRow+2][sCol].setState(PenteMain.EMPTY);
							scorekeeper(s);
						} if(sRow>2){
							if(theBoard[sRow][sCol].getState() * (-1)== theBoard[sRow-1][sCol].getState()
									&& theBoard[sRow][sCol].getState() * (-1) == theBoard[sRow-2][sCol].getState()
									&& theBoard[sRow][sCol].getState() == theBoard[sRow-3][sCol].getState()){
								theBoard[sRow-1][sCol].setState(PenteMain.EMPTY);
								theBoard[sRow-2][sCol].setState(PenteMain.EMPTY);
								scorekeeper(s);
							}
					}
					}
						if(sRow > 2 && sCol < bWidthSquares -3){	
							if(theBoard[sRow][sCol].getState() * (-1) == theBoard[sRow-1][sCol+1].getState()
									&& theBoard[sRow][sCol].getState() * (-1) == theBoard[sRow-2][sCol+2].getState()
									&& theBoard[sRow][sCol].getState() == theBoard[sRow-3][sCol+3].getState()){
								theBoard[sRow-1][sCol+1].setState(PenteMain.EMPTY);
								theBoard[sRow-2][sCol+2].setState(PenteMain.EMPTY);
								scorekeeper(s);
							} if(sCol >2 && sRow > 2){
								if(theBoard[sRow][sCol].getState() * (-1)== theBoard[sRow-1][sCol-1].getState()
										&& theBoard[sRow][sCol].getState() * (-1) == theBoard[sRow-2][sCol-2].getState()
										&& theBoard[sRow][sCol].getState() == theBoard[sRow-3][sCol-3].getState()){
									theBoard[sRow-1][sCol-1].setState(PenteMain.EMPTY);
									theBoard[sRow-2][sCol-2].setState(PenteMain.EMPTY);
									scorekeeper(s);
								}
						}
						}
							if(sCol > 2 && sRow <= bWidthSquares -3){	
								if(theBoard[sRow][sCol].getState() * (-1)== theBoard[sRow+1][sCol-1].getState()
										&& theBoard[sRow][sCol].getState() * (-1) == theBoard[sRow+2][sCol-2].getState()
										&& theBoard[sRow][sCol].getState() == theBoard[sRow+3][sCol-3].getState()){
									theBoard[sRow+1][sCol-1].setState(PenteMain.EMPTY);
									theBoard[sRow+2][sCol-2].setState(PenteMain.EMPTY);
									scorekeeper(s);
								} else {
									
									
									
									
								if(sCol < bWidthSquares - 3 && sRow< bWidthSquares - 3){
									if(theBoard[sRow][sCol].getState() * (-1)== theBoard[sRow+1][sCol+1].getState()
											&& theBoard[sRow][sCol].getState() * (-1) == theBoard[sRow+2][sCol+2].getState()
											&& theBoard[sRow][sCol].getState() == theBoard[sRow+3][sCol+3].getState()){
										theBoard[sRow+1][sCol+1].setState(PenteMain.EMPTY);
										theBoard[sRow+2][sCol+2].setState(PenteMain.EMPTY);
										scorekeeper(s);
									}
									}
							}
						}
		}
				public void scorekeeper(Square s){
							repaint();
							int capper =s.getState();
							if(capper == PenteMain.DARK){
								BPL++;
								JOptionPane.showMessageDialog(null,"black score +1");
							}
							if(capper == PenteMain.LIGHT){
								WPL++;
								JOptionPane.showMessageDialog(null,"white score +1");
							}
							if(BPL == 5){
								JOptionPane.showMessageDialog(null,"black wins");
								System.exit(0);
							}
							if(WPL == 5){
								JOptionPane.showMessageDialog(null,"white wins");
								System.exit(0);
							}
							}
				
public void checkFor5Win(Square s){
	if(winY(s)>3|| winX(s)>3|| winD1(s)>3|| winD2(s)>3){
		repaint();
		String stateStr;
		if(s.getState() == PenteMain.LIGHT){
			stateStr = "White";
		}else{
			stateStr = "Black";
		}
		reset(stateStr);
		repaint();
	}
	
}
public int winY(Square s){
	int row = s.getRow();
	int column = s.getCol();
	boolean didWin = false;
	boolean contin = true;
	int i = 0;
	if(row > 3){
		int c2 = 0;
		while(contin == true && c2 < 4){
			if(theBoard[row-c2-1][column].getState() == theBoard[row-c2][column].getState()){
				i++;
				c2++;
			}else{
				contin = false;
			}
		}
	}
	contin = true;
	if(row < bWidthSquares - 4){
		int c = 0;
		while(contin == true && c < 4){
			if(theBoard[row+c+1][column].getState() == theBoard[row+c][column].getState()){
				i++;
				c++;
			}else{
				contin = false;
			}
		}
	}
	return i;
}
public int winX(Square s){
	int row = s.getRow();
	int column = s.getCol();
	boolean didWin = false;
	boolean contin = true;
	int i = 0;
	if(column > 3){
		int c2 = 0;
		while(contin == true && c2 < 4){
			if(theBoard[row][column-c2-1].getState() == theBoard[row][column-c2].getState()){
				i++;
				c2++;
			}else{
				contin = false;
			}
		}
	}
	contin = true;
	if(column < bWidthSquares - 4){
		int c = 0;
		while(contin == true && c < 4){
			if(theBoard[row][column+c+1].getState() == theBoard[row][column+c].getState()){
				i++;
				c++;
			}else{
				contin = false;
			}
		}
	}
	return i;
}
public int winD1(Square s){
	int row = s.getRow();
	int column = s.getCol();
	boolean didWin = false;
	boolean contin = true;
	int i = 0;
	if(row > 3 && column < bWidthSquares - 4){
		int c2 = 0;
		while(contin == true && c2 < 4){
			if(theBoard[row-c2-1][column+c2+1].getState() == theBoard[row-c2][column+c2].getState()){
				i++;
				c2++;
			}else{
				contin = false;
			}
		}
	}
	contin = true;
	if(row < bWidthSquares - 4 && column > 3){
		int c = 0;
		while(contin == true && c < 4){
			if(theBoard[row+c+1][column-c-1].getState() == theBoard[row+c][column-c].getState()){
				i++;
				c++;
			}else{
				contin = false;
			}
		}
	}
	 return i;
}
public int winD2(Square s){
	int row = s.getRow();
	int column = s.getCol();
	boolean didWin = false;
	boolean contin = true;
	int i = 0;
	if(row > 3 && column > 3){
		int c2 = 0;
		while(contin == true && c2 < 4){
			if(theBoard[row-c2-1][column-c2-1].getState() == theBoard[row-c2][column-c2].getState()){
				i++;
				c2++;
			}else{
				contin = false;
			}
		}
	}
	contin = true;
	if(row < bWidthSquares - 4 && column < bWidthSquares - 4){
		int c = 0;
		while(contin == true && c < 4){
			if(theBoard[row+c+1][column+c+1].getState() == theBoard[row+c][column+c].getState()){
				i++;
				c++;
			}else{
				contin = false;
			}
		}
	}
	return i;
}

public void reset(String stateStr){
	alert(stateStr + " Wins!");
	for(int row=0;row<bWidthSquares;row++){
		for(int column = 0; column<bWidthSquares; column++){
			theBoard[row][column].setState(PenteMain.EMPTY);
		}
	}
	whiteCaps = 0;
	blackCaps = 0;
	theBoard[(int)(bWidthSquares/2)][(int)(bWidthSquares/2)].setState(PenteMain.DARK);
	currentTurn = PenteMain.DARK;
}
public int getBoardWidthInSquares(){
	return bWidthSquares;
}
public Square [][] getActualGameBoard(){
	return theBoard;
}
public void alert(String m){
	JOptionPane.showMessageDialog(null, m);
}
}



		

