package a01027727.b1;

public class CountEachLetter {
	private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
	private static int min;
	private static int max;
	
	
	
	public static String output(String inputString) {
		int totalNumberOfLetter = 0;
		final int[] counts = countLetters(inputString.toLowerCase());

		String entered ="You Entered: " + inputString + "\n";;
		String output = "";
		String total = "";
		for (int i = 0; i < counts.length; i++) {
			if (counts[i] != 0) {
				
				output += (char) ('a' + i) + " appears  " + counts[i] + ((counts[i] == 1) ? " time\n" : " times\n");
				totalNumberOfLetter += counts[i];
				total = "Total Letters: " + totalNumberOfLetter;
			}
		}
		return entered + output + total;
		
	}
	
	public static String countMinimumWordLength(String input) {

		input = input.replace(".", "");
        String [] word = input.split(" ");
        String shortestword = word[0];
        for(int i = 0; i < word.length; i++){
        	if(word[i].length() < shortestword.length()){
        		shortestword = word[i];
      	  } 
        }
        min = shortestword.length();
		return String.valueOf(min);
		
	}
	
	public static String countAverageWordLength(String input) {

		input = input.replace(".", "");
		String [] words = input.split(" ");
		double average = 0;
		for(int i = 0; i < words.length; i++) {
			average += words[i].length();
		}
		
		average = average / words.length;

		
		return String.valueOf(average);
	}
	
	public static String countMaximumWordLength(String input) {

		input = input.replace(".", "");
        String [] word = input.split(" ");
        
        String maxlethWord = "";
        for(int i = 0; i < word.length; i++){
        	if(word[i].length() >= maxlethWord.length()){
        		maxlethWord = word[i];
      	  } 
        }
      max = maxlethWord.length();
		return String.valueOf(max);
		
	}
	
	public static String countWords(String lowerCase) {
		String [] words = lowerCase.split(" ");
		int count = 0;
		for(int i = 0; i < words.length; i++) {
			count++;
		}
		return String.valueOf(count);
	}


	private static int[] countLetters(String s) {
		int[] counts = new int[ALPHABET.length()];
	
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (Character.isLetter(c)) {
				int countsIndex = c - 'a';
				counts[countsIndex]++;
			}
		}
	
		return counts;
	}
}
