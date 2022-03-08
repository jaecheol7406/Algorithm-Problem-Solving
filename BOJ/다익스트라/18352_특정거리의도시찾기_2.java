package _2022_03;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class No_18352_특정거리의도시찾기_2 {
	static int N,M,K,X;
	static ArrayList<Integer>[] list;
	static int[] dist; 
	static int INF = 99999999;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in = br.readLine().split(" ");
		N = toInt(in[0]);
		M = toInt(in[1]);
		K = toInt(in[2]);
		X = toInt(in[3]);
		
		list = new ArrayList[N + 1];
		for(int i = 1; i <= N; i++)
			list[i] = new ArrayList<Integer>();
		
		dist = new int[N + 1];
		Arrays.fill(dist, INF);
		
		for(int i = 0; i < M; i++) {
			in = br.readLine().split(" ");
			int a = toInt(in[0]);
			int b = toInt(in[1]);
			list[a].add(b);
		}
		
		dijkstra(X);
		
		boolean flag = false;
		for(int i = 1; i <= N; i++) {
			if(dist[i] == K) {
				System.out.println(i);
				flag = true;
			}
		}
		
		if(!flag) {
			System.out.println(-1);
		}
	}
	
	static void dijkstra(int from) {
		dist[from] = 0;
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(from);
		
		while(!q.isEmpty()) {
			int v = q.poll();
			
			for(int next : list[v]) {
				if(dist[next] > dist[v] + 1) {
					dist[next] = dist[v] + 1;
					if(dist[next] > K) {
						continue;
					}
					
					q.add(next);
				}
			}
		}
	}
	
	static int toInt(String s) {
		return Integer.parseInt(s);
	}
}