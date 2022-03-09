class Solution {
    
    public int[] solution(int rows, int columns, int[][] queries) {
        int N = rows; int M = columns;
        int[][] map = new int[N][M];
        int num = 1;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                map[i][j] = num++;
            }
        }    
        
        int[] re = new int[queries.length];
        int idx = 0;
        
        for(int[] qu : queries){
            int x1 = qu[0]-1; int y1 = qu[1]-1; int x2 = qu[2]-1; int y2 = qu[3]-1;
            
            int min = Integer.MAX_VALUE;
            
            int nextPut = map[x1][y1];
            min = Integer.min(min, nextPut);
            for(int y = y1 + 1; y <= y2; y++){
                int temp = map[x1][y];
                map[x1][y] = nextPut;
                min = Integer.min(min, nextPut);
                nextPut = temp;
            }
            
            for(int x = x1 + 1; x <= x2; x++){
                int temp = map[x][y2];
                map[x][y2] = nextPut;
                min = Integer.min(min, nextPut);
                nextPut = temp;
            }
            
            for(int y = y2 - 1; y >= y1; y--){
                int temp = map[x2][y];
                map[x2][y] = nextPut;
                min = Integer.min(min, nextPut);
                nextPut = temp;
            }
            
            for(int x = x2 - 1; x >= x1; x--){
                int temp = map[x][y1];
                map[x][y1] = nextPut;
                min = Integer.min(min, nextPut);
                nextPut = temp;
            }
            
            re[idx++] = min;
        }
        
        return re;
    }
}