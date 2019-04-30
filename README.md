# CustomBlockchain
This is a test blockchain to learn and practice concepts of blockchain


The aim of this tutorial series, is to help you build a picture of how one could develop blockchain technology.

In this tutorial we will :

Create your first (very) basic ‘blockchain’.
Implement a simple proof of work ( mining ) system.


Setting Up.
1. clone the repo and change the branch name
2. import to eclipse.
3. Run main method


Each block doesn’t just contain the hash of the block before it, but its own hash is in part, calculated from the previous hash. If the previous block’s data is changed then the previous block’s hash will change ( since it is calculated in part, by the data) in turn affecting all the hashes of the blocks there after. Calculating and comparing the hashes allow us to see if a blockchain is invalid.

What does this mean ? …Changing any data in this list, will change the signature and break the chain.



Now we need a way to check the integrity of our blockchain.
Lets create an isChainValid() Boolean method in the NoobChain class, that will loop through all blocks in the chain and compare the hashes. This method will need to check the hash variable is actually equal to the calculated hash, and the previous block’s hash is equal to the previousHash variable.

Any change to the blockchain’s blocks will cause this method to return false.

On the bitcoin network nodes share their blockchains and the longest valid chain is accepted by the network. What’s to stop someone tampering with data in an old block then creating a whole new longer blockchain and presenting that to the network ? Proof of work. The hashcash proof of work system means it takes considerable time and computational power to create new blocks. Hence the attacker would need more computational power than the rest of the peers combined.

Lets start mining blocks !!!
We will require miners to do proof-of-work by trying different variable values in the block until its hash starts with a certain number of 0’s.

Lets add an int called nonce to be included in our calculateHash() method, and the much needed mineBlock() method :


The mineBlock() method takes in an int called difficulty, this is the number of 0’s they must solve for. Low difficulty like 1 or 2 can be solved nearly instantly on most computers, i’d suggest something around 4–6 for testing. At the time of writing Litecoin’s difficulty is around 442,592.

Lets add the difficulty as a static variable to the NoobChain class :






