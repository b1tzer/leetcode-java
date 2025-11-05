package wang.xpro.utils;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import wang.xpro.base.TreeNode;

@DisplayName("PrintUtils 测试类")
class PrintUtilsTest {
    private static TreeNode root = null;

    @BeforeAll
    static void setUp() {

        // 构建测试树:
        //       1
        //      / \
        //     2   3
        //    /   / \
        //   4   5   6
        //      /
        //     7

        root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);
        root.right.left.left = new TreeNode(7);
    }


    @Test
    void printListNode() {
    }

    @Test
    @DisplayName("=== 水平打印 ===")
    void printHorizontal() {
        PrintUtils.printHorizontal(root);

        // 测试空树
        System.out.println("\n=== 空树测试 ===");
        PrintUtils.printHorizontal(null);
    }

    @Test
    @DisplayName("=== 垂直打印 ===")
    void printVertical() {
        PrintUtils.printVertical(root);

        // 测试空树
        System.out.println("\n=== 空树测试 ===");
        PrintUtils.printVertical(null);

    }

    @Test
    @DisplayName("=== 层序打印 ===")
    void printLevelOrder() {
        PrintUtils.printLevelOrder(root);

        // 测试空树
        System.out.println("\n=== 空树测试 ===");
        PrintUtils.printLevelOrder(null);

    }

    @Test
    @DisplayName("=== 紧凑打印 ===")
    void printCompact() {
        PrintUtils.printCompact(root);

        // 测试空树
        System.out.println("\n=== 空树测试 ===");
        PrintUtils.printCompact(null);
    }

    @Test
    @DisplayName("=== 序列化 ===")
    void serialize() {
        System.out.println(PrintUtils.serialize(root));

        // 测试空树
        System.out.println("\n=== 空树测试 ===");
        PrintUtils.serialize(null);
    }
}