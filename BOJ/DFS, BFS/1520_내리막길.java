package _2021_10;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
/*
 * 
 */
public class No_1520_내리막길 {
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	static int N, M;
	static int[][] map;
	static int[][] dp;
	static int count;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in = br.readLine().split(" ");
		N = toInt(in[0]);
		M = toInt(in[1]);
		map = new int[N][M];
		dp = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			in = br.readLine().split(" ");
			for(int j = 0; j < M; j++) {
				map[i][j] = toInt(in[j]);
			}
			Arrays.fill(dp[i], -1);
		}
		
		count = dfs(0, 0);
		System.out.println(count);
	}
	
	static int dfs(int x, int y) {
		if(x == N - 1 && y == M - 1) {
			return 1;
		}
		
		if(dp[x][y] != -1) {
			return dp[x][y];
		}
		
		dp[x][y] = 0;
		for(int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(!(0 <= nx && nx < N && 0 <= ny && ny < M))
				continue;
			
			if(map[nx][ny] < map[x][y]) {
				dp[x][y] += dfs(nx, ny);
			}
		}
		return dp[x][y];
	}
	
	static int toInt(String s) {
		return Integer.parseInt(s);
	}
}