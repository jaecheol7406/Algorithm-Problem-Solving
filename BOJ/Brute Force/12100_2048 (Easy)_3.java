package _23년_상반기;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

public class No_12100_2048Easy {
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	
	static int N;
	static int[][] map;
	
	static int re = -1;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = toInt(br.readLine());
		map = new int[N][N];
		for(int i = 0; i < N; i++) {
			String[] in = br.readLine().split(" ");
			for(int j = 0; j < N; j++) {
				map[i][j] = toInt(in[j]);
			}
		}
		
		dfs(0, map);
		
		System.out.println(re);
	}
	
	static void dfs(int idx, int[][] map) {
		if(idx == 5) {
			int max = -1;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(max < map[i][j]) {
						max = map[i][j];
					}
				}
			}
			if(re < max) {
				re = max;
			}
			return;
		}
		
		for(int i = 0; i < 4; i++) {
			int[][] temp = new int[N][N];
			for(int j = 0; j < N; j++) {
				temp[j] = map[j].clone();
			}
			
			move(temp);
			
			dfs(idx + 1, temp);
			
			if(i == 3) {
				break;
			}
			
			map = rotate(map);
		}
	}
	
	static int[][] rotate(int[][] map) {
		N = map.length;
		int[][] temp = new int[N][N];
		for(int i = 0; i < N; i++) {
			int jj = N - 1 - i;
			for(int j = 0; j < N; j++) {
				int ii = j;
				
				temp[ii][jj] = map[i][j];
			}
		}
		
		return temp;
	}
	
	static void move(int[][] map) {
		for(int i = 0; i < N; i++) {
			int prev = -1;
			ArrayList<Integer> list = new ArrayList<Integer>();
			for(int j = N - 1; j >= 0; j--) {
				if(map[i][j] == 0) {
					continue;
				}
				if(prev == -1) {
					prev = map[i][j];
					map[i][j] = 0;
					continue;
				}
				
				int now = map[i][j];
				
				if(now == prev) {
					list.add(now * 2);
					map[i][j] = 0;
					prev = -1;
				} else {
					list.add(prev);
					map[i][j] = 0;
					prev = now;
				}
			}
			
			if(prev > 0) {
				list.add(prev);
			}
			
			int reIdx = N - 1;
			for(int v : list) {
				map[i][reIdx--] = v;
			}
		}
	}
	
	public static int toInt(String s) {
		return Integer.parseInt(s);
	}
}
