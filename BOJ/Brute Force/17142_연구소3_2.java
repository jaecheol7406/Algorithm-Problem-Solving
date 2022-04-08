package __22년_상반기;

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
	static Queue<int[]> q;
	static int emptyCount;
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
					emptyCount++;
				}
			}
		}
		
		if(emptyCount == 0) {
			System.out.println(0);
			return;
		}
		
		int n = vList.size();
		int r = M;
		
		dfs(new int[r], 0, 0, new boolean[n], n, r);
		
		if(min == Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(min);
	}
	
	static void spread(int[][] map) {
		int spreadCount = 0;
		int t = 0;
		int qCount = q.size();
		while(true) {
			boolean changed = false;
			for(int i = 0; i < qCount; i++) {
				int[] vPos = q.poll();
				
				int x = vPos[0];
				int y = vPos[1];
				
				for(int j = 0; j < 4; j++) {
					int nx = x + dx[j];
					int ny = y + dy[j];
					
					if(!(0 <= nx && nx < N && 0 <= ny && ny < N)) {
						continue;
					}
					
					if(map[nx][ny] == 1 || map[nx][ny] == 3) {
						continue;
					}
					
					if(!changed)
						changed = true;
					
					if(map[nx][ny] == 0) {
						spreadCount++;
					}
					
					q.add(new int[] {nx, ny});
					map[nx][ny] = 3;
				}
			}
			
			if(!changed)
				return;
			
			t++;
			
			if(spreadCount == emptyCount) {
				break;
			}
			
			qCount = q.size();
		}
		
		if(min > t)
			min = t;
	}
	
	static void dfs(int[] re, int idx, int start, boolean[] check, int n, int r) {
		if(idx == r) {
			int[][] temp = copy(map);
			q = new LinkedList<>();
			for(int vIdx : re) {
				int[] vPos = vList.get(vIdx);
				temp[vPos[0]][vPos[1]] = 3;
				q.add(vPos);
			}
			
			spread(temp);
			return;
		}
		
		for(int i = start; i < n; i++) {
			if(!check[i]) {
				check[i] = true;
				re[idx] = i;
				dfs(re, idx + 1, i, check, n, r);
				check[i] = false;
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
	
	public static int toInt(String s) {
		return Integer.parseInt(s);
	}
}
