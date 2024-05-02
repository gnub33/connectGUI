package edu.wm.cs.cs301.connectn.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;

import edu.wm.cs.cs301.connectn.model.AppColors;
import edu.wm.cs.cs301.connectn.model.ConnectModel;
import edu.wm.cs.cs301.connectn.model.ConnectNResponse;
import edu.wm.cs.cs301.connectn.view.LeaderboardDialog;
import edu.wm.cs.cs301.connectn.view.BoardView;

public class KeyboardButtonAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	
	private final BoardView view;
	
	private final ConnectModel model;
		
	public KeyboardButtonAction(BoardView view, ConnectModel model) {
		this.view = view;
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent event) { //update to occur whenever a new frame is created -- occure not just when "Enter" is pressed
		JButton button = (JButton) event.getSource();
		String text = button.getActionCommand();
		switch (text) {
		case "Enter":
			if (model.getCurrentColumn() >= (model.getColumnCount() - 1)) { //only occurs if 5 letters are entered
				//boolean moreRows = model.setCurrentRow(); //next row
				ConnectNResponse[] currentRow = model.getCurrentRow();
				int greenCount = 0; //counter for # of correct guesses
				for (ConnectNResponse wordleResponse : currentRow) {
					view.setColor(Character.toString(wordleResponse.getChar()),
							wordleResponse.getBackgroundColor(), 
							wordleResponse.getForegroundColor());
					if (wordleResponse.getBackgroundColor().equals(AppColors.GREEN)) {
						greenCount++;
					} 
				}
				
				if (greenCount >= model.getColumnCount()) { //correct word guess - end game
					view.repaintGridPanel();
					model.getLeaderboard().incrementTotalGamesPlayed();
					int currentRowNumber = model.getCurrentRowNumber();
					model.getLeaderboard().addWordsGuessed(currentRowNumber);
					int currentStreak = model.getLeaderboard().getCurrentStreak();
					model.getLeaderboard().setCurrentStreak(++currentStreak);
					
					new LeaderboardDialog(view, model);
				} else { //activates next row
					view.repaintGridPanel();		
					
				}
			}
			break;

		default:
			model.setCurrentColumn(text.charAt(0));
			view.repaintGridPanel();//this updates board to reflect last action
			break;
		}
		
	}

}
