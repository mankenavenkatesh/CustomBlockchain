package com.custom.DummyChain.merkletree;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

public class MerkleTree {

	public MerkleNode rootNode;
	public ArrayList<MerkleNode> leaves;
	public ArrayList<MerkleNode> nodes;
	
   public MerkleTree() {
       this.nodes = new ArrayList<MerkleNode>();
       this.leaves = new ArrayList<MerkleNode>();
   }
   
   public List<MerkleNode> appendLeaves(ArrayList<String> hashList) {	
	  List<MerkleNode> nodes = new ArrayList<MerkleNode>();	  
	  for(int i=0;i<hashList.size();i++) {
		  nodes.add(this.appendLeaf(hashList.get(i)));
	  }
	  return nodes;
   }
   
   public MerkleNode appendLeaf(String hash) {	
	  return this.appendLeaf(new MerkleNode(hash));	   
   }
   
   public MerkleNode appendLeaf(MerkleNode node) {
       this.nodes.add(node);
       this.leaves.add(node);
       return node;
   }
   
   public String buildTree() {
	   if (this.leaves.size() <= 0) throw new InvalidParameterException("Cannot add to a tree with no leaves!");
       this.buildTree(this.leaves);
       return this.rootNode.getHash();
   }
   
   public void buildTree(List<MerkleNode> nodes) {
	   if(nodes.size() ==1) {
		   this.rootNode = nodes.get(0);
	   }
	   else {
		   ArrayList<MerkleNode> parents = new ArrayList<MerkleNode>();		   
		   for(int i=0;i < nodes.size(); i+=2) {
			   if(i+1 < nodes.size()) {
				   MerkleNode node = new MerkleNode(nodes.get(i), nodes.get(i+1));
				   parents.add(node);
			   }else {
				   MerkleNode node = new MerkleNode(nodes.get(i), null);
				   parents.add(node);
			   }
		   }		   
		   buildTree(parents);
	   }	   
   }
   private MerkleNode findLeaf(String hash) {
       return this.leaves.stream()
               .filter((leafnode) -> hash == leafnode.getHash())
               .findFirst()
               .orElse(null);
   }
   
   public List<MerkleProofHash> auditProof(String leafHash) {
       List<MerkleProofHash> auditTrail = new ArrayList<MerkleProofHash>();

       MerkleNode leafNode = this.findLeaf(leafHash);

       if (leafNode != null) {
           if (leafNode.getParentNode() == null) throw new InvalidParameterException("Expected leaf to have a parent!");
           MerkleNode parent = leafNode.getParentNode();
           this.buildAuditTrail(auditTrail, parent, leafNode);
       }

       return auditTrail;
   }
   
   public void buildAuditTrail(List<MerkleProofHash> auditTrail, MerkleNode parent, MerkleNode child) {
	   if (parent != null) {
           if (child.getParentNode() != parent) {
               throw new InvalidParameterException("Parent of child is not expected parent!");
           }
           
	   MerkleNode nextChild = parent.getLeftNode()==child ? parent.getRightNode():parent.getLeftNode();
	   MerkleProofHash.Branch direction = parent.getLeftNode()==child ? MerkleProofHash.Branch.RIGHT:MerkleProofHash.Branch.LEFT;
	   if (nextChild != null) auditTrail.add(new MerkleProofHash(nextChild.getHash(), direction));	   
	   
	   buildAuditTrail(auditTrail, parent.getParentNode(), child.getParentNode());
   }
   }
   
   public static boolean verifyAudit(String rootHash, String leafHash, List<MerkleProofHash> auditTrail) {
       if (auditTrail.size() <= 0) throw new InvalidParameterException("Audit trail cannot be empty!");

       String testHash = leafHash;

       for (MerkleProofHash auditHash : auditTrail) {
           testHash = auditHash.direction == MerkleProofHash.Branch.RIGHT
                   ? testHash+ auditHash.hash
                   : auditHash.hash+testHash;
       }

       return testHash.equals(rootHash);
   }

}
