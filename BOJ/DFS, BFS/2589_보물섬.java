package _22년_하반기;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class No_2589_보물섬 {
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	static int N, M;
	static char[][] map;
	static int max = -1;
	static boolean[][] visit;
	static boolean[][] cal;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in = br.readLine().split(" ");
		N = toInt(in[0]);
		M = toInt(in[1]);
		map = new char[N][M];
		visit = new boolean[N][M];
		
		for(int i = 0; i < N; i++) {
			String s = br.readLine();
			for(int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j);
			}
		}
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				char c = map[i][j];
				if(!visit[i][j] && c == 'L') {
					bfs(i, j);
				}
			}
		}
		
		System.out.println(max);
	}
	
	static void bfs(int sx, int sy) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {sx, sy});
		visit[sx][sy] = true;
		ArrayList<int[]> list = new ArrayList<>();
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
				
				if(visit[nx][ny] || map[nx][ny] == 'W')
					continue;
				
				visit[nx][ny] = true;
				q.add(new int[] {nx, ny});
				list.add(new int[] {nx,ny});
			}
		}
		
		cal = new boolean[N][M];
		for(int[] p : list) {
			if(cal[p[0]][p[1]])
				continue;
			
			cal[p[0]][p[1]] = true;
			bfs2(p[0], p[1]);
		}
	}
	
	static void bfs2(int sx, int sy) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {sx, sy, 0});
		
		boolean[][] visit = new boolean[N][M];
		visit[sx][sy] = true;
		
		int mx = -1, my = -1;
		while(!q.isEmpty()) {
			int[] p = q.poll();
			int x = p[0];
			int y = p[1];
			int d = p[2];
			if(d > max) {
				max = d;
				mx = x; my = y;
			}
			
			for(int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(!(0 <= nx && nx < N && 0 <= ny && ny < M))
					continue;
				
				if(visit[nx][ny] || map[nx][ny] == 'W')
					continue;
				
				visit[nx][ny] = true;
				q.add(new int[] {nx, ny, d + 1});
			}
		}
		
		if(mx != -1)
			cal[mx][my] = true;
	}
	
	public static int toInt(String s) {
		return Integer.parseInt(s);
	}
}
