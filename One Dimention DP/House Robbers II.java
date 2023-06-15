/*
213. House Robber II
Medium
8.3K
120
Companies
You are a professional robber planning to rob houses along a street.
Each house has a certain amount of money stashed. All houses at this place are arranged in a circle.
That means the first house is the neighbor of the last one. Meanwhile, adjacent houses have a security system connected,
and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given an integer array nums representing the amount of money of each house,
return the maximum amount of money you can rob tonight without alerting the police.

Example 1:

Input: nums = [2,3,2]
Output: 3
Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2), because they are adjacent houses.
Example 2:

Input: nums = [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.
Example 3:

Input: nums = [0]
Output: 0

Constraints:

1 <= nums.length <= 100
0 <= nums[i] <= 1000


*/


class Solution {
    public int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int[] nums1 = Arrays.copyOfRange(nums, 0, nums.length - 1);
        int[] nums2 = Arrays.copyOfRange(nums, 1, nums.length);
        int dp1[]=new int[nums1.length+1];
        for(int i=0;i<=nums1.length;i++)
        {
            dp1[i]=-1;
        }
        int dp2[]=new int[nums2.length+1];
        for(int i=0;i<=nums2.length;i++)
        {
            dp2[i]=-1;
        }
        //return Math.max(solveRecursion(nums1,nums.length-2,0), solveRecursion(nums2,nums.length-2,0));
       //return Math.max(solveMem(nums1,nums1.length-1,0,dp1), solveMem(nums2,nums2.length-1,0,dp2)); 
        //return Math.max(solveDP(nums1), solveDP(nums2));
        return Math.max(OptimizedDP(nums1), OptimizedDP(nums2));

    }

//recursion
  public int solveRecursion(int nums[],int i,int max)
    {
        if(i<0)
        {
            return 0;
        }
        int first=nums[i]+solveRecursion(nums,i-2,max);
        int second=0+solveRecursion(nums,i-1,max);
        max=Math.max(first,second);
        return max;
    }
//Memoization
      public int solveMem(int nums[],int i,int max,int dp[])
    {
        if(i<0)
        {
            return 0;
        }
        if(dp[i]!=-1)
        {
            return dp[i];
        }
        int first=nums[i]+solveMem(nums,i-2,max,dp);
        int second=0+solveMem(nums,i-1,max,dp);
        dp[i]=Math.max(first,second);
        return dp[i];
    }
//DP
    public int solveDP(int nums[])
    {
        int n=nums.length;
        int dp[]=new int[n+1];
        int max=0;
       
        dp[0]=nums[0];
        for(int i=1;i<nums.length;i++)
        {
            int fs=nums[i];
            if(i>1) fs=nums[i]+dp[i-2];
            int ss=0+dp[i-1];
            dp[i]=Math.max(fs,ss);
        }
        return dp[nums.length-1];
    }
//Optimized DP
    public int OptimizedDP(int nums[])
    {
         int n=nums.length;
        int prev2=0;
        int prev=nums[0]; 
        int max=0;
       
        
        for(int i=1;i<nums.length;i++)
        {
            int fs=nums[i];
            if(i>1) fs=nums[i]+prev2;
            int ss=0+prev;
            int curri=Math.max(fs,ss);
            prev2=prev;
            prev=curri;
        }
        return prev;
    }
}
