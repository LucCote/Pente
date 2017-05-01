import java.awt.Color;
import java.awt.Graphics;

public class Square {
private int xLoc, yLoc;
private int sWidth;
private Color boardSquareColor = Color.GRAY;
private Color crossHairColor = Color.BLACK;
//private Color squareseperationColor = Color.RED;
private int squareState = PenteMain.EMPTY;
private int mycol;
private int myrow;


public Square(int x,int y, int w, int row, int col) {
	xLoc = x;
	yLoc = y;
	sWidth = w;
	mycol = col;
	myrow = row;
	
}
	public void drawMe(Graphics g){
		g.setColor(boardSquareColor);
		g.fillRect(xLoc, yLoc, sWidth, sWidth);
		
		g.setColor(crossHairColor);
		g.drawLine(xLoc + (int)(sWidth/2), yLoc, xLoc + (int)(sWidth/2), yLoc +sWidth);
		g.drawLine(xLoc, yLoc + (int)(sWidth/2), xLoc + sWidth, yLoc + (int)(sWidth/2));
		
		//g.setColor(squareseperationColor);
		//g.drawLine(xLoc + (int)(sWidth), yLoc, xLoc + (int)(sWidth), yLoc +sWidth);
		///g.drawLine(xLoc, yLoc + (int)(sWidth), xLoc + sWidth, yLoc + (int)(sWidth));
		
		if(squareState == PenteMain.DARK){
			g.setColor(Color.BLACK);
			g.fillOval(xLoc+3, yLoc+3, sWidth-6,sWidth-6);
		}
		if(squareState == PenteMain.LIGHT){
			g.setColor(Color.WHITE);
			g.fillOval(xLoc+3, yLoc+3, sWidth-6,sWidth-6);
		}
	
	
	}
	public void setState(int newState){
		squareState = newState;
	}
	public int getState(){
		return squareState;
	}
	public int getRow(){
		return myrow;
	}
	public int getCol(){
		return mycol;
	}
	public boolean youClickedMe(int mouseX, int mouseY){
		boolean SquareClicked = false;
		if(mouseX >= xLoc && mouseX <= xLoc + sWidth && mouseY >= yLoc && mouseY <= sWidth + yLoc){
			SquareClicked = true;
		}
		return SquareClicked;
	}
	
}
