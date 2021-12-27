package _2021_12;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class No_11559_PuyoPuyo {
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	
	static int N = 12, M = 6;
	static char[][] map = new char[N][M];
	static boolean[][] visit;
	static int count;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int i = 0; i < N; i++) {
			String s = br.readLine();
			for(int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j);
			}
		}
		
		while(true) {
			visit = new boolean[N][M];
			boolean crack = false; 
					
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					if(!visit[i][j] && map[i][j] != '.') {
						if(bfs(i, j, map[i][j])) {
							crack = true;
						}
					}
				}
			}
			
			if(!crack)
				break;
			
			for(int j = 0; j < M; j++) {
				int next = N - 1;
				for(int i = N - 1; i >= 0; i--) {
					if(map[i][j] != '.') {
						map[next][j] = map[i][j];
						if(next-- != i) {
							map[i][j] = '.';	
						}
					}
				}
			}
			
			count++;
		}
		
		System.out.println(count);
	}
	
	static boolean bfs(int sx, int sy, int c) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {sx, sy});
		visit[sx][sy] = true;
		
		List<int[]> list = new ArrayList<>();
		list.add(new int[] {sx, sy});
		
		while(!q.isEmpty()) {
			int[] p = q.poll();
			int x = p[0];
			int y = p[1];
			
			for(int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(!(0 <= nx && nx < N && 0 <= ny && ny < M))
					continue;
				
				if(visit[nx][ny] || map[nx][ny] != c)
					continue;
				
				q.add(new int[] {nx, ny});
				visit[nx][ny] = true;
				list.add(new int[] {nx, ny});
			}
		}
		
		if(list.size() < 4) {
			return false;
		}
		
		for(int[] p : list) {
			int x = p[0];
			int y = p[1];
			
			map[x][y] = '.';
		}
		
		return true;
	}
	
	static int toInt(String s) {
		return Integer.parseInt(s);
	}
}