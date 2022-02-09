import java.util.*;
class Solution {
    static ArrayList<Integer>[] list;
    static int[] dist;
    
    public int solution(int n, int[][] edge) {
        list = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++){
            list[i] = new ArrayList<>();
        }
        
        dist = new int[n + 1];
        Arrays.fill(dist, 99999999);
        
        for(int[] ver : edge){
            int a = ver[0];
            int b = ver[1];
            list[a].add(b);
            list[b].add(a);
        }
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(1);
        dist[1] = 0;
        
        while(!pq.isEmpty()){
            int now = pq.poll();
            
            for(int next : list[now]){
                if(dist[next] > dist[now] + 1){
                    dist[next] = dist[now] + 1;
                    pq.add(next);
                }
            }
        }
        
        int max = -1;
        for(int i = 1; i <= n; i++){
            max = Integer.max(max, dist[i]);
        }
        
        int count = 0;
        for(int i = 1; i <= n; i++){
            if(max == dist[i]) count++;
        }
        
        return count;
    }
}