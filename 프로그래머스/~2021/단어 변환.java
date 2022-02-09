import java.util.*;
class Solution {
    public int solution(String begin, String target, String[] words) {
        int n = words.length;
        int wLen = words[0].length();
        Queue<String> q = new LinkedList<>();
        Queue<boolean[]> q2 = new LinkedList<>();
        Queue<Integer> q3 = new LinkedList<>();
        
        q.add(begin);
        q2.add(new boolean[n]);
        q3.add(1);
        
        while(!q.isEmpty()){
            String now = q.poll();
            boolean[] check = q2.poll();
            int count = q3.poll();
            
            for(int i = 0; i < n; i++){
                if(check[i])
                    continue;
                
                int diff = 0;
                for(int j = 0; j < wLen; j++){
                    if(words[i].charAt(j) != now.charAt(j))
                        diff++;
                }
                
                if(diff == 1){
                    if(words[i].equals(target)){
                        return count;
                    }
                    
                    q.add(words[i]);
                    boolean[] temp = check.clone();
                    temp[i] = true;
                    q2.add(temp);
                    q3.add(count + 1);
                }
            }
        }
        
        return 0;
    }
}