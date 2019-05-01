package com.custom.DummyChain.Calculator;

import com.custom.DummyChain.trasaction.Transaction;

public class TransactionFeeCalculator {

	public static float calculateTransactionFee(Transaction transaction) {
		// As this is simple transaction to transfer value, fee is constant - 1 Dummy Coin
		return 1f;
	}
}
