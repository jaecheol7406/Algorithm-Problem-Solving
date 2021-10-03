package _2021_10;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class No_1916_최소비용구하기 {
	static int INF = Integer.MAX_VALUE;
	static int N, M;
	static int[] dist;
	static ArrayList<Node>[] list;
	static class Node implements Comparable<Node> {
		int v, w;
		public Node(int v, int w) {
			this.v = v;
			this.w = w;
		}
		
		public int compareTo(Node n) {
			return this.w - n.w;
		}
	}
	
	static int from, to;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = toInt(br.readLine());
		M = toInt(br.readLine());
		
		dist = new int[N + 1];
		Arrays.fill(dist, INF);
		
		list = new ArrayList[N + 1];
		for(int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < M; i++) {
			String[] in = br.readLine().split(" ");
			int a = toInt(in[0]);
			int b = toInt(in[1]);
			int w = toInt(in[2]);
			
			list[a].add(new Node(b, w));
		}
		
		String[] in = br.readLine().split(" ");
		from = toInt(in[0]);
		to  = toInt(in[1]);
		
		di();
		System.out.println(dist[to]);
	}
	
	static void di() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(from, 0));
		dist[from] = 0;
		
		boolean[] check = new boolean[N + 1];
		
		while(!pq.isEmpty()) {
			int now = pq.poll().v;
			if(check[now])
				continue;
			
			check[now] = true;
			
			for(Node n : list[now]) {
				int next = n.v;
				int val = n.w;
				
				if(!check[next] && dist[next] > dist[now] + val) {
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