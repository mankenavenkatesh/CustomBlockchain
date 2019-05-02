package com.custom.DummyChain.merkletree;

import java.util.ArrayList;
import java.util.List;

public class TestMerkleTree {

	public static void main(String[] args) {
	      MerkleTree tree = new MerkleTree();
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
			tree.appendLeaves(hashList);
	        String rootHash = tree.buildTree();
	        System.out.println(rootHash);
	        
	        List<MerkleProofHash> auditTrail = tree.auditProof("10");
	        System.out.println(auditTrail);
	        
	        System.out.println(tree.verifyAudit(rootHash, "10", auditTrail));
	        
	}
}
