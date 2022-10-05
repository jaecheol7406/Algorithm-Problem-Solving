package _22년_하반기;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class No_15683_감시 {
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	
	static int[][][] arr = new int[][][] {
		{},
		{
			{0}, {1}, {2}, {3}
		},
		{
			{0,1}, {2,3}
		},
		{
			{0,3}, {0,2}, {1,2}, {1,3}
		},
		{
			{0,1,3}, {0,2,3}, {0,1,2}, {1,2,3}
		},
		{
			{0,1,2,3}
		}
	};
	
	static int N, M;
	static int[][] map;
	static List<int[]> list = new ArrayList<int[]>();
	static int zeroCount;
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in = br.readLine().split(" ");
		N = toInt(in[0]);
		M = toInt(in[1]);
		
		map = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			in = br.readLine().split(" ");
			for(int j = 0; j < M; j++) {
				map[i][j] = toInt(in[j]);
				if(map[i][j] >= 1 && map[i][j] <= 5) {
					list.add(new int[] {i, j});
				} else if(map[i][j] == 0) {
					zeroCount++;
				}
			}
		}
		
		dfs(0, co(map), zeroCount);
		
		System.out.println(min);
	}
	
	static void dfs(int idx, int[][] map, int zeroCount) {
		if(idx == list.size()) {
			min = Integer.min(min, zeroCount);
			return;
		}
		
		int[] p = list.get(idx);
		int x = p[0];
		int y = p[1];
		
		int type = map[x][y];
		int[][] cctvs = arr[type];
		
		for(int[] cctv: cctvs) {
			int[][] temp = co(map);
			int tempZero = zeroCount;
			
			for(int d : cctv) {
				int nx = x;
				int ny = y;
				while(true) {
					nx += dx[d];
					ny += dy[d];
					
					if(!(0 <= nx && nx < N && 0 <= ny && ny < M) || temp[nx][ny] == 6) {
						break;
					}
					
					if(temp[nx][ny] == 0) {
						tempZero--;
						temp[nx][ny] = 7;
					}
				}
			}
			
			dfs(idx + 1, temp, tempZero);
		}
	}
	
	static int[][] co(int[][] map){
		int[][] temp = new int[N][M];
		for(int i = 0; i < N; i++) {
			temp[i] = map[i].clone();
		}
		return temp;
	}
	
	public static int toInt(String s) {
		return Integer.parseInt(s);
	}
}
