package constants;
import solution.TreeNode;

public class Trees {

    // tree: 一颗较为完整的树
    public TreeNode tree = new TreeNode(
            3,
            new TreeNode(
                    9,
                    new TreeNode(
                            1,
                            new TreeNode(
                                    5),
                            null),
                    new TreeNode(
                            2)),
            new TreeNode(
                    20,
                    new TreeNode(
                            15),
                    new TreeNode(
                            7)));

    // tree1和tree2: 不相同的树
    // tree2和tree3: 相同的树
    public TreeNode tree1 = new TreeNode(
            1,
            new TreeNode(
                    1),
            null);

    public TreeNode tree2 = new TreeNode(
            1,
            null,
            new TreeNode(
                    1));

    public TreeNode tree3 = new TreeNode(
            1,
            null,
            new TreeNode(
                    1));

    // tree4: 对称树
    public TreeNode tree4 = new TreeNode(
            1,
            new TreeNode(
                    2,
                    new TreeNode(
                            3),
                    new TreeNode(
                            4)),
            new TreeNode(
                    2,
                    new TreeNode(
                            4),
                    new TreeNode(
                            3)));

    // tree5: 二叉树的层平均值
    public TreeNode tree5 = new TreeNode(
            3,
            new TreeNode(9),
            new TreeNode(
                    20,
                    new TreeNode(15),
                    new TreeNode(7)));
    public TreeNode tree6 = new TreeNode(
        3,
        new TreeNode(
            9,
            new TreeNode(15),
            new TreeNode(7)
        ),
        new TreeNode(20)
    );

    // tree6：二叉搜索树
    public TreeNode tree7 = new TreeNode(
        4,
        new TreeNode(
            2,
            new TreeNode(1),
            new TreeNode(3)
        ),
        new TreeNode(
            6
        )
    );

    // TODO 二叉树构建，二叉搜索树构建、平衡二叉树的平衡
    // public static TreeNode createBST(int[] nums) {
        
    // }

    
}
