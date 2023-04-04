package _23년_상반기;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class No_21608_상어초등학교 {
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	
	static int[][] like;
	static int[][] map;
	static int N, M;
	
	static class Seat implements Comparable<Seat>{
		int x, y;
		int likeCount;
		int emptyCount;
		
		public Seat(int x, int y, int lc, int ec) {
			this.x = x;
			this.y = y;
			likeCount = lc;
			emptyCount = ec;
		}
		
		public int compareTo(Seat s) {
			if(this.likeCount == s.likeCount) {
				if(this.emptyCount == s.emptyCount) {
					if(this.x == s.x) {
						return this.y - s.y;
					}
					
					return this.x - s.x;
				}
				
				return s.emptyCount - this.emptyCount; 
			}
			
			return s.likeCount - this.likeCount;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = toInt(br.readLine());
		M = N * N;
		like = new int[M + 1][M + 1];
		map = new int[N][N];
		for(int i = 0; i < M; i++) {
			String[] in = br.readLine().split(" ");
			int a = toInt(in[0]);
			int b = toInt(in[1]);
			int c = toInt(in[2]);
			int d = toInt(in[3]);
			int e = toInt(in[4]);
			
			like[a][b] = 1;
			like[a][c] = 1;
			like[a][d] = 1;
			like[a][e] = 1;
			
			PriorityQueue<Seat> pq = new PriorityQueue<>();
			
			for(int j = 0; j < N; j++) {
				for(int k = 0; k < N; k++) {
					if(map[j][k] != 0) {
						continue;
					}
					
					int likeCount = 0;
					int emptyCount = 0;
					for(int dd = 0; dd < 4; dd++) {
						int nx = j + dx[dd];
						int ny = k + dy[dd];
						
						if(!(0 <= nx && nx < N && 0 <= ny && ny < N)) {
							continue;
						}
						
						if(like[a][map[nx][ny]] == 1) {
							likeCount++;
						} else if(map[nx][ny] == 0) {
							emptyCount++;
						}
					}
					
					pq.add(new Seat(j,k,likeCount,emptyCount));
				}
			}
			
			Seat seat = pq.poll();
			map[seat.x][seat.y] = a;
		}
		
		int re = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				int a = map[i][j];
				int likeCount = 0;
				for(int dd = 0; dd < 4; dd++) {
					int nx = i + dx[dd];
					int ny = j + dy[dd];
					
					if(!(0 <= nx && nx < N && 0 <= ny && ny < N)) {
						continue;
					}
					
					int b = map[nx][ny];
					if(like[a][b] == 1) {
						likeCount++;
					}
				}
				
				re += (int)Math.pow(10, likeCount - 1);
			}
		}
		
		System.out.println(re);
	}
	
	public static int toInt(String s) {
		return Integer.parseInt(s);
	}
}
