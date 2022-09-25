class Solution {
    int[][] map;
    int n;
    boolean[] visit;
    int re;
    public int solution(int n, int[][] computers) {
        this.n = n;
        this.map = computers;
        visit = new boolean[n];
        
        for(int i = 0; i < n; i++){
            if(!visit[i]){
                visit[i] = true;
                dfs(i);
                re++;
            }
        }
        return re;
    }
    
    void dfs(int i){
        for(int j = 0; j < n; j++){
            if(!visit[j] && map[i][j] == 1){
                visit[j] = true;
                dfs(j);
            }
        }
    }
}