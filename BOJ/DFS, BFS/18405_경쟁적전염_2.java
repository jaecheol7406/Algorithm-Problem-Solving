package _23년_상반기;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class No_18405_경쟁적전염 {
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	static int N,K;
	static int[][] map;
	static int S,X,Y;
	
	static class Node implements Comparable<Node>{
		int x,y,v;
		public Node(int x, int y, int v) {
			this.x=x;
			this.y=y;
			this.v=v;
		}
		public int compareTo(Node n) {
			return this.v - n.v;
		}
	}
	static PriorityQueue<Node> pq;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in = br.readLine().split(" ");
		N = toInt(in[0]);
		K = toInt(in[1]);
		map = new int[N][N];
		
		pq = new PriorityQueue<>();
		
		for(int i = 0; i < N; i++) {
			in = br.readLine().split(" ");
			for(int j = 0; j < N; j++) {
				map[i][j] = toInt(in[j]);
				if(map[i][j] > 0) {
					pq.add(new Node(i,j,map[i][j]));
				}
			}
		}
		
		in = br.readLine().split(" ");
		S = toInt(in[0]);
		X = toInt(in[1]) - 1;
		Y = toInt(in[2]) - 1;
		
		for(int s = 0; s < S; s++) {
			PriorityQueue<Node> newPq = new PriorityQueue<>();
			int size = pq.size();
			for(int j = 0; j < size; j++) {
				Node n = pq.poll();
				
				int x = n.x;
				int y = n.y;
				int v = n.v;
				
				for(int k = 0; k < 4; k++) {
					int nx = x + dx[k];
					int ny = y + dy[k];
					
					if(!(0 <= nx && nx < N && 0 <= ny && ny < N)){
						continue;
					}
					
					if(map[nx][ny] > 0) {
						continue;
					}
					
					newPq.add(new Node(nx, ny, v));
					map[nx][ny] = map[x][y];
				}
			}
			
			pq = newPq;
		}
		
		System.out.println(map[X][Y]);
	}
	
	public static int toInt(String s) {
		return Integer.parseInt(s);
	}
}