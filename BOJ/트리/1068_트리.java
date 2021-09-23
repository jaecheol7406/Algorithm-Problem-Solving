package _2021_09;
import java.io.BufferedReader;
import java.io.InputStreamReader;
/*
 * 
 */
public class No_1068_트리 {
	static int N;
	static int[] parent;
	static int root;
	static int remove;
	static int count;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = toInt(br.readLine());
		parent = new int[N];
		
		String[] in = br.readLine().split(" ");
		for(int i = 0; i < N; i++) {
			parent[i] = toInt(in[i]);
			if(parent[i] == -1)
				root = i;
		}
		
		remove = toInt(br.readLine());
		
		if(root != remove)
			dfs(root);
		System.out.println(count);
	}
	
	static void dfs(int now) {
		boolean hasChild = false;
		
		for(int i = 0; i < N; i++) {
			if(now == i || i == remove)
				continue;
			
			if(parent[i] == now) {
				dfs(i);
				hasChild = true;
			}
		}
		
		if(now != root && !hasChild)
			count++;
	}
	
	static int toInt(String s) {
		return Integer.parseInt(s);
	}
}