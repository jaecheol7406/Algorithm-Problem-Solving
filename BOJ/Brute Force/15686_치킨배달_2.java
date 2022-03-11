package _2022_03;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class No_15686_치킨배달 {
	static int N, M;
	static int[][] map;
	static ArrayList<int[]> chList = new ArrayList<int[]>();
	static ArrayList<int[]> hList = new ArrayList<int[]>();
	static int minRe = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in = br.readLine().split(" ");
		N = toInt(in[0]);
		M = toInt(in[1]);
		map = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			in = br.readLine().split(" ");
			for(int j = 0; j < N; j++) {
				map[i][j] = toInt(in[j]);
				if(map[i][j] == 2) {
					chList.add(new int[] {i, j});
				} else if(map[i][j] == 1) {
					hList.add(new int[] {i, j});
				}
			}
		}
		
		int n = chList.size();
		int r = M;
		
		dfs(new int[r], 0, 0, new boolean[n], n, r);
		System.out.println(minRe);
	}
	
	static void dfs(int[] re, int start, int idx, boolean[] visit, int n, int r) {
		if(idx == r) {
			int sum = 0;
			for(int[] h : hList) {
				int min = Integer.MAX_VALUE;
				for(int rIdx : re) {
					int[] ch = chList.get(rIdx);
					int diff = Math.abs(h[0] - ch[0]) + Math.abs(h[1] - ch[1]);
					if(min > diff)
						min = diff;
				}
				sum += min;
			}
			
			if(minRe > sum)
				minRe = sum;
			
			return;
		}
		
		for(int i = start; i < n; i++) {
			if(!visit[i]) {
				visit[i] = true;
				re[idx] = i;
				dfs(re, i + 1, idx + 1, visit, n, r);
				visit[i] = false;
			}
		}
	}
	
	static int toInt(String s) {
		return Integer.parseInt(s);
	}
}