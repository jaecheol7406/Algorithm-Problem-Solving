package _2021_10;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class No_1149_rgb거리 {
	static int N;
	static int[][] home;
	static int[][] dp;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = toInt(br.readLine());
		home = new int[N][3];
		dp = new int[N][3];
		for(int i = 0; i < N; i++) {
			String[] in = br.readLine().split(" ");
			for(int j = 0; j < 3; j++) {
				home[i][j] = toInt(in[j]);
			}
		}
		
		dp[0][0] = home[0][0];
		dp[0][1] = home[0][1];
		dp[0][2] = home[0][2];
		for(int i = 1; i < N; i++) {
			dp[i][0] =  home[i][0] + Integer.min(dp[i - 1][1], dp[i - 1][2]);
			dp[i][1] =  home[i][1] + Integer.min(dp[i - 1][0], dp[i - 1][2]);
			dp[i][2] =  home[i][2] + Integer.min(dp[i - 1][0], dp[i - 1][1]);
		}
		
		System.out.println(Integer.min(Integer.min(dp[N - 1][0], dp[N - 1][1]), dp[N - 1][2]));
	}
	
	
	
	static int toInt(String s) {
		return Integer.parseInt(s);
	}
}