class Solution {
    int n;
    int[][] computers;
    boolean[] visit;
    public int solution(int n, int[][] computers) {
        this.n = n;
        this.computers = computers;
        visit = new boolean[n];
        int count = 0;
        for(int i = 0; i < n; i++){
            if(!visit[i]){
                dfs(i);
                count++;
            }
        }
        return count;
    }
    
    void dfs(int v){
        visit[v] = true;
        for(int i = 0; i < n; i++){
            int ajd = computers[v][i];
            if(ajd == 1 && !visit[i]){
                dfs(i);
            }
        }
    }
}