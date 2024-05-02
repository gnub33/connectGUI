package edu.wm.cs.cs301.connectn.view;

import javax.swing.JOptionPane; 

public class Instructions { 
		
	public Instructions() {
		
	}
	public static void show() {
		JOptionPane.showMessageDialog
		(null, "Welcome to Connect 4!\n"
				+ "The game is played on a vertical board.\n"
				+ "The player is given blue tokens and the "
				+ "computer will use red tokens.\n"
				+ "The game has three modes: Small, Medium and Large.\n"
				+ "In order to win, you the player must have"
				+ "a certain number of your tokens connect\n"
				+ "either vertically, horizontally or diagnoally.\n"
				+ "(3) on Small, (4) on Medium, (5) on Large.", "Instructions", JOptionPane.INFORMATION_MESSAGE);
	}
	

}
