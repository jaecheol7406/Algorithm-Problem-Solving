package _2021_09;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
/*
 * 
 */
public class No_1238_파티 {
	static int INF = 99999999;
	static int N, M, X;
	static int[][] dist;
	static ArrayList<Node>[] list;
	static class Node {
		int v, w;
		
		public Node(int v, int w) {
			this.v = v;
			this.w = w;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in = br.readLine().split(" ");
		N = toInt(in[0]);
		M = toInt(in[1]);
		X = toInt(in[2]);
		
		dist = new int[N + 1][N + 1];
		for(int i = 1; i <= N; i++)
			Arrays.fill(dist[i], INF);
		
		list = new ArrayList[N + 1];
		for(int i = 1; i <= N; i++)
			list[i] = new ArrayList<>();
		
		for(int i = 0; i < M; i++) {
			in = br.readLine().split(" ");
			int a = toInt(in[0]);
			int b = toInt(in[1]);
			int w = toInt(in[2]);
			
			list[a].add(new Node(b, w));
		}
		
		for(int i = 1; i <= N; i++)
			dijkstra(i);
		
		int max = 2;
		for(int i = 1; i <= N; i++) {
			if(i == X)
				continue;
			
			int t = dist[i][X] + dist[X][i];
			if(max < t)
				max = t;
		}
		
		System.out.println(max);
	}
	
	static void dijkstra(int start) {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(start, 0));
		int[] d = dist[start];
		d[start] = 0;
		
		while(!q.isEmpty()) {
			int now = q.poll().v;
			
			for(Node n : list[now]) {
				int next = n.v;
				int val = n.w;
				
				if(d[next] > d[now] + val) {
					d[next] = d[now] + val;
					q.add(new Node(next, d[next]));
				}
			}
		}
	}
	
	static int toInt(String s) {
		return Integer.parseInt(s);
	}
}