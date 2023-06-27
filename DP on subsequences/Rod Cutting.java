class Solution{
    public int cutRod(int price[], int n) {
        //code here
        int dp[][]=new int[n][n+1];
        for(int arr[]:dp)
        {
            Arrays.fill(arr,-1);
        }
        //return cutRodRecursive(price,n-1,price.length);
        //return cutRodMemoization(price,n-1,price.length,dp);
        //return cutRodDP(price,n);
        return cutRodDPOptimized(price,n);
    }

    //Recursive Approach
    public static int cutRodRecursive(int price[],int n,int N)
    {
        if(n==0)
        {
            return N*price[n];
        }
        int nottake=cutRodRecursive(price,n-1,N);
        int take=Integer.MIN_VALUE;
        int rodlength=n+1;
        if(N>=rodlength)
        {
            take=price[n]+cutRodRecursive(price,n,N-rodlength);
        }
        return Math.max(take,nottake);
    }
    
    //Memoization Approach
    public static int cutRodMemoization(int price[],int n,int N,int dp[][])
    {
        if(n==0)
        {
            return N*price[n];
        }
        if(dp[n][N]!=-1) return dp[n][N];
        int nottake=cutRodMemoization(price,n-1,N,dp);
        int take=Integer.MIN_VALUE;
        int rodlength=n+1;
        if(N>=rodlength)
        {
            take=price[n]+cutRodMemoization(price,n,N-rodlength,dp);
        }
        return dp[n][N]=Math.max(take,nottake);
    }

    //DP Approach
     public static int cutRodDP(int price[],int n)
    {
        int N=n+1;
       int dp[][]=new int[n][n+1];
       for(int i=0;i<N;i++)
       {
           dp[0][i]=i*price[0];
       }
       for(int i=1;i<n;i++)
       {
           for(int j=0;j<n+1;j++)
           {
                    int nottake=dp[i-1][j];
                    int take=Integer.MIN_VALUE;
                    int rodlength=i+1;
                    if(j>=rodlength)
                    {
                        take=price[i]+dp[i][j-rodlength];
                    }
                 dp[i][j]=Math.max(take,nottake);
           }
       }
       return dp[n-1][n];
    }

    //DP Approach Space Optimized
     public static int cutRodDPOptimized(int price[],int n)
    {
        int N=n+1;
       int dp[]=new int[n+1];
       for(int i=0;i<N;i++)
       {
           dp[i]=i*price[0];
       }
       for(int i=1;i<n;i++)
       {
           int curr[]=new int[n+1];
           for(int j=0;j<n+1;j++)
           {
                    int nottake=dp[j];
                    int take=Integer.MIN_VALUE;
                    int rodlength=i+1;
                    if(j>=rodlength)
                    {
                        take=price[i]+curr[j-rodlength];
                    }
                 curr[j]=Math.max(take,nottake);
           }
           dp=curr;
       }
       return dp[n];
    }
}