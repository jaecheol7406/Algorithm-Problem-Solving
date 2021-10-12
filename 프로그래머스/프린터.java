import java.util.*;
class Solution {
    class Node {
        int v, idx;
        public Node(int v, int idx){
            this.v = v;
            this.idx = idx;
        }
    }
    public int solution(int[] priorities, int location) {
        int printed = 0;
        Queue<Node> q = new LinkedList<>();
        for(int i = 0; i < priorities.length; i++){
            int p = priorities[i];
            q.add(new Node(p, i));
        }
        while(true){
            Node n = q.poll();
            boolean can = true;
            
            for(int i = 0; i < q.size(); i++){
                Node temp = q.poll();
                if(temp.v > n.v){
                    can = false;
                }
                q.add(temp);
            }
            
            if(can){
                if(n.idx == location)
                    break;
                printed++;
            } else {
                q.add(n);
            }
        }
        
        return printed + 1;
    }
}