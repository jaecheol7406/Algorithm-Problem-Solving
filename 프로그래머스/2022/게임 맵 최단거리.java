import java.util.*;
class Solution {
    public int solution(int[][] maps) {
        int[] dx = {0,0,1,-1};
        int[] dy = {1,-1,0,0};
        int n = maps.length;
        int m = maps[0].length;
        boolean[][] visit = new boolean[n][m]; 
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0,0,1});
        int min = n * m;
        while(!q.isEmpty()){
            int[] node = q.poll();
            int x = node[0];
            int y = node[1];
            int count = node[2];
            
            for(int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if(!(0 <= nx && nx < n && 0 <= ny && ny < m) || visit[nx][ny])
                    continue;
                if(maps[nx][ny] == 0)
                    continue;
                
                if(nx == n - 1 && ny == m - 1){
                    return count + 1;
                }
                
                visit[nx][ny] = true;
                q.add(new int[] {nx, ny, count + 1});
            }
        }
        
        return -1;
    }
}