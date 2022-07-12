package _22년_하반기;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class No_11559_PuyoPuyo {
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	
	static int N, M;
	static char[][] map;
	static boolean[][] visit;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = 12; M = 6;
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
			boolean flag = false;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					if(map[i][j] != '.' && !visit[i][j]) {
						if(bfs(i, j)) {
							flag = true;
						}
					}
				}
			}
			
			if(!flag)
				break;
			
			down();
			
			t++;
		}
		
		System.out.println(t);
	}
	
	static void down() {
		for(int j = 0; j < M; j++) {
			List<Character> list = new ArrayList<>();
			for(int i = N - 1; i >= 0; i--) {
				if(map[i][j] != '.') {
					list.add(map[i][j]);
					map[i][j] = '.';
				}
			}
			
			int idx = N - 1;
			for(char c : list) {
				if(idx == -1)
					System.exit(-1);
				map[idx--][j] = c;
			}
		}
	}
	
	static boolean bfs(int sx, int sy) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {sx, sy});
		visit[sx][sy] = true;
		int v = map[sx][sy];
		
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
				
				if(visit[nx][ny])
					continue;
				
				if(map[nx][ny] == v) {
					q.add(new int[] {nx,ny});
					visit[nx][ny] = true;
					list.add(new int[] {nx,ny});
				}
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
