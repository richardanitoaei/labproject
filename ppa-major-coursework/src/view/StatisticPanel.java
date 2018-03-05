package view;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import api.ripley.Incident;
import base_statistics.Statistic;
import testView.StatChangeListener;

/**
 * Displays a number of StatisticBoxes simultaneously, and coordinates them such that
 * no two StatisticBoxes display the same statistic at the same time
 * @author patrick
 * @see Statistic
 * @see StatisticBox
 */
public class StatisticPanel extends JPanel {

	private ArrayList<Statistic> statisticsArr;
	private StatChangeListener scl;
	private StatisticBox[] statBoxArr;
	
	/**
	 * Creates a statistics array and sets the panels layout manager
	 * @param statisticsArr
	 */
	public StatisticPanel(ArrayList<Statistic> statisticsArr) {
		
		this.statisticsArr = statisticsArr;
		this.setLayout(new GridLayout(2, 2, 20, 20));
		
	}
	
	/**
	 * Sets the panels layout manager
	 */
	public StatisticPanel() {
		this.setLayout(new GridLayout(2, 2, 20, 20));
		setName("Panel3");

	}
	
	/**
	 * Adds a StatChangeListener to each StatisticBox stored in the array
	 * @param l
	 */
	public void addChangeListener(StatChangeListener l) {
		
		for (StatisticBox box : statBoxArr) box.addStatChangeListener(l);
		
	}
	
	/**
	 * Instances an array of StatisticBox and fills them from an array of Statistic
	 * @param statisticsArr
	 */
	public void setStatisticsArr(ArrayList<Statistic> statisticsArr) {
		
		this.statisticsArr = statisticsArr;
		
		statBoxArr = new StatisticBox[4];
		
		this.removeAll();
		
		for (int i = 0; i < 4; i++) {
			
			statBoxArr[i] = new StatisticBox();
			statBoxArr[i].changeStatTo(statisticsArr.get(i));
			statBoxArr[i].addStatChangeListener(scl);
			this.add(statBoxArr[i]);
			
		}
	}
	
}
