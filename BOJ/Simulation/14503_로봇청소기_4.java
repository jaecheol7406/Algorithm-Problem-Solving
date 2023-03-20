package _23년_상반기;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class No_14503_로봇청소기 {
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};
	
	static int N, M;
	static int x, y, d;
	static int[][] map;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in = br.readLine().split(" ");
		N = toInt(in[0]);
		M = toInt(in[1]);
		
		in = br.readLine().split(" ");
		x = toInt(in[0]);
		y = toInt(in[1]);
		d = toInt(in[2]);
		
		map = new int[N][M];
		for(int i = 0; i < N; i++) {
			in = br.readLine().split(" ");
			for(int j = 0; j < M; j++) {
				map[i][j] = toInt(in[j]);
			}
		}
		
		int re = 0;
		while(true) {
			if(map[x][y] == 0) {
				map[x][y] = 2;	
				re++;
			}
			
			int i = 0;
			for(; i < 4; i++) {
				if(--d == -1) {
					d = 3;
				}
				
				int nx = x + dx[d];
				int ny = y + dy[d];
				
				if(map[nx][ny] == 0) {
					x = nx;
					y = ny;
					break;
				}
			}
			
			if(i != 4) {
				continue;
			}
			
			int back = d % 2 == 0? 2 - d : 4 - d;
			int bx = x + dx[back];
			int by = y + dy[back];
			
			if(map[bx][by] == 1) {
				break;
			}
			
			x = bx;
			y = by;
		}
		
		System.out.println(re);
	}
	
	public static int toInt(String s) {
		return Integer.parseInt(s);
	}
}
