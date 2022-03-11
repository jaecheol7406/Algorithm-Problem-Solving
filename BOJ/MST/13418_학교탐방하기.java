package _2022_03;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class No_13418_학교탐방하기 {
	static int N, M;
	static PriorityQueue<Edge> pq = new PriorityQueue<>(new Comparator<Edge>() {
		public int compare(Edge e1, Edge e2) {
			return e1.w - e2.w;
		}
	});
	static PriorityQueue<Edge> pq2 = new PriorityQueue<>(new Comparator<Edge>() {
		public int compare(Edge e1, Edge e2) {
			return e2.w - e1.w;
		}
	});
	static int[] parent;
	
	static class Edge {
		int a, b, w;
		public Edge(int a, int b, int w) {
			this.a = a;
			this.b = b;
			this.w = w;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in = br.readLine().split(" ");
		N = toInt(in[0]);
		M = toInt(in[1]);
		
		parent = new int[N + 1];
		for(int i = 1; i <= N; i++) {
			parent[i] = i;
		}
		
		for(int i = 0; i <= M; i++) {
			in = br.readLine().split(" ");
			int a = toInt(in[0]);
			int b = toInt(in[1]);
			int w = toInt(in[2]);
			
			pq.add(new Edge(a, b, w));
			pq2.add(new Edge(a, b, w));
		}
		
		int count = 0;
		while(!pq.isEmpty()) {
			Edge e = pq.poll();
			if(find(e.a) != find(e.b)) {
				union(e.a, e.b);
				if(e.w == 0)
					count++;
			}
		}
		
		parent = new int[N + 1];
		for(int i = 1; i <= N; i++) {
			parent[i] = i;
		}
		
		int count2 = 0;
		while(!pq2.isEmpty()) {
			Edge e = pq2.poll();
			if(find(e.a) != find(e.b)) {
				union(e.a, e.b);
				if(e.w == 0)
					count2++;
			}
		}
		
		System.out.println(count * count - count2 * count2);
	}
	
	static int find(int a) {
		if(a == parent[a])
			return a;
		
		return find(parent[a]);
	}
	
	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a < b)
			parent[b] = a;
		else
			parent[a] = b;
	}
	
	static int toInt(String s) {
		return Integer.parseInt(s);
	}
}