package _2021_09;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
/*
 * 
 */
public class No_15683_감시 {
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	
	static ArrayList<int[]>[] list = new ArrayList[6];
	static {
		for(int i = 1; i <= 5; i++)
			list[i] = new ArrayList<>();
		
		list[1].add(new int[] {0});
		list[1].add(new int[] {1});
		list[1].add(new int[] {2});
		list[1].add(new int[] {3});
		
		list[2].add(new int[] {0, 1});
		list[2].add(new int[] {2, 3});
		
		list[3].add(new int[] {0, 3});
		list[3].add(new int[] {0, 2});
		list[3].add(new int[] {1, 2});
		list[3].add(new int[] {1, 3});
		
		list[4].add(new int[] {0,1,3});
		list[4].add(new int[] {0,2,3});
		list[4].add(new int[] {0,1,2});
		list[4].add(new int[] {1,2,3});
		
		list[5].add(new int[] {0,1,2,3});
	}
	
	static int N, M;
	static int[][] map;
	static ArrayList<int[]> poss = new ArrayList<>();
	static int zCount;
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in = br.readLine().split(" ");
		N = toInt(in[0]);
		M = toInt(in[1]);
		map = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			in = br.readLine().split(" ");
			for(int j = 0; j < M; j++) {
				int v = toInt(in[j]);
				map[i][j] = v;
				if(1 <= v && v <= 5)
					poss.add(new int[] {i, j});
				else if(v == 0)
					zCount++;
			}
		}
		
		dfs(0, co(map), zCount);
		System.out.println(min);
	}
	
	static void dfs(int idx, int[][] map, int zCount) {
		if(idx == poss.size()) {
			min = Integer.min(min, zCount);
			return;
		}
		
		int[] p = poss.get(idx);
		int x = p[0];
		int y = p[1];
		int num = map[x][y];
		ArrayList<int[]> cases = list[num];
		for(int[] ca : cases) {
			int[][] t = co(map);
			int deleteCount = 0;
			for(int d : ca) {
				while(true) {
					int nx = x + dx[d];
					int ny = y + dy[d];
					
					if(!(0 <= nx && nx < N && 0 <= ny && ny < M))
						break;
					
					if(t[nx][ny] == 6)
						break;
					
					if(t[nx][ny] == 0) {
						t[nx][ny] = 7;
						deleteCount++;
					}
					
					x = nx;
					y = ny;
				}

				x = p[0];
				y = p[1];
			}
			
			dfs(idx + 1, t, zCount - deleteCount);
		}
	}
	
	static int[][] co(int[][] map){
		int[][] t = new int[N][M];
		for(int i = 0; i < N; i++)
			t[i] = map[i].clone();
		return t;
	}
	
	static int toInt(String s) {
		return Integer.parseInt(s);
	}
}