package _2021_10;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class No_2579_계단오르기_바텀업 {
	static int N;
	static int[] stair;
	static int[][] dp;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = toInt(br.readLine());
		stair = new int[N];
		dp = new int[N][2];
		for(int i = 0; i < N; i++) {
			stair[i] = toInt(br.readLine());
		}
		
		if(N == 1) {
			System.out.println(stair[0]);
			return;
		}
		
		dp[0][0] = stair[0];
		dp[0][1] = stair[0];
		dp[1][0] = stair[1];
		dp[1][1] = stair[0] + stair[1];
		
		for(int i = 2; i < N; i++) {
			dp[i][0] = Integer.max(dp[i - 2][0], dp[i - 2][1]) + stair[i];
			dp[i][1] = dp[i - 1][0] + stair[i];
		}
		
		System.out.println(Integer.max(dp[N - 1][0], dp[N - 1][1]));
	}
	
	static int toInt(String s) {
		return Integer.parseInt(s);
	}
}