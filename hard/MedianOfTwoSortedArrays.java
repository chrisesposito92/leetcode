package hard;

class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // Ensure nums1 is the smaller array to optimize binary search
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }
        
        int m = nums1.length;
        int n = nums2.length;
        int totalLeft = (m + n + 1) / 2;
        
        // Binary search on the smaller array
        int left = 0;
        int right = m;
        
        while (left < right) {
            int i = left + (right - left) / 2;
            int j = totalLeft - i;
            
            // nums1[i] should be >= nums2[j-1] for valid partition
            if (nums1[i] < nums2[j - 1]) {
                left = i + 1;
            } else {
                right = i;
            }
        }
        
        int i = left;
        int j = totalLeft - i;
        
        // Find max of left partition
        int nums1LeftMax = i == 0 ? Integer.MIN_VALUE : nums1[i - 1];
        int nums2LeftMax = j == 0 ? Integer.MIN_VALUE : nums2[j - 1];
        int leftMax = Math.max(nums1LeftMax, nums2LeftMax);
        
        // If odd total length, median is max of left partition
        if ((m + n) % 2 == 1) {
            return leftMax;
        }
        
        // Find min of right partition for even length
        int nums1RightMin = i == m ? Integer.MAX_VALUE : nums1[i];
        int nums2RightMin = j == n ? Integer.MAX_VALUE : nums2[j];
        int rightMin = Math.min(nums1RightMin, nums2RightMin);
        
        // Return average of middle two elements
        return (leftMax + rightMin) / 2.0;
    }
}
