import java.util.ArrayList;

public class Filip2 {
	PenteGameBoard board;
	Square [][] boardArray;
	int stoneColor;
	int bwidthsq;
	int rowMove;
	int colMove;
	int lastMoveR;
	int lastMoveC;
	ArrayList<Square> black = new ArrayList<Square>();
	ArrayList<Square> white = new ArrayList<Square>();
	public Filip2(PenteGameBoard b, int color){
		board = b;
		stoneColor = color;
		bwidthsq = b.getWidthSq();
		boardArray = b.getBoard();
	}
	public Square NextMove(int lastMoveRow, int lastMoveCol){
		lastMoveR = lastMoveRow;
		lastMoveC = lastMoveCol;
		int newMoverow = (int)(Math.random()*19);
		int newMovecol = (int)(Math.random()*19);
		while(boardArray[newMoverow][newMovecol].getState() != PenteMain.EMPTY){
			//System.out.println(newMoverow + "\t" + newMovecol);
			newMoverow = (int)(Math.random()*19);
			newMovecol = (int)(Math.random()*19);
		}
		black.clear();
		white.clear();
		//System.out.println(black.size());
		for(int row=0;row<bwidthsq;row++){
			for(int column = 0; column<bwidthsq; column++){
				if(boardArray[row][column].getState() == PenteMain.BLACK){
					//System.out.println(boardArray[row][column].getRow() + "," + boardArray[row][column].getColumn());
					black.add(boardArray[row][column]);
				}else if(boardArray[row][column].getState() == PenteMain.WHITE){
					white.add(boardArray[row][column]);
				}
			}
		}
		System.out.println(black.size());
		return offense(3);
	
	}
	public Square findNextMove(int check){
		//System.out.println("hit");
		//System.out.println(board.winY(boardArray[lastMoveR][lastMoveC]));
		if(board.winY(boardArray[lastMoveR][lastMoveC]) == check){
			System.out.println("hit");
			int r = 1;
			while(boardArray[lastMoveR+r][lastMoveC].getState() == boardArray[lastMoveR][lastMoveC].getState()){
				r++;
			}
			if(boardArray[lastMoveR+r][lastMoveC].getState() == PenteMain.EMPTY){
				System.out.println("hit2");
				return boardArray[lastMoveR+r][lastMoveC];
			}else{
				r = 1;
				while(boardArray[lastMoveR-r][lastMoveC].getState() == boardArray[lastMoveR][lastMoveC].getState()){
					r++;
				}
				if(boardArray[lastMoveR-r][lastMoveC].getState() == PenteMain.EMPTY){
					System.out.println("hit3");
					return boardArray[lastMoveR-r][lastMoveC];
				}else{
					System.out.println("hit4");
					System.out.println(boardArray[lastMoveR-r][lastMoveC].getState() + "\n" + PenteMain.EMPTY);
					return randSq(check);
				}
			}
		}if(board.winX(boardArray[lastMoveR][lastMoveC]) == check){
			int r = 1;
			while(boardArray[lastMoveR][lastMoveC+r].getState() == boardArray[lastMoveR][lastMoveC].getState()){
				r++;
			}
			if(boardArray[lastMoveR][lastMoveC+r].getState() == PenteMain.EMPTY){
				return boardArray[lastMoveR][lastMoveC+r];
			}else{
				r = 1;
				while(boardArray[lastMoveR][lastMoveC-r].getState() == boardArray[lastMoveR][lastMoveC].getState()){
					r++;
				}
				if(boardArray[lastMoveR][lastMoveC-r].getState() == PenteMain.EMPTY){
					return boardArray[lastMoveR][lastMoveC-r];
				}else{
					return randSq(check);
				}
			}
		}if(board.winD2(boardArray[lastMoveR][lastMoveC]) == check){
			int r = 1;
			while(boardArray[lastMoveR+r][lastMoveC+r].getState() == boardArray[lastMoveR][lastMoveC].getState()){
				r++;
			}
			if(boardArray[lastMoveR+r][lastMoveC+r].getState() == PenteMain.EMPTY){
				return boardArray[lastMoveR+r][lastMoveC+r];
			}else{
				r = 1;
				while(boardArray[lastMoveR-r][lastMoveC-r].getState() == boardArray[lastMoveR][lastMoveC].getState()){
					r++;
				}
				if(boardArray[lastMoveR-r][lastMoveC-r].getState() == PenteMain.EMPTY){
					return boardArray[lastMoveR-r][lastMoveC-r];
				}else{
					return randSq(check);
				}
			}
		}if(board.winD1(boardArray[lastMoveR][lastMoveC]) == check){
			int r = 1;
			while(boardArray[lastMoveR-r][lastMoveC+r].getState() == boardArray[lastMoveR][lastMoveC].getState()){
				r++;
			}
			if(boardArray[lastMoveR-r][lastMoveC+r].getState() == PenteMain.EMPTY){
				return boardArray[lastMoveR-r][lastMoveC+r];
			}else{
				r = 1;
				while(boardArray[lastMoveR+r][lastMoveC-r].getState() == boardArray[lastMoveR][lastMoveC].getState()){
					r++;
				}
				if(boardArray[lastMoveR+r][lastMoveC-r].getState() == PenteMain.EMPTY){
					return boardArray[lastMoveR+r][lastMoveC-r];
				}else{
					return randSq(check);
				}
			}
		}
		else{
			return randSq(check);
		}
		
		
	}
	public Square capturer(){
		return findNextMove(2);
	}
	public Square randSq(int c){
		return offense(c-1);
		
		
	}
	public Square defense(int c){
		return findNextMove(c);
	}
	public Square offense(int chek){
		if(black.size() >= 1){
			System.out.println(black.size() + "\n");
			//black = sortList(black);
		}else{
			System.out.println(black.size() + "\n");
			int newMoverow = (int)(Math.random()*19);
			int newMovecol = (int)(Math.random()*19);
			while(boardArray[newMoverow][newMovecol].getState() != PenteMain.EMPTY){
				//System.out.println(newMoverow + "\t" + newMovecol);
				newMoverow = (int)(Math.random()*19);
				newMovecol = (int)(Math.random()*19);
			}
			return boardArray[newMoverow][newMovecol];
		}
		
		int best = getBestLine(black.get(0));
		int bi = 0;
		for(int count = 1; count < black.size(); count++){
			if(getBestLine(black.get(count)) > best){
				bi = count;
				best = getBestLine(black.get(count));
			}
		}
		if(best == chek){
			if(board.winY(black.get(bi)) == getBestLine(black.get(bi))){
				return placeY(black.get(bi), bi, chek);		
			}else if(board.winD1(black.get(bi)) == getBestLine(black.get(bi))){
				return placeD1(black.get(bi), bi, chek);
			}else if(board.winD1(black.get(bi)) == getBestLine(black.get(bi))){
				return placeD1(black.get(bi), bi, chek);
			}else{
				return placeX(black.get(bi), bi, chek);
			}
		}else{
			return defense(chek);
		}
		
	}
	public int getBestLine(Square s){
		int bestNum = board.winY(s);
		if(board.winX(s) > bestNum){
			bestNum = board.winX(s);
		}if(board.winD1(s) > bestNum){
			bestNum = board.winD1(s);
		}if(board.winD2(s) > bestNum){
			bestNum = board.winD2(s);
		}
		return bestNum;
	}
	public Square placeY(Square s, int bi, int c){
		Square toPlace;
		int r = 1;
		while(boardArray[s.getRow()+r][s.getColumn()].getState() == s.getState()){
			r++;
		}
		if(boardArray[s.getRow()+r][s.getColumn()].getState() == PenteMain.EMPTY){
			toPlace = boardArray[s.getRow()+r][s.getColumn()];
		}else{
			r = 1;
			while(boardArray[s.getRow()-r][s.getColumn()].getState() == s.getState()){
				r++;
			}
			if(boardArray[s.getRow()-r][s.getColumn()].getState() == PenteMain.EMPTY){
				toPlace = boardArray[s.getRow()-r][s.getColumn()];
			}else{
				black.remove(bi);
				toPlace = offense(c);
			}
		}
		return toPlace;
	}
	public Square placeX(Square s, int bi, int c){
		Square toPlace;
		int r = 1;
		while(boardArray[s.getRow()][s.getColumn()+r].getState() == s.getState()){
			System.out.print(r);
			r++;
		}
		if(boardArray[s.getRow()][s.getColumn()+r].getState() == PenteMain.EMPTY){
			toPlace = boardArray[s.getRow()][s.getColumn()+r];
		}else{
			r = 1;
			while(boardArray[s.getRow()][s.getColumn()+r].getState() == s.getState()){
				r++;
			}
			if(boardArray[s.getRow()][s.getColumn()-r].getState() == PenteMain.EMPTY){
				toPlace = boardArray[s.getRow()][s.getColumn()-r];
			}else{
				black.remove(bi);
				toPlace = offense(c);
				
			}
		}
		return toPlace;
	}
	public Square placeD1(Square s, int bi, int c){
		Square toPlace;
		int r = 1;
		while(boardArray[s.getRow()-r][s.getColumn()+r].getState() == s.getState()){
			r++;
		}
		if(boardArray[s.getRow()-r][s.getColumn()+r].getState() == PenteMain.EMPTY){
			toPlace = boardArray[s.getRow()-r][s.getColumn()+r];
		}else{
			r = 1;
			while(boardArray[s.getRow()+r][s.getColumn()-r].getState() == s.getState()){
				r++;
			}
			if(boardArray[s.getRow()+r][s.getColumn()-r].getState() == PenteMain.EMPTY){
				toPlace = boardArray[s.getRow()+r][s.getColumn()-r];
			}else{
				black.remove(bi);
				toPlace = offense(c);
				
			}
		}
		return toPlace;
	}
	public Square placeD2(Square s, int bi, int c){
		Square toPlace;
		int r = 1;
		while(boardArray[s.getRow()+r][s.getColumn()+r].getState() == s.getState()){
			r++;
		}
		if(boardArray[s.getRow()+r][s.getColumn()+r].getState() == PenteMain.EMPTY){
			toPlace = boardArray[s.getRow()+r][s.getColumn()+r];
		}else{
			r = 1;
			while(boardArray[s.getRow()-r][s.getColumn()-r].getState() == s.getState()){
				r++;
			}
			if(boardArray[s.getRow()-r][s.getColumn()-r].getState() == PenteMain.EMPTY){
				toPlace = boardArray[s.getRow()-r][s.getColumn()-r];
			}else{
				black.remove(bi);
				toPlace = offense(c);
				
			}
		}
		return toPlace;
	}
	public ArrayList<Square> sortList(ArrayList<Square> toSort){
		toSort = sort(toSort);
		return toSort;
	}
	
