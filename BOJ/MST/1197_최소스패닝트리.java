package _2022_01;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class No_1197_최소스패닝트리 {
	static int V, E;
	static int[] parent;
	static PriorityQueue<Edge> pq = new PriorityQueue<>();
	
	static class Edge implements Comparable<Edge> {
		int a, b, w;
		
		public Edge(int a, int b, int w) {
			this.a = a;
			this.b = b;
			this.w = w;
		}
		
		@Override
		public int compareTo(Edge o) {
			return this.w - o.w;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in = br.readLine().split(" ");
		V = toInt(in[0]);
		E = toInt(in[1]);
		
		parent = new int[V + 1];
		for(int i = 1; i <= V; i++) {
			parent[i] = i;
		}
		
		for(int i = 0; i < E; i++) {
			in = br.readLine().split(" ");
			int a = toInt(in[0]);
			int b = toInt(in[1]);
			int w = toInt(in[2]);
			
			pq.add(new Edge(a,b,w));
		}
		
		int cost = 0;
		while(!pq.isEmpty()) {
			Edge e = pq.poll();
			
			if(find(e.a) != find(e.b)) {
				union(e.a, e.b);
				cost += e.w;
			}
		}
		
		System.out.println(cost);
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