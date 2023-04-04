package _22년_하반기;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class No_21608_상어초등학교 {
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	static int N;
	static int[][] map;
	static int[][] map2;
	static class Node implements Comparable<Node> {
		int x, y, like, empty;
		public Node(int x, int y, int like, int empty) {
			this.x = x;
			this.y = y;
			this.like = like;
			this.empty = empty;
		}
		public int compareTo(Node n) {
			if(like == n.like) {
				if(empty == n.empty) {
					if(x == n.x) {
						return y - n.y;
					}
					
					return x - n.x;
				}
				
				return n.empty - empty;
			}
			
			return n.like - like;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = toInt(br.readLine());
		map = new int[N*N + 1][N*N + 1];
		map2 = new int[N + 1][N + 1];
		for(int k = 0; k < N*N; k++) {
			String[] in = br.readLine().split(" ");
			int a = toInt(in[0]);
			for(int l = 1; l <= 4; l++) {
				int b = toInt(in[l]);
				map[a][b] = 1;
			}
			
			ArrayList<Node> list = new ArrayList<>();
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= N; j++) {
					if(map2[i][j] > 0)
						continue;
					
					int like = 0, empty = 0;
					for(int d = 0; d < 4; d++) {
						int nx = i + dx[d];
						int ny = j + dy[d];
						
						if(!(1 <= nx && nx <= N && 1 <= ny && ny <= N))
							continue;
						
						int b = map2[nx][ny];
						if(b == 0) {
							empty++;
						} else if(map[a][b] == 1) {
							like++;
						}
					}
					
					list.add(new Node(i, j, like, empty));
				}
			}
			Collections.sort(list);
			Node n = list.get(0);
			map2[n.x][n.y] = a;
		}
		
		int sum = 0;
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				int a = map2[i][j];
				
				int count = 0;
				for(int d = 0; d < 4; d++) {
					int nx = i + dx[d];
					int ny = j + dy[d];
					
					if(!(1 <= nx && nx <= N && 1 <= ny && ny <= N))
						continue;
					
					int b = map2[nx][ny];
					if(map[a][b] == 1)
						count++;
				}
				
				if(count > 0)
					sum += Math.pow(10, count - 1);
			}
		}
		
		System.out.println(sum);
	}
	
	public static int toInt(String s) {
		return Integer.parseInt(s);
	}
}
