package _22년_하반기;
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
	static int min = Integer.MAX_VALUE;
	static ArrayList<int[]> vList = new ArrayList<int[]>();
	static int zeroCount;
	
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
					zeroCount++;
				}
			}
		}
		
		if(zeroCount == 0){
			System.out.println(0);
			return;
		}
		
		int n = vList.size();
		int r = M;
		
		dfs(0, 0, new int[r], new boolean[n], n, r);
		
		if(min == Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(min);
	}
	
	static void dfs(int start, int idx, int[] re, boolean[] visit, int n, int r) {
		if(idx == r) {
			int[][] temp = copy(map);
			Queue<int[]> q = new LinkedList<int[]>();
			for(int rIdx : re) {
				int[] pos = vList.get(rIdx);
				temp[pos[0]][pos[1]] = 3;
				q.add(new int[] {pos[0], pos[1]});
			}
			
			spread(temp, q);
			
			return;
		}
		
		for(int i = start; i < n; i++) {
			if(!visit[i]) {
				visit[i] = true;
				re[idx] = i;
				dfs(i + 1, idx + 1, re, visit, n, r);
				visit[i] = false;
			}
		}
	}
	
	static void spread(int[][] map, Queue<int[]> q) {
		int chCount = 0;
		
		int t = 0;
		while(true) {
			int count = q.size();
			boolean flag = false;
			for(int i = 0; i < count; i++) {
				int[] p = q.poll();
				int x = p[0];
				int y = p[1];
				
				for(int j = 0; j < 4; j++) {
					int nx = x + dx[j];
					int ny = y + dy[j];
					
					if(!(0 <= nx && nx < N && 0 <= ny && ny < N))
						continue;
					
					if(map[nx][ny] == 0 || map[nx][ny] == 2) {
						q.add(new int[] {nx, ny});
						flag = true;
						if(map[nx][ny] == 0) {
							chCount++;
						}
						
						map[nx][ny] = 3;
					} else {
						continue;
					}
				}
			}
			
			if(!flag)
				return;
			
			t++;
			
			if(chCount == zeroCount) {
				break;
			}
		}
		
		if(min > t)
			min = t;
	}
	
	static int[][] copy(int[][] map){
		int[][] temp = new int[N][N];
		for(int i = 0; i < N; i++) {
			temp[i] = map[i].clone();
		}
		return temp;
	}
	
	public static int toInt(String s) {
		return Integer.parseInt(s);
	}
}
