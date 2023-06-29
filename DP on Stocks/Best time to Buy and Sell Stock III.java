/*
 
123. Best Time to Buy and Sell Stock III
Hard
8.4K
159
Companies
You are given an array prices where prices[i] is the price of a given stock on the ith day.

Find the maximum profit you can achieve. You may complete at most two transactions.

Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).

 

Example 1:

Input: prices = [3,3,5,0,0,3,1,4]
Output: 6
Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
Example 2:

Input: prices = [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are engaging multiple transactions at the same time. You must sell before buying again.
Example 3:

Input: prices = [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.
 

Constraints:

1 <= prices.length <= 105
0 <= prices[i] <= 105

 */

class Solution {
    public int maxProfit(int[] prices) {
        
    // return maxProfitRecursion(prices,1,0,2);
        // int dp[][][]=new int[prices.length][2][3];
        // for(int row[][]:dp)
        //     {
        //         for(int r[]:row)
        //             {
        //                 Arrays.fill(r,-1);
        //             }
        //     }
        //  return maxProfitMemoization(prices,1,0,dp,2);
    // return maxProfitDP(prices);
        return maxProfitDPOptimized(prices);
    }


    public static int maxProfitRecursion(int prices[],int buy,int i,int cap)
    {
        if(i==prices.length) 
        {
            return 0;
        }
        if(cap==0) return 0;
        int profit=0;
        if(buy==1)
        {
            profit=Math.max(-prices[i]+maxProfitRecursion(prices,0,i+1,cap),0+maxProfitRecursion(prices,1,i+1,cap));
        }
        else
        {
            profit=Math.max(prices[i]+maxProfitRecursion(prices,1,i+1,cap-1),0+maxProfitRecursion(prices,0,i+1,cap));
        }
        return profit;
    }
    public static int maxProfitMemoization(int prices[],int buy,int i,int dp[][][],int cap)
    {
        if(i==prices.length)
        {
            return 0;
        }
        if(cap==0)
        {
            return 0;
        }
        int profit=0;
        if(dp[i][buy][cap]!=-1)
        {
            return dp[i][buy][cap];
        }
        if(buy==1)
        {
            profit=Math.max(-prices[i]+maxProfitMemoization(prices,0,i+1,dp,cap),0+maxProfitMemoization(prices,1,i+1,dp,cap));
        }
        else
        {
            profit=Math.max(prices[i]+maxProfitMemoization(prices,1,i+1,dp,cap-1),0+maxProfitMemoization(prices,0,i+1,dp,cap));
        }
        return dp[i][buy][cap]=profit;
    }
    public static int maxProfitDP(int prices[]){
        int dp[][][]=new int[prices.length+1][2][3];
        int N=prices.length;
    


        
        for(int i=N-1;i>=0;i--)
        {
            
            for(int buy=0;buy<=1;buy++)
            {
                for(int cap=1;cap<=2;cap++)
                {

                
                int profit=0;
                if(buy==1)
                        {
                            profit=Math.max(-prices[i]+dp[i+1][0][cap],dp[i+1][1][cap]);
                        }
                        else
                        {
                            profit=Math.max(prices[i]+dp[i+1][1][cap-1],dp[i+1][0][cap]);
                        }
                dp[i][buy][cap]=profit;
                }
            }
        }
        return dp[0][1][2];
    }
    public static int maxProfitDPOptimized(int prices[]){
        int ahead[][]=new int[2][3];
        int curr[][]=new int[2][3];
        int N=prices.length;
        
        for(int i=N-1;i>=0;i--)
        {
            
            for(int buy=0;buy<=1;buy++)
            {
                for(int cap=1;cap<=2;cap++)
                {

                
                int profit=0;
                if(buy==1)
                        {
                            profit=Math.max(-prices[i]+ahead[0][cap],ahead[1][cap]);
                        }
                        else
                        {
                            profit=Math.max(prices[i]+ahead[1][cap-1],ahead[0][cap]);
                        }
                curr[buy][cap]=profit;
                }
                ahead=curr;
            }
        }
    
        return ahead[1][2];
    }
}