package _23년_상반기;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class No_21609_상어중학교 {
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	
	static int N, M;
	static int[][] map;
	static boolean[][] visit;
	static int score;
	
	static class Group implements Comparable<Group>{
		ArrayList<Pos> posList;
		Pos stdPos;
		int rainbowCount;
		
		public Group(ArrayList<Pos> pList, Pos sp, int rc) {
			posList = pList;
			stdPos = sp;
			rainbowCount = rc;
		}
		
		public int compareTo(Group g) {
			if(posList.size() == g.posList.size()) {
				if(rainbowCount == g.rainbowCount) {
					if(stdPos.x == g.stdPos.x) {
						return g.stdPos.y - stdPos.y;
					}
					
					return g.stdPos.x - stdPos.x;
				}
				
				return g.rainbowCount - rainbowCount;
			}
			
			return g.posList.size() - posList.size();
		}
	}
	
	static class Pos implements Comparable<Pos>{
		int x, y;
		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
		public int compareTo(Pos p) {
			if(this.x == p.x) {
				return this.y - p.y;
			}
			return this.x - p.x;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in = br.readLine().split(" ");
		N = toInt(in[0]);
		M = toInt(in[1]);
		map = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			in = br.readLine().split(" ");
			for(int j = 0; j < N; j++) {
				map[i][j] = toInt(in[j]);
			}
		}
		
		while(true) {
			visit = new boolean[N][N];
			boolean group = false;
			ArrayList<Group> grList = new ArrayList<>();
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(map[i][j] < 1 || visit[i][j]) {
						continue;
					}
					
					if(bfs(i, j, grList)) {
						group = true;
					}	
				}
			}
						
			if(!group) {
				break;
			}
			
			Collections.sort(grList);
			Group gr = grList.get(0);
			score += Math.pow(gr.posList.size(), 2);
			for(Pos pos : gr.posList) {
				map[pos.x][pos.y] = -2;
			}
			
			down();
			
			map = rotate(map);
			
			down();
		}
		
		System.out.println(score);
	}
	
	static void down() {
		for(int j = 0; j < N; j++) {
			ArrayList<Integer> list = new ArrayList<Integer>();
			for(int i = 0; i < N; i++) {
				if(map[i][j] == -1) {
					int idx = i - 1;
					for(int k = list.size() - 1; k >= 0; k--) {
						map[idx--][j] = list.get(k);
					}
					list = new ArrayList<Integer>();
				} else if(map[i][j] >= 0) {
					list.add(map[i][j]);
					map[i][j] = -2;
				}
			}
			
			if(list.size() > 0) {
				int idx = N - 1;
				for(int k = list.size() - 1; k >= 0; k--) {
					map[idx--][j] = list.get(k);
				}
			}
		}
	}
	
	static int[][] rotate(int[][] map) {
		int[][] temp = new int[N][N];
		for(int i = 0; i < N; i++) {
			int jj = i;
			for(int j = 0; j < N; j++) {
				int ii = N - 1 - j;
				temp[ii][jj] = map[i][j];
			}
		}
		return temp;
	}
	
	static boolean bfs(int sx, int sy, ArrayList<Group> grList) {
		int v = map[sx][sy];
		visit[sx][sy] = true;
		Queue<Pos> q = new LinkedList<>();
		q.add(new Pos(sx, sy));
		
		ArrayList<Pos> posList = new ArrayList<>();
		posList.add(new Pos(sx, sy));
		int rainbowCount = 0;
		
		while(!q.isEmpty()) {
			Pos p = q.poll();
			
			for(int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				
				if(!(0 <= nx && nx < N && 0 <= ny && ny < N)) {
					continue;
				}
				
				if(visit[nx][ny] || map[nx][ny] == -1 || map[nx][ny] == -2) {
					continue;
				}
				
				if(map[nx][ny] == v) {
					visit[nx][ny] = true;
					q.add(new Pos(nx, ny));
					posList.add(new Pos(nx, ny));
				} else if(map[nx][ny] == 0) {
					visit[nx][ny] = true;
					q.add(new Pos(nx, ny));
					posList.add(new Pos(nx, ny));
					rainbowCount++;
				}
			}
		}
		
		if(posList.size() == 1) {
			return false;
		}
		
		Collections.sort(posList);
		
		Pos stdPos = null;
		for(Pos pos : posList) {
			if(map[pos.x][pos.y] != 0) {
				if(stdPos == null) {
					stdPos = pos;
				}
			} else {
				visit[pos.x][pos.y] = false;
			}
		}
		
		Group group = new Group(posList, stdPos, rainbowCount);
		grList.add(group);
		return true;
	}
	
	public static int toInt(String s) {
		return Integer.parseInt(s);
	}
}
