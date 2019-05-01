package com.custom.DummyChain.Calculator;

import com.custom.DummyChain.DummyChain;

public class BlockRewardCalculator {

	public static float calculateBlockReward() {
		// Block Reward is calculated based on Block Height. Let's say Every 2 blocks, block reward gets halved.
		int  blockchainSize = DummyChain.blockchain.size();
		return DummyChain.blockReward;
	}
}
