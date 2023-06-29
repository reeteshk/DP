
/*

 714. Best Time to Buy and Sell Stock with Transaction Fee
Medium
6.4K
174
Companies
You are given an array prices where prices[i] is the price of a given stock on the ith day, and an integer fee representing a transaction fee.

Find the maximum profit you can achieve. You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction.

Note:

You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
The transaction fee is only charged once for each stock purchase and sale.
 

Example 1:

Input: prices = [1,3,2,8,4,9], fee = 2
Output: 8
Explanation: The maximum profit can be achieved by:
- Buying at prices[0] = 1
- Selling at prices[3] = 8
- Buying at prices[4] = 4
- Selling at prices[5] = 9
The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
Example 2:

Input: prices = [1,3,7,5,10,3], fee = 3
Output: 6
 

Constraints:

1 <= prices.length <= 5 * 104
1 <= prices[i] < 5 * 104
0 <= fee < 5 * 104

 */

class Solution {
    public int maxProfit(int[] prices, int fee) {
         //return (int)maxProfitRecursion(prices,1,0,fee);
        // int dp[][]=new int[prices.length][2];
        // for(int row[]:dp)
        // {
        //     Arrays.fill(row,-1);
        // }
        // return maxProfitMemoization(prices,1,0,dp,fee);
        //return maxProfitDP(prices,fee);
        return maxProfitDPOptimized(prices,fee);
    }
 public static long maxProfitRecursion(int prices[],int buy,int i,int fee)
    {
        if(i==prices.length) 
        {
            return 0;
        }
        long profit=0;
        if(buy==1)
        {
            profit=Math.max(-prices[i]+maxProfitRecursion(prices,0,i+1,fee),0+maxProfitRecursion(prices,1,i+1,fee));
        }
        else
        {
            profit=Math.max(prices[i]+maxProfitRecursion(prices,1,i+1,fee)-fee,0+maxProfitRecursion(prices,0,i+1,fee));
        }
        return profit;
    }
    public static int maxProfitMemoization(int prices[],int buy,int i,int dp[][],int fee)
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
            profit=Math.max(-prices[i]+maxProfitMemoization(prices,0,i+1,dp,fee),0+maxProfitMemoization(prices,1,i+1,dp,fee));
        }
        else
        {
            profit=Math.max(prices[i]+maxProfitMemoization(prices,1,i+1,dp,fee)-fee,0+maxProfitMemoization(prices,0,i+1,dp,fee));
        }
        return dp[i][buy]=profit;
    }
    public static int maxProfitDP(int prices[],int fee){
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
                            profit=Math.max(prices[i]+dp[i+1][1]-fee,dp[i+1][0]);
                        }
                 dp[i][buy]=profit;
            }
        }
        return dp[0][1];
    }
    public static int maxProfitDPOptimized(int prices[],int fee){
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
                            profit=Math.max(prices[i]+ahead[1]-fee,ahead[0]);
                        }
                 curr[buy]=profit;
            }
            ahead=curr;
        }
        return ahead[1];
    }
}