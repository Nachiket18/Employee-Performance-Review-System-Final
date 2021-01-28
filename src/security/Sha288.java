package security;

import java.security.SecureRandom;
import java.util.Scanner;

/**
 * Java class for generating hashed value of manager passwords
 * @author GreyHood
 *
 */
public class Sha288
{
	static Scanner sc=new Scanner(System.in);
	
	/**
	 * @param num number to be rotated to right
	 * @param numberOfRotations number of rotations to be performed on the number
	 * @return number, rotated for the specified number of times to the right
	 */
	public static int rotateRight(int num, int numberOfRotations)
	{
		  for(int i=0;i<numberOfRotations-1;i++)
		  {
			  int temp1=num%10;
			  num=num/10;
			  int temp2=10000000*temp1;
			  num=temp2+num;
		  }
		  return num;
	}
	
	/**
	 * @param num number to be rotated to left
	 * @param numberOfRotations number of rotations to be performed on the number
	 * @return number, rotated for the specified number of times to the left
	 */
	public static int rotateLeft(int num, int numberOfRotations)
	{
		  for(int i=0;i<numberOfRotations-1;i++)
		  {
			  int temp1=num/10000000;
			  num=num%10000000;
			  num=num*10+temp1;
		  }
		  return num;
	}
	
	/**
	 * @param block block of text from plaintext
	 * @param hashValueInt initial hash values
	 * @param o block number
	 * @return generated hash value from each block to be appended to generate final hash value
	 */
	public static int[][] hashGenerator(int[][][] block , int[][] hashValueInt, int o) 
	{
		int i=0,j=0,k=0,l=0,m=0,temp3=0,lengthDifference=0;
		long temp1=0,temp2=0;
		int a,b,c,d,e,f,g,h,n;

		String[][] hexBlock = new String[14][1];
		
		String asciiValueString;
		StringBuilder tempAsciiValueString=new StringBuilder();
		
		a=block[o][0][0] ^ ( ( rotateLeft(block[o][3][0], 3) ) ^ block[o][5][0] );
		b=block[o][1][0] + block[o][2][0];
		c=block[o][4][0] | block[o][6][0];
		d=( block[o][6][0] + block[o][7][0] );
		e=block[o][8][0] | block[o][9][0];
		f=rotateLeft(block[o][10][0], 7);
		g=block[o][10][0] ^ block[o][11][0] + block[o][12][0];
		h=block[o][13][0];
		n=a+b;
		
		for(i=0;i<73;i++)
		{
			a = a ^ ( (~b) | ( g & n ) );
			b = c ^ b & (rotateRight( e, 7 ));
			c = a ^ d;
			d = b | ( g & n );
			e = rotateRight(a, 3);
			f = c ^ d & (~e);
			g = d ^ e;
			h = n + rotateLeft(f, 5);
			n = rotateRight(( g | h ), 3);
			
			hashValueInt[0][0] = hashValueInt[0][0] + g + a;
			hashValueInt[1][0] = hashValueInt[1][0] + rotateLeft(b, 5);
			hashValueInt[2][0] = hashValueInt[2][0] + c * 2;
			hashValueInt[3][0] = hashValueInt[3][0] + d+b;
			hashValueInt[4][0] = hashValueInt[4][0] + n + e;
			hashValueInt[5][0] = hashValueInt[5][0] + rotateRight(f, 7);
			hashValueInt[6][0] = hashValueInt[6][0] + f + g;
			hashValueInt[7][0] = hashValueInt[7][0] + h*2;
			hashValueInt[8][0] = hashValueInt[8][0] + n+h;
		}		
		return hashValueInt;			
	}
	
	/**
	 * @param password Manager password in plaintext
	 * @return hashed manager password
	 */
	public static String main(String password) 
	{
		int numberOfBlocks=0,lengthOfMessage=0,i=0,j=0,k=0,m=0,o=0;
		int temp1=0,temp2=0;
		
		String messageDigest;
		String tempString=new String();
		String[] x=new String[9];
		
		int[][] hashValueInt = new int [9][1];
		hashValueInt[0][0]=75998983;	hashValueInt[1][0]=98598999;	hashValueInt[2][0]=49999869;
		hashValueInt[3][0]=85759922;	hashValueInt[4][0]=73839555;	hashValueInt[5][0]=55555555;
		hashValueInt[6][0]=45555555;	hashValueInt[7][0]=49827598;	hashValueInt[8][0]=89819805;
		
		
		lengthOfMessage=password.length();
		
		String asciiValueString;
		StringBuilder tempAsciiValueString=new StringBuilder();
		
		for(i=0;i<password.length();i++)
		{
			temp1=(int)(password.charAt(i));
			tempAsciiValueString.append(temp1);
		}
		
		if( tempAsciiValueString.length() % 112 != 0 )
		{
			while( tempAsciiValueString.length() % 112 != 0 )
			{
				tempAsciiValueString.append(7);
			}
		}
		asciiValueString=tempAsciiValueString.toString();
				
		numberOfBlocks=asciiValueString.length() / 112;
		/*System.out.println("Length after converting into ASCII:" + asciiValueString.length());
		System.out.println("Number of blocks required :" + numberOfBlocks + "\n");*/
		
		int[][][] block = new int[numberOfBlocks][14][1];
		
		while(o < numberOfBlocks)
		{
			for(i=0;i<14;i++)
			{
				tempString=asciiValueString.substring(m, m+8);
				m=m+8;
				block[o][i][0] = Integer.parseInt(tempString);
				temp2=0;
			}
			o++;
		}
		
		o=0;
		for(i=0;i<numberOfBlocks;i++)
		{
			hashValueInt=hashGenerator(block, hashValueInt, o);
			o++;
		}
		
		/*SecureRandom sr=new SecureRandom();
		int randomNumber=sr.nextInt(99999999);
		hashValueInt[0][0]+=randomNumber/10000000;
		hashValueInt[1][0]+=randomNumber/1000000;
		hashValueInt[2][0]+=randomNumber/100000;
		hashValueInt[3][0]+=randomNumber/10000;
		hashValueInt[4][0]+=randomNumber/1000;
		hashValueInt[5][0]+=randomNumber/100;
		hashValueInt[6][0]+=randomNumber/1000;
		hashValueInt[7][0]+=randomNumber/10000;
		hashValueInt[8][0]+=randomNumber/1000;*/
		
		x[0] = Integer.toHexString(hashValueInt[0][0]);  
		x[1] = Integer.toHexString(hashValueInt[1][0]); 
		x[2] = Integer.toHexString(hashValueInt[2][0]); 
		x[3] = Integer.toHexString(hashValueInt[3][0]); 
		x[4] = Integer.toHexString(hashValueInt[4][0]); 
		x[5] = Integer.toHexString(hashValueInt[5][0]); 
		x[6] = Integer.toHexString(hashValueInt[6][0]); 
		x[7] = Integer.toHexString(hashValueInt[7][0]); 
		x[8] = Integer.toHexString(hashValueInt[8][0]);
		
		for(i=0;i<9;i++)
		{
			if(x[i].length() < 8)
			{
				while(x[i].length() != 8)
				{
					x[i] = x[i] + 'd';
				}	
			}
		}
	
		messageDigest = x[0] + x[1] + x[2] + x[3] + x[4] + x[5] + x[6] + x[7] + x[8];
		/*System.out.println("Message Digest using SHA 288 is :"+messageDigest);
		System.out.println("\nMessage Digest Length : " + messageDigest.length());*/
		return messageDigest;
	}
}