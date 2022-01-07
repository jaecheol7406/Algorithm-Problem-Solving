package _2022_01;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class No_17142_연구소3 {
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	
	static int N, M;
	static int[][] map;
	static List<int[]> virusList = new ArrayList<>();
	static int zero;
	static int n, r;
	static Queue<int[]> q = new LinkedList<>();
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in = br.readLine().split(" ");
		N = toInt(in[0]);
		M = toInt(in[1]);
		map = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			in = br.readLine().split(" ");
			for(int j = 0; j < N; j++) {
				map[i][j] = toInt(in[j]);
				if(map[i][j] == 2) {
					virusList.add(new int[] {i, j});
				} else if(map[i][j] == 0) {
					zero++;
				}
			}
		}
		
		if(zero == 0) {
			System.out.println(0);
			return;
		}
		
		n = virusList.size();
		r = M;
		
		dfs(0, new int[r], 0, new boolean[n]);
		
		if(min == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(min);
		}
	}
	
	static void dfs(int start, int[] re, int idx, boolean[] check) {
		if(idx == r) {
			int[][] temp = copy(map);
			q = new LinkedList<int[]>();
			for(int acIdx : re) {
				int[] pos = virusList.get(acIdx);
				int x = pos[0];
				int y = pos[1];
				
				temp[x][y] = 3;
				q.add(new int[] {x, y});
			}
			
			spread(temp);
			return;
		}
		
		for(int i = start; i < n; i++) {
			if(!check[i]) {
				check[i] = true;
				re[idx] = i;
				dfs(i, re, idx + 1, check);
				check[i] = false;
			}
		}
	}
	
	static void spread(int[][] map) {
		int time = 0;
		int remain = zero;
		while(true) {
			if(++time == min)
				return;
			
			int size = q.size();
			
			for(int i = 0; i < size; i++) {
				int[] p = q.poll();
				int x = p[0];
				int y = p[1];
				
				for(int j = 0; j < 4; j++) {
					int nx = x + dx[j];
					int ny = y + dy[j];
					
					if(!(0 <= nx && nx < N && 0 <= ny && ny < N))
						continue;
					
					int next = map[nx][ny];
					if(next == 1 || next == 3)
						continue;
					
					if(next == 0 && --remain == 0) {
						min = Integer.min(min, time);
						return;
					}
					
					map[nx][ny] = 3;
					q.add(new int[] {nx, ny});
				}
			}
			
			if(q.size() == 0) {
				return;
			}
		}
	}
	
	static int[][] copy(int[][] map){
		int[][] temp = new int[N][N];
		for(int i = 0; i < N; i++) {
			temp[i] = map[i].clone();
		}
		
		return temp;
	}
	
	static int toInt(String s) {
		return Integer.parseInt(s);
	}
}