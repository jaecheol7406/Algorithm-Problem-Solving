import java.util.*;
class Solution {
    int INF = 9999999;
    int[] dist;
    ArrayList<Integer>[] list;
    
    public int solution(int n, int[][] edge) {
        dist = new int[n + 1];
        list = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++){
            dist[i] = INF;
            list[i] = new ArrayList<>();
        }
        for(int i = 0; i < edge.length; i++){
            int a = edge[i][0];
            int b = edge[i][1];
            list[a].add(b);
            list[b].add(a);
        }
        di();
        
        int max = 1;
        for(int i = 1; i <= n; i++){
            max = Integer.max(max, dist[i]);
        }
        int count = 0;
        for(int i = 1; i <= n; i++){
            if(max == dist[i])
                count++;
        }
        return count;
    }
    
    public void di(){
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        dist[1] = 0;
        
        while(!q.isEmpty()){
            int cur = q.poll();
            
            for(int next : list[cur]){
                if(dist[next] > dist[cur] + 1 && dist[next] == INF){
                    dist[next] = dist[cur] + 1;
                    q.add(next);
                }
            }
        }
    }
}