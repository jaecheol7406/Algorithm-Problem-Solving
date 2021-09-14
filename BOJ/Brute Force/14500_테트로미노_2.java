package _2021_09;
import java.io.BufferedReader;
import java.io.InputStreamReader;
/*
 * 
 */
public class No_14500_테트로미노 {
	static int N, M;
	static int[][] map;
	static int max = 4;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in = br.readLine().split(" ");
		N = toInt(in[0]);
		M = toInt(in[1]);
		
		map = new int[N][M];
		for(int i = 0; i < N; i++) {
			in = br.readLine().split(" ");
			for(int j = 0; j < M; j++) {
				map[i][j] = toInt(in[j]);
			}
		}
		
		int[][] prev = map;
		for(int r = 1; r <= 4; r++) {
			int[][] temp = rotate(prev);
			
			if(r <= 2)
				one(temp);
			
			if(r <= 2)
				two(temp);
			
			threeFour(temp);
			
			five(temp);
			
			prev = temp;
		}
		
		System.out.println(max);
	}
	
	// 첫번째 도형
	static void one(int[][] map) {
		// 가로
		for(int i = 0; i < N; i++) {
			int sum = 0;
			int j = 0;
			for(; j < 4; j++)
				sum += map[i][j];
			
			if(sum > max)
				max = sum;
			
			for(; j < M; j++) {
				sum += map[i][j];
				sum -= map[i][j - 4];
				if(sum > max)
					max = sum;
			}
		}
	}
	
	// 두번쨰 도형
	static void two(int[][] map) {
		for(int i = 0; i < N - 1; i++) {
			for(int j = 0; j < M - 1; j++) {
				int sum = map[i][j] + map[i][j + 1] + map[i + 1][j] + map[i + 1][j + 1];
				if(sum > max)
					max = sum;
			}
		}
	}
	
	// 세번째, 네번째 도형
	static void threeFour(int[][] map) {
		
		// 세번째, 네번째 도형 모두 기준 똑같음.
		for(int i = 0; i < N - 2; i++) {
			for(int j = 0; j < M - 1; j++) {
				
				// 세번째 도형
				int sum = map[i][j] + map[i + 1][j] + map[i + 2][j] + map[i + 2][j + 1]; // 원래모양
				if(sum > max)
					max = sum;
				
				sum = map[i][j + 1] + map[i + 1][j + 1] + map[i + 2][j + 1] + map[i + 2][j]; // 뒤집어서
				if(sum > max)
					max = sum;
				
				// 네번째 도형
				sum = map[i][j] + map[i + 1][j] + map[i + 1][j + 1] + map[i + 2][j + 1];
				if(sum > max)
					max = sum;
				
				sum = map[i][j + 1] + map[i + 1][j] + map[i + 1][j + 1] + map[i + 2][j];
				if(sum > max)
					max = sum;
			}
		}
		
		
	}
	
	// 다섯번째 도형
	static void five(int[][] map) {
		for(int i = 0; i < N - 1; i++) {
			for(int j = 0; j < M - 2; j++) {
				int sum = map[i][j] + map[i][j + 1] + map[i][j + 2] + map[i + 1][j + 1];
				if(sum > max)
					max = sum;
			}
		}
	}
	
	static int[][] rotate(int[][] map){
		int[][] temp = new int[M][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				temp[j][N - 1 - i] = map[i][j];
			}
		}
		int k = N;
		N = M;
		M = k;
		return temp;
	}
	
	static int toInt(String s) {
		return Integer.parseInt(s);
	}
}