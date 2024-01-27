/*
 
368. Largest Divisible Subset
Medium
4.6K
178
Companies
Given a set of distinct positive integers nums, return the largest subset answer such that every pair (answer[i], answer[j]) of elements in this subset satisfies:

answer[i] % answer[j] == 0, or
answer[j] % answer[i] == 0
If there are multiple solutions, return any of them.

 

Example 1:

Input: nums = [1,2,3]
Output: [1,2]
Explanation: [1,3] is also accepted.
Example 2:

Input: nums = [1,2,4,8]
Output: [1,2,4,8]
 

Constraints:

1 <= nums.length <= 1000
1 <= nums[i] <= 2 * 109
All the integers in nums are unique.
 */


class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        
        ArrayList<Integer>al=new ArrayList<Integer>();
        for(int i=0;i<nums.length;i++)
        {
            al.add(nums[i]);
        }
        return divisibleSet(al);
    }

    public static List<Integer> divisibleSet(List<Integer> arr) {
        int n = arr.size();
    
        //sort the array
        Collections.sort(arr);
        List<Integer> dp = new ArrayList<Integer>();
        List<Integer> hash = new ArrayList<Integer>();
    
        for (int i = 0; i <= n-1; i++) {
            dp.add(1);
            hash.add(i); // initializing with current index
            for (int prev_index = 0; prev_index <= i-1; prev_index++) {
                if (arr.get(i) % arr.get(prev_index) == 0 && 1 + dp.get(prev_index) > dp.get(i)) {
                    dp.set(i, 1 + dp.get(prev_index));
                    hash.set(i, prev_index);
                }
            }
        }
    
        int ans = -1;
        int lastIndex = -1;
    
        for (int i = 0; i <= n-1; i++) {
            if (dp.get(i) > ans) {
                ans = dp.get(i);
                lastIndex = i;
            }
        }
    
        List<Integer> temp = new ArrayList<Integer>();
        temp.add(arr.get(lastIndex));
    
        while (hash.get(lastIndex) != lastIndex) { // till not reach the initialization value
            lastIndex = hash.get(lastIndex);
            temp.add(arr.get(lastIndex));    
        }
    
        // reverse the array 
        Collections.reverse(temp);

        return temp;
    }
}