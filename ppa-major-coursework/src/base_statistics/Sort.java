package base_statistics;

import java.util.ArrayList;
import java.util.Comparator;

import api.ripley.Incident;

/**
 * Provides an interface for sorting an ArrayList of Incidents by a certain predicate derived from one of their fields.
 * @author patrick
 *
 */
public class Sort {
	
	private Sort() {
		
	}
	
	/**
	 * Sorts a supplied ArrayList of Incidents by their date field
	 * @param incidentsArr the supplied ArrayList of Incidents
	 */
	public static void byDate(ArrayList<Incident> incidentsArr) {
		
		incidentsArr.sort(new Comparator<Incident>() {

			@Override
			public int compare(Incident o1, Incident o2) {
				// TODO Auto-generated method stub
				String date1 = o1.getDateAndTime();
				String date2 = o2.getDateAndTime();
				return date1.compareTo(date2);
				
			}
			
		});
		
	}
	
	/**
	 * Sorts a supplied ArrayList of Incidents by their city field
	 * @param incidentsArr the supplied ArrayList of Incidents
	 */
	public static void byCity(ArrayList<Incident> incidentsArr) {
		
		incidentsArr.sort(new Comparator<Incident>() {

			@Override
			public int compare(Incident o1, Incident o2) {
				// TODO Auto-generated method stub
				String city1 = o1.getCity();
				String city2 = o2.getCity();
				return city1.compareTo(city2);
			}
			
		});
	}
	
	/**
	 * Sorts a supplied ArrayList of Incidents by their shape field
	 * @param incidentsArr the supplied ArrayList of Incidents
	 */
	public static void byShape(ArrayList<Incident> incidentsArr) {
		
		incidentsArr.sort(new Comparator<Incident>() {

			@Override
			public int compare(Incident o1, Incident o2) {
				// TODO Auto-generated method stub
				String shape1 = o1.getShape();
				String shape2 = o2.getShape();
				return shape1.compareTo(shape2);
			}
			
		});
		
	}
	
	/**
	 * Sorts a supplied ArrayList of Incidents by their duration field
	 * @param incidentsArr the supplied ArrayList of Incidents
	 */
	public static void byDuration(ArrayList<Incident> incidentsArr) {
		
		//TODO Extend this to fit the brief
		incidentsArr.sort(new Comparator<Incident>() {

			@Override
			public int compare(Incident o1, Incident o2) {
				// TODO Auto-generated method stub
				String duration1 = o1.getDuration();
				String duration2 = o2.getDuration();
				return duration1.compareTo(duration2);
			}
			
		});
	}
	
	/**
	 * Sorts a supplied ArrayList of Incidents by their posted field (time posted)
	 * @param incidentsArr the supplied ArrayList of Incidents
	 */
	public static void byPosted(ArrayList<Incident> incidentsArr) {
		
		incidentsArr.sort(new Comparator<Incident>() {

			@Override
			public int compare(Incident o1, Incident o2) {
				// TODO Auto-generated method stub
				String posted1 = o1.getPosted();
				String posted2 = o2.getPosted();
				return posted1.compareTo(posted2);
			}
			
		});
	}
	
}
