package solution;
import constants.Trees;

public class ExTest {
    public static void main(String[] args) {
    //    Trees trees = new Trees();
    //    System.out.println(trees.tree);
        Trees trees = new Trees();
        Solution so = new Solution();
        // System.out.println(trees.tree7.getMinimumDifference_());
        int[] res = so.twoSum(
            new int[]{-2, -1,2, 4, 7, 11, 13, 15}, 9
        );
        
        // System.out.println(res);
        Solution.output_array(res);
    }
}