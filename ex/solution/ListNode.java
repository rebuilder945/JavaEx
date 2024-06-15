package solution;

import java.util.StringJoiner;

public class ListNode extends Object {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    
    public String toString() {
        ListNode p = this;
        StringJoiner sj = new StringJoiner("->");
        while (p != null) {
            sj.add(String.valueOf(p.val));
            p = p.next;
        }
        return sj.toString();
    }
}