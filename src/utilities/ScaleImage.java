package utilities;

import java.awt.Image;

import javax.swing.ImageIcon;

public class ScaleImage {

	public ImageIcon scaleImg(ImageIcon icon, int width, int height) {
		Image img = icon.getImage();
		Image imgScale = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		ImageIcon scaleIcons = new ImageIcon(imgScale);
		return scaleIcons;
	}
	

}
