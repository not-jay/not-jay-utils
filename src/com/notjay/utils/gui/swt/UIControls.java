package com.notjay.utils.gui.swt;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Window;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.SwingWorker;
import javax.swing.UIManager;

/**
 * Contains thread-safe SWT Component update methods
 * @author xTouchMe
 */
public class UIControls {

	/**
	 * Sets the Look and Feel to the System Default
	 */
	public static void systemLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Centers a Window and sets its width and height
	 * @param frame The Window to center
	 * @param width The Window's width
	 * @param height The Window's height
	 */
	public static void centerWindow(Window frame, int width, int height) {
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		frame.setBounds(gd.getDisplayMode().getWidth()/2 - width/2,
						gd.getDisplayMode().getHeight()/2 - height/2,
						width, height);
	}
	
	/**
	 * Centers a component<br>
	 * This requires the layout to be Absolute
	 * @param component The component to center
	 * @param centerX if true, centers the component along the parent's X
	 * @param centerY if true, centers the component along the parent's Y
	 */
	public static void center(JComponent component, boolean centerX, boolean centerY) {
		if(component == null || (!centerX && !centerY) || component.getParent().getLayout() != null) return;
		
		SwingWorker<Void, String> worker = new SwingWorker<Void, String>() {
			protected Void doInBackground() throws Exception {
				component.setBounds((centerX) ? component.getParent().getWidth()/2 - component.getPreferredSize().width/2
											  : component.getBounds().x,
								   	(centerY) ? component.getParent().getHeight()/2 - component.getPreferredSize().height/2
											  : component.getBounds().y,
								   	component.getPreferredSize().width,
								   	component.getPreferredSize().height);
				return null;
			}
		};
		worker.run();
	}
	
	/**
	 * Updates a JLabel's text from another Thread
	 * @param toUpdate JLabel to update
	 * @param newValue JLabel's new text
	 */
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
