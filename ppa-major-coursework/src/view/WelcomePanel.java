package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.time.Year;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;



@SuppressWarnings("serial")
/**
 * 
 * Provides the welcom screen of the app. It will display all the necessary widgets along with information grabbed from the Ripley API.
 * 
 */
public class WelcomePanel extends JPanel {

	private DateSelection dateSelection;

	private JTextPane panelContent;
	private String firstP = "", secondP = "", thirdP = "", fourthP = "", fifthP = "", sixthP = "";



	/**
	 * Returns the selecte start year and selected end year
	 * @return
	 */
	public int[] getStartAndEndYear() {
		return dateSelection.getStartAndEnd();
	}

	

	/**
	 * Displays the initial information, before grabbing data from the API
	 * @param version
	 */
	public void setInitialContent(double version) {
		
		firstP = "\n\nWelcome to the Ripley API " + version;
		secondP = " \n\nPlease make a selection from the dates above\nto start analysing UFO sightings data.";
		panelContent.setText(firstP);
		panelContent.setText(panelContent.getText() + secondP);
	}

	
	/**
	 * Updates the view to inform the user that the grabbing of infromation process is currently taking place
	 * @param sDate
	 * @param eDate
	 */
	public void setSecondContent(int sDate,int eDate) {
		
		thirdP = " \n\nDate range selected: " + sDate + "-" + eDate + "\n\n";
		fourthP = " \n\nFetching data ... ";
		panelContent.setText("");
		panelContent.setText(firstP+secondP+thirdP+fourthP);
		revalidate();
		

		panelContent.setText("");
		panelContent.setText(firstP);
		panelContent.setText(panelContent.getText() + secondP);
		panelContent.setText(panelContent.getText() + thirdP+fourthP);
	}
	

	/**
	 * Updates the view so it will inform the user about the elapsed time and his options from now on
	 * @param time
	 * @throws BadLocationException
	 */
	public void finalContent(String time) throws BadLocationException {
		fifthP = "\n\nData fetched in " + time;
		sixthP = "\n\n\n\nTo interact with the data, use the buttons\nbelow to switch between panels.";
		
		panelContent.setText("");
		panelContent.setText(firstP+secondP+thirdP+fourthP+fifthP+sixthP);
		
		
	}

	/**
	 * Returns the submit button;
	 * @return
	 */
	public JButton getSubmitButton() {

		return dateSelection.getSubmitButton();

	}

	/**
	 * Creates and alligns all the widgets
	 */
	public WelcomePanel() {

		setLayout(new BorderLayout());
		setName("Panel1");
		dateSelection = new DateSelection(1600, Year.now().getValue());

		add(dateSelection, BorderLayout.NORTH);
		panelContent = new JTextPane();
		panelContent.setEditable(false);
		panelContent.setBackground(new Color(211, 211, 211));
		panelContent.setBounds(30, 30, 50, 50);

		StyledDocument doc = panelContent.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);

		
		panelContent.setText(firstP);
		panelContent.setText(panelContent.getText() + secondP);

		add(panelContent, BorderLayout.CENTER);

	}

}