	public ArrayList<Square> sort(ArrayList<Square> a){
		double n = a.size();
		if ( n <= 1 ){
			return a;
		}
		ArrayList<Square> done;
		double hn = n/2;
		//System.out.println(hn);
		int ln = (int) (hn);
		int lln = (int) Math.floor(hn);
		
		ArrayList<Square> l1 = new ArrayList<Square>();
		ArrayList<Square> l2 = new ArrayList<Square>();
		
		for(int i = 0; i<ln; i++){
			l1.add(a.get(i));
		}
		for(int i = 0; i<lln; i++){
			l2.add(a.get(ln+i));
		}
		l1 = sort(l1);
	    l2 = sort(l2);
	    done = merge(l1, l2);
	    return done;
	}
	
	private ArrayList<Square> merge(ArrayList<Square> list1, ArrayList<Square> list2){
	int length = list1.size() + list2.size();
	ArrayList<Square> newList3 = new ArrayList<Square>();
	int list1Index = 0; 
	int list2Index = 0;
	for(int i=0; i < length; ++i){
		if (list1Index==list1.size()){
			newList3.add(list2.get(list2Index));
			++list2Index;
		}else if (list2Index==list2.size()){
			newList3.add(list1.get(list1Index));
			++list1Index;
		}else if(getBestLine(list1.get(list1Index))>getBestLine(list2.get(list2Index))){
			newList3.add(list2.get(list2Index));
			++list2Index;
		}else {
			newList3.add(list1.get(list1Index));
			++list1Index;
		}
	}
	return newList3;

}
	
	
}
