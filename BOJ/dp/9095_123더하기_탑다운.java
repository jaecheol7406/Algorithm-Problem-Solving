package _2021_10;
import java.io.BufferedReader;
import java.io.InputStreamReader;
/*
 * 
 */
public class No_9095_123더하기_탑다운 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = toInt(br.readLine());
		int[] dp = new int[11];
		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 4;
		
		for(int j = 4; j <= 10; j++) {
			dp[j] = dp[j - 1] + dp[j - 2] + dp[j - 3];
		}
		
		for(int i = 0; i < T; i++) {
			int n = toInt(br.readLine());
			
			System.out.println(dp[n]);
		}
	}
	
	static int toInt(String s) {
		return Integer.parseInt(s);
	}
}