package de.eva.prime.runnables;

import java.util.ArrayList;
import java.util.List;

public class PrimeCalculationUsingRunnable {

	public static void main(String[] args) throws InterruptedException {
		// create runnable objects
		PrimeRunnable firstPart = new PrimeRunnable(0, 25001);
		PrimeRunnable secondPart = new PrimeRunnable(25001, 50000);
		PrimeRunnable thirdPart = new PrimeRunnable(50001, 75000);
		PrimeRunnable fourthPart = new PrimeRunnable(75001, 100000);
		// create threads, which executes the runnables
		Thread firstThread = new Thread(firstPart);
		Thread secondThread = new Thread(secondPart);
		Thread thirdThread = new Thread(thirdPart);
		Thread fourthThread = new Thread(fourthPart);
		// start the threads
		firstThread.start();
		secondThread.start();
		thirdThread.start();
		fourthThread.start();
		// wait for the results
		firstThread.join();
		secondThread.join();
		thirdThread.join();
		fourthThread.join();
		// create result list and collect result from classes
		List<Integer> mainResultList = new ArrayList<Integer>(100000);
		mainResultList.addAll(firstPart.getResultList());
		mainResultList.addAll(secondPart.getResultList());
		mainResultList.addAll(thirdPart.getResultList());
		mainResultList.addAll(fourthPart.getResultList());
		// print results
		System.out.println(mainResultList);
	}

}
