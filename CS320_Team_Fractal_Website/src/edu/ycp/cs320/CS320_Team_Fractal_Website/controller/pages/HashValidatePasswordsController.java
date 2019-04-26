package edu.ycp.cs320.CS320_Team_Fractal_Website.controller.pages;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/*
 * Stolen... i mean resourcefully borrowed... from here:
 * https://howtodoinjava.com/security/how-to-generate-secure-password-hash-md5-sha-pbkdf2-bcrypt-examples/
 * Modified to work with this project.
 */

public class HashValidatePasswordsController 
{
	 public String generateStrongPasswordHash(String password)
	    {
	        int iterations = 1000;
	        char[] chars = password.toCharArray();
	        byte[] salt = getSalt();
	         
	        PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
	        SecretKeyFactory skf = null;
			try 
			{
				skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			} 
			catch (NoSuchAlgorithmException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        byte[] hash = null;
			try 
			{
				hash = skf.generateSecret(spec).getEncoded();
			} 
			catch (InvalidKeySpecException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Hashed password:" + iterations + ":" + toHex(salt) + ":" + toHex(hash));
	        return iterations + ":" + toHex(salt) + ":" + toHex(hash);
	    }
	 
	    public boolean validatePassword(String originalPassword, String storedPassword)
	    {
	        String[] parts = storedPassword.split(":");
	        int iterations = Integer.parseInt(parts[0]);
	        byte[] salt = fromHex(parts[1]);
	        byte[] hash = fromHex(parts[2]);
	         
	        PBEKeySpec spec = new PBEKeySpec(originalPassword.toCharArray(), salt, iterations, hash.length * 8);
	        SecretKeyFactory skf = null;
			try 
			{
				skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			} 
			catch (NoSuchAlgorithmException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        byte[] testHash = null;
			try 
			{
				testHash = skf.generateSecret(spec).getEncoded();
			} 
			catch (InvalidKeySpecException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	         
	        int diff = hash.length ^ testHash.length;
	        for(int i = 0; i < hash.length && i < testHash.length; i++)
	        {
	            diff |= hash[i] ^ testHash[i];
	        }
	        return diff == 0;
	    }
	 
	    private static byte[] getSalt()
	    {
	        SecureRandom sr = null;
			try 
			{
				sr = SecureRandom.getInstance("SHA1PRNG");
			} 
			catch (NoSuchAlgorithmException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        byte[] salt = new byte[16];
	        sr.nextBytes(salt);
	        return salt;
	    }
	     
	    private static String toHex(byte[] array)
	    {
	        BigInteger bi = new BigInteger(1, array);
	        String hex = bi.toString(16);
	        int paddingLength = (array.length * 2) - hex.length();
	        if(paddingLength > 0)
	        {
	            return String.format("%0"  +paddingLength + "d", 0) + hex;
	        }
	        else
	        {
	            return hex;
	        }
	    }	   
	    
	    private static byte[] fromHex(String hex)
	    {
	        byte[] bytes = new byte[hex.length() / 2];
	        for(int i = 0; i<bytes.length ;i++)
	        {
	            bytes[i] = (byte)Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
	        }
	        return bytes;
	    }

}
