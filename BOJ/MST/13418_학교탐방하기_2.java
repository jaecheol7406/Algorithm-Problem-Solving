package __22년_상반기;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class No_13418_학교탐방하기 {
	static int N, M;
	static int[] parent;
	static ArrayList<Edge> list = new ArrayList<>();
	static class Edge{
		int a, b, w;
		public Edge(int a, int b, int w) {
			this.a = a;
			this.b = b;
			this.w = w;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in = br.readLine().split(" ");
		N = toInt(in[0]);
		M = toInt(in[1]);
		
		parent = new int[N + 1];
		for(int i = 0; i <= N; i++) {
			parent[i] = i;
		}
		for(int i = 0; i < M + 1; i++) {
			in = br.readLine().split(" ");
			int a = toInt(in[0]);
			int b = toInt(in[1]);
			int w = toInt(in[2]);
			list.add(new Edge(a, b, w));
		}
		
		Collections.sort(list, new Comparator<Edge>() {
			public int compare(Edge e1, Edge e2) {
				return e1.w - e2.w;
			}
		});
		
		int count = 0;
		for(Edge e : list) {
			if(find(e.a) != find(e.b)) {
				union(e.a, e.b);
				if(e.w == 0)
					count++;
			}
		}
		
		int max = count * count;
		
		Collections.sort(list, new Comparator<Edge>() {
			public int compare(Edge e1, Edge e2) {
				return e2.w - e1.w;
			}
		});
		
		count = 0;
		parent = new int[N + 1];
		for(int i = 0; i <= N; i++) {
			parent[i] = i;
		}
		for(Edge e : list) {
			if(find(e.a) != find(e.b)) {
				union(e.a, e.b);
				if(e.w == 0)
					count++;
			}
		}
		
		int min = count * count;
		System.out.println(max - min);
	}
	
	static int find(int a) {
		if(a == parent[a]) {
			return a;
		}
		
		return find(parent[a]);
	}
	
	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a < b) {
			parent[b] = a;
		} else {
			parent[a] = b;
		}
	}
	
	public static int toInt(String s) {
		return Integer.parseInt(s);
	}
}
