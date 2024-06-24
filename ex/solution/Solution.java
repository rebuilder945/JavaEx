package solution;
import java.math.BigInteger;
import java.util.AbstractSequentialList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.StringJoiner;

class Solution {
    //utils
    /* 输出数组 */
    public static void output_array(int[] arr) {
        for (int i = 0; i < arr.length; ++i) {
            System.out.println(arr[i]);
            // System.out.print(String.valueOf(arr[i]) + ", ");
        }
    }

    public static void output_array(int[][] arr) {
        for (int i = 0; i < arr.length; ++i) {
            for (int j = 0; j < arr[0].length; ++j) {
                System.out.print(String.valueOf(arr[i][j]) + " ");
            }       
            System.out.print("\n");
        }
    }

    // 35. 搜索插入位置（二分查找）
    public int searchInsert(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        int mid = 0;
        while (l <= r) {
            mid = ((r - l) >> 1) + l;
            if (target < nums[mid]) {
                r = mid - 1;
            } else if (target > nums[mid]) {
                l = mid + 1;
            } else {
                return mid;
            }
        }
        return l;
    }

    // 67. 二进制求和
    public String addBinary(String a, String b) {
        StringBuilder ans = new StringBuilder();
        char[] a_ = a.toCharArray();
        char[] b_ = b.toCharArray();
        int carry = 0;
        for (int i = a_.length - 1, j = b_.length - 1; i >= 0 || j >= 0; --i, --j) {
            int tmp_sum = (i >= 0 ? a_[i] - '0' : 0)
                    + (j >= 0 ? b_[j] - '0' : 0)
                    + carry;
            ans.append(tmp_sum % 2);
            carry = tmp_sum >= 2 ? 1 : 0;
        }
        if (carry == 1) {
            ans.append(1);
        }
        return ans.reverse().toString();
    }    

    // 
    public int reverseBits(int n) {
        return Integer.reverse(n);
    }

