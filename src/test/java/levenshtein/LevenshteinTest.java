package levenshtein;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LevenshteinTest {
    @Test
    void test_base_case() {
        assertLevenshtein("a", "a", 0);
    }

    @Test
    void test_one_character_deletion() {
        assertLevenshtein("ab", "a", 1);
        assertLevenshtein("ab", "b", 1);
        assertLevenshtein("abc", "ac", 1);
        assertLevenshtein("12345678", "1235678", 1);
//        assertLevenshtein("a", "b", 1);
//        assertLevenshtein("a", "ab", 1);
    }

    @Test
    @Disabled
    void test2() {
        assertLevenshtein("a", "abc", 2);
    }

    private void assertLevenshtein(String word1, String word2, int expected) {
        int distance = new Levenshtein().getDistance(word1, word2);
        assertThat(distance).isEqualTo(expected);
    }
}
