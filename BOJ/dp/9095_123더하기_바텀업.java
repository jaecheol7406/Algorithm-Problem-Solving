package _2021_10;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import sun.java2d.pipe.SpanIterator;
/*
 * 
 */
public class No_9095_123더하기_바텀업 {
	static int[] dp;
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = toInt(br.readLine());
		dp = new int[11];
		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 4;
		
		for(int j = 4; j <= 10; j++) {
			dp[j] = dp[j - 1] + dp[j - 2] + dp[j - 3];
		}
		
		for(int i = 0; i < T; i++) {
			int n = toInt(br.readLine());
			
			System.out.println(dfs(n));
		}
	}
	
	static int dfs(int n) {
		if(n == 1)
			return 1;
		else if(n == 2)
			return 2;
		else if(n == 3)
			return 4;
		
//		if(dp[n] != 0)
//			return dp[n];
		
		return dp[n - 1] + dp[n - 2] + dp[n - 3];
	}
	
	static int toInt(String s) {
		return Integer.parseInt(s);
	}
}