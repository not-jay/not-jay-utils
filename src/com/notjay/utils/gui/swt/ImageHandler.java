package com.notjay.utils.gui.swt;

import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * Contains image related functions
 * @author xTouchMe
 */
public class ImageHandler {

	public static ImageIcon getResizedImage(String path, int newWidth, int newHeight) {
		if(path.endsWith(".gif")) return new ImageIcon(path);
		
		Image i = new ImageIcon(path).getImage();
		
		return new ImageIcon(i.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH));
	}
	
	public static ImageIcon getResizedImage(String path, int newWidth) {
		if(path.endsWith(".gif")) return new ImageIcon(path);
		
		Image i = new ImageIcon(path).getImage();
		float width = i.getWidth(null);
		float height = i.getHeight(null);
		float ratio = width/height;
		
		return new ImageIcon(i.getScaledInstance(newWidth, (int)((float)newWidth/ratio), Image.SCALE_SMOOTH));
	}
	
}
