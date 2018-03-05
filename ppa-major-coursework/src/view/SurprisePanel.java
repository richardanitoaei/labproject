package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
/**
 * Provides the last panel that will contain an image that can be customized by prssing on different buttons
 * 
 * @author Malin Stoica
 *
 */
public class SurprisePanel extends JPanel implements ActionListener {

	private JPanel mainPanel;
	private JButton soundTrigger;
	private static String[] clips = { ("res/sound/better.wav"), ("res/sound/funny.wav"), ("res/sound/getaway.wav"),
			("res/sound/iqs.wav"), ("res/sound/job.wav"), ("res/sound/mistaken.wav"), ("res/sound/nuts.wav"),
			("res/sound/sing.wav"), ("res/sound/smarter.wav"), ("res/sound/touch.wav"),
			("res/sound/thugify_sound.wav") };

	private JPanel gifPanel;
	private JPanel rightButtons;
	private JPanel leftButtons;
	private JButton addAK;
	private JButton addHat;
	private JButton addShades;
	private JButton clear;
	private JButton thug;
	private JButton barrelRoll;
	private static JLabel gifLabel;
	private static String[] current;
	private static JButton button;

	private static final String AK = "res/gif/ak/1_ak.png";
	private static final String HAT_AK = "res/gif/hat_ak/1_hat_ak.png";
	private static final String SHADES_AK = "res/gif/shades_ak/1_shades_ak.png";
	private static final String HAT_SHADES_AK = "res/gif/hat_shades_ak/1_hat_shades_ak.png";
	private static final String HAT = "res/gif/hat/1_hat.png";
	private static final String SHADES = "res/gif/shades/1_shades.png";
	private static final String HAT_SHADES = "res/gif/hat_shades/1_hat_shades.png";
	private static final String CLEAR = "res/gif/normal/1.png";
	private static final String AK_ROLL = "res/gif/ak/ak_roll.gif";
	private static final String HAT_AK_ROLL = "res/gif/hat_ak/hat_ak_roll.gif";
	private static final String SHADES_AK_ROLL = "res/gif/shades_ak/shades_ak_roll.gif";
	private static final String HAT_SHADES_AK_ROLL = "res/gif/hat_shades_ak/hat_shades_ak_roll.gif";
	private static final String HAT_ROLL = "res/gif/hat/hat_roll.gif";
	private static final String SHADES_ROLL = "res/gif/shades/shades_roll.gif";
	private static final String HAT_SHADES_ROLL = "res/gif/hat_shades/hat_shades_roll.gif";
	private static final String CLEAR_ROLL = "res/gif/normal/clear_roll.gif";
	private static final String THUG = "res/gif/thugify/thugify.gif";
	private static final String AK_GIF = "res/gif/ak/ak_talking.gif";
	private static final String HAT_AK_GIF = "res/gif/hat_ak/hat_ak_talking.gif";
	private static final String SHADES_AK_GIF = "res/gif/shades_ak/shades_ak_talking.gif";
	private static final String HAT_SHADES_AK_GIF = "res/gif/hat_shades_ak/hat_shades_ak_talking.gif";
	private static final String HAT_GIF = "res/gif/hat/hat_talking.gif";
	private static final String SHADES_GIF = "res/gif/shades/shades_talking.gif";
	private static final String HAT_SHADES_GIF = "res/gif/hat_shades/hat_shades_talking.gif";
	private static final String CLEAR_GIF = "res/gif/normal/clear_talking.gif";

