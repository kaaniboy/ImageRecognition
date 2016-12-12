import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.Timer;

import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.javacv.OpenCVFrameGrabber;
import org.bytedeco.javacv.Frame;

import static org.bytedeco.javacpp.opencv_core.*;
import static org.bytedeco.javacpp.opencv_imgproc.*;
import static org.bytedeco.javacpp.opencv_objdetect.*;

public class WebcamPanel extends JPanel {
	IplImage grabbedImage;
	OpenCVFrameGrabber grabber = new OpenCVFrameGrabber("");
	OpenCVFrameConverter.ToIplImage grabberConverter = new OpenCVFrameConverter.ToIplImage();
	Java2DFrameConverter paintConverter = new Java2DFrameConverter();
	
	private static final int IMAGE_WIDTH = 500;
	private static final int IMAGE_HEIGHT = 375;

	public WebcamPanel() {
		setSize(new Dimension(IMAGE_WIDTH, IMAGE_HEIGHT));
		
		grabber.setImageWidth(IMAGE_WIDTH);
		grabber.setImageHeight(IMAGE_HEIGHT);
		
		try {
			grabber.start();
		} catch (org.bytedeco.javacv.FrameGrabber.Exception e) {
			e.printStackTrace();
		}
		
		Timer timer = new Timer(100, new BackgroundImageGrabber());
		timer.start();
		setBackground(Color.BLACK);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (grabbedImage == null)
			return;

		Frame frame = grabberConverter.convert(grabbedImage);
		BufferedImage image = paintConverter.getBufferedImage(frame, 2.2 / grabber.getGamma());
		Graphics2D imageGraphics = image.createGraphics();
		imageGraphics.drawString("Webcam", 20, 20);
		g.drawImage(image, 0, 0, null);

	}

	private class BackgroundImageGrabber implements ActionListener {

		OpenCVFrameConverter.ToIplImage grabberConverter = new OpenCVFrameConverter.ToIplImage();
		Java2DFrameConverter paintConverter = new Java2DFrameConverter();

		@Override
		public void actionPerformed(ActionEvent event) {
			try {
				grabbedImage = grabberConverter.convert(grabber.grab());
				repaint();
			} catch (Exception e) {
				System.out.println("Exception in BackgroundImageGrabber");
				e.printStackTrace();
			}

		}
	}
}
