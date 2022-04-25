package __22년_상반기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class No_15683_감시 {
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	
	static int cctv[][][] = new int[][][] {
			{{0}, {1}, {2}, {3}},
			{{0,1}, {2,3}},
			{{0,3}, {0,2}, {1,2}, {1,3}},
			{{0,1,3}, {0,2,3}, {0,1,2}, {1,2,3}},
			{{0,1,2,3}}
	};
	
	static int N, M;
	static int[][] map;
	static int zeroCount;
	static ArrayList<int[]> list = new ArrayList<>();
	static int min;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in = br.readLine().split(" ");
		N = toInt(in[0]);
		M = toInt(in[1]);
		map = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			in = br.readLine().split(" ");
			for(int j = 0; j < M; j++) {
				map[i][j] = toInt(in[j]);
				if(map[i][j] == 0) {
					zeroCount++;
				} else if(map[i][j] != 6) {
					list.add(new int[] {i, j});
				}
			}
		}
		
		min = zeroCount;
		
		dfs(0, map, zeroCount);
		
		System.out.println(min);
	}
	
	static void dfs(int idx, int[][] map, int zero) {
		if(idx == list.size()) {
			if(min > zero)
				min = zero;
			
			return;
		}
		
		int[] pos = list.get(idx);
		int x = pos[0];
		int y = pos[1];
		int ccNum = map[x][y] - 1;
		int[][] cc = cctv[ccNum];
		for(int[] dirs : cc) {
			int[][] temp = co(map);
			int tempZero = zero;
			
			for(int dir : dirs) {
				int nx = x, ny = y;
				while(true) {
					nx += dx[dir];
					ny += dy[dir];
					
					if(!(0 <= nx && nx < N && 0 <= ny && ny < M) || temp[nx][ny] == 6)
						break;
					
					if(temp[nx][ny] == 0) {
						temp[nx][ny] = '7';
						tempZero--;
					}
				}
			}
			
			dfs(idx + 1, temp, tempZero);
		}
	}
	
	static int[][] co(int[][] map){
		int[][] temp = new int[N][M];
		for(int i = 0; i < N; i++) {
			temp[i] = map[i].clone();
		}
		return temp;
	}
	
	public static int toInt(String s) {
		return Integer.parseInt(s);
	}
}
