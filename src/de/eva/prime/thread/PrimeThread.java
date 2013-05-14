package de.eva.prime.thread;

import java.util.List;

import de.eva.prime.Prime;


public class PrimeThread extends Thread {
	
	private Prime prime;

	public PrimeThread(int lowerBound, int upperBound) {
		prime = new Prime(lowerBound, upperBound);
	}

	@Override
	public void run() {
        prime.createPrimeList();
	}

	public List<Integer> getResultList(){
		return prime.getResultList();
	}
	
}