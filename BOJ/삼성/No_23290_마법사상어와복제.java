package _23년_상반기;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class No_23290_마법사상어와복제 {
	static int[] dx = {0,0,-1,-1,-1,0,1,1,1};
	static int[] dy = {0,-1,-1,0,1,1,1,0,-1};
	
	static int[] ddx = {0,-1,0,1,0};
	static int[] ddy = {0,0,-1,0,1};
	
	static int M,S;
	static int N = 4;
	static ArrayList<Integer>[][] fishArr;
	static int[][] smell;
	static int shx, shy;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		fishArr = new ArrayList[N][N];
		smell = new int[N][N];
		String[] in = br.readLine().split(" ");
		M = toInt(in[0]);
		S = toInt(in[1]);
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				fishArr[i][j] = new ArrayList<>();
			}
		}
		
		for(int i = 0; i < M; i++) {
			in = br.readLine().split(" ");
			int x = toInt(in[0]) - 1;
			int y = toInt(in[1]) - 1;
			int d = toInt(in[2]);
			
			fishArr[x][y].add(d);
		}
		
		in = br.readLine().split(" ");
		shx = toInt(in[0]) - 1;
		shy = toInt(in[1]) - 1;
		
		for(int s = 0; s < S; s++) {
			ArrayList<Integer>[][] temp = new ArrayList[N][N];
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					temp[i][j] = new ArrayList<>();
				}
			}
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					for(int d : fishArr[i][j]) {
						boolean flag = false;
						for(int count = 0; count < 8; count++) {
							int nx = i + dx[d];
							int ny = j + dy[d];
							
							if(!(0 <= nx && nx < N && 0 <= ny && ny < N) || 
									(nx == shx && ny == shy) ||
										smell[nx][ny] > 0) {
								if(--d == 0) {
									d = 8;
								}
								
								if(count == 7) {
									break;
								}
								
								continue;
							}
							
							flag = true;
							
							temp[nx][ny].add(d);
							
							break;
						}
						
						if(!flag) {
							temp[i][j].add(d);
						}
					}
				}
			}
			
			int max = -1;
			int[][] maxPos = new int[3][2];
			boolean[][] kill = new boolean[N][N];
			for(int i = 1; i <= 4; i++) {
				int nx = shx + ddx[i];
				int ny = shy + ddy[i];
				if(!(0 <= nx && nx < N && 0 <= ny && ny < N)) {
					continue;
				}
				kill[nx][ny] = true;
				int count1 = temp[nx][ny].size();
				for(int j = 1; j <= 4; j++) {
					int nx2 = nx + ddx[j];
					int ny2 = ny + ddy[j];
					if(!(0 <= nx2 && nx2 < N && 0 <= ny2 && ny2 < N)) {
						continue;
					}
					kill[nx2][ny2] = true;
					int count2 = temp[nx2][ny2].size();
					for(int k = 1; k <= 4; k++) {
						int nx3 = nx2 + ddx[k];
						int ny3 = ny2 + ddy[k];
						if(!(0 <= nx3 && nx3 < N && 0 <= ny3 && ny3 < N)) {
							continue;
						}
						
						int count3 = 0;
						if(!kill[nx3][ny3]) {
							count3 = temp[nx3][ny3].size();
						}
						
						int count = count1 + count2 + count3;
						if(max < count) {
							max = count;
							maxPos[0][0] = nx; maxPos[0][1] = ny;
							maxPos[1][0] = nx2; maxPos[1][1] = ny2;
							maxPos[2][0] = nx3; maxPos[2][1] = ny3;
						}
					}
					
					kill[nx2][ny2] = false;
				}
				
				kill[nx][ny] = false;
			}
			
			shx = maxPos[2][0];
			shy = maxPos[2][1];
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(smell[i][j] > 0) {
						smell[i][j]--;
					}
				}
			}
			
			for(int[] pos : maxPos) {
				int x = pos[0];
				int y = pos[1];
				if(temp[x][y].size() > 0) {
					smell[pos[0]][pos[1]] = 2;
				}
				temp[x][y] = new ArrayList<>();
			}
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					for(int d : temp[i][j]) {
						fishArr[i][j].add(d);
					}
				}
			}
		}
		
		int sum = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				sum += fishArr[i][j].size();
			}
		}
		
		System.out.println(sum);
	}
	
	public static int toInt(String s) {
		return Integer.parseInt(s);
	}
}
