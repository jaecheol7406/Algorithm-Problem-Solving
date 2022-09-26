import java.util.*;
class Solution {
    char[][] map;
    public int[] solution(String[][] places) {
        int n = 5;
        int[] re = new int[n];
        for(int k = 0; k < n; k++){
            String[] place = places[k];
            ArrayList<int[]> list = new ArrayList<>();
            map = new char[n][n];
            for(int i = 0; i < n; i++){
                String s = place[i];
                for(int j = 0; j < n; j++){
                    char c = s.charAt(j);
                    if(c == 'P'){
                        list.add(new int[]{i, j});
                    }
                    map[i][j] = c;
                }
            }
            
            boolean flag = true;
            for(int i = 0; i < list.size() - 1; i++){
                int[] p1 = list.get(i);
                for(int j = i + 1; j < list.size(); j++){
                    int[] p2 = list.get(j);
                    if(dist(p1, p2) > 2){
                        continue;
                    }
                    
                    if(dist(p1, p2) == 1){
                        flag = false;
                        break;
                    }
                    
                    if(!check(p1, p2)){
                        flag = false;
                        break;
                    }
                }
                
                if(!flag){
                    break;
                }
            }
            
            if(flag){
                re[k] = 1;   
            }
        }
        return re;
    }
    
    boolean check(int[] p1, int[] p2){
        if(p1[0] == p2[0]){
            int x = p1[0];
            int y = p1[1] + 1;
            if(map[x][y] == 'O')
                return false;
        } else if(p1[1] == p2[1]){
            int x = p1[0] + 1;
            int y = p1[1];
            if(map[x][y] == 'O')
                return false;
        } else if(p1[1] > p2[1]){
            if(map[p1[0]][p1[1] - 1] == 'O' || map[p2[0]][p2[1] + 1] == 'O')
                return false;
        } else {
            if(map[p1[0]][p1[1] + 1] == 'O' || map[p2[0]][p2[1] - 1] == 'O')
                return false;
        }
        return true;
    }
    
    int dist(int[] p1, int[] p2){
        return Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
    }
}