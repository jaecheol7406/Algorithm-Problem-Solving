package _2022_02;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class swea_1949_등산로조성 {
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	static int T;
	static int N,K;
	static int[][] map;
	static int max;
	static List<int[]> maxList;
	static int maxLen;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = toInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			String[] in = br.readLine().split(" ");
			N = toInt(in[0]);
			K = toInt(in[1]);
			map = new int[N][N];
			max = -1;
			maxList = new ArrayList<>();
			maxLen = 1;
			for(int i = 0; i < N; i++) {
				in = br.readLine().split(" ");
				for(int j = 0; j < N; j++) {
					map[i][j] = toInt(in[j]);
					if(max < map[i][j]) {
						max = map[i][j];
						maxList = new ArrayList<int[]>();
						maxList.add(new int[] {i, j});
					} else if(max == map[i][j]) {
						maxList.add(new int[] {i, j});
					}
				}
			}
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					int height = map[i][j];
					
					for(int k = 0; k <= K; k++) {
						if(height - k < 0) {
							break;
						}
						
						map[i][j] = height - k;
						make();
					}
					
					map[i][j] = height;
				}
			}
			
			print(t, maxLen);
		}
	}
	
	static void make() {
		for(int[] std : maxList) {
			dfs(std[0], std[1], 1);
		}
	}
	
	static void dfs(int x, int y, int len) {
		boolean flag = false;
		for(int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(!(0 <= nx && nx < N && 0 <= ny && ny < N))
				continue;
			
			if(map[nx][ny] < map[x][y]) {
				flag = true;
				
				dfs(nx, ny, len + 1);
			}
		}
		
		if(!flag) {
			if(maxLen < len) {
				maxLen = len;
			}
		}
	}
	
	public static int toInt(String s) {
		return Integer.parseInt(s);
	}
	
	public static void print(int t, int result) {
		System.out.println("#" + t + " " + result);
	}
	
	public static void print(int t, String result) {
		System.out.println("#" + t + " " + result);
	}
}
