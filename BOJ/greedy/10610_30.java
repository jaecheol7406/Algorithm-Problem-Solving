import java.util.Arrays;
import java.util.Scanner;
/*
 * 10610_30_greedy
 */
public class Main {
	static String N;
	
	public static void main(String[] args) {
		input();
		solve();
	}
	
	static void input() {
		Scanner sc = new Scanner(System.in);
		N = sc.nextLine();
		sc.close();
	}
	
	static void solve() {
		int sum = 0;
		boolean hasZero = false;
		for(int i = 0; i < N.length(); i++) {
			int n = Integer.parseInt(String.valueOf(N.charAt(i)));
			if(n == 0)
				hasZero = true;
			sum += n;
		}
		if(sum % 3 != 0 || !hasZero) {
			System.out.println(-1);
			return;
		}
		char[] arr = N.toCharArray();
		Arrays.sort(arr);
		for(int i = arr.length - 1; i >= 0; i--)
			System.out.print(arr[i]);
	}
}