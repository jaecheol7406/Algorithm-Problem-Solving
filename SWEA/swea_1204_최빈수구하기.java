package _2022_02;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class swea_1204_최빈수구하기 {
	static int T;
	static int N = 1000;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = toInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			br.readLine();
			
			String[] in = br.readLine().split(" ");
			int[] counts = new int[101];
			for(int i = 0; i < N; i++) {
				int score = toInt(in[i]);
				counts[score]++;
			}
			
			int max = -1;
			int maxScore = -1;
			for(int i = 0; i <= 100; i++) {
				if(max <= counts[i]) {
					max = counts[i];
					maxScore = i;
				}
			}
			
			print(t, maxScore);
		}
	}
	
	public static int toInt(String s) {
		return Integer.parseInt(s);
	}
	
	public static void print(int t, int result) {
		System.out.println("#" + t + " " + result);
	}
	
	public static void print(int t, String result) {
		System.out.println("#" + t + " " + result);
	}
}
