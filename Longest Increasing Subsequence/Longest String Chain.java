/*
 1048. Longest String Chain
Medium
6K
218
Companies
You are given an array of words where each word consists of lowercase English letters.

wordA is a predecessor of wordB if and only if we can insert exactly one letter anywhere in wordA without changing the order of the other characters to make it equal to wordB.

For example, "abc" is a predecessor of "abac", while "cba" is not a predecessor of "bcad".
A word chain is a sequence of words [word1, word2, ..., wordk] with k >= 1, where word1 is a predecessor of word2, word2 is a predecessor of word3, and so on. A single word is trivially a word chain with k == 1.

Return the length of the longest possible word chain with words chosen from the given list of words.

 

Example 1:

Input: words = ["a","b","ba","bca","bda","bdca"]
Output: 4
Explanation: One of the longest word chains is ["a","ba","bda","bdca"].
Example 2:

Input: words = ["xbc","pcxbcf","xb","cxbc","pcxbc"]
Output: 5
Explanation: All the words can be put in a word chain ["xb", "xbc", "cxbc", "pcxbc", "pcxbcf"].
Example 3:

Input: words = ["abcd","dbqca"]
Output: 1
Explanation: The trivial word chain ["abcd"] is one of the longest word chains.
["abcd","dbqca"] is not a valid word chain because the ordering of the letters is changed.
 

Constraints:

1 <= words.length <= 1000
1 <= words[i].length <= 16
words[i] only consists of lowercase English letters.

 */

class Solution {
    public int longestStrChain(String[] words) {
        
        List<String> words1 = Arrays.asList(words);
        return longestStrChain(words1);
    }

    public static int longestStrChain(List<String> arr) {

        int n = arr.size();

        Collections.sort(arr, new Comparator<String>() {
            public int compare(String s1, String s2) {
                return s1.length() - s2.length();
            }
        });

        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        int maxi = 1;

        for (int i = 0; i <= n - 1; i++) {

            for (int prev_index = 0; prev_index <= i - 1; prev_index++) {

                if (compare(arr.get(i), arr.get(prev_index)) && 1 + dp[prev_index] > dp[i]) {
                    dp[i] = 1 + dp[prev_index];
                }
            }

            if (dp[i] > maxi)
                maxi = dp[i];
        }
        return maxi;
    }
    public static boolean compare(String s1, String s2) {
        if (s1.length() != s2.length() + 1)
            return false;

        int first = 0;
        int second = 0;

        while (first < s1.length()) {
            if (second < s2.length() && s1.charAt(first) == s2.charAt(second)) {
                first++;
                second++;
            } else {
                first++;
            }
        }
        if (first == s1.length() && second == s2.length())
            return true;
        else
            return false;
    }
}
