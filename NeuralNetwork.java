import java.util.Random;

public class NeuralNetwork {
	private static int INPUT_SIZE = 5;
	private static int HIDDEN_SIZE = 3;
	private static int OUTPUT_SIZE = 4;
	
	private double[][] weights1 = new int[INPUT_SIZE][HIDDEN_SIZE];
	private double[][] weights2 = new int[HIDDEN_SIZE][OUTPUT_SIZE];
	
	public NeuralNetwork() {
		
	}
	
	private void initializeRandom() {
		Random rand = new Random();
		for(int i = 0; i < INPUT_SIZE; i++) {
			for(int j = 0; j < HIDDEN_SIZE; j++) {
				weights1[i][j] = rand.nextDouble();
			}
		}
		
		for(int i = 0; i < HIDDEN_SIZE; i++) {
			for(int j = 0; j < OUTPUT_SIZE; j++) {
				weights2[i][j] = rand.nextDouble();
			}
		}
	}
	
	public int[] run(int[] input) {
		if(input.length != INPUT_SIZE) throw new IllegalArgumentException();
		
		return null;
	}
	
	@Override
	public String toString() {
		
	}
}
