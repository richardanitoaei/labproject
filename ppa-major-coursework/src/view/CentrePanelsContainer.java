package view;

import java.awt.CardLayout;
import java.awt.Component;

import javax.swing.JPanel;

@SuppressWarnings("serial")
/**
 * Provides the JPanel that holds all the other panels/screens.
 * 
 *
 */
public class CentrePanelsContainer extends JPanel {

	public CentrePanelsContainer(){
		setLayout(new CardLayout());
	}
	
	/**
	 * Adds the provided JPanel to the list of current panels. 
	 * @param jpanel JPanel object
	 * @param panelName JPanel's name
	 */
	public void addPanel(JPanel jpanel,String panelName){
	
		this.add(jpanel,panelName);
		
	}
	
}
