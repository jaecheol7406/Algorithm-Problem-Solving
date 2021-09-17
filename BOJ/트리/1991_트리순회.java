package _2021_09;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
/*
 * 
 */
public class No_1991_트리순회 {
	static HashMap<String, String[]> tree = new HashMap<>();
	static int N;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = toInt(br.readLine());
		for(int i = 0; i < N; i++) {
			String[] in = br.readLine().split(" ");
			String parent = in[0];
			String left = in[1];
			String right = in[2];
			tree.put(parent, new String[] {left, right});
		}
		
		pre("A");
		System.out.println();
		in("A");
		System.out.println();
		post("A");
		System.out.println();
	}
	
	static void pre(String parent) {
		String[] child = tree.get(parent);
		String left = child[0];
		String right = child[1];
		
		System.out.print(parent);
		if(!left.equals("."))
			pre(left);
		
		if(!right.equals("."))
			pre(right);
	}
	
	static void in(String parent) {
		String[] child = tree.get(parent);
		String left = child[0];
		String right = child[1];
		
		if(!left.equals("."))
			in(left);
		
		System.out.print(parent);

		if(!right.equals("."))
			in(right);
	}
	
	static void post(String parent) {
		String[] child = tree.get(parent);
		String left = child[0];
		String right = child[1];
		
		if(!left.equals("."))
			post(left);
		
		if(!right.equals("."))
			post(right);
		
		System.out.print(parent);
	}
	
	static int toInt(String s) {
		return Integer.parseInt(s);
	}
}