import java.util.*;
class Solution {
    HashMap<String, PriorityQueue<Node>> map = new HashMap<>();
    HashMap<String, Integer> map2 = new HashMap<>();
    class Node implements Comparable<Node> {
        int idx, n;
        public Node(int idx, int n){
            this.idx = idx;
            this.n = n;
        }
        public int compareTo(Node node){
            return node.n - n;
        }
    }
    
    public int[] solution(String[] genres, int[] plays) {
        for(int i = 0; i < genres.length; i++){
            String gen = genres[i];
            int pl = plays[i];
            
            PriorityQueue<Node> pq = map.get(gen);
            if(pq == null){
                pq = new PriorityQueue<>();
            }
            pq.add(new Node(i, pl));
            map.put(gen, pq);
            
            map2.put(gen, map2.getOrDefault(gen, 0) + pl);
        }
        
        List<Map.Entry<String, Integer>> list = new LinkedList<>(map2.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>(){
            public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b){
                return b.getValue() - a.getValue();
            }
        });
        
        List<Integer> re = new ArrayList<>();
        for(Map.Entry<String, Integer> en : list){
            String gen = en.getKey();
            PriorityQueue<Node> pq = map.get(gen);
            Node first = pq.poll();
            re.add(first.idx);
            
            if(pq.isEmpty())
                continue;
            
            Node second = pq.poll();
            re.add(second.idx);
        }
        
        int[] reArr = new int[re.size()];
        int arrIdx = 0;
        for(int idx : re){
            reArr[arrIdx++] = idx;
        }
        
        return reArr;
    }
}