package _2021_09;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
/*
 * 
 */
public class No_11725_트리의부모찾기 {
	static int N;
	static ArrayList<Integer>[] list;
	static int[] parent;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = toInt(br.readLine());
		list = new ArrayList[N + 1];
		for(int i = 1; i <= N; i++)
			list[i] = new ArrayList<>();
		
		parent = new int[N + 1];
		
		for(int i = 0; i < N - 1; i++) {
			String[] in = br.readLine().split(" ");
			int a = toInt(in[0]);
			int b = toInt(in[1]);
			list[a].add(b);
			list[b].add(a);
		}
		
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(1);
		parent[1] = 1;
		
		while(!q.isEmpty()) {
			int v = q.poll();
			
			for(int next : list[v]) {
				if(parent[next] == 0) {
					parent[next] = v;
					q.add(next);	
				}
			}
		}
		
		for(int i = 2; i <= N; i++)
			System.out.println(parent[i]);
	}
	
	static int toInt(String s) {
		return Integer.parseInt(s);
	}
}