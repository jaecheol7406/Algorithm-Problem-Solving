package __22년_상반기;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class No_21610_마법사상어와비바라기 {
	static int[] dx = {0,0,-1,-1,-1,0,1,1,1};
	static int[] dy = {0,-1,-1,0,1,1,1,0,-1};
	
	static int N, M;
	static int[][] map;
	static ArrayList<int[]> clouds = new ArrayList<>();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] in = br.readLine().split(" ");
		N = toInt(in[0]);
		M = toInt(in[1]);
		
		map = new int[N][N];
		
		clouds.add(new int[] {N - 1, 0});
		clouds.add(new int[] {N - 1, 1});
		clouds.add(new int[] {N - 2, 0});
		clouds.add(new int[] {N - 2, 1});
		
		for(int i = 0; i < N; i++) {
			in = br.readLine().split(" ");
			for(int j = 0; j < N; j++) {
				map[i][j] = toInt(in[j]);
			}
		}
		
		for(int i = 0; i < M; i++) {
			in = br.readLine().split(" ");
			int d = toInt(in[0]);
			int s = toInt(in[1]) % N;
			
			boolean[][] flag = new boolean[N][N];
			
			ArrayList<int[]> newClouds = new ArrayList<>();
			ArrayList<int[]> newWaters = new ArrayList<>();
			
			for(int[] cloud : clouds) {
				int x = cloud[0];
				int y = cloud[1];
				
				x = change(x + dx[d] * s);
				y = change(y + dy[d] * s);
				
				map[x][y]++;
				flag[x][y] = true;
				
				newWaters.add(new int[] {x, y});
			}
			
			int[][] temp = new int[N][N];
			for(int[] wa : newWaters) {
				int x = wa[0];
				int y = wa[1];

				int count = 0;
				for(int j = 2; j <= 8; j += 2) {
					int nx = x + dx[j];
					int ny = y + dy[j];
					
					if(!(0 <= nx && nx < N && 0 <= ny && ny < N)) {
						continue;
					}
					
					if(map[nx][ny] > 0) {
						count++;
					}
				}
				temp[x][y] = count;
			}
			
			for(int j = 0; j < N; j++) {
				for(int k = 0; k < N; k++) {
					if(temp[j][k] > 0) {
						map[j][k] += temp[j][k];
					}
					
					if(map[j][k] >= 2) {
						if(!flag[j][k]) {
							map[j][k] -= 2;
							newClouds.add(new int[] {j, k});
						}
					}
				}
			}
			
			clouds = newClouds;
			
			for(int j = 0; j < N; j++) {
				for(int k = 0; k < N; k++) {
					System.out.print(map[j][k] + " ");
				}
				System.out.println();
			}
			System.out.println();
		}
		
		int re = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				re += map[i][j];
			}
		}
		
		System.out.println(re);
	}
	
	static int change(int idx) {
		if(idx < 0)
			return idx + N;
		else if(idx >= N)
			return idx - N;
		else
			return idx;
	}
	
	public static int toInt(String s) {
		return Integer.parseInt(s);
	}
}
