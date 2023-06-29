/*
 
188. Best Time to Buy and Sell Stock IV
Hard
6.6K
194
Companies
You are given an integer array prices where prices[i] is the price of a given stock on the ith day, and an integer k.

Find the maximum profit you can achieve. You may complete at most k transactions: i.e. you may buy at most k times and sell at most k times.

Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).

 

Example 1:

Input: k = 2, prices = [2,4,1]
Output: 2
Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
Example 2:

Input: k = 2, prices = [3,2,6,5,0,3]
Output: 7
Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4. Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
 

Constraints:

1 <= k <= 100
1 <= prices.length <= 1000
0 <= prices[i] <= 1000

 */


class Solution {
    public int maxProfit(int k, int[] prices) {
          // return maxProfitRecursion(prices,1,0,k);
            // int dp[][][]=new int[prices.length][2][k+1];
            // for(int row[][]:dp)
            //     {
            //         for(int r[]:row)
            //             {
            //                 Arrays.fill(r,-1);
            //             }
            //     }
            //  return maxProfitMemoization(prices,1,0,dp,k);
         //return maxProfitDP(prices,k);
            return maxProfitDPOptimized(prices,k);
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
        public static int maxProfitDP(int prices[],int k){
            int dp[][][]=new int[prices.length+1][2][k+1];
            int N=prices.length;
        


            
            for(int i=N-1;i>=0;i--)
            {
                
                for(int buy=0;buy<=1;buy++)
                {
                    for(int cap=1;cap<=k;cap++)
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
            return dp[0][1][k];
        }
        public static int maxProfitDPOptimized(int prices[],int k){
            int ahead[][]=new int[2][k+1];
            int curr[][]=new int[2][k+1];
            int N=prices.length;
            
            for(int i=N-1;i>=0;i--)
            {
                
                for(int buy=0;buy<=1;buy++)
                {
                    for(int cap=1;cap<=k;cap++)
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
        
            return ahead[1][k];
        }
    }
