package view;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Year;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * 
 * Provides the Date Selection option in the main screen. It is formed from two
 * JComboBoxes that take values from the Ripley API
 *
 */
public class DateSelection extends JPanel {

	private JPanel contentPanel;

	private int selectedStart, selectedEnd;
	private DefaultComboBoxModel<Integer> startComboBoxModel, endComboBoxModel;
	private DefaultListModel<Integer> dates;
	private JComboBox<Integer> startDateSelector, endDateSelector;
	private JLabel fromLabel, toLabel;
	private JButton submitDateSelection;

	/**
	 * Creates and updates the JComboBoxes accoring to the information parsed
	 * from the Riplay API
	 * 
	 * @param minimumDate
	 *            minimum date displayed the the widget
	 * @param maximumDate
	 *            maximum date displayed by the widget
	 */
	public DateSelection(int minimumDate, int maximumDate) {

		this.setLayout(new BorderLayout());
		this.selectedStart = minimumDate;

		dates = new DefaultListModel<Integer>();

		startDateSelector = new JComboBox<>();
		startDateSelector.setActionCommand("startDateCB");
		// startDateSelector.addActionListener(this);

		endDateSelector = new JComboBox<Integer>();
		endDateSelector.setActionCommand("endDateCB");
		// endDateSelector.addActionListener(this);

		fromLabel = new JLabel("From:");
		fromLabel.setLabelFor(startDateSelector);

		toLabel = new JLabel("To:");
		toLabel.setLabelFor(endDateSelector);

		startComboBoxModel = new DefaultComboBoxModel<Integer>();
		endComboBoxModel = new DefaultComboBoxModel<Integer>();

		for (int year = selectedStart; year < Year.now().getValue();) {

			int thisYear = ++year;
			startComboBoxModel.addElement(thisYear);
			endComboBoxModel.addElement(maximumDate - (thisYear - minimumDate) + 1);
		}

		startDateSelector.setModel(startComboBoxModel);
		endDateSelector.setModel(endComboBoxModel);

		submitDateSelection = new JButton("Submit choices");

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.rowHeights = new int[] { 20 };
		gridBagLayout.columnWeights = new double[] { 0.0, 2.0, 0.0, 2.0, 0.5 };
		gridBagLayout.rowWeights = new double[] { 0.0 };

		contentPanel = new JPanel(gridBagLayout);

		GridBagLayout panelGridBagLayout = new GridBagLayout();

		GridBagConstraints labelConstraints = new GridBagConstraints();

		labelConstraints.fill = GridBagConstraints.BOTH;
		labelConstraints.insets = new Insets(5, 5, 5, 5);

		labelConstraints.gridx = 0;
		labelConstraints.gridy = 0;
		contentPanel.add(fromLabel, labelConstraints);

		labelConstraints.gridx = 1;
		contentPanel.add(startDateSelector, labelConstraints);

		labelConstraints.gridx = 3;
		contentPanel.add(toLabel, labelConstraints);

		labelConstraints.gridx = 4;
		contentPanel.add(endDateSelector, labelConstraints);

		labelConstraints.gridx = 5;
		contentPanel.add(submitDateSelection, labelConstraints);

		add(contentPanel, BorderLayout.CENTER);

	}

	

	/**
	 * Returns the selected start year and selected end year
	 * 
	 * @return
	 */
	public int[] getStartAndEnd() {

		return new int[] { (int) startDateSelector.getSelectedItem(), (int) endDateSelector.getSelectedItem() };

	}

	/**
	 * Returns the Submit button
	 * 
	 * @return
	 */
	public JButton getSubmitButton() {

		return submitDateSelection;
	}
}
