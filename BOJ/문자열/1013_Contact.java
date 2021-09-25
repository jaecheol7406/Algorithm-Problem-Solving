package _2021_09;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/*
 * 
 */
public class No_1013_Contact {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = toInt(br.readLine());
		
		Pattern pattern = Pattern.compile("^(100+1+|01)+$");
		
		for(int i = 0; i < t; i++) {
			String s = br.readLine();
			Matcher matcher = pattern.matcher(s);
			if(matcher.find())
				System.out.println("YES");
			else
				System.out.println("NO");
		}
	}
	
	static int toInt(String s) {
		return Integer.parseInt(s);
	}
}