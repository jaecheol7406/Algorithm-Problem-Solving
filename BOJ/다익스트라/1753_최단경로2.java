package _2022_01;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class No_1753_최단경로 {
	static int INF = 99999999;
	static int V, E, K;
	static int[] dist;
	static ArrayList<Node>[] list;
	
	static class Node implements Comparable<Node>{
		int v, w;
		
		public Node(int v, int w) {
			this.v = v;
			this.w = w;
		}
		public int compareTo(Node n) {
			return w - n.w;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in = br.readLine().split(" ");
		V = toInt(in[0]);
		E = toInt(in[1]);
		K = toInt(br.readLine());
		
		dist = new int[V + 1];
		Arrays.fill(dist, INF);
		
		list = new ArrayList[V + 1];
		for(int i = 1; i <= V; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < E; i++) {
			in = br.readLine().split(" ");
			int a = toInt(in[0]);
			int b = toInt(in[1]);
			int w = toInt(in[2]);
			
			list[a].add(new Node(b, w));
		}
		
		dijkstra(K);
		for(int i = 1; i <= V; i++) {
			if(dist[i] == INF) {
				System.out.println("INF");
			} else {
				System.out.println(dist[i]);
			}
		}
	}
	
	static void dijkstra(int start) {
		dist[start] = 0;
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(start, 0));
		
		while(!pq.isEmpty()) {
			Node n = pq.poll();
			int now = n.v;
			
			for(Node node : list[now]) {
				int next = node.v;
				int val = node.w;
				
				if(dist[next] > dist[now] + val) {
					dist[next] = dist[now] + val;
					pq.add(new Node(next, dist[next]));
				}
			}
		}
	}
	
	static int toInt(String s) {
		return Integer.parseInt(s);
	}
}