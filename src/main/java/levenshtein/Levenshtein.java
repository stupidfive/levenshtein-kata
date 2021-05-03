package levenshtein;

public class Levenshtein {
    public int getDistance(String word1, String word2) {
        if (word1.equals(word2)) {
            return 0;
        }
        if (reachableByOneStep(word1, word2)) {
            return 1;
        }
        return -1;
    }

    private boolean reachableByOneStep(String word1, String word2) {
        return reachableByOneSubstitution(word1, word2)
                || reachableByOneInsertion(word1, word2)
                || reachableByOneDeletion(word1, word2);
    }

    private boolean reachableByOneSubstitution(String word1, String word2) {
        if (word1.length() == word2.length()) {
            int count = 0;
            for (int i = 0; i < word2.length(); i++) {
                if (word1.charAt(i) != word2.charAt(i)) {
                    count++;
                }
            }
            return count == 1;
        }
        return false;
    }

    private boolean reachableByOneInsertion(String word1, String word2) {
        for (int i = 0; i < word2.length(); i++) {
            if (deleteCharAt(word2, i).equals(word1)) {
                return true;
            }
        }
        return false;
    }

    private boolean reachableByOneDeletion(String word1, String word2) {
        for (int i = 0; i < word1.length(); i++) {
            if (deleteCharAt(word1, i).equals(word2)) {
                return true;
            }
        }
        return false;
    }

    private String deleteCharAt(String word, int at) {
        return word.substring(0, at) + word.substring(at + 1);
    }
}
