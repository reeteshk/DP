/* 
300. Longest Increasing Subsequence
Medium
17.7K
333
Companies
Given an integer array nums, return the length of the longest strictly increasing 
subsequence
.

 

Example 1:

Input: nums = [10,9,2,5,3,7,101,18]
Output: 4
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
Example 2:

Input: nums = [0,1,0,3,2,3]
Output: 4
Example 3:

Input: nums = [7,7,7,7,7,7,7]
Output: 1
 

Constraints:

1 <= nums.length <= 2500
-104 <= nums[i] <= 104

*/


class Solution {
    public int lengthOfLIS(int[] nums) {
        
        // int dp[][]=new int[nums.length][nums.length+1];
        // for(int  arr[]:dp)
        // {
        //     Arrays.fill(arr,-1);
        // }
        
        //return lengthOfLISRecursive(nums,0,-1);
       
       // return lengthOfLISMemoization(nums,0,-1,dp);
       
       //return lengthOfLISDP(nums);
       
       //return lengthOfLISDPOptimization(nums);
       
       //return lengthOfLISDPMoreOptimized(nums);
       
       //return printingLIS(nums);

        return LISUsingBinarySearch(nums);
       
    }
    public static int lengthOfLISRecursive(int nums[],int i,int prev)
    {
        if(i==nums.length)
        {
            return 0;
        }
        int nottake=lengthOfLISRecursive(nums,i+1,prev);
        int take=0;
        if(prev==-1 || nums[i]>nums[prev])
        {
            take=1+lengthOfLISRecursive(nums,i+1,-1);
        }
        return Math.max(take,nottake);
    }
    public static int lengthOfLISMemoization(int nums[],int i,int prev,int dp[][])
    {
        if(i==nums.length)
        {
            return 0;
        }
        if(dp[i][prev+1]!=-1) return dp[i][prev+1];
        int nottake=lengthOfLISMemoization(nums,i+1,prev,dp);
        int take=0;
        if(prev==-1 || nums[i]>nums[prev])
        {
            take=1+lengthOfLISMemoization(nums,i+1,i,dp);
        }
        return dp[i][prev+1]=Math.max(take,nottake);
    }
    public static int lengthOfLISDP(int nums[])
    {
        int n=nums.length;
        int dp[][]=new int[n+1][n+1];

        for(int i=n-1;i>=0;i--)
        {
            for(int prev=i-1;prev>=-1;prev--)
            {
                int nottake=dp[i+1][prev+1];
                int take=0;
                if(prev==-1 || nums[i]>nums[prev])
            {
                take=1+dp[i+1][i+1];
            }
                dp[i][prev+1]=Math.max(take,nottake);
        }
        }
        return dp[0][-1+1];
    }
     public static int lengthOfLISDPOptimization(int nums[])
    {
        int n=nums.length;
        int dp[]=new int[n+1];
        int curr[]=new int[n+1];

        for(int i=n-1;i>=0;i--)
        {
            for(int prev=i-1;prev>=-1;prev--)
            {
                int nottake=dp[prev+1];
                int take=0;
                if(prev==-1 || nums[i]>nums[prev])
            {
                take=1+dp[i+1];
            }
                curr[prev+1]=Math.max(take,nottake);
        }
        dp=curr;
        }
        return dp[-1+1];
    }
    public static int lengthOfLISDPMoreOptimized(int nums[])
    {
        
        int n=nums.length;
        int dp[]=new int[n];
        Arrays.fill(dp,1);
        
        for(int i=0; i<=n-1; i++){
            for(int prev_index = 0; prev_index <=i-1; prev_index ++){
                
                if(nums[prev_index]<nums[i]){
                    dp[i] = Math.max(dp[i], 1 + dp[prev_index]);
                }
            }
        }
        int ans=-1;
        for(int i=0;i<=n-1;i++)
        {
            ans=Math.max(ans,dp[i]);
        }
        return ans;
    }

    public static int printingLIS(int nums[])
    {
        int n=nums.length;
        int dp[]=new int[n];
        Arrays.fill(dp,1);
        int maxi=1;
        int hash[]=new int[n];
        int lastindex=-1;
        for(int i=0;i<n;i++)
        {
            hash[i]=i;
        }
        for(int i=0;i<n;i++)
        {
            for(int prev=0;prev<i;prev++)
            {
                if(nums[prev]<nums[i] && 1+dp[prev]>dp[i])
                {
                    dp[i]=1+dp[prev];
                    hash[i]=prev;
                }
            }
            if(dp[i]>maxi)
            {
                maxi=dp[i];
                lastindex=i;
            }
        }
    int ans = -1;
    int lastIndex =-1;
    
    for(int i=0; i<=n-1; i++){
        if(dp[i]> ans){
            ans = dp[i];
            lastIndex = i;
        }
    }
    
    ArrayList<Integer> temp=new ArrayList<>();
    temp.add(nums[lastIndex]);
    
    while(hash[lastIndex] != lastIndex){ // till not reach the initialization value
        lastIndex = hash[lastIndex];
        temp.add(nums[lastIndex]);    
    }
    
    // reverse the array 
    
    System.out.print("The subsequence elements are ");
    
    for(int i=temp.size()-1; i>=0; i--){
        System.out.print(temp.get(i)+" ");
    }
    System.out.println();
    
    return ans;
    }


    public static int LISUsingBinarySearch(int nums[])
    {
        int[] a=new int[nums.length];int k=0;
        a[k]=nums[0];
        for(int i=1;i<nums.length;i++){
            if(nums[i]<a[k]){
                int lb=0,ub=k;
                while(lb<=ub){
                    int mid=(lb+ub)/2;
                    if(a[mid]<nums[i])
                        lb=mid+1;
                    else
                        ub=mid-1;
                }
                a[lb]=nums[i];
            }
            else if(a[k]!=nums[i]){
                k++;
                a[k]=nums[i];
            } 
        }
        return k+1;

    }
}