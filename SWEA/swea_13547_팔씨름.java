package swea;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
	static int T;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = toInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			String s = br.readLine();
			int count = 0;
			for(int i = 0; i < s.length(); i++) {
				if(s.charAt(i) == 'o')
					count++;
			}
			
			int remain = 15 - s.length();
			if(count + remain >= 8) {
				print(t, "YES");
			} else {
				print(t, "NO");
			}
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
