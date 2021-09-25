package _2021_09;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
/*
 * 
 */
public class No_5052_전화번호목록 {
	static int t, n;
	static String[] arr;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		t = toInt(br.readLine());
		
		while(t-- > 0) {
			n = toInt(br.readLine());
			arr = new String[n];
			for(int i = 0; i < n; i++) {
				arr[i] = br.readLine();
			}
			if(doit())
				System.out.println("YES");
			else
				System.out.println("NO");
		}
	}
	
	static boolean doit() {
		Arrays.sort(arr);
		for(int i = 1; i < n; i++) {
			if(arr[i].startsWith(arr[i - 1])) {
				return false;
			}
		}
		
		return true;
	}
	
	static int toInt(String s) {
		return Integer.parseInt(s);
	}
}