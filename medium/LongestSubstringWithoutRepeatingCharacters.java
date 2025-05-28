package medium;

class Solution {
    public int lengthOfLongestSubstring(String s) {
        // Use array for constant time lookups (ASCII characters)
        int[] charIndex = new int[128];
        // Initialize with -1 to indicate character hasn't been seen
        java.util.Arrays.fill(charIndex, -1);
        
        int maxLength = 0;
        int left = 0;
        
        // Sliding window approach - expand right pointer
        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);
            
            // If character was seen and is within current window
            if (charIndex[currentChar] >= left) {
                // Move left pointer past the duplicate
                left = charIndex[currentChar] + 1;
            }
            
            // Update character's last seen position
            charIndex[currentChar] = right;
            
            // Update max length
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        return maxLength;
    }
}