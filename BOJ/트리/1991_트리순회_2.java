package _2022_02;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

public class No_1991_트리순회 {
	static int N;
	static HashMap<Character, char[]> hm = new HashMap<Character, char[]>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = toInt(br.readLine());
		for(int i = 0; i < N; i++) {
			String[] in = br.readLine().split(" ");
			char a = in[0].charAt(0);
			char left = in[1].charAt(0);
			char right = in[2].charAt(0);
						
			hm.put(a, new char[] {left, right});
		}
		
		pre('A');
		System.out.println();
		in('A');
		System.out.println();
		post('A');
		System.out.println();
	}
	
	static void pre(char c) {
		System.out.print(c);
		char[] child = hm.get(c);
		char left = child[0];
		char right = child[1];
		if(left != '.')
			pre(left);
		if(right != '.')
			pre(right);
	}
	
	static void in(char c) {
		char[] child = hm.get(c);
		char left = child[0];
		char right = child[1];
		if(left != '.')
			in(left);
		System.out.print(c);
		if(right != '.')
			in(right);
	}
	
	static void post(char c) {
		char[] child = hm.get(c);
		char left = child[0];
		char right = child[1];
		if(left != '.')
			post(left);
		if(right != '.')
			post(right);
		System.out.print(c);
	}
	
	static int toInt(String s) {
		return Integer.parseInt(s);
	}
}