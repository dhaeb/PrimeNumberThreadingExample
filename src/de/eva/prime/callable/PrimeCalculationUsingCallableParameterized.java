package de.eva.prime.callable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class PrimeCalculationUsingCallableParameterized {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		// create executor service to coordinate thread handling
		ExecutorService executor = Executors.newCachedThreadPool();
		// create list of callable instances
		List<Callable<List<Integer>>> threadList = new ArrayList<Callable<List<Integer>>>();
		// set calculation configuration
		int maxPrime = 100000;
		int numberOfThreads = 4;
		if(args.length != 0)
			numberOfThreads = Integer.parseInt(args[0]);
		int partThreads = (maxPrime / numberOfThreads);
		// add callables to the list of threads - calculate the bounds in which they will search for prime numbers
		for(int i = 0; i < numberOfThreads ; i++){
			threadList.add(new PrimeCallable(i * partThreads + 1, partThreads * (i+1)));
		}
		// invoke all callables (start all threads) 
		// NOTE: the Future object will contain the result, if the callable is done with the calculatoin
		List<Future<List<Integer>>> futureList = executor.invokeAll(threadList);
		// create and fill result list / every future.get() waits for the result of the callable
		ArrayList<Integer> resultList = new ArrayList<Integer>();
		for(Future<List<Integer>> currentFuture : futureList){
			resultList.addAll(currentFuture.get());
		}
		//print result
		System.out.println(resultList);
		// cleanup callables
		executor.shutdown();
	}
}

