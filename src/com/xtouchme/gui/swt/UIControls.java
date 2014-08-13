package com.xtouchme.gui.swt;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingWorker;
import javax.swing.UIManager;

/**
 * Contains thread-safe SWT Component update methods
 * @author xTouchMe
 */
public class UIControls {

	public static void systemLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void centerFrame(JFrame frame, int width, int height) {
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		frame.setBounds(gd.getDisplayMode().getWidth()/2 - width/2,
						gd.getDisplayMode().getHeight()/2 - height/2,
						width, height);
	}
	
	public static void update(final JLabel toUpdate, final String newValue) {
		SwingWorker<Void, String> worker = new SwingWorker<Void, String>() {
			protected Void doInBackground() throws Exception {
				toUpdate.setText(newValue);
				toUpdate.setBounds(toUpdate.getBounds().x,
								   toUpdate.getBounds().y,
								   toUpdate.getPreferredSize().width,
								   toUpdate.getPreferredSize().height);
				return null;
			}
		};
		worker.run();
	}
	
}
