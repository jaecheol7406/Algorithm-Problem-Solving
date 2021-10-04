package _2021_10;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class No_11726_2xn타일링 {
	static int n;
	static int[] dp;
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = toInt(br.readLine());
		if(n <= 2) {
			System.out.println(n);
			return;
		}
		
		dp = new int[n + 1];
		dp[1] = 1;
		dp[2] = 2;
		for(int i = 3; i <= n; i++) {
			dp[i] = (dp[i - 1] + dp[i - 2]) % 10007;
		}
		
		System.out.println(dp[n]);
	}
	
	static int toInt(String s) {
		return Integer.parseInt(s);
	}
}