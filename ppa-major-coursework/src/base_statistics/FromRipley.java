package base_statistics;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import api.ripley.Incident;

/**
 * Class used largely to derive information from a supplied data set of
 * Incidents.
 * 
 * @author patrick
 *
 */
public class FromRipley {

	private static ArrayList<String> UsStates;

	private String[] treat = { /* thankyou ba$ed martin */
			"Alabama,Ala.,AL", "Alaska,Alaska,AK", "American,Samoa,,,AS", "Arizona,Ariz.,AZ", "Arkansas,Ark.,AR",
			"California,Calif.,CA", "Colorado,Colo.,CO", "Connecticut,Conn.,CT", "Delaware,Del.,DE",
			"Dist.,of,Columbia,D.C.,DC", "Florida,Fla.,FL", "Georgia,Ga.,GA", "Guam,Guam,GU", "Hawaii,Hawaii,HI",
			"Idaho,Idaho,ID", "Illinois,Ill.,IL", "Indiana,Ind.,IN", "Iowa,Iowa,IA", "Kansas,Kans.,KS",
			"Kentucky,Ky.,KY", "Louisiana,La.,LA", "Maine,Maine,ME", "Maryland,Md.,MD", "Marshall,Islands,,,MH",
			"Massachusetts,Mass.,MA", "Michigan,Mich.,MI", "Micronesia,,,FM", "Minnesota,Minn.,MN",
			"Mississippi,Miss.,MS", "Missouri,Mo.,MO", "Montana,Mont.,MT", "Nebraska,Nebr.,NE", "Nevada,Nev.,NV",
			"New_Hampshire,N.H.,NH", "New_Jersey,N.J.,NJ", "New_Mexico,N.M.,NM", "New_York,N.Y.,NY",
			"North_Carolina,N.C.,NC", "North_Dakota,N.D.,ND", "Northern_Marianas,,MP", "Ohio,Ohio,OH",
			"Oklahoma,Okla.,OK", "Oregon,Ore.,OR", "Palau,,PW", "Pennsylvania,Pa.,PA", "Puerto,Rico,P.R.,PR",
			"Rhode,Island,R.I.,RI", "South_Carolina,S.C.,SC", "South_Dakota,S.D.,SD", "Tennessee,Tenn.,TN",
			"Texas,Tex.,TX", "Utah,Utah,UT", "Vermont,Vt.,VT", "Virginia,Va.,VA", "Virgin_Islands,V.I.,VI",
			"Washington,Wash.,WA", "West,Virginia,W.Va.,WV", "Wisconsin,Wis.,WI", "Wyoming,Wyo.,WY" };

