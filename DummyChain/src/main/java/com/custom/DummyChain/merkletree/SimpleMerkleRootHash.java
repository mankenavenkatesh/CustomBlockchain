package com.custom.DummyChain.merkletree;

import java.util.ArrayList;

public class SimpleMerkleRootHash {	
	
	public static String getMerkleRoot(ArrayList<String> hashList, int i, int j) {
		
		String merkleRootHash = null;		
		if(i == j) {
			return hashList.get(i);
		}
		if(i+1 == j) {
			merkleRootHash = mergeHash(getMerkleRoot(hashList, i, i ), getMerkleRoot(hashList, j, j));
			return merkleRootHash;
		}
		
		merkleRootHash = mergeHash(getMerkleRoot(hashList, i, i+ (j-i) / 2 ), getMerkleRoot(hashList, (i+ ((j-i) / 2)) +1, j));			
		return merkleRootHash;
	}
	
	
	public static String mergeHash(String hash1, String hash2) {
		return hash1+hash2;
	}
	
	
	public static void main(String[] args) {
		
		ArrayList<String> hashList = new ArrayList<String>();
		hashList.add("1");
		hashList.add("2");
		hashList.add("3");
		hashList.add("4");
		hashList.add("5");
		hashList.add("6");
		hashList.add("7");
		hashList.add("8");
		hashList.add("9");
		hashList.add("10");
		hashList.add("11");
		hashList.add("12");
		hashList.add("13");
		hashList.add("14");
		hashList.add("15");
		hashList.add("16");
		String merkleRoot = getMerkleRoot(hashList, 0 , hashList.size()-1);
		System.out.println(merkleRoot);
	}
	
	
}
