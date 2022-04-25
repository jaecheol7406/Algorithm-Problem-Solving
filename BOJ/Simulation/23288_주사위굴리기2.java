package __22년_상반기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class No_23288_주사위굴리기2 {
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};
	
	static int[] hor = new int[]{1,3,6,4};
	static int[] ver = new int[]{1,5,6,2};
	
	static int N,M,K;
	static int[][] map;
	static int[][] score;
	static boolean[][] visit;
	
	static int x, y, d = 1;
	static int result;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in = br.readLine().split(" ");
		N = toInt(in[0]);
		M = toInt(in[1]);
		K = toInt(in[2]);
		
		map = new int[N][M];
		score = new int[N][M];
		visit = new boolean[N][M];
		
		for(int i = 0; i < N; i++) {
			in = br.readLine().split(" ");
			for(int j = 0; j < M; j++) {
				map[i][j] = toInt(in[j]);
			}
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(!visit[i][j]) {
					bfs(i, j);
				}
			}
		}
		
		while(K-- > 0) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			
			if(nx == N) {
				nx = N - 2;
				d = changeDir(d);
			} else if(nx == -1) {
				nx = 1;
				d = changeDir(d);
			} else if(ny == M) {
				ny = M - 2;
				d = changeDir(d);
			} else if(ny == -1) {
				ny = 1;
				d = changeDir(d);
			}
			
			if(d == 1) {
				moveRight();
			} else if(d == 3) {
				moveLeft();
			} else if(d == 2) {
				moveDown();
			} else {
				moveUp();
			}
			
			result += score[nx][ny];
			
			int A = hor[2];
			int B = map[nx][ny];
			if(A > B) {
				if(++d == 4)
					d = 0;
			} else if(A < B) {
				if(--d == -1)
					d = 3;
			} else {
				
			}
			
			x = nx;
			y = ny;
		}
		
		System.out.println(result);
	}
	
	static void leftRotate(int[] arr) {
		int first = arr[0];
		for(int i = 1; i < 4; i++) {
			arr[i - 1] = arr[i];
		}
		arr[3] = first;
	}
	
	static void rightRotate(int[] arr) {
		int last = arr[3];
		for(int i = 2; i >= 0; i--) {
			arr[i + 1] = arr[i];
		}
		arr[0] = last;
	}
	
	static void moveLeft() {
		leftRotate(hor);
		ver[0] = hor[0];
		ver[2] = hor[2];
	}
	
	static void moveRight() {
		rightRotate(hor);
		ver[0] = hor[0];
		ver[2] = hor[2];
	}

	static void moveUp() {
		leftRotate(ver);
		hor[0] = ver[0];
		hor[2] = ver[2];
	}
	
	static void moveDown()	{
		rightRotate(ver);
		hor[0] = ver[0];
		hor[2] = ver[2];
	}
	
	static int changeDir(int d) {
		int changed = 2 - d;
		if(d % 2 == 1)
			changed = 4 - d;
		return changed;
	}
	
	static void bfs(int sx, int sy) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {sx, sy});
		visit[sx][sy] = true;
		
		List<int[]> list = new ArrayList<>();
		list.add(new int[] {sx, sy});
		
		while(!q.isEmpty()) {
			int[] p = q.poll();
			int x = p[0];
			int y = p[1];
			
			for(int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(!(0 <= nx && nx < N && 0 <= ny && ny < M) || visit[nx][ny])
					continue;
				
				if(map[x][y] == map[nx][ny]) {
					q.add(new int[] {nx, ny});
					visit[nx][ny] = true;
					
					list.add(new int[] {nx, ny});
				}
			}
		}
		
		int v = list.size() * map[sx][sy];
		for(int[] p : list) {
			score[p[0]][p[1]] = v;
		}
	}
	
	public static int toInt(String s) {
		return Integer.parseInt(s);
	}
}
