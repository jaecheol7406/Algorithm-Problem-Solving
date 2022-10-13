package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
	public int solution(int[][] sizes) {
		int leftMax = -1;
		int rightMax = -1;
		for(int i = 0; i < sizes.length; i++) {
			int left = sizes[i][0];
			int right = sizes[i][1];
			
			if(left < right) {
				sizes[i][0] = right;
				sizes[i][1] = left;
			}
		}
		
		for(int i = 0; i < sizes.length; i++) {
			leftMax = Integer.max(leftMax, sizes[i][0]);
			rightMax = Integer.max(rightMax, sizes[i][1]);
		}
		
		return leftMax * rightMax;
	}
	
	int[][] co(int[][] s){
		int[][] temp = new int[s.length][s[0].length];
		for(int i = 0; i < temp.length; i++) {
			temp[i] = s[i].clone();
		}
		return temp;
	}
	
	public static void main(String[] args) throws Exception {
		Solution so = new Solution();
		int[][] s = new int[][] {{14,4},{19,6},{6,16},{18,7},{7,11}};
		System.out.println(so.solution(s));
	}
}
