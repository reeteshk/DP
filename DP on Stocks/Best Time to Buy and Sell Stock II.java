/*
 
122. Best Time to Buy and Sell Stock II
Medium
11.5K
2.6K
Companies
You are given an integer array prices where prices[i] is the price of a given stock on the ith day.

On each day, you may decide to buy and/or sell the stock. You can only hold at most one share of the stock at any time. However, you can buy it then immediately sell it on the same day.

Find and return the maximum profit you can achieve.

 

Example 1:

Input: prices = [7,1,5,3,6,4]
Output: 7
Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
Total profit is 4 + 3 = 7.
Example 2:

Input: prices = [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
Total profit is 4.
Example 3:

Input: prices = [7,6,4,3,1]
Output: 0
Explanation: There is no way to make a positive profit, so we never buy the stock to achieve the maximum profit of 0.
 

Constraints:

1 <= prices.length <= 3 * 104
0 <= prices[i] <= 104
 */

class Solution {
    public int maxProfit(int[] prices) {
        
        //return (int)maxProfitRecursion(prices,1,0);
        // int dp[][]=new int[prices.length][2];
        // for(int row[]:dp)
        // {
        //     Arrays.fill(row,-1);
        // }
        // return maxProfitMemoization(prices,1,0,dp);
        //return maxProfitDP(prices);
        return maxProfitDPOptimized(prices);
    }

    //Recursive Approach
    public static long maxProfitRecursion(int prices[],int buy,int i)
    {
        if(i==prices.length)
        {
            return 0;
        }
        long profit=0;
        if(buy==1)
        {
            profit=Math.max(-prices[i]+maxProfitRecursion(prices,0,i+1),0+maxProfitRecursion(prices,1,i+1));
        }
        else
        {
            profit=Math.max(prices[i]+maxProfitRecursion(prices,1,i+1),0+maxProfitRecursion(prices,0,i+1));
        }
        return profit;
    }

    //Memoization Approach
    public static int maxProfitMemoization(int prices[],int buy,int i,int dp[][])
    {
        if(i==prices.length)
        {
            return 0;
        }
        int profit=0;
        if(dp[i][buy]!=-1)
        {
            return dp[i][buy];
        }
        if(buy==1)
        {
            profit=Math.max(-prices[i]+maxProfitMemoization(prices,0,i+1,dp),0+maxProfitMemoization(prices,1,i+1,dp));
        }
        else
        {
            profit=Math.max(prices[i]+maxProfitMemoization(prices,1,i+1,dp),0+maxProfitMemoization(prices,0,i+1,dp));
        }
        return dp[i][buy]=profit;
    }

    //Using Dynamic programming approach
    public static int maxProfitDP(int prices[]){
        int dp[][]=new int[prices.length+1][2];
        int N=prices.length;
        dp[N][1]=0;
        dp[N][0]=0;
        
        for(int i=N-1;i>=0;i--)
        {
            
            for(int buy=0;buy<=1;buy++)
            {
                int profit=0;
                 if(buy==1)
                        {
                            profit=Math.max(-prices[i]+dp[i+1][0],dp[i+1][1]);
                        }
                        else
                        {
                            profit=Math.max(prices[i]+dp[i+1][1],dp[i+1][0]);
                        }
                 dp[i][buy]=profit;
            }
        }
        return dp[0][1];
    }

    //Using Optimized Dynamic Programming
    public static int maxProfitDPOptimized(int prices[]){
        int ahead[]=new int[2];
        int N=prices.length;
        ahead[1]=0;
        ahead[0]=0;
        
        for(int i=N-1;i>=0;i--)
        {
            int curr[]=new int[2];
            for(int buy=0;buy<=1;buy++)
            {
                int profit=0;
                 if(buy==1)
                        {
                            profit=Math.max(-prices[i]+ahead[0],ahead[1]);
                        }
                        else
                        {
                            profit=Math.max(prices[i]+ahead[1],ahead[0]);
                        }
                 curr[buy]=profit;
            }
            ahead=curr;
        }
        return ahead[1];
    }
}