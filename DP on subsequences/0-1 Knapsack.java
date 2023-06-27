public class Solution 
{ 
    //Function to return max value that can be put in knapsack of capacity W.
    static int knapSack(int W, int wt[], int val[], int n) 
    { 
         // your code here 
         int dp[][]=new int[n][W+1];
         for(int ar[]:dp)
         {
             Arrays.fill(ar,-1);
         }
          //return RecursiveKnapSack(W,wt,val,n-1);
          //return MemoizationKnapSack(W,wt,val,n-1,dp);
         // return DPKnapsack(W,wt,val,n);
          return DPKnapsackSpaceOptimized(W,wt,val,n);
    }

    //Recursive Approach
    public static int RecursiveKnapSack(int W, int wt[], int val[], int n)
    {
        if(n==0)
        {
            if(wt[0]<=W) return val[0];
            else return 0;
        }
        int nottake=0+RecursiveKnapSack(W,wt,val,n-1);
        int take=Integer.MIN_VALUE;
        if(wt[n]<=W)
        {
            take=val[n]+RecursiveKnapSack(W-wt[n],wt,val,n-1);
        }
        return Math.max(take,nottake);
    }
    
    //Memoization Approach
     public static int MemoizationKnapSack(int W, int wt[], int val[], int n,int dp[][])
    {
        if(n==0)
        {
            if(wt[0]<=W) return val[0];
            else return 0;
        }
        if(dp[n][W]!=-1) return dp[n][W];
        int nottake=0+MemoizationKnapSack(W,wt,val,n-1,dp);
        int take=Integer.MIN_VALUE;
        if(wt[n]<=W)
        {
            take=val[n]+MemoizationKnapSack(W-wt[n],wt,val,n-1,dp);
        }
        return dp[n][W]=Math.max(take,nottake);
    }

    //DP Approach
    public static int DPKnapsack(int W, int wt[], int val[], int n)
    {
        int dp[][]=new int[n][W+1];
        
        for(int i=wt[0];i<=W;i++)
        {
            dp[0][i]=val[0];
        }
        for(int i=1;i<n;i++)
        {
            for(int w=0;w<=W;w++)
            {
                int nottake=0+dp[i-1][w];
                int take=Integer.MIN_VALUE;
                if(wt[i]<=w)
                {
                    take=val[i]+dp[i-1][w-wt[i]];
                }
                dp[i][w]=Math.max(take,nottake);
            }
        }
        return dp[n-1][W];
        
    }


    //DP Approach Space Optimized
    public static int DPKnapsackSpaceOptimized(int W, int wt[], int val[], int n)
    {
        int dp[]=new int[W+1];
        
        for(int i=wt[0];i<=W;i++)
        {
            dp[i]=val[0];
        }
        for(int i=1;i<n;i++)
        {   
            int curr[]=new int[W+1];
            for(int w=0;w<=W;w++)
            {
                int nottake=0+dp[w];
                int take=Integer.MIN_VALUE;
                if(wt[i]<=w)
                {
                    take=val[i]+dp[w-wt[i]];
                }
                curr[w]=Math.max(take,nottake);
            }
            dp=curr;
        }
        return dp[W];
        
    }
    
}
 