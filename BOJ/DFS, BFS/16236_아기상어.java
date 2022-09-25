package _22년_하반기;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class No_16236_아기상어 {
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	
	static int N;
	static int[][] map;
	static int shx, shy;
	static int size = 2;
	static int eatCount = 0;
	static int t;
	
	static class Node implements Comparable<Node> {
		int x, y, dist;
		
		public Node(int x, int y, int dist) {
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
		
		public int compareTo(Node n) {
			if(dist == n.dist) {
				if(x == n.x) {
					return y - n.y;
				}
				
				return x - n.x;	
			}
			
			return dist - n.dist;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = toInt(br.readLine());
		map = new int[N][N];
		for(int i = 0; i < N; i++) {
			String[] in = br.readLine().split(" ");
			for(int j = 0; j < N; j++) {
				map[i][j] = toInt(in[j]);
				if(map[i][j] == 9) {
					shx = i;
					shy = j;
					map[i][j] = 0;
				}
			}
		}
		
		while(true) {
			if(!bfs(shx, shy))
				break;
		}
		System.out.println(t);
	}
	
	static boolean bfs(int sx, int sy) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {sx, sy, 0});
		PriorityQueue<Node> canEat = new PriorityQueue<>();
		boolean[][] visit = new boolean[N][N];
		visit[sx][sy] = true;
		
		while(!q.isEmpty()) {
			int[] p = q.poll();
			int x = p[0];
			int y = p[1];
			int dist = p[2];
			
			for(int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(!(0 <= nx && nx < N && 0 <= ny && ny < N) || visit[nx][ny])
					continue;
			
				if(map[nx][ny] > size)
					continue;
				
				visit[nx][ny] = true;
				
				q.add(new int[] {nx, ny, dist + 1});
				
				if(map[nx][ny] < size && map[nx][ny] > 0) {
					canEat.add(new Node(nx, ny, dist + 1));
				}
			}
		}
		
		if(canEat.size() == 0)
			return false;
		
		Node eat = canEat.poll();
		map[eat.x][eat.y] = 0;
		if(++eatCount == size) {
			size++;
			eatCount = 0;
		}
		shx = eat.x; shy = eat.y;
		t += eat.dist;
		return true;
	}
	
	public static int toInt(String s) {
		return Integer.parseInt(s);
	}
}
