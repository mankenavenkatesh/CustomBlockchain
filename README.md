
# CustomBlockchain

This is a test blockchain to learn and practice concepts of blockchain

The aim of this tutorial series, is to help you build a picture of how one could develop blockchain technology.

In this tutorial we will :

- Add Transactions to block. Digitally Sign the transactions.

- Ownership of value through Accounts.

  
  

## - Add Transactions to block. Digitally Sign the transactions.

#### Q. In a P2P network how to transfer value? 
**Solution -** 
 - Add Transactions to block.
 - Each Transaction will have from, to, value.

#### Q. How to ensure the transaction propagated in the network is not tampered by Peers?
**Solution -** 
 - Sender can Digitally Sign the transaction. 
 - Miners validate Digital Signature of transaction.


## - Ownership of value through Accounts.

#### Q. In a P2P network how to prove the ownership of DummyCoin?
**Solution -** 
 - Manage all the user accounts in a block.
 - Add User Accounts to the block header.
 - User Accounts is part of the hash now. So tampering of User Accounts will lead to hash change which leads to breakage of links between blocks.

