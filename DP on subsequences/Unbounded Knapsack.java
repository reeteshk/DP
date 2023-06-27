class Solution{
    static int knapSack(int N, int W, int val[], int wt[])
    {
        // code here
        int dp[][]=new int[N][W+1];
        for(int row[]:dp)
        {
            Arrays.fill(row,-1);
        }
        
       // return Knapsackrecursive(N-1,W,val,wt);
        // return KnapsackMemoization(N-1,W,val,wt,dp);
       // return KnapsackDP(N,W,val,wt);
       return KnapsackDPOptimized(N,W,val,wt);
       
       
        
    }

    //Recursive Approach
    public static int Knapsackrecursive(int N,int W,int val[],int wt[])
    {
        if(N==0)
        {
                return ((int)(W/wt[0]))*val[0];
          
        }
        int nottake=Knapsackrecursive(N-1,W,val,wt);
        int take=0;
        if(W>=wt[N])
        {
            take=val[N]+Knapsackrecursive(N,W-wt[N],val,wt);
        }
        return Math.max(take,nottake);
    }

    //Memoization Approach
     public static int KnapsackMemoization(int N,int W,int val[],int wt[],int dp[][])
    {
        if(N==0)
        {
                return ((int)(W/wt[0]))*val[0];
          
        }
        if(dp[N][W]!=-1) return dp[N][W];
        int nottake=KnapsackMemoization(N-1,W,val,wt,dp);
        int take=0;
        if(W>=wt[N])
        {
            take=val[N]+KnapsackMemoization(N,W-wt[N],val,wt,dp);
        }
        return dp[N][W]=Math.max(take,nottake);
    }

    //DP Approach
    public static int KnapsackDP(int N,int W,int val[],int wt[])
    {
         int dp[][]=new int[N][W+1];
         
         for(int i=0;i<=W;i++)
         {
             dp[0][i]=((int)(i/wt[0]))*val[0];
         }
         
         for(int i=1;i<N;i++)
         {
             for(int j=0;j<=W;j++)
             {
                 int nottake=dp[i-1][j];
                int take=0;
                if(j>=wt[i])
                {
                    take=val[i]+dp[i][j-wt[i]];
                }
                 dp[i][j]=Math.max(take,nottake);
             }
         }
         return dp[N-1][W];
    }


    //Space Optimized DP
     public static int KnapsackDPOptimized(int N,int W,int val[],int wt[])
    {
         int dp[]=new int[W+1];
         
         for(int i=0;i<=W;i++)
         {
             dp[i]=((int)(i/wt[0]))*val[0];
         }
         
         for(int i=1;i<N;i++)
         {
             int curr[]=new int[W+1];
             for(int j=0;j<=W;j++)
             {
                 int nottake=dp[j];
                int take=0;
                if(j>=wt[i])
                {
                    take=val[i]+curr[j-wt[i]];
                }
                 curr[j]=Math.max(take,nottake);
             }
             dp=curr;
         }
         return dp[W];
    }
}