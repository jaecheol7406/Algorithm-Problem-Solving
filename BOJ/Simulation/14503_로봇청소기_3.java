package _22년_하반기;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class No_14503_로봇청소기 {
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	static int[][] map;
	static int N, M;
	static int x, y, d;
	static int re;
	
	public static void main(String[] args) throws Exception{
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
		
		while(true) {
			if(map[x][y] == 0) {
				re++;
				map[x][y] = 2;	
			}
			
			boolean flag = false;
			for(int c = 0; c < 4; c++) {
				if(--d == -1)
					d = 3;
				
				int nx = x + dx[d];
				int ny = y + dy[d];
				
				if(map[nx][ny] == 0) {
					flag = true;
					x = nx;
					y = ny;
					break;
				}
			}
			
			if(flag) {
				continue;
			}
			
			int nd = 4 - d;
			if(d % 2 == 0) {
				nd = 2 - d;
			}
			
			int nx = x + dx[nd];
			int ny = y + dy[nd];
			
			if(map[nx][ny] == 1) {
				break;
			}
			
			x = nx;
			y = ny;
		}
		
		System.out.println(re);
	}
	
	public static int toInt(String s) {
		return Integer.parseInt(s);
	}
}
