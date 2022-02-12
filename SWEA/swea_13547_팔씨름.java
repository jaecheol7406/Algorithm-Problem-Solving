package _2022_02;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class swea_13547_팔씨름 {
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
				System.out.println("#" + t + " YES");
			} else {
				System.out.println("#" + t + " NO");
			}
		}
	}
	
	public static int toInt(String s) {
		return Integer.parseInt(s);
	}
}
