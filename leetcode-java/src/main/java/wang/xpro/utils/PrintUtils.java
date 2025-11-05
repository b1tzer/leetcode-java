package wang.xpro.utils;

import wang.xpro.base.ListNode;
import wang.xpro.base.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 打印工具类
 *
 * @author b1tzer
 */
public class PrintUtils {
    public static void printListNode(ListNode head) {
        // 打印链表
        ListNode current = head;

        List<String> values = new ArrayList<>();
        while (current != null) {
            values.add(String.valueOf(current.val));
            current = current.next;
        }

        System.out.println(String.join(" -> ", values));
    }

    /**
     * 打印二叉树（水平方向，适合控制台输出）
     *
     * @param root 树的根节点
     */
    public static void printHorizontal(TreeNode root) {
        if (root == null) {
            System.out.println("null");
            return;
        }

        List<List<String>> lines = new ArrayList<>();
        List<TreeNode> level = new ArrayList<>();
        List<TreeNode> nextLevel = new ArrayList<>();

        level.add(root);
        boolean hasNextLevel = true;

        while (!level.isEmpty() && hasNextLevel) {
            List<String> line = new ArrayList<>();
            hasNextLevel = false;

            for (TreeNode node : level) {
                if (node == null) {
                    line.add("   ");
                    nextLevel.add(null);
                    nextLevel.add(null);
                } else {
                    line.add(String.format("%3d", node.val));
                    nextLevel.add(node.left);
                    nextLevel.add(node.right);
                    if (node.left != null || node.right != null) {
                        hasNextLevel = true;
                    }
                }
            }

            lines.add(line);
            level = nextLevel;
            nextLevel = new ArrayList<>();
        }

        // 打印所有行
        printLines(lines);
    }

    /**
     * 打印每一行
     *
     * @param lines 行列表
     */
    private static void printLines(List<List<String>> lines) {
        if (lines.isEmpty()) return;

        int depth = lines.size();
        int width = (int) Math.pow(2, depth - 1) * 3;

        for (int i = 0; i < depth; i++) {
            List<String> line = lines.get(i);
            int space = (width / (int) Math.pow(2, i)) - 3;
            StringBuilder sb = new StringBuilder();

            // 前导空格
            sb.append(" ".repeat(space / 2));

            for (int j = 0; j < line.size(); j++) {
                sb.append(line.get(j));
                if (j < line.size() - 1) {
                    sb.append(" ".repeat(space));
                }
            }

            System.out.println(sb);

            // 打印连接线（除了最后一行）
            if (i < depth - 1) {
                sb = new StringBuilder();
                sb.append(" ".repeat(space / 2 - 1));

                for (int j = 0; j < line.size(); j++) {
                    if (!line.get(j).trim().isEmpty()) {
                        sb.append("/ \\");
                    } else {
                        sb.append("   ");
                    }
                    if (j < line.size() - 1) {
                        sb.append(" ".repeat(space - 2));
                    }
                }
                System.out.println(sb);
            }
        }
    }

    /**
     * 简洁版打印（垂直方向，适合深度较大的树）
     *
     * @param root 树的根节点
     */
    public static void printVertical(TreeNode root) {
        printVertical(root, "", true);
    }

    private static void printVertical(TreeNode node, String prefix, boolean isLeft) {
        if (node == null) {
            System.out.println(prefix + (isLeft ? "└── " : "┌── ") + "null");
            return;
        }

        System.out.println(prefix + (isLeft ? "└── " : "┌── ") + node.val);

        String newPrefix = prefix + (isLeft ? "    " : "│   ");
        printVertical(node.right, newPrefix, false);
        printVertical(node.left, newPrefix, true);
    }

    /**
     * 层序遍历打印（广度优先，直观显示层级关系）
     *
     * @param root 树的根节点
     */
    public static void printLevelOrder(TreeNode root) {
        if (root == null) {
            System.out.println("null");
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int level = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            System.out.print("Level " + level + ": ");
            List<String> levelValues = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node != null) {
                    levelValues.add(String.valueOf(node.val));
                    queue.offer(node.left);
                    queue.offer(node.right);
                } else {
                    levelValues.add("null");
                }
            }

            // 检查是否全是null，如果是则停止
            boolean allNull = levelValues.stream().allMatch("null"::equals);
            if (allNull) break;

            System.out.println(String.join(" ", levelValues));
            level++;
        }
    }

    /**
     * 紧凑型打印（节省空间）
     *
     * @param root 树的根节点
     */
    public static void printCompact(TreeNode root) {
        System.out.println(serialize(root, ", "));
    }

    /**
     * 生成树的字符串表示（用于assert或测试）
     *
     * @param root 树的根节点
     * @return 树的字符串表示
     */
    public static String serialize(TreeNode root) {
        return serialize(root, ", ");
    }

    /**
     * 生成树的字符串表示（用于assert或测试）
     *
     * @param root      树的根节点
     * @param separator 分隔符
     * @return 树的字符串表示
     */
    public static String serialize(TreeNode root, String separator) {
        if (root == null) return "null";
        return convertTree2String(root, separator);
    }

    private static String convertTree2String(TreeNode root, String separator) {
        List<String> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node != null) {
                result.add(String.valueOf(node.val));
                queue.offer(node.left);
                queue.offer(node.right);
            } else {
                result.add("null");
            }
        }

        int i = result.size() - 1;
        while (i >= 0 && "null".equals(result.get(i))) {
            i--;
        }

        return "[" + String.join(separator, result.subList(0, i + 1)) + "]";
    }
}