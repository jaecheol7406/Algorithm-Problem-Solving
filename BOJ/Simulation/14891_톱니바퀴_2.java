package __22년_상반기;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class No_14891_톱니바퀴 {
	static int N = 4;
	static int[][] wheels = new int[N][8];
	
	static int K;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int i = 0; i < N; i++) {
			String[] in = br.readLine().split("");
			for(int j = 0; j < 8; j++) {
				wheels[i][j] = toInt(in[j]);
			}
		}
		
		K = toInt(br.readLine());
		for(int i = 0; i < K; i++) {
			String[] in = br.readLine().split(" ");
			int idx = toInt(in[0]) - 1;
			int d = toInt(in[1]);
			
			int[] rotateDir = new int[N];
			rotateDir[idx] = d;
			
			// left
			int prevDir = d;
			for(int j = idx - 1; j >= 0; j--) {
				int prev = wheels[j + 1][6];
				int now = wheels[j][2];
				
				if(now == prev) {
					break;
				}
				
				rotateDir[j] = prevDir * -1;
				prevDir = rotateDir[j];
			}
			
			for(int j = 0; j <= idx - 1; j++) {
				if(rotateDir[j] != 0) {
					rotate(wheels[j], rotateDir[j]);
				}
			}
			
			// right
			prevDir = d;
			for(int j = idx + 1; j < N; j++) {
				int prev = wheels[j - 1][2];
				int now = wheels[j][6];
				
				if(now == prev) {
					break;
				}
				
				rotateDir[j] = prevDir * -1;
				prevDir = rotateDir[j];
			}
			
			for(int j = idx + 1; j < N; j++) {
				if(rotateDir[j] != 0) {
					rotate(wheels[j], rotateDir[j]);
				}
			}
			
			// middle
			rotate(wheels[idx], d);
		}
		
		int score = 0;
		for(int i = 0; i < N; i++) {
			if(wheels[i][0] == 0)
				continue;
			
			score += Math.pow(2, i);
		}
		System.out.println(score);
	}
	
	static void rotate(int[] wheel, int d) {
		if(d == 1) {
			int last = wheel[7];
			for(int i = 6; i >= 0; i--) {
				wheel[i + 1] = wheel[i];
			}
			wheel[0] = last;
		} else {
			int first = wheel[0];
			for(int i = 1; i <= 7; i++) {
				wheel[i - 1] = wheel[i];
			}
			wheel[7] = first;
		}
	}
	
	public static int toInt(String s) {
		return Integer.parseInt(s);
	}
}
