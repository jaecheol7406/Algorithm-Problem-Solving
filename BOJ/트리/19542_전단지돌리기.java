package _2021_09;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
/*
 * 
 */
public class No_19542_전단지돌리기 {
	static int N, S, D;
	static ArrayList<Integer>[] list;
	static int[] depth;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in = br.readLine().split(" ");
		N = toInt(in[0]);
		S = toInt(in[1]);
		D = toInt(in[2]);
		
		list = new ArrayList[N + 1];
		for(int i = 1; i <= N; i++)
			list[i] = new ArrayList<>();
		
		depth = new int[N + 1];
		
		for(int i = 0; i < N - 1; i++) {
			in = br.readLine().split(" ");
			int a = toInt(in[0]);
			int b = toInt(in[1]);
			list[a].add(b);
			list[b].add(a);
		}
		
		dfs(S, 0);
		
		int count = 0;
		for(int i = 1; i <= N; i++) {
			if(i == S)
				continue;
			
			if(depth[i] >= D)
				count++;
		}
		System.out.println(count * 2);
	}
	
	static int dfs(int v, int parent){
		int max = 0;
		for(int next : list[v]) {
			if(next != parent) {
				max = Integer.max(max, dfs(next, v) + 1);
			}
		}
		depth[v] = max;
		return max;
	}
	
	static int toInt(String s) {
		return Integer.parseInt(s);
	}
}