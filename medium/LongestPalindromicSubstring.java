package medium;

class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }
        
        int start = 0;
        int maxLen = 0;
        
        // Check each possible center of palindrome
        for (int i = 0; i < s.length(); i++) {
            // Odd length palindromes (single center)
            int len1 = expandAroundCenter(s, i, i);
            // Even length palindromes (two centers)
            int len2 = expandAroundCenter(s, i, i + 1);
            
            int len = Math.max(len1, len2);
            
            // Update if we found a longer palindrome
            if (len > maxLen) {
                maxLen = len;
                // Calculate starting position
                start = i - (len - 1) / 2;
            }
        }
        
        return s.substring(start, start + maxLen);
    }
    
    private int expandAroundCenter(String s, int left, int right) {
        // Expand while characters match
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        // Return length of palindrome
        return right - left - 1;
    }
}