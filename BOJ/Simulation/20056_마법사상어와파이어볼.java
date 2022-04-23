package __22년_상반기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class No_20056_마법사상어와파이어볼 {
	static int[] dx = {-1,-1,0,1,1,1,0,-1};
	static int[] dy = {0,1,1,1,0,-1,-1,-1};
	static int N, M, K;
	static ArrayList<Ball>[][] map;
	
	static class Ball {
		int m, d, s;
		public Ball(int m, int d, int s) {
			this.m = m;
			this.d = d;
			this.s = s;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in = br.readLine().split(" ");
		N = toInt(in[0]);
		M = toInt(in[1]);
		K = toInt(in[2]);
		
		map = new ArrayList[N][N];
		
		for(int i = 0; i < M; i++) {
			in = br.readLine().split(" ");
			int a = toInt(in[0]) - 1;
			int b = toInt(in[1]) - 1;
			int m = toInt(in[2]);
			int s = toInt(in[3]);
			int d = toInt(in[4]);
			
			map[a][b] = new ArrayList<>();
			map[a][b].add(new Ball(m, d, s));
		}
		
		while(true) {
			ArrayList<Ball>[][] temp = new ArrayList[N][N];
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(map[i][j] == null)
						continue;
					
					for(Ball b : map[i][j]) {
						int realSpeed = b.s % N;
						int nx = i + (dx[b.d] * realSpeed);
						nx = changePos(nx);
						int ny = j + (dy[b.d] * realSpeed);
						ny = changePos(ny);
						
						if(temp[nx][ny] == null)
							temp[nx][ny] = new ArrayList<Main.Ball>();
						
						temp[nx][ny].add(new Ball(b.m, b.d, b.s));
					}
				}
			}
			
			map = new ArrayList[N][N];
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(temp[i][j] == null)
						continue;
					
					if(temp[i][j].size() == 1) {
						map[i][j] = new ArrayList<>();
						map[i][j].add(temp[i][j].get(0));
						continue;
					}
					
					int newM = 0, newS = 0, newD = -1;
					int evenCount = 0, oddCount = 0;
					for(Ball b : temp[i][j]) {
						newM += b.m;
						newS += b.s;
						
						if(b.d % 2 == 0) {
							evenCount++;
						} else {
							oddCount++;
						}
					}
					
					newM /= 5;
					if(newM == 0)
						continue;
					
					map[i][j] = new ArrayList<>();
					
					newS /= temp[i][j].size();
					
					newD = 1;
					if(evenCount == temp[i][j].size() || oddCount == temp[i][j].size()) {
						newD = 0;
					}
					
					for(int k = 0; k < 4; k++) {
						map[i][j].add(new Ball(newM, newD, newS));
						newD += 2;
					}
				}
			}
			
			if(--K == 0) {
				break;
			}
		}
		
		int sum = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(map[i][j] == null || map[i][j].size() == 0)
					continue;
				
				int m = map[i][j].get(0).m;
				sum += m * map[i][j].size();
			}
		}
		
		System.out.println(sum);
	}
	
	static int changePos(int a) {
		if(a < 0) {
			a = a + N;
		} else if(a >= N) {
			a = a - N;
		}
		
		return a;
	}
	
	public static int toInt(String s) {
		return Integer.parseInt(s);
	}
}
