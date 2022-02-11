import java.util.*;

class Solution {
    int N;
    HashMap<String, Integer> playPerGenre = new HashMap<>();
    HashMap<String, PriorityQueue<Song>> songsOfGenre = new HashMap<>();
    class Song implements Comparable<Song>{
        int idx;
        int play;
        public Song(int idx, int play){
            this.idx = idx;
            this.play = play;
        }
        public int compareTo(Song s){
            return s.play - this.play;
        }
    }
    class Node implements Comparable<Node>{
        String gen;
        int play;
        public Node(String gen, int play){
            this.gen = gen;
            this.play = play;
        }
        public int compareTo(Node n){
            return n.play - this.play;
        }
    }
    public int[] solution(String[] genres, int[] plays) {
        N = genres.length;
        for(int i = 0; i < N; i++){
            String gen = genres[i];
            int play = plays[i];
            
            playPerGenre.put(gen, playPerGenre.getOrDefault(gen, 0) + play);
            
            PriorityQueue<Song> songs = songsOfGenre.get(gen);
            if(songs == null){
                songs = new PriorityQueue<>();
            }
            
            songs.add(new Song(i, play));
            songsOfGenre.put(gen, songs);
        }
        
        List<Node> list = new ArrayList<>();
        for(String gen : playPerGenre.keySet()){
            int count = playPerGenre.get(gen);
            list.add(new Node(gen, count));
        }
        Collections.sort(list);
        
        List<Integer> resultList = new ArrayList<>();
        for(Node n : list){
            String gen = n.gen;
            PriorityQueue<Song> songs = songsOfGenre.get(gen);
            int count = 0;
            while(!songs.isEmpty()){
                Song song = songs.poll();
                resultList.add(song.idx);
                
                if(++count == 2)
                    break;
            }
        }
        
        int[] resultArray = new int[resultList.size()];
        for(int i = 0; i < resultArray.length; i++){
            resultArray[i] = resultList.get(i);
        }
        
        return resultArray;
    }
}