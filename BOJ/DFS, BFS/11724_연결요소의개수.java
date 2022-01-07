package _2022_01;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class No_11724_연결요소의개수 {
	static int N, M;
	static int[][] map;
	static boolean[] visit;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in = br.readLine().split(" ");
		N = toInt(in[0]);
		M = toInt(in[1]);
		
		map = new int[N + 1][N + 1];
		
		for(int i = 0; i < M; i++) {
			in = br.readLine().split(" ");
			int a = toInt(in[0]);
			int b = toInt(in[1]);
			map[a][b] = 1;
			map[b][a] = 1;
		}
		
		visit = new boolean[N + 1];
		
		int count = 0;
		for(int i = 1; i <= N; i++) {
			if(!visit[i]) {
				count++;
				dfs(i);
			}
		}
		
		System.out.println(count);
	}
	
	static void dfs(int a) {
		visit[a] = true;
		for(int i = 1; i <= N; i++) {
			if(!visit[i] && map[a][i] == 1) {
				dfs(i);
			}
		}
	}
	
	static int toInt(String s) {
		return Integer.parseInt(s);
	}
}