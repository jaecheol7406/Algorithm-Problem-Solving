package __22년_상반기;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class No_20413_MVP다이아몬드Easy {
	static int N;
	static int[] std = new int[5];
	static String level = "BSGPD";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = toInt(br.readLine());
		
		String[] in = br.readLine().split(" ");
		for(int i = 0; i < 4; i++) {
			std[i + 1] = toInt(in[i]);
		}
		
		String s = br.readLine();
		int prevAmount = 0;
		int re = 0;
		for(int i = 0; i < N; i++) {
			char c = s.charAt(i);
			
			int idx = level.indexOf(c);
			if(idx == level.length() - 1) {
				prevAmount = std[idx];
			} else {
				prevAmount = std[idx + 1] - 1 - prevAmount;
			}
			
			re += prevAmount;
		}
		System.out.println(re);
	}
	
	public static int toInt(String s) {
		return Integer.parseInt(s);
	}
}
