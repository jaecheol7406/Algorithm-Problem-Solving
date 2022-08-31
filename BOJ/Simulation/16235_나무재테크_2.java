package _22년_하반기;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class No_16235_나무재테크 {
	static int[] dx = {0,0,1,1,1,-1,-1,-1};
	static int[] dy = {1,-1,0,1,-1,0,-1,1};
	static int N,M,K;
	static int[][] A;
	static int[][] energy;
	static ArrayList<Integer>[][] map;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in = br.readLine().split(" ");
		N = toInt(in[0]);
		M = toInt(in[1]);
		K = toInt(in[2]);
		
		A = new int[N][N];
		for(int i = 0; i < N; i++) {
			in = br.readLine().split(" ");
			for(int j = 0; j < N; j++) {
				A[i][j] = toInt(in[j]);
			}
		}
		energy = new int[N][N];
		for(int i = 0; i < N; i++) {
			Arrays.fill(energy[i], 5);
		}
		
		map = new ArrayList[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				map[i][j] = new ArrayList<>();
			}
		}
		
		for(int i = 0; i < M; i++) {
			in = br.readLine().split(" ");
			int x = toInt(in[0]) - 1;
			int y = toInt(in[1]) - 1;
			int z = toInt(in[2]);
			
			map[x][y].add(z);
		}
		
		while(K-- > 0) {
			int[][] plusEnergy = new int[N][N];
			int[][] plus = new int[N][N];
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					ArrayList<Integer> trees = map[i][j];
					ArrayList<Integer> newTrees = new ArrayList<Integer>();
					for(int k = 0; k < trees.size(); k++) {
						int tree = trees.get(k);
						if(tree <= energy[i][j]) {
							newTrees.add(tree + 1);
							if((tree + 1) % 5 == 0) {
								for(int d = 0; d < 8; d++) {
									int nx = i + dx[d];
									int ny = j + dy[d];
									if(!(0 <= nx && nx < N && 0 <= ny && ny < N))
										continue;
									plus[nx][ny]++;
								}
							}
							energy[i][j] -= tree;
						} else {
							for(int k2 = k; k2 < trees.size(); k2++) {
								plusEnergy[i][j] += trees.get(k2) / 2;
							}
							break;
						}
					}
					
					map[i][j] = newTrees;
					
				}
			}
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					energy[i][j] += (plusEnergy[i][j] + A[i][j]);
					for(int k = 0; k < plus[i][j]; k++) {
						map[i][j].add(0, 1);
					}
				}
			}
		}
		
		int re = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				re += map[i][j].size();
			}
		}
		
		System.out.println(re);
	}
	
	public static int toInt(String s) {
		return Integer.parseInt(s);
	}
}
