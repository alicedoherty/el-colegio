/* SELF ASSESSMENT 

1. readDictionary
- I have the correct method definition [Mark out of 5:5]
- Comment: Method defined properly
- My method reads the words from the "words.txt" file. [Mark out of 5:5]
- Comment: File/buffered reader used to read in the words from words.txt
- It returns the contents from "words.txt" in a String array or an ArrayList. [Mark out of 5:5]
- Comment: Words returned in an ArrayList

2. readWordList
- I have the correct method definition [Mark out of 5:5]
- Comment: Method defined properly
- My method reads the words provided (which are separated by commas, saves them to an array or ArrayList of String references and returns it. [Mark out of 5:5]
- Comment: Reads in words separated by commas, ignore spaces (e.g in case user places space at end of list) and returns in an ArrayList

3. isUniqueList
- I have the correct method definition [Mark out of 5:5]
- Comment: Method defined properly
- My method compares each word in the array with the rest of the words in the list. [Mark out of 5:]
- Comment: I don't use a loop that compares each word. Instead I convert the ArrayList to a set so any duplicates will be removed.
	Then by comparing the length of the ArrayList to the set if there's a different it's because there's a duplicate.
//- Exits the loop when a non-unique word is found. [Mark out of 5:]
//- Comment: 
- Returns true is all the words are unique and false otherwise. [Mark out of 5:5]
- Comment: Returns true if non of the words are the same

4. isEnglishWord
- I have the correct method definition [Mark out of 5:5]
- Comment: Method defined properly
- My method uses the binarySearch method in Arrays library class. [Mark out of 3:3]
- Comment: Used the binarySearch, first has to convert ArrayList to an array
- Returns true if the binarySearch method return a value >= 0, otherwise false is returned. [Mark out of 2:2]
- Comment: Returns true if the method returns a value >= 0

5. isDifferentByOne
- I have the correct method definition [Mark out of 5:5]
- Comment: Method correctly defined
- My method loops through the length of a words comparing characters at the same position in both words searching for one difference. [Mark out of 10:10]
- Comment: Loops through and every time the characters are the same, count++, if it's different by one the count should be one less than the word length

6. isWordChain
- I have the correct method definition [Mark out of 5:5]
- Comment: Method correctly defined
- My method calls isUniqueList, isEnglishWord and isDifferentByOne methods and prints the appropriate message [Mark out of 10:10]
- Comment: All other methods called and if isWordChain() == true, valid chain printed and vice versa

7. main
- Reads all the words from file words.txt into an array or an ArrayList using the any of teh Java.IO classes covered in lectures [Mark out of 10:10]
- Comment: Passed the file into a file reader which is passed into readDictionary to convert it to an ArrayList
- Asks the user for input and calls isWordChain [Mark out of 5:5]
- Comment: User asked for list, then isWordChain is called

 Total Mark out of 100 (Add all the previous marks):
*/

import java.util.*;
import java.io.*;


public class WordLinks {
	public static ArrayList<String> dictionaryWords;
	public static ArrayList<String> userWordList;

	public static void main(String[] args) throws IOException {	
		try {
			FileReader fileReader = new FileReader("words.txt");
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			dictionaryWords = readDictionary(bufferedReader);
		}
		catch (Exception e) {
			System.out.print(e);
		}

		String userWords = null;
		boolean finished = false;
		Scanner input = new Scanner(System.in);

		while(!finished) {
			System.out.println("Enter a comma separated list of words (or an empty list to quit):");
			if(input.hasNext())
			{
				userWords = input.nextLine();
				userWordList = readWordList(userWords);
				if(isWordChain())
					System.out.println("Valid chain of words from Lewis Carroll's word-links game.");
				else
					System.out.println("Not a valid chain of words from Lewis Carroll's word-links game.");
			}
			else
				finished = true;
		}
		input.close();
	}

	public static ArrayList<String> readDictionary(BufferedReader bufferedReader) {
		dictionaryWords = new ArrayList<String>();
		try {
			String nextWord = bufferedReader.readLine();
			while(nextWord != null)
			{
				dictionaryWords.add(nextWord);
				nextWord = bufferedReader.readLine();
			}
		} 
		catch (Exception e) {
			System.out.println(e);
		}
		return dictionaryWords;
	}


	public static ArrayList<String> readWordList(String userList) {
		userList = userList.replaceAll(" ", "");
		String[] words = userList.split(",");
		List<String> tmpList = Arrays.asList(words);
		ArrayList<String> userWordList = new ArrayList<String>(tmpList);
		return userWordList;
	}

	public static boolean isUniqueList() {
		Set<String> set = new HashSet<String>(userWordList);
		if(set.size() < userWordList.size())
			return false;
		else
			return true;
	}

	public static boolean isEnglishWord(String word) {
		String[] dictionaryArray = dictionaryWords.toArray(new String[dictionaryWords.size()]);
		if(Arrays.binarySearch(dictionaryArray, word) >= 0)
			return true;
		else
			return false;
	}

	public static boolean isDifferentByOne(String word1, String word2) {
		if(word1.length() != word2.length())
			return false;
		int count = 0;
		for(int i=0; i<word1.length(); i++) {
			if(word1.charAt(i) == word2.charAt(i))
				count++;
		}
		return count == word1.length() - 1;
	}

	public static boolean isWordChain() {
		if(isUniqueList()) {
			for(int i=0; i<userWordList.size()-1; i++) {
				if(isEnglishWord(userWordList.get(i)) && isEnglishWord(userWordList.get(i+1))) {
					if(!isDifferentByOne(userWordList.get(i), userWordList.get(i+1))) {
						return false;
					}
				}
				else
					return false;
			}
		}
		else {
			return false;
		}
		return true;
	}
}
