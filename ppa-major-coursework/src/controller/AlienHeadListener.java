package controller;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;

import api.ripley.Incident;
import view.SightingsPanel;

/**
 * Provides a popout window showing a list of sightings for a specific state.
 * The state corresponds to the state in which the clicked AlienHead exists on the MapPanel
 * @author patrick
 * @see AlienHead
 * 
 */
public class AlienHeadListener implements ActionListener {

	private HashMap<String, Point> stateCoordinates;
	private ArrayList<Incident> incidentsArr;
	
	/**
	 * Constructor passes and assigns values 
	 * @param stateCoordinates
	 * @param incidentsArr
	 */
	public AlienHeadListener(HashMap<String, Point> stateCoordinates, ArrayList<Incident> incidentsArr) {
		
		this.stateCoordinates = stateCoordinates;
		this.incidentsArr = incidentsArr;
		
	}
	
	/**
	 * Instances a popout list of Sightings from a specific state
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton sourceButton = (JButton) e.getSource();
		Point buttonCoordinates = sourceButton.getLocation();
		
		String state = "";
		
		for (Map.Entry<String, Point> entry : stateCoordinates.entrySet()) {
			
			if (entry.getValue() == buttonCoordinates) state = entry.getKey().toUpperCase();
			
		}
		
		SightingsPanel sightingsPanel = new SightingsPanel();
		sightingsPanel.addSightsings(incidentsArr);
		
		JFrame popoutList = new JFrame(state + " sightings");
		
		popoutList.getContentPane().setLayout(new BorderLayout());
		popoutList.getContentPane().add(sightingsPanel, BorderLayout.CENTER);
		
		popoutList.setSize(600, 500);
		popoutList.setVisible(true);
		
	}


}
