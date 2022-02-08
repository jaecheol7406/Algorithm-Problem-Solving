package _2022_01;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class No_10026_적록색약 {
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	static int N;
	static char[][] map;
	static boolean[][] visit;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = toInt(br.readLine());
		map = new char[N][N];
		for(int i = 0; i < N; i++) {
			String s = br.readLine();
			for(int j = 0; j < N; j++) {
				map[i][j] = s.charAt(j);
			}
		}
		
		int count = 0;
		visit = new boolean[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(visit[i][j])
					continue;
				bfs(i, j, 1);
				count++;
			}
		}
		
		int count2 = 0;
		visit = new boolean[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(visit[i][j])
					continue;
				bfs(i, j, 2);
				count2++;
			}
		}
		
		System.out.println(count + " " + count2);
	}
	
	static void bfs(int sx, int sy, int a) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {sx, sy});
		visit[sx][sy] = true;
		
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
				
				char now = map[x][y];
				char next = map[nx][ny];
				if(a == 1) {
					if(now == next) {
						q.add(new int[] {nx ,ny});
						visit[nx][ny] = true;
					}
				} else {
					if((now == 'B' && next == now) || (now != 'B' && next != 'B')) {
						q.add(new int[] {nx, ny});
						visit[nx][ny] = true;
					}
				}
			}
		}
	}
	
	static int toInt(String s) {
		return Integer.parseInt(s);
	}
}