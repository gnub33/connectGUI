package edu.wm.cs.cs301.connectn.view;

import javax.swing.JPanel;
import javax.swing.WindowConstants;

import connectV2.connect;
import connectV2.model.ComputerAI;
import connectV2.model.ConnectNModel;
import connectV2.model.WinCheck;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import connectV2.model.ComputerAI;

public  class DrawGrid extends JPanel  implements MouseListener {
	
	private static final long serialVersionUID = 1L;
	
	private final BoardFrame view;
	
	private final ConnectNModel model;
	
	Color WHITE = new Color(255, 255, 255);
	Color RED = new Color(210, 50, 15);
	Color BLUE = new Color(40, 40, 205);
	
	Color currentClr = BLUE;
	
    int startX = 10;
    int startY = 10;
    int cellWidth = 60;
    int turn = 1; //human = even; computer = odd
    int rows = 6;
    int cols = 7;
    int win = 0;
    int active = 1;  // controls mouse click action

    Color[][] grid = new Color[rows][cols];
    
	ComputerAI com = new ComputerAI(rows, cols, grid, BLUE);

    public DrawGrid(BoardFrame view, ConnectNModel model, int width) {
    	this.model = model;
    	this.view = view;
//    	this.rowsCount = rows;
//    	this.colsCount = cols;
        //setSize(dimension);
        //setPreferredSize(dimension);
        addMouseListener(this);
        //1. initialize array here
        int x = 0;
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
            	grid[row][col] =  new Color(255, 255, 255); //white by default
            }
        }
    }
    
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        Dimension d = getSize();
        g2.setColor(new Color(125, 125, 125));  // bg color
        g2.fillRect(0,0,d.width,d.height);
        startX = 0;
        startY = 40;

        //2) draw grid here
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
              g2.setColor(grid[row][col]);  //default white
              g2.fillOval(startX, startY, cellWidth, cellWidth);  //start top left corner; width is 40
              startX += cellWidth; //initall on top of each other, this increments x coor
            }
            startX = 0; // resets after inner loop
            startY += cellWidth;
        }

        g2.setColor(new Color(255, 255, 255));
//        whoseTurn(g2);
        g2.drawString("MODE: MEDIUM", 500, 60); //mode should be IN variable
        g2.drawString("Turn #" + turn, 500, 80);  //Counts the number of turn
       
    }

    public void mousePressed(MouseEvent e) {
    	isFull(); 				//before button does anything should end game if board full
    	if (active == 1) { 		//mouse event only occurs if game is running
	    	try {
	            int x = e.getX();
	            int y = e.getY();
	            
	           //when click occurs, tells which row/col we are in
	            int xPos = x/cellWidth; 
	            int yPos = isVacant(xPos);
	            
	            if(yPos<0) {
	            	//System.out.println("Column is Full");
	            } else {
	            	//human turn
	            	currentClr = BLUE;
	            	grid[yPos][xPos] = new Color(40, 40, 205); //player blue
	            	repaint();
	            	checkWin();
	            	//System.out.println("yPos: " + yPos +" xPos: " +xPos);
	            	
	            	//comp turn
	            	int colChoice = com.colSelect(); //random 0 to 6 == 7 columns
	            	currentClr = RED;
	            	do { //run once
	            		xPos = colChoice;
	            		yPos = isVacant(xPos);
	            		
	            	}
	            	while (yPos < 0); //keep looping until y is positive
	            	
	            	grid[yPos][xPos] = RED; //computer red
	            	turn++;
	            	repaint();
	            	checkWin();
//	            	System.out.println("PA: "+active);
	            	checkGameOver();
	            }
	
	    	} catch(ArrayIndexOutOfBoundsException error) {
	    		//System.out.println("Column is Full "); //SHOULD OUTPUt to GUI if time permits
	    	}
    	}
    	//checkGameOver();
    }
    
    public void checkGameOver() {
    	int pa = 3;   		//placeholder
    	if(isActive()==0) {  //no more clicking
    		String one = "You Win";
    		String two = "You Loose";
    		String three = "Board Full: Tie Game";
    		
    		String result = " ";
//    		System.out.println("win is:: " + win); //saying its zero ??
    		
    		switch(win) {
    		case 1:
    			result = one;
    			break;
    		case 2:
    			result = two;
    			break;
    		case 3: 
    			result = three;
    			break;
    		default:
    			break;
    		}
    		
    		pa = model.showPlayAgain(result); //pass reason for game over
    		if(pa == 0) {  //WIN
    			active = 1;
    			//view.initialize();
    			view.destroy();
    			connect c = new connect();
    			c.run();
    		} else if (pa == 1) { //LOOSE
    			view.shutdown();
    		}
    	}
    }
    
    public void checkWin() {  //just prints turn to GUI, NOT needed
    	
    	win = WinCheck.check(rows, cols, grid, currentClr);
    	
    	switch(win) {
    	case 1:
    		active = 0;
//    		System.out.println("YOU WIN");
    		break;
    	case 2:
    		active = 0;
//    		System.out.println("YOU LOOSE");
    		break;
    	case 0:
    		//Continue
    		//no winner found
    		break;
    	}
    	
    	checkGameOver();
    }
    
    public void isFull() {
    	
    	int full = 0;
    	
    	for (int row = 0; row < 1; row++) { //only needs top row
            for (int col = 0; col < grid[0].length; col++) {
            	if(!(grid[row][col].equals(WHITE))) { //meaning its occupied
            		full ++;
            	}
            }
        }
    	//System.out.println("top row full fullness: " +full + "/7"); //
    	if(full == 7) {
    		active = 0;
    		win = 3;
//    		System.out.println("BOARD is Full"); 
    	}
    }
    
    public int isActive() {
    	return active;
    }
    
    public void resetTurnCount() {
    	turn = 1;
    }
    
    public void defaultClr() {
    	currentClr = BLUE;
    }
    
    public int isVacant(int xPos) {
    	int yPos = rows-1;  //local variable; rows-1 == bottom row
    	while(!(grid[yPos][xPos].equals(WHITE) || yPos < 0)) {  //while position is NOT white OR column is Not full (-1)
    		yPos--;
    	}
    	return yPos;  //if -1; column if ull and invalid
    	
    }

	@Override
	public void mouseClicked(MouseEvent e) {
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
    
}