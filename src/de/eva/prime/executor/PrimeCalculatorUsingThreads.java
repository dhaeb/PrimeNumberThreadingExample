package de.eva.prime.executor;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import de.eva.prime.callable.PrimeCalculationUsingCallableParameterized;

public class PrimeCalculatorUsingThreads {

	private static class Pair<T extends Comparable<T>, U>  implements Comparable<Pair<T, ?>>{
		
		public static CategoryDataset wrapPairs(Iterable<Pair<Integer, Long>> pairs) {
			DefaultCategoryDataset returnable = new DefaultCategoryDataset();
			for(Pair<Integer, Long> p : pairs){
				returnable.addValue(p.get_2(), "graph",p.get_1()); 
			}
			return returnable;
		}
		
		private T _1;
		private U _2;

		public Pair(T _1, U _2) {
			this._1 = _1;
			this._2 = _2;
		}
		public T get_1() {
			return _1;
		}
		public U get_2() {
			return _2;
		}
		@Override
		public String toString() {
			return "Pair [_1=" + _1 + ", _2=" + _2 + "]";
		}
		@Override
		public int compareTo(Pair<T, ?> arg0) {
			return _1.compareTo(arg0.get_1());
		}
	
	}
	
	private static class ChartFrame extends JFrame {
		private static final long serialVersionUID = 1L;

		public ChartFrame(CategoryDataset dataset) {
			JFreeChart createLineChart = ChartFactory.createLineChart("Count of Threads VS Execution Time", 
																	   "Count of Threads", 
																	"Time taken [MS]", dataset);
			this.setContentPane(new ChartPanel(createLineChart));;
			this.setPreferredSize(new Dimension(1000, 600));
			this.pack();
			this.setVisible(true);
			this.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					ChartFrame.this.dispose();
					super.windowClosing(e);
				}
			});
		}
	}
	
	public static void main(String[] args) throws Exception {
		List<Pair<Integer, Long>> resultList = new ArrayList<Pair<Integer, Long>>();
		resultList.addAll(calcForBounds(1, 10, 1));
		resultList.addAll(calcForBounds(20, 100, 10));
		resultList.addAll(calcForBounds(10000, 10000, 2500));
		new ChartFrame(Pair.wrapPairs(resultList));
	}

	private static List<Pair<Integer, Long>> calcForBounds(int start, int end, int step) throws Exception {
		List<Pair<Integer, Long>> resultList = new ArrayList<Pair<Integer, Long>>();
		for(int threadCount = start; threadCount <= end; threadCount+=step){
			resultList.add(executePrimeCalc(threadCount));
		}
		return resultList;
	}

	private static Pair<Integer,Long> executePrimeCalc(int threadCount) throws InterruptedException, ExecutionException {
		long startTime = System.currentTimeMillis();
		PrimeCalculationUsingCallableParameterized.main(new String[]{Integer.toString(threadCount)});
		long endTime = System.currentTimeMillis() - startTime;
		return new Pair<Integer, Long>(threadCount, endTime);
	}
}
