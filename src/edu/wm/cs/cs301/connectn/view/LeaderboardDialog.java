package edu.wm.cs.cs301.connectn.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import edu.wm.cs.cs301.connectn.model.ConnectModel;

public class LeaderboardDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private final ExitAction exitAction;
	
	private final NextAction nextAction;
	
	private final BoardView view;
	
	private final ConnectModel model;
	
	public LeaderboardDialog(BoardView view, ConnectModel model) {
		
		super(view.getFrame(), "Statistics", true);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {  //this is what actually closes frame
			@Override
			 public void windowClosing(WindowEvent event) {//updates stats then terminates program
				System.out.println("Closing.....");
				dispose();
				view.shutdown();
			}
		});
		this.view = view;
		this.model = model;
		this.exitAction = new ExitAction();
		this.nextAction = new NextAction();
		
		add(createMainPanel(), BorderLayout.NORTH);
		add(createButtonPanel(), BorderLayout.SOUTH);
		
		pack();
		setLocationRelativeTo(view.getFrame());
		setVisible(true);
	}
	
	private JPanel createMainPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
		panel.add(createTopPanel(), BorderLayout.NORTH);
		panel.add(createBottomPanel(), BorderLayout.SOUTH);
		
		return panel;
	}
	
	private JPanel createTopPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
		
		panel.add(createTitlePanel(), BorderLayout.NORTH);
		panel.add(createSummaryPanel(), BorderLayout.SOUTH);
		
		return panel;
	}
	
	private JPanel createTitlePanel() {
		JPanel panel = new JPanel(new FlowLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
		
		JLabel label = new JLabel("Statistics");
		label.setFont(AppFonts.getTitleFont());
		panel.add(label);
		
		return panel;
	}
	
	private JPanel createBottomPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
		
//		panel.add(createSubtitlePanel(), BorderLayout.NORTH);
//		panel.add(new DistributionPanel(view, model), BorderLayout.SOUTH);
		
		return panel;
	}
	
	private JPanel createSubtitlePanel() {
		JPanel panel = new JPanel(new FlowLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
		
		JLabel label = new JLabel("Guess Distribution");
		label.setFont(AppFonts.getTitleFont());
		panel.add(label);
		
		return panel;
	}
	
	private JPanel createSummaryPanel() {
		JPanel panel = new JPanel(new GridLayout(0, 4));
		panel.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
		
		int totalGamesPlayed = model.getLeaderboard().getTotalGamesPlayed();
		int currentStreak = model.getLeaderboard().getCurrentStreak();
		int longestStreak = model.getLeaderboard().getLongestStreak();
		List<Integer> wordsGuessed = model.getLeaderboard().getWordsGuessed();
		int percent = (wordsGuessed.size() * 1000 + 5) / (totalGamesPlayed * 10);
		
		panel.add(createStatisticsPanel(totalGamesPlayed, "Played", ""));
		panel.add(createStatisticsPanel(percent, "Win %", ""));
		panel.add(createStatisticsPanel(currentStreak, "Current", "Streak"));
		panel.add(createStatisticsPanel(longestStreak, "Longest", "Streak"));
		
		return panel;
	}
	
	private JPanel createStatisticsPanel(int value, String line1, String line2) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
		Font textFont = AppFonts.getTextFont();
		
		JLabel valueLabel = new JLabel(String.format("%,d", value));
		valueLabel.setFont(AppFonts.getTitleFont());
		valueLabel.setAlignmentX(CENTER_ALIGNMENT);
		panel.add(valueLabel);
		
		JLabel label = new JLabel(line1);
		label.setFont(textFont);
		label.setAlignmentX(CENTER_ALIGNMENT);
		panel.add(label);
		
		label = new JLabel(line2);
		label.setFont(textFont);
		label.setAlignmentX(CENTER_ALIGNMENT);
		panel.add(label);
		
		return panel;
	}
	
	private JPanel createButtonPanel() {
		JPanel panel = new JPanel(new FlowLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
		
		InputMap inputMap = panel.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "exitAction");
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "nextAction");
		ActionMap actionMap = panel.getActionMap();
		actionMap.put("nextAction", nextAction);
		actionMap.put("exitAction", exitAction);
		
		JButton nextButton = new JButton("Next Word");
		nextButton.addActionListener(nextAction);
		panel.add(nextButton);
		
		JButton exitButton = new JButton("Exit Wordle"); //This button performs similar action needed for task 1
		exitButton.addActionListener(exitAction); //Need to add action listener to red 'x's 
		panel.add(exitButton);
		
		Dimension nextDimension = nextButton.getPreferredSize();
		Dimension exitDimension = exitButton.getPreferredSize();
		int maxWidth = Math.max(nextDimension.width, exitDimension.width) + 10;
		nextButton.setPreferredSize(new Dimension(maxWidth, nextDimension.height));
		exitButton.setPreferredSize(new Dimension(maxWidth, exitDimension.height));
		
		return panel;
	}
	
	private class ExitAction extends AbstractAction {

		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent event) {
			dispose();
			view.shutdown();
		}
		
	}
	
	private class NextAction extends AbstractAction {

		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent event) {
			dispose();
			model.initialize();
			view.repaintGridPanel();
			view.resetDefaultColors();
		}
		
	}

}
