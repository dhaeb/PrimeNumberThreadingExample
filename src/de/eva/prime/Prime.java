package de.eva.prime;


/**
 * Class to calculate prime numbers using the sequential prime number test.
 * @author Christian Maehlig, Dan Haeberlein
 *
 */
public class Prime {

	protected int lowerBound;
	protected int upperBound;
	
	public Prime(int lowerBound, int upperBound) {
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
	}

	public void calculatePrimes() {
		/* simple prime number test, having the complexity of O(n) 
		 * good example for multithreading in terms of complexity */
		if (lowerBound % 2 == 0){
			++lowerBound;
		}
		for (int i = lowerBound; i <= upperBound; i += 2) {
			boolean isPrime = true;
			for (int j = 3; j < i; ++j) {
				if (i % j == 0) {
					isPrime = false;
					break;
				}
			}
			if (isPrime == true && i >= 2){
				System.out.println(i);
			}
		}
	}
}
