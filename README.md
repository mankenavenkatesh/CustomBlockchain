
# CustomBlockchain

This is a test blockchain to learn and practice concepts of blockchain

The aim of this tutorial series, is to help you build a picture of how one could develop blockchain technology.

In this tutorial we will :

- Block Rewards
- Gas fee
- Coinbase Transactions

  
## Block Rewards

#### Q. In a P2P network how are new DummyCoins Generated or mined?
**Solution -** 
 - Every mined block will have a block reward which is added as part of coinbase transaction.
 - This block reward is nothing but new Dummy Coins.
 - This block reward keep changing over a time period based on block height.
 - This block reward goes to the miner as part of coinbase transactions.


#### Q. what if miner simply reward themselves excess of the block reward and total transaction fee?
**Solution -** 
 - A miner that rewarded themselves incorrectly, would find that block being deemed invalid by other participants in the network.
 - Thus, that block would not be included in the blockchain and that miner would not be able to recoup the financial costs taken on during the proof of work mining process.


## Transaction fee
- Transaction fee is determined by the gas used * price per unit of gas.


## Coinbase Transactions

#### Q. In a P2P network how are miners incentivised for processing the blocks?
**Solution -** 
 - Miners will add one extra transactions to the top of transactions list in the block they are mining.
 - This Transaction is called coinbase transaction. Because, This transaction includes the new coins generated as block reward.
 - This transaction will have "to" address as miner address.
 - This transaction will have "from" address and digital signature of ??.
 - This transaction will have value which is sum of block reward + sum of all transaction fees.

#### Q. What if miner spends the Dummycoins in coinbase transactions and later the block turns out to be stale block  after a fork in the blockchain.?

**Solution -** 
- Another important feature of a coinbase transaction is that DummyCoins involved in the transaction cannot be spent until they have received at least 100 block confirmations. 



#### Q. Will genesis block contains coinbase transaction? 

**Solution -** 
- Yes.  


