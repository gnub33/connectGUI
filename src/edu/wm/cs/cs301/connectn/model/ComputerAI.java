package edu.wm.cs.cs301.connectn.model;

/**
 * a class that implements the Player interface and
represents computer-controlled player. Should have an automated algorithm for
choosing the next move. Requires no user input.
 * 
 * 
 * You may change this file as much as you like, including:
 * 	- adding constructor parameters
 *  - overloading the constructor
 *  - adding instance variables
 *  - adding methods (public or private)
 *  
 * HOWEVER:
 *  - You may not move the file to a different package
 * 	- You may not change the name of the file or the class
 *  - You must implement the Player interface
 */
import java.lang.Math;

import java.awt.Color;

public class ComputerAI implements Player {
	
	static Color WHITE = new Color(255, 255, 255);
	Color BLUE = new Color(40, 40, 205);

	
	private final int rows;  		//number of rows
	private final int cols;  		//number of colums
	private final Color[][] grid;
	private final Color currentClr;

	public ComputerAI(int rows, int cols, Color[][] grid, Color currentClr ) {
		this.rows = rows;
		this.cols = cols;
		this.grid = grid;
		this.currentClr = currentClr;
	}
	
	int max = 0;
	int min = 1;
	int height = 0;
	
	//private int x = 0; //instance variable for column choice -- maybe add to constructor
	
	private int colChoice;
	
	int randomCol() { 
		//random selects colum x
		// check rows are full
		//if col not full place token
		//else select new row
		// game should auto end if grid full
		
		//ENSURES MOVE IS ALWAYS VALID
		int tempX = -1;
		int randInt = (int)(Math.random()*7);
		while (tempX == -1) {										//keep looping until valid x is found
			for (int row = rows-1; row >= 0; row--) {  				//start at bottom, go to top row (0)
				if (grid[row][randInt].equals(WHITE)) {
					tempX = randInt;
					return tempX;									//stops looking as soon as white is found
				} else {
					randInt = (int)(Math.random()*7);				//reset randInt
				}
			}
		}
		return 0;
	}
	
	public int getColChoice() {
		return colChoice;
	}

	@Override
	public void takeTurn() {
	}
	
	public int colSelect() {
		int x = 0;
		//runs winMove() first to check for possible win
		//if none select vacant adjacent position
		//if none default to random

		if (winStrat() != -1) {
			x = winStrat();
		} else if (bestMove() != -1) {
			x = bestMove();
		} else {
			x = randomCol();					//should return valid non-empty column number
		}
		return x;
	}
	
	public int winStrat() {
		//should return column of winning position
		int pos = -1;
		//checks board for n-1 win tokens and return column of the nth if available
		
		//vertical
    	for (int row = rows-1; row > 2; row--) {
			for (int col = 0; col < cols; col++) {
				if (grid[row][col].equals(currentClr) 
						&& grid[row-1][col].equals(currentClr)
						&& grid[row-2][col].equals(currentClr)) 
				{
					if(grid[row][col-3].equals(WHITE)) {  //the winning position is vacant
						pos = row;
						return pos;						  //end search bc position is found
					}
				}
			}
		}
    	
    	//left to right
    	for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols-4; col++) {
				if (grid[row][col].equals(currentClr) 
						&& grid[row][col+1].equals(currentClr)
						&& grid[row][col+2].equals(currentClr)) 
				{
					if(grid[row][col+3].equals(WHITE)) {
						pos = row;
						return pos;	
					}
				}
			}
		}
    	//right to left
    	for (int row = 0; row < rows; row++) {
			for (int col = cols-1; col > 2; col--) {
				if ((grid[row][col].equals(currentClr)) 
						&& (grid[row][col-1].equals(currentClr))
						&& (grid[row][col-2].equals(currentClr))) 
				{
					if(grid[row][col-3].equals(WHITE)) {
						pos = row;
						return pos;
					}
				}
			}
		}
    	
    	//downstairs
    	for (int row = 0; row < rows - 3; row++) {   //row 1 to 3
			for (int col = 0; col < cols - 3; col++) {  //col 1 to 4
				if ((grid[row][col].equals(currentClr)) 
						&& (grid[row+1][col+1].equals(currentClr))
						&& (grid[row+2][col+2].equals(currentClr))) 
				{
					if(grid[row+3][col+3].equals(WHITE)) {
						pos = row;
						return pos;
					}
				}
			}
			
		}
    	
    	//upstairs
    	for (int row = rows-1; row > 2; row--) {
			for (int col = 0; col < cols - 3; col++) {
				if ((grid[row][col].equals(currentClr)) 
						&& (grid[row-1][col+1].equals(currentClr))
						&& (grid[row-2][col+2].equals(currentClr))) 
				{
					if(grid[row-3][col+3].equals(WHITE)) {
						pos = row;
						return pos;
					}
				}
			}
		}
		return pos;    //theoretically should never be reached?
	}
	
	public int bestMove() {
		int pos = -1;
		return pos;
	}
	
	public int getX() {
		return 1;
	}
	
	public int getY() {
		return 1;
	}

}


