package view;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import api.ripley.Incident;
import base_statistics.FromRipley;
import base_statistics.ModelWrapper;
import controller.AlienHeadListener;
import testView.AlienHead;

/**
 * 
 * Creates and displays the second panel/map panel
 *
 */
public class MapPanel extends JPanel {
	
	private HashMap<String, Point> stateCoordinates;
	
	/**
	 * Sets out the dimension and maps all the placeholders for the Alien Heads onto the image;
	 */
	public MapPanel() {
		setLayout(null);
		setName("Panel2");
		setPreferredSize(new Dimension(1024, 622));
		
		stateCoordinates = new HashMap<String, Point>();
		
		stateCoordinates.put("CA", new Point(52, 280));
		stateCoordinates.put("SD", new Point(63, 200));
		stateCoordinates.put("WY", new Point(294, 180));
		stateCoordinates.put("WI", new Point(586, 142));
		stateCoordinates.put("WV", new Point(926, 321));
		stateCoordinates.put("WA", new Point(108, 43));
		stateCoordinates.put("VA", new Point(795, 286));
		stateCoordinates.put("VT", new Point(820, 53));
		stateCoordinates.put("UT", new Point(219, 255));
		stateCoordinates.put("TX", new Point(446, 459));
		stateCoordinates.put("TN", new Point(665, 350));
		stateCoordinates.put("SC", new Point(777, 380));
		stateCoordinates.put("RI", new Point(926, 194));
		stateCoordinates.put("PA", new Point(795, 213));
		stateCoordinates.put("OR", new Point(93, 111));
		stateCoordinates.put("OK", new Point(458, 365)); 
		stateCoordinates.put("OH", new Point(705, 255));
		stateCoordinates.put("ND", new Point(406, 96));
		stateCoordinates.put("NC", new Point(807, 338));
		stateCoordinates.put("NY", new Point(830, 142));
		stateCoordinates.put("NM", new Point(294, 375));
		stateCoordinates.put("NJ", new Point(926, 243));
		stateCoordinates.put("NH", new Point(820, 20));
		stateCoordinates.put("NE", new Point(419, 233));
		stateCoordinates.put("MT", new Point(272, 83));
		stateCoordinates.put("MO", new Point(557, 302));
		stateCoordinates.put("MS", new Point(609, 430));
		stateCoordinates.put("MN", new Point(524, 111));
		stateCoordinates.put("MI", new Point(686, 180));
		stateCoordinates.put("MA", new Point(820, 83));
		stateCoordinates.put("MD", new Point(946, 295));
		stateCoordinates.put("ME", new Point(904, 96));
		stateCoordinates.put("LA", new Point(557, 459));
		stateCoordinates.put("KY", new Point(686, 302));
		stateCoordinates.put("KS", new Point(446, 302));
		stateCoordinates.put("IA", new Point(524, 213));
		stateCoordinates.put("SD", new Point(419, 164));
		stateCoordinates.put("IN", new Point(670, 255));
		stateCoordinates.put("IL", new Point(609, 265));
		stateCoordinates.put("ID", new Point(184, 128));
		stateCoordinates.put("HI", new Point(294, 563));
		stateCoordinates.put("GA", new Point(733, 416));
		stateCoordinates.put("FL", new Point(784, 520));
		stateCoordinates.put("DE", new Point(936, 269));
		stateCoordinates.put("DC", new Point(767, 243));
		stateCoordinates.put("CT", new Point(945, 213));
		stateCoordinates.put("CO", new Point(328, 269));
		stateCoordinates.put("AZ", new Point(207, 350));
		stateCoordinates.put("AL", new Point(665, 430));
		stateCoordinates.put("AK", new Point(108, 498));
		stateCoordinates.put("NV", new Point(134, 233));
		stateCoordinates.put("AR", new Point(557, 380));
		
		
		JLabel background = new JLabel("");
		background.setIcon(new ImageIcon("res/USStates.png"));
		background.setBounds(0, 0, 1024, 622);
		add(background);
		
		
	}
	
	/**
	 * Modifies the map so that the size of the AlienHeads is relevant in accordance with the data supplied.
	 * @param model
	 */
	public void setIncidentsArr(ModelWrapper model) {
		
		ArrayList<Incident> incidentsArr = model.getIncidentsArr();
		
		this.removeAll();
		FromRipley fr = new FromRipley();
		
		int currentMax = fr.getMinMax(incidentsArr)[1];
		
		int currentMin = 1;
		
		double gradient = 1.0 / (double)(currentMax - currentMin);
		double intercept = 1.50 - (gradient * ((double)currentMax));
				
		for (Map.Entry<String, Point> entry : stateCoordinates.entrySet()) {
			
			String state = entry.getKey();
			int numberOfSightings = FromRipley.sightingsInState(incidentsArr, state);
			
			AlienHead head = new AlienHead(currentMin, currentMax, gradient, intercept, state);
			head.setBounds((int)entry.getValue().getX(), (int)entry.getValue().getY(), 40, 60);
			head.resizeAlien(numberOfSightings, currentMax);
			ArrayList<Incident> stateIncidents = model.getIncidentsFromState(state);
			head.addActionListener(new AlienHeadListener(stateCoordinates, stateIncidents));
			this.add(head);
		
		}
		
		JLabel background = new JLabel("");
		background.setIcon(new ImageIcon("res/USStates.png"));
		background.setBounds(0, 0, 1024, 622);
		add(background);
		
	}
	


}