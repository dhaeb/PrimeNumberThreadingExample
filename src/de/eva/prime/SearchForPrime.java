package de.eva.prime;

import de.eva.prime.callable.PrimeCalculationUsingCallable;
import de.eva.prime.callable.PrimeCalculationUsingCallableParameterized;
import de.eva.prime.runnables.PrimeCalculationUsingRunnable;
import de.eva.prime.thread.PrimeCalculationUsingThread;

public class SearchForPrime {

	public static void main(String[] args) throws Exception {
		long timeBeforeThreadExecution = System.currentTimeMillis();
		
		Prime primeCalculator = new Prime(0, 100000);
		primeCalculator.createPrimeList();
		System.out.println(primeCalculator.getResultList());
		
//		PrimeCalculationUsingCallable.main(args);
//		PrimeCalculationUsingCallableParameterized.main(new String[]{"10000"});
//		PrimeCalculationUsingRunnable.main(args);
//		PrimeCalculationUsingThread.main(args);
		
		long timeAfterThreadExecution = System.currentTimeMillis();
		System.out.println(getTimeInSeconds(timeBeforeThreadExecution, timeAfterThreadExecution));
	}

	private static String getTimeInSeconds(long timeBeforeThreadExecution, long timeAfterThreadExecution) {
		return "The exection has taken " + (timeAfterThreadExecution - timeBeforeThreadExecution) / 1000.0d + " seconds";
	}

}
