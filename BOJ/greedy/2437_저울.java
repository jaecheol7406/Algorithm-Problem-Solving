package _2021_09;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
/*
 * 
 */
public class No_2437_저울 {	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = toInt(br.readLine());
		String[] in = br.readLine().split(" ");
		int[] arr = new int[N];
		for(int i = 0; i < N; i++) {
			arr[i] = toInt(in[i]);
		}
		
		Arrays.sort(arr);
		
		int sum = 0;
		
		for(int i = 0; i < N; i++) {
			if(arr[i] > sum + 1) {
				break;
			}
			
			sum += arr[i];
		}
		
		System.out.println(sum + 1);
	}
	
	static int toInt(String s) {
		return Integer.parseInt(s);
	}
}