package _2021_10;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class No_18809_Gaaaaaaaaaarden {
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	
	static int N, M;
	static int G, R, T;
	static int[][] map;
	static ArrayList<int[]> canDo = new ArrayList<>();
	static ArrayList<Integer> canDoIdxs = new ArrayList<>();
	static Queue<int[]> q;
	static int max;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in = br.readLine().split(" ");
		N = toInt(in[0]);
		M = toInt(in[1]);
		G = toInt(in[2]);
		R = toInt(in[3]);
		T = G + R;
		
		map = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			in = br.readLine().split(" ");
			for(int j = 0; j < M; j++) {
				int v = toInt(in[j]);
				if(v == 0) {
					map[i][j] = 1;
				} else if(v == 2) {
					canDo.add(new int[] {i, j});
					map[i][j] = 0;
				} else {
					map[i][j] = 0;
				}
			}
		}
		
		dfs(0, new int[T], 0, new boolean[canDo.size()]);
		System.out.println(max);
	}
	
	static void dfs(int start, int[] re, int idx, boolean[] check) {
		if(idx == T) {
			canDoIdxs = new ArrayList<>();
			for(int i = 0; i < T; i++) {
				canDoIdxs.add(re[i]);
			}
			
			dfs2(0, 0, new boolean[T]);
			return;
		}
		
		for(int i = start; i < canDo.size(); i++) {
			if(!check[i]) {
				check[i] = true;
				re[idx] = i;
				dfs(i, re, idx + 1, check);
				check[i] = false;
			}
		}
	}
	
	static void dfs2(int start, int idx, boolean[] check) {
		if(idx == G) {
			q = new LinkedList<>();
			int[][] temp = co(map);
			for(int i = 0; i < T; i++) {
				int gIdx = canDoIdxs.get(i);
				int[] p = canDo.get(gIdx);
				if(check[i]) {
					temp[p[0]][p[1]] = 2;
				} else {
					temp[p[0]][p[1]] = 3;
				}
				q.add(new int[] {p[0], p[1]});
			}
			
			spread(temp);
			return;
		}
		
		for(int i = start; i < T; i++) {
			if(!check[i]) {
				check[i] = true;
				dfs2(i, idx + 1, check);
				check[i] = false;
			}
		}
	}
	
	static void spread(int[][] map) {
		int count = 0;
		int size = q.size();
		while(true) {
			int[][] temp = new int[N][M];
			for(int i = 0; i < size; i++) {
				int[] p = q.poll();
				int x = p[0];
				int y = p[1];
				int now = map[x][y];
				
				for(int j = 0; j < 4; j++) {
					int nx = x + dx[j];
					int ny = y + dy[j];
					
					if(!(0 <= nx && nx < N && 0 <= ny && ny < M))
						continue;
					
					int next = map[nx][ny];
					if(next == 4)
						continue;
					
					if(next != 0)
						continue;
					
					if(temp[nx][ny] == 0) {
						temp[nx][ny] = now;
					} else if(temp[nx][ny] == 2) {
						if(now == 3) {
							temp[nx][ny] = 4;
							map[nx][ny] = 4;
							count++;
						}
					} else if(temp[nx][ny] == 3) {
						if(now == 2) {
							temp[nx][ny] = 4;
							map[nx][ny] = 4;
							count++;
						}
					}
					
				}
			}
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					if(temp[i][j] == 2 || temp[i][j] == 3) {
						q.add(new int[] {i, j});
						map[i][j] = temp[i][j];
					}
				}
			}
			
			size = q.size();
			if(size == 0)
				break;
		}
		
		if(count > max)
			max = count;
	}
	
	static int[][] co(int[][] map){
		int[][] t = new int[N][M];
		for(int i = 0; i < N; i++) {
			t[i] = map[i].clone();
		}
		return t;
	}
	
	static void print(int[][] map) {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	static int toInt(String s) {
		return Integer.parseInt(s);
	}
}