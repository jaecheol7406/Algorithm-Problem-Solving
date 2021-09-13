package _2021_09;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
/*
 * 
 */
public class No_10026_적록색약 {
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	static int N;
	static char[][] map;
	static boolean[][] visit;
	static int a, b;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = toInt(br.readLine());
		map = new char[N][N];
		for(int i = 0; i < N; i++) {
			String[] arr = br.readLine().split("");
			for(int j = 0; j < N; j++) {
				map[i][j] = arr[j].charAt(0);
			}
		}
		
		visit = new boolean[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(!visit[i][j]) {
					bfs(i, j, map[i][j]);
					a++;
				}
			}
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(map[i][j] == 'G')
					map[i][j] = 'A';
			}
		}
		
		visit = new boolean[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(!visit[i][j]) {
					bfs(i, j, map[i][j]);
					b++;
				}
			}
		}
		
		System.out.println(a + " " + b);
	}
	
	static void bfs(int sx, int sy, char color) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {sx, sy});
		visit[sx][sy] = true;
		
		while(!q.isEmpty()) {
			int x = q.peek()[0];
			int y = q.poll()[1];
			
			for(int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(!(0 <= nx && nx < N && 0 <= ny && ny < N))
					continue;
				
				if(visit[nx][ny] || map[nx][ny] != map[x][y])
					continue;
				
				q.add(new int[] {nx, ny});
				visit[nx][ny] = true;
			}
		}
	}
	
	static int toInt(String s) {
		return Integer.parseInt(s);
	}
}