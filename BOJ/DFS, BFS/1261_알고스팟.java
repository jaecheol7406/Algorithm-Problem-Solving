package _23년_상반기;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class No_1261_알고스팟 {
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	
	static class Node implements Comparable<Node>{
		int x, y, count;
		
		public Node(int x, int y, int count) {
			this.x = x;
			this.y = y;
			this.count = count;
		}
		
		public int compareTo(Node n) {
			return this.count - n.count;
		}
	}
	
	static int N,M;
	static int[][] map;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in = br.readLine().split(" ");
		M = toInt(in[0]);
		N = toInt(in[1]);
		map = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			in = br.readLine().split("");
			for(int j = 0; j < M; j++) {
				map[i][j] = toInt(in[j]);
			}
		}
		
		int re = 0;
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[][] visit = new boolean[N][M];
		
		pq.add(new Node(0,0,0));
		visit[0][0] = true;
		
		while(!pq.isEmpty()) {
			Node n = pq.poll();
			
			if(n.x == N - 1 && n.y == M - 1) {
				re = n.count;
				break;
			}
			
			for(int i = 0; i < 4; i++) {
				int nx = n.x + dx[i];
				int ny = n.y + dy[i];
				
				if(!(0 <= nx && nx < N && 0 <= ny && ny < M) || visit[nx][ny]) {
					continue;
				}
				
				if(map[nx][ny] == 1) {
					pq.add(new Node(nx, ny, n.count + 1));
				} else {
					pq.add(new Node(nx, ny, n.count));
				}
				
				visit[nx][ny] = true;
			}
		}
		
		System.out.println(re);
	}
	
	public static int toInt(String s) {
		return Integer.parseInt(s);
	}
}
