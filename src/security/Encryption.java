package security;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * Java class for encrypting plaintext
 * @author GreyHood
 *
 */
public class Encryption
{
	static Scanner sc=new Scanner(System.in);
	static String ciphertext;
	static String randomString;
	static int lengthForQuotient;
	static int[] quotient=new int[300];
	
	/**
	 * function for generating cryptographic key 
	 */
	public static void randString()
	{
		int len = 10;
		final String AB = "!@#$%&*-=_+{}[]()<>,.?/:;'123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		Random rnd = new Random();
		StringBuilder sb = new StringBuilder( len );
		for( int i = 0; i < len; i++ ) 
			sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
		randomString = sb.toString();
	}
	
	/**
	 * Generates ciphertext for given plaintext
	 * @param plainText Plaintext entry
	 * @param length length of plaintext entry
	 */
	public static void encrypt(String plainText, int length)
	{
		int i=0,j=0,k=0,totalWords=1,totalSingleDigits=0,randomStringLength=0,max=0,min=0,spaceChar=32;
		int temp1=0,temp2=0;
		int[] plainTextToInt=new int[300];
		int[] singleWordAsciiTotal=new int[300];
		int[] randomStringAscii=new int[300];
		int maxRandomStringAscii = 0;
		int minRandomStringAscii = 0;
		int finalkey = 0;
		int[] finalStringAscii=new int[300];
		int xorRandomString = 0;
		int xorFinalKey = 0;
		int xorQuotient = 0;
		int constantForXor = 29;
		int transferedXor = 0;
		lengthForQuotient = length;
		
		char[] finalStringChar=new char[300];
		
		for(i=0;i<plainText.length();i++)
		{
			plainTextToInt[i]=(int)plainText.charAt(i);				
		}
		
		for(i=0;i<randomString.length();i++)
		{
			temp1=(int)randomString.charAt(j);						
			randomStringAscii[i]=temp1;
			j++;
		}
		
		int[] tempnew = new int[300];
		int z=0,tempcount=0;
		k=0;
		
		for(i=0;i<randomString.length();i++)
		{
			
			for(j=0;j<randomString.length();j++)
			{
				tempnew[z] = randomStringAscii[j];
				z++;
				tempcount++;
			}
			
			Arrays.sort(tempnew);
			minRandomStringAscii=tempnew[tempnew.length - tempcount];
			maxRandomStringAscii=tempnew[tempnew.length - 1];
			tempnew = new int[300];
			tempcount=0;
			k++;
			i=j;
		}
		
		finalkey=minRandomStringAscii ^ maxRandomStringAscii;
		
		j=0;
		for(i=0;i<plainText.length();i++)
		{
			quotient[i]=plainTextToInt[i]/26;
			finalStringAscii[i]=(((finalkey++)%26) + (plainTextToInt[i])%26)+73;  
		}
		
		for(i=0;i<plainText.length();i++)
		{
			finalStringChar[i]=(char)(finalStringAscii[i]);
		}
		
		ciphertext = String.valueOf(finalStringChar);
	}
	
	/**
	 * @return ciphertext generated from plaintext
	 */
	public String getCiphertext()
	{
		int counter = 0;
		for (int i = 0; i < lengthForQuotient; i++)
		    if (quotient[i] != 0)
		        counter ++;
		
		String quotientString = "";
		for(int i = 0;i<counter;i++)
		{
			String temp = String.valueOf(quotient[i]);
			quotientString = quotientString + temp;
			quotientString += "_";
		}
		ciphertext += "quotient";
		ciphertext += quotientString;
		return ciphertext;
	}
	
	/**
	 * @return cryptographic key for the specific database tuple
	 */
	public String getOtp()
	{
		return randomString;
	}
}