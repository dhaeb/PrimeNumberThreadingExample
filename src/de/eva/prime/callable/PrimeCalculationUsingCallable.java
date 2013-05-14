package de.eva.prime.callable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class PrimeCalculationUsingCallable {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		// create callable objects
		PrimeCallable firstPart = new PrimeCallable(0, 25001);
		PrimeCallable secondPart = new PrimeCallable(25001, 50000);
		PrimeCallable thirdPart = new PrimeCallable(50001, 75000);
		PrimeCallable fourthPart = new PrimeCallable(75001, 100000);
		// create thread pool for executing callables
		ExecutorService threadPool = Executors.newFixedThreadPool(4);
		// create future objects which will contain the result of the callable threads
		// the submit methods start callables automaticly in new threads
		Future<List<Integer>> willContainResultOfFirstPart = threadPool.submit(firstPart);
		Future<List<Integer>> willContainResultOfSecondPart = threadPool.submit(secondPart);
		Future<List<Integer>> willContainResultOfThridPart = threadPool.submit(thirdPart);
		Future<List<Integer>> willContainResultOfFourthPart = threadPool.submit(fourthPart);
		// collect results in a resultlist
		List<Integer> resultList = new ArrayList<Integer>();
		resultList.addAll(willContainResultOfFirstPart.get());
		resultList.addAll(willContainResultOfSecondPart.get());
		resultList.addAll(willContainResultOfThridPart.get());
		resultList.addAll(willContainResultOfFourthPart.get());
		// print result
		System.out.println(resultList);
		// clean up callables
		threadPool.shutdown();
	}
}

