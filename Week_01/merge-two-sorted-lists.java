class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        ListNode dummy = new ListNode();
        ListNode t3 = dummy;
        while(l1!=null && l2!=null) {
            if(l1.val < l2.val) {
                t3.next = l1;
                l1 = l1.next;
            }
            else {
                t3.next = l2;
                l2 = l2.next;
            }
            t3 = t3.next;
        }
        t3.next = l1==null?l2:l1;
        return dummy.next;
    }
}