class Solution {
    int[][] dun;
    int n;
    int max = -1;
    
    public int solution(int k, int[][] dungeons) {
        dun = dungeons;
        n = dun.length;
        dfs(0, new boolean[n], k);
        return max;
    }
    
    void dfs(int idx, boolean[] check, int k){
        if(max < idx)
            max = idx;
        
        if(idx == n){
            return;
        }
        
        for(int i = 0; i < n; i++){
            if(!check[i]){
                if(dun[i][0] > k){
                    continue;
                }
                
                check[i] = true;
                dfs(idx + 1, check, k - dun[i][1]);
                check[i] = false;
            }
        }
    }
}