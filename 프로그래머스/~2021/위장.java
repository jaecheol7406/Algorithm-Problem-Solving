import java.util.*;
class Solution {
    HashMap<String, Integer> map = new HashMap<>();
    public int solution(String[][] clothes) {
        for(String[] cl : clothes){
            String k = cl[1];
            String v = cl[0];
            
            map.put(k, map.getOrDefault(k, 0) + 1);
        }
        
        int mul = 1;
        for(String k : map.keySet()){
            int v = map.get(k);
            mul *= (v + 1);
        }
        
        return mul - 1;
    }
}