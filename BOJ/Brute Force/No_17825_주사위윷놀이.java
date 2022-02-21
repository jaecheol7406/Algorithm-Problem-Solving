package _2022_02;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;

public class No_17825_주사위윷놀이 {
	static int[] num;
	static int maxScore = 0;
	static int[][] board = new int[][] {
		{-1,2,4,6,8,10,12,14,16,18,20,22,24,26,28,30,32,34,36,38,40},
		{-1,13,16,19,25,30,35,40},
		{-1,22,24,25,30,35,40},
		{-1,28,27,26,25,30,35,40}
	};
	
	static HashMap<Integer, Boolean> dupMap = new HashMap<Integer, Boolean>();
	static HashSet<Integer> dupSet = new HashSet<Integer>();
	static {
		dupMap.put(25, false); dupMap.put(30, false); dupMap.put(35, false); dupMap.put(40, false);
		dupSet.add(25); dupSet.add(30); dupSet.add(35); dupSet.add(40);
	}
	
	static int[][] status = new int[4][2];
	static boolean[] finish = new boolean[4];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in = br.readLine().split(" ");
		num = new int[10];
		for(int i = 0; i < 10; i++) {
			num[i] = toInt(in[i]);
		}
		
		dfs(0, 0);
		
		System.out.println(maxScore);
	}
	
	static void dfs(int idx, int score) {
		if(idx == 10) {
			if(maxScore < score) {
				maxScore = score;
			}
			return;
		}
		
		boolean in = false;
		int n = num[idx];
		for(int i = 0; i < status.length; i++) {
			if(finish[i])
				continue;
			
			int[] horse = status[i];
			int bIdx = horse[0];
			int hIdx = horse[1];
			
			hIdx += n;
			if(hIdx >= board[bIdx].length) {
				finish[i] = true;
				int bTemp = horse[0];
				int hTemp = horse[1];
				horse[0] = 0;
				horse[1] = 0;
				
				if(dupSet.contains(board[bTemp][hTemp])) {
					dupMap.put(board[bTemp][hTemp], false);
				}
				dfs(idx + 1, score);
				if(dupSet.contains(board[bTemp][hTemp])) {
					dupMap.put(board[bTemp][hTemp], true);
				}
				
				horse[0] = bTemp;
				horse[1] = hTemp;
				finish[i] = false;
				
				continue;
			}
						
			int plus = board[bIdx][hIdx];

			if(bIdx == 0) {
				if(hIdx == 5) {
					bIdx = 1;
					hIdx = 0;
				} else if(hIdx == 10) {
					bIdx = 2;
					hIdx = 0;
				} else if(hIdx == 15) {
					bIdx = 3;
					hIdx = 0;
				}
			}
			
			if(dupSet.contains(board[bIdx][hIdx]) && dupMap.get(board[bIdx][hIdx])) {
				continue;
			}
			
			boolean dup = false;
			for(int j = 0; j < status.length; j++) {
				if(j == i)
					continue;
				
				if(status[j][0] == bIdx && status[j][1] == hIdx) {
					dup = true;
					break;
				}
			}
			
			if(dup) {
				continue;
			}
			
			in = true;
			
			int bTemp = horse[0];
			int hTemp = horse[1];
			horse[0] = bIdx;
			horse[1] = hIdx;
			
			if(dupSet.contains(board[bTemp][hTemp])) {
				dupMap.put(board[bTemp][hTemp], false);
			}
			if(dupSet.contains(board[bIdx][hIdx])) {
				dupMap.put(board[bIdx][hIdx], true);
			}
			dfs(idx + 1, score + plus);
			if(dupSet.contains(board[bTemp][hTemp])) {
				dupMap.put(board[bTemp][hTemp], true);
			}
			if(dupSet.contains(board[bIdx][hIdx])) {
				dupMap.put(board[bIdx][hIdx], false);
			}
			
			horse[0] = bTemp;
			horse[1] = hTemp;
		}
		
		if(!in) {
			dfs(idx + 1, score);
		}
	}
	
	static int toInt(String s) {
		return Integer.parseInt(s);
	}
}