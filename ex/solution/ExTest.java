package solution;
import java.util.List;

import constants.Trees;

public class ExTest {
    public static void main(String[] args) {
        // Trees trees = new Trees();
        // System.out.println(trees.tree);
        // Trees trees = new Trees();
        Solution so = new Solution();
        // System.out.println(trees.tree7.getMinimumDifference_());
        // int[][] test = new int[][]{
        //     // new int[]{0, 1, 0},
        //     // new int[]{0, 0, 1},
        //     // new int[]{1, 1, 1},
        //     // new int[]{0, 0, 0},
        //     new int[]{1, 1},
        //     new int[]{1, 0}
        // };
        int ans = so.longestConsecutive(   
            // new int[]{100,4,200,1,3,2}
            new int[]{0,3,7,2,5,8,4,6,0,1}
            // new int[]{1,0,-1}
        );
        System.out.println(ans);
        // Solution.output_array(test);
    }
    
}