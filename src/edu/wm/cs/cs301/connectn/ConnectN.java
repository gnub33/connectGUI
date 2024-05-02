package edu.wm.cs.cs301.connectn;

import edu.wm.cs.cs301.connectn.model.ConnectNModel;
import edu.wm.cs.cs301.connectn.view.BoardFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;


//import connectV2.view.DrawGrid;
//import edu.wm.cs.cs301.connectn.view.BoardView;

public class connect implements Runnable {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new connect());
		
		//Can't use the Cross-Platform Look and Feel on Windows - Needs investigation
		if (!System.getProperty("os.name").contains("Windows")) {
			//Must use cross-platform look and feel so button backgrounds work on Mac
			try {
			    UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void run() {
		new BoardFrame(new ConnectNModel());
	}
}



