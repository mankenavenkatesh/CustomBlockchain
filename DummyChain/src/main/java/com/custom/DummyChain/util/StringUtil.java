package com.custom.DummyChain.util;

import java.security.Key;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.Base64;

public class StringUtil {

	public static String applySHA256(String input) {

		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(input.getBytes("UTF-8"));
			StringBuffer hashString = new StringBuffer();
			for(int i=0; i<hash.length;i++) {
				String hexString = Integer.toHexString(0xff & hash[i]);
				if(hexString.length() == 1) hashString.append('0');
				hashString.append(hexString);
			}
			return hashString.toString();
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
		
		
	}
	
	//Applies ECDSA Signature and returns the result ( as bytes ).
			public static byte[] applyECDSASig(PrivateKey privateKey, String input) {
			Signature dsa;
			byte[] output = new byte[0];
			try {
				dsa = Signature.getInstance("ECDSA", "BC");
				dsa.initSign(privateKey);
				byte[] strByte = input.getBytes();
				dsa.update(strByte);
				byte[] realSig = dsa.sign();
				output = realSig;
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			return output;
		}
		
		//Verifies a String signature 
		public static boolean verifyECDSASig(PublicKey publicKey, String data, byte[] signature) {
			try {
				Signature ecdsaVerify = Signature.getInstance("ECDSA", "BC");
				ecdsaVerify.initVerify(publicKey);
				ecdsaVerify.update(data.getBytes());
				return ecdsaVerify.verify(signature);
			}catch(Exception e) {
				throw new RuntimeException(e);
			}
		}
	
	//Returns difficulty string target, to compare to hash. eg difficulty of 5 will return "00000"  
		public static String getDificultyString(int difficulty) {
			return new String(new char[difficulty]).replace('\0', '0');
		}

		public static String getStringFromKey(Key key) {
			return Base64.getEncoder().encodeToString(key.getEncoded());
		}
		
}
