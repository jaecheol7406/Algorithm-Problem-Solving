package _2021_10;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Solution_13041_게으름뱅이왕국 {
	static int N, K, T;
	static int[] a, counts;
	static PriorityQueue<Node> pq = new PriorityQueue<>();
	static class Node implements Comparable<Node> {
		long cost;
		int idx;
		public Node(long c, int i) {
			this.cost = c;
			this.idx = i;
		}
		public int compareTo(Node n) {
			if(this.cost == n.cost) {
				return this.idx - n.idx;
			}
			
			if(this.cost < n.cost)
				return -1;
			return 1;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			String[] in = br.readLine().split(" ");
			N = toInt(in[0]);
			K = toInt(in[1]);
			a = new int[N + 1];
			counts = new int[K + 1];
						
			in = br.readLine().split(" ");
			for(int i = 1; i <= N; i++) {
				a[i] = toInt(in[i - 1]);
				counts[a[i]]++;
			}
			
			int done = 0;
			for(int i = 1; i <= K; i++) {
				if(counts[i] != 0) {
					done++;
				}
			}
			
			pq = new PriorityQueue<>();
			in = br.readLine().split(" ");
			for(int i = 1; i <= N; i++) {
				pq.add(new Node(toInt(in[i - 1]), i));
			}
			
			if(done == K) {
				System.out.println("#" + t + " 0");
				continue;
			}
			
			long minCost = 0;
			while(!pq.isEmpty()) {
				Node node = pq.poll();
				int idx = node.idx;
				int count = counts[a[idx]];
				if(count >= 2) {
					counts[a[idx]]--;
					done++;
					minCost += node.cost;
					if(done == K)
						break;
				}
			}
			
			System.out.println("#" + t + " " + minCost);
		}
	}
	
	static int toInt(String s) {
		return Integer.parseInt(s);
	}
}