package wang.xpro.base;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 链表节点
 *
 * @author b1tzer
 */
@NoArgsConstructor
@AllArgsConstructor
public class ListNode {
    public int val;

    public ListNode next;

    /**
     * 构造方法
     *
     * @param val 节点值
     */
    public ListNode(int val) {
        this.val = val;
    }
}