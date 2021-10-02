package _2021_10;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class No_1003_피보나치함수 {
	static int T;
	static int N;
	static int[][] dp;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = toInt(br.readLine());
		
		for(int i = 0; i < T; i++) {
			N = toInt(br.readLine());
			dp = new int[N + 1][2];
			for(int j = 0; j <= N; j++)
				Arrays.fill(dp[j], -1);
			int[] re = d(N);
			System.out.println(re[0] + " " + re[1]);
		}
	}
	
	static int[] d(int n) {
		if(n == 0) {
			return new int[] {dp[n][0] = 1, dp[n][1] = 0};
		} else if(n == 1) {
			return new int[] {dp[n][0] = 0, dp[n][1] = 1};
		}
		
		if(dp[n][0] != -1) {
			return dp[n];
		}
		
		dp[n][0] = d(n - 1)[0] + d(n - 2)[0];
		dp[n][1] = d(n - 1)[1] + d(n - 2)[1];
		
		return dp[n];
	}
	
	static int toInt(String s) {
		return Integer.parseInt(s);
	}
}