    // 回文数
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        } else if (x < 10) {
            return true;
        }
        StringBuilder sb = new StringBuilder(
            String.valueOf(x)
        );
        return sb.reverse().toString().equals(String.valueOf(x));
        
        // if (x < 0) {
        //     return false;
        // }
        // char[] x_ = String.valueOf(x).toCharArray();
        // for (int i = 0; i < (int)(x_.length / 2); ++i) {
        //     int j = x_.length - i - 1;
        //     if (x_[i] != x_[j]) {
        //         return false;
        //     }
        // }
        // return true;


    }

    // 66. 加一(大数相加，进位)
    public int[] plusOne(int[] digits) {
        int carry = 0;
        for (int i = digits.length - 1; i >= 0 ; --i) {
            int tmp = 
                i == digits.length - 1 ? 
                digits[i] + carry + 1 : 
                digits[i] + carry;                
            carry = tmp >= 10 ? 1 : 0;
            digits[i] = tmp % 10;
        }
        if (carry == 1) {
            ArrayList<Integer> tmp_arr = new ArrayList<>();
            tmp_arr.add(1);
            for (int i: digits) {
                tmp_arr.add(i);
            }
            int[] res = new int[digits.length + 1];
            for (int i = 0; i < digits.length + 1; ++i) {
                res[i] = tmp_arr.get(i);
            }
            return res;
        } else {
            return digits;
        }
    }

    // 69. x的平方根
    public int mySqrt(int x) {
        double ans = x; // 设置初始值
        double delta = 1e-5; // 精度
        while (ans * ans - x > delta) {
            // 计算ans的平方与x的差值，控制误差
            ans = (double)((ans + x / ans) / 2);
        }
        return (int)ans;
    }

    // 70. 爬楼梯
    // 经典的动态规划题目，同Fibonacci数列。注意状态转移，当前状态与之前
    // 状态的关系：假设之前状态已知，如何在一步的情况下转移到当前状态
    // 由于状态的联系比较少，所以可以直接用几个变量来转换。（滚动数组）
    // 法1 自底向上动态规划（通过过去状态递推）
    public int climbStairs(int n) {
        int p = 1;
        int q = 1;
        int k = p;
        for (int i = 2; i < n + 1; ++i) {
            k = p + q;
            p = q;
            q = k;
        }
        return k;
    }
    // 法2 自顶向下动态规划（保存中间结果避免重复计算）
    public HashMap<Integer, Integer> hm = new HashMap<>();
    public int climbStairs_(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        if (hm.containsKey(n)) return hm.get(n);
        int tmp = climbStairs_(n - 1) + climbStairs_(n - 2);
        hm.put(n, tmp);
        return tmp;
    }
    // 法3 矩阵快速幂
    // TODO 待理解
    
    // 238. 除自身以外数组的乘积
    // 贪心维护左右方向的乘积，可以用结果数组维护一边的（所有位置的前缀或后缀乘积）
    // ，另一边用一个变量动态更新（维护前缀或后缀乘积）即可
    public int[] productExceptSelf(int[] nums) {
        int length = nums.length;
        int[] post = new int[length];
        int tmp = 1;
        post[length - 1] = 1;
        for (int i = length - 2; i >= 0; --i) {
            post[i] = nums[i + 1] * tmp;
            tmp = post[i];
        }
        tmp = 1;
        // post[0] = post[1];
        for (int i = 1; i < nums.length; ++i) {
            tmp *= nums[i - 1];
            post[i] *= tmp;
        }
        return post;
    }

    // 134. 加油站
    public int canCompleteCircuit(int[] gas, int[] cost) {       
        for (int i = 0; i < gas.length;) {
            int sum = 0;
            int j = 0;
            if (gas[i] - cost[i] >= 0) {
                for (; j < gas.length; ++j) {
                    int index = (i + j) % gas.length;
                    sum += (gas[index] - cost[index]);
                    if (sum < 0) {
                        break;
                    }
                }
                if (j == gas.length) {
                    return i;
                }
                i += j + 1;
            } else {
                ++i;
            }
        }
        return -1;
    }
    // 法2
    public int canCompleteCircuit_(int[] gas, int[] cost) {         
        int min = Integer.MAX_VALUE;  
        int minIndex = -1;
        int tmp = 0;
        for (int i = 0; i < gas.length; ++i) {
            tmp += gas[i] - cost[i];
            if (tmp < min && tmp < 0) {
                min = tmp;
                minIndex = i;
            }
        }
        if (tmp < 0) {
            return -1;
        } else {
            return minIndex + 1;
        }
    }

    // 135. 分发糖果
    public int candy(int[] ratings) {
        int length = ratings.length;
        if (length == 1) {
            return 1;
        }
        int ans = 0;
        int[] candies = new int[length];   
        for (int i = 0; i < length - 1; ++i) {
            int l = ratings[i];
            int r = ratings[i + 1];         
            if (l > r && candies[i] <= candies[i + 1]) {
                ++candies[i];
            } else if (l < r && candies[l] >= candies[i + 1]) {
                ++candies[i + 1];
            } else {
                continue;
            }
            ++ans;
        }
        return ans + length;
    }

    // 12. 整数转罗马数字
    // public String intToRoman(int num) {
        
    // }

    // 151. 反转字符串中的单词
    public String reverseWords(String s) {
        if (s.length() == 1) {
            return s;
        }        
        // Stack<String> stack = new Stack<>();
        // ArrayList<String> ans = new ArrayList<>();
        StringJoiner sj = new StringJoiner(" ");

        int begin = -1;
        int end = -1;
        for (int i = s.length() - 1; i >= 0; --i) {
            if (s.charAt(i) == ' ') {
                if (begin == -1) {
                    continue;
                } else {
                    sj.add(s.substring(end + 1, begin + 1));
                    end = begin = -1;
                }
            } else {
                end = i - 1;
                // 只有非空格的时候要考虑是否是最后一位
                if (end == -1) {
                    if (begin == -1) {
                        sj.add(s.substring(end + 1, i + 1));
                    } else {
                        sj.add(s.substring(end + 1, begin + 1));
                    }
                } else if (begin == -1) {
                    begin = i;
                }
            }
        }
        // while (!stack.empty()) {
        //     sj.add(stack.pop());
        // }
        // for (int i = ans.size() - 1; i >= 0; --i) {
        //     sj.add(ans.get(i));
        // }
        return sj.toString();
    }

    // 6. z字形变换
    // public String convert(String s, int numRows) {
        
    // }

    // 167. 两数之和2-输入有序数组
    // 双指针秒了，O(n), O(1)
    // 或二分查找，对每个数在其右边查找(target-该数), O(nlogn), O(1)
    public int[] twoSum(int[] numbers, int target) {
        int i = 0, j = numbers.length - 1;
        while (i < j && numbers[i] + numbers[j] != target) {
            while(numbers[i] + numbers[j] < target) {
                ++i;
            }
            while(numbers[i] + numbers[j] > target) {
                --j;
            }
        }
        return new int[]{i + 1, j + 1};
    }

    // 209. 长度最小的子数组
    // 滑动窗口: O(n), O(1)
    public int minSubArrayLen(int target, int[] nums) {
        int i = 0;
        int j = 0;
        int tmp_sum = nums[i];
        int ans = nums.length + 1;
        while (i < nums.length && j < nums.length && i <= j) {
            if (tmp_sum >= target) {
                ans = Math.min(ans, j - i + 1);
                tmp_sum -= nums[i++];
            } else {
                // 循环中进行下标+1操作，由于范围是lazy-judge的
                // 因此要先判断一次是否越界
                ++j;
                if (j < nums.length) {
                    tmp_sum += nums[j];
                }
            }
        }
        return ans != nums.length ? ans : 0;
    }
    // 同样是滑动窗口的思想，用双while写法代替多余的if判断
    public int minSubArrayLen_(int target, int[] nums) {
        int i = 0, j = 0, tmp_sum = 0, ans= nums.length + 1;
        while (i < nums.length && j < nums.length && i <= j) {
            tmp_sum += nums[j];
            while (tmp_sum >= target && i < nums.length) {
                ans = j - i + 1 < ans ? j - i + 1 : ans;
                tmp_sum -= nums[i++];
            }
            ++j;            
        }
        return ans == nums.length + 1 ? 0 : ans;
    }
    // TODO: 前缀和+二分查找：
    //
        
    // 3. 无重复字符的最长子串
    // 滑动窗口: O(n), O(|Σ|)（其中 Σ\SigmaΣ 表示字符集（即字符串中可以出现的字符），∣Σ∣|\Sigma|∣Σ∣ 表示字符集的大小。）
    // 题中没有明确说明字符集，因此可以默认为所有 ASCII 码在 [0,128)[0, 128)[0,128) 内的字符
    // 也可用哈希表
    // Map<Character, Integer> map = new HashMap<>();
    // 但HashMap的查键、删除和赋值速度太慢    
    public int lengthOfLongestSubstring(String s) {
        int i = 0, j = 0, ans_len = 0;
        char[] s_ = s.toCharArray();        
        int[] map = new int[128];
        while (i < s_.length && j < s_.length && i <= j) {            
            while (map[s_[j]] == 1 && i < s_.length) {
                map[s_[i++]] = 0;
            }
            map[s_[j++]] = 1;
            ans_len = j - i > ans_len ? j - i : ans_len;
        }
        return ans_len;        
    }

    // 36. 有效的数独
    // naive method
    public boolean isValidSudoku(char[][] board) {
        // 按行
        int[] map = new int[9];
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board.length; ++j) {
                char cur = board[i][j];
                if (cur == '.') {
                    continue;
                }
                int cur_num = cur - '0';
                if (map[cur_num - 1] != 0) {
                    return false;
                }
                map[cur_num - 1] = 1;
            }
            // 清空map
            map = new int[9];
        }
        // 按列
        map = new int[9];
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board.length; ++j) {
                char cur = board[j][i];
                if (cur == '.') {
                    continue;
                }
                int cur_num = cur - '0';
                if (map[cur_num - 1] != 0) {
                    return false;
                }
                map[cur_num - 1] = 1;
            }
            // 清空map
            map = new int[9];
        }
        // 按块
        map = new int[9];
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                for (int k = 0; k < 3; ++k) {
                    for (int l = 0; l < 3; ++l) {
                        char cur = board[i * 3 + k][j * 3 + l];
                        if (cur == '.') {
                            continue;
                        }
                        int cur_num = cur - '0';
                        if (map[cur_num - 1] != 0) {
                            return false;
                        }
                        map[cur_num - 1] = 1;
                    }
                }
                // 清空map
                map = new int[9];
            }
        }
        return true;
    }
    // TODO: 解决bug，并思考如何bitVectorBlock也只用一个数字
    // one traverse + bit calculation（位向量判断一个限位数列中是否含有重复数字，用：<<, |, &运算符）
    public boolean isValidSudoku_(char[][] board) {
        int bitVectorRow = 0;
        int bitVectorColumn = 0;
        int[] bitVectorBlock = new int[9];
        int bitPosition;
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board.length; ++j) {                
                if (board[i][j] == '.') {
                    continue;
                }
                // 比较行
                int row_num = board[i][j] - '0';
                bitPosition = 1 << row_num;
                if ((bitVectorRow & bitPosition) != 0) {
                    return false;
                }
                bitVectorRow |= bitPosition;

                // 比较块
                if ((bitVectorBlock[(i / 3) * 3 + (j / 3)] & bitPosition) != 0) {
                    return false;
                }
                bitVectorBlock[(i / 3) * 3 + (j / 3)] |= bitPosition;

                // 比较列
                int column_num = board[j][i] - '0';
                bitPosition = 1 << column_num;
                if ((bitVectorColumn & bitPosition) != 0) {
                    return false;
                }
                bitVectorColumn |= bitPosition;            
            }
            // 仅清零行和列的bitVector
            bitVectorRow = 0;
            bitVectorColumn = 0;
        }
        return true;
    }

    // 54. 螺旋矩阵
    // 递归版本的矩阵dfs
    public List<Integer> dfs_matrix(int[][] matrix, List<Integer> res, int i, int j) {
        res.add(matrix[i][j]);
        matrix[i][j] = 101;
        // go right
        if (j < matrix[0].length - 1 && matrix[i][j + 1] != 101) {
            // go up if up is reachable
            if (i >= 1 && matrix[i - 1][j] != 101) {
                dfs_matrix(matrix, res, i - 1, j);
            } else {
                dfs_matrix(matrix, res, i, j + 1);
            }
        }
        // go down
        if (i < matrix.length - 1 && matrix[i + 1][j] != 101) {
            dfs_matrix(matrix, res, i + 1, j);
        }
        // go left
        if (j >= 1 && matrix[i][j - 1] != 101) {            
            dfs_matrix(matrix, res, i, j - 1);
        }
        // go up
        if (i >= 1 && matrix[i - 1][j] != 101) {
            dfs_matrix(matrix, res, i - 1, j);
        }
        return res;
    }
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        return dfs_matrix(matrix, res, 0, 0);
    }
    // TODO: 实现非递归化，用栈或直接循环即可。

    // 48. 旋转图像
    public void rotate(int[][] matrix) {
        // 转置
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j <= i; ++j) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }
        // 左右reverse  
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < (int)(matrix[0].length / 2); ++j) {
                int tmp = matrix[i][matrix[0].length - 1 - j];
                matrix[i][matrix[0].length - 1 - j] = matrix[i][j];
                matrix[i][j] = tmp;
            }            
        }
    }

    // 73. 矩阵置零
    public void setZeroes(int[][] matrix) {
        // 记录第0行和第0列是否有0
        int flag_row = 0;
        if (matrix[0][0] == 0) {
            flag_row = 1;
        } else {
            // 查看第0行是否存在0
            for (int j = 1; j < matrix[0].length; ++j) {
                if (matrix[0][j] == 0) {
                    flag_row = 1; // 指示0行有0
                    matrix[0][j] = 0; // 指示j列有0
                }
            }
            // 查看第0列是否存在0
            for (int i = 0; i < matrix.length; ++i) {
                if (matrix[i][0] == 0) {
                    matrix[0][0] = 0; //指示0列有0
                    matrix[i][0] = 0; // 指示i行有0
                }
            }
        }
        // 记录有0的行和列
        for (int i = 1; i < matrix.length; ++i) {
            for (int j = 1; j < matrix[0].length; ++j) {
                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }
        // 清零有0的列
        for (int j = 1; j < matrix[0].length; ++j) {
            if (matrix[0][j] == 0) {
                for (int i = 0; i < matrix.length; ++i) {
                    matrix[i][j] = 0;
                }
            }
        }
        // 清零有0的行
        for (int i = 1; i < matrix.length; ++i) {
            if (matrix[i][0] == 0) {
                for (int j = 0; j < matrix[0].length; ++j) {
                    matrix[i][j] = 0;
                }
            }
        }
        // 看第0列是否需要清零
        if (matrix[0][0] == 0) {
            for (int i = 0; i < matrix.length; ++i) {
                matrix[i][0] = 0;
            }
        }
        // 看第0行是否需要清零
        if (flag_row == 1) {
            for (int j = 0; j < matrix[0].length; ++j) {
                matrix[0][j] = 0;
            }
        }
    }

    // 74. 生命游戏
    public void gameOfLife(int[][] board) {
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                // 获取周围的细胞
                int[] surrounding = new int[8];
                surrounding[0] = j > 0 ? board[i][j - 1] : -1; // left
                surrounding[1] = j < board[0].length - 1 ? board[i][j + 1] : -1; // right
                surrounding[2] = i > 0 ? board[i - 1][j] : -1; // up 
                surrounding[3] = i < board.length - 1 ? board[i + 1][j] : -1; // down
                surrounding[4] = (surrounding[0] != -1 && surrounding[2] != -1) ? board[i - 1][j - 1] : -1; // leftup
                surrounding[5] = (surrounding[1] != -1 && surrounding[2] != -1) ? board[i - 1][j + 1] : -1; // rightup
                surrounding[6] = (surrounding[0] != -1 && surrounding[3] != -1) ? board[i + 1][j - 1] : -1; // leftdown
                surrounding[7] = (surrounding[1] != -1 && surrounding[3] != -1) ? board[i + 1][j + 1] : -1; // rightdown
                // 记录周围活细胞个数
                int live = 0;
                for (int i_ : surrounding) {
                    // 变化前的活细胞 + 未变化的活细胞
                    if (i_ == 3 || i_ == 1) ++live;
                }
                // 细胞状态变化
                // 2:由0变为1, 3:由1变为0
                if (board[i][j] == 1) {
                    if (live < 2 || live > 3) {
                        board[i][j] = 3;
                    }
                } else {
                    if (live == 3) {
                        board[i][j] = 2;
                    }
                }
            }            
        }
        // 还原2和3
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                if (board[i][j] == 2) {
                    board[i][j] = 1;
                }
                if (board[i][j] == 3) {
                    board[i][j] = 0;
                }                
            }
        }
    }

    // 49. 字母异位词分组
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        for (String x : strs) {
            // 法1）计算唯一的哈希字符串            
            // char[] tmp = x.toCharArray();
            // int[] project = new int[26];
            // StringBuffer hash_str = new StringBuffer();
            // for (int i = 0; i < tmp.length; ++i) {
            //     project[tmp[i] - 'a'] += 1;
            // }
            // for (int i = 0; i < project.length; ++i) {
            //     if (project[i] != 0) {
            //         hash_str.append(i + 'a');
            //         hash_str.append(project[i]);
            //     }
            // }
            // 法2）直接排序
            char[] tmp = x.toCharArray();
            Arrays.sort(tmp);            
            String x_ = new String(tmp);
            // 放入哈希表对应位置
            List<String> tmp_list = map.getOrDefault(x_, new ArrayList<String>());
            tmp_list.add(x);
            map.put(x_, tmp_list);
            // 可用上面的map.getOrDefault简化书写，但要注意最后得put
            // if (map.get(x_) == null) {
            //     map.put(x_, new ArrayList<String>());
            // }
            // map.get(x_).add(x);
        }
        return new ArrayList<>(map.values());
    }

    // 128. 最长连续序列
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        // 每一个放入hash表
        HashMap<Integer, Integer> map = new HashMap<>();        
        for (int num : nums) {
            map.put(num, 1);
        }
        // 计算每个数字前面的数的连续长度
        int len = 0;
        int max_len = len;
        for (int num : nums) {
            int tmp = num;
            len = 0;
            while (map.containsKey(tmp) && map.get(tmp) != 0) {
                len += map.get(tmp);
                map.put(tmp, 0);
                tmp -= 1;
            }
            map.put(num, len);
            max_len = len > max_len ? len : max_len;
        }
        return max_len;
    }
    // 可继续优化。。。

    // 

}