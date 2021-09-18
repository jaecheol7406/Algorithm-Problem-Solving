package _2021_09;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
/*
 * 
 */
public class No_1167_트리의지름 {
	static int V;
	static ArrayList<Node>[] list;
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
		V = toInt(br.readLine());
		
		list = new ArrayList[V + 1];
		for(int i = 1; i <= V; i++)
			list[i] = new ArrayList<>();
		
		for(int i = 0; i < V; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = toInt(st.nextToken());
			while(true) {
				int b = toInt(st.nextToken());
				if(b == -1)
					break;
				
				int w = toInt(st.nextToken());
				
				list[a].add(new Node(b, w));
			}
		}
		
		dfs(1, 0, 0);
		dfs(node, 0, 0);
		System.out.println(max);
	}
	
	static void dfs(int now, int parent, int sum) {
		if(max < sum) {
			max = sum;
			node = now;
		}
		for(Node next : list[now]) {
			if(next.v == parent)
				continue;
			
			dfs(next.v, now, sum + next.w);
		}
	}
	
	static int toInt(String s) {
		return Integer.parseInt(s);
	}
}