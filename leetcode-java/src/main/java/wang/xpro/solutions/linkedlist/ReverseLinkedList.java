package wang.xpro.solutions.linkedlist;

import wang.xpro.base.ListNode;
import wang.xpro.utils.PrintUtils;

import static wang.xpro.template.ListNodeTemplate.createListNode;

/**
 * <h5><a href="https://leetcode.cn/problems/reverse-linked-list-ii/description/">反转链表 II</a></h5>
 * <p>
 * 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
 * </p>
 * <p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2021/02/19/rev2ex2.jpg" style="width: 542px; height: 222px;">
 * </p>
 * 示例 1： <p>
 * 输入：head = [1,2,3,4,5], left = 2, right = 4
 * <p>
 * 输出：[1,4,3,2,5]
 *
 * <p>
 * 示例 2：
 * <p>
 * 输入：head = [5], left = 1, right = 1
 * <p>
 * 输出：[5]
 *
 * @author b1tzer
 */
public class ReverseLinkedList {
    public static void main(String[] args) {
        ListNode node = createListNode(5);
        PrintUtils.printListNode(node);

        ListNode reversedNode = reverseBetween(node, 2, 5);
        PrintUtils.printListNode(reversedNode);
    }

    public static ListNode reverseBetween(ListNode head, int left, int right) {
        // 输入校验：空链表或无须反转的情况
        if (head == null || left == right) return head;

        ListNode dummy = new ListNode(0, head);

        // 1. 定位到left的前一个节点
        ListNode preReverse = dummy;
        for (int i = 1; i < left; i++) {
            preReverse = preReverse.next;
        }

        // 2. 开始反转
        ListNode reverseStart = preReverse.next;
        ListNode current = reverseStart.next;

        // 反转区间内的节点
        for (int i = left; i < right; i++) {
            // 将current节点移动到反转区间的最前面
            // 首先保存current的下一个节点，用于调整完顺序后更新current指向下一个节点
            reverseStart.next = current.next;

            // 然后将current节点插入到开始循环前一个节点的后面，相当于把当前节点放到反转区域的最前面
            current.next = preReverse.next;

            // 最后将preReverse的next指向current，完成插入操作
            preReverse.next = current;

            // 更新current指向下一个节点，继续反转操作
            current = reverseStart.next;
        }

        return dummy.next;
    }

    // 递归法

    /**
     * 后驱节点
     */
    private static ListNode successor = null;

    /**
     * 反转链表的部分区间 [left, right]
     *
     * @param head  链表头节点
     * @param left  左边界
     * @param right 右边界
     * @return 反转后的链表头节点
     */
    public static ListNode reverseBetweenRecursion(ListNode head, int left, int right) {
        if (left == 1) {
            return reverseN(head, right);
        }

        head.next = reverseBetweenRecursion(head.next, left - 1, right - 1);
        return head;
    }

    /**
     * 反转链表的前 N 个节点
     *
     * @param head 链表头节点
     * @param n    反转的节点数量
     * @return 反转后的链表头节点
     */
    public static ListNode reverseN(ListNode head, int n) {
        if (n == 1) {
            // 记录第 n + 1 个节点
            successor = head.next;
            return head;
        }
        // 以 head.next 为起点，需要反转前 n - 1 个节点
        ListNode last = reverseN(head.next, n - 1);
        head.next.next = head;
        // 让反转之后的 head 节点和后面的节点连起来
        head.next = successor;
        return last;

    }
}
