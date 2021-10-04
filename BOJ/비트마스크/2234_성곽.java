package _2021_10;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
/*
 * 
 */
public class No_2234_성곽 {
	static int[] dx = {0,-1,0,1};
	static int[] dy = {-1,0,1,0};
	
	static int N,M;
	static int[][] map;
	static boolean[][] visit;
	static ArrayList<HashSet<int[]>> list = new ArrayList<>();
	
	static int maxSize;
	static int removeMaxSize;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in = br.readLine().split(" ");
		M = toInt(in[0]);
		N = toInt(in[1]);
		
		map = new int[N][M];
		for(int i = 0; i < N; i++) {
			in = br.readLine().split(" ");
			for(int j = 0; j < M ;j++) {
				map[i][j] = toInt(in[j]);
			}
		}
		visit = new boolean[N][M];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(!visit[i][j]) {
					bfs(i, j);
				}
			}
		}
		
		for(int i = 0; i < list.size() - 1; i++) {
			HashSet<int[]> std = list.get(i);
			for(int j = i + 1; j < list.size(); j++) {
				HashSet<int[]> ano = list.get(j);
				if(removeMaxSize > std.size() + ano.size())
					continue;
				
				remove(std, ano);
			}
		}
		
		System.out.println(list.size());
		System.out.println(maxSize);
		System.out.println(removeMaxSize);
	}
	
	static void remove(HashSet<int[]> std, HashSet<int[]> ano) {
		for(int[] a : std) {
			for(int[] b : ano) {
				if(Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]) == 1) {
					removeMaxSize = std.size() + ano.size();
					return;
				}
			}
		}
	}
	
	static void bfs(int sx, int sy) {
		Queue<int[]> q = new LinkedList<int[]>();
		q.add(new int[] {sx, sy});
		visit[sx][sy] = true;
		
		HashSet<int[]> hs = new HashSet<>();
		hs.add(new int[] {sx, sy});
		
		while(!q.isEmpty()) {
			int[] p = q.poll();
			int x = p[0];
			int y = p[1];
			
			int v = map[x][y];
			int comp = 1;
			for(int i = 0; i < 4; i++) {
				if((v & comp) > 0) {
					comp = comp << 1;
					continue;
				}
				
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(!(0 <= nx && nx < N && 0 <= ny && ny < M) || visit[nx][ny]) {
					comp = comp << 1;
					continue;
				}
				
				q.add(new int[] {nx, ny});
				hs.add(new int[] {nx, ny});
				visit[nx][ny] = true;
				
				comp = comp << 1;
			}
		}
		
		list.add(hs);
		if(maxSize < hs.size())
			maxSize = hs.size();
	}
	
	static int toInt(String s) {
		return Integer.parseInt(s);
	}
}
