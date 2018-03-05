package base_statistics;

import api.ripley.Incident;
/**
 * Extends the Incident class to allow for construction (and subsequent upcasting) of InstantiableIncidents.
 * @author patrick
 * @see SerializableIncident
 * @see Serializer
 */
public class InstantiableIncident extends Incident {
	
	/**
	 * Constructor matches the superclass constructor exactly. Because of this, any
	 * InstantiableIncident can successfully be cast to Incident.
	 * @param incidentID
	 * @param dateAndTime
	 * @param city
	 * @param state
	 * @param shape
	 * @param duration
	 * @param summary
	 * @param posted
	 */
	protected InstantiableIncident(String incidentID, String dateAndTime, String city, String state, String shape,
			String duration, String summary, String posted) {
		super(incidentID, dateAndTime, city, state, shape, duration, summary, posted);
		// TODO Auto-generated constructor stub
	}

}
