class Solution{


    static Boolean isSubsetSum(int N, int arr[], int sum){
        // code here
        int dp[][]=new int[arr.length][sum+1];
        for(int a[]:dp)
        {
            Arrays.fill(a,-1);
        }
       // return isSubsetSumRecursion(N,arr,sum,arr.length-1);
      //  return isSubsetSumMemoization(N,arr,sum,arr.length-1,dp);
     // return isSubsetSumDP(N,arr,sum);
     return isSubsetSumDPSpaceOptimized(N,arr,sum);
    }

    //Recursive Approach
    public static boolean isSubsetSumRecursion(int N,int arr[],int sum,int i)
    {
        if(i<0 || sum<0)
        {
            return false;
        }
        if(sum==0)
        {
            return true;
        }
     
        boolean take=isSubsetSumRecursion(N,arr,sum-arr[i],i-1);
        boolean nottake=isSubsetSumRecursion(N,arr,sum,i-1);
        return take || nottake;
    }
    
    //Memoization Approach
    public static boolean isSubsetSumMemoization(int N,int arr[],int sum,int i,int dp[][])
    {
        // if(i<0)
        // {
        //     return false;
        // }
        if(sum==0)
        {
            return true;
        }
        if(sum<0)
        {
            return false;
        }
        if(i==0)
        {
            return arr[0]==sum;
        }
        if(dp[i][sum]!=-1)
        {
            return dp[i][sum]==0?false:true;
        }
        boolean take=isSubsetSumMemoization(N,arr,sum-arr[i],i-1,dp);
        boolean nottake=isSubsetSumMemoization(N,arr,sum,i-1,dp);
         dp[i][sum]=take || nottake?1:0;
         return take||nottake;
    }

    //DP Approach
    public static boolean isSubsetSumDP(int N,int arr[],int sum)
    {
        boolean dp[][]=new boolean[N][sum+1];
        for(int i=0;i<arr.length;i++)
        {
            dp[i][0]=true;
        }
       
        
        if(arr[0]<=sum)
        {
            dp[0][arr[0]]=true;
        }
        for(int i=1;i<N;i++)
        {
            for(int j=1;j<=sum;j++)
            {
                boolean nottake=dp[i-1][j];
                 boolean take=false;
                 if(arr[i]<=j)
                {
                    take=dp[i-1][j-arr[i]];
                }
                dp[i][j]=take || nottake;
            }
        }
        return dp[N-1][sum];
        
    }

    //DP Approach Space Optimized
     public static boolean isSubsetSumDPSpaceOptimized(int N,int arr[],int sum)
    {
        boolean dp[]=new boolean[sum+1];
  
       
        dp[0]=true;
        if(arr[0]<=sum)
        {
            dp[arr[0]]=true;
        }
        for(int i=1;i<N;i++)
        {
            boolean curr[]=new boolean[sum+1];
            for(int j=1;j<=sum;j++)
            {
                boolean nottake=dp[j];
                 boolean take=false;
                 if(arr[i]<=j)
                {
                    take=dp[j-arr[i]];
                }
                curr[j]=take || nottake;
            }
            dp=curr;
        }
        return dp[sum];
        
    }
}