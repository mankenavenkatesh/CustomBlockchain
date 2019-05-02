
# CustomBlockchain

This is a test blockchain to learn and practice concepts of blockchain

The aim of this tutorial series, is to help you build a picture of how one could develop blockchain technology.

In this tutorial we will learn:

- Merkle Trees
- Transaction merkle tree
- World State Merkle tree

  
## Merkle Tree

#### Q. In a P2P network will all nodes have all the blockchain data?
**Solution -** 
 - Each node participates in verifying and propagating transaction and block information, discovering and maintaining connections to peer nodes. A full node contains entire blockchain with all the data including transactions and storage.
 - Considering no of blocks generated and huge no of transactions inside a block, It's is difficult for every node to maintain all the data.
 - it is not realistic to have everyone run a complete node. 
 
    
#### Q. If entire blockchain is not present, how will nodes verify the blocks?
**Solution -** 
- SPV - simple payment verification
-  SPV is a lightweight nodes, it does not need to download all the data block chain, also does not require authentication and transaction data blocks . 
- Conversely, when the SPV wants to verify the validity of a transaction, it retrieves some of the required data from the full node it is connected to. 
- This mechanism ensures that multiple SPV light wallet nodes can be run in the case of only one full node.
- In order to make SPV possible, there is a need to check whether a block contains a transaction without downloading the block data in full. 
- This is where the Merkle Tree comes into play.


## Transaction merkle tree
#### Q. How is transaction merkle tree constructed? 
**Solution -** 
- Each block constructs a Merkle Tree, which starts from the bottommost leaf node, and each transaction's hash is a leaf node (the dual SHA256 algorithm used in Bitcoin). The number of leaf nodes must be an even number, but not every block can contain even transaction data. If there are odd transaction data, the last transaction data will be copied (this only happens in the Merkle Tree, not in the block).
- Moving from bottom to top, leaf nodes are grouped in pairs, their hash values ​​are joined together, and new hash values ​​are calculated again on this basis. The new hash forms a new tree node. This process is continually repeated until there is only one tree node called the root node. 
- The hash of this root node is the only representative of the transaction data in the block, it will be saved to the block header and used to participate in the calculation of the POW system.

#### Q. How is transaction verifed by light nodes using merkle tree? 
**Solution -** 
- The advantage of the Merkle tree is that the node can verify the legitimacy of a transaction without downloading the entire block. 
- To do this, you only need to trade Hash, Merkle tree root hash and Merkle paths.
- 