	/**
	 * Read the array of US State Abbreviations to a stored field of the class.
	 */
	public FromRipley() {

		UsStates = new ArrayList<String>();

		try {

			Files.lines(Paths.get("res/US_state_abbreviations")).map(string -> " " + string + " ")
					.forEach(UsStates::add);

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

	/**
	 * Returns the number of incidents classified as hoaxes in the supplied
	 * range of incidents.
	 * 
	 * @param incidentsArr
	 * @return
	 */
	public static Statistic numberOfHoaxes(ArrayList<Incident> incidentsArr) {

		// filter out any Incidents for which their String representation does
		// not contain the word "HOAX"
		// count the total number of elements post-filtering
		int numberOfHoaxes = (int) incidentsArr.stream().map(incident -> incident.toString())
				.filter(string -> string.contains("HOAX")).count();

		return new Statistic("Number of Hoaxes", numberOfHoaxes);

	}

	/**
	 * Returns the number of incidents that occured outside of the USA in the
	 * supplied range of incidents.
	 * 
	 * @param incidentsArr
	 * @return
	 */
	public static Statistic incidentsOutsideUsa(ArrayList<Incident> incidentsArr) {

		// filter out any incidents for which their state field does not match
		// any element of the UsStates array
		int incidentsOutsideUsa = (int) incidentsArr.stream().map(incident -> incident.toString())
				.filter(string -> !UsStates.stream().parallel().anyMatch(string::contains)).count();

		return new Statistic("Incidents outside of USA", incidentsOutsideUsa);

	}

	/**
	 * For each state, calculate the total number of sightings over the
	 * specified data set. Return the smallest and largest of these values.
	 * 
	 * @param incidentsArr
	 * @return
	 */
	public int[] getMinMax(ArrayList<Incident> incidentsArr) {

		HashMap<String, Integer> occurencesByState = new HashMap<String, Integer>();

		for (String s : UsStates)
			occurencesByState.put(s, 0);

		// increment the entry corresponding to the incident's state in
		// occurencesByState by 1.
		for (Incident i : incidentsArr) {

			occurencesByState.putIfAbsent(i.getState(), 0);
			occurencesByState.put(i.getState(), occurencesByState.get(i.getState()) + 1);

		}

		int currentMax = 0;
		int currentMin = Integer.MAX_VALUE;

		String location = "";

		// iterate over the map to compute the largest and smallest values that
		// exist in it.
		for (Map.Entry<String, Integer> entry : occurencesByState.entrySet()) {

			if (entry.getValue() == null)
				continue;

			if (entry.getValue() > currentMax) {

				currentMax = entry.getValue();

			} else if (entry.getValue() < currentMin) {

				currentMin = entry.getValue();

			}

		}

		// return these values
		return new int[] { currentMin, currentMax };

	}

	/**
	 * Given an array of incidents and a state, return the number of sightings
	 * over the data set that occured in the supplied state
	 * 
	 * @param incidentsArr
	 * @param state
	 * @return
	 */
	public static int sightingsInState(ArrayList<Incident> incidentsArr, String state) {

		return (int) incidentsArr.stream().map(incident -> incident.toString())
				.filter(string -> string.contains(state.toUpperCase())).count();

	}

	/**
	 * Returns the most common location for UFO sightings over the data set,
	 * with the exception of locations in the USA
	 * 
	 * @param incidentsArr
	 * @return
	 */
	public static Statistic mostCommonOutsideUsa(ArrayList<Incident> incidentsArr) {

		HashMap<String, Integer> occurencesByLocation = new HashMap<String, Integer>();

		// create an array of Incidents that occured outside of the USA
		incidentsArr = incidentsArr.stream()
				.filter(incident -> !UsStates.stream().parallel().anyMatch(incident.toString()::contains))
				.collect(Collectors.toCollection(ArrayList::new));

		// update a map with the total number of sightings for each city, using
		// the specific incident's city as the key
		for (Incident i : incidentsArr) {

			int currentCount = occurencesByLocation.containsKey(i.getCity()) ? occurencesByLocation.get(i.getCity()) + 1
					: 1;

			occurencesByLocation.put(i.getCity(), currentCount);

		}

		int currentMax = 0;
		String location = "";

		// iterate over the map's entry set to find the maximum, and store a
		// reference to the associated key
		for (Map.Entry<String, Integer> entry : occurencesByLocation.entrySet()) {

			if (entry.getValue() > currentMax) {

				currentMax = entry.getValue();
				location = entry.getKey();

			}

		}

		// return a new statistic using said key
		return new Statistic("Most common sightings outside USA", location);

	}

	public static Statistic mostCommonMonth(ArrayList<Incident> incidentsArr) {

		int jan = 0, feb = 0, mar = 0, apr = 0, may = 0, jun = 0, jul = 0, aug = 0, sep = 0, oct = 0, nov = 0, dec = 0;
		int[] incidentsOfMonth = { jan, feb, mar, apr, may, jun, jul, aug, sep, oct, nov, dec };
		String[] month = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
				"October", "November", "December" };

		incidentsArr.forEach(incident -> {
			int monthInt = Integer.parseInt(incident.getDateAndTime().substring(5, 7));
			incidentsOfMonth[(monthInt - 1)] += 1;
		});
		int indexOfMax = 0;
		int max = incidentsOfMonth[0];
		for (int i = 0; i < incidentsOfMonth.length; i++) {
			if (incidentsOfMonth[i] > max) {
				max = incidentsOfMonth[i];
				indexOfMax = i;
			}
		}

		return new Statistic("Most common month sightings ", month[indexOfMax]);

	}

	public static Statistic youtubeSightings(String sDate, String eDate) throws IOException {

		return new Statistic("Number of sightings on Youtube ",
				new GoogleAPI("Aliensightings", sDate, eDate).getOutput());

	}

	/**
	 * Predicts the most likely state in which a UFO sighting will occur next,
	 * by using a weighted average over the range of dates of incidents. Using a
	 * weighted average accounts for both cyclical changes in frequency, and
	 * 'flurries' of sightings (common metrics for predicting UFO sightings).
	 * 
	 * @param incidentsArr
	 * @return
	 */
	public static Statistic likeliestState(ArrayList<Incident> incidentsArr) {

		Sort.byDate(incidentsArr);

		// establish some variables for calculating the weight of each year's
		// aggregate sightings
		int startYear = getYear(incidentsArr.get(0));
		int endYear = getYear(incidentsArr.get(incidentsArr.size() - 1));
		int range = endYear - startYear;

		// sum of all integers up to and including range.
		// used to give each year an incremental weighting, and ensure
		// that the sum of these weightings is 1
		int fractionalSumTotal = (range * (range + 1)) / 2;

		// filter the passed array of Incidents to include only incidents that
		// occured
		// in the USA (as this method returns a state)
		// locations from countries without states are excluded first to reduce
		// the
		// number of times we need to check our UsStates array
		ArrayList<Incident> safelyIterable = new ArrayList();
		safelyIterable.addAll(incidentsArr);
		ArrayList<Incident> incidentsInUsa = safelyIterable.stream()
				.filter(incident -> !incident.getState().equals("Not specified.")) // excludes
																					// any
																					// location
																					// without
																					// a
																					// state
				.filter(incident -> !UsStates.stream() // excludes locations in
														// non-US countries with
														// states i.e Australia
														// / Canada
						.parallel().anyMatch(incident.getState().toUpperCase()::contains))
				.collect(Collectors.toCollection(ArrayList::new));

		int length = incidentsInUsa.size();

		// Each key in the below map is a year, in the range of incidents
		// supplied to the method.
		// The 'value' maps total sightings to their state for the corresponding
		// year in the key.
		TreeMap<Integer, TreeMap<String, ArrayList<Incident>>> sightingsByYearAndState = new TreeMap<Integer, TreeMap<String, ArrayList<Incident>>>();

		for (Incident i : incidentsInUsa) {

			// store the state and year of each specific incident in temp
			// variables
			String state = i.getState().toUpperCase();

			Integer year = getYear(i);

			// add an entry for the year of the incident if none exists already
			sightingsByYearAndState.putIfAbsent(year, new TreeMap<String, ArrayList<Incident>>());

			// add an entry for the state of the incident if none exists already
			sightingsByYearAndState.get(year).putIfAbsent(state, new ArrayList<Incident>());

			// totalSightings is initialised to 1 on the assumption that if this
			// is the first incident
			// in its state and year, no key for it will yet exists, so this
			// incident will be the first
			// of its specific attributes.
			int totalSightings = 1;

			// retrieve the current number of sightings for this incident's
			// state and year
			// (if they exist already)
			if (sightingsByYearAndState.get(year).containsKey(state)) {

				totalSightings = sightingsByYearAndState.get(year).get(state).size() + 1;

			}

			// update the ArrayList for the current year and state with this
			// incident.
			sightingsByYearAndState.get(year).get(state).add(i);

		}

		// this map is used to hold the weighted average (and hence likelihood
		// of further sightings)
		// for each state
		TreeMap<String, Double> weightedAverages = new TreeMap<String, Double>();

		for (Map.Entry<Integer, TreeMap<String, ArrayList<Incident>>> entry : sightingsByYearAndState.entrySet()) {

			int thisYear = entry.getKey();

			// assigns an incremental weight by position in the range
			double weight = (thisYear + 1) - startYear;

			// iterate
			for (Map.Entry<String, ArrayList<Incident>> specificYear : entry.getValue().entrySet()) {

				String state = specificYear.getKey();

				weightedAverages.putIfAbsent(state, 0.0);

				int sightings = specificYear.getValue().size();

				double currentTotal = weightedAverages.get(state);

				double increase = (float) (weight++ / fractionalSumTotal) * sightings;

				weightedAverages.put(state, currentTotal + increase);

			}

		}

		String likeliestState = "";

		double highestValue = 0.0;

		for (Map.Entry<String, Double> entry : weightedAverages.entrySet()) {

			if (entry.getValue() > highestValue) {
				highestValue = entry.getValue();
				likeliestState = entry.getKey();
			}
		}

		return new Statistic("Likeliest State", likeliestState);

	}

	public static Statistic mostCommonShape(ArrayList<Incident> incidentsArr) {

		Map<String, Integer> shapes = new HashMap<String, Integer>();
		Map.Entry<String, Integer> maxEntry = null;
		String shape;

		for (Incident i : incidentsArr) {
			shapes.put(i.getShape(), 0);

		}

		for (Incident i : incidentsArr)
			shapes.computeIfPresent(i.getShape(), (k, v) -> v + 1);

		for (Map.Entry<String, Integer> entry : shapes.entrySet()) {
			if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
				maxEntry = entry;
			}
		}

		shape = maxEntry.getKey();

		return new Statistic("The most common shape of the sightings", shape);

	}

