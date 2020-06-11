/* SELF ASSESSMENT

 1. Did I use easy-to-understand meaningful variable names formatted properly (in lowerCamelCase)?
        Mark out of 5: 5
        Comment: Variable names in lowerCamelCase and are clear

 2. Did I indent the code appropriately?
        Mark out of 5: 5
        Comment:  Code is indented properly

 3. Did I write the createCipher function correctly (parameters, return type and function body) and invoke it correctly?
       Mark out of 20: 19
        Comment:  2D array used to make cipher, first row is in alphabetic order ending in a space, second row are the letters randomised

 4. Did I write the encrypt function correctly (parameters, return type and function body) and invoke it correctly?
       Mark out of 20:  20
        Comment: Two for loops used to sort through each letter and change it according to the random mapping

 5. Did I write the decrypt function correctly (parameters, return type and function body) and invoke it correctly?
       Mark out of 20:  20
        Comment: Functions same as the encrypt except changes letters the opposite way

 6. Did I write the main function body correctly (repeatedly obtaining a string and encrypting it and then decrypting the encrypted version)?
       Mark out of 25: 25
        Comment: Continues to ask user for string until 'quit' is entered

 7. How well did I complete this self-assessment?
        Mark out of 5: 5
        Comment: All questions answered with comments reflecting on my program

 Total Mark out of 100 (Add all the previous marks): 99 
*/ 

import java.util.Scanner;
import java.util.Random;

public class Cipher {
	
	public static final int MAX_NUMBER = 26;
	public static final int ALPHABET_ASCII_START = 97;

	public static void main(String[] args) {
		Scanner userInput = new Scanner(System.in);
		boolean finished = false;
		
		while (!finished)
		{
			System.out.println("Please enter the text you want to encrypt (or enter quit).");
			String plainText = userInput.nextLine();
			
			if (plainText.equals("quit"))
			{
				finished = true;
			}		
			else
			{
				String lowercasePlainText = plainText.toLowerCase();
				
				
				char[][] cipher = createCipher();
				
				String encryptedString = encrypt(lowercasePlainText, cipher);
				String decryptedString = decrypt(encryptedString, cipher);
				
				System.out.println("The text encrypted is: " + encryptedString);
				System.out.println("The text decrypted is: " + decryptedString);
			}	
		}
		userInput.close();
	}

	public static char[][] createCipher() {
		char[][] randomMap = new char[27][27];
		randomMap[0][26] = ' ';
		randomMap[1][26] = ' ';
				
		int letterCharacter = ALPHABET_ASCII_START;
		
		for (int index = 0; index < randomMap.length - 1; index++)
		{
			randomMap[0][index] = (char)letterCharacter;
			randomMap[1][index] = (char)letterCharacter;
			letterCharacter++;
		}
		
		Random generator = new Random();
		
		for (int index = 0; index < randomMap.length - 1; index++)
		{
			int randomIndex = generator.nextInt(MAX_NUMBER);
			char temp = randomMap[1][index];
			randomMap[1][index] = randomMap[1][randomIndex];
			randomMap[1][randomIndex] = temp;
		}	
		
		return randomMap;
	}
	
	public static String encrypt(String plainText, char[][] cipher) {
		char[] plainTextArray = plainText.toCharArray();
		char[] encrypted = new char[plainTextArray.length];
		
		for (int index = 0; index < encrypted.length; index++)
		{
			for (int cipherIndex = 0; cipherIndex < cipher.length; cipherIndex++)
			{
				if (plainTextArray[index] == cipher[0][cipherIndex])
				{
					encrypted[index] = cipher[1][cipherIndex];
				}
			}
		}
		
		String encryptedString = new String(encrypted);
		return encryptedString;
	}
	
	public static String decrypt(String encryptedString, char[][] cipher) {
		char[] encryptedArray = encryptedString.toCharArray();
		char[] decrypted = new char[encryptedArray.length];
		
		for (int index = 0; index < decrypted.length; index++)
		{
			for (int cipherIndex = 0; cipherIndex < cipher.length; cipherIndex++)
			{
				if (encryptedArray[index] == cipher [1][cipherIndex])
				{
					decrypted[index] = cipher [0][cipherIndex];
				}
			}
		}
		
		String decryptedString = new String(decrypted);
		return decryptedString;
	}
}
