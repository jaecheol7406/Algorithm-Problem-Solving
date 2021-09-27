package _2021_09;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
/*
 * 
 */
public class No_7569_토마토 {
	static int[] dh = {0,0,0,0,1,-1};
	static int[] dx = {0,0,1,-1,0,0};
	static int[] dy = {1,-1,0,0,0,0};
	
	static int N, M, H;
	static int[][][] map;
	static int zCount;
	static Queue<int[]> q = new LinkedList<int[]>();
	static boolean[][][] visit;
	static int t;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in = br.readLine().split(" ");
		M = toInt(in[0]);
		N = toInt(in[1]);
		H = toInt(in[2]);
		
		map = new int[H][N][M];
		visit = new boolean[H][N][M];

		for(int k = 0; k < H; k++) {
			for(int i = 0; i < N; i++) {
				in = br.readLine().split(" ");
				for(int j = 0; j < M; j++) {
					map[k][i][j] = toInt(in[j]);
					
					if(map[k][i][j] == 0)
						zCount++;
					else if(map[k][i][j] == 1) {
						q.add(new int[] {k, i, j});
						visit[k][i][j] = true;
					}
				}
			}
		}
		
		if(zCount == 0) {
			System.out.println(0);
			return;
		}
		
		int size = q.size();
		while(true) {
			t++;
			
			for(int j = 0; j < size; j++) {
				int[] p = q.poll();
				int h = p[0];
				int x = p[1];
				int y = p[2];
				
				for(int i = 0; i < 6; i++) {
					int nh = h + dh[i];
					int nx = x + dx[i];
					int ny = y + dy[i];
					
					if(!(0 <= nx && nx < N && 0 <= ny && ny < M && 0 <= nh && nh < H))
						continue;
					
					if(visit[nh][nx][ny])
						continue;
					
					int v = map[nh][nx][ny];
					if(v == 0) {
						q.add(new int[] {nh, nx, ny});
						visit[nh][nx][ny] = true;
						if(--zCount == 0) {
							System.out.println(t);
							return;
						}
					}
				}
			}
			
//			for(int h = 0; h < H; h++) {
//				for(int i = 0; i < N; i++) {
//					for(int j = 0; j < M; j++) {
//						System.out.print(map[h][i][j] + " ");
//					}
//					System.out.println();
//				}
//				System.out.println();
//			}
//			System.out.println();
			
			if(q.isEmpty()) {
				System.out.println(-1);
				return;
			}
			
			size = q.size();
		}
	}
	
	static int toInt(String s) {
		return Integer.parseInt(s);
	}
}