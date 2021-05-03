package levenshtein;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LevenshteinTest {
    @Test
    void test_base_case() {
        assertLevenshtein("a", "a", 0);
    }

    /**
     *  distance = 1
     * delete
     * 一个字符的删除
     * 多个字符删除
     *
     * insert
     * 一个
     * 多个
     *
     * substitute
     * 一个
     * 多个
     *
     * 综合
     *
     *
     * "kitten" and "sitting"  3
     *
     *  kitten
     *  sitting
     *
     *
     *
     */
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
    @Disabled
    void test2() {
        assertLevenshtein("a", "abc", 2);
    }

    private void assertLevenshtein(String word1, String word2, int expected) {
        int distance = new Levenshtein().getDistance(word1, word2);
        assertThat(distance).isEqualTo(expected);
    }
}
