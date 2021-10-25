package _2021_10;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class No_14890_경사로 {
	static int N, L;
	static int[][] map;
	static int[][] temp;
	static boolean[][] check;
	static int count;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in = br.readLine().split(" ");
		N = toInt(in[0]);
		L = toInt(in[1]);
		map = new int[N][N];
		temp = new int[N][N];
		for(int i = 0; i < N; i++) {
			in = br.readLine().split(" ");
			for(int j = 0; j < N; j++) {
				map[i][j] = toInt(in[j]);
			}
		}
		
		cal(map);
		rotate();
		cal(temp);
		
		System.out.println(count);
	}
	
	static void rotate() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				temp[j][N - 1 - i] = map[i][j];
			}
		}
	}
	
	static void cal(int[][] map) {
		check = new boolean[N][N];
		for(int i = 0; i < N; i++) {
			int prev = map[i][0];
			int j = 1;
			for(; j < N; j++) {
				int now = map[i][j];
				if(now == prev)
					continue;
				
				if(Math.abs(now - prev) > 1)
					break;
				
				int l = 0;
				
				if(now > prev) {
					int k = j - 1;
					while(k >= 0) {
						if(map[i][k] == prev) {
							if(check[i][k])
								break;
							
							if(++l == L)
								break;
						} else {
							break;
						}
						k--;
					}
				} else {
					int k = j;
					while(k < N) {
						if(map[i][k] == now) {
							check[i][k] = true;
							if(++l == L)
								break;
						} else {
							break;
						}
						k++;
					}
				}

				if(l != L) {
					break;
				}
				
				prev = now;
			}
			
			if(j == N) {
				count++;
			}
		}
	}
	
	static int toInt(String s) {
		return Integer.parseInt(s);
	}
}