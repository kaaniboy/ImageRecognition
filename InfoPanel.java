import java.util.Arrays;

import javax.swing.JPanel;

public class InfoPanel extends JPanel {
	NeuralNetwork net;

	public InfoPanel() {
		net = new NeuralNetwork();
		System.out.println(net);
		System.out.println("Hidden layer results:");
		System.out.println(Arrays.toString(net.run(new double[] { 1, 1, .5, .25, 1 })));
	}

}
