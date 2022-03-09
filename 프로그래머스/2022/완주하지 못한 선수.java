import java.util.*;
class Solution {
    public String solution(String[] participant, String[] completion) {
        // 정렬
//         Arrays.sort(participant);
//         Arrays.sort(completion);
        
//         for(int i = 0; i < completion.length; i++){
//             if(!completion[i].equals(participant[i])){
//                 return participant[i];
//             }
//         }
        
//         return participant[participant.length - 1];
        
        
        
        // 해쉬맵
        HashMap<String, Integer> hm = new HashMap<>();
        for(String s : completion){
            hm.put(s, hm.getOrDefault(s, 0) + 1);
        }
        
        for(String s : participant){
            Integer count = hm.get(s);
            if(count == null){
                return s;
            }
            
            if(--count == 0){
                hm.remove(s);
            } else {
                hm.put(s, count);
            }
        }
        
        return null;
    }
}