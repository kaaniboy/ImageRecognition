import java.text.DecimalFormat;
import java.util.Random;

public class NeuralNetwork {
	private static int INPUT_SIZE = 5;
	private static int HIDDEN_SIZE = 3;
	private static int OUTPUT_SIZE = 4;
	
	private double[][] weights1 = new double[INPUT_SIZE][HIDDEN_SIZE];
	private double[][] weights2 = new double[HIDDEN_SIZE][OUTPUT_SIZE];
	
	public NeuralNetwork() {
		initializeRandom();
	}
	
	private void initializeRandom() {
		Random rand = new Random();
		for(int i = 0; i < INPUT_SIZE; i++) {
			for(int j = 0; j < HIDDEN_SIZE; j++) {
				weights1[i][j] = rand.nextDouble() * 2 - 1;
			}
		}
		
		for(int i = 0; i < HIDDEN_SIZE; i++) {
			for(int j = 0; j < OUTPUT_SIZE; j++) {
				weights2[i][j] = rand.nextDouble() * 2 - 1;
			}
		}
	}
	
	public double[] run(double[] input) {
		if(input.length != INPUT_SIZE) throw new IllegalArgumentException();
		
		
		double[] hidden = new double[HIDDEN_SIZE];
		
		for(int i = 0; i < HIDDEN_SIZE; i++) {
			for(int j = 0; j < INPUT_SIZE; j++) {
				hidden[i] += input[j] * weights1[j][i];
			}
			hidden[i] = sigmoid(hidden[i]);
		}
		
		double[] output = new double[OUTPUT_SIZE];
		
		for(int i = 0; i < OUTPUT_SIZE; i++) {
			for(int j = 0; j < HIDDEN_SIZE; j++) {
				output[i] += hidden[j] * weights2[j][i];
			}
			output[i] = sigmoid(output[i]);
		}
		return output;
	}
	
	private double sigmoid(double x) {
		return 1/(1 + Math.pow(Math.E, -1 * x));
	}
	
	@Override
	public String toString() {
		DecimalFormat df = new DecimalFormat("0.00");
		StringBuilder sb = new StringBuilder();
		sb.append("Input layer to hidden layer:\n\n");
		
		for(int i = 0; i < INPUT_SIZE; i++) {
			for(int j = 0; j < HIDDEN_SIZE; j++) {
				sb.append(df.format(weights1[i][j]) + " ");
			}
			sb.append("\n");
		}
		
		sb.append("\nHidden layer to output layer:\n\n");
		
		for(int i = 0; i < HIDDEN_SIZE; i++) {
			for(int j = 0; j < OUTPUT_SIZE; j++) {
				sb.append(df.format(weights2[i][j]) + " ");
			}
			sb.append("\n");
		}
		
		return sb.toString();
	}
}
