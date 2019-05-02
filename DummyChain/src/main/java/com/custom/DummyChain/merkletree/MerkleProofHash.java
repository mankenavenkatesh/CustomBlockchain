package com.custom.DummyChain.merkletree;

public class MerkleProofHash {

	enum Branch{
		RIGHT,
		LEFT,
		OLD_ROOT
	}
	
	  public String hash;
	  public Branch direction;
	  
	  public MerkleProofHash(String hash, Branch direction) {
	        this.hash = hash;
	        this.direction = direction;
	    }

	    public String getHash() {
	        return hash;
	    }

	    public Branch getDirection() {
	        return direction;
	    }

	    @Override
	    public String toString() {
	        String hash = this.hash.toString();
	        String direction = this.direction.toString();
	        return hash.concat("  is ".concat(direction).concat(" Child"));
	    }
}
