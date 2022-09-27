package _22년_하반기;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class No_18405_경쟁적전염 {
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	static int N,K;
	static int[][] map;
	static int S,X,Y;
	static Queue<int[]>[] list;
	static boolean[][] visit;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in = br.readLine().split(" ");
		N = toInt(in[0]);
		K = toInt(in[1]);
		
		map = new int[N][N];
		list = new Queue[K + 1];
		visit = new boolean[N][N];
		
		for(int i = 1; i <= K; i++) {
			list[i] = new LinkedList<>();
		}

		for(int i = 0; i < N; i++) {
			in = br.readLine().split(" ");
			for(int j = 0; j < N; j++) {
				map[i][j] = toInt(in[j]);
				if(map[i][j] > 0) {
					list[map[i][j]].add(new int[] {i, j});
					visit[i][j] = true;
				}
			}
		}
		
		in = br.readLine().split(" ");
		S = toInt(in[0]);
		X = toInt(in[1]) - 1;
		Y = toInt(in[2]) - 1;
		
		while(S-- > 0) {
			boolean flag = false;
			for(int i = 1; i <= K; i++) {
				Queue<int[]> q = list[i];
				int size = q.size();
				for(int j = 0; j < size; j++) {
					int[] p = q.poll();
					int x = p[0];
					int y = p[1];
					
					for(int k = 0; k < 4; k++) {
						int nx = x + dx[k];
						int ny = y + dy[k];
						
						if(!(0 <= nx && nx < N && 0 <= ny && ny < N))
							continue;
						
						if(visit[nx][ny])
							continue;
						
						visit[nx][ny] = true;
						q.add(new int[] {nx, ny});
						map[nx][ny] = i;
						flag = true;
					}
				}
			}
			if(!flag) {
				break;
			}
		}
		
		System.out.println(map[X][Y]);
	}
	
	public static int toInt(String s) {
		return Integer.parseInt(s);
	}
}
