import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Frame extends JFrame {
	private static final int FRAME_WIDTH = 1000;
	private static final int FRAME_HEIGHT = 375;
	public Frame() {
		setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		setTitle("Neural Network");
		
		setLayout(new GridLayout(1,2));
		
		WebcamPanel camPanel = new WebcamPanel();
		add(camPanel);
		add(new InfoPanel());
	}
}
