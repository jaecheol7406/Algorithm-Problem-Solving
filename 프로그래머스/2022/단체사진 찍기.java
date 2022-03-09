import java.util.*;
class Solution {
    HashMap<Character, Integer> hm = new HashMap<>();
    int count;
    int N = 8;
    String[] data;
    
    public int solution(int n, String[] data) {
        this.data = data;
        hm.put('A', 0); hm.put('C', 1); hm.put('F', 2); hm.put('J', 3);
        hm.put('M', 4); hm.put('N', 5); hm.put('R', 6); hm.put('T', 7);
        
        dfs(new Integer[N], 0, new boolean[N], N);
        
        return count;
    }
    
    void dfs(Integer[] re, int idx, boolean[] visit, int N){
        if(idx == N){
            List<Integer> list = Arrays.asList(re);
            boolean flag = true;
            for(String da : data){
                int a = hm.get(da.charAt(0));
                int b = hm.get(da.charAt(2));
                int num = Integer.parseInt(String.valueOf(da.charAt(4)));
                int dif = Math.abs(list.indexOf(a) - list.indexOf(b));
                if(da.charAt(3) == '='){
                    if(dif != num + 1){
                        flag = false;
                        break;
                    }    
                } else if(da.charAt(3) == '>'){
                    if(dif <= num + 1){
                        flag = false;
                        break;   
                    }
                } else {
                    if(dif >= num + 1){
                        flag = false;
                        break;   
                    }
                }
            }
            
            if(flag)
                count++;
            
            return;
        }
        
        for(int i = 0; i < N; i++){
            if(!visit[i]){
                visit[i] = true;
                re[idx] = i;
                dfs(re, idx + 1, visit, N);
                visit[i] = false;
            }
        }
    }
}