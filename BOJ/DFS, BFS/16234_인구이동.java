package _2022_01;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class No_16234_인구이동 {
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	
	static int N, L, R;
	static int[][] map;
	static int t;
	static boolean[][] visit;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in = br.readLine().split(" ");
		N = toInt(in[0]);
		L = toInt(in[1]);
		R = toInt(in[2]);
		
		map = new int[N][N];
		for(int i = 0; i < N; i++) {
			in = br.readLine().split(" ");
			for(int j = 0; j < N; j++) {
				map[i][j] = toInt(in[j]);
			}
		}
		
		while(true) {
			visit = new boolean[N][N];
			boolean flag = false;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(!visit[i][j] && bfs(i, j)) {
						flag = true;
					}
				}
			}
			
			if(!flag)
				break;
			
			t++;
		}
		
		System.out.println(t);
	}
	
	static boolean bfs(int sx, int sy) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {sx, sy});
		visit[sx][sy] = true;
		
		List<int[]> list = new ArrayList<int[]>();
		list.add(new int[] {sx, sy});
		int sum = 0;
		sum += map[sx][sy];
		
		while(!q.isEmpty()) {
			int[] p = q.poll();
			int x = p[0];
			int y = p[1];
			
			for(int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(!(0 <= nx && nx < N && 0 <= ny && ny < N))
					continue;
				
				if(visit[nx][ny])
					continue;
				
				int diff = Math.abs(map[nx][ny] - map[x][y]);
				
				if(L <= diff && diff <= R) {
					visit[nx][ny] = true;
					q.add(new int[] {nx, ny});
					list.add(new int[] {nx, ny});
					sum += map[nx][ny];
				}
			}
		}
		
		if(list.size() == 1)
			return false;
		
		int avg = sum / list.size();
		for(int[] p : list) {
			map[p[0]][p[1]] = avg;
		}
		return true;
	}
	
	static int toInt(String s) {
		return Integer.parseInt(s);
	}
}