import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        Map<String, Integer> genrePlayCnt = new HashMap<>(); 
        Map<String, List<int[]>> genreSongs = new HashMap<>();
        
        for(int i = 0; i < genres.length; i++) {
            String genre = genres[i];
            int play = plays[i];
            
            genrePlayCnt.put(genre, genrePlayCnt.getOrDefault(genre, 0) + play);
            
            genreSongs.putIfAbsent(genre, new ArrayList<>());
            genreSongs.get(genre).add(new int[]{i, play});
        }
        
        List<String> sortedGenres = new ArrayList<>(genrePlayCnt.keySet());
        sortedGenres.sort((a,b) -> genrePlayCnt.get(b) - genrePlayCnt.get(a));
        
        List<Integer> result = new ArrayList<>();
        
        for(String genre : sortedGenres) {
            List<int[]> songs = genreSongs.get(genre);
            songs.sort((a,b) -> b[1] == a[1] ? a[0] - b[0] : b[1] - a[1]);
            
            for(int i = 0; i < songs.size() && i < 2; i++) {
                result.add(songs.get(i)[0]);
            }
        }
        
        return result.stream().mapToInt(i -> i).toArray();
        
    }
}