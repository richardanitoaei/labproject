package base_statistics;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.stream.Collectors;

import api.ripley.Incident;

public class Serializer {
	
	private static String startDate;
	private static String endDate;
	
	private Serializer() {
		
		
	}
	
	public static void write(ArrayList<Incident> incidentsArr) {
		
		ArrayList<SerializableIncident> incidentsSer = new ArrayList<SerializableIncident>();
		
		incidentsArr.stream()
				.forEach(i -> incidentsSer.add(new SerializableIncident(i)));
		

		
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("res/data.ser"))) {
	        
			out.writeObject(incidentsSer);
			
		} catch (IOException e) {

			e.printStackTrace();
			
		}
		
	}
	
	public static ArrayList<Incident> read() {
		
		ArrayList<SerializableIncident> incidentsSer = new ArrayList<SerializableIncident>();
		ArrayList<Incident> incidentsArr = new ArrayList<Incident>();
		
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("res/data.ser"))) {
			
			incidentsSer = (ArrayList<SerializableIncident>)in.readObject();
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
			
		}
		
		incidentsSer.stream().forEach(i -> incidentsArr.add(
				new InstantiableIncident(
				i.getIncidentID(), i.getDateAndTime(), i.getCity(), i.getState(), 
				i.getShape(), i.getDuration(), i.getSummary(), i.getPosted())));
		
		
		return incidentsArr.stream()
				.map(i -> (Incident)i)
				.collect(Collectors.toCollection(ArrayList::new));
		
	}
}
