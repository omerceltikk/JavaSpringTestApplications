package com.example.demo_v6.cryptFunctions;

import java.text.Normalizer;
import java.util.ArrayList;

public class CryptFunction {
	String text;
	 String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
			 +"0123456789"
	         + "abcdefghijklmnopqrstuvxyz"; 
	 String AlphaString = "abcdefghijklmnopqrstuvxyz"; 
	 
	public String CreateCryptTexts(String entityText) {
		char[] charArray = entityText.toCharArray();
		String createdCryptString = "";
		ArrayList<String> codeArray = new ArrayList();
		for(int i = 0; i < charArray.length; i++) {
			StringBuilder sb = new StringBuilder(32); 
			Integer cCode = (int) charArray[i];
			char[] cCodeArray = cCode.toString().toCharArray();
			if(cCode != 10) {		
			int randomNumber = (int)(Math.random() * (AlphaString.length() - 10) + 10); 
			char randomNumberToString = AlphaString.charAt(randomNumber);
			int ccodeValueWithRandom = randomNumber *32 * cCode;
			System.out.println(ccodeValueWithRandom);
			char[] ccodeValueWithRandomArray = String.valueOf(ccodeValueWithRandom).toCharArray();
			
				if(ccodeValueWithRandomArray.length == 5) {
					 createdCryptString += createRandomString((randomNumber-3))
							+ AlphaString.charAt(Character.getNumericValue(ccodeValueWithRandomArray[0]))
							+ AlphaString.charAt(Character.getNumericValue(ccodeValueWithRandomArray[1]))
							+ AlphaNumericString.charAt((int) (AlphaNumericString.length() * Math.random()))
							+ AlphaString.charAt(Character.getNumericValue(ccodeValueWithRandomArray[2]))
							+ AlphaString.charAt(Character.getNumericValue(ccodeValueWithRandomArray[3]))
							+ AlphaString.charAt(Character.getNumericValue(ccodeValueWithRandomArray[4]))
							+ createRandomString(32 - ((randomNumber+3)+4)) 
							+ randomNumberToString
							+ AlphaString.charAt(ccodeValueWithRandomArray.length)
							+ createRandomString(2);
	
				}else if(ccodeValueWithRandomArray.length == 6) {
					createdCryptString += createRandomString((randomNumber-3))
							+ AlphaString.charAt(Character.getNumericValue(ccodeValueWithRandomArray[0]))
							+ AlphaString.charAt(Character.getNumericValue(ccodeValueWithRandomArray[1]))
							+ AlphaNumericString.charAt((int) (AlphaNumericString.length() * Math.random()))
							+ AlphaString.charAt(Character.getNumericValue(ccodeValueWithRandomArray[2]))
							+ AlphaString.charAt(Character.getNumericValue(ccodeValueWithRandomArray[3]))
							+ AlphaString.charAt(Character.getNumericValue(ccodeValueWithRandomArray[4]))
							+ AlphaString.charAt(Character.getNumericValue(ccodeValueWithRandomArray[5]))
							+ createRandomString(32 - ((randomNumber+4)+4)) 
							+ randomNumberToString
							+ AlphaString.charAt(ccodeValueWithRandomArray.length)
							+ createRandomString(2);
					
				}else if(ccodeValueWithRandomArray.length == 4) {
					createdCryptString += createRandomString((randomNumber-3))
							+ AlphaString.charAt(Character.getNumericValue(ccodeValueWithRandomArray[0]))
							+ AlphaString.charAt(Character.getNumericValue(ccodeValueWithRandomArray[1]))
							+ AlphaNumericString.charAt((int) (AlphaNumericString.length() * Math.random()))
							+ AlphaString.charAt(Character.getNumericValue(ccodeValueWithRandomArray[2]))
							+ AlphaString.charAt(Character.getNumericValue(ccodeValueWithRandomArray[3]))
							+ createRandomString(32 - ((randomNumber+2)+4)) 
							+ randomNumberToString
							+ AlphaString.charAt(ccodeValueWithRandomArray.length)
							+ createRandomString(2);
				}
			}
		}
		
		return createdCryptString;
	}
	
	public String DeCryptTexts(String entityText) {
		ArrayList<String> splittedStringArray = new ArrayList();
		ArrayList<Integer> codeArray = new ArrayList();
		
		 for (int i = 0; i < (entityText.length()) / 32; i++) {
	            int startIndex = i * 32;
	            int endIndex = Math.min(startIndex + 32, entityText.length());
	            splittedStringArray.add(entityText.substring(startIndex, endIndex)) ;
	        }
			for(int i = 0; i < splittedStringArray.size(); i++) {
				char[] charArray = splittedStringArray.get(i).toCharArray();
				int randomIndexValue = AlphaString.indexOf(charArray[28]) - 1 ;
				int cCodeLength = AlphaString.indexOf(charArray[29]);
				int cCode;
				String numericString =  String.valueOf(AlphaString.indexOf(charArray[randomIndexValue-2]))
						+ String.valueOf(AlphaString.indexOf(charArray[randomIndexValue-1]))
						+ String.valueOf(AlphaString.indexOf(charArray[randomIndexValue+1]))
						+ String.valueOf(AlphaString.indexOf(charArray[randomIndexValue+2]));
				if(cCodeLength == 5) {
					numericString +=  String.valueOf(AlphaString.indexOf(charArray[randomIndexValue+3]));
					
				}else if(cCodeLength == 6) {
					numericString +=  String.valueOf(AlphaString.indexOf(charArray[randomIndexValue+3]))
							+ String.valueOf(AlphaString.indexOf(charArray[randomIndexValue+4]));
				}
				int multiplierCode = (randomIndexValue + 1) * 32;
				cCode = Integer.parseInt(numericString) / multiplierCode;
				codeArray.add(cCode);
			}

		return asciiToString(codeArray);
	}
	private static String asciiToString(ArrayList<Integer> codeArray) {
	        StringBuilder sb = new StringBuilder();
	        for (int ascii : codeArray) {
	            sb.append((char) ascii);
	        }
	   
	        return sb.toString();
	    }
	private String createRandomString (Integer n) {
		
		 StringBuilder sb = new StringBuilder(n); 
		 
		 for (int i = 0; i < n; i++) { 
			 
			   int index  = (int)(AlphaNumericString.length() * Math.random()); 
			   sb.append(AlphaNumericString 
			      .charAt(index)); 
			  } 

		  return sb.toString(); 
	}
	
}



  