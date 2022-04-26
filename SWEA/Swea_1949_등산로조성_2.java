package __22년_상반기;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Swea_1949_등산로조성 {
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	
	static int T;
	static int N,K;
	static int[][] map;
	static int[][] dp;
	static int max = Integer.MIN_VALUE;
	static int maxValue = -1;
	static ArrayList<int[]> maxList = new ArrayList<int[]>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = toInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			String[] in = br.readLine().split(" ");
			N = toInt(in[0]);
			K = toInt(in[1]);
			max = Integer.MIN_VALUE;
			maxValue = -1;
			maxList = new ArrayList<int[]>();
			
			map = new int[N][N];
			
			for(int i = 0; i < N; i++) {
				in = br.readLine().split(" ");
				for(int j = 0; j < N; j++) {
					map[i][j] = toInt(in[j]);
					if(maxValue < map[i][j]) {
						maxValue = map[i][j];
					}
				}
			}
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(map[i][j] == maxValue) {
						maxList.add(new int[] {i, j});
					}
				}
			}
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					int v = map[i][j];
					for(int k = 0; k < K; k++) {
						if(--map[i][j] < 0)
							break;
						
						find();
					}
					
					map[i][j] = v;
				}
			}
			
			print(max, t);
		}
	}
	
	static void find() {
		// 문제 잘못읽어서 존재하는 모든 경우 탐색하기 위해 dp로 했음. 가장 높은 봉우리에서 시작하는걸 늦게 봄.
//		dp = new int[N][N];
//		
//		for(int i = 0; i < N; i++) {
//			for(int j = 0; j < N; j++) {
//				if(dp[i][j] == 0) {
//					dp[i][j] = dfs(i, j);
//				}
//				
//				max = Integer.max(max, dp[i][j]);
//			}
//		}
		
//		int maxHeight = 0;
//		for(int i = 0; i < N; i++) {
//			for(int j = 0; j < N; j++) {
//				if(maxHeight < map[i][j]) {
//					maxHeight = map[i][j];
//				}
//			}
//		}
//		
//		for(int i = 0; i < N; i++) {
//			for(int j = 0; j < N; j++) {
//				if(map[i][j] == maxHeight) {
//					dfs2(i, j, 1);
//				}
//			}
//		}
		
		for(int[] p : maxList) {
			dfs2(p[0], p[1], 1);
		}
	}
	
	static void dfs2(int x, int y, int len) {
		boolean flag = false;
		for(int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(!(0 <= nx && nx < N && 0 <= ny && ny < N))
				continue;
			
			if(map[nx][ny] < map[x][y]) {
				dfs2(nx, ny, len + 1);
				flag = true;
			}
		}
		if(!flag) {
			max = Integer.max(max, len);
		}
	}
	
	static int dfs(int x, int y) {
		int max2 = Integer.MIN_VALUE;
		boolean flag = false;
		for(int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(!(0 <= nx && nx < N && 0 <= ny && ny < N))
				continue;
			
			if(map[nx][ny] >= map[x][y]) {
				continue;
			}
			
			flag = true;
			if(dp[nx][ny] != 0) {
				max2 = Integer.max(max2, 1 + dp[nx][ny]);
			} else {
				max2 = Integer.max(max2, 1 + dfs(nx, ny));
			}
		}
		
		if(!flag) {
			dp[x][y] = 1;
			return 1;
		} else {
			dp[x][y] = max2;
			return max2;
		}
	}
	
	public static void print(int v, int t) {
		System.out.println("#" + t + " " + v);
	}
	
	public static void print(String v, int t) {
		System.out.println("#" + t + " " + v);
	}
	
	public static int toInt(String s) {
		return Integer.parseInt(s);
	}
}
