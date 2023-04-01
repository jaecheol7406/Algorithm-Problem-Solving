package _23년_상반기;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class No_11724_연결요소의개수 {
	static int N,M;
	static int[][] map;
	static boolean[] visit;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] in = br.readLine().split(" ");
		N = toInt(in[0]);
		M = toInt(in[1]);
		map = new int[N][N];
		visit = new boolean[N];
		
		for(int i = 0; i < M; i++) {
			in = br.readLine().split(" ");
			int a = toInt(in[0]) - 1;
			int b = toInt(in[1]) - 1;
			map[a][b] = 1;
			map[b][a] = 1;
		}
		
		int count = 0;
		
		for(int i = 0; i < N; i++) {
			if(visit[i]) {
				continue;
			}
			
			count++;
			visit[i] = true;
			
			for(int j = 0; j < N; j++) {
				if(map[i][j] == 1 && !visit[j]) {
					dfs(j);
				}
			}
		}
		
		System.out.println(count);
	}
	
	static void dfs(int i) {
		visit[i] = true;
		for(int j = 0; j < N; j++) {
			if(map[i][j] == 1 && !visit[j]) {
				dfs(j);
			}
		}
	}
	
	public static int toInt(String s) {
		return Integer.parseInt(s);
	}
}
