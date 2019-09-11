package com.custom.DummyChain;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;

import com.custom.DummyChain.util.StringUtil;

public class Transaction {

	public String transactionId;
	public PublicKey sender;
	public PublicKey reciepient;
	public float value;
	public byte[] signature; 
	private static int sequence = 0;
	
	
	
	// Constructor: 
	public Transaction(PublicKey from, PublicKey to, float value) {
		this.sender = from;
		this.reciepient = to;
		this.value = value;	
	}
	
	private String calculateHash() {
		sequence++;
		return StringUtil.applySHA256(
				StringUtil.getStringFromKey(sender) +
				StringUtil.getStringFromKey(reciepient) +
				Float.toString(value) + sequence
				);
	}
	
	//Signs all the data we dont wish to be tampered with.
	public void generateSignature(PrivateKey privateKey) {
		String data = StringUtil.getStringFromKey(sender) + StringUtil.getStringFromKey(reciepient) + Float.toString(value)	;
		signature = StringUtil.applyECDSASig(privateKey,data);		
	}
	//Verifies the data we signed hasnt been tampered with
	public boolean verifiySignature() {
		String data = StringUtil.getStringFromKey(sender) + StringUtil.getStringFromKey(reciepient) + Float.toString(value)	;
		return StringUtil.verifyECDSASig(sender, data, signature);
	}

}
