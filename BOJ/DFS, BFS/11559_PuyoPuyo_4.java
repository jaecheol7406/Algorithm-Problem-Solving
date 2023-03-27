package _23년_상반기;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class No_11559_PuyoPuyo {
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	static int N = 12;
	static int M = 6;
	static char[][] map;
	static boolean[][] visit;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new char[N][M];
		
		for(int i = 0; i < N; i++) {
			String s = br.readLine();
			for(int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j);
			}
		}
		
		int t = 0;
		while(true) {
			visit = new boolean[N][M];
			boolean cango = false;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					if(!visit[i][j] && map[i][j] != '.') {
						if(bfs(i, j, map[i][j])) {
							cango = true;
						}
					}
				}
			}
			
			if(!cango) {
				break;
			}
			
			for(int j = 0; j < M; j++) {
				int targetIdx = N - 1;
				for(int i = N - 1; i >= 0; i--) {
					if(map[i][j] != '.') {
						map[targetIdx][j] = map[i][j];
						if(i != targetIdx) {
							map[i][j] = '.';	
						}
						targetIdx--;
					}
				}
			}
			
			t++;
		}
		
		System.out.println(t);
	}
	
	static boolean bfs(int sx, int sy, char c) {
		List<int[]> list = new ArrayList<int[]>();
		Queue<int[]> q = new LinkedList<int[]>();
		q.add(new int[] {sx, sy});
		visit[sx][sy] = true;
		
		list.add(new int[] {sx, sy});
		
		while(!q.isEmpty()) {
			int[] node = q.poll();
			int x = node[0];
			int y = node[1];
			
			for(int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(!(0 <= nx && nx < N && 0 <= ny && ny < M) || visit[nx][ny]) {
					continue;
				}
				
				if(map[nx][ny] != c) {
					continue;
				}
				
				visit[nx][ny] = true;
				q.add(new int[] {nx, ny});
				list.add(new int[] {nx, ny});
			}
		}
		
		if(list.size() >= 4) {
			for(int[] p : list) {
				map[p[0]][p[1]] = '.';
			}
			return true;
		}
		
		return false;
	}
	
	public static int toInt(String s) {
		return Integer.parseInt(s);
	}
}
