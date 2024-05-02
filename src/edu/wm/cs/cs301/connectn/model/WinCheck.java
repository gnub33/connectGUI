package edu.wm.cs.cs301.connectn.model;

import java.awt.Color;

public class WinCheck {
	
	static Color BLUE = new Color(40, 40, 205);

	public static int check(int rows, int cols, Color[][] grid, Color currentClr ) {
		int win = 0;
//    	System.out.println("");
//    	System.out.println("Col len: " + cols);
//    	System.out.println("Row len: " + rows);
//    	System.out.println("");
    	
    	//vertical
    	for (int row = rows-1; row > 2; row--) {
			for (int col = 0; col < cols; col++) {
				if (grid[row][col].equals(currentClr) 
						&& grid[row-1][col].equals(currentClr)
						&& grid[row-2][col].equals(currentClr)  
						&& grid[row-3][col].equals(currentClr)) 
				{
					if(grid[row][col].equals(BLUE)) {  //you win
						win = 1;
					}
					else  //loose
						win =  2;
				}
			}
		}
    	
    	//left to right
    	for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols-4; col++) {
				if ((grid[row][col].equals(currentClr)) 
						&& (grid[row][col+1].equals(currentClr))
						&& (grid[row][col+2].equals(currentClr))  
						&& (grid[row][col+3].equals(currentClr))) 
				{
					if(grid[row][col].equals(BLUE)) {
						win = 1;
					}
					else
						win =  2;
				}
			}
		}
    	//right to left
    	for (int row = 0; row < rows; row++) {
			for (int col = cols-1; col > 2; col--) {
				if ((grid[row][col].equals(currentClr)) 
						&& (grid[row][col-1].equals(currentClr))
						&& (grid[row][col-2].equals(currentClr))  
						&& (grid[row][col-3].equals(currentClr))) 
				{
					if(grid[row][col].equals(BLUE)) {
						win = 1;
					}
					else
						win =  2;
				}
			}
		}
    	
    	//downstairs
    	for (int row = 0; row < rows - 3; row++) {   //row 1 to 3
			for (int col = 0; col < cols - 3; col++) {  //col 1 to 4
				if ((grid[row][col].equals(currentClr)) 
						&& (grid[row+1][col+1].equals(currentClr))
						&& (grid[row+2][col+2].equals(currentClr))  
						&& (grid[row+3][col+3].equals(currentClr))) 
				{
					if(grid[row][col].equals(BLUE)) {
						win = 1;
					}
					else
						win =  2;
				}
			}
			
		}
    	
    	//upstairs
    	for (int row = rows-1; row > 2; row--) {
			for (int col = 0; col < cols - 3; col++) {
				if ((grid[row][col].equals(currentClr)) 
						&& (grid[row-1][col+1].equals(currentClr))
						&& (grid[row-2][col+2].equals(currentClr))  
						&& (grid[row-3][col+3].equals(currentClr))) 
				{
					if(grid[row][col].equals(BLUE)) {
						win = 1;
					}
					else
						win =  2;
				}
			}
		}
    	return win;
	}
}
