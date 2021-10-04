package _2021_10;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class No_10870_피보나치수5 {
	static int n;
	static int[] dp;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = toInt(br.readLine());
		dp = new int[n + 1];
		Arrays.fill(dp, -1);
		
		System.out.println(fi(n));
	}
	
	static int fi(int n) {
		if(n == 0 || n == 1)
			return n;
		
		if(dp[n] != -1)
			return dp[n];
		
		return fi(n - 1) + fi(n - 2);
	}
	
	static int toInt(String s) {
		return Integer.parseInt(s);
	}
}