package _2021_10;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class No_1966_프린터큐 {
	static int T, N, M;
	static class Node {
		int v; 
		int idx;
		
		public Node(int v, int idx) {
			this.v = v;
			this.idx = idx;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = toInt(br.readLine());
		while(T-- > 0) {
			String[] in = br.readLine().split(" ");
			N = toInt(in[0]);
			M = toInt(in[1]);
			in = br.readLine().split(" ");
			
			Queue<Node> q = new LinkedList<>();
			for(int i = 0; i < N; i++) {
				q.add(new Node(toInt(in[i]), i));
			}
			
			int printed = 0;
			while(true) {
				Node std = q.poll();
				int size = q.size();
				boolean goBack = false;
				for(int i = 0; i < size; i++) {
					Node node = q.poll();
					if(node.v > std.v) {
						goBack = true;
					}
					
					q.add(node);
				}
				
				if(goBack) {
					q.add(std);
					continue;
				}
				
				if(std.idx == M) {
					break;
				}
				
				printed++;
			}
			
			System.out.println(printed + 1);
		}
	}
	
	static int toInt(String s) {
		return Integer.parseInt(s);
	}
}