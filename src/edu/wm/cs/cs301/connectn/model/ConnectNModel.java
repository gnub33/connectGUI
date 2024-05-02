package edu.wm.cs.cs301.connectn.model;

import edu.wm.cs.cs301.connectn.PlayAgain;

import edu.wm.cs.cs301.connectn.BoardFrame;

public class ConnectNModel {

	public ConnectNModel() {
		//constructor
	}
	
	public int showPlayAgain(String result) {
		//trigger play again dialog
		return PlayAgain.showPlayAgain(result);
	}
}
