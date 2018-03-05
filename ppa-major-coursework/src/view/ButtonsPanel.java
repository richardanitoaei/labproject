package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.ButtonPanelController;

@SuppressWarnings("serial")
	/**
	 * 
	 * Provides a JPanel containing the buttons necessary to navigate between
	 * the pages of the cards
	 *
	 */
	public class ButtonsPanel extends JPanel implements ActionListener {

		JButton nextPanel, previousPanel;
		JLabel text;

		CardLayout cardLayout;
		CentrePanelsContainer centralPanel;

		ButtonPanelController buttonController;

		/**
		 * Creates all the necessary widgets and displays them in the proper
		 * order
		 * 
		 * @param cardLayout
		 * @param centralPanel
		 */

		public ButtonsPanel(CardLayout cardLayout, CentrePanelsContainer centralPanel) {

			this.cardLayout = cardLayout;
			this.centralPanel = centralPanel;
			nextPanel = new JButton(" > ");
			previousPanel = new JButton(" < ");
			buttonController = new ButtonPanelController(centralPanel, cardLayout, previousPanel, nextPanel);

			setLayout(new BorderLayout());

			// nextPanel = new JButton(" > ");
			nextPanel.setActionCommand("next panel");
			nextPanel.addActionListener(buttonController);

			// previousPanel = new JButton(" < ");
			previousPanel.setEnabled(false);
			previousPanel.setActionCommand("previous panel");
			previousPanel.addActionListener(buttonController);

			text = new JLabel();
			text.setHorizontalAlignment(JLabel.CENTER);

			add(nextPanel, BorderLayout.EAST);
			add(text, BorderLayout.CENTER);
			add(previousPanel, BorderLayout.WEST);

		}

		/**
		 * Updates the text of the text JLabel
		 * 
		 * @param str
		 */
		public void setText(String str) {
			text.setText(str);
		}

		public void setButtonsEnable(boolean b) {

			nextPanel.setEnabled(b);
			// previousPanel.setEnabled(!b);

		}

		/*
		 * @Override ======= /** Displays the page that is requested by the user
		 * after clicking a button.
		 */
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
