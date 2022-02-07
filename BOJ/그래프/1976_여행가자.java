package _2022_01;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class No_1976_여행가자 {
	static int N, M;
	static int[][] map;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = toInt(br.readLine());
		M = toInt(br.readLine());
		map = new int[N][N];
		for(int i = 0; i < N; i++) {
			String[] in =br.readLine().split(" ");
			for(int j = 0; j < N; j++) {
				map[i][j] = toInt(in[j]);
			}
		}
		
		for(int k = 0; k < N; k++) {
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(i == j) {
						map[i][j] = 1;
						continue;
					}
					if(map[i][k] == 1 && map[k][j] == 1) {
						map[i][j] = 1;
					}
				}
			}
		}
		
		String[] in = br.readLine().split(" ");
		int prev = toInt(in[0]) - 1;
		for(int i = 1; i < M; i++) {
			int now = toInt(in[i]) - 1;
			if(map[prev][now] != 1) {
				System.out.println("NO");
				return;
			}
			
			prev = now;
		}
		
		System.out.println("YES");
	}
	
	static int toInt(String s) {
		return Integer.parseInt(s);
	}
}