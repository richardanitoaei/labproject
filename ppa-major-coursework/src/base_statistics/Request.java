package base_statistics;

import java.util.ArrayList;
import java.util.List;

import api.ripley.Incident;
import api.ripley.Ripley;

/**
 * Provides an interface for caching, and thus more efficient use of data from the Ripley API
 * @author patrick
 * @see Serializer
 * @see InstantiableIncident
 * @see SerializableIncident
 */
public class Request {
	
	private ArrayList<Incident> incidentsArr;
	private Ripley ripley;
	String startOfArr, endOfArr;
	private static final String PRIVATE_KEY = "90tLI3GXsd6yVD6ql2OMtA==";
	private static final String PUBLIC_KEY = "lBgm4pVp9Q7VqL46EnH7ew==";
	
	/**
	 * Constructor creates and stores an instance of Ripley with the supplied keys,
	 * and reads an ArrayList of Incidents from the cache.ser file in res/
	 */
	public Request() {
		
		this.ripley = new Ripley(PRIVATE_KEY, PUBLIC_KEY);
		incidentsArr = Serializer.read();
		
	}
	
	/**
	 * Gets incidents in a specified range from the Ripley API, substituting data from the cache where
	 * possible
	 * @param requestStart the year from which data should be requested, from the first day of the year
	 * @param requestEnd the year to which data should be requested, until the last day of the year
	 * @return an ArrayList of Incidents within the specified date range
	 */
	public ArrayList<Incident> getIncidentsInRange(String requestStart, String requestEnd) {
		
		// Append additional information to requestStart and requestEnd to support
		// the format required by the Ripley API
		requestStart += "-01-01 00:00:01";
		requestEnd += "-12-31 23:59:59";
		
		// if the data read from the cache doesnt contain anything of use:
		if (incidentsArr == null || incidentsArr.size() == 0) {
			
			// send a comprehensive request to Ripley for the entire range of dates
			
			incidentsArr = ripley.getIncidentsInRange(requestStart, requestEnd);
			
			// write the resulting ArrayList to the cache, and return it
			Serializer.write(incidentsArr);
			return incidentsArr;
					
		}
			
		// Sort the Incident array by date, and establish the first and last date in the array
		Sort.byDate(incidentsArr);
		String startOfArr = incidentsArr.get(0).getDateAndTime();
		String endOfArr = incidentsArr.get(incidentsArr.size() - 1).getDateAndTime();
		
		
		// if request is completely outside cached range
		if (requestStart.compareTo(endOfArr) > 0 || requestEnd.compareTo(startOfArr) < 0) {
			
			
			incidentsArr = ripley.getIncidentsInRange(requestStart, requestEnd);
			// note that in this case, the data is not cached
			// this may cause problems if the user never requests overlapping data sets
			return incidentsArr;
			
		}
		
		// if the user has requested data that precedes the start of the cached array
		if (requestStart.compareTo(startOfArr) < 0) {
			
			// request data between the users requested start date, and the start of our
			// cached array
			
			ArrayList<Incident> prefix = ripley.getIncidentsInRange(requestStart, startOfArr);
			
			// add the resulting array to our Incident array, and sort it by date
			prefix.stream().forEach(incident -> incidentsArr.add(incident));
			Sort.byDate(incidentsArr);
			
		}
		
		// if the user has requested data that goes past the end of the cached array
		if (requestEnd.compareTo(endOfArr) > 0) {
			
			
			
			// request data up until the date that the user has requested,
			// starting from the end of the cached data set
			ArrayList<Incident> affix = ripley.getIncidentsInRange(endOfArr, requestEnd);
			
			// add the resulting array to our Incident array, and sort it by date
			affix.stream().forEach(incident -> incidentsArr.add(incident));
			Sort.byDate(incidentsArr);
			
		}
		
		// initialise a boolean to describe whether the requested data is a subset of the cached data
		boolean insideRange = requestStart.compareTo(startOfArr) > 0
				&& requestEnd.compareTo(endOfArr) < 0;
		
		if (insideRange) {
			
			// compute the indexes of the requested start and end date, return a sublist of the cached data
			int startIndex = 0;
			int endIndex = incidentsArr.size() - 1;
			
			for (int i = 0; i < incidentsArr.size(); i++) {
					
				if (incidentsArr.get(i).getDateAndTime().compareTo(requestStart) > 0) {
					// establishes the start index as the last index for which the date of the incident
					// is less than the requested start date.
					startIndex = i - 1;
					break;
					
				}
				
			}
			
			for (int i = incidentsArr.size() - 1; i > 0; --i) {
								
				if (incidentsArr.get(i).getDateAndTime().compareTo(requestEnd) < 0) {
					// establishes the end index as the last index for which the date of the incident
					// is greater than the requested end date.
					endIndex = i + 1;
					break;
					
				}
				
			}
			
			// return the sublist from the computed start and end indexes
			return new ArrayList<Incident>(incidentsArr.subList(startIndex, endIndex));
			
		}
		
		// repeats the above process, but to account for the case where the users requested dates
		// only overlap in one direction (i.e. the start date is inside the cached date range, 
		// but the end is not.
		int startIndex = 0;
		int endIndex = incidentsArr.size() - 1;
		
		for (int i = 0; i < incidentsArr.size(); i++) {
							
			if (incidentsArr.get(i).getDateAndTime().compareTo(requestStart) > 0) {
				// establishes the start index as the last index for which the date of the incident
				// is less than the requested start date.
				startIndex = i == 0 ? 0 : i - 1;
				break;
				
			}
			
		}
		
		for (int i = incidentsArr.size() - 1; i > 0; --i) {
							
			if (incidentsArr.get(i).getDateAndTime().compareTo(requestEnd) < 0) {
				// establishes the end index as the last index for which the date of the incident
				// is greater than the requested end date.
				endIndex = i == incidentsArr.size() - 1? i : i + 1;
				break;
				
			}
			
		}
		
		// sort and serialize the array of incidents, return the necessary sublist
		Sort.byDate(incidentsArr);
		
		Serializer.write(incidentsArr);
		
		return new ArrayList<Incident>(incidentsArr.subList(startIndex, endIndex));
		
	}

	public double getVersion() {
		// TODO Auto-generated method stub
		return ripley.getVersion();
	}

	public String getLastUpdated() {
		// TODO Auto-generated method stub
		return ripley.getLastUpdated();
	}

	public String getAcknowledgementString() {
		// TODO Auto-generated method stub
		return ripley.getAcknowledgementString();
	}
	

	
	//TODO add WindowListener to serialize incidentsArr on close
}
