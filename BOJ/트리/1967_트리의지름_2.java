package _2021_09;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

import _2021_09.No_1167_트리의지름.Node;
/*
 * 
 */
public class No_1967_트리의지름_2 {
	static int N;
	static int[] parent;
	static ArrayList<Node>[] list;
	static class Node {
		int v, w;
		public Node(int v, int w) {
			this.v = v;
			this.w = w;
		}
	}
	
	static int node;
	static int max = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = toInt(br.readLine());
		if(N == 1) {
			System.out.println(0);
			return;
		}
		
		parent = new int[N + 1];
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
		
		parent[1] = 1;
		dfs(1, 0);
		
		Arrays.fill(parent, 0);
		parent[node] = node;
		max = 0;
		dfs(node, 0);
		
		System.out.println(max);
	}
	
	static void dfs(int now, int sum) {
		if(max < sum) {
			max = sum;
			node = now;
		}
		
		for(Node n : list[now]) {
			int next = n.v;
			int val = n.w;
			
			if(parent[next] == 0) {
				parent[next] = now;
				dfs(next, sum + val);
			}
		}
	}
	
	static int toInt(String s) {
		return Integer.parseInt(s);
	}
}