package levenshtein;

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
    }

    @Test
    void test_one_character_insertion() {
        assertLevenshtein("a", "ab", 1);
        assertLevenshtein("a", "ac", 1);
        assertLevenshtein("abc", "abcd", 1);
        assertLevenshtein("abd", "abcd", 1);
    }

    @Test
    void test_one_character_substitution() {
        assertLevenshtein("a", "b", 1);
        assertLevenshtein("ab", "ac", 1);
        assertLevenshtein("abc", "adc", 1);
        assertLevenshtein("ebcddd", "abcddd", 1);
    }

    @Test
    void test_recursive_substitution() {
        assertLevenshtein("ab", "cd", 2);
        assertLevenshtein("abc", "def", 3);
        assertLevenshtein("abc", "aef", 2);
    }

    @Test
    void test_recursive_deletion() {
        assertLevenshtein("ab", "", 2);
        assertLevenshtein("abc", "", 3);
        assertLevenshtein("abc", "b", 2);
    }

    @Test
    void test_recursive_insertion() {
        assertLevenshtein("", "ab", 2);
        assertLevenshtein("", "abc", 3);
        assertLevenshtein("b", "abc", 2);
    }

    @Test
    void test_kitten() {
        assertLevenshtein("kitten", "sitting", 3);
    }

    private void assertLevenshtein(String word1, String word2, int expected) {
        int distance = new Levenshtein().getDistance(word1, word2);
        assertThat(distance).isEqualTo(expected);
    }
}
