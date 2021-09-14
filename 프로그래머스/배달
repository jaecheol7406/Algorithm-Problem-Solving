import java.util.*;
class Solution {
    public static final int INF = 99999999;
    int start = 1;
    int[][] map;
    int[] dist;
    ArrayList<Node>[] list;
    class Node {
        int v, w;
        public Node(int v, int w){
            this.v = v;
            this.w = w;
        }
    }
    
    public int solution(int N, int[][] road, int K) {
        dist = new int[N + 1];
        Arrays.fill(dist, INF);
        
        list = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++)
            list[i] = new ArrayList<>();
        map = new int[N + 1][N + 1];
        for(int[] info : road){
            int a = info[0];
            int b = info[1];
            int c = info[2];
            
            if(map[a][b] == 0 || map[a][b] > c){
                map[a][b] = c;
                list[a].add(new Node(b, c));
                list[b].add(new Node(a, c));
            }
        }
        
        dijkstra();
        
        int count = 0;
        for(int i = 1; i <= N; i++){
            if(dist[i] <= K)
                count++;
        }
        return count;
    }
    
    public void dijkstra(){
        dist[start] = 0;
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(start, 0));
        
        while(!q.isEmpty()){
            int now = q.poll().v;
            
            for(Node n : list[now]){
                int next = n.v;
                int val = n.w;
                
                if(dist[next] > dist[now] + val){
                    dist[next] = dist[now] + val;
                    q.add(new Node(next, dist[next]));
                }
            }
        }
    }
}