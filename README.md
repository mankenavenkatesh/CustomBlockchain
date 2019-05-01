
# CustomBlockchain

This is a test blockchain to learn and practice concepts of blockchain

The aim of this tutorial series, is to help you build a picture of how one could develop blockchain technology.

In this tutorial we will :

- Block Rewards
- Gas fee
- Coinbase Account Transfer

  
## Block Rewards

#### Q. In a P2P network how are new DummyCoins Generated or mined?
**Solution -** 
 - Every mined block will have a block reward which is added to coinbase Accont as part of block mining.
 - This block reward is nothing but new Dummy Coins.
 - This block reward keep changing over a time period based on block height.


#### Q. what if miner simply reward themselves excess of the block reward and total transaction fee?
**Solution -** 
 - A miner that rewarded themselves incorrectly, would find that block being deemed invalid by other participants in the network.
 - Thus, that block would not be included in the blockchain and that miner would not be able to recoup the financial costs taken on during the proof of work mining process.


## Transaction fee
- Transaction fee is determined by the gas used * price per unit of gas.


## Coinbase Account Transfer

#### Q. In a P2P network how are miners incentivised for processing the blocks?
**Solution -** 
 - when transaction is added to candidate block, transaction processor will process the transaction and transfer the transaction fee to the coinbase account stored as a field in the candidate block.
 - Once all transactions are added to the block, block will be mined. 
 - During mining proccess, block reward is added to the coinbase account. 
- This is how miners get paid for the work done.
