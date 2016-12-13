import java.util.Arrays;

import javax.swing.JPanel;

import Jama.Matrix;

public class InfoPanel extends JPanel {
	NeuralNetwork net;

	public InfoPanel() {
		net = new NeuralNetwork();
		net.print();
		/* System.out.println("Hidden layer results:"); */
		Matrix input = new Matrix(new double[][] { { 1, 1, .5, .25, 1 } }).transpose();
		System.out.println("\nOutput:\n");
		net.run(input).print(5, 2);
	}

}
