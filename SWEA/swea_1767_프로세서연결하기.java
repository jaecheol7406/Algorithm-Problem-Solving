package _2022_02;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class swea_1767_프로세서연결하기 {
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	
	static int T;
	static int N;
	static int[][] map;
	static List<int[]> coreList;
	static int maxCoreLinked;
	static int minLenSum;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = toInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			N = toInt(br.readLine());
			map = new int[N][N];
			coreList = new ArrayList<>();
			maxCoreLinked = 0;
			minLenSum = N * N;
			
			for(int i = 0; i < N; i++) {
				String[] in = br.readLine().split(" ");
				for(int j = 0; j < N; j++) {
					map[i][j] = toInt(in[j]);
					if(map[i][j] == 1) {
						if(i != 0 && i != N - 1 && j != 0 && j != N - 1) {
							coreList.add(new int[] {i, j});
						}
					}
				}
			}
			
			dfs(0, 0, 0);
			print(t, minLenSum);
		}
	}
	
	static void dfs(int idx, int coreLinked, int lenSum) {
		if(idx == coreList.size()) {
			if(maxCoreLinked == coreLinked) {
				if(lenSum < minLenSum) {
					minLenSum = lenSum;
				}
			} else if(maxCoreLinked < coreLinked) {
				minLenSum = lenSum;
				maxCoreLinked = coreLinked;
			}
			
			return;
		}
		
		int[] pos = coreList.get(idx);
		boolean flag = false;
		for(int i = 0; i < 4; i++) {
			int x = pos[0];
			int y = pos[1];
			List<int[]> eleString = new ArrayList<int[]>();
			while(true) {
				x += dx[i];
				y += dy[i];
				
				if(!(0 <= x && x < N && 0 <= y && y < N)) {
//					if(lenSum + eleString.size() < minLenSum) {
						flag = true;
						dfs(idx + 1, coreLinked + 1, lenSum + eleString.size());
//					}
					
					for(int[] elePos : eleString) {
						map[elePos[0]][elePos[1]] = 0;
					}
					
					break;
				}
				
				if(map[x][y] == 1 || map[x][y] == 2) {
					for(int[] elePos : eleString) {
						map[elePos[0]][elePos[1]] = 0;
					}
					break;
				}
				
				eleString.add(new int[] {x, y});
				map[x][y] = 2;
			}
		}
		
		if(!flag) {
			dfs(idx + 1, coreLinked, lenSum);
		}
	}
	
	public static int toInt(String s) {
		return Integer.parseInt(s.trim());
	}
	
	public static void print(int t, int result) {
		System.out.println("#" + t + " " + result);
	}
	
	public static void print(int t, String result) {
		System.out.println("#" + t + " " + result);
	}
}
