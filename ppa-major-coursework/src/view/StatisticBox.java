package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import base_statistics.Statistic;
import testView.StatChangeListener;

/**
 * Provides a JPanel that will hold one statistic
 * 
 *
 */
public class StatisticBox extends JPanel {

	private JButton leftButton, rightButton;
	private JLabel statisticTitle, content;
	private Statistic displayedStatistic;
	
	public StatisticBox() {
		
		this.setLayout(new BorderLayout());
		
		leftButton = new JButton("<");
		rightButton = new JButton(">");
		
		statisticTitle = new JLabel("<html><B>Placeholder</B></html>", SwingConstants.CENTER);
		content = new JLabel("1234", SwingConstants.CENTER);
		
		this.add(leftButton, BorderLayout.WEST);
		this.add(rightButton, BorderLayout.EAST);
		this.add(statisticTitle, BorderLayout.NORTH);
		this.add(content, BorderLayout.CENTER);
		
	}
	
	/**
	 * Updates the view to the supplied statistic
	 * @param s
	 */
	public void changeStatTo(Statistic s) {
		
		if (displayedStatistic != null) displayedStatistic.setIsDisplayed(false);
		setContentsFrom(s);
		
	}
	
	/**
	 * Displays the provided statistic by parsing information from the Statistic object
	 * @param statistic
	 */
	public void setContentsFrom(Statistic statistic) {
		
		if (displayedStatistic != null) displayedStatistic.setIsDisplayed(false);
		
		displayedStatistic = statistic;
		
		statisticTitle.setText("<html><B>" + statistic.getTitle() + "</B></html>");
		content.setText(statistic.getContents());
		statistic.setIsDisplayed(true);
		
	}
	
	/**
	 * Adds the supplied listener to the buttons in the view
	 * @param l
	 */
	public void addStatChangeListener(StatChangeListener l) {
		
		leftButton.addActionListener(l);
		rightButton.addActionListener(l);
		
	}
	
}
