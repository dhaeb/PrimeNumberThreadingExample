package de.eva.prime.thread;

import java.util.ArrayList;
import java.util.List;

public class PrimeCalculationUsingThread {
	
	public static void main(String[] args) throws InterruptedException {
		//create Thread / prime objects
		PrimeThread firstThread = new PrimeThread(0, 25001);
		PrimeThread secondThread = new PrimeThread(25001, 50000);
		PrimeThread thirdThread = new PrimeThread(50001, 75000);
		PrimeThread fourthThread = new PrimeThread(75001, 100000);
		// start calculation
		firstThread.start();
		secondThread.start();
		thirdThread.start();
		fourthThread.start();
		//wait for all results
		firstThread.join();
		secondThread.join();
		thirdThread.join();
		fourthThread.join();
		// create result list and collect result from classes
		List<Integer> mainResultList = new ArrayList<Integer>(100000);
		mainResultList.addAll(firstThread.getResultList());
		mainResultList.addAll(secondThread.getResultList());
		mainResultList.addAll(thirdThread.getResultList());
		mainResultList.addAll(fourthThread.getResultList());
		// print results
		System.out.println(mainResultList);
	}
}
