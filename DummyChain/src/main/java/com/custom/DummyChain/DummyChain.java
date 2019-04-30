package com.custom.DummyChain;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.GsonBuilder;

/**
 * Custom Blockchain
 *
 */
public class DummyChain 
{
	public static List<Block> blockchain = new ArrayList<Block>();
	public static int difficulty = 5;
	
	
    public static void main( String[] args )
    {
        // add blocks to our blockchain
        System.out.println("Trying to Mine block 1... ");        
        addBlock(new Block("Hi..i am first block", "0"));
        
        
        System.out.println("Trying to Mine block 2... ");
        addBlock(new Block("Hi...i am 2nd block", blockchain.get(blockchain.size()-1).hash));
        
        
        System.out.println("Trying to Mine block 3... ");
        addBlock(new Block("Hi...i am 3rd block", blockchain.get(blockchain.size()-1).hash));
        
		String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);		
		System.out.println(blockchainJson);
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
