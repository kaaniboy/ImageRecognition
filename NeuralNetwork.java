import java.text.DecimalFormat;
import java.util.Random;

import Jama.Matrix;

public class NeuralNetwork {
	private static int INPUT_SIZE = 5;
	private static int HIDDEN_SIZE = 3;
	private static int OUTPUT_SIZE = 4;
	
	private Matrix weights1;
	private Matrix weights2;
	private double[][] w1 = new double[HIDDEN_SIZE][INPUT_SIZE];
	private double[][] w2 = new double[OUTPUT_SIZE][HIDDEN_SIZE];
	
	public NeuralNetwork() {
		initializeRandom();
	}
	
	private void initializeRandom() {
		Random rand = new Random();
		for(int i = 0; i < HIDDEN_SIZE; i++) {
			for(int j = 0; j < INPUT_SIZE; j++) {
				w1[i][j] = rand.nextDouble() * 2 - 1;
			}
		}
		
		weights1 = new Matrix(w1);
		
		for(int i = 0; i < OUTPUT_SIZE; i++) {
			for(int j = 0; j < HIDDEN_SIZE; j++) {
				w2[i][j] = rand.nextDouble() * 2 - 1;
			}
		}
		
		weights2 = new Matrix(w2);
	}
	
	public Matrix run(Matrix input) {
		if(input.getColumnDimension() != 1 || input.getRowDimension() != INPUT_SIZE) {
			throw new IllegalArgumentException();
		}
		Matrix hiddenActivations = weights1.times(input);
		elementwiseSigmoid(hiddenActivations);
		
		Matrix outputActivations = weights2.times(hiddenActivations);
		elementwiseSigmoid(outputActivations);
		
		return outputActivations;
	}
	
	private void elementwiseSigmoid(Matrix mat) {
		for(int i = 0; i < mat.getRowDimension(); i++) {
			for(int j = 0; j < mat.getColumnDimension(); j++) {
				mat.set(i, j, sigmoid(mat.get(i, 0)));
			}
		}
	}
	private double sigmoid(double x) {
		return 1/(1 + Math.pow(Math.E, -1 * x));
	}
	
	public void print() {
		System.out.println("Input layer to hidden layer:\n\n");
		
		weights1.print(5, 2);
		
		/*for(int i = 0; i < INPUT_SIZE; i++) {
			for(int j = 0; j < HIDDEN_SIZE; j++) {
				sb.append(df.format(w1[i][j]) + " ");
			}
			sb.append("\n");
		}*/
		
		System.out.println("\nHidden layer to output layer:\n\n");
		
		weights2.print(5, 2);
		
		/*for(int i = 0; i < HIDDEN_SIZE; i++) {
			for(int j = 0; j < OUTPUT_SIZE; j++) {
				sb.append(df.format(weights2[i][j]) + " ");
			}
			sb.append("\n");
		}*/
	}
}
