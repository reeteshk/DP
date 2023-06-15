/* 

518. Coin Change II
Medium
7.2K
130
Companies
You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.

Return the number of combinations that make up that amount. If that amount of money cannot be made up by any combination of the coins, return 0.

You may assume that you have an infinite number of each kind of coin.

The answer is guaranteed to fit into a signed 32-bit integer.

 

Example 1:

Input: amount = 5, coins = [1,2,5]
Output: 4
Explanation: there are four ways to make up the amount:
5=5
5=2+2+1
5=2+1+1+1
5=1+1+1+1+1
Example 2:

Input: amount = 3, coins = [2]
Output: 0
Explanation: the amount of 3 cannot be made up just with coins of 2.
Example 3:

Input: amount = 10, coins = [10]
Output: 1
 

Constraints:

1 <= coins.length <= 300
1 <= coins[i] <= 5000
All the values of coins are unique.
0 <= amount <= 5000
Accepted
412.1K
Submissions
678.2K
Acceptance Rate
60.8%
Seen this question in a real interview before?
1/4
*/

class Solution {
    public int change(int amount, int[] coins) {
        int dp[][]=new int[coins.length][amount+1];
        for(int row[]:dp)
        {
            Arrays.fill(row,-1);
        }
        // int ans = coinchangeRec(coins,amount,coins.length-1);
        // return ans;
      //  return coinchangeMemoization(coins,amount,coins.length-1,dp);
      //  return coinchangeDP(coins,amount);
      return coinchangeDPOptimized(coins,amount);
         
  
    }
    
//Recursive Approach 
 public int coinchangeRec(int coins[],int amount,int n)
    {
        if(n==0)
        {
            if(amount%coins[n]==0) return 1;
            else return 0;

        }

        int nottake=0+coinchangeRec(coins,amount,n-1);
        int take= 0;

        if(coins[n]<=amount)
        {
            take=coinchangeRec(coins,amount-coins[n],n);
        }
        return take+nottake;
    }

//Memoization Approach
public int coinchangeMemoization(int coins[],int amount,int n,int dp[][])
    {
        if(n==0)
        {
            if(amount%coins[n]==0) return 1;
            else return 0;
        }
        if(dp[n][amount]!=-1)
        {
            return dp[n][amount];
        }
        int nottake=coinchangeMemoization(coins,amount,n-1,dp);
        int take= 0;

        if(coins[n]<=amount)
        {
            take=coinchangeMemoization(coins,amount-coins[n],n,dp);
        }
        return dp[n][amount]=take+nottake;
    }

//Dynamic Programming Approach
public int coinchangeDP(int coins[],int amount)
    {
        int n=coins.length;
        int dp[][]=new int[n][amount+1];
        for(int A=0;A<=amount;A++)
        {
            if(A%coins[0]==0) dp[0][A]=1;
            //else condition is automatically fulfilled
        }
        for(int i=1;i<n;i++)
        {
            for(int A=0;A<=amount;A++)
            {
                int nottake=0+dp[i-1][A];
                 int take= 0;

                    if(coins[i]<=A)
             {
                          take=dp[i][A-coins[i]];
            }
                dp[i][A]=take+nottake;
            }

        }

        return dp[n-1][amount];
    }


//Dynamic Programming Optimized Approach
 public int coinchangeDPOptimized(int coins[],int amount)
    {
        int n=coins.length;
        int prev[]=new int[amount+1];
        int curr[]=new int[amount+1];

      //  int dp[][]=new int[n][amount+1];
        for(int A=0;A<=amount;A++)
        {
            if(A%coins[0]==0) prev[A]=1;
            //else condition will be satisfied automatically
        }
        for(int i=1;i<n;i++)
        {
            for(int A=0;A<=amount;A++)
            {
                int nottake=0+prev[A];
                 int take= 0;

                    if(coins[i]<=A)
             {
                          take=curr[A-coins[i]];
            }
                curr[A] =(take+nottake);
            }
            prev=curr;


        }
        return prev[amount];
    }