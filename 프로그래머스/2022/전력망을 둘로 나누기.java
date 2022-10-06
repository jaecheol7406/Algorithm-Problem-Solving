import java.util.*;
class Solution {
    int[][] map;
    boolean[] visit;
    int min = Integer.MAX_VALUE;
    int n;
    public int solution(int n, int[][] wires) {
        map = new int[n + 1][n + 1];
        this.n = n;
        for(int i = 0; i < n - 1; i++){
            int[] w = wires[i];
            int a = w[0];
            int b = w[1];
            map[a][b] = 1;
            map[b][a] = 1;
        }
        
        for(int k = 0; k < n - 1; k++){
            int a = wires[k][0];
            int b = wires[k][1];
            map[a][b] = 0;
            map[b][a] = 0;
            
            visit = new boolean[n + 1];
            boolean flag = false;
            int count1 = 0, count2 = 0;
            for(int i = 1; i <= n; i++){
                if(!visit[i]){
                    visit[i] = true;
                    if(!flag){
                        count1 = dfs(i);    
                    } else {
                        count2 = dfs(i);
                    }
                    flag = true;
                }
            }
            
            int dif = Math.abs(count1 - count2);
            if(min > dif){
                min = dif;
            }
            
            map[a][b] = 1;
            map[b][a] = 1;
        }
        return min;
    }
    
    int dfs(int i){
        int count = 1;
        for(int j = 1; j <= n; j++){
            if(!visit[j] && map[i][j] == 1){
                visit[j] = true;
                count += dfs(j);
            }
        }
        return count;
    }
}