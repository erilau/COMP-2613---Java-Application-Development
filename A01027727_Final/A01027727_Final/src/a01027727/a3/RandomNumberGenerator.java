package a01027727.a3;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class RandomNumberGenerator {
	
	private Set<Integer> randomNumbers;
	
	public RandomNumberGenerator(){
		randomNumbers = new HashSet<>();


		
		for (int i = 0; i < 6; i++) {

			 randomNumbers.add((int) (1+(Math.random() * 49)));

		}
		if(randomNumbers.size() < 6) {
			randomNumbers.add((int) (1+(Math.random() * 49)));
		}
		

	}
	
	public String getRandomNumbers(){
		String numbers = null;
		numbers = Arrays.deepToString(randomNumbers.toArray());
		numbers = numbers.replaceAll("\\p{P}","");
	
		return numbers;
	}
	
	
	
}
