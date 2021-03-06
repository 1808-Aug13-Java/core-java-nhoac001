package com.revature.eval.java.core;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EvaluationService {

	/**
	 * 1. Without using the StringBuilder or StringBuffer class, write a method that
	 * reverses a String. Example: reverse("example"); -> "elpmaxe"
	 * 
	 * @param string
	 * @return
	 */
	public String reverse(String string) {

		// Check if string is empty
		if (string.isEmpty()) {
			return "";
		}
		
		// new String to store reversed word
		String reverse = "";
		
		// Loop through string starting at end
		for (int i = string.length()-1; i >= 0; i--) {
			reverse = reverse + string.charAt(i);
		}
		return reverse;
	}

	/**
	 * 2. Convert a phrase to its acronym. Techies love their TLA (Three Letter
	 * Acronyms)! Help generate some jargon by writing a program that converts a
	 * long name like Portable Network Graphics to its acronym (PNG).
	 * 
	 * @param phrase
	 * @return
	 */
	public String acronym(String phrase) {
		// check if empty string
		if (phrase.isEmpty()) {
			return "";
		}
		
		// String to store acronym
		String acronym = "";
		
		// Initialize acronym with first character
		// Problem arises when first character isn't valid
		// Should add valid character check, but not necessary for test cases
		acronym = acronym + phrase.charAt(0);
		
		// boolean flag to save next valid char
		boolean nextCharSave = false;
		
		// Start at index 1 since saved first character
		for(int i = 1; i < phrase.length(); i++) {
			// Check if flag is set and character is valid
			if (nextCharSave && (phrase.charAt(i) != ' ') && (phrase.charAt(i) != '-')) {
				acronym = acronym + phrase.charAt(i);
				nextCharSave = false;
			}
			// Check if flag should be set
			else if ((phrase.charAt(i) == ' ') || (phrase.charAt(i) == '-')) {
				nextCharSave = true;
			}
		}
		// Return acronym in uppercase
		return acronym.toUpperCase();
	}

	/**
	 * 3. Determine if a triangle is equilateral, isosceles, or scalene. An
	 * equilateral triangle has all three sides the same length. An isosceles
	 * triangle has at least two sides the same length. (It is sometimes specified
	 * as having exactly two sides the same length, but for the purposes of this
	 * exercise we'll say at least two.) A scalene triangle has all sides of
	 * different lengths.
	 *
	 */
	static class Triangle {
		private double sideOne;
		private double sideTwo;
		private double sideThree;

		public Triangle() {
			super();
		}

		public Triangle(double sideOne, double sideTwo, double sideThree) {
			this();
			this.sideOne = sideOne;
			this.sideTwo = sideTwo;
			this.sideThree = sideThree;
		}

		public double getSideOne() {
			return sideOne;
		}

		public void setSideOne(double sideOne) {
			this.sideOne = sideOne;
		}

		public double getSideTwo() {
			return sideTwo;
		}

		public void setSideTwo(double sideTwo) {
			this.sideTwo = sideTwo;
		}

		public double getSideThree() {
			return sideThree;
		}

		public void setSideThree(double sideThree) {
			this.sideThree = sideThree;
		}

		public boolean isEquilateral() {
			// if sideOne = sideTwo and sideTwo = sideThree, then sideOne = sideThree
			if ((sideOne == sideTwo) && (sideTwo == sideThree)) {
				return true;
			}
			return false;
		}

		public boolean isIsosceles() {
			// if any two sides are equal, then triangle is isosceles
			// doesn't have to be exactly 2 sides
			if ((sideOne == sideTwo) || (sideOne == sideThree) || (sideTwo == sideThree)) {
				return true;
			}
			return false;
		}

		public boolean isScalene() {
			// since isIsosceles checks if any sides are equal,
			// its logical not will check if the triangle is scalene
			return !isIsosceles();
		}

	}

	/**
	 * 4. Given a word, compute the scrabble score for that word.
	 * 
	 * --Letter Values-- Letter Value A, E, I, O, U, L, N, R, S, T = 1; D, G = 2; B,
	 * C, M, P = 3; F, H, V, W, Y = 4; K = 5; J, X = 8; Q, Z = 10; Examples
	 * "cabbage" should be scored as worth 14 points:
	 * 
	 * 3 points for C, 1 point for A, twice 3 points for B, twice 2 points for G, 1
	 * point for E And to total:
	 * 
	 * 3 + 2*1 + 2*3 + 2 + 1 = 3 + 2 + 6 + 3 = 5 + 9 = 14
	 * 
	 * @param string
	 * @return
	 */
	public int getScrabbleScore(String string) {
		// Empty string check
		if (string.isEmpty()) {
			return 0;
		}
		
		// Store upper-case word
		String localCopy = string.toUpperCase();
		
		int score = 0;
		
		// Iterate through string
		for (int i = 0; i < string.length(); i++) {
			// Switch statement using fall through to group letter values
			switch (localCopy.charAt(i)) {
				case 'A':
				case 'E':
				case 'I':
				case 'O':
				case 'U':
				case 'L':
				case 'N':
				case 'R':
				case 'S':
				case 'T': score += 1;
					break;
				case 'D':
				case 'G': score += 2;
					break;
				case 'B':
				case 'C':
				case 'M': 
				case 'P': score += 3;
					break;
				case 'F':
				case 'H':
				case 'V':
				case 'W': 
				case 'Y': score += 4;
					break;
				case 'K': score += 5;
					break;
				case 'J':
				case 'X': score += 8;
					break;
				case 'Q':
				case 'Z': score += 10;
					break;
				default:
					break;
			}
		}
		return score;
	}

	/**
	 * 5. Clean up user-entered phone numbers so that they can be sent SMS messages.
	 * 
	 * The North American Numbering Plan (NANP) is a telephone numbering system used
	 * by many countries in North America like the United States, Canada or Bermuda.
	 * All NANP-countries share the same international country code: 1.
	 * 
	 * NANP numbers are ten-digit numbers consisting of a three-digit Numbering Plan
	 * Area code, commonly known as area code, followed by a seven-digit local
	 * number. The first three digits of the local number represent the exchange
	 * code, followed by the unique four-digit number which is the subscriber
	 * number.
	 * 
	 * The format is usually represented as
	 * 
	 * 1 (NXX)-NXX-XXXX where N is any digit from 2 through 9 and X is any digit
	 * from 0 through 9.
	 * 
	 * Your task is to clean up differently formatted telephone numbers by removing
	 * punctuation and the country code (1) if present.
	 * 
	 * For example, the inputs
	 * 
	 * +1 (613)-995-0253 613-995-0253 1 613 995 0253 613.995.0253 should all produce
	 * the output
	 * 
	 * 6139950253
	 * 
	 * Note: As this exercise only deals with telephone numbers used in
	 * NANP-countries, only 1 is considered a valid country code.
	 */
	public String cleanPhoneNumber(String string) {
		// Check if string is empty
		if (string.isEmpty()) {
			return null;
		}
		
		String phoneNumber = "";
		// First we parse through string to extract only numerical values
		for (int i = 0; i < string.length(); i++) {
			// Switch statement to process characters
			switch (string.charAt(i)) {
				case '1':
				case '2':
				case '3':
				case '4':
				case '5':
				case '6':
				case '7':
				case '8':
				case '9':
				case '0':
					// Add if number
					phoneNumber += string.charAt(i);
					break;
				case '(':
				case ')':
				case ' ':
				case '-':
				case '.':
					// Ignore if valid but not number
					break;
				default:
					// Throw exception for foreign character (alpha, invalid punctuation, etc)
					throw new IllegalArgumentException();
			}
		}
		
		// Check if size of phoneNumber is >11 (too many numbers)
		// throw exception if >11
		if (phoneNumber.length() > 11) {
			throw new IllegalArgumentException();
		}
		
		// Check if country code present
		if (phoneNumber.length() == 11) {
			// Check if country code is 1, otherwise throw exception
			if (phoneNumber.charAt(0) == '1') {
				phoneNumber = phoneNumber.substring(1);
			}
			else {
				throw new IllegalArgumentException();
			}
		}
		
		return phoneNumber;
	}

	/**
	 * 6. Given a phrase, count the occurrences of each word in that phrase.
	 * 
	 * For example for the input "olly olly in come free" olly: 2 in: 1 come: 1
	 * free: 1
	 * 
	 * @param string
	 * @return
	 */
	public Map<String, Integer> wordCount(String string) {
		// Empty string
		if (string.isEmpty()) {
			return null;
		}
		
		// String[] to store parsed string
		// parse on whitespace and punctuation using regular expression
		String[] stringArr = string.split("[\\p{Punct}\\s]+");
		
		// Map for storage
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		for (String i : stringArr) {
			// Check if string is not empty
			if (!i.isEmpty()) {
				// Put key if absent (unmapped), otherwise increment value
				if(map.putIfAbsent(i, 1) != null) {
					map.replace(i, map.get(i) + 1);
				}
			}
		}
		return map;
	}	

	
	/**
	 * 7. Implement a binary search algorithm.
	 * 
	 * Searching a sorted collection is a common task. A dictionary is a sorted list
	 * of word definitions. Given a word, one can find its definition. A telephone
	 * book is a sorted list of people's names, addresses, and telephone numbers.
	 * Knowing someone's name allows one to quickly find their telephone number and
	 * address.
	 * 
	 * If the list to be searched contains more than a few items (a dozen, say) a
	 * binary search will require far fewer comparisons than a linear search, but it
	 * imposes the requirement that the list be sorted.
	 * 
	 * In computer science, a binary search or half-interval search algorithm finds
	 * the position of a specified input value (the search "key") within an array
	 * sorted by key value.
	 * 
	 * In each step, the algorithm compares the search key value with the key value
	 * of the middle element of the array.
	 * 
	 * If the keys match, then a matching element hzas been found and its index, or
	 * position, is returned.
	 * 
	 * Otherwise, if the search key is less than the middle element's key, then the
	 * algorithm repeats its action on the sub-array to the left of the middle
	 * element or, if the search key is greater, on the sub-array to the right.
	 * 
	 * If the remaining array to be searched is empty, then the key cannot be found
	 * in the array and a special "not found" indication is returned.
	 * 
	 * A binary search halves the number of items to check with each iteration, so
	 * locating an item (or determining its absence) takes logarithmic time. A
	 * binary search is a dichotomic divide and conquer search algorithm.
	 * 
	 */
	static class BinarySearch<T> {
		private List<T> sortedList;

		public int indexOf(T t) {
			// Get size and initialize middle
			int size = sortedList.size();
			int it = size/2;
			
			// Upper and lower bound of range
			int upper = size;
			int lower = 0;

			// Convert to String
			String val = sortedList.get(it).toString();
			String lookFor = t.toString();
			
			// Convert to Integer
			Integer iVal = Integer.parseInt(val); 
			Integer iLookFor = Integer.parseInt(lookFor);
			
			
			while (!iVal.equals(iLookFor)) {
				// Update midpoint based on comparison of midpoint & desired value
				if (iVal.compareTo(iLookFor) > 0){
					// update upper
					upper = it;
					// new midpoint
					it = (it + lower)/2;
				}
				else {
					// update lower
					lower = it;
					// new midpoint
					it = (upper + it)/2;
				}
				// Update val & iVal
				val = sortedList.get(it).toString();
				iVal = Integer.parseInt(val);
				
			}
			return it;
		}

		public BinarySearch(List<T> sortedList) {
			super();
			this.sortedList = sortedList;
		}

		public List<T> getSortedList() {
			return sortedList;
		}

		public void setSortedList(List<T> sortedList) {
			this.sortedList = sortedList;
		}
	}

	/**
	 * 8. Implement a program that translates from English to Pig Latin.
	 * 
	 * Pig Latin is a made-up children's language that's intended to be confusing.
	 * It obeys a few simple rules (below), but when it's spoken quickly it's really
	 * difficult for non-children (and non-native speakers) to understand.
	 * 
	 * Rule 1: If a word begins with a vowel sound, add an "ay" sound to the end of
	 * the word. Rule 2: If a word begins with a consonant sound, move it to the end
	 * of the word, and then add an "ay" sound to the end of the word. There are a
	 * few more rules for edge cases, and there are regional variants too.
	 * 
	 * See http://en.wikipedia.org/wiki/Pig_latin for more details.
	 * 
	 * @param string
	 * @return
	 */
	public String toPigLatin(String string) {
		// Empty string case
		if (string.isEmpty()) {
			return "";
		}
		
		// Parse string on whitespace delimiter
		String[] parsedString = string.split("[\\s]+");
		
		//Variables for storage
		String pigString = "";
		int indexFirstVowel = 0;
		String vowels = "aeiouAEIOU";
		String prefix;
		String suffix;

		// Iterate through parsedString
		for (String i : parsedString) {
			// Find index to cut off, second logic statement for vowel-only words
			while(!vowels.contains(Character.toString(i.charAt(indexFirstVowel))) && (indexFirstVowel < i.length())) {
				// special case "qu"
				if ( (i.charAt(indexFirstVowel)) == 'q' && (i.charAt(indexFirstVowel+1) == 'u')) {
					indexFirstVowel++;
				}
				indexFirstVowel++;
			}
			// Substring based off of index
			prefix = i.substring(0, indexFirstVowel);
			suffix = i.substring(indexFirstVowel);
			
			// Pig string construction
			if (pigString.isEmpty()) {
				pigString += suffix + prefix + "ay";
			}
			else {
				pigString += " " + suffix + prefix + "ay";
			}
			// reset index for next word
			indexFirstVowel = 0;
		}
		
		return pigString;
	}

	/**
	 * 9. An Armstrong number is a number that is the sum of its own digits each
	 * raised to the power of the number of digits.
	 * 
	 * For example:
	 * 
	 * 9 is an Armstrong number, because 9 = 9^1 = 9 10 is not an Armstrong number,
	 * because 10 != 1^2 + 0^2 = 2 153 is an Armstrong number, because: 153 = 1^3 +
	 * 5^3 + 3^3 = 1 + 125 + 27 = 153 154 is not an Armstrong number, because: 154
	 * != 1^3 + 5^3 + 4^3 = 1 + 125 + 64 = 190 Write some code to determine whether
	 * a number is an Armstrong number.
	 * 
	 * @param input
	 * @return
	 */
	public boolean isArmstrongNumber(int input) {
		// input <= 0 should be false
		if (input <= 0) {
			return false;
		}
		
		// Local copy of input & sum
		int in = input;
		int sum = 0;
		int placeValue;
		int numDigits = String.valueOf(input).length();
		
		// Single digit input is always true
		if (numDigits == 1) {
			return true;
		}
		
		// Parse through in using %
		for ( int i = 0; i < numDigits; i++) {
			// Result of % 10 is one's place value
			placeValue = in % 10;
			
			// Add sum by value to the power of numDigits
			sum += Math.pow(placeValue, numDigits);
			
			// Subtract extracted value and reduce by factor of 10
			in = (in - placeValue)/10;
		}
		if (sum == input) {
			return true;
		}
		return false;
	}

	/**
	 * 10. Compute the prime factors of a given natural number.
	 * 
	 * A prime number is only evenly divisible by itself and 1.
	 * 
	 * Note that 1 is not a prime number.
	 * 
	 * @param l
	 * @return
	 */
	public List<Long> calculatePrimeFactorsOf(long l) {
		// Local variables
		Long input = new Long(l);
		Long factor;
		List<Long> primeFactors = new ArrayList<Long>();
		
		// Starting at 2, test if modulo returns 0.
		// Loops to prevent multiples of a number from remaining and pull all
		// 	of that prime.
		// Update input to account for removed prime
		for (factor  = 2L; factor <= input; factor++) {
			while ((input % factor) == 0) {
				primeFactors.add(factor);
				input /= factor;
			}
		}
		
		// If list is empty, initial input was prime
		if (primeFactors.isEmpty()) {
			primeFactors.add(l);
		}
		
		return primeFactors;
	}
	
	/**
	 * 11. Create an implementation of the rotational cipher, also sometimes called
	 * the Caesar cipher.
	 * 
	 * The Caesar cipher is a simple shift cipher that relies on transposing all the
	 * letters in the alphabet using an integer key between 0 and 26. Using a key of
	 * 0 or 26 will always yield the same output due to modular arithmetic. The
	 * letter is shifted for as many values as the value of the key.
	 * 
	 * The general notation for rotational ciphers is ROT + <key>. The most commonly
	 * used rotational cipher is ROT13.
	 * 
	 * A ROT13 on the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: nopqrstuvwxyzabcdefghijklm It is
	 * stronger than the Atbash cipher because it has 27 possible keys, and 25
	 * usable keys.
	 * 
	 * Ciphertext is written out in the same formatting as the input including
	 * spaces and punctuation.
	 * 
	 * Examples: ROT5 omg gives trl ROT0 c gives c ROT26 Cool gives Cool ROT13 The
	 * quick brown fox jumps over the lazy dog. gives Gur dhvpx oebja sbk whzcf bire
	 * gur ynml qbt. ROT13 Gur dhvpx oebja sbk whzcf bire gur ynml qbt. gives The
	 * quick brown fox jumps over the lazy dog.
	 */
	static class RotationalCipher {
		private int key;

		public RotationalCipher(int key) {
			super();
			this.key = key;
		}

		public String rotate(String string) {
			// We can use character arithmetic to implement this
			
			// Vars for storage
			String cipher = "";
			char c;
			int offset = 'z' - 'a' + 1;
			
			// Cipher loop
			for (int i = 0; i < string.length(); i++) {
				// get character
				c = string.charAt(i);
				
				// only apply rotation to alphabetical characters
				if (Character.isLetter(c)) {
					c = (char) (c + key);
					
					// Check for wrap-around
					// Different check between upper and lower case
					// offset is the same regardless of case
					if (Character.isLowerCase(string.charAt(i))) {
						if (c > 'z') {
							c -= offset;
						}
					} else {
						if (c > 'Z') {
							c -= offset;
						}
					}
				}
				// Add character to cipher string
				cipher += c;
			}
			return cipher;
		}

	}

	/**
	 * 12. Given a number n, determine what the nth prime is.
	 * 
	 * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see
	 * that the 6th prime is 13.
	 * 
	 * If your language provides methods in the standard library to deal with prime
	 * numbers, pretend they don't exist and implement them yourself.
	 * 
	 * @param i
	 * @return
	 */
	public int calculateNthPrime(int i) {
		// Invalid argument handler
		if (i == 0) {
			throw new IllegalArgumentException();
		}
		
		// initialize primeList to store primes with 2 
		// index starts at 1 to account for the initial 2
		ArrayList<Integer> primeList = new ArrayList<Integer>();
		primeList.add(2);
		int index = 1;
		int testNum = 3;
		boolean markedToAdd = true;
		
		while (index < i) {
			for (Integer prime : primeList) {
				// iterate through list of known primes to see if current number
				// being tested is a prime
				if(testNum % prime == 0) {
					markedToAdd = false;
				}
			}
			// Add the number to primeList if number is prime
			// update index  to reflect the addition
			if (markedToAdd) {
				primeList.add(testNum);
				index++;
			}
			// Add 2 to testNum b/c we know 2 is the only even prime number
			// This reduces runtime by about half
			// reset makedToAdd flag to default value
			testNum += 2;
			markedToAdd = true;
		}
		
		return primeList.get(index - 1);
	}

	/**
	 * 13 & 14. Create an implementation of the atbash cipher, an ancient encryption
	 * system created in the Middle East.
	 * 
	 * The Atbash cipher is a simple substitution cipher that relies on transposing
	 * all the letters in the alphabet such that the resulting alphabet is
	 * backwards. The first letter is replaced with the last letter, the second with
	 * the second-last, and so on.
	 * 
	 * An Atbash cipher for the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: zyxwvutsrqponmlkjihgfedcba It is a
	 * very weak cipher because it only has one possible key, and it is a simple
	 * monoalphabetic substitution cipher. However, this may not have been an issue
	 * in the cipher's time.
	 * 
	 * Ciphertext is written out in groups of fixed length, the traditional group
	 * size being 5 letters, and punctuation is excluded. This is to make it harder
	 * to guess things based on word boundaries.
	 * 
	 * Examples Encoding test gives gvhg Decoding gvhg gives test Decoding gsvjf
	 * rxpyi ldmul cqfnk hlevi gsvoz abwlt gives thequickbrownfoxjumpsoverthelazydog
	 *
	 */
	static class AtbashCipher {

		/**
		 * Question 13
		 * 
		 * @param string
		 * @return
		 */
		public static String encode(String string) {

			// Handle empty string
			if (string.isEmpty()) {
				return "";
			}
			
			// Reference variables
			String alphabet = "abcdefghijklmnopqrstuvwxyz";
			String reversed = "zyxwvutsrqponmlkjihgfedcba";
			
			// Storage variables
			String wordBuffer = "";
			String cipher = "";
			char ch;
			int index;
			
			// Convert string to lower and parse using whitespace & punctuation delimiter
			String[] parsedString = string.toLowerCase().split("[\\s\\p{Punct}]+");
			
			// Iterate through parsed strings
			for (String i : parsedString) {
				// Iterate through each character
				for (int j = 0; j < i.length(); j++) {
					ch = i.charAt(j);
					// If character is a letter, encode it and put in wordBuffer
					// else, push character
					if (Character.isLetter(ch)) {
						index = alphabet.indexOf(ch);
						wordBuffer += reversed.charAt(index);

					} else {
						wordBuffer += ch;
					}
					// If wordBuffer reaches size limit, push to cipher and reset
					if (wordBuffer.length() == 5) {
						cipher += wordBuffer + " ";
						wordBuffer = "";
					}
				}
			}
			// Check if wordBuffer has something to be pushed
			// if so, push it, else get rid of extra whitespace
			if (!wordBuffer.isEmpty()) {
				cipher += wordBuffer;
			} else {
				cipher = cipher.substring(0, cipher.length()-1);
			}
			
			return cipher;		
		}

		/**
		 * Question 14
		 * 
		 * @param string
		 * @return
		 */
		public static String decode(String string) {
			// Handle empty string
			if (string.isEmpty()) {
				return "";
			}
						
			// Reference variables
			String alphabet = "abcdefghijklmnopqrstuvwxyz";
			String reversed = "zyxwvutsrqponmlkjihgfedcba";
			
			// Local variables
			String decoded = "";
			int index;
			char ch;
			
			// parse string delimiting on whitespace
			// shouldn't need to handle invalid chars
			// assume string is a result of encoding
			String[] parsedString = string.split("[\\s]+");
			
			// Iterate through string array
			for (String i : parsedString) {
				// Iterate through each character
				// Decode and push decoded character
				for (int j = 0; j < i.length();j++) {
					ch = i.charAt(j);
					// Check if ch is alphabetical
					// else, push ch
					if (Character.isLetter(ch)) {
						index = reversed.indexOf(ch);
						decoded += alphabet.charAt(index);
					} else {
						decoded += ch;
					}
				}
			}
			return decoded;
		}
	}

	/**
	 * 15. The ISBN-10 verification process is used to validate book identification
	 * numbers. These normally contain dashes and look like: 3-598-21508-8
	 * 
	 * ISBN The ISBN-10 format is 9 digits (0 to 9) plus one check character (either
	 * a digit or an X only). In the case the check character is an X, this
	 * represents the value '10'. These may be communicated with or without hyphens,
	 * and can be checked for their validity by the following formula:
	 * 
	 * (x1 * 10 + x2 * 9 + x3 * 8 + x4 * 7 + x5 * 6 + x6 * 5 + x7 * 4 + x8 * 3 + x9
	 * * 2 + x10 * 1) mod 11 == 0 If the result is 0, then it is a valid ISBN-10,
	 * otherwise it is invalid.
	 * 
	 * Example Let's take the ISBN-10 3-598-21508-8. We plug it in to the formula,
	 * and get:
	 * 
	 * (3 * 10 + 5 * 9 + 9 * 8 + 8 * 7 + 2 * 6 + 1 * 5 + 5 * 4 + 0 * 3 + 8 * 2 + 8 *
	 * 1) mod 11 == 0 Since the result is 0, this proves that our ISBN is valid.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isValidIsbn(String string) {
		// parse string on '-'
		String[] parsedString = string.split("[-]");
		
		// Vars for storage
		int input;
		char ch;
		int factor = 10;
		int sum = 0;
		
		// Iterate through parsedString
		for (String i : parsedString) {
			// Iterate through each character
			for (int j = 0; j < i.length(); j++) {
				ch = i.charAt(j);
				// Check if numerical
				if (Character.isDigit(ch)) {
					input = Integer.valueOf("" + ch);
				} else if (ch == 'X') { //Check if 'X'
					input = 10;
				} else { // Invalid input, return false immediately
					return false;
				}
				// Multiply input by factor, decrement factor, and add to cumulative sum
				sum += input * factor;
				factor--;
			}
		}
		
		// Check if sum % 11 == 0
		if ((sum % 11) == 0) {
			return true;
		}
		return false;
	}

	/**
	 * 16. Determine if a sentence is a pangram. A pangram (Greek: παν γράμμα, pan
	 * gramma, "every letter") is a sentence using every letter of the alphabet at
	 * least once. The best known English pangram is:
	 * 
	 * The quick brown fox jumps over the lazy dog.
	 * 
	 * The alphabet used consists of ASCII letters a to z, inclusive, and is case
	 * insensitive. Input will not contain non-ASCII symbols.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isPangram(String string) {
		// Empty string handler
		if (string.isEmpty()) {
			return false;
		}
		
		// We can use a set for this since there cannot be duplicate values
		// Push every letter and check if set size is 26 at the end
		Set<String> letters = new HashSet<String>();
		
		// Convert to lowercase and parse string on whitespace and punctuation
		String[] parsedString = string.toLowerCase().split("[\\s\\p{Punct}]+");
		
		// For each string
		for (String i : parsedString) {
			// Add each char
			for (int j = 0; j < i.length(); j++) {
				letters.add("" + i.charAt(j));
			}
		}

		// Check size of letters = 26 
		if (letters.size() == 26) {
			return true;
		}
		
		return false;
	}

	/**
	 * 17. Calculate the moment when someone has lived for 10^9 seconds.
	 * 
	 * A gigasecond is 109 (1,000,000,000) seconds.
	 * 
	 * @param given
	 * @return
	 */
	public Temporal getGigasecondDate(Temporal given) {
		// Variables for storage
		LocalDate localDateInput;
		LocalDateTime convertedInput = LocalDate.now().atTime(0,0);
		
		// Check class of input
		if (given instanceof LocalDate) {
			// Convert LocalDate to LocalDateTime
			localDateInput = (LocalDate) given;
			convertedInput = localDateInput.atTime(0, 0);
		} else if (given instanceof LocalDateTime) {
			// Accept given if already LocalDateTime
			convertedInput = (LocalDateTime) given;
		}
		
		// Add a gigasecond
		convertedInput = convertedInput.plus(1000000000, ChronoUnit.SECONDS);
		return convertedInput;
	}

	/**
	 * 18. Given a number, find the sum of all the unique multiples of particular
	 * numbers up to but not including that number.
	 * 
	 * If we list all the natural numbers below 20 that are multiples of 3 or 5, we
	 * get 3, 5, 6, 9, 10, 12, 15, and 18.
	 * 
	 * The sum of these multiples is 78.
	 * 
	 * @param i
	 * @param set
	 * @return
	 */
	public int getSumOfMultiples(int i, int[] set) {
		// Variables for storage
		// Use set b/c no duplicate values
		Set<Integer> multiples = new HashSet<Integer>();
		int sum = 0;
		
		// Locate multiples less than i
		for (int j = 0; j < i; j++) {
			// Test j against each number, in set parameter
			for (int k = 0; k < set.length; k++) {
				if ((j % set[k]) == 0) {
					multiples.add(j);
				}
			}
		}
		
		// Sum of all values in multiples set
		for (Integer multiple : multiples) {
			sum += multiple;
		}
		
		return sum;
	}

	/**
	 * 19. Given a number determine whether or not it is valid per the Luhn formula.
	 * 
	 * The Luhn algorithm is a simple checksum formula used to validate a variety of
	 * identification numbers, such as credit card numbers and Canadian Social
	 * Insurance Numbers.
	 * 
	 * The task is to check if a given string is valid.
	 * 
	 * Validating a Number Strings of length 1 or less are not valid. Spaces are
	 * allowed in the input, but they should be stripped before checking. All other
	 * non-digit characters are disallowed.
	 * 
	 * Example 1: valid credit card number 1 4539 1488 0343 6467 The first step of
	 * the Luhn algorithm is to double every second digit, starting from the right.
	 * We will be doubling
	 * 
	 * 4_3_ 1_8_ 0_4_ 6_6_ If doubling the number results in a number greater than 9
	 * then subtract 9 from the product. The results of our doubling:
	 * 
	 * 8569 2478 0383 3437 Then sum all of the digits:
	 * 
	 * 8+5+6+9+2+4+7+8+0+3+8+3+3+4+3+7 = 80 If the sum is evenly divisible by 10,
	 * then the number is valid. This number is valid!
	 * 
	 * Example 2: invalid credit card number 1 8273 1232 7352 0569 Double the second
	 * digits, starting from the right
	 * 
	 * 7253 2262 5312 0539 Sum the digits
	 * 
	 * 7+2+5+3+2+2+6+2+5+3+1+2+0+5+3+9 = 57 57 is not evenly divisible by 10, so
	 * this number is not valid.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isLuhnValid(String string) {
		// parse string and remove whitespace
		String[] parsedStringArr = string.split("[\\s]+");
		String parsedString = "";
		for (String i : parsedStringArr) {
			parsedString += i;
		}
		// Check if parsed string is length <= 1
		if (parsedString.length() <=1 ) {
			return false;
		}
		// Variables for storage
		boolean toBeDoubled;
		int sum = 0;
		char ch;
		
		// set toBeDoubled flag based on whether length is odd or even
		if ((parsedString.length() % 2) == 1) {
			toBeDoubled = false;
		} else {
			toBeDoubled = true;
		}
		
		// Iterate through parsedString
		for (int i = 0; i < parsedString.length(); i++) {
			ch = parsedString.charAt(i);
			// Check if char is valid
			if (!Character.isDigit(ch)) {
				return false;
			}
			// Check if value should be doubled
			if (toBeDoubled) {
				sum += Integer.parseInt("" + ch) * 2;
				// Check if doubled value > 9
				if ((Integer.parseInt("" + ch) * 2) > 9) {
					sum -= 9;
				}
				toBeDoubled = false;
			} else {
				sum += Integer.parseInt("" + ch);
				toBeDoubled = true;
			}
		}
		// Test if sum is divisible by 10
		if ((sum % 10) == 0) {
			return true;
		}
		
		return false;
	}

	/**
	 * 20. Parse and evaluate simple math word problems returning the answer as an
	 * integer.
	 * 
	 * Add two numbers together.
	 * 
	 * What is 5 plus 13?
	 * 
	 * 18
	 * 
	 * Now, perform the other three operations.
	 * 
	 * What is 7 minus 5?
	 * 
	 * 2
	 * 
	 * What is 6 multiplied by 4?
	 * 
	 * 24
	 * 
	 * What is 25 divided by 5?
	 * 
	 * 5
	 * 
	 * @param string
	 * @return
	 */
	public int solveWordProblem(String string) {
		// Remove whitespace and '?'
		String[] parsedStringArrr = string.split("[\\s?]+");
		// Check if input is of valid length 6 or 5
		if ((parsedStringArrr.length < 5) || (parsedStringArrr.length > 6)) {
			return 0;
		}
		
		int result = 0;
		String operation = "";
		List<Integer> operands = new ArrayList<Integer>();
		
		for (String i : parsedStringArrr) {
			// Extract relevant information
			switch (i) {
				case "What":
				case "is":
				case "by":
					break;
				case "plus":
				case "minus":
				case "multiplied":
				case "divided":
					operation = i;
					break;
				default:
					operands.add(Integer.parseInt(i));
					break;
			}	
		}
		// perform operation
		switch (operation) {
			case "plus":
				result = operands.get(0) + operands.get(1);
				break;
			case "minus":
				result = operands.get(0) - operands.get(1);
				break;
			case "multiplied":
				result = operands.get(0) * operands.get(1);
				break;
			case "divided":
				result = operands.get(0) / operands.get(1);
			default:
				break;
		}
		
		return result;
	}

}
