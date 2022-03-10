package _2022_03;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;

public class No_1987_알파벳 {
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	
	static int N, M;
	static char[][] map;
	
	static int max;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in = br.readLine().split(" ");
		N = toInt(in[0]);
		M = toInt(in[1]);
		
		map = new char[N][M];
		
		for(int i = 0; i < N; i++) {
			String s = br.readLine();
			for(int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j);
			}
		}
		HashSet<Character> hs = new HashSet<Character>();
		hs.add(map[0][0]);
		dfs(0, 0, 1, hs);
		System.out.println(max);
	}
	
	static void dfs(int x, int y, int count, HashSet<Character> hs) {
		if(max < count)
			max = count;
		
		for(int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(!(0 <= nx && nx < N && 0 <= ny && ny < M))
				continue;
			
			char next = map[nx][ny];
			if(hs.contains(next))
				continue;
			
			hs.add(next);
			dfs(nx, ny, count + 1, hs);
			hs.remove(next);
		}
	}
	
	static int toInt(String s) {
		return Integer.parseInt(s);
	}
}