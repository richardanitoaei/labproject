package testView;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class SoundTriggerPanel extends JFrame {
	
	private JPanel mainPanel;
	private JTextPane explanation;
	private JButton soundTrigger;
	private String[] clips = {
		("res/sound/better.wav"),
		//("res/sound/funny.wav"),
		//("res/sound/getaway.wav"),
		("res/sound/iqs.wav"),
		("res/sound/job.wav"),
		//("res/sound/mistaken.wav"),
		//("res/sound/nuts.wav"),
		//("res/sound/sing.wav"),
		("res/sound/smarter.wav"),
		("res/sound/touch.wav")
	};
	
	private int index = 0;
	
	public SoundTriggerPanel() {
		
		soundTrigger = new JButton("Starty the Marty Party");
		soundTrigger.addActionListener(new ActionListener() {
			
			boolean active = true;
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (!active) return;
				
				Random random = new Random();
				
				
				
			
				
				try (AudioInputStream in = AudioSystem.getAudioInputStream(new File(clips[index]))) {
				
					Clip clip = AudioSystem.getClip();
					clip.open(in);
					
					AudioFormat format = in.getFormat();
				    long frames = in.getFrameLength();
				    double durationInSeconds = (frames + 0.0) / format.getFrameRate();
					clip.start();
					final JButton button = (JButton) e.getSource();
					
					button.setEnabled(false);
					
					Thread.sleep((long) (durationInSeconds * 1000));
					button.setEnabled(true);
					
				} catch (LineUnavailableException e1) {
					e1.printStackTrace();
				} catch (IOException e2) {
					e2.printStackTrace();
				} catch (UnsupportedAudioFileException e2) {
					e2.printStackTrace();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				
				index = (index + 1) % clips.length;
				
			}
			
		});
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		explanation = new JTextPane();
		explanation.setText("Unsatisfied with the automatic marker, we decided to make our own..."
				+ "\nPress the button below for instant and in-depth feedback on your work!"
				+ "\nWarning: Feedback will be delivered solely via Alien quotes.");
		
		StyledDocument style = explanation.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		style.setParagraphAttributes(0, style.getLength(), center, false);
		
		explanation.setBackground(mainPanel.getBackground());
		explanation.setEditable(false);
		mainPanel.add(explanation, BorderLayout.CENTER);
		mainPanel.add(soundTrigger, BorderLayout.SOUTH);
		this.add(mainPanel);
		this.setSize(600, 400);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		
		SoundTriggerPanel stp = new SoundTriggerPanel();
		
	}
	
}
