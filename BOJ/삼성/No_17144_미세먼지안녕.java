package _22년_하반기;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class No_17144_미세먼지안녕 {
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	static int N,M;
	static int T;
	static int[][] map;
	static int x1 = -1, y1, x2, y2;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in = br.readLine().split(" ");
		N = toInt(in[0]);
		M = toInt(in[1]);
		T = toInt(in[2]);
		
		map = new int[N][M];
		for(int i = 0; i < N; i++) {
			in = br.readLine().split(" ");
			for(int j = 0; j < M; j++) {
				int v = toInt(in[j]);
				map[i][j] = v;
				if(v == -1) {
					if(x1 == -1) {
						x1 = i;
						y1 = j;
					} else {
						x2 = i;
						y2 = j;
					}
				}
			}
		}
		
		while(T-- > 0) {
			int[][] temp = new int[N][M];
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					if(map[i][j] == 0 || map[i][j] == -1)
						continue;
					
					int spv = map[i][j] / 5;
					for(int d = 0; d < 4; d++) {
						int nx = i + dx[d];
						int ny = j + dy[d];
						
						if(!(0 <= nx && nx < N && 0 <= ny && ny < M))
							continue;
						
						if(map[nx][ny] == -1)
							continue;
						
						temp[nx][ny] += spv;
						map[i][j] -= spv;
					}
					temp[i][j] += map[i][j];
				}
			}
			
			map = temp;
			map[x1][y1] = -1;
			map[x2][y2] = -1;
			
			up();
			down();
		}

		int sum = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] != -1) {
					sum += map[i][j];
				}
			}
		}
		
		System.out.println(sum);
	}
	
	static void up() {
		for(int i = x1 - 2; i >= 0; i--) {
			map[i + 1][0] = map[i][0];
			map[i][0] = 0;
		}
		
		for(int j = 1; j < M; j++) {
			map[0][j - 1] = map[0][j];
			map[0][j] = 0;
		}
		
		for(int i = 1; i <= x1; i++) {
			map[i - 1][M - 1] = map[i][M - 1];
			map[i][M - 1] = 0;
		}
		
		for(int j = M - 2; j >= 1; j--) {
			map[x1][j + 1] = map[x1][j];
			map[x1][j] = 0;
		}
	}

	static void down() {
		for(int i = x2 + 2; i < N; i++) {
			map[i - 1][0] = map[i][0];
			map[i][0] = 0;
		}
		
		for(int j = 1; j < M; j++) {
			map[N - 1][j - 1] = map[N - 1][j];
			map[N - 1][j] = 0;
		}
		
		for(int i = N - 2; i >= x2; i--) {
			map[i + 1][M - 1] = map[i][M - 1];
			map[i][M - 1] = 0;
		}
		
		for(int j = M - 2; j >= 1; j--) {
			map[x2][j + 1] = map[x2][j];
			map[x2][j] = 0;
		}
	}
	
	public static int toInt(String s) {
		return Integer.parseInt(s);
	}
}
