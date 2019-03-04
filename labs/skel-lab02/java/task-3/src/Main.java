import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

	static class SortHomework1 implements Comparator<Homework> {
		public int compare(Homework c1, Homework c2) {
			if (c1.score == c2.score) {
				return c1.deadline - c2.deadline;
			} else {
				return c2.score - c1.score;
			}
		}
	}
	static class SortHomework2 implements Comparator<Homework> {
		public int compare(Homework c1, Homework c2) {
			if (c1.deadline == c2.deadline) {
				return c2.score - c1.score;
			} else {
				return c1.deadline - c2.deadline;
			}
		}
	}

	static class Homework {
		public int deadline;
		public int score;

		public Homework() {
			deadline = 0;
			score = 0;
		}
	}

	static class Task {
		public final static String INPUT_FILE = "in";
		public final static String OUTPUT_FILE = "out";

		int n;
		Homework[] hws;
		ArrayList<Homework> homework = new ArrayList<Homework>();

		private void readInput() {
			try {
				Scanner sc = new Scanner(new File(INPUT_FILE));
				n = sc.nextInt();
				hws = new Homework[n];
				for (int i = 0; i < n; i++) {
					hws[i] = new Homework();
					hws[i].deadline = sc.nextInt();
					hws[i].score = sc.nextInt();
				}
				sc.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		private void writeOutput(int result) {
			try {
				PrintWriter pw = new PrintWriter(new File(OUTPUT_FILE));
				pw.printf("%d\n", result);
				pw.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		private int getMax(int a, int b) {
			if (a > b) {
				return a;
			} return b;
		}

		private int getResult() {
			// TODO: Aflati punctajul maxim pe care il puteti obtine
			// planificand optim temele.
			for (Homework hw: hws) {
				homework.add(hw);
			}
			// for (Homework hw: homework) {
			// 	System.out.println(hw.deadline + " -- " + hw.score);
			// }
			Collections.sort(homework, new SortHomework1());
			// System.out.println("******************************");
			// for (Homework hw: homework) {
			// 	System.out.println(hw.deadline + " -- " + hw.score);
			// }
			int currentWeek = 0, points1 = 0;
			for (Homework hw: homework) {
				if (hw.deadline > currentWeek) {
					points1 += hw.score;
					currentWeek++;
				}
			}
			Collections.sort(homework, new SortHomework2());
			currentWeek = 0;
			int points2 = 0;
			for (Homework hw: homework) {
				if (hw.deadline > currentWeek) {
					points2 += hw.score;
					currentWeek++;
				}
			}
			return getMax(points1, points2);
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
