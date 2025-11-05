package wang.xpro.template;

import wang.xpro.base.ListNode;

/**
 * 链表节点模板
 *
 * @author b1tzer
 */
public class ListNodeTemplate {
    /**
     * 创建链表节点
     *
     * @param size 链表节点数量
     * @return 链表头节点
     */
    public static ListNode createListNode(int size) {
        if (size <= 0) {
            return null;
        }
        ListNode head = new ListNode(-1);
        ListNode cur = head;
        for (int i = 1; i < size + 1; i++) {
            cur.next = new ListNode(i);
            cur = cur.next;
        }
        return head.next;
    }
}
