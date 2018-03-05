package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JComboBox;

import api.ripley.Incident;
import base_statistics.Sort;
import view.SightingsPanel;

public class SightingsController implements ActionListener,MouseListener {
	
	JComboBox<String> sortBy;
	ArrayList<Incident> incidentsArr;
	SightingsPanel sightingsPanel;

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String sortType = (String)sortBy.getSelectedItem();
		switch (sortType) {
		
		case "Date":
			Sort.byDate(incidentsArr);
			sightingsPanel.addSightsings(incidentsArr);
			break;
			
		case "City":
			Sort.byCity(incidentsArr);
			sightingsPanel.addSightsings(incidentsArr);
			break;
			
		case "Shape":
			Sort.byShape(incidentsArr);
			sightingsPanel.addSightsings(incidentsArr);
			break;
			
		case "Duration":
			Sort.byDuration(incidentsArr);
			sightingsPanel.addSightsings(incidentsArr);
			break;
			
		case "Posted":
			Sort.byPosted(incidentsArr);
			sightingsPanel.addSightsings(incidentsArr);
			break;
			
		default:
			break;
		}
		
	}

}
