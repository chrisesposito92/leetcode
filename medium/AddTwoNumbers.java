package medium;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // Use dummy node to simplify edge cases - we'll return dummy.next
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        int carry = 0;
        
        // Continue while we have digits in either list OR a carry value
        while (l1 != null || l2 != null || carry != 0) {
            // Start with carry from previous addition
            int sum = carry;
            
            // Add digit from first list if available
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            
            // Add digit from second list if available
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            
            // Calculate new carry (1 if sum >= 10, 0 otherwise)
            carry = sum / 10;
            // Create new node with single digit (sum % 10)
            current.next = new ListNode(sum % 10);
            current = current.next;
        }
        
        // Return the actual result, skipping dummy node
        return dummy.next;
    }
}