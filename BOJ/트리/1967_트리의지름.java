package _2021_09;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
/*
 * 
 */
public class No_1967_트리의지름 {
	static int N;
	static ArrayList<Node>[] list;
	static boolean[] visit;
	
	static int node;
	
	static int max = -1;
	
	static class Node {
		int v, w;
		
		public Node(int v, int w) {
			this.v = v;
			this.w = w;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = toInt(br.readLine());
		list = new ArrayList[N + 1];
		for(int i = 1; i <= N; i++)
			list[i] = new ArrayList<>();
		
		for(int i = 0; i < N - 1; i++) {
			String[] in = br.readLine().split(" ");
			int a = toInt(in[0]);
			int b = toInt(in[1]);
			int w = toInt(in[2]);
			list[a].add(new Node(b, w));
			list[b].add(new Node(a, w));
		}
		
		visit = new boolean[N + 1];
		visit[1] = true;
		dfs(1, 0);
		
		visit = new boolean[N + 1];
		visit[node] = true;
		dfs(node, 0);
		System.out.println(max);
	}
	
	static void dfs(int v, int dist) {
		if(max < dist) {
			max = dist;
			node = v;
		}
		for(Node node : list[v]) {
			if(!visit[node.v]) {
				visit[node.v] = true;
				dfs(node.v, dist + node.w);
			}
		}
	}
	
	static int toInt(String s) {
		return Integer.parseInt(s);
	}
}