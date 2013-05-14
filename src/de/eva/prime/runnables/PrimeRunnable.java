package de.eva.prime.runnables;

import de.eva.prime.Prime;


public class PrimeRunnable extends Prime implements Runnable {
	
	public PrimeRunnable(int lowerBound, int upperBound) {
		super(lowerBound, upperBound);
	}

	@Override
	public void run() {
        executePrimeCalculation();
	}

	private void executePrimeCalculation() {
		int start = this.lowerBound;
        int end = this.upperBound;

        if (start == 2)
        	System.out.println(2);
        if (start % 2 == 0)
            ++start;
        for (int i = start; i <= end; i += 2) 
        {
            boolean prime = true;
            for (int j = 3; j < i; ++j)
            {
                if (i % j == 0) 
                {
                    prime = false;
                    break;
                }
            }
            if (prime == true && i >= 2)
            	resultList.add(i);
        }
	}

}