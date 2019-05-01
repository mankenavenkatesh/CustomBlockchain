package com.custom.DummyChain.wallet;

import com.custom.DummyChain.DummyChain;
import com.custom.DummyChain.Account.Account;
import com.custom.DummyChain.trasaction.*;
import java.security.*;
import java.security.spec.ECGenParameterSpec;

public class Wallet {
	
	public PrivateKey privateKey;
	public PublicKey publicKey;
	
	public Wallet(){
		generateKeyPair();	
	}
		
	public void generateKeyPair() {
		try {
			KeyPairGenerator keyGen = KeyPairGenerator.getInstance("ECDSA","BC");
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
			ECGenParameterSpec ecSpec = new ECGenParameterSpec("prime192v1");
			// Initialize the key generator and generate a KeyPair
			keyGen.initialize(ecSpec, random);   //256 bytes provides an acceptable security level
	        	KeyPair keyPair = keyGen.generateKeyPair();
	        	// Set the public and private keys from the keyPair
	        	privateKey = keyPair.getPrivate();
	        	publicKey = keyPair.getPublic();
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	 //returns balance of this account
		public float getBalance() {			
	        Account myAccount = DummyChain.blockchain.get(DummyChain.blockchain.size()-1).accounts.get(publicKey);
	        if(myAccount==null) {
	        	return 0;
	        }
	        return myAccount.value;	        		
		}
		
	
	// Generates and returns a new transaction from this wallet.
	public Transaction sendFunds(PublicKey _recipient,float value ) {
		if(getBalance() < value) { //gather balance and check funds.
			System.out.println("#Not Enough funds to send transaction. Transaction Discarded.");
			return null;
		}    
		Transaction newTransaction = new Transaction(publicKey, _recipient , value);
		newTransaction.generateSignature(privateKey);
		return newTransaction;
	}
}
