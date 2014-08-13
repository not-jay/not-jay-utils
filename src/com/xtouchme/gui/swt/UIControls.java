package com.xtouchme.gui.swt;

import javax.swing.JLabel;
import javax.swing.SwingWorker;

/**
 * Contains thread-safe SWT Component update methods
 * @author xTouchMe
 */
public class UIControls {

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
