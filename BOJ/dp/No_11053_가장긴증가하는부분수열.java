package _2021_10;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class No_11053_가장긴증가하는부분수열 {
	static int N;
	static int[] arr;
	static int[] dp;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = toInt(br.readLine());
		arr = new int[N];
		String[] in = br.readLine().split(" ");
		for(int i = 0; i < N; i++) {
			arr[i] = toInt(in[i]);
		}
		
		dp = new int[N];
		dp[0] = 1;
		for(int i = 1; i < N; i++) {
			int max = -1;
			for(int j = 0; j < i; j++) {
				if(arr[j] >= arr[i])
					continue;
				
				if(max < dp[j])
					max = dp[j];
			}
			if(max != -1)
				dp[i] = max + 1;
			else
				dp[i] = 1;
		}
		
		int max = -1;
		for(int i = 0; i < N; i++) {
			if(max < dp[i])
				max = dp[i];
		}
		System.out.println(max);
	}
	
	static int toInt(String s) {
		return Integer.parseInt(s);
	}
}