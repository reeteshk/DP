/*

309. Best Time to Buy and Sell Stock with Cooldown
Medium
8.5K
283
Companies
You are given an array prices where prices[i] is the price of a given stock on the ith day.

Find the maximum profit you can achieve. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times) with the following restrictions:

After you sell your stock, you cannot buy stock on the next day (i.e., cooldown one day).
Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).

 

Example 1:

Input: prices = [1,2,3,0,2]
Output: 3
Explanation: transactions = [buy, sell, cooldown, buy, sell]
Example 2:

Input: prices = [1]
Output: 0
 

Constraints:

1 <= prices.length <= 5000
0 <= prices[i] <= 1000

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
        return maxProfitDP(prices);
        //return maxProfitDPOptimized(prices);
    }
public static long maxProfitRecursion(int prices[],int buy,int i)
    {
        if(i>=prices.length) 
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
            profit=Math.max(prices[i]+maxProfitRecursion(prices,1,i+2),0+maxProfitRecursion(prices,0,i+1));
        }
        return profit;
    }
    public static int maxProfitMemoization(int prices[],int buy,int i,int dp[][])
    {
        if(i>=prices.length)
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
            profit=Math.max(prices[i]+maxProfitMemoization(prices,1,i+2,dp),0+maxProfitMemoization(prices,0,i+1,dp));
        }
        return dp[i][buy]=profit;
    }
    public static int maxProfitDP(int prices[]){
        int dp[][]=new int[prices.length+2][2];
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
                            profit=Math.max(prices[i]+dp[i+2][1],dp[i+1][0]);
                        }
                 dp[i][buy]=profit;
            }
        }
        return dp[0][1];
    }
    //Space optimization is not possible as we have to take care of the three diffrent row, DP solution is sufficient
}