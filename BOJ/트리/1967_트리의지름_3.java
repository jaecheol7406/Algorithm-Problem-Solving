package _2022_02;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class No_1967_트리의지름 {
	static int N;
	static ArrayList<Node>[] list;
	static class Node{
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
		for(int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < N - 1; i++) {
			String[] in = br.readLine().split(" ");
			int a = toInt(in[0]);
			int b = toInt(in[1]);
			int w = toInt(in[2]);
			list[a].add(new Node(b, w));
			list[b].add(new Node(a, w));
		}
		
		int to = maxFrom(1)[0];
		System.out.println(maxFrom(to)[1]);
	}
	
	static int[] maxFrom(int v) {
		int max = 0;
		int maxNode = 1;
		Queue<int[]> q = new LinkedList<>();
		boolean[] visit = new boolean[N + 1];
		q.add(new int[] {v, 0});
		visit[v] = true;
		while(!q.isEmpty()) {
			int[] p = q.poll();
			int now = p[0];
			int dist = p[1];
			
			boolean flag = false;
			for(Node node : list[now]) {
				int next = node.v;
				int val = node.w;
				if(visit[next])
					continue;
				
				q.add(new int[] {next, dist + val});
				visit[next] = true;
				flag = true;
			}
			
			if(!flag && max < dist) {
				max = dist;
				maxNode = now;
			}
		}
		
		return new int[] {maxNode, max};
	}
	
	static int toInt(String s) {
		return Integer.parseInt(s);
	}
}