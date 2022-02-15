package _2022_02;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class swea_1824_혁진이의프로그램검증{
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	static int T;
	static int N, M;
	static char[][] map;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = toInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			String[] in = br.readLine().split(" ");
			N = toInt(in[0]);
			M = toInt(in[1]);
			
			map = new char[N][M];
			for(int i = 0; i < N; i++) {
				String s = br.readLine();
				for(int j = 0; j < M; j++) {
					map[i][j] = s.charAt(j);
				}
			}
			
			String result = "NO";
			
			Queue<int[]> q = new LinkedList<>();
			q.add(new int[] {0, 0, 0, 0});
			boolean[][][][] visit = new boolean[N][M][4][16];
			visit[0][0][0][0] = true;
			while(!q.isEmpty()) {
				int[] info = q.poll();
				int x = info[0];
				int y = info[1];
				int memory = info[2];
				int d = info[3];
				
				int[] next = null;
				
				char c = map[x][y];
				
				if(c == '?') {
					boolean finish = false;
					for(int i = 0; i < 4; i++) {
						next = go(i, x, y);
						int nx = next[0];
						int ny = next[1];
						
						if(map[nx][ny] == '@') {
							finish = true;
							break;
						}
						
						if(visit[nx][ny][i][memory])
							continue;
						
						q.add(new int[] {nx, ny, memory, i});
						visit[nx][ny][i][memory] = true;	
					}
					
					if(finish) {
						result = "YES";
						break;
					}
					
					continue;
				}
				
				if('0' <= c && c <= '9') {
					memory = c - 48;
				} else if(c == '>' || c == '<' || c == 'v' || c == '^') {
					if(c == '>') {
						d = 0;
					} else if(c == '<') {
						d = 1;
					} else if(c == 'v') {
						d = 2;
					} else {
						d = 3;
					}
				} else if(c == '+') {
					if(++memory == 16) {
						memory = 0;
					}
				} else if(c == '-') {
					if(--memory == -1) {
						memory = 15;
					}
				} else if(c == '_') {
					if(memory == 0) {
						d = 0;
					} else {
						d = 1;
					}
				} else if(c == '|') {
					if(memory == 0) {
						d = 2;
					} else {
						d = 3;
					}
				} else if(c == '@') {
					result = "YES";
					break;
				} 
				
				next = go(d, x, y);
				x = next[0];
				y = next[1];
				
				if(visit[x][y][d][memory])
					continue;
				
				q.add(new int[] {x, y, memory, d});
				visit[x][y][d][memory] = true;
			}
			
			print(t, result);
		}
	}
	
	static int[] go(int d, int x, int y) {
		int nx = x + dx[d];
		int ny = y + dy[d];
		
		if(nx == -1) {
			nx = N - 1;
		} else if(nx == N) {
			nx = 0;
		} else if(ny == -1) {
			ny = M - 1;
		} else if (ny == M){
			ny = 0;
		}
		
		return new int[] {nx, ny};
	}
	
	public static int toInt(String s) {
		return Integer.parseInt(s);
	}
	
	public static void print(int t, int result) {
		System.out.println("#" + t + " " + result);
	}
	
	public static void print(int t, String result) {
		System.out.println("#" + t + " " + result);
	}
}
