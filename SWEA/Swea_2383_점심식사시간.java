package __22년_상반기;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Swea_2383_점심식사시간 {
	static int T;
	static int N;
	static ArrayList<Person> list = new ArrayList<>();
	
	static int x1,y1,k1, x2,y2,k2;
	
	static int n, r;
	
	static int min = Integer.MAX_VALUE;
	
	static class Person {
		int x, y, dist1, dist2, target, remain, strRemain;
		boolean inStair, finish;
		
		public Person(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = toInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			N = toInt(br.readLine());
			list = new ArrayList<>();
			x1 = -1;
			for(int i = 0; i < N; i++) {
				String[] in = br.readLine().split(" ");
				for(int j = 0; j < N; j++) {
					int v = toInt(in[j]);
					if(v == 1) {
						list.add(new Person(i, j));
					} else if(v > 1) {
						if(x1 == -1) {
							x1 = i; y1 = j; k1 = v;
						} else {
							x2 = i; y2 = j; k2 = v;
						}
					}
				}
			}
			
			for(Person p : list) {
				int dist1 = Math.abs(p.x - x1) + Math.abs(p.y - y1);
				int dist2 = Math.abs(p.x - x2) + Math.abs(p.y - y2);
				p.dist1 = dist1;
				p.dist2 = dist2;
			}
			
			min = Integer.MAX_VALUE;
			
			n = list.size();
			
			for(r = 0; r <= n / 2; r++) {
				dfs(0, new int[r], 0, new boolean[n]);
			}
			
			print(min, t);
		}
	}
	
	static void dfs(int start, int[] re, int idx, boolean[] check) {
		if(idx == r) {
			int[] re2 = new int[n - r];
			int tempIdx = 0;
			for(int i = 0; i < n; i++) {
				if(!check[i])
					re2[tempIdx++] = i;
			}
			
			for(int reIdx : re) {
				Person p = list.get(reIdx);
				p.target = 1;
				p.remain = p.dist1;
				p.inStair = false;
				p.finish = false;
				p.strRemain = k1;
			}
			
			for(int re2Idx : re2) {
				Person p = list.get(re2Idx);
				p.target = 2;
				p.remain = p.dist2;
				p.inStair = false;
				p.finish = false;
				p.strRemain = k2;
			}
			
			simul();
			
			for(int reIdx : re) {
				Person p = list.get(reIdx);
				p.target = 2;
				p.remain = p.dist2;
				p.inStair = false;
				p.finish = false;
				p.strRemain = k2;
			}
			
			for(int re2Idx : re2) {
				Person p = list.get(re2Idx);
				p.target = 1;
				p.remain = p.dist1;
				p.inStair = false;
				p.finish = false;
				p.strRemain = k1;
			}
			
			simul();
			
			return;
		}
		
		for(int i = start; i < n; i++) {
			if(!check[i]) {
				check[i] = true;
				re[idx] = i;
				dfs(i + 1, re, idx + 1, check);
				check[i] = false;
			}
		}
	}
	
	static void simul() {
		int st1count = 0;
		int st2count = 0;
		int finishCount = 0;
		
		int t = 0;
		while(true) {
			t++;
			
			for(Person p : list) {
				if(p.finish || !p.inStair)
					continue;
				
				if(--p.strRemain == 0) {
					p.finish = true;
					if(p.target == 1)
						st1count--;
					else 
						st2count--;
					
					finishCount++;
				}
			}

			if(finishCount == n)
				break;
			
			for(Person p : list) {
				if(p.finish || p.inStair)
					continue;
				
				if(p.target == 1) {
					if(p.remain > -1) {
						p.remain--;
					}
					
					if(p.remain == -1 && st1count < 3) {
						p.inStair = true;
						st1count++;
					}
				} else {
					if(p.remain > -1) {
						p.remain--;
					}
					
					if(p.remain == -1 && st2count < 3) {
						p.inStair = true;
						st2count++;
					}
				}
			}
		}
		
		min = Integer.min(min, t);
	}
	
	public static void print(int v, int t) {
		System.out.println("#" + t + " " + v);
	}
	
	public static void print(String v, int t) {
		System.out.println("#" + t + " " + v);
	}
	
	public static int toInt(String s) {
		return Integer.parseInt(s);
	}
}
