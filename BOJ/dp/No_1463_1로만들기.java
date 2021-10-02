package _2021_10;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class No_1463_1로만들기 {
	static int[] dp;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = toInt(br.readLine());
		dp = new int[N + 1];
		System.out.println(dfs(N));
	}
	
	static int dfs(int N) {
		if(N == 1)
			return 0;
		else if(N == 2 || N == 3)
			return 1;
		
		if(dp[N] != 0)
			return dp[N];
		
		int min = Integer.MAX_VALUE;
		if(N % 3 == 0) {
			min = Integer.min(min, dfs(N / 3));
		}
		
		if(N % 2 == 0) {
			min = Integer.min(min, dfs(N / 2));
		}
		
		min = Integer.min(min, dfs(N - 1));
		return dp[N] += (1 + min);
	}
	
	static int toInt(String s) {
		return Integer.parseInt(s);
	}
}