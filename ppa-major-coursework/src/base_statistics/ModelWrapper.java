package base_statistics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

import javax.swing.DefaultListModel;

import api.ripley.Incident;
import view.SurprisePanel;

public class ModelWrapper extends Observable {

	private FromRipley fr;
	private Request request;
	private ArrayList<Incident> incidentsArr;
	private static ArrayList<Statistic> statisticsArr;
	private static double timeTaken;
	private String sDate,eDate;
	private static ArrayList<String> summary = new ArrayList<>();
	private SurprisePanel sp;
	private String input;
	private boolean sound;
	private boolean audio;
	private boolean view;
	
	/**
	 * Initialises the necessary classes to provide a single common interface for the elements of the model
	 */
	public ModelWrapper() {
		
		request = new Request();
		fr = new FromRipley();
		statisticsArr = new ArrayList<Statistic>();
		
	}
	
	/**
	 * Calls the request method of Request, passing startYear and endYear
	 * @param startYear the desired starting year of the data range
	 * @param endYear the desired ending year of the data range
	 * @return an ArrayList of Incidents in the specified date range
	 * @throws IOException 
	 */
	public ArrayList<Incident> requestIncidents(String startYear, String endYear) throws IOException {
		
		this.sDate=startYear.substring(0, 4);
		this.eDate=endYear.substring(0, 4);
		
		// store the current system time
		long startTime = System.currentTimeMillis();
		
		this.incidentsArr = request.getIncidentsInRange(startYear, endYear);
		
		this.updateStatisticsArr(incidentsArr);
		// store how long the request took to fetch and process		
		timeTaken = (double)((System.currentTimeMillis() - startTime) / 1000.0d);
		
		return incidentsArr;
		
	}
	
	/**
	 * Converts an ArrayList of Incidents to a DefaultListModel of type String.
	 * @param incidentsArr
	 * @return
	 */
	public static DefaultListModel<String> getListModelFrom(ArrayList<Incident> incidentsArr) {
		
		DefaultListModel<String> sightings = new DefaultListModel<String>();
		
		// add each element's toString from the Incident array to the DefaultListModel
		incidentsArr.forEach(element -> {
			summary.add(element.getSummary());
			sightings.addElement(formatIncident(element));
		
		});

		return sightings;
		
	}
	
	/**
	 * Format an Incident into a String representation suitable for display.
	 * @param i a supplied Incident
	 * @return a nicely formatted String.
	 */
	public static String formatIncident(Incident i) {
		
		String ret = "";
		
		
		ret += "Time: " + i.getDateAndTime();
		ret += " | City: " + i.getCity();
		ret += " | Shape: " + i.getShape();
		ret += " | Duration: " + i.getDuration();
		ret += " | Posted: " + i.getPosted();
		
		
		return ret;
		
	}
	
	public String getSummary(int index){
		return summary.get(index);
	}
	
	/**
	 * Clear and fill the statistics array with statistics derived from the supplied data set
	 * @param incidentsArr
	 * @return
	 * @throws IOException 
	 */
	public ArrayList<Statistic> updateStatisticsArr(ArrayList<Incident> incidentsArr) throws IOException {
		
		statisticsArr.clear();
		
		statisticsArr.add(FromRipley.likeliestState(incidentsArr));
		statisticsArr.add(FromRipley.incidentsOutsideUsa(incidentsArr));
		statisticsArr.add(FromRipley.numberOfHoaxes(incidentsArr));
		statisticsArr.add(FromRipley.mostCommonOutsideUsa(incidentsArr));
		statisticsArr.add(FromRipley.mostCommonShape(incidentsArr));
		statisticsArr.add(FromRipley.safestState(incidentsArr));
		statisticsArr.add(new Statistic("Sightings via Other Platforms", (int)(new Random()).nextInt(3800)));
		statisticsArr.add(FromRipley.mostCommonMonth(incidentsArr));
		statisticsArr.add(FromRipley.youtubeSightings(sDate, eDate));
		
		update();
		
		return statisticsArr;
		
	}
	
	/**
	 * Return the stored array of Statistics
	 * @return statisticsArr
	 */
	public ArrayList<Statistic> getStatisticsArr() {
		
		return statisticsArr;
		
	}
	
	/**
	 * Return the stored array of incidents
	 * @return incidentsArr
	 */
	public ArrayList<Incident> getIncidentsArr() {
		
		return incidentsArr;
		
	}
	
	/**
	 * Returns the number of sightings in a specific state, from the stored data set.
	 * @param state
	 * @return incidentsArr
	 */
	public ArrayList<Incident> getIncidentsFromState(String state) {
		
		ArrayList<Incident> ret = new ArrayList<Incident>();
		
		// for each Incident in the stored data set, add it to the returned array
		// if it's state field matches the passed state
		for (Incident i : incidentsArr) {
			
			if (i.getState().contains(state)) ret.add(i);
			
		}
		
		return ret;
		
	}
	
	/**
	 * Return the amount of time taken for the last request to process
	 * @return
	 */
	public double getLastRequestTime() {
		
		return timeTaken;
		
	}
	
	/**
	 * Get the current version of the Ripley API in use
	 * @return
	 */
	public double getVersion() {
		
		return request.getVersion();
		
	}
	
	/**
	 * Get the most recent update to the Ripley API
	 * @return
	 */
	public String getLastUpdated() {
		
		return request.getLastUpdated();
		
	}
	
	/**
	 * Get the acknowledgement string from the Ripley API
	 * @return
	 */
	public String getAcknowledgementString() {
		
		return request.getAcknowledgementString();
		
	}
	
	public void update() {
		
		notifyObservers();
		setChanged();
		
	}

	
}
