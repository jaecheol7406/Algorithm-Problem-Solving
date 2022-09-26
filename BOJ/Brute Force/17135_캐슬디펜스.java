package _22년_하반기;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class No_17135_캐슬디펜스 {
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	
	static int N, M, D;
	static int[][] map;
	static int enemyCount;
	static int max = -1;
	static class Node implements Comparable<Node>{
		int x,y,d;
		public Node(int x, int y, int d) {
			this.x = x;
			this.y = y;
			this.d = d;
		}
		public int compareTo(Node n) {
			if(d == n.d) {
				return y - n.y;
			}
			return d - n.d;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in = br.readLine().split(" ");
		N = toInt(in[0]);
		M = toInt(in[1]);
		D = toInt(in[2]);
		
		map = new int[N][M];
		for(int i = 0; i < N; i++) {
			in = br.readLine().split(" ");
			for(int j = 0; j < M; j++) {
				map[i][j] = toInt(in[j]);
				if(map[i][j] == 1) {
					enemyCount++;
				}
			}
		}
		
		for(int i = 0; i < M - 2; i++) {
			for(int j = i + 1; j < M - 1; j++) {
				for(int k = j + 1; k < M; k++) {
					simul(new int[] {i,j,k}, co(map));
				}
			}
		}
		
		System.out.println(max);
	}
	
	static int[][] co(int[][] map){
		int[][] temp = new int[N][M];
		for(int i = 0; i < N; i++) {
			temp[i] = map[i].clone();
		}
		return temp;
	}
	
	static void simul(int[] arr, int[][] map) {
		int count = 0;
		int remove = 0;
		while(true) {
			ArrayList<int[]> reList = new ArrayList<>();
			
			for(int idx : arr) {
				bfs(N - 1, idx, map, reList);
			}
			
			int removeNow = 0;
			for(int[] p : reList) {
				int x = p[0];
				int y = p[1];
				
				if(map[x][y] == 1) {
					map[x][y] = 0;
					removeNow++;
				}
			}
			
			count += removeNow;
			remove += removeNow;
			
			if(count == enemyCount) {
				break;
			}
			
			for(int j = 0; j < M; j++) {
				if(map[N - 1][j] == 1) {
					map[N - 1][j] = 0;
					count++;
				}
			}

			if(count == enemyCount) {
				break;
			}
			
			for(int i = N - 2; i >= 0; i--) {
				for(int j = 0; j < M; j++) {
					map[i + 1][j] = map[i][j];
				}
			}
			
			for(int j = 0; j < M; j++) {
				map[0][j] = 0;
			}
		}
		
		if(max < remove) {
			max = remove;
		}
	}
	
	static void bfs(int sx, int sy, int[][] map, ArrayList<int[]> removeList) {
		if(map[sx][sy] == 1) {
			removeList.add(new int[] {sx, sy});
			return;
		}
		
		Queue<int[]> q = new LinkedList<>();
		boolean[][] visit = new boolean[N][M];
		q.add(new int[] {sx, sy, 1});
		visit[sx][sy] = true;
		
		ArrayList<Node> list = new ArrayList<>();
		
		while(!q.isEmpty()) {
			int[] node = q.poll();
			int x = node[0];
			int y = node[1];
			int d = node[2];
			
			if(map[x][y] == 1 && d <= D) {
				list.add(new Node(x,y,d));
			}
		
			for(int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(!(0 <= nx && nx < N && 0 <= ny && ny < M))
					continue;
				
				if(visit[nx][ny])
					continue;
				
				visit[nx][ny] = true;
				q.add(new int[] {nx,ny,d + 1});
				
//				if(map[nx][ny] == 1 && d + 1 <= D) {
//					list.add(new Node(x,y,d + 1));
//				}
			}
		}
		
		Collections.sort(list);
		if(list.size() == 0)
			return;
		
		Node n = list.get(0);
		removeList.add(new int[] {n.x, n.y});
	}
	
	public static int toInt(String s) {
		return Integer.parseInt(s);
	}
}