	public SurprisePanel() {

		setName("Panel4");
		soundTrigger = new JButton("Starty the Marty Party");

		soundTrigger.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				button = (JButton) arg0.getSource();
				playAudio(false);

			}

		});

		current = new String[1];

		leftButtons = new JPanel();
		rightButtons = new JPanel();
		gifPanel = new JPanel();
		gifLabel = new JLabel();

		addAK = new JButton("+1 Power");
		addAK.addActionListener(this);
		addShades = new JButton("+1 Vision");
		addShades.addActionListener(this);
		addHat = new JButton("+1 Appearance");
		addHat.addActionListener(this);

		leftButtons.setLayout(new GridLayout(3, 1));

		leftButtons.add(addAK);
		leftButtons.add(addShades);
		leftButtons.add(addHat);

		barrelRoll = new JButton("+1 Ability");
		barrelRoll.addActionListener(this);
		clear = new JButton("Don't press this");
		clear.addActionListener(this);
		thug = new JButton("THUGIFY");
		thug.addActionListener(this);

		rightButtons.setLayout(new GridLayout(3, 1));

		rightButtons.add(barrelRoll);
		rightButtons.add(clear);
		rightButtons.add(thug);

		gifPanel.setLayout(new FlowLayout());
		Icon normal = new ImageIcon("res/gif/normal/1.png");
		current[0] = "clear";
		gifLabel.setIcon(normal);
		gifPanel.add(gifLabel);

		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());

		mainPanel.add(soundTrigger, BorderLayout.NORTH);
		mainPanel.add(leftButtons, BorderLayout.EAST);
		mainPanel.add(rightButtons, BorderLayout.WEST);
		mainPanel.add(gifPanel, BorderLayout.CENTER);

		this.add(mainPanel);
		
	}



	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == addAK) {
			customize("ak", false);
		} else if (arg0.getSource() == addHat) {
			customize("hat", false);
		} else if (arg0.getSource() == addShades) {
			customize("shades", false);
		} else if (arg0.getSource() == clear) {
			customize("clear", false);
		} else if (arg0.getSource() == barrelRoll) {
			customize("barrelRoll", false);
		} else if (arg0.getSource() == thug) {
			customize("thug", false);
		}
	}

	/**
	 * analyzies what is already on screen and than updates accordingly 
	 * @param input
	 * @param condition
	 */
	static void customize(String input, boolean condition) {

		gifLabel.removeAll();
		if (!condition)
			switch (input) {
			case "ak":
				switch (current[0]) {
				case "clear":
					current[0] = "ak";
					gifLabel.setIcon(new ImageIcon(AK));
					break;
				case "hat":
					current[0] = "hat_ak";
					gifLabel.setIcon(new ImageIcon(HAT_AK));
					break;
				case "shades":
					current[0] = "shades_ak";
					gifLabel.setIcon(new ImageIcon(SHADES_AK));
					break;
				case "hat_shades":
					current[0] = "hat_shades_ak";
					gifLabel.setIcon(new ImageIcon(HAT_SHADES_AK));
					break;

				default:
					gifLabel.setIcon(new ImageIcon(AK));
				}
				break;

			case "hat":
				switch (current[0]) {
				case "clear":
					current[0] = "hat";
					gifLabel.setIcon(new ImageIcon(HAT));
					break;
				case "ak":
					current[0] = "hat_ak";
					gifLabel.setIcon(new ImageIcon(HAT_AK));
					break;
				case "shades":
					current[0] = "hat_shades";
					gifLabel.setIcon(new ImageIcon(HAT_SHADES));
					break;
				case "shades_ak":
					current[0] = "hat_shades_ak";
					gifLabel.setIcon(new ImageIcon(HAT_SHADES_AK));
					break;
				default:
					gifLabel.setIcon(new ImageIcon(HAT));
				}
				break;

			case "shades":
				switch (current[0]) {
				case "clear":
					current[0] = "shades";
					gifLabel.setIcon(new ImageIcon(SHADES));
					break;
				case "hat":
					current[0] = "hat_shades";
					gifLabel.setIcon(new ImageIcon(HAT_SHADES));
					break;
				case "ak":
					current[0] = "shades_ak";
					gifLabel.setIcon(new ImageIcon(SHADES_AK));
					break;
				case "shades_ak":
					current[0] = "hat_shades_ak";
					gifLabel.setIcon(new ImageIcon(HAT_SHADES_AK));
					break;
				default:
					gifLabel.setIcon(new ImageIcon(SHADES));

				}
				break;
				
			case "hat_shades" :
				gifLabel.setIcon(new ImageIcon(HAT_SHADES));
				break;
			
			case "hat_ak" : 
				gifLabel.setIcon(new ImageIcon(HAT_AK));
				break;
			
			case "shades_ak" :
				gifLabel.setIcon(new ImageIcon(SHADES_AK));
				break;
			
			case "hat_shades_ak" :
				gifLabel.setIcon(new ImageIcon(HAT_SHADES_AK));
				break; 
				
				
			case "clear":

				current[0] = "clear";
				gifLabel.setIcon(new ImageIcon(CLEAR));
				break;

			case "barrelRoll":

				switch (current[0]) {

				case "clear":

					gifLabel.setIcon(new ImageIcon(CLEAR_ROLL));
					break;

				case "hat":

					gifLabel.setIcon(new ImageIcon(HAT_ROLL));
					break;

				case "shades":

					gifLabel.setIcon(new ImageIcon(SHADES_ROLL));
					break;

				case "ak":

					gifLabel.setIcon(new ImageIcon(AK_ROLL));
					break;

				case "hat_shades":

					gifLabel.setIcon(new ImageIcon(HAT_SHADES_ROLL));
					break;

				case "shades_ak":

					gifLabel.setIcon(new ImageIcon(SHADES_AK_ROLL));
					break;

				case "hat_ak":

					gifLabel.setIcon(new ImageIcon(HAT_AK_ROLL));
					break;

				case "hat_shades_ak":

					gifLabel.setIcon(new ImageIcon(HAT_SHADES_AK_ROLL));
					break;

				default:

				}
				break;

			case "thug":
				gifLabel.setIcon(new ImageIcon(THUG));
				playAudio(true);
				break;

			default:

			}
		if (condition)
			switch (current[0]) {

			case "clear":

				gifLabel.setIcon(new ImageIcon(CLEAR_GIF));
				break;

			case "hat":

				gifLabel.setIcon(new ImageIcon(HAT_GIF));
				break;

			case "shades":

				gifLabel.setIcon(new ImageIcon(SHADES_GIF));
				break;

			case "ak":

				gifLabel.setIcon(new ImageIcon(AK_GIF));
				break;

			case "hat_shades":

				gifLabel.setIcon(new ImageIcon(HAT_SHADES_GIF));
				break;

			case "shades_ak":

				gifLabel.setIcon(new ImageIcon(SHADES_AK_GIF));
				break;

			case "hat_ak":

				gifLabel.setIcon(new ImageIcon(HAT_AK_GIF));
				break;

			case "hat_shades_ak":

				gifLabel.setIcon(new ImageIcon(HAT_SHADES_AK_GIF));
				break;
			
			case "thug" : 
				
				gifLabel.setIcon(new ImageIcon(THUG));
			
			default:

			}

		gifLabel.repaint();

	}

	/**
	 * plays an audio file. condition determines if it s the thug button
	 * @param condition
	 */
	static void playAudio(boolean condition) {

		int index = (int) (0 + Math.random() * 10);
		
		if(condition){
			current[0] = "thug";
			index = 10;
		}

		try (AudioInputStream in = AudioSystem.getAudioInputStream(new File(clips[index]))) {

			Clip clip = AudioSystem.getClip();
			clip.open(in);

			Thread playGif = new Thread(new Runnable() {

				@Override
				public void run() {

					customize(current[0], true);

				}

			});
			
			if(index == 10 && condition)
				clip.start();
			else {
				playGif.start();
				clip.start();
			}

		

			button.setEnabled(false);

			clip.addLineListener(new LineListener() {
				public void update(LineEvent evt) {
					if (evt.getType() == LineEvent.Type.STOP) {
						customize(current[0], false);
						button.setEnabled(true);
					}
				}
			});

		} catch (LineUnavailableException e1) {
			e1.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		} catch (UnsupportedAudioFileException e2) {
			e2.printStackTrace();
		}

	}
}