	/**
	 * Returns the state with the least sightings over the given period of time.
	 * 
	 * @param incidentsArr
	 * @return
	 */
	public static Statistic safestState(ArrayList<Incident> incidentsArr) {

		Map<String, Integer> states = new HashMap<String, Integer>();
		Map.Entry<String, Integer> minEntry = null;
		String state;

		// log all the states that have recorded sightings over the given period
		// of time and initializes the HashMap so the values can be incremented
		// in order to track
		// the current number of incidents in each one
		for (Incident i : incidentsArr) {
			states.put(i.getState(), 0);

		}

		// each time there is an incident that has taken place in a specific
		// state, the value corresponding to it will be incremented by 1 in the
		// hashmap
		for (Incident i : incidentsArr)
			states.computeIfPresent(i.getState(), (k, v) -> v + 1);

		// search for the minimum number of sights in a given state, ignoring
		// the ones that are label as "not specified"
		for (Map.Entry<String, Integer> entry : states.entrySet()) {
			if (minEntry == null || entry.getValue().compareTo(minEntry.getValue()) < 0
					&& !minEntry.getValue().equals("Not specified.")) {
				minEntry = entry;
			}
		}

		state = minEntry.getKey();

		return new Statistic("The safest state", state);

	}

	private static int getYear(Incident incident) {

		return Integer.parseInt(incident.getDateAndTime().substring(0, 4));

	}

}
