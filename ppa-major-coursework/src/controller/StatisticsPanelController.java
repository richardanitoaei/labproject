package controller;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class StatisticsPanelController implements ActionListener {

	CardLayout cardLayout;
	JPanel statsPanel;

	public StatisticsPanelController(CardLayout cardLayout, JPanel statsPanel) {
		this.cardLayout = cardLayout;
		this.statsPanel = statsPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().contains("next stats")) {

			cardLayout.next(statsPanel);
		}

		else if (e.getActionCommand().contains("previous stats")) {

			cardLayout.previous(statsPanel);

		}

	}

}
