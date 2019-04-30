package com.custom.DummyChain;

import java.util.Date;

import com.custom.DummyChain.util.StringUtil;

public class Block {

	public String prevBlockHash;
	public String hash;
	private String data;
	public Long timeStamp;
	public int nonce;
	
	public Block(String data, String prevBlockHash) {
		this.prevBlockHash = prevBlockHash;
		this.data = data;
		this.timeStamp = new Date().getTime();
		this.hash = calculateHash();
	}
	
	public String calculateHash() {
		
		String calculatedHash = StringUtil.applySHA256(prevBlockHash + Long.toString(timeStamp) + Integer.toString(nonce) + data);
		return calculatedHash;
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
