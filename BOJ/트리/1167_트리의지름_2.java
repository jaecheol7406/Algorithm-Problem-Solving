package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Test3 {
	static int n;
	static ArrayList<Node>[] list;
	static class Node {
		int v, w;
		public Node(int v, int w) {
			this.v = v;
			this.w = w;
		}
		
	}
	
	static int v1, re = -1;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		list = new ArrayList[n + 1];
		for(int i = 1; i <= n; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < n; i++) {
			String[] in = br.readLine().split(" ");
			int a = Integer.parseInt(in[0]);
			
			for(int j = 1; j < in.length - 1; j += 2) {
				if(Integer.parseInt(in[j]) == -1)
					break;
				
				int b = Integer.parseInt(in[j]);
				int w = Integer.parseInt(in[j + 1]);

				list[a].add(new Node(b, w));
				list[b].add(new Node(a, w));
			}
		}
		
		find(1);
		find(v1);
		System.out.println(re);
	}
	
	static void find(int a) {
		Queue<int[]> q = new LinkedList<>();
		boolean[] visit = new boolean[n + 1];
		q.add(new int[] {a, 0});
		visit[a] = true;
		
		while(!q.isEmpty()) {
			int[] arr = q.poll();
			int v = arr[0];
			int dist = arr[1];
			
			boolean flag = false;
			for(Node node : list[v]) {
				if(!visit[node.v]) {
					visit[node.v] = true;
					q.add(new int[] {node.v, dist + node.w});
					flag = true;
				}
			}

			if(!flag && re < dist) {
				v1 = v;
				re = dist;
			}
		}
	}
}
