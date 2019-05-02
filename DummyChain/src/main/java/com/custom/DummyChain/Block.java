package com.custom.DummyChain;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.custom.DummyChain.util.StringUtil;
import com.custom.DummyChain.Account.*;
import com.custom.DummyChain.Calculator.BlockRewardCalculator;
import com.custom.DummyChain.Calculator.TransactionFeeCalculator;
import com.custom.DummyChain.merkletree.MerkleTree;
import com.custom.DummyChain.trasaction.*;


public class Block {

	public String prevBlockHash;
	public String hash;	
	public Long timeStamp;
	public String transactionMerkleRoot;
	public int nonce;
	public HashMap<PublicKey, Account> accounts = new HashMap<PublicKey, Account>();
	public ArrayList<Transaction> transactions = new ArrayList<Transaction>();
	public PublicKey coinbase;
	
	public Block( String prevBlockHash, HashMap<PublicKey, Account> accounts, PublicKey coinbase) {
		this.prevBlockHash = prevBlockHash;		
		this.timeStamp = new Date().getTime();
		// copying accounts from previous block. Every block maintains accounts. This will be changed in later tutorials to world(global) state.
		this.accounts = accounts;
		this.coinbase = coinbase;
		this.hash = calculateHash();
		
	}
	
	public String calculateHash() {
		
		String calculatedHash = StringUtil.applySHA256(prevBlockHash + Long.toString(timeStamp) + Integer.toString(nonce) + transactionMerkleRoot + accounts.toString() + coinbase.toString());
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
				}else {
					if(processGenesisTransaction(transaction) != true) {
						System.out.println("Transaction failed to process. Discarded.");
						return false;
					}					
				}
				transactions.add(transaction);
				System.out.println("Transaction Successfully added to Block");
				return true;
	}
	
	
	public boolean processGenesisTransaction(Transaction transaction) {
		// Verify the signature
		if(transaction.verifiySignature() == false) {
			System.out.println("#Transaction Signature failed to verify");
			return false;
		}				
		accounts.put(transaction.reciepient, new Account(transaction.value));
		return true;		
	}
	
	
	public boolean processTransaction(Transaction transaction) {
		// Verify the signature
		if(transaction.verifiySignature() == false) {
			System.out.println("#Transaction Signature failed to verify");
			return false;
		}
		
		float transactionFee = TransactionFeeCalculator.calculateTransactionFee(transaction);
		
		//check if sender has funds to pay transaction fee
		if(!(getBalance(transaction.sender) >= transactionFee)) {
			System.out.println("Sender doesn't have enough funds to pay for transaction");
			return false;
		}
		
		// Transfer Transaction fee to the coinbase account		
		transferFunds(transaction.sender,  coinbase, transactionFee);
		
				
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
		getOrCreate(from).value -= value;
		getOrCreate(to).value += value;		
		return true;				
	}	
	
	public Account getOrCreate(PublicKey address) {
		if(accounts.get(address) != null) {
			return accounts.get(address);
		}else {
			accounts.put(address, new Account(0));
			return accounts.get(address);
		}
	  }
	
	
	public float getBalance(PublicKey address) {		
		return getOrCreate(address).value;
	}
	
	public boolean rewardCoinBase() {
		Account coinBaseAccount = getOrCreate(coinbase);
		coinBaseAccount.value += BlockRewardCalculator.calculateBlockReward();
		return true;
	}
	
	public String getMerkleRoot(ArrayList<Transaction> transactions) {
		if(transactions.size()==0) {
			return null;
		}
		MerkleTree tree = new MerkleTree();
	    ArrayList<String> hashList = new ArrayList<String>();
	    for(Transaction transaction : transactions) {
	    	hashList.add(transaction.transactionId);
	    }
	    tree.appendLeaves(hashList);
        String rootHash = tree.buildTree();
        return rootHash;
	}
	
	public void mineBlock(int difficulty) {	
		boolean rewardedSuccessfully = rewardCoinBase();
		this.transactionMerkleRoot = getMerkleRoot(transactions);
		if(!rewardedSuccessfully) {
			System.out.println("Block Rewarding is not successful. Mining Stopped");
		}
		String target = StringUtil.getDificultyString(difficulty); //Create a string with difficulty * "0" 
		while(! hash.substring(0, difficulty).equals(target)) {
			nonce ++;
			hash = calculateHash();
		}
		System.out.println("Block Mined!!! : " + hash);
	}
}
