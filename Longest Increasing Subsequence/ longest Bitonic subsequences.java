/*
 Longest Bitonic subsequence
MediumAccuracy: 47.34%Submissions: 50K+Points: 4
Sharpen up your programming skills, participate in coding contests & explore high-paying jobs

Given an array of positive integers. Find the maximum length of Bitonic subsequence. 
A subsequence of array is called Bitonic if it is first strictly increasing, then strictly decreasing.
 

Example 1:

Input: nums = [1, 2, 5, 3, 2]
Output: 5
Explanation: The sequence {1, 2, 5} is
increasing and the sequence {3, 2} is 
decreasing so merging both we will get 
length 5.
Example 2:

Input: nums = [1, 11, 2, 10, 4, 5, 2, 1]
Output: 6
Explanation: The bitonic sequence 
{1, 2, 10, 4, 2, 1} has length 6.
 

Your Task:
You don't need to read or print anything. Your task is to complete the function LongestBitonicSequence() which takes the array nums[] as input parameter and returns the maximum length of bitonic subsequence.
 

Expected Time Complexity: O(n2)
Expected Space Complexity: O(n)
 

Constraints:
1 ≤ length of array ≤ 103
1 ≤ arr[i] ≤ 106

 */

class Solution
{
    public int LongestBitonicSequence(int[] nums)
    {
        // Code here
        return longestBitonicSequence(nums,nums.length);
    }
    
    static int longestBitonicSequence(int[] arr, int n){
    
    int[] dp1= new int[n];
    int[] dp2= new int[n];
    
    Arrays.fill(dp1,1);
    Arrays.fill(dp2,1);
    
    for(int i=0; i<=n-1; i++){
        for(int prev_index = 0; prev_index <=i-1; prev_index ++){
            
            if(arr[prev_index]<arr[i]){
                dp1[i] = Math.max(dp1[i], 1 + dp1[prev_index]);
            }
        }
    }
    
    // reverse the direction of nested loops
    for(int i=n-1; i>=0; i--){
        for(int prev_index = n-1; prev_index >i; prev_index --){
            
            if(arr[prev_index]<arr[i]){
                dp2[i] = Math.max(dp2[i], 1 + dp2[prev_index]);
            }
        }
    }
    
    int maxi = -1;
    
    for(int i=0; i<n; i++){
        maxi = Math.max(maxi, dp1[i] + dp2[i] - 1);
    }
    
    return maxi;
    
}
}