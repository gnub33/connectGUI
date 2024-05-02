package edu.wm.cs.cs301.connectn.view;

import javax.swing.JOptionPane; 

public class PlayAgain { //just returns value
		
	public static int showPlayAgain(String result) {
		
		int response = JOptionPane.showConfirmDialog
				(null, "Result is " +result, "Play Again?", JOptionPane.YES_NO_OPTION);
		//yes == 0
		//no == 1
		
		return response;
	}
}
