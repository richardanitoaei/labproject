package testView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

import javax.swing.JButton;

import base_statistics.Statistic;
import view.StatisticBox;

public class StatChangeListener implements ActionListener {
	
	private ArrayList<Statistic> statisticsArr;
	private Random random;
	
	public StatChangeListener(ArrayList<Statistic> statisticsArr) {
		
		random = new Random();
		
		this.statisticsArr = statisticsArr;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		ArrayList<Statistic> nonDisplayedStats = statisticsArr.stream()
				.filter(stat -> !stat.getIsDisplayed())
				.collect(Collectors.toCollection(ArrayList::new));
		
		JButton source = (JButton) e.getSource();
				
		StatisticBox sourceBox = (StatisticBox) source.getParent();
		
		sourceBox.changeStatTo(nonDisplayedStats.get(random.nextInt(nonDisplayedStats.size())));
		
	}

}
