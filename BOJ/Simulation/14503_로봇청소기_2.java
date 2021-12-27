package _2021_12;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class No_14503_로봇청소기 {
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	static int N, M, x, y, d;
	static int[][] map;
	
	static int count;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in = br.readLine().split(" ");
		N = toInt(in[0]);
		M = toInt(in[1]);
		
		map = new int[N][M];
		
		in = br.readLine().split(" ");
		x = toInt(in[0]);
		y = toInt(in[1]);
		d = toInt(in[2]);
		
		for(int i = 0; i < N; i++) {
			in = br.readLine().split(" ");
			for(int j = 0; j < M; j++) {
				map[i][j] = toInt(in[j]);
			}
		}
		
		go();
		
		System.out.println(count);
	}
	
	static void go() {
		while(true) {
			if(map[x][y] == 0) {
				map[x][y] = 2;
				count++;
			}
			
			boolean canGo = false;
			for(int i = 0; i < 4; i++) {
				if(--d == -1) {
					d = 3;
				}
				
				int nx = x + dx[d];
				int ny = y + dy[d];
				
				if(map[nx][ny] == 0) {
					canGo = true;
					x = nx;
					y = ny;
					break;
				}
			}
			
			if(canGo)
				continue;
			
			int back = (d % 2 == 0 ? 2 - d : 4 - d);
			
			int bx = x + dx[back];
			int by = y + dy[back];
			if(map[bx][by] == 1)
				break;
			
			x = bx;
			y = by;
		}
	}
	
	static int toInt(String s) {
		return Integer.parseInt(s);
	}
}