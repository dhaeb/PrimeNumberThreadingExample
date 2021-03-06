package de.eva.prime;


public class SearchForPrime {

	public static void main(String[] args) throws Exception {
		long timeBeforeThreadExecution = System.currentTimeMillis();
		Prime primeCalculator = new Prime(0, 100000);
		primeCalculator.createPrimeList();
		System.out.println(primeCalculator.getResultList());
		long timeAfterThreadExecution = System.currentTimeMillis();
		System.out.println(getTimeInSeconds(timeBeforeThreadExecution, timeAfterThreadExecution));
	}

	private static String getTimeInSeconds(long timeBeforeThreadExecution, long timeAfterThreadExecution) {
		return "The exection has taken " + (timeAfterThreadExecution - timeBeforeThreadExecution) / 1000.0d + " seconds";
	}

}
