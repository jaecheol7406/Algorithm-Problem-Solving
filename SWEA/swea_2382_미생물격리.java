package _2022_02;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class swea_2382_미생물격리 {
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	static int T;
	static int N, K, M;
	static Node[][] map;
	static class Node {
		int count, dir;
		public Node(int count, int dir) {
			this.count = count;
			this.dir = dir;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = toInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			String[] in = br.readLine().split(" ");
			N = toInt(in[0]);
			M = toInt(in[1]);
			K = toInt(in[2]);
			
			map = new Node[N][N];
			
			for(int i = 0; i < K; i++) {
				in = br.readLine().split(" ");
				int x = toInt(in[0]);
				int y = toInt(in[1]);
				int count = toInt(in[2]);
				int dir = toInt(in[3]) - 1;
				
				map[x][y] = new Node(count, dir);
			}
			
			while(M-- > 0) {
				ArrayList<Node>[][] list = new ArrayList[N][N];
				
				for(int i = 0; i < N; i++) {
					for(int j = 0; j < N; j++) {
						if(map[i][j] == null) {
							continue;
						}
						
						Node n = map[i][j];
						int nx = i + dx[n.dir];
						int ny = j + dy[n.dir];
						
						if(list[nx][ny] == null) {
							list[nx][ny] = new ArrayList<>();	
						}
						
						list[nx][ny].add(new Node(n.count, n.dir));
						
						map[i][j] = null;
					}
				}
				
				for(int i = 0; i < N; i++) {
					for(int j = 0; j < N; j++) {
						if(list[i][j] == null) {
							continue;
						}
						
						if(i == 0 || i == N - 1 || j == 0 || j == N - 1) {
							Node n = list[i][j].get(0);
							if(n.count / 2 != 0) {
								map[i][j] = new Node(n.count / 2, opposite(n.dir));	
							}
						} else {
							int maxCount = -1;
							int dir = -1;
							int sum = 0;
							for(Node n : list[i][j]) {
								sum += n.count;
								if(maxCount < n.count) {
									maxCount = n.count;
									dir = n.dir;
								}
							}
							
							map[i][j] = new Node(sum, dir);
						}
					}
				}
			}
			
			int re = 0;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(map[i][j] != null)
						re += map[i][j].count;
				}
			}
			
			print(t, re);
		}
	}
	
	static int opposite(int dir) {
		if(dir == 0 || dir == 1) {
			return 1 - dir;
		}
		
		return 5 - dir;
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
