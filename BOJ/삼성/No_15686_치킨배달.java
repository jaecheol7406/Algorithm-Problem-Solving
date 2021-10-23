package _2021_10;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class No_15686_치킨배달 {
	static int N, M;
	static ArrayList<int[]> homes = new ArrayList<>();
	static ArrayList<int[]> chs = new ArrayList<>();
	static int result = Integer.MAX_VALUE;
	static int n, r;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in = br.readLine().split(" ");
		N = toInt(in[0]);
		M = toInt(in[1]);
		for(int i = 0; i < N; i++) {
			in = br.readLine().split(" ");
			for(int j = 0; j < N; j++) {
				int v = toInt(in[j]);
				if(v == 1) {
					homes.add(new int[] {i, j});
				} else if(v == 2) {
					chs.add(new int[] {i, j});
				}
			}
		}
		
		n = chs.size();
		r = M;
		dfs(0, new int[r], 0, new boolean[n]);
		System.out.println(result);
	}
	
	static void dfs(int start, int[] re, int idx, boolean[] check) {
		if(idx == r) {
			int sum = 0;
			for(int[] h : homes) {
				int x = h[0];
				int y = h[1];
				
				int min = Integer.MAX_VALUE;
				for(int chIdx : re) {
					int[] p = chs.get(chIdx);
					int nx = p[0];
					int ny = p[1];
					
					int dist = Math.abs(x - nx) + Math.abs(y - ny);
					if(dist < min)
						min = dist;
				}
				sum += min;
			}
			
			if(sum < result) {
				result = sum;
			}
			return;
		}
		
		for(int i = start; i < n; i++) {
			if(!check[i]) {
				check[i] = true;
				re[idx] = i;
				dfs(i, re, idx + 1, check);
				check[i] = false;
			}
		}
	}
	
	static int toInt(String s) {
		return Integer.parseInt(s);
	}
}