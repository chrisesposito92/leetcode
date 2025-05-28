package medium;

class Solution {
    public String convert(String s, int numRows) {
        // Edge cases
        if (numRows == 1 || numRows >= s.length()) {
            return s;
        }
        
        StringBuilder result = new StringBuilder();
        int cycleLen = 2 * numRows - 2;
        int n = s.length();
        
        // Process each row
        for (int row = 0; row < numRows; row++) {
            for (int j = 0; j + row < n; j += cycleLen) {
                // Add character in current column
                result.append(s.charAt(j + row));
                
                // Add character in diagonal (except for first and last row)
                if (row != 0 && row != numRows - 1 && j + cycleLen - row < n) {
                    result.append(s.charAt(j + cycleLen - row));
                }
            }
        }
        
        return result.toString();
    }
}
