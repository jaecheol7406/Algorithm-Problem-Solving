package _2021_09;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
/*
 * 
 */
public class No_1927_최소힙 {
	static int N;
	static PriorityQueue<Integer> pq = new PriorityQueue<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = toInt(br.readLine());
		for(int i = 0; i < N; i++) {
			int x = toInt(br.readLine());
			if(x == 0) {
				if(pq.size() == 0)
					System.out.println(0);
				else
					System.out.println(pq.poll());
			} else {
				pq.add(x);
			}
		}
	}
	
	static int toInt(String s) {
		return Integer.parseInt(s);
	}
}