package _2021_10;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class No_18119_단어암기 {
	static int N, M;
	static int[] words;
	static int know;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in = br.readLine().split(" ");
		N = toInt(in[0]);
		M = toInt(in[1]);
		words = new int[N];
		for(int i = 0; i < N; i++) {
			String s = br.readLine();
			for(char c : s.toCharArray()) {
				words[i] |= 1 << (c - 'a'); 
			}
		}
		
		know = N;
		
		int alphabet = (1 << 26) - 1;
		
		for(int i = 0; i < M; i++) {
			in = br.readLine().split(" ");
			int o = toInt(in[0]);
			char c = in[1].charAt(0);
			
			if(o == 1) {
				alphabet &= ~(1 << (c - 'a'));
			} else {
				alphabet |= 1 << (c - 'a');
			}
			
			int count = 0;
			for(int word : words) {
				if(word == (word & alphabet))
					count++;
			}
			System.out.println(count);
		}
	}
	
	static int toInt(String s) {
		return Integer.parseInt(s);
	}
}