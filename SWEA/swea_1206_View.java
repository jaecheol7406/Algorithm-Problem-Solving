package _2022_02;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class swea_1206_View {
	static int T;
	static int N;
	static int[] map;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = 10;
		
		for(int t = 1; t <= T; t++) {
			N = toInt(br.readLine());
			map = new int[N];
			String[] in = br.readLine().split(" ");
			for(int i = 0; i < N; i++) {
				map[i] = toInt(in[i]);
			}
			
			int sum = 0;
			for(int i = 2; i <= N - 3; i++) {
				int leftMax = Integer.max(map[i - 1], map[i - 2]);
				if(leftMax > map[i]) {
					continue;
				}
				
				int rightMax = Integer.max(map[i + 1], map[i + 2]);
				if(rightMax > map[i])
					continue;
				
				sum += (map[i] - Integer.max(leftMax, rightMax));
			}
			
			print(t, sum);
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
