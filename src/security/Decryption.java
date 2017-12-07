package security;

import java.util.Arrays;

/**
 * Java class for decrypting database entries
 * @author GreyHood
 *
 */
public class Decryption 
{
	/**
	 * Decryption function
	 * @param cipherText Ciphertext to be decrypted to plaintext
	 * @param randomString Key for decrypting ciphertext
	 * @return Decrypted plaintext String
	 */
	public static String decrypt(String cipherText, String randomString) 
	{
		int i=0,j=0,k=0,temp1=0,max=0,min=0,length=0,spaceChar=32;
		int[] randomStringAscii=new int[300];
		int maxRandomStringAscii = 0;
		int minRandomStringAscii = 0;
		int finalkey = 0;
		int[] finalStringCharAscii=new int[300];
		int[] cipherToInt=new int[300];
		
		k=0;
		int count = 0;
		int[] quotientArray = new int[100];
		String tempString = "";
		
		String[] temp = cipherText.split("quotient");
		String arrayString = temp[1];
		cipherText = temp[0];

		for(j=0;j<arrayString.length();j++)
		{
			if(arrayString.charAt(j) == '_')
			{
				count++;
				quotientArray[k] = Integer.parseInt(tempString);
				k++;
				tempString = "";
			}
			else
			{
				tempString += Integer.parseInt(String.valueOf(arrayString.charAt(j)));
			}
		}
		
		int[] quotient = new int[count];
		for(int l=0;l<count;l++)
		{
			quotient[l] = quotientArray[l];
		}
				
		j=0;
		k=0;
		char[] finalStringChar=new char[300];
		
		/*while(cipherText.charAt(i) != '\0')
		{
			length++;
			i++;
		}*/
		
		cipherText = cipherText.trim().replaceAll("\0", "").toString();
		length = cipherText.length();
		
		
		for(i=0;i<length;i++)
		{
			cipherToInt[i]=(int)cipherText.charAt(i);				
		}
		cipherToInt[i+1]=0;
				
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
		finalStringChar=cipherText.toCharArray();
		for(i=0;i<length;i++)
		{
			temp1=((cipherToInt[i]-73) - (((finalkey++)) % 26)) ;
			finalStringCharAscii[i]=(26 * quotient[i]) + temp1;
		}
			
		for(i=0;i<length;i++)
		{
			finalStringChar[i]=(char)(finalStringCharAscii[i]);
		}
		
		String plainText = String.valueOf(finalStringChar);
		return plainText;		
	}
}
