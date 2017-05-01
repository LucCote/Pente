import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Filip {
	
	PenteGameBoard myBoard;	
	int myStoneColor;
	int boardWidthSquares;
	Square[][] theGameBoard;
	int LMR;
	int LMC;
	
	ArrayList<Square> black = new ArrayList<Square>();
	
	
public Filip(PenteGameBoard b, int stoneColor){
	myBoard = b;
	myStoneColor = stoneColor;
	boardWidthSquares = b.getBoardWidthInSquares();
	theGameBoard = b.getActualGameBoard();
	JOptionPane.showMessageDialog(null, "Hi, Filip here. Ready to play!");
}

public Square doComputerMove( int lastMoveRow, int lastMoveCol){
	LMR = lastMoveRow;
	LMC = lastMoveCol;
	int newMoveRow, newMoveCol;
	do{
	newMoveRow = (int)(Math.random()* boardWidthSquares);
	newMoveCol = (int)(Math.random()* boardWidthSquares);
	}while(theGameBoard[newMoveRow][newMoveCol].getState() != PenteMain.EMPTY);
	black.clear();
	for(int row = 0; row< boardWidthSquares; ++row){
		for(int col = 0; col< boardWidthSquares; ++col){
			if(theGameBoard[row][col].getState() == myStoneColor){
				black.add(theGameBoard[row][col]);
			}
		}
	}
	return offense(3);
}

public Square filsNextMove(int check){
	if(myBoard.winY(theGameBoard[LMR][LMC])>=check){
		int rowI = 1;
		while(theGameBoard[LMR+rowI][LMC].getState()== theGameBoard[LMR][LMC].getState()){
			rowI++;
		}
		if(theGameBoard[LMR+rowI][LMC].getState()==PenteMain.EMPTY){
			return theGameBoard[LMR+rowI][LMC];
		}else{
			rowI=1;
			while(theGameBoard[LMR-rowI][LMC].getState()== theGameBoard[LMR][LMC].getState()){
				rowI++;
			}
			if(theGameBoard[LMR-rowI][LMC].getState()==PenteMain.EMPTY){
				return theGameBoard[LMR-rowI][LMC];
			}else{
				return randSq();
			}
	}
		
	
}
	if(myBoard.winX(theGameBoard[LMR][LMC])>=check){
		int colI = 1;
		while(theGameBoard[LMR][LMC+colI].getState()== theGameBoard[LMR][LMC].getState()){
			colI++;
		}
		if(theGameBoard[LMR][LMC+colI].getState()==PenteMain.EMPTY){
			return theGameBoard[LMR][LMC+colI];
		}else{
			colI=1;
			while(theGameBoard[LMR][LMC-colI].getState()== theGameBoard[LMR][LMC].getState()){
				colI++;
			}
			if(theGameBoard[LMR][LMC-colI].getState()==PenteMain.EMPTY){
				return theGameBoard[LMR][LMC-colI];
			}else{
				return randSq();
			}
	}
	}
	if(myBoard.winD1(theGameBoard[LMR][LMC])>=check){
		int rowI = 1;
		while(theGameBoard[LMR-rowI][LMC+rowI].getState()== theGameBoard[LMR][LMC].getState()){
			rowI++;
		}
		if(theGameBoard[LMR-rowI][LMC+rowI].getState()==PenteMain.EMPTY){
			return theGameBoard[LMR-rowI][LMC+rowI];
		}else{
			rowI=1;
			while(theGameBoard[LMR+rowI][LMC-rowI].getState()== theGameBoard[LMR][LMC].getState()){
				rowI++;
			}
			if(theGameBoard[LMR+rowI][LMC-rowI].getState()==PenteMain.EMPTY){
				return theGameBoard[LMR+rowI][LMC-rowI];
			}else{
				return randSq();
			}
	}
		
	
}
	if(myBoard.winD2(theGameBoard[LMR][LMC])>=check){
		int colI = 1;
		while(theGameBoard[LMR+colI][LMC+colI].getState()== theGameBoard[LMR][LMC].getState()){
			colI++;
		}
		if(theGameBoard[LMR+colI][LMC+colI].getState()==PenteMain.EMPTY){
			return theGameBoard[LMR+colI][LMC+colI];
		}else{
			colI=1;
			while(theGameBoard[LMR-colI][LMC-colI].getState()== theGameBoard[LMR][LMC].getState()){
				colI++;
			}
			if(theGameBoard[LMR-colI][LMC-colI].getState()==PenteMain.EMPTY){
				return theGameBoard[LMR-colI][LMC-colI];
			}else{
				return randSq();
			}
	}
		
	
}
	else{
	return offense(check -1);
}

}

public Square offense(int chek){
		if(black.size() >= 1){
			if(chek == 1 ){
				return filsNextMove(1);
			}
			//System.out.println(black.size() + "\n");
			//black = sortList(black);
		}else{
			//System.out.println(black.size() + "\n");
			int newMoverow = (int)(Math.random()*19);
			int newMovecol = (int)(Math.random()*19);
			while(theGameBoard[newMoverow][newMovecol].getState() != PenteMain.EMPTY){
				//System.out.println(newMoverow + "\t" + newMovecol);
				newMoverow = (int)(Math.random()*19);
				newMovecol = (int)(Math.random()*19);
			}
			return theGameBoard[newMoverow][newMovecol];
		}
		
		int best = getBestLine(black.get(0));
		int bi = 0;
		for(int count = 1; count < black.size(); count++){
			if(getBestLine(black.get(count)) > best){
				bi = count;
				best = getBestLine(black.get(count));
			}
		}
		if(best >= chek){
			if(myBoard.winY(black.get(bi))== best &&myBoard.winX(black.get(bi))==best&&myBoard.winD1(black.get(bi))==best&&myBoard.winD2(black.get(bi))==best){
				int chose = (int)(Math.random()*4);
				//System.out.println(chose);
				//System.out.println("chose");
				if (chose==0){
					//System.out.println("1");
					return placeY(black.get(bi), bi, chek);
					
				}else if(chose == 1){
					//System.out.println("2");
					return placeX(black.get(bi), bi, chek);
					
				}else if(chose==2){
					//System.out.println("3");
					return placeD1(black.get(bi), bi, chek);
				
				}else if(chose == 3){
					//System.out.println("4");
						return placeD2(black.get(bi), bi, chek);
						
					}
				}
		else if(myBoard.winY(black.get(bi)) == getBestLine(black.get(bi))){
				return placeY(black.get(bi), bi, chek);		
			}if(myBoard.winX(black.get(bi)) == getBestLine(black.get(bi))){
				return placeX(black.get(bi), bi, chek);
			}if(myBoard.winD1(black.get(bi)) == getBestLine(black.get(bi))){
				return placeD1(black.get(bi), bi, chek);
			}		
				return placeD2(black.get(bi), bi, chek);
			
		}
		return filsNextMove(chek);
		
		
	}

	public int getBestLine(Square s){
		int bestNum = myBoard.winY(s);
		if(myBoard.winX(s) > bestNum){
			bestNum = myBoard.winX(s);
		}if(myBoard.winD1(s) > bestNum){
			bestNum = myBoard.winD1(s);
		}if(myBoard.winD2(s) > bestNum){
			bestNum = myBoard.winD2(s);
		}

		return bestNum;
	}
	public Square placeY(Square s, int bi, int c){
		System.out.println("y");
		Square toPlace;
		int r = 0;
		boolean contin = true;
		while(theGameBoard[s.getRow()+r][s.getCol()].getState() == s.getState() && contin == true){
			if(s.getRow()+r +1< boardWidthSquares){
				r++;
			}else{
				contin = false;
			}
		}
		if(theGameBoard[s.getRow()+r][s.getCol()].getState() == PenteMain.EMPTY){
			toPlace = theGameBoard[s.getRow()+r][s.getCol()];
		}else{
			contin = true;
			r = 0;
			while(theGameBoard[s.getRow()-r][s.getCol()].getState() == s.getState() && contin == true){
				if(s.getRow()-r -1 >=0){
					r++;
				}else{
					contin = false;
				}
			}
			if(theGameBoard[s.getRow()-r][s.getCol()].getState() == PenteMain.EMPTY){
				toPlace = theGameBoard[s.getRow()-r][s.getCol()];
			}else{
				black.remove(bi);
				toPlace = offense(c);
			}
		}
		return toPlace;
	}
	public Square placeX(Square s, int bi, int c){
		System.out.println("x");
		Square toPlace;
		int r = 0;
		boolean contin = true;
		while(theGameBoard[s.getRow()][s.getCol()+r].getState() == s.getState() && contin == true){
			if(s.getCol()+r+1 < boardWidthSquares){
			r++;
			}else {
				contin = false;
			}
		}
		if(theGameBoard[s.getRow()][s.getCol()+r].getState() == PenteMain.EMPTY){
			toPlace = theGameBoard[s.getRow()][s.getCol()+r];
		}else{
			r = 0;
			contin = true;
			while(theGameBoard[s.getRow()][s.getCol()-r].getState() == s.getState() && contin == true){
				if(s.getCol()-r-1 >= 0){
				r++;
				}else{
					contin = false;
				}
			}
			if(theGameBoard[s.getRow()][s.getCol()-r].getState() == PenteMain.EMPTY){
				toPlace = theGameBoard[s.getRow()][s.getCol()-r];
			}else{
				black.remove(bi);
				toPlace = offense(c);
				
			}
		}
		return toPlace;
	}
	public Square placeD1(Square s, int bi, int c){
		System.out.println("D1");
		Square toPlace;
		int r = 0;
		boolean contin = true;
		while(theGameBoard[s.getRow()-r][s.getCol()+r].getState() == s.getState() && contin == true){
		if(s.getRow()-r -1 >= 0 && s.getCol()+r + 1 < boardWidthSquares){
				r++;
		}else{
			contin = false;
		}
		}
		contin = true;
		if(theGameBoard[s.getRow()-r][s.getCol()+r].getState() == PenteMain.EMPTY){
			toPlace = theGameBoard[s.getRow()-r][s.getCol()+r];
		}else{
			r = 0;
			while(theGameBoard[s.getRow()+r][s.getCol()-r].getState() == s.getState() && contin == true){
				if(s.getRow() +r +1 <boardWidthSquares  && s.getCol() -r -1 >=0 ){
					r++;
				}else {
					contin = false;
				}
			}
			if(theGameBoard[s.getRow()+r][s.getCol()-r].getState() == PenteMain.EMPTY){
				toPlace = theGameBoard[s.getRow()+r][s.getCol()-r];
			}else{
					black.remove(bi);
					toPlace = offense(c);
				
			}
		}
		return toPlace;
	}
	public Square placeD2(Square s, int bi, int c){
		System.out.println("D2");
		Square toPlace;
		int r = 0;
		boolean contin = true;
		while(theGameBoard[s.getRow()+r][s.getCol()+r].getState() == s.getState()&& contin == true){
			if(s.getRow()+r+1 < boardWidthSquares && s.getCol()+r+1< boardWidthSquares ){
			r++;
			}else{
				contin = false;
			}
		}
		if(theGameBoard[s.getRow()+r][s.getCol()+r].getState() == PenteMain.EMPTY){
			toPlace = theGameBoard[s.getRow()+r][s.getCol()+r];
		}else{
			r = 0;
			contin = true;
			while(theGameBoard[s.getRow()-r][s.getCol()-r].getState() == s.getState()&& contin == true){
				if(s.getRow()-r-1 >=0 && s.getCol()-r-1 >= 0){
				r++;
				}else{
					contin = false;
				}
			}
			if(theGameBoard[s.getRow()-r][s.getCol()-r].getState() == PenteMain.EMPTY){
				toPlace = theGameBoard[s.getRow()-r][s.getCol()-r];
			}else{
				black.remove(bi);
				toPlace = offense(c);
				
			}
		}
		return toPlace;
	}


public Square randSq(){
	int newMoveRow, newMoveCol;
	do{
	newMoveRow = (int)(Math.random()* boardWidthSquares);
	newMoveCol = (int)(Math.random()* boardWidthSquares);
	}while(theGameBoard[newMoveRow][newMoveCol].getState() != PenteMain.EMPTY);

	return theGameBoard[newMoveRow][newMoveCol];
	
}
}

