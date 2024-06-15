package solution;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
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

    // 
}