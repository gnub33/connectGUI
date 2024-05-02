package edu.wm.cs.cs301.connectn.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import edu.wm.cs.cs301.connectn.ConnectNModel;

public class BoardFrame {
	
	private final JFrame frame;
	
	private final ConnectNModel model;
	
	private final DrawGrid GridPanel;
	
	private int width = 200;

	public BoardFrame(ConnectNModel model) {
		this.model = model;
		//this.keyboardPanel = new KeyboardPanel(this, model);
		//int width = 200;
		this.GridPanel = new DrawGrid(this, model, width);
		this.frame = createAndShowGUI();
		
	}
	
	private JFrame createAndShowGUI() {
		
		JFrame frame = new JFrame("ConnectN");
		frame.setSize(650, 700);
		frame.setPreferredSize(frame.getSize());
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);//does not control program termination
		frame.setJMenuBar(createMenuBar());
		frame.setResizable(false);
		frame.addWindowListener(new WindowAdapter() {  //this is what actually closes frame
			@Override
			 public void windowClosing(WindowEvent event) {//updates stats then terminates program
				shutdown();
			}
		});
		
		//DrawGrid GridPanel = new DrawGrid(frame.getSize());

		
		frame.add(createTitlePanel(), BorderLayout.NORTH);
		frame.add(GridPanel, BorderLayout.CENTER);
		//frame.add(keyboardPanel.getPanel(), BorderLayout.SOUTH);
		
		frame.pack();
		
        
		frame.setLocationByPlatform(true);
		frame.setVisible(true);
		
		System.out.println("Frame size: " + frame.getSize());
		
		return frame;
	}	
	private JMenuBar createMenuBar() {
		
		JMenuBar menuBar = new JMenuBar();
		
		JMenu diffMenu = new JMenu("Mode");
		menuBar.add(diffMenu);
		
		JMenuItem kidItem = new JMenuItem("Small");
		kidItem.addActionListener(event -> frame.dispose());
		//kidItem.addActionListener(event -> new BoardFrame(new ConnectNModel('k')));
		diffMenu.add(kidItem);
	
		JMenuItem normalItem = new JMenuItem("Medium");
		normalItem.addActionListener(event -> frame.dispose());
		normalItem.addActionListener(event -> new BoardFrame(new ConnectNModel())); //no parameter defaults to og constructer
		diffMenu.add(normalItem);
		
		JMenuItem hardItem = new JMenuItem("Large");
		hardItem.addActionListener(event -> frame.dispose());
		//hardItem.addActionListener(event -> new BoardFrame(new ConnectNModel('h'))); //will work for any character
		diffMenu.add(hardItem);
		
		JMenu helpMenu = new JMenu("Help");
		menuBar.add(helpMenu);
		
		JMenuItem instructionsItem = new JMenuItem("Instructions...");
		instructionsItem.addActionListener(event -> Instructions.show());
		helpMenu.add(instructionsItem);
		
		JMenuItem aboutItem = new JMenuItem("About...");
		aboutItem.addActionListener(event -> new AboutDialog(this));
		helpMenu.add(aboutItem);
		
		return menuBar;
	}
	
	private JPanel createTitlePanel() {
		JPanel panel = new JPanel(new FlowLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
		
		InputMap inputMap = panel.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "cancelAction");
		ActionMap actionMap = panel.getActionMap();
		actionMap.put("cancelAction", new CancelAction());
		
		JLabel label = new JLabel("ConnectN");
		//label.setFont(AppFonts.getTitleFont());
		panel.add(label);
		
		return panel;
	}
	

	public void initialize() {
		//resetDefaultColors();
		/////initalize
		//reset colors on board
		this.GridPanel.repaint();
		//reset turn count
		this.GridPanel.resetTurnCount();
		//set current color to blue
		this.GridPanel.defaultClr();
	}
	
	
	
	public void shutdown() {
		//model.showPlayAgain();
		frame.dispose();
		System.exit(0);
	}
	
//	public void checkGameOver() {
//		int pa = 3;
//		if(GridPanel.isActive() == 0) {
//			pa = model.showPlayAgain();
//			if(pa == 0) {
//				// play again
//				createAndShowGUI();
//				System.out.println("RESTARTING ");
//			}
//			else if(pa == 1) {
//				System.out.println("ENDING ");
//				shutdown();
//			}
//		}
//	}
	
	public void destroy() {
		frame.dispose();
	}
	
	public void setColor(String letter, Color backgroundColor, Color foregroundColor) {
		//keyboardPanel.setColor(letter, backgroundColor, foregroundColor);
	}
	
	public void repaintGridPanel() {
		//GridPanel.repaint();
	}

	public JFrame getFrame() {
		return frame;
	}
	
	private class CancelAction extends AbstractAction {

		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent event) {
			shutdown();
		}
		
	}

}
