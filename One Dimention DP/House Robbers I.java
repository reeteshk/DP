/*
198. House Robber
Medium
17.7K
334
Companies
You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.
 


Example 1:

Input: nums = [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.
Example 2:

Input: nums = [2,7,9,3,1]
Output: 12
Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
Total amount you can rob = 2 + 9 + 1 = 12.


Constraints:

1 <= nums.length <= 100
0 <= nums[i] <= 400


*/

// public int rob(int[] nums) {
//     int dp[]=new int[nums.length];
//     for(int i=0;i<nums.length;i++)
//     {
//         dp[i]=-1;
//     }
//     return adjSum(nums,nums.length,dp);
// }


class Solution {
    public int rob(int[] nums) {
        // int dp[]=new int[nums.length];
        // for(int i=0;i<nums.length;i++)
        // {
        //     dp[i]=-1;
        // }
       return adjSumSpaceOpz(nums,nums.length);
    }

    //Recursive Approach
      public static int adjSum(int nums[],int n,int dp[]){
        if(n==0)
        {
            return nums[n];
        }
        if(n<0)
        {
            return 0;
        }
        if(dp[n]!=-1)
        {
            return dp[n];
        }
        int pick=nums[n]+adjSum(nums,n-2,dp);
        int notpick=adjSum(nums,n-1,dp);
        return dp[n]=Math.max(pick,notpick);
    }
    //Memoization Approach
    public static int adjSum(int nums[],int n)
    {
        int dp[]=new int[nums.length+1];
       dp[0]= nums[0];
         for(int i=1 ;i<n; i++){
        int pick = nums[i];
        if(i>1)
            pick += dp[i-2];
        int nonPick = 0+ dp[i-1];
        
        dp[i]= Math.max(pick, nonPick);
    }
    return dp[n-1];
    }

    //DP
    public static int adjSumSpace(int nums[])
    {
        int dp[]=new int[nums.length+1];
       dp[0]= nums[0];
         for(int i=1 ;i<nums.length; i++){
        int pick = nums[i];
        if(i>1)
            pick += dp[i-2];
        int nonPick = 0+ dp[i-1];
        dp[i]= Math.max(pick, nonPick);
    }
    return dp[nums.length-1];
    }

    //Optimized DP
    public static int adjSumSpaceOpz(int nums[],int n)
    {
       int prev= nums[0];
       int prev2=0;
         for(int i=1 ;i<n; i++){
        int pick = nums[i];
        if(i>1)
            pick += prev2;
        int nonPick = 0+ prev;
        
        int curri= Math.max(pick, nonPick);
        prev2=prev;
        prev=curri;
    }
    return prev;
    }
}
