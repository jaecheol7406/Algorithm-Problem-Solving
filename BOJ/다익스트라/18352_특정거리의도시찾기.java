package _2021_09;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
/*
 * 
 */
public class No_18352_특정거리의도시찾기 {
	static int INF = 99999999;
	static int N, M, K, X;
	static int[] dist;
	static ArrayList<Integer>[] list;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in = br.readLine().split(" ");
		N = toInt(in[0]);
		M = toInt(in[1]);
		K = toInt(in[2]);
		X = toInt(in[3]);
		
		dist = new int[N + 1];
		Arrays.fill(dist, INF);
		
		list = new ArrayList[N + 1];
		for(int i = 1; i <= N; i++)
			list[i] = new ArrayList<>();
		
		for(int i = 0; i < M; i++) {
			in = br.readLine().split(" ");
			int a = toInt(in[0]);
			int b = toInt(in[1]);
			
			list[a].add(b);
		}
		
		di();
		
		ArrayList<Integer> re = new ArrayList<>();
		for(int i = 1; i <= N; i++) {
			if(dist[i] == K)
				re.add(i);
		}
		
		if(re.size() == 0)
			System.out.println(-1);
		else{
			Collections.sort(re);
			for(int v : re)
				System.out.println(v);
		}
	}
	
	static void di() {
		Queue<Integer> q = new LinkedList<>();
		q.add(X);
		dist[X] = 0;
		
		while(!q.isEmpty()) {
			int now = q.poll();
			
			for(int next : list[now]) {			
				if(dist[next] == INF) {
					dist[next] = dist[now] + 1;
					q.add(next);
				}
			}
		}
	}
	
	static int toInt(String s) {
		return Integer.parseInt(s);
	}
}