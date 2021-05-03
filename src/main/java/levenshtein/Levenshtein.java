package levenshtein;

public class Levenshtein {

    public static final int INFINITE = Integer.MAX_VALUE - 100;

    public int getDistance(String word1, String word2) {
        if (word1.equals(word2)) {
            return 0;
        }

        if (reachableByOneStep(word1, word2)) {
            return 1;
        }

        int differentSince = getDifferentSince(word1, word2);
        String subWord1 = word1.substring(differentSince);
        String subWord2 = word2.substring(differentSince);
        int subDistanceBySubstitution = getSubDistanceBySubstitution(subWord1, subWord2);
        int subDistanceByDeletion = getSubDistanceByDeletion(subWord1, subWord2);
        int subDistanceByInsertion = getSubDistanceByInsertion(subWord1, subWord2);
        return getMin(subDistanceBySubstitution, subDistanceByDeletion, subDistanceByInsertion) + 1;
    }

    private int getMin(int subDistanceBySubstitution, int subDistanceByDeletion, int subDistanceByInsertion) {
        int min = Math.min(subDistanceBySubstitution, subDistanceByDeletion);
        return Math.min(min, subDistanceByInsertion);
    }

    private int getSubDistanceByInsertion(String subWord1, String subWord2) {
        if (subWord2.length() == 0) {
            return INFINITE;
        }
        return getDistance(
                subWord1,
                subWord2.substring(1)
        );
    }

    private int getSubDistanceByDeletion(String subWord1, String subWord2) {
        if (subWord1.length() == 0) {
            return INFINITE;
        }
        return getDistance(
                subWord1.substring(1),
                subWord2
        );
    }

    private int getSubDistanceBySubstitution(String subWord1, String subWord2) {
        if (subWord1.length() == 0) {
            return INFINITE;
        }
        if (subWord2.length() == 0) {
            return INFINITE;
        }
        return getDistance(
                subWord1.substring(1),
                subWord2.substring(1)
        );
    }

    private int getDifferentSince(String word1, String word2) {
        int minLength = Math.min(word1.length(), word2.length());
        for (var i = 0; i < minLength; i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                return i;
            }
        }
        return minLength;
    }

    private boolean reachableByOneStep(String word1, String word2) {
        return reachableByOneInsertion(word1, word2)
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
