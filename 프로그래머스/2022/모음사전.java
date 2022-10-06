import java.util.*;
class Solution {
    String dic = "AEIOU";
    ArrayList<String> list = new ArrayList<>();
    public int solution(String word) {
        for(int r = 1; r <= 5; r++){
            dfs(0, r, "");    
        }
        
        Collections.sort(list);
        
        int idx = 1;
        for(String w : list){
            if(w.equals(word)){
                return idx;
            }
            idx++;
        }
        
        return -1;
    }
    
    void dfs(int idx, int r, String word){
        if(idx == r){
            list.add(word);
            return;
        }
        
        for(int i = 0; i < dic.length(); i++){
            dfs(idx + 1, r, word + dic.charAt(i));
        }
    }
}