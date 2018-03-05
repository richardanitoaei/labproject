package testView;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import base_statistics.FromRipley;

/**
 * Lorem Ipsum Gypsum est dest west
 * @author patrick
 *
 */
public class AlienHead extends JButton {

	private BufferedImage bufferedImage;
	private FromRipley fr;
	private ImageIcon picture;
	private String state;
	private static int currentMax, currentMin;
	private double gradient, intercept;
	
	/**
	 * passes values to allow for resizing of the alien head based on some stats
	 * @param currentMin
	 * @param currentMax
	 * @param gradient
	 * @param intercept
	 * @param state
	 */
	public AlienHead(int currentMin, int currentMax, double gradient, double intercept, String state) {
		
		try {
			
	        bufferedImage = ImageIO.read(new File("res/alien2.png"));
	        
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
		
		this.setBorderPainted(false);
		this.state = state;
			
	}
	
	/**
	 * Resizes the AlienHead JButton based on the number of sightings in this AlienHead's state
	 * compared with the maximum number of sightings by state in the dataset.
	 * 
	 * @param thisSightings
	 * @param currentMax
	 */
	public void resizeAlien(int thisSightings, int currentMax) {
		
		// calculate a scale factor with a lower bound of 0.5
		double scaleFactor = 
				thisSightings > currentMax / 2 ? 
						1.50 * ((double)thisSightings / (double)currentMax)
						: 0.5;
						
		int height = (int) (scaleFactor * bufferedImage.getHeight());
		int width = (int) (scaleFactor * bufferedImage.getWidth());
		
		// resize the image and set this JButton's Icon to it.
		Image image = bufferedImage.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
		
		this.setIcon(new ImageIcon(image));
		
	}
	
}
