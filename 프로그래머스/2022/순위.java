class Solution {
    public int solution(int n, int[][] results) {
        int[][] map = new int[n][n];
        for(int[] re : results){
            int a = re[0] - 1;
            int b = re[1] - 1;
            map[a][b] = 1;
        }
        for(int k = 0; k < n; k++){
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    if(map[i][j] == 0 && map[i][k] == 1 && map[k][j] == 1){
                        map[i][j] = 1;
                    }
                }
            }
        }
        
        int re = 0;
        for(int v = 0; v < n; v++){
            boolean[] visit = new boolean[n];
            int count = 1;
            for(int j = 0; j < n; j++){
                if(j == v)
                    continue;
                
                if(map[v][j] == 1 && !visit[j]){
                    visit[j] = true;
                    count++;
                }
            }
            for(int i = 0; i < n; i++){
                if(i == v)
                    continue;
                
                if(map[i][v] == 1 && !visit[i]){
                    visit[i] = true;
                    count++;
                }
            }
            
            if(count == n){
                re++;
            }
        }
        
        return re;
    }
}