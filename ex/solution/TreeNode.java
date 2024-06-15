package solution;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class TreeNode {
    // Definition for a binary tree node.
    int val;
    TreeNode left;
    TreeNode right;
    public TreeNode() {}
    public TreeNode(int val) { this.val = val; }
    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    // 以下是基本的树操作代码
    // 遍历获取树结构表示
    public String travers(TreeNode node) {
        StringBuilder sb = new StringBuilder();
        // 先序遍历
        // System.out.println(node.val);
        sb.append(String.valueOf(node.val));
        sb.append(':');
        sb.append('[');
        sb.append(
            node.left == null ?
            "" : 
            travers(
                node.left
            )
        );
        // 中序遍历
        // System.out.println(node.val);
        sb.append(',');
        sb.append(
            node.right == null ?
            "" : 
            travers(
                node.right
            )
        );
        sb.append(']');
        // 后序遍历
        // System.out.println(node.val);
        return sb.toString();
    }

    // dfs前序递归以及前中后的非递归
    public String DFS(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        if (root == null) {
            return "";
        }        
        sb.append(String.valueOf(root.val));
        sb.append(DFS(root.left));
        sb.append(DFS(root.right));
        return sb.toString();
    }

    public String DFS_iter_preOrder(TreeNode root) {
        if (root == null) {
            return "";
        }
        String ans = "";        
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        stack.push(root);
        while (!stack.isEmpty()) {   
            cur = stack.pop();
            ans += String.valueOf(cur.val);
            // 注意先右后左，弹出的时候才是根左右的顺序
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
        return ans;        
    }

    public String DFS_iter_inOrder(TreeNode root) {
        String ans = "";
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            // 到达最左
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            // 中序root
            cur = stack.pop();
            ans += String.valueOf(cur.val);
            // 处理右子树
            cur = cur.right;            
        }
        return ans;
    }
    
    public String DFS_iter_postOrder(TreeNode root) {
        // 即先序改为“根右左”顺序，然后逆序(利用栈实现逆序)，即得到后续遍历序列
        if (root == null) {
            return "";
        }
        String ans = "";
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        TreeNode cur = root;
        stack1.push(root);
        while (!stack1.isEmpty()) {
            cur = stack1.pop();
            stack2.push(cur);

            if (cur.left != null) {
                stack1.push(cur.left);
            }
            if (cur.right != null) {
                stack1.push(cur.right);
            }
        }
        while (!stack2.isEmpty()) {
            ans += String.valueOf(stack2.pop().val);
        }
        return ans;
    }

    public String BFS(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        if (root == null) {
                return "";
        }
        sb.append(String.valueOf(root.val));
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root.left);
        q.add(root.right);
        while (!q.isEmpty()) {
            TreeNode cur = q.poll();
            if (cur == null) {
                continue;
            }
            sb.append(String.valueOf(cur.val));
            if (cur.left != null) {
                q.add(cur.left);
            }
            if (cur.right != null) {
                q.add(cur.right);
            }
        }
        return sb.toString();
    }

    public String toString() {
        // return travers(this);
        return DFS_iter_postOrder(this);
    }

    // 以下是题目specific的相关树处理代码
    // 100.判断两棵树是否相同
    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        if (p.val == q.val) {
            return isSameTree(p.left, q.left)
                && isSameTree(p.right, q.right);
        }
        return false;
    }

    // 226.翻转树
    public TreeNode invertTree() {
        // invert self
        TreeNode tmp = this.left;
        this.left = this.right;
        this.right = tmp;
        if (this.left != null) {
            this.left.invertTree();        
        }
        if (this.right != null) {
            this.right.invertTree();
        }
        return this;
    }

    // 101.判断树是否对称
    // 101-1 判断左右子树是否互为翻转
    // public boolean isSymmetric() {
    //     if (this.left != null && this.right != null) {
    //         return isSameTree(this.left, this.right.invertTree());
    //     } else if (this.left == null && this.right == null) {
    //         return true;
    //     } else {
    //         return false;
    //     }
    // }

    // 101-2 直接判断左右子树是否对称相等
    public boolean isSymmetric() {
        return check(this.left, this.right);
    }

    private boolean check(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        if (p.val == q.val) {
            return check(p.left, q.right)
                && check(p.right, q.left);
        }
        return false;
    }
    
    // 128.寻找是否存在路径总和满足要求
    public static boolean hasPathSum(TreeNode root, int targetSum) {
        // 处理如果输入root即为空
        if (root == null) {
            return false;
        }
        // 结束判断条件：叶节点处
        if (root.left == null 
            && root.right == null 
            && root.val == targetSum
        ) {
            return true;
        }
        // 用减法代替加法可以避免在递归过程中处理path_length的传参
        return hasPathSum(root.left, targetSum - root.val) 
            || hasPathSum(root.right, targetSum - root.val);
    }
    // private boolean checkPath(TreeNode root, int targetSum, int v) {
    //     // 处理如果输入root即为空
    //     if (root == null) {
    //         return false;
    //     }
    //     // 结束判断条件：叶节点处
    //     int path_length = v + root.val;
    //     if (root.left == null 
    //         && root.right == null 
    //         && path_length == targetSum
    //     ) {
    //         return true;
    //     }
    //     return checkPath(root.left, targetSum, path_length)
    //         || checkPath(root.right, targetSum, path_length);
    // }

    // 222.计算完全二叉树节点个数
    // 222-1 复杂度O(n)：直接dfs遍历，然后计数
    public static int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return countNodes(root.left) + countNodes(root.right) + 1;
    }
    
    // 222-2 复杂度O((logn)^2)：二分查找、位运算
    // TODO 待理解和完成
   
    // 637. 二叉树的层平均值
    // 法1
    public List<Double> averageOfLevels_1(TreeNode root) {
        // 层次遍历栈
        Queue<TreeNode> q = new LinkedList<>();
        // 返回答案列表
        List<Double> ans = new ArrayList<>();
        // 用于判断当前层是否遍历结束
        int cur_layer_left = 1;
        // 获取当前层的个数
        int cur_layer_num = 1;
        // 统计每一层的个数
        int next_layer = 0;
        // 用于累计一层的值总和
        double tmp = 0;
        // 根节点入栈
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode cur = q.poll();
            tmp += cur.val;     
            // 统计下一层的节点个数       
            if (cur.left != null) {
                q.add(cur.left);
                ++next_layer;
            }
            if (cur.right != null) {
                q.add(cur.right);
                ++next_layer;
            }
            // 判断当前层是否结束，结束时计算当前层均值
            // 下一层的节点个数也已统计完毕，更新为当前层节点个数
            if (--cur_layer_left == 0) {
                ans.add(tmp / cur_layer_num);
                tmp = 0;
                cur_layer_left = cur_layer_num = next_layer;
                next_layer = 0;
            }
        }
        return ans;
    }
    // 法2
    public List<Double> averageOfLevels_2(TreeNode root) {
        // 层次遍历栈
        Queue<TreeNode> q = new LinkedList<>();
        // 返回答案列表
        List<Double> ans = new ArrayList<>();
        q.add(root);
        while (!q.isEmpty()) {
            int cur_lay_num = q.size();
            double sum = 0;
            for (int i = 0; i < cur_lay_num; ++i) {
                TreeNode cur = q.poll();
                sum += cur.val;
                if (cur.left != null) {
                    q.add(cur.left);
                }
                if (cur.right != null) {
                    q.add(cur.right);
                }
            }
            ans.add(sum / cur_lay_num);
        }
        return ans;
    }

    // 二叉搜索树
    // 二叉搜索树相关操作代码
    // 530. 二叉搜索树的最小绝对差
    // 二叉搜索树性质：中序遍历序列递增
    // 法1. 非递归
    public int getMinimumDifference() {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = this;
        int ans = Integer.MAX_VALUE;
        int pre = 0;
        int flag = 0;
        while (!stack.isEmpty() || cur != null) {            
            // 到最左边
            while (cur != null) {
                stack.push(cur);                
                cur = cur.left;
            }
            // 中序root，跳过首个节点作差
            cur = stack.pop();
            if (flag != 0) {
                int tmp = Math.abs(cur.val - pre);
                ans = tmp < ans ? tmp : ans;
            }
            // 保存当前值，为了下一个与此作差
            pre = cur.val;
            // 标记非首个节点
            flag += 1;
            // 处理右边
            cur = cur.right;
        }
        return ans;
    }
    // 法2. 递归
    public int getMinimumDifference_() {
        // dfs_(this);
        // return ans;
        return dfs__(this);
    }
    public int ans = Integer.MAX_VALUE;
    public int pre = Integer.MAX_VALUE;
    private void dfs_(TreeNode node) {    
        // 间接递归
        if (node == null) {
            return;
        }
        dfs_(node.left);
        if (pre != -1) {
            int tmp = Math.abs(node.val - pre);
            ans = ans > tmp ? tmp : ans;
        }
        pre = node.val;
        dfs_(node.right);
    }
    private int dfs__(TreeNode node) {
        // 直接递归
        if (node == null) {
            return -1;
        }
        int l_min = dfs__(node.left);
        ans = Math.abs(node.val - pre);
        pre = node.val;
        int r_min = dfs__(node.right);
        return Math.min(Math.min(ans, l_min), r_min);
    }
    
    // 
}
