class Solution {
    public int[] solution(int brown, int yellow) {
        int size = brown + yellow;
        for(int x = 2; x <= size / 2; x++){
            int y = size / x;
            if((x-2) * (y-2) == yellow){
                return new int[] {y, x};
            }
        }
        return null;
    }
}