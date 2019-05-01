package com.custom.DummyChain;

import java.security.PublicKey;
import java.security.Security;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.custom.DummyChain.Account.Account;
import com.custom.DummyChain.trasaction.Transaction;
import com.custom.DummyChain.wallet.Wallet;
import com.google.gson.GsonBuilder;

/**
 * Custom Blockchain
 *
 */
public class DummyChain 
{
	public static List<Block> blockchain = new ArrayList<Block>();
	public static int difficulty = 3;
	public static Wallet walletA;
	public static Wallet walletB;
	public static Transaction genesisTransaction;

	
    public static void main( String[] args )
    {
    	
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider()); //Setup Bouncey castle as a Security Provider

		//Create wallets:
		walletA = new Wallet();
		walletB = new Wallet();		
		Wallet coinbase = new Wallet();
		//create genesis transaction, which sends 100 DummyCoin to walletA: 
		genesisTransaction = new Transaction(coinbase.publicKey, walletA.publicKey, 100f);
		genesisTransaction.generateSignature(coinbase.privateKey);	 //manually sign the genesis transaction	
		genesisTransaction.transactionId = "0"; //manually set the transaction id								


		System.out.println("Creating and Mining Genesis block... ");
		Block genesis = new Block("0", new HashMap<PublicKey, Account>());
		genesis.addTransaction(genesisTransaction);
		addBlock(genesis);        				
		System.out.println("\nWalletA's balance is: " + walletA.getBalance());		

		// copying accounts from previous block. Every block maintains accounts. This will be changed in later tutorials to world(global) state.
		System.out.println("Creating and Mining block1... ");
		Block block1 = new Block(genesis.hash, (HashMap<PublicKey, Account>) genesis.accounts.clone());
		System.out.println("\nWalletA is Attempting to send funds (40) to WalletB...");
		block1.addTransaction(walletA.sendFunds(walletB.publicKey, 40f));
		addBlock(block1);
		System.out.println("\nWalletA's balance is: " + walletA.getBalance());
		System.out.println("WalletB's balance is: " + walletB.getBalance());
		
		
		System.out.println("Creating and Mining block2... ");
		Block block2 = new Block(block1.hash, (HashMap<PublicKey, Account>) block1.accounts.clone());
		System.out.println("\nWalletA Attempting to send more funds (1000) than it has...");
		block2.addTransaction(walletA.sendFunds(walletB.publicKey, 1000f));
		addBlock(block2);
		System.out.println("\nWalletA's balance is: " + walletA.getBalance());
		System.out.println("WalletB's balance is: " + walletB.getBalance());
		

		System.out.println("Creating and Mining block3... ");
		Block block3 = new Block(block2.hash, (HashMap<PublicKey, Account>) block2.accounts.clone());
		System.out.println("\nWalletB is Attempting to send funds (20) to WalletA...");
		block3.addTransaction(walletB.sendFunds( walletA.publicKey, 20));
		System.out.println("\nWalletA's balance is: " + walletA.getBalance());
		System.out.println("WalletB's balance is: " + walletB.getBalance());
		
        		
        System.out.println("Blockchain is valid - "+isChainValid());
    }
    
    public static boolean isChainValid() {
    	Block currentBlock;
    	Block previousBlock;
		String hashTarget = new String(new char[difficulty]).replace('\0', '0');

    	
    	for(int i=1; i< blockchain.size(); i++ ) {
    		currentBlock = blockchain.get(i);
    		previousBlock = blockchain.get(i-1);
    		
    		// Validating if hash is computed correctly for current block
    		String hash = currentBlock.calculateHash();
    		if(!currentBlock.hash.equals(hash)) {
    			System.out.println("hash is computed incorrectly");
    			return false;
    		}
    		
    		// Validating if previousblock hash matches
    		if(!previousBlock.hash.equals(currentBlock.prevBlockHash)) {
    			System.out.println("previous block hash doesn't match");
    			return false;
    		}
    		
    		// Validate if block is mined correctly
    		if(!currentBlock.hash.substring(0, difficulty).equals(hashTarget)) {
    			System.out.println("This block hasn't been mined");
				return false;
    		}   
    		    	
    	}
    	return true;
    }
    
    public static void addBlock(Block newBlock) {
    	newBlock.mineBlock(difficulty);
    	blockchain.add(newBlock);
    }
}
