
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

#### Q. What if peer nodes in the network tampers transaction data? How will merkle tree will help? 
**Solution -** 
- The parent-to-child hashing process/structure described above results in a key property of Merkle Trees: they provides a means to prove the integrity and validity of your data.
- For example, if we were to change the value of a data block, the entire path leading to the ‘root hash’ would also be changed. Therefore, if we hold the value of the root hash, we could verify the consistency of data by rebuilding the trie to get the root hash and then compare it with the root hash value in which we are holding. It is impossible to fake data without changing the value of the root.

#### Q. How is transaction verifed by light nodes using merkle tree? 
**Solution -** 
- The advantage of the Merkle tree is that the node can verify the legitimacy of a transaction without downloading the entire block. 
- To do this, you only need to trade Hash, Merkle tree root hash and Merkle paths.
- 
#### Merkle Trees | Beneficial Properties
The key benefits of Merkle Trees consist of the following properties:
- Data Consistency / Verification
- Merkle Tree proofs are computationally easy and fast
- Merkle Tree proofs require only a small chunks of data to be broadcasted across a network



## WorldState merkle tree
#### Q. Before this tutorial, World state is present in each block. World state contains all user accounts.Now when miner mines the block and propagates the block, he has to send all the account information. How can we optimize this?

**Solution -** 
- We can use concept of merkle trees for the same. Instead of putting world state in the block, Put the world state outside the block and just add the merkle root to the block. 

- This merkle root is the root of merkle tree created using all the account information after executing the transactions present in the block.

- This reduces the size of block and optimizes the block propagation.

 #### Q. This is all good. but still each block has a separate merkle tree holding all the world state. As number of accounts grows into millions, it becomes difficult to maintain merkle tree per block . How can we solve this?
 **Solution -** 

- Can we create single world state which contains all the accounts.
- This world state is updated with account info after executing the transactions. 
- We can create a merkle tree of updated world state and  take the merkle root and store it in the block. Once merkle root hash is added to block, delete the merkle tree. 
- This will solve the problem of having separate merkle tree. There will be only one world state (Global state). The merkle root hash of latest block will represent the global world state merkle tree.
- For the next block create, again process repeats.


 #### Q. But as there is only one global state and one merkle tree, How will peer nodes validate previous blocks. To validate previous blocks, they need the instance of world state at the time when previous block was created?
 **Solution -** 

# Patricia Merkle Trees
https://medium.com/shyft-network-media/understanding-trie-databases-in-ethereum-9f03d2c3325d













