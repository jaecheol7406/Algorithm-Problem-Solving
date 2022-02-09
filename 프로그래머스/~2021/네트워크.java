class Solution {
    int n;
    int[][] coms;
    boolean[] check;
    int count;
    
    public int solution(int n, int[][] computers) {
        this.n = n;
        this.coms = computers;
        check = new boolean[n];
        
        for(int i = 0; i < n; i++){
            if(!check[i]){
                count++;
                check[i] = true;
                dfs(i);   
            }
        }
        
        return count;
    }
    
    public void dfs(int now){
        for(int i = 0; i < n; i++){
            if(i != now && !check[i] && coms[now][i] == 1){
                check[i] = true;
                dfs(i);
            }
        }
    }
}