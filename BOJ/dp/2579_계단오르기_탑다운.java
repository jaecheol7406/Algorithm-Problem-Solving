package _2021_10;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class No_2579_계단오르기_탑다운 {
	static int N;
	static int[] stair;
	static int[] dp;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = toInt(br.readLine());
		stair = new int[N];
		dp = new int[N];
		for(int i = 0; i < N; i++) {
			stair[i] = toInt(br.readLine());
		}
		
		System.out.println(dfs(N - 1));
	}
	
	static int dfs(int n) {
		if(n == 0) {
			return dp[n] = stair[n];
		} else if(n == 1) {
			return dp[n] = stair[n] + stair[n - 1];
		} else if(n == 2) {
			return dp[n] = Integer.max(stair[0], stair[1]) + stair[2];
		}
		
		if(dp[n] != 0)
			return dp[n];
		
		return dp[n] = Integer.max(dfs(n - 2), dfs(n - 3) + stair[n - 1]) + stair[n]; 
	}
	
	static int toInt(String s) {
		return Integer.parseInt(s);
	}
}