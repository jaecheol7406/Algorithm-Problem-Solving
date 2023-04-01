package _23년_상반기;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class No_17142_연구소3 {
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	
	static int N, M;
	static int[][] map;
	static ArrayList<int[]> vList = new ArrayList<int[]>();
	static int n, r;
	static int zCount;
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception{
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
					vList.add(new int[] {i, j});
				} else if(map[i][j] == 0) {
					zCount++;
				}
			}
		}
		n = vList.size();
		r = M;
		dfs(0,0,new int[r], new boolean[n]);
		
		if(min == Integer.MAX_VALUE) {
			min = -1;
		}
		System.out.println(min);
	}
	
	static void dfs(int start, int idx, int[] re, boolean[] check) {
		if(idx == r) {
			int[][] temp = new int[N][N];
			for(int i = 0; i < N; i++) {
				temp[i] = map[i].clone();
			}
			
			Queue<int[]> q = new LinkedList<int[]>();
			for(int i = 0; i < r; i++) {
				int[] pos = vList.get(re[i]);
				temp[pos[0]][pos[1]] = 3;
				q.add(new int[] {pos[0], pos[1]});
				
			}
			
			spread(temp, q);
			
			return;
		}
		
		for(int i = start; i < n; i++) {
			if(!check[i]) {
				check[i] = true;
				re[idx] = i;
				dfs(i + 1, idx + 1, re, check);
				check[i] = false;
			}
		}
	}
	
	static void spread(int[][] map, Queue<int[]> q) {
		int t = 0;
		int acCount = 0;
		while(true) {
			if(acCount == zCount) {
				break;
			}
			
			boolean flag = false;
			int count = q.size();
			for(int i = 0; i < count; i++) {
				int[] pos = q.poll();
				int x = pos[0];
				int y = pos[1];
				
				for(int j = 0; j < 4; j++) {
					int nx = x + dx[j];
					int ny = y + dy[j];
					
					if(!(0 <= nx && nx < N && 0 <= ny && ny < N) || map[nx][ny] == 1) {
						continue;
					}
					
					if(map[nx][ny] == 0) {
						map[nx][ny] = 3;
						acCount++;
						q.add(new int[] {nx,ny});
						flag = true;
					} else if(map[nx][ny] == 2) {
						map[nx][ny] = 3;
						q.add(new int[] {nx,ny});
						flag = true;
					}
				}
			}
			
			if(!flag) {
				return;
			}
			
			if(++t == min) {
				return;
			}
		}
		
		min = t;
	}
	
	public static int toInt(String s) {
		return Integer.parseInt(s);
	}
}
