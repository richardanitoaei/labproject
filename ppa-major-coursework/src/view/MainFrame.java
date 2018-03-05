package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import base_statistics.ModelWrapper;
import controller.DateListener;


/**
 * 
 * Provides the main frame that holds the CentrePanelsContainer. 
 *
 */
public class MainFrame implements Observer {

	private int startYear, endYear;

	private static ModelWrapper model;
	private static JFrame frame;
	private static CardLayout cardLayout;
	private static CentrePanelsContainer centralPanel;
	private static ButtonsPanel buttonsPanel;
	private static StatisticPanel statisticPanel;
	private static SightingsPanel sightingsPanel;
	private static SurprisePanel surprisePanel;
	private static MapPanel mapPanel;
	private static WelcomePanel wp;


	
	/**
	 * Sets up the widgets and hard-codes the model into the app.
	 * @param model
	 */
	public MainFrame(ModelWrapper model) {

		frame = new JFrame("Application");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setMinimumSize(new Dimension(1024, 650));
		frame.setLocationRelativeTo(null);

		centralPanel = new CentrePanelsContainer();

		buttonsPanel = new ButtonsPanel((CardLayout) centralPanel.getLayout(), centralPanel);
		buttonsPanel.setButtonsEnable(false);

		wp = new WelcomePanel();
		frame.add(buttonsPanel, BorderLayout.SOUTH);

		
		wp.getSubmitButton().addActionListener(new DateListener(this, model));
		sightingsPanel = new SightingsPanel();
		mapPanel = new MapPanel();
		statisticPanel = new StatisticPanel();
		surprisePanel = new SurprisePanel();
        

		centralPanel.addPanel(wp, wp.getName());
		centralPanel.addPanel(mapPanel, mapPanel.getName());
		centralPanel.addPanel(statisticPanel, statisticPanel.getName());
		centralPanel.addPanel(surprisePanel, surprisePanel.getName());
		frame.add(centralPanel);
		

		frame.setVisible(true);
		
		this.model = model;

		
		wp.setInitialContent(model.getVersion());
		buttonsPanel.setText(model.getLastUpdated());

		

		JOptionPane.showMessageDialog(frame, model.getAcknowledgementString(), "Acknowledgement",
				JOptionPane.PLAIN_MESSAGE);

		

	}

	/**
	 * Updates the model.
	 */
	public static void updateModel() {
		model.update();
	}

	/**
	 * Initializes the widgets and updates the model.
	 */
	public static void initWidget() {

		

		updateModel();
		frame.repaint();

	}

	/**
	 * Adds the observer and makes the MainFrame visible
	 * @param args
	 */
	public static void main(String[] args) {

		ModelWrapper mw = new ModelWrapper();
		MainFrame mf = new MainFrame(mw);
		mw.addObserver(mf);
		// mf.setVisible(true);

	}

	@Override
	public void update(Observable o, Object arg) {

		statisticPanel.setStatisticsArr(model.getStatisticsArr());
		mapPanel.setIncidentsArr(model);

	}
	
	/**
	 * Returns the first panel
	 * @return
	 */
	public WelcomePanel getWelcomePanel() {
		
		return wp;
		
	}
	
	/**
	 * Returns the Buttons panel
	 * @return
	 */
	public ButtonsPanel getButtonsPanel() {
		
		return buttonsPanel;
		
	}
	
	/**
	 * Returns the sightings
	 * @return
	 */
	public SightingsPanel getSightingsPanel() {
		
		return sightingsPanel;
		
	}
	
	/**
	 * Returns the frame
	 * @return
	 */
	public JFrame getFrame() {
		
		return frame;
		
	}
	
	/**
	 * Returns the third panel
	 * @return
	 */
	public StatisticPanel getStatisticPanel() {
		
		return statisticPanel;
		
	}
	
}
