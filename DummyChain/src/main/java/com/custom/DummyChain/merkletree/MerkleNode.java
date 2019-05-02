package com.custom.DummyChain.merkletree;

public class MerkleNode {

	public String hash;
	public MerkleNode leftNode;
	public MerkleNode rightNode;
	public MerkleNode parentNode;
	
	public MerkleNode() {

	}
	
	public MerkleNode(String hash) {
		this.hash = hash;
	}
	
	public MerkleNode(MerkleNode left, MerkleNode right) {
		this.leftNode = left;
		this.rightNode = right;
		this.leftNode.parentNode = this;
		
		if(this.rightNode != null) {
			this.rightNode.parentNode = this;
		
		}
		this.computeHash();
	}
	
	public void computeHash() {
		if(this.rightNode != null) {			
			this.hash = this.leftNode.hash + this.rightNode.hash;
		}
		else{
			this.hash = this.leftNode.hash; 
		}
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public MerkleNode getLeftNode() {
		return leftNode;
	}

	public void setLeftNode(MerkleNode leftNode) {
		this.leftNode = leftNode;
	}

	public MerkleNode getRightNode() {
		return rightNode;
	}

	public void setRightNode(MerkleNode rightNode) {
		this.rightNode = rightNode;
	}

	public MerkleNode getParentNode() {
		return parentNode;
	}

	public void setParentNode(MerkleNode parentNode) {
		this.parentNode = parentNode;
	}
	
	
	
}
