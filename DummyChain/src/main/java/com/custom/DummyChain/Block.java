package com.custom.DummyChain;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.custom.DummyChain.util.StringUtil;
import com.custom.DummyChain.Account.*;
import com.custom.DummyChain.trasaction.*;


public class Block {

	public String prevBlockHash;
	public String hash;	
	public Long timeStamp;
	public int nonce;
	public HashMap<PublicKey, Account> accounts;
	public ArrayList<Transaction> transactions;
	
	public Block( String prevBlockHash) {
		this.prevBlockHash = prevBlockHash;		
		this.timeStamp = new Date().getTime();
		this.hash = calculateHash();
	}
	
	public String calculateHash() {
		
		String calculatedHash = StringUtil.applySHA256(prevBlockHash + Long.toString(timeStamp) + Integer.toString(nonce) + transactions.toString() + accounts.toString() );
		return calculatedHash;
	}
	
	public boolean addTransaction(Transaction transaction) {		
		//process transaction and check if valid, unless block is genesis block then ignore.
				if(transaction == null) return false;		
				if((prevBlockHash != "0")) {
					if((processTransaction(transaction) != true)) {
						System.out.println("Transaction failed to process. Discarded.");
						return false;
					}
				}
				transactions.add(transaction);
				System.out.println("Transaction Successfully added to Block");
				return true;
	}
	
	public boolean processTransaction(Transaction transaction) {
		// Verify the signature
		if(transaction.verifiySignature() == false) {
			System.out.println("#Transaction Signature failed to verify");
			return false;
		}
		
		//check if sender has funds to transfer
		if(!(getBalance(transaction.sender) >= transaction.value)) {
			System.out.println("Sender doesn't have enough funds to transfer");
			return false;
		}
		
		// Transfer funds
		return transferFunds(transaction.sender, transaction.reciepient, transaction.value);
		
	}
	
	public boolean transferFunds(PublicKey from, PublicKey to, float value) {
		//check if sender has funds to transfer
		if(!(getBalance(from) >= value)) {
				System.out.println("Sender doesn't have enough funds to transfer");
				return false;
			}
		accounts.get(from).value -= value;
		accounts.get(to).value += value;
		return true;				
	}
	
	
	public float getBalance(PublicKey address) {
		return accounts.get(address).value;
	}
	
	public void mineBlock(int difficulty) {		
		String target = StringUtil.getDificultyString(difficulty); //Create a string with difficulty * "0" 
		while(! hash.substring(0, difficulty).equals(target)) {
			nonce ++;
			hash = calculateHash();
		}
		System.out.println("Block Mined!!! : " + hash);
	}
}
