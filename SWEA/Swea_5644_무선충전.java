package __22년_상반기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class Swea_5644_무선충전 {
	static int[] dx = {0,-1,0,1,0};
	static int[] dy = {0,0,1,0,-1};
	
	static int T;
	static int N = 10;
	static int M, A;
	static int[][] moveInfo;
	static int[][] pos;
	static ArrayList<Integer>[][] map;
	
	static int[] bcInfo;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = toInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			map = new ArrayList[N][N];
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					map[i][j] = new ArrayList<>();
				}
			}
			
			String[] in = br.readLine().split(" ");
			M = toInt(in[0]);
			A = toInt(in[1]);
			
			moveInfo = new int[2][M];
			pos = new int[2][2];
			bcInfo = new int[A];
			
			pos[0][0] = 0; pos[0][1] = 0;
			pos[1][0] = 9; pos[1][1] = 9;
			
			for(int i = 0; i < 2; i++) {
				in = br.readLine().split(" ");
				for(int j = 0; j < M; j++) {
					moveInfo[i][j] = toInt(in[j]);
				}
			}
			
			for(int i = 0; i < A; i++) {
				in = br.readLine().split(" ");
				int x = toInt(in[1]) - 1;
				int y = toInt(in[0]) - 1;
				int c = toInt(in[2]);
				int p = toInt(in[3]);
				bfs(x, y, c, p, i);
				bcInfo[i] = p;
			}
						
			int sum = 0;
			for(int i = 0; i <= M; i++) {
				int max = -1;
				int[] bcCount = new int[A];
				
				ArrayList<Integer>[] list = new ArrayList[2];
				int[] maxP = new int[2];
				
				for(int j = 0; j < 2; j++) {
					int[] p = pos[j];
					
					ArrayList<Integer> bcList = map[p[0]][p[1]];
					if(bcList.size() == 0)
						continue;
					
					list[j] = new ArrayList<>();
					
					for(int idx : bcList) {
						bcCount[idx]++;
						list[j].add(idx);
						if(maxP[j] < bcInfo[idx])
							maxP[j] = bcInfo[idx];
					}
				}
				
				if(list[0] == null) {
					sum += maxP[1];
					if(i == M)
						break;
					for(int j = 0; j < 2; j++) {
						int[] p = pos[j];
						p[0] += dx[moveInfo[j][i]];
						p[1] += dy[moveInfo[j][i]];
					}
					continue;
				}
				
				if(list[1] == null) {
					sum += maxP[0];
					if(i == M)
						break;
					for(int j = 0; j < 2; j++) {
						int[] p = pos[j];
						p[0] += dx[moveInfo[j][i]];
						p[1] += dy[moveInfo[j][i]];
					}
					continue;
				}
				
				for(int j = 0; j < A; j++) {
					int count = bcCount[j];
					if(count >= 2) {
						max = Integer.max(max, bcInfo[j]);
					}
				}
				
				for(int idx : list[0]) {
					for(int idx2 : list[1]) {
						if(idx == idx2)
							continue;
						max = Integer.max(max, bcInfo[idx] + bcInfo[idx2]);
					}
				}
				sum += max;

				if(i == M)
					break;
				
				for(int j = 0; j < 2; j++) {
					int[] p = pos[j];
					p[0] += dx[moveInfo[j][i]];
					p[1] += dy[moveInfo[j][i]];
				}
			}
			
			print(sum, t);
		}
	}
	
	static void bfs(int sx, int sy, int c, int p, int idx) {
		Queue<int[]> q = new LinkedList<>();
		boolean[][] visit = new boolean[N][N];
		q.add(new int[] {sx, sy, 0});
		visit[sx][sy] = true;
		map[sx][sy].add(idx);
		
		while(!q.isEmpty()) {
			int[] info = q.poll();
			int x = info[0];
			int y = info[1];
			int dist = info[2];
			
			if(dist > c)
				continue;
			
			map[x][y].add(idx);
			
			for(int i = 1; i <= 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(!(0 <= nx && nx < N && 0 <= ny && ny < N) || visit[nx][ny])
					continue;
				
				q.add(new int[] {nx, ny, dist + 1});
				visit[nx][ny] = true;
			}
		}
	}
	
	public static void print(int v, int t) {
		System.out.println("#" + t + " " + v);
	}
	
	public static void print(String v, int t) {
		System.out.println("#" + t + " " + v);
	}
	
	public static int toInt(String s) {
		return Integer.parseInt(s);
	}
}
