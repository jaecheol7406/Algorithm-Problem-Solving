package _2022_01;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class No_16235_나무재테크 {
	static int[] dx = {0,0,1,1,1,-1,-1,-1};
	static int[] dy = {1,-1,0,-1,1,0,-1,1};
	
	static int N, M, K;
	static int[][] energys;
	static int[][] plus;
	static PriorityQueue<Integer>[][] map;
	
	static int total;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in = br.readLine().split(" ");
		N = toInt(in[0]);
		M = toInt(in[1]);
		K = toInt(in[2]);
		
		energys = new int[N][N];
		for(int i = 0; i < N; i++) {
			Arrays.fill(energys[i], 5);
		}
		
		plus = new int[N][N];
		
		map = new PriorityQueue[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				map[i][j] = new PriorityQueue<Integer>();
			}
		}
		
		for(int i = 0; i < N; i++) {
			in = br.readLine().split(" ");
			for(int j = 0; j < N; j++) {
				plus[i][j] = toInt(in[j]);
			}
		}
		
		for(int i = 0; i < M; i++) {
			in = br.readLine().split(" ");
			int x = toInt(in[0]) - 1;
			int y = toInt(in[1]) - 1;
			int age = toInt(in[2]);
			
			map[x][y].add(age);
		}
		
		while(K-- > 0) {
			
			int[][] newTreeCounts = new int[N][N];
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					PriorityQueue<Integer> trees = map[i][j];
					if(trees.size() == 0)
						continue;
					
					int energy = energys[i][j];
					
					PriorityQueue<Integer> newTrees = new PriorityQueue<Integer>();
					
					int energyFromDie = 0;
					while(!trees.isEmpty()) {
						int tree = trees.poll();
						if(tree <= energy) {
							newTrees.add(tree + 1);
							energy -= tree;
							
							if((++tree) % 5 == 0) {
								for(int k = 0; k < 8; k++) {
									int nx = i + dx[k];
									int ny = j + dy[k];
									
									if(!(0 <= nx && nx < N && 0 <= ny && ny < N))
										continue;
									
									newTreeCounts[nx][ny]++;
								}
							}
						} else {
							energyFromDie += (tree / 2);
							break;
						}
					}
					
					while(!trees.isEmpty()) {
						int tree = trees.poll();
						energyFromDie += (tree / 2);
					}
					
					map[i][j] = newTrees;
					energys[i][j] = energy + energyFromDie;
				}
			}
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					int newTreeCount = newTreeCounts[i][j];
					for(int k = 0; k < newTreeCount; k++) {
						map[i][j].add(1);
					}
					
					energys[i][j] += plus[i][j];
				}
			}
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				total += map[i][j].size();
			}
		}
		
		System.out.println(total);
	}
	
	static int toInt(String s) {
		return Integer.parseInt(s);
	}
}