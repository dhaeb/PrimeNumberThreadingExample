package de.eva.prime.callable;

import java.util.List;
import java.util.concurrent.Callable;

import de.eva.prime.Prime;

public class PrimeCallable extends Prime implements Callable<List<Integer>> {

	public PrimeCallable(int lowerBound, int upperBound) {
		super(lowerBound, upperBound);
	}

	@Override
	public List<Integer> call() throws Exception {
		createPrimeList();
		return getResultList();
	}
	
}
