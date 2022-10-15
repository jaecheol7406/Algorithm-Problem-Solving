package _22년_하반기;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class No_17143_낚시왕 {
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,1,-1};
	
	static int N,M,K;
	static Shark[][] map;
	static class Shark {
		int s, d, z;
		public Shark(int c, int d, int e) {
			s = c;
			this.d = d;
			z = e;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in = br.readLine().split(" ");
		N = toInt(in[0]);
		M = toInt(in[1]);
		K = toInt(in[2]);
		map = new Shark[N][M];
		
		int rs = N * 2 - 2;
		int cs = M * 2 - 2;
		for(int i = 0; i < K; i++) {
			in = br.readLine().split(" ");
			int x = toInt(in[0]) - 1;
			int y = toInt(in[1]) - 1;
			int s = toInt(in[2]);
			int d = toInt(in[3]) - 1;
			int z = toInt(in[4]);
			
			if(d == 0 || d == 1) {
				s = s % rs;
			} else {
				s = s % cs;
			}
			
			map[x][y] = new Shark(s,d,z);
		}
		
		int sum = 0;
		for(int j = 0; j < M; j++) {
			for(int i = 0; i < N; i++) {
				if(map[i][j] != null) {
					sum += map[i][j].z;
					map[i][j] = null;
					break;
				}
			}
			
			move();
		}
		
		System.out.println(sum);
	}
	
	static void move() {
		Shark[][] temp = new Shark[N][M];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] == null)
					continue;
				
				Shark sh = map[i][j];
				int nx = i; int ny = j;
				for(int t = 0; t < sh.s; t++) {
					nx += dx[sh.d];
					ny += dy[sh.d];
					
					if(nx == N) {
						sh.d = 0;
						nx = N - 2;
					} else if(nx == -1) {
						sh.d = 1;
						nx = 1;
					} else if(ny == M) {
						sh.d = 3;
						ny = M - 2;
					} else if(ny == -1) {
						sh.d = 2;
						ny = 1;
					}
				}
				
				if(temp[nx][ny] == null) {
					temp[nx][ny] = sh;
				} else {
					if(sh.z > temp[nx][ny].z) {
						temp[nx][ny] = sh;
					}
				}
			}
		}
		
		map = temp;
	}
	
	public static int toInt(String s) {
		return Integer.parseInt(s);
	}
}
