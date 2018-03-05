package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.text.BadLocationException;

import api.ripley.Incident;
import base_statistics.ModelWrapper;
import testView.StatChangeListener;
import view.MainFrame;

public class DateListener implements ActionListener {

	private MainFrame mf;
	private ModelWrapper model;
	
	public DateListener(MainFrame mf, ModelWrapper model) {
		
		this.mf = mf;
		this.model = model;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		mf.getButtonsPanel().setButtonsEnable(false);
		int[] years = mf.getWelcomePanel().getStartAndEndYear();
		mf.getWelcomePanel().setSecondContent(years[0], years[1]);
		mf.getFrame().revalidate();

		ArrayList<Incident> incidentsArr = new ArrayList<>();
		try {
			incidentsArr = model.requestIncidents(String.valueOf(years[0]),
					String.valueOf(years[1]));
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			incidentsArr = new ArrayList<Incident>();
			e2.printStackTrace();
		}

		System.out.println(String.valueOf(years[0]) + ", " + String.valueOf(years[1]) + " years");

		mf.initWidget();

		StatChangeListener scl = new StatChangeListener(model.getStatisticsArr());

		mf.getStatisticPanel().addChangeListener(new StatChangeListener(model.getStatisticsArr()));
		mf.getSightingsPanel().addSightsings(incidentsArr);

		double requestTime = model.getLastRequestTime();

		System.out.println("Time taken to fetch and process data: " + (int) (requestTime / 60) + " minutes, "
				+ requestTime % 60 + " seconds.");

		try {
			mf.getWelcomePanel().finalContent((int) (requestTime / 60) + " minutes, " + (int)requestTime % 60 + " seconds.");
		} catch (BadLocationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		mf.getButtonsPanel().setButtonsEnable(true);

	}
}
