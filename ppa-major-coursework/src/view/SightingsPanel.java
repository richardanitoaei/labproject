package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import api.ripley.Incident;
import base_statistics.ModelWrapper;
import base_statistics.Sort;

/**
 * 
 * Dislays a pop up with all the sightings and when clicking on one of the sightings it provides more information.
 *
 */
public class SightingsPanel extends JPanel implements MouseListener{

	JPanel sortPanel;
	JComboBox<String> sortBy;
	private String[] sort = { "-", "Date", "City", "Shape", "Duration", "Posted" };
	private DefaultListModel<String> sightings;
	private JList sightingsList;
	private JScrollPane scrollPane;
	private ArrayList<Incident> incidentsArr;
	private ModelWrapper mw;


	ArrayList<String> test = new ArrayList<>();

	public SightingsPanel() {
		
		setLayout(new BorderLayout());
		sightingsList = new JList<>();
		sightings = new DefaultListModel<String>();
		
		mw = new ModelWrapper();
		
		sightingsList.setModel(sightings);
		
		//Add Mouse Listener to show details as user double click on any Incident
		sightingsList.addMouseListener(this);
		///
				
		sortPanel = new JPanel(new GridLayout(1, 1));
		sortBy = new JComboBox<>(sort);
		sortBy.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				String sortType = (String)sortBy.getSelectedItem();
				switch (sortType) {
				
				case "Date":
					Sort.byDate(incidentsArr);
					addSightsings(incidentsArr);
					break;
					
				case "City":
					Sort.byCity(incidentsArr);
					addSightsings(incidentsArr);
					break;
					
				case "Shape":
					Sort.byShape(incidentsArr);
					addSightsings(incidentsArr);
					break;
					
				case "Duration":
					Sort.byDuration(incidentsArr);
					addSightsings(incidentsArr);
					break;
					
				case "Posted":
					Sort.byPosted(incidentsArr);
					addSightsings(incidentsArr);
					break;
					
				default:
					break;
				}
				
			}
			
		});
		sortPanel.add(sortBy);

		add(sortPanel, BorderLayout.NORTH);
		
		scrollPane = new JScrollPane(sightingsList);
		
		add(scrollPane, BorderLayout.CENTER);

	}

	/**
	 * Updates the local array of incidents with the one supplied 
	 * @param incidentsArr
	 */
	public void addSightsings(ArrayList<Incident> incidentsArr) {
		
		this.incidentsArr = incidentsArr;
		sightingsList.setModel(ModelWrapper.getListModelFrom(incidentsArr));
		
		//mw.update();

	}

	@Override
	public void mouseClicked(MouseEvent e) {

		if (e.getClickCount() == 2) {
			JList list = (JList) e.getSource();
			DefaultListModel<String> listModel = (DefaultListModel<String>)list.getModel();
			int selectedIndex = list.getSelectedIndex();
			if (selectedIndex != -1) { // -1 if there's no selection
			    //model.remove(listModel.remove(selectedIndex));

				JOptionPane.showMessageDialog(this, incidentsArr.get(selectedIndex).getSummary()/*listModel.getElementAt(selectedIndex)*/, "Incident detail",
						JOptionPane.PLAIN_MESSAGE);


			    
			}
	        
	     }
		
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
	
	
	
	

}
