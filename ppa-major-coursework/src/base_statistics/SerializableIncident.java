package base_statistics;

import java.io.Serializable;

import api.ripley.Incident;

/**
 * Allows serialization of Incidents, to allow for 'caching' of the data.
 * @author patrick
 * @see InstantiableIncident
 * @see Serializer
 */
public class SerializableIncident implements Serializable {
	
	private static final long serialVersionUID = 6516424185521686136L;
	
	private String incidentID, dateAndTime, city, state, 
	shape, duration, summary, posted, toString;
		
	/**
	 * Constructor takes an instance of Incident, and assigns values to reflect the values in the Incidents fields.
	 * @param i the Incident from which fields are copied and assigned
	 */
	public SerializableIncident(Incident i) {
		
		incidentID = i.getIncidentID();
		dateAndTime = i.getDateAndTime();
		city = i.getCity();
		state = i.getState();
		shape = i.getShape();
		duration = i.getDuration();
		summary = i.getSummary();
		posted = i.getPosted();
	
	}
	
	/**
	 * Empty constructor
	 */
	public SerializableIncident() {
		
	}

	/**
	 * Returns the copied incidents' incidentID field
	 * @return the Incident's incidentID
	 */
	public String getIncidentID() {
		return incidentID;
	}

	/**
	 * Returns the copied incidents' dateAndTime field
	 * @return the Incident's dateAndTime
	 */
	public String getDateAndTime() {
		return dateAndTime;
	}

	/**
	 * Returns the copied incidents' city field
	 * @return the Incident's city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Returns the copied incidents' state field
	 * @return the Incident's state
	 */
	public String getState() {
		return state;
	}

	/**
	 * Returns the copied incidents' shape field
	 * @return the Incident's shape
	 */
	public String getShape() {
		return shape;
	}

	/**
	 * Returns the copied incidents' duration field
	 * @return the Incident's duration
	 */
	public String getDuration() {
		return duration;
	}

	/**
	 * Returns the copied incidents' summary field
	 * @return the Incident's summary
	 */
	public String getSummary() {
		return summary;
	}

	/**
	 * Returns the copied incidents' posted field
	 * @return the Incident's posted
	 */
	public String getPosted() {
		return posted;
	}

	/**
	 * Returns the copied incidents' toString methods return value
	 * @return the Incident's string representation
	 */
	public String getToString() {
		return toString;
	}

}
