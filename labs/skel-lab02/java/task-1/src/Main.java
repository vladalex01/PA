import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;



public class Main {

	// class SortValue implements Comparator<Obj> {
	// 	public double compare(Obj c1, Obj c2) {
	// 		return c1.value - c2.value;
	// 	}
	// }
	static class SortValue implements Comparator<Obj> {
		public int compare(Obj c1, Obj c2) {
			if (c1.value < c2.value) {
				return 1;
			} return -1;
		}
	}

	static class Obj {
		public int weight;
		public int price;
		public int id;
		public double value;

		public Obj() {
			weight = 0;
			price = 0;
			value = 0.0;
		}
	};

	static class Task {
		public final static String INPUT_FILE = "in";
		public final static String OUTPUT_FILE = "out";

		int n, w;
		Obj[] objs;
		ArrayList<Obj> candidates = new ArrayList<Obj>();

		private void readInput() {
			try {
				Scanner sc = new Scanner(new File(INPUT_FILE));
				n = sc.nextInt();
				w = sc.nextInt();
				objs = new Obj[n];
				for (int i = 0; i < n; i++) {
					objs[i] = new Obj();
					objs[i].weight = sc.nextInt();
					objs[i].price = sc.nextInt();
					objs[i].id = i;
					objs[i].value = (objs[i].price + 0.0) / (objs[i].weight + 0.0);
				}
				sc.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		private double getValue(int weight, int price) {
			return (price + 0.0) / (weight + 0.0);
		} 

		private void writeOutput(double result) {
			try {
				PrintWriter pw = new PrintWriter(new File(OUTPUT_FILE));
				pw.printf("%.4f\n", result);
				pw.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		private double getResult() {
			// TODO: Aflati profitul maxim care se poate obtine cu
			// obiectele date.
			double profit = 0;
			for (int i = 0; i < n; i++) {
				candidates.add(objs[i]);
			}
			// for (Obj candidate: candidates) {
			// 	System.out.println(candidate.id + " --- " + candidate.value);
			// }
			// System.out.println("*************************************");
			Collections.sort(candidates, new SortValue());
			// for (Obj candidate: candidates) {
			// 	System.out.println(candidate.id + " --- " + candidate.value);
			// }
			for (Obj candidate: candidates) {
				if (candidate.weight < w) {
					w -= candidate.weight;
					profit += candidate.price;
					continue;
				} else {
					profit += candidate.value * w;
					break;
				}
			}
			return profit;
		}


		public void solve() {
			readInput();
			writeOutput(getResult());
		}
	}

	public static void main(String[] args) {
		new Task().solve();
	}
}
