package _2022_02;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class swea_5650_핀볼게임 {
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	
	static int[][] block = new int[6][4];
	static {
		block[1][0] = 1; block[1][1] = 3; block[1][2] = 0; block[1][3] = 2;
		block[2][0] = 1; block[2][1] = 2; block[2][2] = 3; block[2][3] = 0;
		block[3][0] = 2; block[3][1] = 0; block[3][2] = 3; block[3][3] = 1;
		block[4][0] = 3; block[4][1] = 0; block[4][2] = 1; block[4][3] = 2;
		block[5][0] = 1; block[5][1] = 0; block[5][2] = 3; block[5][3] = 2;
	}
	
	static int[][][] warm = new int[11][2][2];
	static boolean[] flag = new boolean[11];
	
	static int T;
	static int N;
	static int[][] map;
	static List<int[]> list;
	static int max;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = toInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			N = toInt(br.readLine());
			map = new int[N][N];
			list = new ArrayList<>();
			max = -1;
			warm = new int[11][2][2];
			flag = new boolean[11];
			
			for(int i = 0; i < N; i++) {
				String[] in = br.readLine().split(" ");
				for(int j = 0; j < N; j++) {
					int v = map[i][j] = toInt(in[j]);
					if(v == 0) {
						list.add(new int[] {i,j});
					} else if(6 <= v && v <= 10) {
						if(!flag[v]) {
							warm[v][0][0] = i;
							warm[v][0][1] = j;
							flag[v] = true;
						} else {
							warm[v][1][0] = i;
							warm[v][1][1] = j;
						}
					}
				}
			}
			
			for(int[] pos : list) {
				int x = pos[0];
				int y = pos[1];
				
				for(int d = 0; d < 4; d++) {
					play(x,y,d);
				}
			}
			
			print(t, max);
		}
	}
	
	static void play(int sx, int sy, int d) {
		int x = sx;
		int y = sy;
		int score = 0;
		while(true) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			
			if(!(0 <= nx && nx < N && 0 <= ny && ny < N)) {
				d = block[5][d];
				x = nx; y = ny;
				score++;
				continue;
			}
			
			if(map[nx][ny] == 0) {
				if(sx == nx && sy == ny) {
					break;
				}
				
				x = nx;
				y = ny;
				continue;
			}
			
			if(map[nx][ny] == -1) {
				break;
			}
			
			if(1 <= map[nx][ny] && map[nx][ny] <= 5) {
				int blockNum = map[nx][ny];
				int changeD = block[blockNum][d];
				x = nx; y = ny; d = changeD;
				
				score++;
			} else {
				int[][] warms = warm[map[nx][ny]];
				int[] first = warms[0];
				int[] second = warms[1];
				if(nx == first[0] && ny == first[1]) {
					x = second[0]; y = second[1];
				} else {
					x = first[0]; y = first[1];
				}
			}
		}
		
		if(max < score) {
			max = score;
		}
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
