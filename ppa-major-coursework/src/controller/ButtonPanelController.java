package controller;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import view.CentrePanelsContainer;



public class ButtonPanelController implements ActionListener{
	
	CentrePanelsContainer centralPanel;
	CardLayout cardLayout;
	JButton previousPanel,nextPanel;
	public ButtonPanelController(CentrePanelsContainer centralPanel, CardLayout cardLayout,JButton previousPanel,JButton nextPanel){
		this.centralPanel=centralPanel;
		this.cardLayout = cardLayout;
		this.previousPanel=previousPanel;
		this.nextPanel=nextPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		
		if (e.getActionCommand().contains("next panel")) {

			cardLayout.next(centralPanel);
		}

		else if (e.getActionCommand().contains("previous panel")) {

			cardLayout.previous(centralPanel);

		}

		JPanel card = null;
		for (Component comp : centralPanel.getComponents()) {
			if (comp.isVisible() == true) {
				card = (JPanel) comp;
			}
		}

		switch (card.getName()) {

		case "Panel1":
			previousPanel.setEnabled(false);
			break;

		case "Panel2":
			previousPanel.setEnabled(true);
			nextPanel.setEnabled(true);
			break;

		case "Panel3":
			previousPanel.setEnabled(true);
			nextPanel.setEnabled(true);
			break;

		case "Panel4":
			nextPanel.setEnabled(false);
			break;

		}

	}
		
		
		
	


}
