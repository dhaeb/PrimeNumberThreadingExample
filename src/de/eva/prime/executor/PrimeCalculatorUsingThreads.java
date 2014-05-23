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


/**
 * Darstellung eines Plots: Vorgegebenen Anzahl von Threads in Abhängigkeit zur Dauer der Primzahlberechnung 
 * 
 * @author Dan Häberlein
 *
 */
public class PrimeCalculatorUsingThreads {

	/**
	 * Einfache Klasse um ein beliebiges Paar von Werten in Java zu repräsentieren.
	 * Konkret werden Instanzen dieser Klasse benutzt um die Messwerte der Zeitmessung (y) für 
	 * die entsprechende Anzahl von Threads (x) zu repräsentieren.
	 * 
	 * @author Dan Häberlein
	 *
	 * @param <T>	The type of the first Element 
	 * @param <U>	The type of the second Element
	 */
	private static class Pair<T extends Comparable<T>, U>  implements Comparable<Pair<T, ?>>{
		
		/**
		 * Mit dieser Methode werden die Messpunkte in eine Form überführt,
		 * sodass sie für die Bibliothek JFreeChart ({@link JFreeChart} lesbar sind. 
		 * 
		 * @param Eine Liste von Messpunkten
		 * @return Ein Datensatz lesbar für ({@link JFreeChart}
		 */
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
	
	/**
	 * Mithilfe dieser Klasse wird ein JFreeChart erzeugt und in ein einfaches Diagramm in einem JFrame (Swing-Fenster) darstellt.  
	 * 
	 * @author Dan Häberlein
	 *
	 */
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

	/**
	 * Berechnet die Ausführungszeit der Primzahlberechnung bis 100000 von start bis end Threads.
	 * 
	 * @param start	Anzahl Treads mit welcher die Berechnung begonnen wird  
	 * @param end   Letzte Anzahl Threads welche noch berechnet werden soll, dannach abbruch
	 * @param step  Erhöhung von Start nach jeder Berechnung um step 
	 * @return
	 * @throws Exception
	 */
	private static List<Pair<Integer, Long>> calcForBounds(int start, int end, int step) throws Exception {
		List<Pair<Integer, Long>> resultList = new ArrayList<Pair<Integer, Long>>();
		for(int threadCount = start; threadCount <= end; threadCount+=step){
			resultList.add(executePrimeCalc(threadCount));
		}
		return resultList;
	}

	/**
	 * Berechnet die Primzahlen mit einer gegebenen Anzahl von Threads.
	 * 
	 * @param threadCount Die Anzahl an Threads, die zur Berechnung verwendet werden.
	 * @return Paardarstellung (Anzahl Threads / Zeit benötigt in Millisekunden)
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	private static Pair<Integer,Long> executePrimeCalc(int threadCount) throws InterruptedException, ExecutionException {
		long startTime = System.currentTimeMillis();
		PrimeCalculationUsingCallableParameterized.main(new String[]{Integer.toString(threadCount)});
		long endTime = System.currentTimeMillis() - startTime;
		return new Pair<Integer, Long>(threadCount, endTime);
	}
}
