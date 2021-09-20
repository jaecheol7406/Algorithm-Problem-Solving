package _2021_09;
import java.io.BufferedReader;
import java.io.InputStreamReader;
/*
 * 
 */
public class No_12100_2048easy {
	static int N;
	static int[][] map;
	static int max = -1;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = toInt(br.readLine());
		map = new int[N][N];
		for(int i = 0; i < N; i++) {
			String[] in=  br.readLine().split(" ");
			for(int j = 0; j < N; j++) {
				map[i][j] = toInt(in[j]);
			}
		}
		
		if(N == 1) {
			System.out.println(map[0][0]);
		} else {
			dfs(0, map);
			System.out.println(max);
		}
	}
	
	static void dfs(int count, int[][] map){
		if(count == 1) {
			int maxv = -1;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(maxv < map[i][j])
						maxv = map[i][j];
				}
			}
			
			if(maxv > max)
				max = maxv;
			
			return;
		}
		
		int[][] t = co(map);
		right(t);
		dfs(count + 1, t);
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				System.out.print(t[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
		
		t = co(map);
		left(t);
		dfs(count + 1, t);
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				System.out.print(t[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
		
		t = co(map);
		down(t);
		dfs(count + 1, t);
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				System.out.print(t[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
		
		t = co(map);
		up(t);
		dfs(count + 1, t);
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				System.out.print(t[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
		
	}
	
	static void right(int[][] map) {
		for(int i = 0; i < N; i++) {
			int[] temp = new int[N];
			int idx = N - 1;
			int prev = map[i][N - 1];
			temp[idx--] = prev;
			for(int j = N - 2; j >= 0; j--) {
				int now = map[i][j];
				map[i][j] = 0;
				if(now == 0)
					continue;
				
				if(prev == now) {
					temp[idx + 1] = now * 2;
					idx--;
					prev = -1;
				} else {
					temp[idx--] = now;
					prev = now;
				}
			}
			
			idx = N - 1;
			for(int j = N - 1; j >= 0; j--) {
				if(temp[j] != 0)
					map[i][idx--] = temp[j];
			}
		}
	}

	static void left(int[][] map) {
		for(int i = 0; i < N; i++) {
			int[] temp = new int[N];
			int idx = 0;
			int prev = map[i][0];
			temp[idx++] = prev;
			for(int j = 1; j < N; j++) {
				int now = map[i][j];
				map[i][j] = 0;
				if(now == 0)
					continue;
				
				if(prev == now) {
					temp[idx - 1] = now * 2;
					idx++;
					prev = -1;
				} else {
					temp[idx++] = now;
					prev = now;
				}
			}
			
			idx = 0;
			for(int j = 0; j < N; j++) {
				if(temp[j] != 0)
					map[i][idx++] = temp[j];
			}
		}
	}
	
	static void down(int[][] map) {
		for(int j = 0; j < N; j++) {
			int[] temp = new int[N];
			int idx = N - 1;
			int prev = map[N - 1][j];
			temp[idx--] = prev;
			for(int i = N - 2; i >= 0; i--) {
				int now = map[i][j];
				map[i][j] = 0;
				if(now == 0)
					continue;
				
				if(prev == now) {
					temp[idx + 1] = now * 2;
					idx--;
					prev = -1;
				} else {
					temp[idx--] = now;
					prev = now;
				}
			}
			
			idx = N - 1;
			for(int i = N - 1; i >= 0; i--) {
				if(temp[i] != 0)
					map[idx--][j] = temp[i];
			}
		}
	}
	
	static void up(int[][] map) {
		for(int j = 0; j < N; j++) {
			int[] temp = new int[N];
			int idx = 0;
			int prev = map[0][j];
			temp[idx++] = prev;
			for(int i = 1; i < N; i++) {
				int now = map[i][j];
				map[i][j] = 0;
				if(now == 0)
					continue;
				
				if(prev == now) {
					temp[idx - 1] = now * 2;
					idx++;
					prev = -1;
				} else {
					temp[idx++] = now;
					prev = now;
				}
			}
			
			idx = 0;
			for(int i = 0; i < N; i++) {
				if(temp[i] != 0)
					map[idx++][j] = temp[i];
			}
		}
	}
	static int[][] co(int[][] map){
		int[][] t = new int[N][N];
		for(int i = 0; i < N; i++)
			t[i] = map[i].clone();
		return t;
	}
	
	static int toInt(String s) {
		return Integer.parseInt(s);
	}
}