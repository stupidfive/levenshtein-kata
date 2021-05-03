package levenshtein;

public class Levenshtein {
    public int getDistance(String word1, String word2) {
        if (word1.equals(word2)) {
            return 0;
        }

        for (int i = 0; i < word1.length(); i++) {
            if (deleteCharAt(word1, i).equals(word2)) {
                return 1;
            }
        }

        return -1;
    }

    private String deleteCharAt(String word, int at) {
        return word.substring(0, at) + word.substring(at + 1);
    }
}
