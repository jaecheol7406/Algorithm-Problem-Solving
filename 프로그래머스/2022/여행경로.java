import java.util.*;

// visitd을 전역으로 넣음
class Solution {
    int n;
    boolean[] visit;
    String[][] tickets;
    PriorityQueue<String> pq = new PriorityQueue<>();
    
    public String[] solution(String[][] tickets) {
        this.tickets = tickets;
        n = tickets.length;
        visit = new boolean[n];
                
        for(int i = 0; i < n; i++){
            String from = tickets[i][0];
            String to = tickets[i][1];
            
            if(from.equals("ICN")){
                visit[i] = true;
                dfs(to, "ICN" + to, 1);
                visit[i] = false;
            }
        }
        
        String reStr = pq.poll();
        String[] re = new String[reStr.length() / 3];
        for(int i = 0; i <= reStr.length() - 3; i += 3){
            re[i / 3] = reStr.substring(i, i + 3);
        }
        return re;
    }
    
    void dfs(String city, String path, int count){
        if(count == n){
            pq.add(path);
            return;
        }
        
        for(int i = 0; i < n; i++){
            if(visit[i])
                continue;
            
            String from = tickets[i][0];
            String to = tickets[i][1];
            
            if(from.equals(city)){
                visit[i] = true;
                dfs(to, path + to, count + 1);
                visit[i] = false;
            }
        }
    }
}


// visit을 매개변수로 넣음
import java.util.*;
class Solution2 {
    int N;
    String[][] tis;
    PriorityQueue<String> pq = new PriorityQueue<>();
    public String[] solution(String[][] tickets) {
        N = tickets.length;
        tis = tickets;
        
        for(int i = 0; i < N; i++){
            if(!tis[i][0].equals("ICN"))
                continue;
            
            boolean[] visit = new boolean[N];
            visit[i] = true;
            dfs(1, tis[i][1], tis[i][0] + tis[i][1], visit);
        }
        
        String s = pq.poll();
        String[] re = new String[s.length() / 3];
        int idx = 0;
        for(int i = 0; i < s.length(); i += 3){
            re[idx++] = s.substring(i, i + 3);
        }
        return re;
    }
    
    void dfs(int count, String std, String re, boolean[] visit){
        if(count == N){
            pq.add(re);
            return;
        }
        
        for(int i = 0; i < N; i++){
            if(visit[i])
                continue;
            
            if(std.equals(tis[i][0])){
                visit[i] = true;
                dfs(count + 1, tis[i][1], re + tis[i][1], visit);
                visit[i] = false;
            }
        }
    }
}